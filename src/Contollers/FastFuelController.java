package Contollers;

import boundary.fastFuelBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.Costumer;
import entity.CreditCard;
import entity.GasStation;
import entity.Vehicle;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 *  *  A department responsible for logical calculations and communicating with the client server and DB
 *  *  For page "fastFuelBoundary"
 *
 * @author Hana Wiener
 * @see fastFuelBoundary - - the form's Boundary class
 */
public class FastFuelController extends BasicController{
    /**
     * The boundary controlled by this controller
     */
    private fastFuelBoundary myBoundary;

    /**
     * Instantiates a new Marketing-Campaign Management controller.
     *
     * @param myBoundary
     */
    public FastFuelController(fastFuelBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }
    /*Logic Methods*/
    /**
     * this method send quarry GET_ALL_COSTUMER_TABLE to data base
     * in order to get the costumer details.
     */
    public void getOwnerDetails(String ownerID) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(ownerID);
        varArray.add(ownerID);
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_OWNER_DETAILS,varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    public void getVehicleDetails(String vehicleID){
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(vehicleID);
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_VEHICLE_DETAILS,varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    public void getAllStations(){
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_STATIONS);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * this method will start a quarry GET_ALL_VEHICLE_TABLE
     * in order to get all vehicles form data base
     */
    public void getVehicleTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_VEHICLE_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * this method will update fuel inventory in a spesific station.
     *
     * @param newInventory
     * @param stationNumber
     */
    public void updateFuelInventory(String newInventory,Integer stationNumber,String gasType){
        ArrayList<Object> varArray = new ArrayList<>();
        SqlAction sqlAction;
        varArray.add(newInventory);
        varArray.add(stationNumber);
        switch (gasType){
            case "Scooter Fuel":
                 sqlAction = new SqlAction(SqlQueryType.UPDATE_SCOOTER_INVENTORY_CUSTOMER_PURCHASE,varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Diesel":
                 sqlAction = new SqlAction(SqlQueryType.UPDATE_DIESEL_INVENTORY_CUSTOMER_PURCHASE,varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Gasoline-95":
                 sqlAction = new SqlAction(SqlQueryType.UPDATE_95_INVENTORY_CUSTOMER_PURCHASE,varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            default:
                break;
        }


    }
    public void insertNewOrderForStock(ArrayList<Object> varArray){
        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_ORDERFORSTOCK,varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    public void updatePurchase(ArrayList<Object> varArray){
        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_PURCHASE_FUEL_FOR_HOME_HEATING1,varArray);
        super.sendSqlActionToClient(sqlAction);
    }


    /**
     * This method is responsible for getting results from the client
     * Divided into cases to separate getting results from different queries
     *
     * @param result - The result recieved from the DB
     */
    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            try {
                switch (result.getActionType()) {
                    case GET_OWNER_DETAILS:
                        myBoundary.setOwnerAndDetails(this.changeResultToCostumer(result));
                        break;
                    case GET_VEHICLE_DETAILS:
                        Vehicle temp = this.changeResultToVehicle(result);
                        getOwnerDetails(temp.getOwnerID());
                        myBoundary.setCorrectVehicleFueling(temp);
                        break;
                    case GET_ALL_STATIONS:
                        myBoundary.setAllStation(this.changeResultToGasStation(result));
                        break;
                    case GET_ALL_VEHICLE_TABLE:
                        myBoundary.setAllVehicleArray(this.changeResultToVehicles(result));
                        break;
                    default:
                        break;

                }
            } catch (NullPointerException npe) {
            }
        });
    }




    /**
     * This method create array list of GasStation from the data base result.
     *
     * @param result the result
     * @return Array list of GasStation
     */
    private ArrayList<GasStation> changeResultToGasStation(SqlResult result) {
        //the table coloms: StationNumber, companyName, StationName, inventory_95, inventory_scooter, inventory_diesel, FuelLimit
        ArrayList<GasStation> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            GasStation gasStation = new GasStation((Integer) a.get(0), (String) a.get(1), (String) a.get(2), (String) a.get(3), (String) a.get(4), (String) a.get(5),
                    (String) a.get(6), (Double) a.get(7));
            resultList.add(gasStation);
        }
        return resultList;
    }


