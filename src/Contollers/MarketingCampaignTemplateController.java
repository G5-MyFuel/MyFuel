package Contollers;

import boundary.MarketingCampaignTemplateBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.MarketingCampaignTemplate;
import javafx.application.Platform;

import java.sql.Time;
import java.util.ArrayList;

/**
 * @author Hana Wiener
 */

public class MarketingCampaignTemplateController extends BasicController  {

    private MarketingCampaignTemplateBoundary myBoundary; /**     * The boundary controlled by this controller     */
   private MarketingCampaignTemplate tempTemplate;
   private int TemplateCounter;

    /**
     * Instantiates a new Template Management controller.
     *
     * @param myBoundary the my boundary
     */
    public MarketingCampaignTemplateController(MarketingCampaignTemplateBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }


    public void getTemplatesTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_TEMPLATES_TABLE);
        super.sendSqlActionToClient(sqlAction);

    }

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
     * This method create array list of templates from the data base result.
     *
     * @param result the result
     * @return Array list of costumers
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

    public int getTemplateCounter() {
        return TemplateCounter;
    }

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