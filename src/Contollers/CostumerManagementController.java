package Contollers;

import boundary.CostumerManagmentTablePageBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.Costumer;
import entity.CreditCard;
import entity.Vehicle;
import javafx.application.Platform;

import java.util.ArrayList;
/**
 * the class Costumer Management Controller
 *
 * @author Itay Ziv
 * @see CostumerManagementController - the form's Controller class
 *
 */

public class CostumerManagementController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private CostumerManagmentTablePageBoundary myBoundary;
    private ArrayList<Vehicle> dbVehicles = new ArrayList<>();

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public CostumerManagementController(CostumerManagmentTablePageBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /**
     * this method send quarry GET_ALL_COSTUMER_TABLE to data base
     * in order to get the costumer details.
     */
    public void getCostumerTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_COSTUMER_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * this method send quarry GET_ALL_VEHICLE_TABLE to get all
     * the vehicle table from data base
     */
    public void getVehicleTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_VEHICLE_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * this method get a quarry type and UPDATE specific costumer
     * and parameter of costumer in databse
     * @param colm
     * @param cosID
     * @param val
     */
    public void updateCostumerDetailsInDb(SqlQueryType colm, String cosID, String val) {
        //set Costumer data into varArray
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(val);
        varArray.add(cosID);
        SqlAction sqlAction = new SqlAction(colm, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * this method UPDATE costumer stations in data base.
     * @param cos
     */
    public void setCostumerStation(Costumer cos) {
        ArrayList<Object> varArray = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            varArray.add(cos.getFuelCompany().get(i));
        varArray.add(cos.getUserID());
        SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_USER_STATIONS, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * this method get the costumer vehicles.
     * @param CostumerID
     */
    public void getCostumerVehicles(String CostumerID) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(CostumerID);
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_COSTUMER_VEHICLES, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * this method remove a given vehicle form data base
     * @param vehicleID
     */
    public void removeVehicle(String vehicleID) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(vehicleID);
        SqlAction sqlAction = new SqlAction(SqlQueryType.REMOVE_VEHICLE, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * this method remove a given costumer from database.
     * @param CostumerID
     */
    public void removeCostumer(String CostumerID) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(CostumerID);
        SqlAction sqlAction = new SqlAction(SqlQueryType.REMOVE_COSTUMER, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    public void addVehicleToDB(String OwnerID, String VehicleID, String fuelType) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(VehicleID);
        varArray.add(fuelType);
        varArray.add(OwnerID);
        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_VEHICLE, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     *
     * @param result - The result received from the DB
     */
    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_COSTUMER_TABLE:
                    ArrayList<Costumer> resultList = new ArrayList<>();
                    resultList.addAll(this.changeResultToCostumer(result));
                    myBoundary.setCostumerTable(resultList);
                    break;
                case GET_ALL_COSTUMER_VEHICLES:
                    ArrayList<Vehicle> VehicleresultList = new ArrayList<>();
                    VehicleresultList.addAll(this.changeResultToVehicleArr(result));
                    myBoundary.setVehicleTable(VehicleresultList);
                    break;
                case GET_ALL_VEHICLE_TABLE:
                    this.changeResultToVehicle(result);
                    break;
                default:
                    break;
            }
        });
    }

    /**
     *
     * @param vehicleID
     * @return true or false
     */
    public boolean isVehicleExistInDb(String vehicleID) {
        for (Vehicle v : dbVehicles) {
            if (v.getVehicleID().equals(vehicleID))
                return true;
        }
        return false;
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
                    (String) a.get(11), (String) a.get(12), (String) a.get(13), null, (String) a.get(6), (String) a.get(5));
            //add fuel companies.
            temp.add((String)a.get(14));
            temp.add((String)a.get(15));
            temp.add((String)a.get(16));
            cos.setFuelCompany(temp);
            temp.clear();
            CreditCard card = new CreditCard(cos, (String) a.get(1), (String) a.get(2), (String) a.get(3));
            cos.setCostumerCreditCard(card);
            for (Vehicle v : dbVehicles) {
                if(v.getOwnerID().equals(cos.getUserID()))
                    cos.getCostumerVehicle().add(v);
            }
            resultList.add(cos);
        }
        return resultList;
    }

    /**
     * This method create array list of Vehicles of specific costumer from the data base result.
     *
     * @param result the result
     * @return Array list of Vehicles
     */
    private ArrayList<Vehicle> changeResultToVehicleArr(SqlResult result) {
        ArrayList<Vehicle> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            Vehicle v = new Vehicle((String) a.get(2), (String) a.get(0), (String) a.get(1));
            resultList.add(v);
        }
        return resultList;
    }

    /**
     * This method create array list of Vehicles from the data base result.
     *
     * @param result the result
     * @return Array list of costumers
     */
    private void changeResultToVehicle(SqlResult result) {
        dbVehicles = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            Vehicle vehicle = new Vehicle((String) a.get(2), (String) a.get(0), (String) a.get(1));
            dbVehicles.add(vehicle);
        }
    }

    public ArrayList<Vehicle> getDbVehicles() {
        return dbVehicles;
    }

    public void setDbVehicles(ArrayList<Vehicle> dbVehicles) {
        this.dbVehicles = dbVehicles;
    }
}
