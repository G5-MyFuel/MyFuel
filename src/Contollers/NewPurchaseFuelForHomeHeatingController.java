package Contollers;

import boundary.NewPurchaseFuelForHomeHeatingBoundary;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.Costumer;
import entity.CreditCard;
import entity.ShippingDay;
import entity.User;
import javafx.application.Platform;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Daniel
 * @see NewPurchaseFuelForHomeHeatingController - the from's Controller class
 */

public class NewPurchaseFuelForHomeHeatingController extends BasicController {
    /* Variables*/
    private static NewPurchaseFuelForHomeHeatingController Instance = null;
    private NewPurchaseFuelForHomeHeatingBoundary myBoundary;
    private ArrayList<ShippingDay> availableTimesInDate = new ArrayList<>();

    /* Methods*/
    public NewPurchaseFuelForHomeHeatingController(NewPurchaseFuelForHomeHeatingBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /**
     * validate page-check if text filed is empty and return answer
     * @param guiObj
     * @return
     */
    public boolean validatePage(Object guiObj) {
        if (guiObj instanceof JFXTextField) {
            JFXTextField jfxTextField = (JFXTextField) guiObj;
            if (jfxTextField.getText().isEmpty())
                return false;
            return true;
        }
        if (guiObj instanceof JFXTextArea) {
            JFXTextArea jfxTextArea = (JFXTextArea) guiObj;
            if (jfxTextArea.getText().isEmpty())
                return false;
            return true;
        }
        return false;
    }

    /**
     * Every action in this page
     * @param result - The result recieved from the DB
     */
    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            try {
                switch (result.getActionType()) {
                    case GET_ALL_USERS_TABLE:
                        System.out.println("NewPurchaseFuelForHomeHeatingController -> myController.getUsersTable();");
                        ArrayList<User> resultListUsers = new ArrayList<>();
                        break;
                    case GET_ALL_SHIPPING_DATES_AVAILABLE:
                        System.out.println("NewPurchaseFuelForHomeHeatingController -> myController.GetAvailableTimesToShippingDate();");
                        availableTimesInDate = changeResultToAvailableShippingDatesArrayList(result);
                        System.out.println(availableTimesInDate);
                        myBoundary.setAvailableTimesForShipping(availableTimesInDate);
                        break;
                    case INSERT_NEW_AVAILABLE_DATE_FOR_SHIPPING:
                        System.out.println("NewPurchaseFuelForHomeHeatingController -> myController.INSERT_NEW_AVAILABLE_DATE_FOR_SHIPPING;");
                        break;
                    case GET_SPECIFIC_CUSTOMER_DETAILS:
                        myBoundary.setCurrentCostumerDetailsFromDB(this.fromResultSetToCustomers(result));
                        break;
                    case INSERT_NEW_PURCHASE_FUEL_FOR_HOME_HEATING:
                        System.out.println("N");
                        break;
                    case INSERT_NEW_PURCHASE_FUEL_FOR_HOME_HEATING1:
                        System.out.println("N1");
                        break;
                }
            } catch (NullPointerException npe) {
            }
        });
    }

    /**
     * Active when there is a new purchase by costumer and saves it in DB
     * @param varArray
     */
    public void INSERT_NEW_PURCHASE_FUEL_FOR_HOME_HEATING(ArrayList<Object> varArray) {
        System.out.println("*****HH*****");
        varArray.toString();
        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_PURCHASE_FUEL_FOR_HOME_HEATING, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * Active when there is a new purchase by costumer and saves it in DB
     * @param varArray1
     */
    public void INSERT_NEW_PURCHASE_FUEL_FOR_HOME_HEATING1(ArrayList<Object> varArray1) {
        SqlAction sqlAction2 = new SqlAction(SqlQueryType.INSERT_NEW_PURCHASE_FUEL_FOR_HOME_HEATING1, varArray1);
        super.sendSqlActionToClient(sqlAction2);
    }

    /**
     * Get a specific costumer from DB
     * @param str
     */
    public void GET_SPECIFIC_CUSTOMER_DETAILS(String str) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(str);
        varArray.add(str);
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_SPECIFIC_CUSTOMER_DETAILS, varArray);
        super.sendSqlActionToClient(sqlAction);
    }



    /**
     * This method create array list of costumers from the data base result.
     *
     * @param result the result
     * @return Array list of costumers
     */
    private Costumer fromResultSetToCustomers(SqlResult result) {
        /*result coloms: 0-ID ,1-creditCardNumber ,2-cardExpirationDate ,3-CVV ,4-cosType ,5-pricingModel ,6-purchasePlan ,7-userID ,8-userType ,
         9-userPassword, 10-isLogin, 11-firstName, 12-lastName, 13-Email, 14-FuelCompany1, 15-FuelCompany2, 16-FuelCompany3
         */
        ArrayList<Object> a = new ArrayList<>(result.getResultData().get(0));
        ArrayList<String> stations = new ArrayList<>();
        Costumer spesificCostumer;
        spesificCostumer = new Costumer((String) a.get(0), (String) a.get(9), (String) a.get(4),
                (String) a.get(11), (String) a.get(12), (String) a.get(13), null, (String) a.get(6), (String) a.get(5));
        //add fuel companies.
        stations.add((String) a.get(14));
        stations.add((String) a.get(15));
        stations.add((String) a.get(16));
        spesificCostumer.setFuelCompany(stations);
        CreditCard card = new CreditCard(spesificCostumer, (String) a.get(1), (String) a.get(2), (String) a.get(3));
        spesificCostumer.setCostumerCreditCard(card);
        return spesificCostumer;
    }


    /**
     * get all available shipping dates Table from DB
     */
    public void GetShippingOptionalDatesTableFromDB() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_SHIPPING_DATES_AVAILABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    //

    /**
     * set new row in ShippingOptionalDates Table
     */
    public void InsertNewAvailableDateToDB(String dateAsString) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateAsString);
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(dateAsString);
        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_AVAILABLE_DATE_FOR_SHIPPING, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * convert the result from db to arrayList of all available dates for shipping
     *
     * @param result
     * @return ArrayList<ShippingDay>
     */
    private ArrayList<ShippingDay> changeResultToAvailableShippingDatesArrayList(SqlResult result) {
        ArrayList<ShippingDay> shippingDayArrayList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            String dateStr = String.valueOf((Date) a.get(0));
            ShippingDay sd = new ShippingDay(dateStr,
                    (Integer) a.get(1),
                    (Integer) a.get(2),
                    (Integer) a.get(3),
                    (Integer) a.get(4),
                    (Integer) a.get(5),
                    (Integer) a.get(6));
            shippingDayArrayList.add(sd);
        }
        return shippingDayArrayList;
    }


    //All results from DB as ArrayList - getters and setters:

    public ArrayList<ShippingDay> getAvailableTimesInDate() {
        return availableTimesInDate;
    }

    public void setAvailableTimesInDate(ArrayList<ShippingDay> availableTimesInDate) {
        this.availableTimesInDate = availableTimesInDate;
    }

    /**

    public void INSERT_NEW_PURCHES_toDB(ArrayList<String> myPurchase) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(myPurchase.get(0));
        varArray.add(myPurchase.get(1));
        varArray.add(myPurchase.get(2));
        varArray.add(myPurchase.get(3));
        varArray.add(myPurchase.get(4));
        varArray.add(myPurchase.get(6));

        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_PURCHES, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    public void INSERT_NEW_PURCHES_TO_HOME_HEATING_toDB(ArrayList<String> myPurchase) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add();
        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_PURCHES_TO_HOME_HEATING, varArray);
        super.sendSqlActionToClient(sqlAction);
    }
     */
}
