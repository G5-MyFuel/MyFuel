package Contollers;

import boundary.CustomerRegistrationBoundary;
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
        varArray.add(costumer.getServicePlan());
        if (costumer.getCostumerVehicle().size() > 1) {
            ArrayList<Object> vehicleArray = new ArrayList<>();
            for (Vehicle v : costumer.getCostumerVehicle()) {
                vehicleArray.add(v.getVehicleID());
                vehicleArray.add(v.getGasType());
                vehicleArray.add(v.getOwnerID());
                SqlAction sqlActionForVehicle = new SqlAction(SqlQueryType.INSERT_NEW_VEHICLE, vehicleArray);
                super.sendSqlActionToClient(sqlActionForVehicle);
                vehicleArray.clear();
            }
        }
        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_COSTUMER, varArray);
        super.sendSqlActionToClient(sqlAction);
        tempCostumer.getCostumerVehicle().clear();
        tempCostumer = null;
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


    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_COSTUMER_TABLE:
                    ArrayList<Costumer> cosArray = new ArrayList<>();
                    cosArray.addAll(this.changeResultToCostumer(result));
                    myBoundary.setAllDBCostumerArray(cosArray);
                    break;
                case GET_ALL_VEHICLE_TABLE:
                    ArrayList<Vehicle> vehicleArray = new ArrayList<>();
                    vehicleArray.addAll(this.changeResultToVehicle(result));
                    myBoundary.setAllVehicleArray(vehicleArray);
                default:
                    break;
            }
        });

    }

    public void getVehicleTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_VEHICLE_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    public void getCostumerTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_COSTUMER_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    public Costumer getTempCostumer() {
        return tempCostumer;
    }

    public void setTempCostumer(Costumer tempCostumer) {
        this.tempCostumer = tempCostumer;
    }


    /**
     * This method create array list of costumers from the data base result.
     *
     * @param result the result
     * @return Array list of costumers
     */
    private ArrayList<Costumer> changeResultToCostumer(SqlResult result) {
        ArrayList<Costumer> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            Costumer cos = new Costumer((String) a.get(0), (String) a.get(1), (String) a.get(2),
                    (String) a.get(3), (String) a.get(4), (String) a.get(5), null, (String) a.get(9), null, (String) a.get(10));
            CreditCard card = new CreditCard(cos, (String) a.get(6), (String) a.get(7), (String) a.get(8));
            cos.setCostumerCreditCard(card);
            resultList.add(cos);
        }
        return resultList;
    }

    /**
     * This method create array list of Vehicles from the data base result.
     *
     * @param result the result
     * @return Array list of costumers
     */
    private ArrayList<Vehicle> changeResultToVehicle(SqlResult result) {
        ArrayList<Vehicle> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            Vehicle vehicle = new Vehicle((String) a.get(2), (String) a.get(0), (String) a.get(1));
            resultList.add(vehicle);
        }
        return resultList;
    }
}
