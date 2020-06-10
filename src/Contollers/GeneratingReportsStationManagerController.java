package Contollers;

import boundary.GeneratingReportsStationManagerBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import javafx.application.Platform;

import java.util.ArrayList;

public class GeneratingReportsStationManagerController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private final GeneratingReportsStationManagerBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public GeneratingReportsStationManagerController(GeneratingReportsStationManagerBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    public void QuarterlyReportData(String ReportType, String startDate, String endDate) {
        switch (ReportType) {
            case "Quarterly revenue report":
                ArrayList<Object> varArray = new ArrayList<>();
                varArray.add(startDate);
                varArray.add(endDate);
                SqlAction sqlAction = new SqlAction(SqlQueryType.GET_Quarterly_Revenue, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Purchases report":
                /*sqlAction = new SqlAction(SqlQueryType.GET_FullSubscriptionSingleVehicle_PRICE);
                super.sendSqlActionToClient(sqlAction);
                break;*/
            case "Quantity of items in stock report":
                /*sqlAction = new SqlAction(SqlQueryType.GET_RegularSubscriptionMultiVehicle_PRICE);
                super.sendSqlActionToClient(sqlAction);
                break;*/
            default:
                break;
        }
    }


    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_Quarterly_Revenue:
                    myBoundary.setData(this.changeResultToString(result));
                    break;
                /*case GET_FullSubscriptionSingleVehicle_PRICE:
                    myBoundary.setData(this.changeResultToString(result));
                    break;
                case GET_RegularSubscriptionMultiVehicle_PRICE:
                    myBoundary.setData(this.changeResultToString(result));
                    break;
                case INSERT_NEW_PRICE:
                    myBoundary.setData("");
                    break;*/

                default:
                    break;
            }
        });

    }

    /**
     * This method create String from the data base result.
     *
     * @param result the result
     * @return String
     */
    private String changeResultToString(SqlResult result) {

        Float TotalPrice = new Float(0);
        //ArrayList<String> revenue = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            TotalPrice += Float.parseFloat((String) a.get(4));
        }
        return TotalPrice.toString();
    }

}