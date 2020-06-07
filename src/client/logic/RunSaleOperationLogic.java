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
    private boolean insertCostumerFlag;
    private SaleOperation tempSaleOperation;


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
     /*   String SaleID = operation.getSaleOperationID();
        String TemplateID = operation.getTemplateID();
        Date startDate = operation.getBeginDate();
        Date endDate = operation.getEndDate();


        String nullTemp= "1";
        String Quarry = " INSERT INTO `SaleOperation`(`SaleOperationID`, `TemplateID`, `BeginDate`, `EndDate`) VALUES ([SaleID],[TemplateID],[startDate],[endDate])";
 */
        tempSaleOperation = operation.getSaleOperation();
      /*  String ID = "test";
        String quarry = "INSERT INTO `bpsdc8o22sikrlpvvxqm`.`SaleOperation` (`ID`, `Password`, `Type`, `First Name`, `Last Name`, `Email Adress`, `Credit Card Number`, `Purchase Plan`, `Vehicle ID`) VALUES ('"+ID+"', '"+ID+"', '"+"null"+"', '"+ID+"', '"+ID+"', '"+ID+"', '"+"null"+"', '"+"null"+"', '"+"null"+"');";
       // String quarry = "INSERT INTO `SaleOperation`(`SaleOperationID`, `TemplateID`, `BeginDate`, `EndDate`) VALUES ([value-1],[value-2],[value-3],[value-4]);";
        ClientApp.client.handleMessageFromClientUI(new Message(OperationType.updateRequirement,quarry));

       */
    }
}
