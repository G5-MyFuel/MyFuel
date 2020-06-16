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

    /*Logic Variables*/
    private static ViewAnalyticDataController Instance = null; //no need?

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
            Rating cos = new Rating(Integer.parseInt((String) a.get(0)),Integer.parseInt((String)a.get(1)));//?????????
            resultList.add(cos);
        }
        return resultList;
    }


}