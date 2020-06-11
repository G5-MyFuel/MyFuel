package Contollers;

import boundary.GeneratingReportsStationManagerBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.SaleOperation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public void GetReportData(String ReportType, String startDate, String endDate) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(startDate);
        switch (ReportType) {
            case "Quarterly revenue report":
                varArray.add(endDate);
                SqlAction sqlAction = new SqlAction(SqlQueryType.GET_Quarterly_Revenue, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Purchases report":
                sqlAction = new SqlAction(SqlQueryType.GET_Purchases_Report, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Quantity of items in stock report":
                /*sqlAction = new SqlAction(SqlQueryType.GET_RegularSubscriptionMultiVehicle_PRICE);
                super.sendSqlActionToClient(sqlAction);
                break;*/
            default:
                break;
        }
    }

    /*public void PurchasesReportData(String ReportType, String startDate) {

        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(startDate);
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_Purchases_Report, varArray);
        super.sendSqlActionToClient(sqlAction);
    }*/

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_Quarterly_Revenue:
                    myBoundary.setQuarterlyData(this.changeResultToQuarterlyReport(result));
                    break;
                case GET_Purchases_Report:
                    ArrayList<String> resultList = new ArrayList<>();
                    resultList.addAll(this.getDieselpurchaseID(result));
                    //int salesAmount = 0;
                    myBoundary.setPurchasesData(resultList);

                    break;
                /*case GET_RegularSubscriptionMultiVehicle_PRICE:
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
    private String changeResultToQuarterlyReport(SqlResult result) {

        Float TotalPrice = new Float(0);
        for (ArrayList<Object> a : result.getResultData()) {
            TotalPrice += Float.parseFloat((String) a.get(4));
        }
        return TotalPrice.toString();
    }

    private ArrayList<String> getDieselpurchaseID(SqlResult result) {

        ArrayList<String> resultList = new ArrayList<>();
        Integer salesAmount = 0;
        Float TotalPrice = new Float(0);
        //ArrayList<String> purchaseID = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            TotalPrice += Float.parseFloat((String) a.get(3));
            salesAmount++;
            //purchaseID.add((String) a.get(0));
        }
        resultList.add(TotalPrice.toString());
        resultList.add(salesAmount.toString());
        return resultList;
    }

}