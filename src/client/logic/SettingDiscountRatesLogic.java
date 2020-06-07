package client.logic;

import client.ClientApp;
import common.tools.Message;
import common.tools.OperationType;
import common.tools.ReturnMsgType;
import server.serverControllers.MySqlConnection;

public class SettingDiscountRatesLogic {

    MySqlConnection mySqlConnector;

    /*Logic Variables*/
    private static SettingDiscountRatesLogic Instance = null;
    //private SettingDiscountRatesController settingDiscountRatesController = SettingDiscountRatesController.getInstance();

    /*Logic Methods*/

    public void getDiscountRatesTable(String query) {
        //(type of message to server, the message) = (requirement,query to get all employees table)
        ClientApp.chatClient.handleMessageFromClientUI(new Message(OperationType.getRequirementData, (Object) query));
        System.out.println("Hey");
    }

    public static SettingDiscountRatesLogic getInstance() {
        if (Instance == null)
            Instance = new SettingDiscountRatesLogic();
        return Instance;
    }
    public void setData(Object object) {
        ReturnMsgType currentPrice = (ReturnMsgType) object;
        System.out.println(ReturnMsgType.values());
        //System.out.println(currentPrice);

    }

}