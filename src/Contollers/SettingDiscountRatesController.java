package Contollers;

import boundary.SettingDiscountRatesBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 * A department responsible for logical calculations and communicating with the client server and DB
 * For page "SettingDiscountRatesBoundary"
 * <p>
 * * @author Nir Asulin
 *
 * @see SettingDiscountRatesBoundary - the form's gui controller (boundary) class
 */

public class SettingDiscountRatesController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private final SettingDiscountRatesBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public SettingDiscountRatesController(SettingDiscountRatesBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /**
     * This method is responsible for requesting information from DB through the server
     * Divided into cases to separate sending a different queries
     *
     * @param paramArray - An array of variables for query
     */
    public void getDiscountRatesTable(ArrayList<String> paramArray /*String SubscriptionType*/) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.addAll(paramArray);
        varArray.remove(0);
        switch (paramArray.get(0)) {
            case "Regular monthly subscription (single)":   //20
                SqlAction sqlAction = new SqlAction(SqlQueryType.GET_RegularSubscriptionSingleVehicle_PRICE, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Full monthly subscription":  //7
                sqlAction = new SqlAction(SqlQueryType.GET_FullSubscriptionSingleVehicle_PRICE, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Regular monthly subscription (multiple)":   //15
                sqlAction = new SqlAction(SqlQueryType.GET_RegularSubscriptionMultiVehicle_PRICE, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Insert NewRate":
                sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_PRICE, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Get Manager data":
                sqlAction = new SqlAction(SqlQueryType.GET_Manager_Data, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            default:
                break;
        }
    }

    /**
     * This method is responsible for getting results from the client
     * Divided into cases to separate getting results from different queries
     *
     * @param result - The result received from the DB
     */
    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            try {
                switch (result.getActionType()) {
                    case GET_Manager_Data:
                        myBoundary.setManagerData(this.changeResultToManagerData(result));
                        break;
                    case GET_RegularSubscriptionSingleVehicle_PRICE:
                        myBoundary.setData(this.changeResultToString(result));
                        break;
                    case GET_FullSubscriptionSingleVehicle_PRICE:
                        myBoundary.setData(this.changeResultToString(result));
                        break;
                    case GET_RegularSubscriptionMultiVehicle_PRICE:
                        myBoundary.setData(this.changeResultToString(result));
                        break;
                    default:
                        break;
                }
            } catch (NullPointerException e) {

            }
        });
    }

    /**
     * This method create String from the data base result.
     *
     * @param result - The result received from the DB
     * @return String - The current subscription rate
     */
    private String changeResultToString(SqlResult result) {
        String revenue = null;
        ArrayList<Object> a = result.getResultData().get(0); //Saves first line from result to a (get(0))
        revenue = (String) a.get(1);    //Saves second column from a to revenue (get(1))
        return revenue;
    }

    /**
     * This method create array list of String from the data base result.
     *
     * @param result - The result received from the DB
     * @return Array list of String contains manager company and manager station
     */
    private ArrayList<String> changeResultToManagerData(SqlResult result) {

        ArrayList<String> resultList = new ArrayList<>();

        for (ArrayList<Object> a : result.getResultData()) {
            resultList.add((String) a.get(1));
            resultList.add((String) a.get(2));
        }
        return resultList;
    }
}