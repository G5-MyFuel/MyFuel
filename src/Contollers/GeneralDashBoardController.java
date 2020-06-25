package Contollers;

import boundary.generalDashBoardBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import common.assets.enums.FuelTypes;
import entity.Prices;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 * A department responsible for bringing the appropriate navigation buttons
 * to each user as they log on
 *
 * @author itay ziv
 */
@SuppressWarnings("serial")
public class GeneralDashBoardController extends BasicController {
    private generalDashBoardBoundary myBoundary;
    private static Double fuelAmountOfPreMonthForCurrentUser = 0.0;
    private String currentUserID = null;


    /**
     * The boundary controlled by this controller
     */

    public GeneralDashBoardController() {

    }

    public GeneralDashBoardController(generalDashBoardBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /**
     * @param result - The result received from the DB
     */
    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
                switch (result.getActionType()) {
                    case GET_ALL_UPDATED_PRICES:
                        this.changeResultToFuelPrices(result);
                        break;
                    case GET_PRE_MONTH_PURCHASE_FUEL_AMOUNT_OF_USER:
                        this.changeResultToFueAmountOfPreMonthOfCurUser(result, currentUserID);
                        Prices.fuelAmountOfPreviousMonth = Double.valueOf(fuelAmountOfPreMonthForCurrentUser);
                        break;
                    default:
                        break;
                }

        });
    }

    /**
     * get Customer Purchase Amount In Last Month From DB
     *
     * @param customerId
     */
    public void getCustomerPurchaseAmountInLastMonthFromDB(String customerId) {
        ArrayList<Object> vars = new ArrayList<>();
        if (currentUserID != null) vars.add(currentUserID);
        if (customerId != null) vars.add(customerId);
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_PRE_MONTH_PURCHASE_FUEL_AMOUNT_OF_USER, vars);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * change Result To Fuel Amount Of Pre Month Of Cur User
     *
     * @param result
     * @param userID
     */
    private void changeResultToFueAmountOfPreMonthOfCurUser(SqlResult result, String userID) {
        for (ArrayList<Object> a : result.getResultData()) {
            this.fuelAmountOfPreMonthForCurrentUser = (Double) a.get(1);
            System.out.println(fuelAmountOfPreMonthForCurrentUser.getClass().getName() + "-->" + fuelAmountOfPreMonthForCurrentUser.toString());
        }
    }





    /**
     * get All Updated Prices From DB
     * execute GET_ALL_UPDATED_PRICES quarry
     */
    public void getAllUpdatedPricesFromDB() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_UPDATED_PRICES);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * change result to fuel prices.
     *
     * @param result
     */
    private void changeResultToFuelPrices(SqlResult result) {
        FuelTypes ft;
        String s;
        for (ArrayList<Object> a : result.getResultData()) {
            s = (String) a.get(0);
            ft = FuelTypes.valueOf(s);
            switch (ft) {
                case Diesel:
                    Prices.setBasePrice_diesel((Double) a.get(1));
                    break;
                case Gasoline95:
                    Prices.setBasePrice_95((Double) a.get(1));
                    break;
                case HomeHeatingFuel:
                    Prices.setBasePrice_homeHeating((Double) a.get(1));
                    break;
                case ScooterFuel:
                    Prices.setBasePrice_scooter((Double) a.get(1));
                    break;
            }
            System.out.println(ft.toString() + "'s Price refreshed to: " + a.get(1));
        }
    }

    /**
     * getFuelAmountOfPreMonthForCurrentUser
     *
     * @return Double
     */
    public Double getFuelAmountOfPreMonthForCurrentUser() {
        return fuelAmountOfPreMonthForCurrentUser;
    }

    /**
     * set Fuel Amount Of Pre Month For CurrentUser method
     *
     * @param fuelAmountOfPreMonthForCurrentUser
     */
    public void setFuelAmountOfPreMonthForCurrentUser(Double fuelAmountOfPreMonthForCurrentUser) {
        this.fuelAmountOfPreMonthForCurrentUser = fuelAmountOfPreMonthForCurrentUser;
    }

    /**
     * get Current User ID method
     *
     * @return currentUserID
     */
    public String getCurrentUserID() {
        return currentUserID;
    }

    /**
     * set Current User ID method
     *
     * @param currentUserID
     */
    public void setCurrentUserID(String currentUserID) {
        this.currentUserID = currentUserID;
    }
}
