package Contollers;

import boundary.GeneratingReportsMarketingManagerBoundary;
import boundary.GeneratingReportsStationManagerBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.PurchasesReport;
import entity.QuantityItemsStockReport;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 *  A department responsible for logical calculations and communicating with the client server and DB
 *  For page "GeneratingReportsStationManagerBoundary"
 *
 *  * @author Nir Asulin
 * @see GeneratingReportsStationManagerBoundary - the form's gui controller (boundary) class
 */

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

    /**
     * This method is responsible for requesting information from DB through the server
     * Divided into cases to separate sending a different queries
     * @param paramArray - An array of variables for query
     */
    public void GetReportData(ArrayList<String> paramArray) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.addAll(paramArray);
        varArray.remove(0);
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
                case GET_Quarterly_Revenue:
                    myBoundary.setQuarterlyData(this.changeResultToQuarterlyReport(result));
                    break;
                case GET_Purchases_Report:
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
     * This method create Quarterly Report from the data base result.
     *
     * @param result - The result received from the DB
     *
     * @return String
     */
    private String changeResultToQuarterlyReport(SqlResult result) {

        Double TotalPrice = new Double(0);
        for (ArrayList<Object> a : result.getResultData()) {
            TotalPrice += (Double) a.get(0);
        }
        return TotalPrice.toString();
    }

    /**
     * This method create array list of Purchases Report from the data base result.
     *
     * @param result - The result received from the DB
     *
     * @return ArrayList<PurchasesReport>
     */
    private ArrayList<PurchasesReport> changeResultToPurchasesReport(SqlResult result) {

        Double[] fuelAmount = new Double[]{Double.valueOf(0),Double.valueOf(0),Double.valueOf(0)};
        Integer[] salesAmount = new Integer[]{0,0,0};
        ArrayList<PurchasesReport> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            if (((String) a.get(0)).equals("Gasoline95")) {
                fuelAmount[0] += (Double) a.get(1);
                salesAmount[0]++;
            }
            if (((String) a.get(0)).equals("Diesel")) {
                fuelAmount[1] += (Double) a.get(1);
                salesAmount[1]++;
            }
            if (((String) a.get(0)).equals("ScooterFuel")) {
                fuelAmount[2] += (Double) a.get(1);
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

    /**
     * This method create array list of Quantity Items Stock Report from the data base result.
     *
     * @param result - The result received from the DB
     *
     * @return ArrayList<QuantityItemsStockReport>
     */
    private ArrayList<QuantityItemsStockReport> changeResultToQuantityItemsStockReport(SqlResult result) {

        ArrayList<QuantityItemsStockReport> resultList = new ArrayList<>();

        for (ArrayList<Object> a : result.getResultData()) {
            resultList.add(new QuantityItemsStockReport((String) a.get(0) + " liters"));
            resultList.add(new QuantityItemsStockReport((String) a.get(1) + " liters"));
            resultList.add(new QuantityItemsStockReport((String) a.get(2) + " liters"));
        }
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