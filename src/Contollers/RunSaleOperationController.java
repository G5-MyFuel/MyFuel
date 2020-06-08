package Contollers;

import boundary.RunSaleOperationBoundary;
import entity.SaleOperation;
import server.MysqlConnection;

/**
 * @author hani
 * @see RunSaleOperationController - the form's logic class
 */

public class RunSaleOperationController {

    MysqlConnection mySqlConnector;
    private boolean insertCostumerFlag;
    private SaleOperation tempSaleOperation;


    /*Logic Variables*/
    private static RunSaleOperationController Instance = null;

    public RunSaleOperationController(RunSaleOperationBoundary runSaleOperationBoundary) {
    }

    /*Logic Methods*/




    public void setSaleOperationInDB(SaleOperation operation) {

        tempSaleOperation = operation.getSaleOperation();

    }

    public void insertNewSaleOperation() {
    }
}
