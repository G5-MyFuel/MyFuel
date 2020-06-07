package client.logic;

import common.entity.SaleOperation;
import server.serverControllers.MySqlConnection;

/**
 * @author hani
 * @see RunSaleOperationController - the form's logic class
 */

public class RunSaleOperationController {

    MySqlConnection mySqlConnector;
    private boolean insertCostumerFlag;
    private SaleOperation tempSaleOperation;


    /*Logic Variables*/
    private static RunSaleOperationController Instance = null;

    /*Logic Methods*/


    /**
     * RunSaleOperationLogic Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of logic class
     */
    public static RunSaleOperationController getInstance() {
        if (Instance == null)
            Instance = new RunSaleOperationController();
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
