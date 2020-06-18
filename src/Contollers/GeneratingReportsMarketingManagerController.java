package Contollers;

import boundary.GeneratingReportsMarketingManagerBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.CommentsReport;
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
    private ArrayList<CommentsReport> changeResultToCommentsReport(SqlResult result) {

        ArrayList<CommentsReport> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {

            String customerID = (String) a.get(0);
            Double b = new Double(0);
            b = (Double) a.get(1);
            String customerTotalSum = b.toString();
            //System.out.println(customerID + ":  "+customerTotalSum);
            resultList.add(new CommentsReport(customerID, customerTotalSum));
            //resultList.add(new CommentsReport((String) a.get(0), (String) a.get(1)));
        }
        System.out.println(resultList);
        return resultList;
        /*
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
         */
    }

}