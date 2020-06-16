package Contollers;

import boundary.MarketingCampaignTemplateBoundary;
import boundary.ViewAnalyticDataBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.MarketingCampaignTemplate;
import entity.Rating;
import javafx.application.Platform;
import server.MysqlConnection;

import java.sql.Time;
import java.util.ArrayList;

public class ViewAnalyticDataController extends BasicController {
    private ViewAnalyticDataBoundary myBoundary; /**     * The boundary controlled by this controller     */
    private AnalyticDataCreator analyticDataCreator = new AnalyticDataCreator(myBoundary);

    /*Logic Variables*/
    private static ViewAnalyticDataController Instance = null;

    public ViewAnalyticDataController(ViewAnalyticDataBoundary viewAnalyticDataBoundary) {
        this.myBoundary = myBoundary;
    }


    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_RATING_TABLE:
                    ArrayList<Rating> resultList = new ArrayList<>();
                    resultList.addAll(this.changeResultToRating(result));
                    myBoundary.setRatingTable(resultList);

                    break;

                default:
                    break;
            }
        });
    }

    public void getRatingTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_RATING_TABLE);
        super.sendSqlActionToClient(sqlAction);

    }

    private ArrayList<Rating> changeResultToRating(SqlResult result){
        ArrayList<Rating> resultList = new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            Rating cos = new Rating(Integer.parseInt((String) a.get(0)),
                    (Integer)a.get(1),
                    (String)a.get(2));
            resultList.add(cos);
             }
        return resultList;
    }

    public void GetDateToCalcRating (){
       // SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_PURCHASE_TABLE);
        //super.sendSqlActionToClient(sqlAction);

    }
}