package Contollers;

import boundary.GeneratingReportsMarketingManagerBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import javafx.application.Platform;

import java.util.ArrayList;

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
            default:
                break;
        }
    }

    /*Logic Methods*/

        @Override
        public void getResultFromClient(SqlResult result){
            Platform.runLater(() -> {
                switch (result.getActionType()) {
                    case GET_Manager_Data:
                        //myBoundary.setCommentsReportData(this.changeResultToCommentsReport(result));
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
    private String changeResultToCommentsReport(SqlResult result) {

        Float TotalPrice = new Float(0);
        for (ArrayList<Object> a : result.getResultData()) {
            TotalPrice += Float.parseFloat((String) a.get(0));
        }
        return TotalPrice.toString();
    }

}