package Contollers;

import boundary.GeneratingReportsStationManagerBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.FuelTypes;
import entity.PurchasesReport;
import entity.QuantityItemsStockReport;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Arrays;

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
            case "Send Quarterly revenue report":
                sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_Quarterly_Report, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Send Purchases report":
                sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_Purchases_Report, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Send Quantity of items in stock report":
                sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_QuantityItemsStock_Report, varArray);
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
                    //PurchasesReport resultList = changeResultToPurchasesReport(result);
                    myBoundary.setPurchasesData(changeResultToPurchasesReport(result));
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

    private ArrayList<PurchasesReport> changeResultToPurchasesReport(SqlResult result) {

        Float[] fuelAmount = new Float[]{Float.valueOf(0),Float.valueOf(0),Float.valueOf(0)};
        Integer[] salesAmount = new Integer[]{0,0,0};
        ArrayList<PurchasesReport> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            if (((String) a.get(0)).equals("Gasoline 95")) {
                fuelAmount[0] += Float.parseFloat((String) a.get(1));
                salesAmount[0]++;
            }
            if (((String) a.get(0)).equals("Diesel")) {
                fuelAmount[1] += Float.parseFloat((String) a.get(1));
                salesAmount[1]++;
            }
            if (((String) a.get(0)).equals("Scooter fuel")) {
                fuelAmount[2] += Float.parseFloat((String) a.get(1));
                salesAmount[2]++;
            }
        }
        resultList.add(new PurchasesReport("Gasoline 95"));
        resultList.add(new PurchasesReport("Diesel"));
        resultList.add(new PurchasesReport("Scooter fuel"));
        for (int i = 0; i < 3; i++) {
            resultList.get(i).setQuantityPurchased(fuelAmount[i].toString() + " liters");
            resultList.get(i).setSalesAmount(salesAmount[i].toString() + " purchase");
        }
        return resultList;
    }

    private ArrayList<QuantityItemsStockReport> changeResultToQuantityItemsStockReport(SqlResult result) {

        ArrayList<QuantityItemsStockReport> resultList = new ArrayList<>();

        for (ArrayList<Object> a : result.getResultData()) {
            resultList.add(new QuantityItemsStockReport((String) a.get(0) + " liters"));
            resultList.add(new QuantityItemsStockReport((String) a.get(1) + " liters"));
            resultList.add(new QuantityItemsStockReport((String) a.get(2) + " liters"));
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