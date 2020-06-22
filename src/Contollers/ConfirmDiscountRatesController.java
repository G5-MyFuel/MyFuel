package Contollers;

import boundary.ConfirmDiscountRatesBoundary;
import boundary.MarketingCampaignTemplateBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.DiscountRate;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 *  A department responsible for logical calculations and communicating with the client server and DB
 *  For page "ConfirmDiscountRatesBoundary"
 *
 *  * @author Nir Asulin
 * @see ConfirmDiscountRatesBoundary - the form's gui controller (boundary) class
 */

public class ConfirmDiscountRatesController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private final ConfirmDiscountRatesBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public ConfirmDiscountRatesController(ConfirmDiscountRatesBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /**
     * This method is responsible for requesting information from DB through the server
     * Divided into cases to separate sending a different queries
     * @param paramArray - An array of variables for query
     */
    public void GetDiscountRatesData(ArrayList<String> paramArray) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.addAll(paramArray);
        varArray.remove(0);
        System.out.println(varArray);
        switch (paramArray.get(0)) {
            case "Get Discount Rates Data":
                SqlAction sqlAction = new SqlAction(SqlQueryType.Get_DiscountRates_Table, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Update New Discount Rate":
                sqlAction = new SqlAction(SqlQueryType.UPDATE_NEW_DiscountRate, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Remove New Discount Rate":
                sqlAction = new SqlAction(SqlQueryType.Remove_NEW_DiscountRate, varArray);
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
            switch (result.getActionType()) {
                case GET_Manager_Data:
                    myBoundary.setManagerData(this.changeResultToManagerData(result));
                    break;
                case Get_DiscountRates_Table:
                    myBoundary.setDiscountRatesData(this.changeResultToDiscountRatesData(result));
                    break;
                case Remove_NEW_DiscountRate:
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * This method create array list of DiscountRate from the data base result.
     *
     * @param result - The result received from the DB
     * @return Array list of DiscountRate
     */
    private ArrayList<DiscountRate> changeResultToDiscountRatesData(SqlResult result) {

        ArrayList<DiscountRate> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            resultList.add(new DiscountRate((String) a.get(0), (String) a.get(1), (String) a.get(3)));
        }
        System.out.println(resultList);
        return resultList;
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