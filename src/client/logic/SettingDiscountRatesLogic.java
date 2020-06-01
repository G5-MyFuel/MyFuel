package client.logic;

import client.ClientApp;
import client.boundary.SettingDiscountRatesController;
import common.tools.Message;
import common.tools.OperationType;
import server.serverControllers.MySqlConnection;

public class SettingDiscountRatesLogic {

    MySqlConnection mySqlConnector;

    /*Logic Variables*/
    private static SettingDiscountRatesLogic Instance = null;
    //private SettingDiscountRatesController settingDiscountRatesController = SettingDiscountRatesController.getInstance();

    /*Logic Methods*/

    public void getDiscountRatesTable(String query) {
        //(type of message to server, the message) = (requirement,query to get all employees table)
        ClientApp.client.handleMessageFromClientUI(new Message(OperationType.getRequirementData, (Object) query));
    }

    public static SettingDiscountRatesLogic getInstance() {

        if (Instance == null)
            Instance = new SettingDiscountRatesLogic();
        return Instance;
    }


}