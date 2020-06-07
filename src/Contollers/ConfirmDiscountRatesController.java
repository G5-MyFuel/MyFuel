package Contollers;

import server.MysqlConnection;

public class ConfirmDiscountRatesController {

    MysqlConnection mySqlConnector;

    /*Logic Variables*/
    private static ConfirmDiscountRatesController Instance = null;
    //private SettingDiscountRatesController settingDiscountRatesController = SettingDiscountRatesController.getInstance();

    /*Logic Methods*/

    public static ConfirmDiscountRatesController getInstance() {

        if (Instance == null)
            Instance = new ConfirmDiscountRatesController();
        return Instance;
    }
}