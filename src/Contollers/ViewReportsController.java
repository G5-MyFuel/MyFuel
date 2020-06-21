package Contollers;

import boundary.GeneratingReportsStationManagerBoundary;
import boundary.ViewReportsBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.PurchasesReport;
import entity.QuantityItemsStockReport;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 * A department responsible for logical calculations and communicating with the client server and DB
 * For page "ViewReportsBoundary"
 * <p>
 * * @author Nir Asulin
 *
 * @see ViewReportsBoundary - the form's gui controller (boundary) class
 */

public class ViewReportsController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private final ViewReportsBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public ViewReportsController(ViewReportsBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /**
     * This method is responsible for requesting information from DB through the server
     * Divided into cases to separate sending a different queries
     *
     * @param paramArray - An array of variables for query
     */
    public void GetReportData(ArrayList<String> paramArray) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.addAll(paramArray);
        varArray.remove(0);
        System.out.println(varArray);
        switch (paramArray.get(0)) {
            case "Get Manager data":
                SqlAction sqlAction = new SqlAction(SqlQueryType.GET_Manager_Data, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Check if exists Quarterly revenue report":
                sqlAction = new SqlAction(SqlQueryType.CheckIfExists_Quarterly_Report, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "View Quarterly revenue report":
                sqlAction = new SqlAction(SqlQueryType.View_Quarterly_Report, varArray);
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
                case CheckIfExists_Quarterly_Report:
                    myBoundary.checkIfExists(this.checkIfExists(result));
                    break;
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
     * This method create Quarterly Report from the data base result.
     *
     * @param result - The result received from the DB
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
     * This method create array list of Purchases Report from the data base result.
     *
     * @param result - The result received from the DB
     * @return ArrayList<PurchasesReport>
     */
    private ArrayList<PurchasesReport> changeResultToPurchasesReport(SqlResult result) {

        String[] fuelAmount = new String[]{"0.0 liters", "0.0 liters", "0.0 liters"};
        String[] salesAmount = new String[]{"0 purchase", "0 purchase", "0 purchase"};
        ArrayList<PurchasesReport> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
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

    /**
     * This method create array list of Quantity Items Stock Report from the data base result.
     *
     * @param result - The result received from the DB
     *
     * @return ArrayList<QuantityItemsStockReport>
     */
    private ArrayList<QuantityItemsStockReport> changeResultToQuantityItemsStockReport(SqlResult result) {

        String[] fuelAvailableInventory = new String[]{"0.0 liters", "0.0 liters", "0.0 liters"};
        ArrayList<QuantityItemsStockReport> resultList = new ArrayList<>();

        for (ArrayList<Object> a : result.getResultData()) {
            if (((String) a.get(0)).equals("Gasoline 95"))
                fuelAvailableInventory[0] = (String) a.get(1);
            if (((String) a.get(0)).equals("Diesel"))
                fuelAvailableInventory[1] = (String) a.get(1);
            if (((String) a.get(0)).equals("Scooter fuel"))
                fuelAvailableInventory[2] = (String) a.get(1);
        }

        for (int i = 0; i < 3; i++)
            resultList.add(new QuantityItemsStockReport(fuelAvailableInventory[i]));
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

    private String checkIfExists (SqlResult result){

        Long isExsits;
        ArrayList<Object> a = result.getResultData().get(0);
        isExsits = (Long) a.get(0);
        System.out.println(isExsits);
        return isExsits.toString();
    }
}