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
    private final SettingDiscountRatesBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public SettingDiscountRatesController(SettingDiscountRatesBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /*Logic Methods*/

    public void getDiscountRatesTable(String SubscriptionType) {

        switch (SubscriptionType) {
            case "Regular monthly subscription - single vehicle":   //20
                SqlAction sqlAction = new SqlAction(SqlQueryType.GET_RegularSubscriptionSingleVehicle_PRICE);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Full monthly subscription (for single vehicle)":  //7
                sqlAction = new SqlAction(SqlQueryType.GET_FullSubscriptionSingleVehicle_PRICE);
                super.sendSqlActionToClient(sqlAction);
                break;
            case "Regular monthly subscription - number of vehicles":   //15
                sqlAction = new SqlAction(SqlQueryType.GET_RegularSubscriptionMultiVehicle_PRICE);
                super.sendSqlActionToClient(sqlAction);
                break;
            default:
                break;
        }
    }

    public void setNewPriceInDB(String newPrice, String subscriptionType) {

        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(newPrice);
        varArray.add(subscriptionType);
        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_PRICE, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_RegularSubscriptionSingleVehicle_PRICE:
                    myBoundary.setData(this.changeResultToString(result));
                    break;
                case GET_FullSubscriptionSingleVehicle_PRICE:
                    myBoundary.setData(this.changeResultToString(result));
                    break;
                case GET_RegularSubscriptionMultiVehicle_PRICE:
                    myBoundary.setData(this.changeResultToString(result));
                    break;
                case INSERT_NEW_PRICE:
                    //myBoundary.setData(this.changeResultToString(result));
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
        ArrayList<Object> a = result.getResultData().get(0);
        //a = result.getResultData().get(0);
        currentRate = (String) a.get(1);
        return currentRate;
    }
}