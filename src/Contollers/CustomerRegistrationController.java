package Contollers;

import boundary.CustomerRegistrationBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.Costumer;
import entity.CreditCard;
import entity.Vehicle;
import javafx.application.Platform;

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
    private String companyName = "";

    private Costumer tempCostumer;


    public CustomerRegistrationController(CustomerRegistrationBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /*Logic Methods*/

    public void setCostumerInDB(Costumer costumer) {
        //set Costumer data into CostumerTablevarArray
        ArrayList<Object> CostumerTablevarArray = new ArrayList<>();
        //bulding customer
        CostumerTablevarArray.add(costumer.getUserID());
        if (costumer.getCostumerCreditCard() != null) {
            CostumerTablevarArray.add(costumer.getCostumerCreditCard().getCardNumber());
            CostumerTablevarArray.add(costumer.getCostumerCreditCard().getExperationDate());
            CostumerTablevarArray.add(costumer.getCostumerCreditCard().getCardSecurityNumber());
        } else {
            for (int i = 0; i < 3; i++)
                CostumerTablevarArray.add("No Card Exists");
        }
        CostumerTablevarArray.add(costumer.getCostumerType());
        CostumerTablevarArray.add(costumer.getPurchasePlan());
        CostumerTablevarArray.add(costumer.getServicePlan());
        //
        //building user.
        ArrayList<Object> UserTablevarArray = new ArrayList<>();
        UserTablevarArray.add(costumer.getUserID());
        UserTablevarArray.add("Aa" + costumer.getUserPassword());
        UserTablevarArray.add(costumer.getUserFirstName());
        UserTablevarArray.add(costumer.getUserLastName());
        UserTablevarArray.add(costumer.getUserEmail());
        UserTablevarArray.add(companyName);
        //
        //inserting costumer vehicles
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
        //execute quarry
        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_COSTUMER, CostumerTablevarArray);
        super.sendSqlActionToClient(sqlAction);
        SqlAction sqlAction2 = new SqlAction(SqlQueryType.INSERT_NEW_COSTUMER_USER, UserTablevarArray);
        super.sendSqlActionToClient(sqlAction2);
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
            Costumer cos = new Costumer((String) a.get(0), (String) a.get(9), (String) a.get(4),
                    (String) a.get(11), (String) a.get(12), (String) a.get(13), null, (String) a.get(5), null, (String) a.get(6));
            CreditCard card = new CreditCard(cos, (String) a.get(1), (String) a.get(2), (String) a.get(3));
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
