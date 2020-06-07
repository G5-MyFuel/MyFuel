package Contollers;

import server.MysqlConnection;

public class GeneratingReportsStationManagerController {

    MysqlConnection mySqlConnector;

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