    private Vehicle changeResultToVehicle(SqlResult result){
        //result coloms: VehicleID, fuel Type, ownerID
        ArrayList<Object> a = new ArrayList<>(result.getResultData().get(0));
        return new Vehicle((String)a.get(2),(String)a.get(0),(String)a.get(1));
    }
    /**
     * This method create array list of Vehicles from the data base result.
     *
     * @param result the result
     * @return Array list of costumers
     */
    private ArrayList<Vehicle> changeResultToVehicles(SqlResult result) {
        ArrayList<Vehicle> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            Vehicle vehicle = new Vehicle((String) a.get(2), (String) a.get(0), (String) a.get(1));
            resultList.add(vehicle);
        }
        return resultList;
    }

    /**
     * This method create array list of costumers from the data base result.
     *
     * @param result the result
     * @return Array list of costumers
     */
    private Costumer changeResultToCostumer(SqlResult result) {
        /*result coloms: 0-ID ,1-creditCardNumber ,2-cardExpirationDate ,3-CVV ,4-cosType ,5-pricingModel ,6-purchasePlan ,7-userID ,8-userType ,
         9-userPassword, 10-isLogin, 11-firstName, 12-lastName, 13-Email, 14-FuelCompany1, 15-FuelCompany2, 16-FuelCompany3
         */
        ArrayList<Object> a = new ArrayList<>(result.getResultData().get(0));
        ArrayList<String> stations = new ArrayList<>();
        Costumer ownerOfVehicle;
        ownerOfVehicle = new Costumer((String) a.get(0), (String) a.get(9), (String) a.get(4),
                (String) a.get(11), (String) a.get(12), (String) a.get(13), null, (String) a.get(6), (String) a.get(5));
        //add fuel companies.
        stations.add((String) a.get(14));
        stations.add((String) a.get(15));
        stations.add((String) a.get(16));
        ownerOfVehicle.setFuelCompany(stations);
        CreditCard card = new CreditCard(ownerOfVehicle, (String) a.get(1), (String) a.get(2), (String) a.get(3));
        ownerOfVehicle.setCostumerCreditCard(card);
        return ownerOfVehicle;
    }


}

 /* case GET_GASSTATION_INVENTORY_TABLE:
                        ArrayList<GasStation> resultList2 = new ArrayList<>();
                        resultList2.addAll(this.changeResultToGasStation(result));
                       myBoundary.setSalesTable(resultList2); // ??
                        break;
                    case UPDATE_DIESEL_INVENTORY_CUSTOMER_PURCHASE:
                        break;
                    case UPDATE_95_INVENTORY_CUSTOMER_PURCHASE:
                        break;
                    case UPDATE_SCOOTER_INVENTORY_CUSTOMER_PURCHASE:
                        break;
                        // IF STOCK OUT OF LIMIT -> ALART
                        */

/*
//hani comments - myabie will be good for use later.

                    case INSERT_FASTFUEL_PURCHES:
                        break;
                    case INSERT_FASTFUEL_PURCHES_TO_FASTFUEL_TABLE:
                        break;
                   case GET_GASSTATION_INVENTORY_TABLE:
                        ArrayList<GasStation> resultList2 = new ArrayList<>();
                        resultList2.addAll(this.changeResultToGasStation(result));
                       myBoundary.setSalesTable(resultList2); // ??
                        break;
                    case UPDATE_DIESEL_INVENTORY_CUSTOMER_PURCHASE:
                        break;
                    case UPDATE_95_INVENTORY_CUSTOMER_PURCHASE:
                        break;
                    case UPDATE_SCOOTER_INVENTORY_CUSTOMER_PURCHASE:
                        break;
                        // IF STOCK OUT OF LIMIT -> ALART
                        */