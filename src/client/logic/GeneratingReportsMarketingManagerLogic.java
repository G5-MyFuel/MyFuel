package client.logic;

import server.serverControllers.MySqlConnection;

public class GeneratingReportsMarketingManagerLogic {

    MySqlConnection mySqlConnector;

    /*Logic Variables*/
    private static GeneratingReportsMarketingManagerLogic Instance = null;
    //private SettingDiscountRatesController settingDiscountRatesController = SettingDiscountRatesController.getInstance();

    /*Logic Methods*/

    public static GeneratingReportsMarketingManagerLogic getInstance() {

        if (Instance == null)
            Instance = new GeneratingReportsMarketingManagerLogic();
        return Instance;
    }
}