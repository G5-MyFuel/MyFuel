package Contollers;

import boundary.GeneratingReportsStationManagerBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.PurchasesReport;
import entity.QuantityItemsStockReport;
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

    public void GetReportData(ArrayList<String> paramArray/*String ReportType, String startDate, String endDate*/) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.addAll(paramArray);
        varArray.remove(0);
        System.out.println(paramArray);
        System.out.println(varArray);
        switch (paramArray.get(0)) {
            case "Get Manager data":
                SqlAction sqlAction = new SqlAction(SqlQueryType.GET_Manager_Data, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Quarterly revenue report":
                //varArray.add(endDate);
                sqlAction = new SqlAction(SqlQueryType.GET_Quarterly_Revenue, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Purchases report":
                sqlAction = new SqlAction(SqlQueryType.GET_Purchases_Report, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Quantity of items in stock report":
                sqlAction = new SqlAction(SqlQueryType.GET_QuantityItemsStock_Report, varArray);
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
                case GET_Manager_Data:
                    myBoundary.setManagerData(this.changeResultToManagerData(result));
                    break;
                case GET_Quarterly_Revenue:
                    myBoundary.setQuarterlyData(this.changeResultToQuarterlyReport(result));
                    break;
                case GET_Purchases_Report:
                    PurchasesReport resultList = changeResultToPurchasesReport(result);
                    myBoundary.setPurchasesData(resultList);
                    break;
                case GET_QuantityItemsStock_Report:
                    myBoundary.setQuantityItemsStockData(changeResultToQuantityItemsStockReport(result));
                    break;
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
            TotalPrice += Float.parseFloat((String) a.get(0));
        }
        return TotalPrice.toString();
    }

    private PurchasesReport changeResultToPurchasesReport(SqlResult result) {

        Integer salesAmount = 0;
        Float quantityPurchased = new Float(0);
        String FuelType = new String();
        for (ArrayList<Object> a : result.getResultData()) {
            FuelType = (String) a.get(0);
            quantityPurchased += Float.parseFloat((String) a.get(1));
            salesAmount++;
        }
        PurchasesReport resultList = new PurchasesReport(FuelType, quantityPurchased.toString() + " liters", salesAmount.toString() + " purchase");
        return resultList;
    }

    private ArrayList<QuantityItemsStockReport> changeResultToQuantityItemsStockReport(SqlResult result) {

        ArrayList<QuantityItemsStockReport> resultList = new ArrayList<>();

        for (ArrayList<Object> a : result.getResultData()) {
            resultList.add(new QuantityItemsStockReport((String )a.get(0)+" liters"));
            resultList.add(new QuantityItemsStockReport((String )a.get(1)+" liters"));
            resultList.add(new QuantityItemsStockReport((String )a.get(2)+" liters"));
        }
        return resultList;
    }

    private ArrayList<String> changeResultToManagerData(SqlResult result) {

        ArrayList<String> resultList = new ArrayList<>();

        for (ArrayList<Object> a : result.getResultData()) {
            resultList.add((String) a.get(1));
            resultList.add((String) a.get(2));
        }
        return resultList;
    }
}