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
                case GET_COMPANY_FOR_CUSTOMER:
                    this.fromResultToArrayListOfCompanies(result);
                    break;
                case GET_PRICING_MODEL_DISCOUNT:
                    this.fromResultToPricingModelDiscount(result);
                    break;
            }
        });
    }


    public void GET_CURRENT_MARKETING_CAMPEIGN_fromDB() {
        System.out.println("GET_CURRENT_MARKETING_CAMPEIGN_fromDB");
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
        System.out.println("fromResultToMrketingCampaignArrayList");

        myPrices.marketingCapmeignDiscount(resArr);
    }



    public void getCompanysForCustomer(String userId) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(userId);
        System.out.println("getCompanysForCustomer");
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_COMPANY_FOR_CUSTOMER,varArray);
        super.sendSqlActionToClient(sqlAction);

    }


    private void fromResultToArrayListOfCompanies(SqlResult result) {
        ArrayList<String> resArr = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            resArr.add((String) a.get(0)); //company1 name
            resArr.add((String) a.get(1)); //company2 name
            resArr.add((String) a.get(2)); //company3 name
        }
        System.out.println("fromResultToArrayListOfCompanies");

        myPrices.myCompanies(resArr);
    }

    public void getPricingModelDiscount(ArrayList<String> vArray) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(vArray.get(0));
        varArray.add(vArray.get(1));
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_PRICING_MODEL_DISCOUNT, varArray);
        System.out.println("getPricingModelDiscount");

        super.sendSqlActionToClient(sqlAction);
    }

    private void fromResultToPricingModelDiscount(SqlResult result) {
        ArrayList<String> resArr = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            resArr.add((String) a.get(0)); //discount
        }
        System.out.println("fromResultToPricingModelDiscount");
        myPrices.setPricingModelDiscount(resArr);
    }
}




