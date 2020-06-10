package Contollers;

import boundary.RunSaleOperationBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.SaleOperation;
import entity.SaleOperationTemplate;
import javafx.application.Platform;

import java.sql.Date;
import java.sql.Time;
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
    private boolean flagSale = true;
    private int SaleCounter;
    SaleOperation newSale;
    /**
     * Instantiates a new Sale-Operation Management controller.
     *
     * @param myBoundary the my boundary
     */
    public RunSaleOperationController(RunSaleOperationBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

   /*Logic Methods*/
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
                case GET_ALL_TEMPLATES_TABLE:
                    ArrayList<String> templateList = new ArrayList<>();
                    templateList.addAll(this.changeResultToTemplateList(result));
                    myBoundary.setTemplateList(templateList);
                    break;
                case GET_CHOSEN_TEMPLATE_DETAILS:
                    ArrayList<SaleOperationTemplate> templateChosenList = new ArrayList<>();
                    templateChosenList.addAll(this.changeResultToChosenTemplateDetails(result));
                    myBoundary.setChosenTemplateDetails(templateChosenList);
                    break;
                case GET_ALL_SALES_TO_CHACK_SALE:
                    flagSale = true;
                    System.out.println(this.changeResultToSaleAndCheck(result) + "in the case");
                    myBoundary.setFlagSale(this.changeResultToSaleAndCheck(result));
                    break;

                default:
                    break;
            }
        });
    }

    public void getSalesTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_SALES_TABLE);
        super.sendSqlActionToClient(sqlAction);
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

    //get template list:
    public void getTemplateList() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_TEMPLATES_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    private ArrayList<String> changeResultToTemplateList(SqlResult result){
        ArrayList<String> resultList = new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            String tempTemplate = new String((String) a.get(1));
            resultList.add(tempTemplate);
        }
        return resultList;
    }

    //get choosen template details:
    public void getChoosenTemplateDetails() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_CHOSEN_TEMPLATE_DETAILS);
        super.sendSqlActionToClient(sqlAction);
    }

    private ArrayList<SaleOperationTemplate> changeResultToChosenTemplateDetails(SqlResult result){
        String myCchosenTemplate = myBoundary.getChoosenTemplate();
        ArrayList<SaleOperationTemplate> resultList = new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            if (myCchosenTemplate.equals((String)a.get(1))) {
                SaleOperationTemplate cos = new SaleOperationTemplate((String) a.get(0),
                        (String)a.get(1),
                        (String) a.get(2),
                        (String) a.get(3),
                        (String) a.get(4),
                        null,null);
                cos.setBeginHour(Time.valueOf((String) a.get(5)));
                cos.setEndHour(Time.valueOf((String) a.get(6)));
                resultList.add(cos);
            }//end if
        }//end for
        return resultList;
    }

    //CHACK IF SALE CAN RUN: according to its date and day
    public void chackIfSaleCanRun(SaleOperation newSale) {
        this.newSale = newSale;
        //get all sales from db:
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_SALES_TO_CHACK_SALE);
        super.sendSqlActionToClient(sqlAction);
    }

    private boolean changeResultToSaleAndCheck(SqlResult result){
        System.out.println(newSale.getBeginDate());
        System.out.println(newSale.getEndDate());

        for(ArrayList<Object> a: result.getResultData()) {
            //if the dates are overlap - flag=false.
           System.out.println(Date.valueOf((String) a.get(2)));
            System.out.println(Date.valueOf((String) a.get(3)));
            if ( (( newSale.getBeginDate().after(Date.valueOf((String) a.get(2)))) ==true &&
                    ( newSale.getBeginDate().before(Date.valueOf((String) a.get(3)))) ==true ) ||
                    (( newSale.getEndDate().after(Date.valueOf((String) a.get(2)))) ==true &&
                            ( newSale.getEndDate().before(Date.valueOf((String) a.get(3)))) ==true ))
            {
               flagSale = false;
                System.out.println(flagSale);
            }
            System.out.println(flagSale);
        }
        return flagSale;
    }

    public boolean getFlagSale() { return flagSale;   }


}
