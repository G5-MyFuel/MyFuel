package Contollers;

import boundary.GeneratingReportsMarketingManagerBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.CommentsReport;
import entity.CustomerPeriodicCharacterizationReport;
import javafx.application.Platform;

import java.util.ArrayList;

import static java.sql.Types.DOUBLE;

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

    public void GetReportData(ArrayList<String> paramArray) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.addAll(paramArray);
        varArray.remove(0);
        System.out.println(paramArray);
        System.out.println(varArray);
        switch (paramArray.get(0)) {
            case "Comments Report for Marketing Campaign":
                SqlAction sqlAction = new SqlAction(SqlQueryType.GET_Comments_Report, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            /*case "Get Customers List":
                sqlAction = new SqlAction(SqlQueryType.GET_Customers_List, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;*/
            case "Customer Periodic Characterization Report":
                sqlAction = new SqlAction(SqlQueryType.GET_Customers_List, varArray);
                super.sendSqlActionToClient(sqlAction);
                sqlAction = new SqlAction(SqlQueryType.GET_CustomerPeriodicCharacterization_Report, varArray);
                super.sendSqlActionToClient(sqlAction);
                break;
            default:
                break;
        }
    }

    /*Logic Methods*/

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
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
     * This method create Comments Report from the data base result.
     *
     * @param result the result
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

    private ArrayList<CustomerPeriodicCharacterizationReport> changeResultToCustomerPeriodicCharacterizationReport(SqlResult result) {

        ArrayList<CustomerPeriodicCharacterizationReport> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()){

            resultList.add(new CustomerPeriodicCharacterizationReport((String) a.get(0), a.get(1).toString()));
        }

        return resultList;
    }

}