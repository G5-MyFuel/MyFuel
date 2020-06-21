package Contollers;

import boundary.GeneratingReportsMarketingManagerBoundary;
import boundary.SettingDiscountRatesBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.CommentsReport;
import entity.CustomerPeriodicCharacterizationReport;
import javafx.application.Platform;

import java.util.ArrayList;

import static java.sql.Types.DOUBLE;

/**
 *  A department responsible for logical calculations and communicating with the client server and DB
 *  For page "GeneratingReportsMarketingManagerBoundary"
 *
 *  * @author Nir Asulin
 * @see GeneratingReportsMarketingManagerBoundary - the form's gui controller (boundary) class
 */

public class GeneratingReportsMarketingManagerController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private final GeneratingReportsMarketingManagerBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public GeneratingReportsMarketingManagerController(GeneratingReportsMarketingManagerBoundary myBoundary) {
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
            case "Comments Report for Marketing Campaign":
                SqlAction sqlAction = new SqlAction(SqlQueryType.GET_Comments_Report, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Customer Periodic Characterization Report":
                sqlAction = new SqlAction(SqlQueryType.GET_Customers_List, varArray);
                super.sendSqlActionToClient(sqlAction);
                sqlAction = new SqlAction(SqlQueryType.GET_CustomerPeriodicCharacterization_Report, varArray);
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
                case GET_Comments_Report:
                    myBoundary.setCommentsReportData(this.changeResultToCommentsReport(result));
                    break;
                case GET_Customers_List:
                    myBoundary.setCustomersListData(this.changeResultToCustomersList(result));
                    break;
                case GET_CustomerPeriodicCharacterization_Report:
                    myBoundary.setCustomerPeriodicCharacterizationReportData(this.changeResultToCustomerPeriodicCharacterizationReport(result));
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * This method create array list of Comments Report from the data base result.
     *
     * @param result - The result received from the DB
     *
     * @return ArrayList<CommentsReport>
     */
    private ArrayList<CommentsReport> changeResultToCommentsReport(SqlResult result) {

        ArrayList<CommentsReport> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData())
            resultList.add(new CommentsReport((String) a.get(0), a.get(1).toString()));
        return resultList;
    }

    private ArrayList<CustomerPeriodicCharacterizationReport> changeResultToCustomersList(SqlResult result) {

        ArrayList<CustomerPeriodicCharacterizationReport> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData())
            resultList.add(new CustomerPeriodicCharacterizationReport((String) a.get(0), a.get(1).toString()));
        return resultList;
    }

    /**
     * This method create array list of Customer Periodic Characterization Report from the data base result.
     *
     * @param result - The result received from the DB
     *
     * @return ArrayList<CustomerPeriodicCharacterizationReport>
     */
    private ArrayList<CustomerPeriodicCharacterizationReport> changeResultToCustomerPeriodicCharacterizationReport(SqlResult result) {

        ArrayList<CustomerPeriodicCharacterizationReport> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()){
            String customerID = (String) a.get(0);
            String companyName = (String) a.get(2);
            switch ((String) a.get(2)) {
                case "YELLOW":
                    resultList.add(new CustomerPeriodicCharacterizationReport((String) a.get(0), a.get(1).toString(), "-","-"));
                    break;
                case "SONOL":
                    resultList.add(new CustomerPeriodicCharacterizationReport((String) a.get(0), "-", a.get(1).toString(),"-"));
                    break;
                case "PAZ":
                    resultList.add(new CustomerPeriodicCharacterizationReport((String) a.get(0), "-", "-",a.get(1).toString()));
                    break;
                default:
                    break;
            }
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