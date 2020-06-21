package Contollers;

import boundary.MarketingCampaignTemplateBoundary;
import boundary.ViewAnalyticDataBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.MarketingCampaignTemplate;
import javafx.application.Platform;

import java.sql.Time;
import java.util.ArrayList;

/**
 *  A department responsible for logical calculations and communicating with the client server and DB
 *  For page "MarketingCampaignTemplateBoundary"
 *
 *  * @author Hana Wiener
 * @see MarketingCampaignTemplateBoundary - the form's gui controller (boundary) class
 */

public class MarketingCampaignTemplateController extends BasicController  {
    /**     * The boundary controlled by this controller     */
    private MarketingCampaignTemplateBoundary myBoundary;
    /**
     *Variable for use in placing
     */
    private MarketingCampaignTemplate tempTemplate;
    /**
     *  Responsible for numbering the new templates as follows by numbers
     */
   private int TemplateCounter;

    /**
     * Instantiates a new Template Management controller.
     *
     * @param myBoundary the my boundary
     */
    public MarketingCampaignTemplateController(MarketingCampaignTemplateBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /**
     * This method is responsible for getting results from the client
     * Divided into cases to separate getting results from different queries
     *
     * @param result - The result received from the DB
     */
    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_TEMPLATES_TABLE:
                    ArrayList<MarketingCampaignTemplate> resultList = new ArrayList<>();
                    resultList.addAll(this.changeResultToTemplate(result));
                    myBoundary.setTemplateTable(resultList);
                    break;
                case INSERT_NEW_TEMPLATE:
                    break;
                default:
                    break;
            }
        });
    }
    /**
     * This method is responsible for requesting information from DB through the server
     *  The query: GET_ALL_TEMPLATES_TABLE
     */
    public void getTemplatesTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_TEMPLATES_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * This method create array list of templates from the data base result.
     *
     * @param result the result
     * @return Array list of Template
     */
    private ArrayList<MarketingCampaignTemplate> changeResultToTemplate(SqlResult result){
        ArrayList<MarketingCampaignTemplate> resultList = new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            MarketingCampaignTemplate cos = new MarketingCampaignTemplate((String) a.get(0),(String)a.get(1),(String) a.get(2),(String) a.get(3),(String) a.get(4),
                    null,null);
            cos.setBeginHour(Time.valueOf((String) a.get(5)));
            cos.setEndHour(Time.valueOf((String) a.get(6)));
            TemplateCounter= Integer.parseInt((String) a.get(0));
            resultList.add(cos);
        }
        return resultList;
    }

    /**
     * get Template Counter method
     *
     * @return TemplateCounter
     */
    public int getTemplateCounter() {
        return TemplateCounter;
    }

    /**
     * This method is responsible for requesting of save information in DB through the server
     * The query: INSERT_NEW_TEMPLATE
     *
     * @param template
     */
    public void setTemplateInDB(MarketingCampaignTemplate template){
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(template.getTemplateID());
        varArray.add(template.getTemplateName());
        varArray.add(template.getFuelType());
        varArray.add(template.getDiscountPercentages());
        varArray.add(template.getDay());
        varArray.add(template.getBeginHour().toString());
        varArray.add(template.getEndHour().toString());
        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_TEMPLATE, varArray);
        super.sendSqlActionToClient(sqlAction);
    }


}