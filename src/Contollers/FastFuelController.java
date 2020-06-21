package Contollers;

import boundary.fastFuelBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.*;
import javafx.application.Platform;

import java.sql.Date;
import java.util.ArrayList;

/**
 * @author Daniel Gabbay
 */
public class FastFuelController extends BasicController{
    private fastFuelBoundary myBoundary;

    public FastFuelController(fastFuelBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            try {
                switch (result.getActionType()) {
                    case GET_ALL_COSTUMER_TABLE:
                        ArrayList<Costumer> resultList = new ArrayList<>();
                        resultList.addAll(this.changeResultToCostumer(result));
                        myBoundary.setCostumerTable(resultList);
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
                    case GET_GASSTATION_INVENTORY_TABLE:
                        ArrayList<GasStation> resultList2 = new ArrayList<>();
                    //    resultList2.addAll(this.changeResultToGasStation(result));
                     //  myBoundary.setSalesTable(resultList2); // ??
                        break;
                  /*  case UPDATE_DIESEL_INVENTORY_CUSTOMER_PURCHASE:
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


    public void updateInvetory(String fuelType, ArrayList<String> myArray) {
        ArrayList<Object> varArray =  new  ArrayList<Object>();
        varArray.add(myArray.get(0));
        varArray.add(myArray.get(1));

        switch (fuelType) {
            case "Diesel":
                SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_95_INVENTORY_CUSTOMER_PURCHASE, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Gasoline95":
                 sqlAction = new SqlAction(SqlQueryType.UPDATE_DIESEL_INVENTORY_CUSTOMER_PURCHASE, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "ScooterFuel":
                 sqlAction = new SqlAction(SqlQueryType.UPDATE_SCOOTER_INVENTORY_CUSTOMER_PURCHASE, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
        };
    }


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

            GasStation gasStation = new GasStation((Integer) a.get(0),
                    (String) a.get(1),
                    null,null,
                    (String) a.get(2), (String) a.get(3),
                    (String) a.get(4),
                    (Double) a.get(5));
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
            temp.add((String)a.get(14));
            temp.add((String)a.get(15));
            temp.add((String)a.get(16));
            Costumer cos = new Costumer((String) a.get(0), null, null,
                    null, null, null, null, null, null);
            cos.setFuelCompany(temp);
            temp.clear();
            resultList.add(cos);
        }
        return resultList;
    }

}
