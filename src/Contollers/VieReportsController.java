package Contollers;

import boundary.VieReportsBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.PurchasesReport;
import entity.QuantityItemsStockReport;
import javafx.application.Platform;

import java.util.ArrayList;

public class VieReportsController extends BasicController {

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
                SqlAction sqlAction = new SqlAction(SqlQueryType.View_Quarterly_Report, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "View Purchases report":
                sqlAction = new SqlAction(SqlQueryType.View_Purchases_Report, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "View Quantity of items in stock report":
                sqlAction = new SqlAction(SqlQueryType.View_QuantityItemsStock_Report, varArray);
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
                case View_Quarterly_Report:
                    myBoundary.setQuarterlyData(this.changeResultToQuarterlyReport(result));
                    break;
                case View_Purchases_Report:
                    myBoundary.setPurchasesData(this.changeResultToPurchasesReport(result));
                    break;
                case View_QuantityItemsStock_Report:
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

        String revenue = "There is no revenue for this quarter";
        for (ArrayList<Object> a : result.getResultData()) {
            revenue = (String) a.get(0);
        }
        return revenue;
    }

    /**
     * This method create String from the data base result.
     *
     * @param result the result
     * @return String
     */
    private ArrayList<PurchasesReport> changeResultToPurchasesReport(SqlResult result) {

        String[] fuelAmount = new String[]{"0.0 liters", "0.0 liters", "0.0 liters"};
        String[] salesAmount = new String[]{"0 purchase", "0 purchase", "0 purchase"};
        ArrayList<PurchasesReport> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            System.out.println(a);
            if (((String) a.get(0)).equals("Gasoline 95")) {
                fuelAmount[0] = (String) a.get(1);
                salesAmount[0] = (String) a.get(2);
            }
            if (((String) a.get(0)).equals("Diesel")) {
                fuelAmount[1] = (String) a.get(1);
                salesAmount[1] = (String) a.get(2);
            }
            if (((String) a.get(0)).equals("Scooter fuel")) {
                fuelAmount[2] = (String) a.get(1);
                salesAmount[2] = (String) a.get(2);
            }
        }
        resultList.add(new PurchasesReport("Gasoline 95"));
        resultList.add(new PurchasesReport("Diesel"));
        resultList.add(new PurchasesReport("Scooter fuel"));
        for (int i = 0; i < 3; i++) {
            resultList.get(i).setQuantityPurchased(fuelAmount[i]);
            resultList.get(i).setSalesAmount(salesAmount[i]);
        }
        return resultList;
    }

    private ArrayList<QuantityItemsStockReport> changeResultToQuantityItemsStockReport(SqlResult result) {

        String[] fuelAvailableInventory = new String[]{"0.0 liters", "0.0 liters", "0.0 liters"};
        ArrayList<QuantityItemsStockReport> resultList = new ArrayList<>();

        for (ArrayList<Object> a : result.getResultData()) {
            System.out.println(a);
            if (((String) a.get(0)).equals("Gasoline 95"))
                fuelAvailableInventory[0] = (String) a.get(1);
            if (((String) a.get(0)).equals("Diesel"))
                fuelAvailableInventory[1] = (String) a.get(1);
            if (((String) a.get(0)).equals("Scooter fuel"))
                fuelAvailableInventory[2] = (String) a.get(1);
        }

        for (int i = 0; i < 3; i++)
            //resultList.get(i).setQuantityPurchased(fuelAmount[i]);
            resultList.add(new QuantityItemsStockReport(fuelAvailableInventory[i]));
        return resultList;
    }
}