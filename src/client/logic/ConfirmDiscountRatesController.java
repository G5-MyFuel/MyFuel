package client.logic;

import server.serverControllers.MySqlConnection;

public class ConfirmDiscountRatesController {

    MySqlConnection mySqlConnector;

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