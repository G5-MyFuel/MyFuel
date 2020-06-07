package client.logic;

import server.serverControllers.MySqlConnection;

/**
 * @author hani
 */
public class SaleOperationTemplateController {

    private static SaleOperationTemplateController Instance = null;
    MySqlConnection mySqlConnector;
  //  ArrayList<SaleOperationTemplate> templateArrayList;

    /**
     * SaleOperationTemplateLogic Instance getter using SingleTone DesignPatterns
     * @return Instance of logic class
     */
    public static SaleOperationTemplateController getInstance() {
        if (Instance == null)
            Instance = new SaleOperationTemplateController();
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
