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
                    case GET_ALL_COSTUMER_TABLE:
                        ArrayList<Costumer> resultList = new ArrayList<>();
                        resultList.addAll(this.changeResultToCostumer(result));
                        myBoundary.setCostumersArry(resultList);
                        break;
                    case GET_ALL_COSTUMER_VEHICLES:
                        ArrayList<Vehicle> VehicalList = new ArrayList<>();
                        VehicalList.addAll(this.changeResultToCars(result));
                        myBoundary.setCarOfCustomer(VehicalList);
                        break;
                    case GET_OPTIONAL_STATIONS:
                        ArrayList<GasStation> resultList1 = new ArrayList<>();
                        resultList1.addAll(this.changeResultToUserStation(result));
                        myBoundary.setStationsInArrayAndChooseRandomly(resultList1);
                        break;

                     case INSERT_FASTFUEL_PURCHES:
                        break;
                    case INSERT_FASTFUEL_PURCHES_TO_FASTFUEL_TABLE:
                        break;
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
                }
            } catch (NullPointerException npe) {
            }
        });
    }

    /**
     * This method update Inventory of the appropriate fuel type in DB
     * @param fuelType
     * @param myArray
     */
    public void updateInvetory(String fuelType, ArrayList<String> myArray) {
        ArrayList<Object> varArray =  new  ArrayList<Object>();
        varArray.add(myArray.get(0));
        varArray.add(myArray.get(1));

        switch (fuelType) {
            case "Diesel":
                varArray.add("Diesel");
                SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_95_INVENTORY_CUSTOMER_PURCHASE, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Gasoline95":
                varArray.add("Gasoline95");
                 sqlAction = new SqlAction(SqlQueryType.UPDATE_DIESEL_INVENTORY_CUSTOMER_PURCHASE, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "ScooterFuel":
                varArray.add("ScooterFuel");
                sqlAction = new SqlAction(SqlQueryType.UPDATE_SCOOTER_INVENTORY_CUSTOMER_PURCHASE, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
        };
    }

    /**
     *  This method get a customer and find the gas stations he can refuel at
     * @param costumerID
     */
    public void getOptionalStationForCustomer(String costumerID) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(costumerID);
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_OPTIONAL_STATIONS, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * This method create array list of GasStation from the data base result.
     *
     * @param result the result
     * @return Array list of GasStation
     */
    private ArrayList<GasStation> changeResultToUserStation(SqlResult result){
        // gs.StationNumber, companyName,inventory_95 , inventory_scooter, inventory_diesel
        ArrayList<GasStation> resultList = new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
           ArrayList<String> gasStations = new ArrayList<String>();

            GasStation gasStation = new GasStation((Integer) a.get(0), (String) a.get(1),null,null,(String) a.get(2), (String) a.get(3),
                    (String) a.get(4),(Double) a.get(5));
            resultList.add(gasStation);
        }
        return resultList;
    }


    /**
     * this method send quarry GET_ALL_COSTUMER_TABLE to data base
     * in order to get the costumer details.
     */
    public void getCustomer() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_COSTUMER_TABLE);
        super.sendSqlActionToClient(sqlAction);
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

    /**
     * Get a customer and find his vehicles
     * @param costumerID
     */
    public void getCarsForCustomer(String costumerID) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(costumerID);
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_COSTUMER_VEHICLES, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * Modifies the results that came from DB for vehicles Array
     *
     * @param result
     * @return resultList of vehicle
     */
    private ArrayList<Vehicle> changeResultToCars(SqlResult result){
        //  `Vehicle ID`  |   `Fuel Type`   |   `Owner ID
        ArrayList<Vehicle> resultList = new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            Vehicle vehicle = new Vehicle((String) a.get(2), (String) a.get(0),(String) a.get(1));
            resultList.add(vehicle);
        }
        return resultList;
    }
}