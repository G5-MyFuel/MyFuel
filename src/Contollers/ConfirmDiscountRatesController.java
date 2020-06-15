package Contollers;

import boundary.ConfirmDiscountRatesBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
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
        //ArrayList<Object> varArray = new ArrayList<>();
        //varArray.addAll(paramArray);
        //varArray.remove(0);
        switch (paramArray.get(0)) {
            case "Get Discount Rates data":
                SqlAction sqlAction = new SqlAction(SqlQueryType.GET_Manager_Data/*, varArray*/);
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
    private String changeResultToDiscountRatesData(SqlResult result) {

        /*Float TotalPrice = new Float(0);
        for (ArrayList<Object> a : result.getResultData()) {
            TotalPrice += Float.parseFloat((String) a.get(0));
        }
        return TotalPrice.toString();*/
        return "";
    }

}