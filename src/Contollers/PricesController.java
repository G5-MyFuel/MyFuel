package Contollers;

import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.Prices;
import javafx.application.Platform;

import java.util.ArrayList;

public class PricesController extends BasicController {

    private Prices myPrices;
    private  Double fuelAmountOfPreMonthForCurrentUser = 0.0;


    public PricesController(Prices p) {
        this.myPrices = p;
    }

    public void getPricingModelDiscount(ArrayList<String> vArray) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(vArray.get(0));
        varArray.add(vArray.get(1));
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_PRICING_MODEL_DISCOUNT, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_CURRENT_MARKETING_CAMPEIGN:
                    this.fromResultToMrketingCampaignArrayList(result);
                    break;
                case GET_PRICING_MODEL_DISCOUNT:
                    this.fromResultToPricingModelDiscount(result);
                    break;
            }
        });
    }


    public void GET_CURRENT_MARKETING_CAMPEIGN_fromDB() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_CURRENT_MARKETING_CAMPEIGN);
        super.sendSqlActionToClient(sqlAction);
    }

    public void fromResultToMrketingCampaignArrayList(SqlResult result) {
        System.out.println(result.getResultData().toString());
        ArrayList<String> resArr = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            resArr.add((String) a.get(0)); //CampaignID
            resArr.add((String) a.get(1)); //TemplateName
            resArr.add((String) a.get(2)); //fuelType
            resArr.add((String) a.get(3)); //DiscountPercentages
        }
        myPrices.marketingCapmeignDiscount(resArr);
    }

    private void fromResultToPricingModelDiscount(SqlResult result) {
        ArrayList<String> resArr = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            resArr.add((String) a.get(0)); //discount
        }
        System.out.println(resArr.toString());
        myPrices.setPricingModelDiscount(resArr);
    }
}