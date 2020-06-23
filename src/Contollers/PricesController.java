package Contollers;

import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.Prices;
import javafx.application.Platform;

import java.util.ArrayList;

public class PricesController extends BasicController {

    private Prices myPrices;

    public PricesController(Prices p) {
        this.myPrices = p;
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_CURRENT_MARKETING_CAMPEIGN:
                    this.fromResultToMrketingCampaignArrayList(result);
                    break;
            }
        });
    }

    public void GET_CURRENT_MARKETING_CAMPEIGN_fromDB() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_CURRENT_MARKETING_CAMPEIGN);
        super.sendSqlActionToClient(sqlAction);
    }

    public void fromResultToMrketingCampaignArrayList(SqlResult result) {
        ArrayList<String> resArr = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            resArr.add((String) a.get(0)); //CampaignID
            resArr.add((String) a.get(1)); //TemplateName
            resArr.add((String) a.get(2)); //fuelType
            resArr.add((String) a.get(3)); //DiscountPercentages
        }
        myPrices.marketingCapmeignDiscount(resArr);
    }

}




