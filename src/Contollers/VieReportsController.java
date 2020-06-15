package Contollers;

import boundary.VieReportsBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import javafx.application.Platform;

import java.util.ArrayList;

public class VieReportsController extends BasicController{

    /**
     * The boundary controlled by this controller
     */
    private final VieReportsBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public VieReportsController(VieReportsBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }


    public void GetReportData(ArrayList<String> paramArray) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.addAll(paramArray);
        varArray.remove(0);
        System.out.println(paramArray);
        System.out.println(varArray);
        switch (paramArray.get(0)) {
            case "View Quarterly revenue report":
                SqlAction sqlAction = new SqlAction(SqlQueryType.View_Quarterly_Revenue, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            default:
                break;
        }
    }


    @Override
    public void getResultFromClient(SqlResult result) {

        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case View_Quarterly_Revenue:
                    myBoundary.setQuarterlyData(this.changeResultToQuarterlyReport(result));
                    break;
                /*case GET_Quarterly_Revenue:
                    myBoundary.setQuarterlyData(this.changeResultToQuarterlyReport(result));
                    break;
                case GET_Purchases_Report:
                    //PurchasesReport resultList = changeResultToPurchasesReport(result);
                    myBoundary.setPurchasesData(changeResultToPurchasesReport(result));
                    break;
                case GET_QuantityItemsStock_Report:
                    myBoundary.setQuantityItemsStockData(changeResultToQuantityItemsStockReport(result));
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

        String revenue = "There is no revenue for this quarter";
        for (ArrayList<Object> a : result.getResultData()) {
            revenue = (String)a.get(0);
        }
        return revenue;
    }
}