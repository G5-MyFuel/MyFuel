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


/** the class Customer Registration Controller
 * @author itay
 * @see CustomerRegistrationController - the form's logic class
 */

public class CustomerRegistrationController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private CustomerRegistrationBoundary myBoundary;
    private ArrayList<Costumer> allDBCostumerArray = new ArrayList<>();
    private ArrayList<Vehicle> allVehicleArray = new ArrayList<>();
    private Costumer tempCostumer;


    public CustomerRegistrationController(CustomerRegistrationBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /*Logic Methods*/

    public void setCostumerInDB(Costumer costumer) {
        tempCostumer = costumer;
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
        CostumerTablevarArray.add(costumer.getPricingModel());
        //
        //building user.
        ArrayList<Object> UserTablevarArray = new ArrayList<>();
        UserTablevarArray.add(costumer.getUserID());
        UserTablevarArray.add("Aa" + costumer.getUserPassword());
        UserTablevarArray.add(costumer.getUserFirstName());
        UserTablevarArray.add(costumer.getUserLastName());
        UserTablevarArray.add(costumer.getUserEmail());
        for (int i = 0; i < 3; i++) {//add fuel companies
            UserTablevarArray.add(costumer.getFuelCompany().get(i));
        }
        //
        //inserting costumer vehicles
        ArrayList<Object> vehicleArray = new ArrayList<>();
        for (Vehicle v : costumer.getCostumerVehicle()) {
            vehicleArray.add(v.getVehicleID());
            vehicleArray.add(v.getGasType());
            vehicleArray.add(v.getOwnerID());
            SqlAction sqlActionForVehicle = new SqlAction(SqlQueryType.INSERT_NEW_VEHICLE, vehicleArray);
            super.sendSqlActionToClient(sqlActionForVehicle);
            vehicleArray.clear();
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

    /**
     *
     * @param result - The result recieved from the DB
     */
    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_COSTUMER_TABLE:
                    allDBCostumerArray.addAll(this.changeResultToCostumer(result));
                    break;
                case GET_ALL_VEHICLE_TABLE:
                    allVehicleArray.addAll(this.changeResultToVehicle(result));
                    break;
                case INSERT_NEW_COSTUMER:
                    myBoundary.onRegisterSuccses();
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
        ArrayList<String> temp = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            Costumer cos = new Costumer((String) a.get(0), (String) a.get(9), (String) a.get(4),
                    (String) a.get(11), (String) a.get(12), (String) a.get(13), null, (String) a.get(6), null, (String) a.get(5));
            //add fuel companies.
            temp.add((String) a.get(14));
            temp.add((String) a.get(15));
            temp.add((String) a.get(16));
            cos.setFuelCompany(temp);
            temp.clear();
            CreditCard card = new CreditCard(cos, (String) a.get(1), (String) a.get(2), (String) a.get(3));
            cos.setCostumerCreditCard(card);
            resultList.add(cos);
        }
        return resultList;
    }

    public boolean isCostumerExist(String cosID) {
        for (Costumer cos : allDBCostumerArray) {
            if (cos.getUserID().equals(cosID))
                return true;
        }
        return false;
    }

    public boolean isVehicleExistInDb(String vehicleID) {
        for (Vehicle v : allVehicleArray) {
            if (v.getVehicleID().equals(vehicleID))
                return true;
        }
        return false;
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
