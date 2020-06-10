package Contollers;

import boundary.GeneratingReportsStationManagerBoundary;
import common.assets.SqlResult;
import javafx.application.Platform;

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

    public void QuarterlyReportData(String ReportType, String ReportYear, String Quarter) {
        switch (ReportType) {
            case "Quarterly revenue report":
                /*SqlAction sqlAction = new SqlAction(SqlQueryType.GET_RegularSubscriptionSingleVehicle_PRICE);
                super.sendSqlActionToClient(sqlAction);
                break;*/
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
        /*Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_RegularSubscriptionSingleVehicle_PRICE:
                    myBoundary.setData(this.changeResultToString(result));
                    break;
                case GET_FullSubscriptionSingleVehicle_PRICE:
                    myBoundary.setData(this.changeResultToString(result));
                    break;
                case GET_RegularSubscriptionMultiVehicle_PRICE:
                    myBoundary.setData(this.changeResultToString(result));
                    break;
                case INSERT_NEW_PRICE:
                    myBoundary.setData("");
                    break;

                default:
                    break;
            }
        });*/

    }

}