package client.logic;

import server.serverControllers.MySqlConnection;

public class ConfirmDiscountRatesLogic {

    MySqlConnection mySqlConnector;

    /*Logic Variables*/
    private static ConfirmDiscountRatesLogic Instance = null;
    //private SettingDiscountRatesController settingDiscountRatesController = SettingDiscountRatesController.getInstance();

    /*Logic Methods*/

    public static ConfirmDiscountRatesLogic getInstance() {

        if (Instance == null)
            Instance = new ConfirmDiscountRatesLogic();
        return Instance;
    }
}