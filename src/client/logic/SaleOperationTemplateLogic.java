package client.logic;

import client.ClientApp;
import common.entity.SaleOperationTemplate;
import common.tools.Message;
import common.tools.OperationType;
import server.serverControllers.MySqlConnection;

import java.util.ArrayList;

/**
 * @author hani
 */
public class SaleOperationTemplateLogic {

    private static SaleOperationTemplateLogic Instance = null;
    MySqlConnection mySqlConnector;
  //  ArrayList<SaleOperationTemplate> templateArrayList;

    /**
     * SaleOperationTemplateLogic Instance getter using SingleTone DesignPatterns
     * @return Instance of logic class
     */
    public static SaleOperationTemplateLogic getInstance() {
        if (Instance == null)
            Instance = new SaleOperationTemplateLogic();
        return Instance;
    }
/*
    public void getTemplateTable() {
        ClientApp.client.handleMessageFromClientUI(new Message(OperationType.getRequirementData, "SELECT * FROM CampaignTemplates as T;"));
    }

    public void getDatafromServer() {
        ClientApp.client.handleMessageFromClientUI(new Message(OperationType.getRequirementData, "SELECT * FROM CampaignTemplates"));
    }


    public ArrayList<SaleOperationTemplate> getTemplatesArrayList() {
        return templateArrayList;
    }

    public void setTemplateArrayList(ArrayList<SaleOperationTemplate> templateArrayList) {
        this.templateArrayList = templateArrayList;
    }
*/
}
