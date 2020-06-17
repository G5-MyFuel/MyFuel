package Contollers;

import boundary.generalDashBoardBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import common.assets.enums.FuelTypes;
import entity.Prices;
import entity.User;
import javafx.application.Platform;

import java.util.ArrayList;

public class GeneralDashBoardController extends BasicController {
    private generalDashBoardBoundary myBoundary;

    /**
     * The boundary controlled by this controller
     */


    public GeneralDashBoardController(generalDashBoardBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_PURCHASE_FUEL_AMOUNT_OF_USER:
                    ArrayList<User> resultListFuelPrices = new ArrayList<>();
                    this.changeResultToFuelPrices(result);
                    Prices p = new Prices();
                    System.out.println(p.toString());
                    //System.out.println(myBoundary.getAllDBUsersArrayList()); //test
                    break;
                //
                default:
                    System.err.println(this.getClass().getName().toString() + ": result error from db");
                    break;
            }
        });
    }

    //קבלת כל הלקוחות
    private void getAllCustomersPurchaseAmountInLastMonthFromDB() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_PURCHASE_FUEL_AMOUNT_OF_USER);
        super.sendSqlActionToClient(sqlAction);
    }


    //קבלת מחירים עדכניים
    private void getAllUpdatedPricesFromDB() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_UPDATED_PRICES);
        super.sendSqlActionToClient(sqlAction);
    }

    private void changeResultToFuelPrices(SqlResult result) {
        FuelTypes ft;

        for (ArrayList<Object> a : result.getResultData()) {
            String s = (String) a.get(1);
            switch (s) {
                case "Diesel":
                    Prices.setBasePrice_diesel((Double) a.get(2));
                    break;
                case "Gasoline95":
                    Prices.setBasePrice_95((Double) a.get(2));
                    break;
                case "HomeHeatingFuel":
                    Prices.setBasePrice_homeHeating((Double) a.get(2));
                    break;
                case "ScooterFuel":
                    Prices.setBasePrice_scooter((Double) a.get(2));
                    break;
            }
        }
    }

}
