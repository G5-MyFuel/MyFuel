package Contollers;

import boundary.CostumerManagmentTablePageBoundary;
import boundary.SaleOperationTemplateBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import server.MysqlConnection;

/**
 * @author Hana Wiener
 */

public class SaleOperationTemplateController {
    /**
     * The boundary controlled by this controller
     */
    private SaleOperationTemplateBoundary myBoundary;

    /**
     * Instantiates a new Template Management controller.
     *
     * @param myBoundary the my boundary
     */
    public SaleOperationTemplateController(SaleOperationTemplateBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }


    public void getCostumerTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_COSTUMER_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }


    public void getTemplatesTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_TEMPLATES_TABLE);

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
