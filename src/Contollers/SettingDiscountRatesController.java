package Contollers;

import boundary.SettingDiscountRatesBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import javafx.application.Platform;

import java.util.ArrayList;

public class SettingDiscountRatesController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private SettingDiscountRatesBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public SettingDiscountRatesController(SettingDiscountRatesBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /*Logic Methods*/

    public void getDiscountRatesTable() {

        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_DiscountRates_TABLE);
        super.sendSqlActionToClient(sqlAction);
        System.out.println("Hey");
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_DiscountRates_TABLE:
                    myBoundary.setData(this.changeResultToString(result));
                    break;

                default:
                    break;
            }
        });
    }

    /**
     * This method create array list of costumers from the data base result.
     *
     * @param result the result
     * @return Array list of costumers
     */
    private String changeResultToString(SqlResult result) {
        String currentRate = null;
        ArrayList<Object> a;
        a = result.getResultData().get(0);
        currentRate = (String) a.get(1);
        return currentRate;
    }
}