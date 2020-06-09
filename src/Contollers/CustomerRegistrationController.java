package Contollers;

import boundary.CostumerManagmentTablePageBoundary;
import boundary.CustomerRegistrationBoundary;
import client.ClientApp;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.Costumer;
import entity.CreditCard;
import entity.Vehicle;
import javafx.application.Platform;
import server.MysqlConnection;

import java.util.ArrayList;

/**
 * @author itay
 * @see CustomerRegistrationController - the form's logic class
 */

public class CustomerRegistrationController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private CustomerRegistrationBoundary myBoundary;


    MysqlConnection mySqlConnector;
    private boolean insertCostumerFlag;
    private Costumer tempCostumer;


    public CustomerRegistrationController(CustomerRegistrationBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /*Logic Methods*/

    public void setCostumerInDB(Costumer costumer) {
        //set Costumer data into varArray
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(costumer.getID());
        varArray.add("Aa" + costumer.getCustomerPassword());
        varArray.add(costumer.getCostumerType());
        varArray.add(costumer.getFname());
        varArray.add(costumer.getLname());
        varArray.add(costumer.getEmailAdress());
        if (costumer.getCostumerCreditCard() != null) {
            varArray.add(costumer.getCostumerCreditCard().getCardNumber());
            varArray.add(costumer.getCostumerCreditCard().getExperationDate());
            varArray.add(costumer.getCostumerCreditCard().getCardSecurityNumber());
        } else {
            varArray.add("No Card Exists");
            varArray.add("No Card Exists");
            varArray.add("No Card Exists");
        }
        varArray.add(costumer.getPurchasePlan());
        varArray.add(costumer.getCostumerVehicle().get(0).getVehicleID());
        varArray.add(costumer.getCostumerVehicle().get(0).getGasType());
        varArray.add(costumer.getServicePlan());
        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_COSTUMER, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    public void addCostumerCreditCard(CreditCard card) {
        this.tempCostumer.setCostumerCreditCard(card);
    }

    public void setCostumerFirstPhase(Costumer costumer) {
        this.tempCostumer = costumer;
    }

    public void setCostumerSecoundPhase(ArrayList<Vehicle> vehicles) {
        tempCostumer.setCostumerVehicle(vehicles);
    }

    public void setCostumerThirdPhase(Costumer costumer) {

    }

    public Costumer getTempCostumer() {
        return tempCostumer;
    }

    public void setTempCostumer(Costumer tempCostumer) {
        this.tempCostumer = tempCostumer;
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_COSTUMER_TABLE:
                    ArrayList<Costumer> resultList = new ArrayList<>();
                    resultList.addAll(this.changeResultToCostumer(result));
                    myBoundary.setAllDBCostumerArray(resultList);
                    break;
                case GET_ALL_VEHICLE_TABLE:
                    ArrayList<Vehicle> vehicaleArray = new ArrayList<>();

                default:
                    break;
            }
        });

    }

    public void getVehicaleTable(){
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_VEHICLE_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    public void getCostumerTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_COSTUMER_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }












    /**
     * This method create array list of costumers from the data base result.
     *
     * @param result the result
     * @return Array list of costumers
     */
    private ArrayList<Costumer> changeResultToCostumer(SqlResult result){
        ArrayList<Costumer> resultList=new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            Costumer cos = new Costumer((Integer) a.get(0), (String)a.get(1),(String)a.get(2),
                    (String)a.get(3),(String)a.get(4),(String)a.get(5),null,(boolean)a.get(9),null,(String)a.get(12));
            CreditCard card = new CreditCard(cos,(String)a.get(6),(String)a.get(7),(String)a.get(8));
            Vehicle vehicle = new Vehicle(cos.getID().toString(),(String)a.get(10),(String)a.get(11)); //here i need to find a way to get all vehicles and not just 1 of them.
            cos.setCostumerCreditCard(card);
            cos.addCostumerVehicle(vehicle);
            resultList.add(cos);
        }
        return resultList;
    }
}
