package client.logic;

import server.serverControllers.MySqlConnection;

public class GeneratingReportsStationManagerController {

    MySqlConnection mySqlConnector;

    /*Logic Variables*/
    private static GeneratingReportsStationManagerController Instance = null;
    //private SettingDiscountRatesController settingDiscountRatesController = SettingDiscountRatesController.getInstance();

    /*Logic Methods*/

    public static GeneratingReportsStationManagerController getInstance() {

        if (Instance == null)
            Instance = new GeneratingReportsStationManagerController();
        return Instance;
    }
}