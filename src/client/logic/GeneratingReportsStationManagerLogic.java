package client.logic;

import server.serverControllers.MySqlConnection;

public class GeneratingReportsStationManagerLogic {

    MySqlConnection mySqlConnector;

    /*Logic Variables*/
    private static GeneratingReportsStationManagerLogic Instance = null;
    //private SettingDiscountRatesController settingDiscountRatesController = SettingDiscountRatesController.getInstance();

    /*Logic Methods*/

    public static GeneratingReportsStationManagerLogic getInstance() {

        if (Instance == null)
            Instance = new GeneratingReportsStationManagerLogic();
        return Instance;
    }
}