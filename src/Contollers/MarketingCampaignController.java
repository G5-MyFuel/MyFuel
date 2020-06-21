package Contollers;

import boundary.MarketingCampaignBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.MarketingCampaign;
import entity.MarketingCampaignTemplate;
import javafx.application.Platform;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 *  A department responsible for logical calculations and communicating with the client server and DB
 *  For page "MarketingCampaignBoundary"
 *
 * @author Hana Wiener
 * @see MarketingCampaignBoundary - the form's Boundary class
 */

public class MarketingCampaignController extends BasicController {
    /**
     * The boundary controlled by this controller
     */
    private MarketingCampaignBoundary myBoundary;
    /**
     *Variables for use in placing
     */
    private MarketingCampaign tempMarketingCampaign;
    private MarketingCampaign newSale;
    /**
     * A flag that expresses whether the campaign (submitted for review) can run or not
     */
    private boolean flagSale = true;
    /**
     *  Responsible for numbering the new campaign as follows by numbers
     */
    private int SaleCounter;

    /**
     * Instantiates a new Marketing-Campaign Management controller.
     *
     * @param myBoundary the my boundary
     */
    public MarketingCampaignController(MarketingCampaignBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

   /*Logic Methods*/

    /**
     * This method is responsible for getting results from the client
     * Divided into cases to separate getting results from different queries
     *
     * @param result - The result recieved from the DB
     */
    @Override
    public void getResultFromClient(SqlResult result) {

        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_SALES_TABLE:
                    ArrayList<MarketingCampaign> resultList = new ArrayList<>();
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
                    ArrayList<MarketingCampaignTemplate> templateChosenList = new ArrayList<>();
                    templateChosenList.addAll(this.changeResultToChosenTemplateDetails(result));
                    myBoundary.setChosenTemplateDetails(templateChosenList);
                    break;
                case GET_ALL_SALES_TO_CHACK_SALE:
                    flagSale = true;
                    myBoundary.setFlagSale(this.changeResultToSaleAndCheck(result));
                    break;
                default:
                    try {
                    } catch (NullPointerException e) {

                    }
                    break;
            }
        });
    }

    /**
     * This method is responsible for requesting information from DB through the server
     *  The query: GET_ALL_SALES_TABLE
     */
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
    private ArrayList<MarketingCampaign> changeResultToSale(SqlResult result){

        ArrayList<MarketingCampaign> resultList = new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            MarketingCampaign cos = new MarketingCampaign((String) a.get(0),(String)a.get(1),null,null);
            cos.setBeginDate(Date.valueOf((String) a.get(2)));
            cos.setEndDate(Date.valueOf((String) a.get(3)));

            SaleCounter = Integer.parseInt((String) a.get(0));
            resultList.add(cos);
        }
        return resultList;
    }

    /**
     * Returns the sales counter for external departments
     * @return SaleCounter
     */
    public int getSaleCounter() {
        return SaleCounter;
    }

    /**
     * This method is responsible for requesting of save information in DB through the server
     * The query: INSERT_NEW_SALE
     *
     * @param operation
     */
    public void setSaleOperationInDB(MarketingCampaign operation) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(operation.getCampaignID());
        varArray.add(operation.getTemplateName());
        varArray.add(operation.getBeginDate());
        varArray.add(operation.getEndDate());

       SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_SALE, varArray);
       super.sendSqlActionToClient(sqlAction);
    }

    /**
     * This method is responsible for requesting information from DB through the server
     *  The query: GET_ALL_TEMPLATES_TABLE
     */
    public void getTemplateList() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_TEMPLATES_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * This method is responsible for changing the information that came from DB
     *  Change information to : String ArrayList
     * @param result
     * @return  resultList
     */
    private ArrayList<String> changeResultToTemplateList(SqlResult result){
        ArrayList<String> resultList = new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            String tempTemplate = new String((String) a.get(1));
            resultList.add(tempTemplate);
        }
        return resultList;
    }

    /**
     * This method is responsible for requesting information from DB through the server
     *  The query: GET_CHOSEN_TEMPLATE_DETAILS
     */
    public void getChoosenTemplateDetails() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_CHOSEN_TEMPLATE_DETAILS);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * This method is responsible for changing the information that came from DB to entities
     * * Change information to entity: MarketingCampaignTemplate

     * @param result
     * @return resultList
     */
    private ArrayList<MarketingCampaignTemplate> changeResultToChosenTemplateDetails(SqlResult result){
        String myCchosenTemplate = myBoundary.getChoosenTemplate();
        ArrayList<MarketingCampaignTemplate> resultList = new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            if (myCchosenTemplate.equals((String)a.get(1))) {
                MarketingCampaignTemplate cos = new MarketingCampaignTemplate((String) a.get(0), (String)a.get(1), (String) a.get(2),
                        (String) a.get(3),(String) a.get(4), null,null);
                cos.setBeginHour(Time.valueOf((String) a.get(5)));
                cos.setEndHour(Time.valueOf((String) a.get(6)));
                resultList.add(cos);
                System.out.println("lats see" + resultList);
            }//end if
        }//end for
        return resultList;
    }

    /**
     *  This method is responsible for requesting information from DB through the server
     * The query: GET_ALL_SALES_TO_CHACK_SALE
     * to chack if sale can run and not overlap other sales
     *
     * @param newSale
     */
    public void chackIfSaleCanRun(MarketingCampaign newSale) {
        flagSale = true;
        this.newSale = newSale;
        //get all sales from db:
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_SALES_TO_CHACK_SALE);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     *  This method checks whether the campaign can run -
     *  whether there are no dates of other promotions that overlap with it
     *  Keeps the "true" in flag if it can run
     *
     * @param result
     * @return flagSale - if can run sale
     */
    private boolean changeResultToSaleAndCheck(SqlResult result){
        for(ArrayList<Object> a: result.getResultData()) {
            //if the dates are overlap - flag=false.
            if ( (( newSale.getBeginDate().after(Date.valueOf((String) a.get(2)))) ==true &&
                    ( newSale.getBeginDate().before(Date.valueOf((String) a.get(3)))) ==true ) ||
                    (( newSale.getEndDate().after(Date.valueOf((String) a.get(2)))) ==true &&
                            ( newSale.getEndDate().before(Date.valueOf((String) a.get(3)))) ==true )||
                    newSale.getBeginDate().equals(Date.valueOf((String) a.get(2)))  ||
                    newSale.getBeginDate().equals(Date.valueOf((String) a.get(3))) ||
                    newSale.getEndDate().equals(Date.valueOf((String) a.get(2)))  ||
                    newSale.getEndDate().equals(Date.valueOf((String) a.get(3)))   ||
                    ( newSale.getBeginDate().before(Date.valueOf((String) a.get(2))) ==true &&
                            newSale.getEndDate().after(Date.valueOf((String) a.get(3))) ) )
            {
               flagSale = false;
            }
        }
        return flagSale;
    }

    /**
     *  get FlagSale method
     * @return flagSale
     */
    public boolean getFlagSale() { return flagSale;   }

}
