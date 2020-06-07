package client.logic;

import server.serverControllers.MySqlConnection;

public class GeneratingReportsMarketingManagerController {

    MySqlConnection mySqlConnector;

    /*Logic Variables*/
    private static GeneratingReportsMarketingManagerController Instance = null;
    //private SettingDiscountRatesController settingDiscountRatesController = SettingDiscountRatesController.getInstance();

    /*Logic Methods*/

    public static GeneratingReportsMarketingManagerController getInstance() {

        if (Instance == null)
            Instance = new GeneratingReportsMarketingManagerController();
        return Instance;
    }
}