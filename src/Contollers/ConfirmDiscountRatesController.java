package Contollers;

import boundary.ConfirmDiscountRatesBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.DiscountRate;
import javafx.application.Platform;

import java.util.ArrayList;

public class ConfirmDiscountRatesController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private final ConfirmDiscountRatesBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public ConfirmDiscountRatesController(ConfirmDiscountRatesBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /*Logic Methods*/

    public void GetDiscountRatesData(ArrayList<String> paramArray) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.addAll(paramArray);
        varArray.remove(0);
        System.out.println(paramArray);
        System.out.println(varArray);
        switch (paramArray.get(0)) {
            case "Get Discount Rates Data":
                SqlAction sqlAction = new SqlAction(SqlQueryType.Get_DiscountRates_Table);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Update New Discount Rate":
                sqlAction = new SqlAction(SqlQueryType.UPDATE_NEW_DiscountRate, varArray);
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
                case Get_DiscountRates_Table:
                    myBoundary.setDiscountRatesData(this.changeResultToDiscountRatesData(result));
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
    private ArrayList<DiscountRate> changeResultToDiscountRatesData(SqlResult result) {


        //Float TotalPrice = new Float(0);
        ArrayList<DiscountRate> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            resultList.add(new DiscountRate((String) a.get(0), (String) a.get(1), (String) a.get(3)));
            //TotalPrice += Float.parseFloat((String) a.get(0));
        }
        //return TotalPrice.toString();
        System.out.println(resultList);
        return resultList;
    }

}