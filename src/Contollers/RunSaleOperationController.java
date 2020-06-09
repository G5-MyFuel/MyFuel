package Contollers;

import boundary.RunSaleOperationBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.SaleOperation;
import javafx.application.Platform;

import java.sql.Date;
import java.util.ArrayList;

/**
 * @author hani
 * @see RunSaleOperationController - the form's logic class
 */

public class RunSaleOperationController extends BasicController {
    /**
     * The boundary controlled by this controller
     */
    private RunSaleOperationBoundary myBoundary;

    private SaleOperation tempSaleOperation;

    private int SaleCounter;
    /**
     * Instantiates a new Sale-Operation Management controller.
     *
     * @param myBoundary the my boundary
     */
    public RunSaleOperationController(RunSaleOperationBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /*Logic Variables*/
    //private static RunSaleOperationController Instance = null;

   /*Logic Methods*/

    public void getSalesTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_SALES_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_SALES_TABLE:
                    ArrayList<SaleOperation> resultList = new ArrayList<>();
                    resultList.addAll(this.changeResultToSale(result));
                    myBoundary.setSalesTable(resultList);
                    break;
                case INSERT_NEW_SALE:
                    break;
                default:
                    break;
            }
        });
    }
    /**
     * This method create array list of templates from the data base result.
     *
     * @param result the result
     * @return Array list of costumers
     */
    private ArrayList<SaleOperation> changeResultToSale(SqlResult result){
        ArrayList<SaleOperation> resultList = new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            SaleOperation cos = new SaleOperation((String) a.get(0),(String)a.get(1),null,null);
            cos.setBeginDate(Date.valueOf((String) a.get(2)));
            cos.setEndDate(Date.valueOf((String) a.get(3)));
            SaleCounter= Integer.parseInt((String) a.get(0));
            resultList.add(cos);
        }
        return resultList;
    }

    public int getSaleCounter() {
        return SaleCounter;
    }

    public void setSaleOperationInDB(SaleOperation operation) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(operation.getSaleOperationID());
        varArray.add(operation.getTemplateName());
        varArray.add(operation.getBeginDate());
        varArray.add(operation.getEndDate());

       SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_SALE, varArray);
       super.sendSqlActionToClient(sqlAction);
    }


}
