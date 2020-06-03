package client.logic;

import client.ClientApp;
import com.sun.org.apache.xpath.internal.operations.Operation;
import common.entity.Costumer;
import common.entity.SaleOperation;
import common.tools.Message;
import common.tools.OperationType;
import server.serverControllers.MySqlConnection;

import java.sql.Date;// sql class of date !!!!

/**
 * @author hani
 * @see RunSaleOperationLogic - the form's logic class
 */
public class RunSaleOperationLogic {
    MySqlConnection mySqlConnector;
    /*Logic Variables*/
    private static RunSaleOperationLogic Instance = null;

    /*Logic Methods*/


    /**
     * RunSaleOperationLogic Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of logic class
     */
    public static RunSaleOperationLogic getInstance() {
        if (Instance == null)
            Instance = new RunSaleOperationLogic();
        return Instance;
    }


    public void setSaleOperationInDB(SaleOperation operation) {
        String SaleID = operation.getSaleOperationID();
        String TemplateID = operation.getTemplateID();
        Date startDate = operation.getBeginDate();
        Date endDate = operation.getEndDate();

        String nullTemp= "1";
        String Quarry = " INSERT INTO `SaleOperation`(`SaleOperationID`, `TemplateID`, `BeginDate`, `EndDate`) VALUES ([SaleID],[TemplateID],[startDate],[endDate])";
        ClientApp.client.handleMessageFromClientUI(new Message(OperationType.updateRequirement,Quarry));
    }
}
