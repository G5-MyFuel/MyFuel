package Contollers;

import boundary.NewPurchaseFuelForHomeHeatingBoundary;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.ShippingDay;
import entity.User;
import javafx.application.Platform;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewPurchaseFuelForHomeHeatingController extends BasicController {
    /* Variables*/
    private NewPurchaseFuelForHomeHeatingBoundary myBoundary;
    private static NewPurchaseFuelForHomeHeatingController Instance = null;
    private ArrayList<ShippingDay> availableTimesInDate = new ArrayList<>();


    /* Methods*/

    public NewPurchaseFuelForHomeHeatingController(NewPurchaseFuelForHomeHeatingBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /**
     * NewPurchaseFuelForHomeHeatingLogic Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of logic class
     */
//    public static NewPurchaseFuelForHomeHeatingController getInstance() {
//        if (Instance == null)
//            Instance = new NewPurchaseFuelForHomeHeatingController();
//        return Instance;
//    }
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

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_USERS_TABLE:
                    System.out.println("NewPurchaseFuelForHomeHeatingController -> myController.getUsersTable();");
                    ArrayList<User> resultListUsers = new ArrayList<>();
//                resultListUsers.addAll(this.changeResultToUsers(result));
//                myBoundary.setAllDBUsersArrayList(resultListUsers);
                    break;
                case GET_ALL_SHIPPING_DATES_AVAILABLE:
                    System.out.println("NewPurchaseFuelForHomeHeatingController -> myController.GetAvailableTimesToShippingDate();");
                    availableTimesInDate = changeResultToAvailableShippingDatesArrayList(result);
                    try {
                        myBoundary.setAvailableTimesForShipping();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case INSERT_NEW_AVAILABLE_DATE_FOR_SHIPPING:
                    System.out.println("NewPurchaseFuelForHomeHeatingController -> myController.INSERT_NEW_AVAILABLE_DATE_FOR_SHIPPING;");
                    break;
            }
        });
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
        ArrayList<Object>varArray = new ArrayList<>();
        varArray.add(dateAsString);
        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_AVAILABLE_DATE_FOR_SHIPPING,varArray);
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

}
