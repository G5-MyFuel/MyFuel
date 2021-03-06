package entity;

import Contollers.GeneralDashBoardController;
import Contollers.PricesController;
import common.assets.enums.FuelTypes;
import common.assets.enums.PricingModelTypes;
import common.assets.enums.PurchasePlanTypes;
import common.assets.enums.ShippingMethod;

import java.util.ArrayList;

/**
 * @author Daniel Gabbay
 */
public class Prices {


    /**
     * Misrad Hatahbura prices
     */
    private static Double basePrice_95 = 4.7;
    private static Double basePrice_scooter = 3.8;
    private static Double basePrice_diesel = 5.1;
    public static Double basePrice_homeHeating = 6.2;

    /**
     * Shipping fuel for home heating prices
     */
    private final Double fastShipping = 0.0;
    private final Double standardShipping = 0.0;

    /**
     * Purchase plan discount
     */
    private final Double purchasePlanDiscount_exclusive = 0.95;
    private final Double purchasePlanDiscount_multiple = 0.92;

    //Pricing model discount
    private Double pricingModel_Casual_fueling = 1.0;
    private Double pricingModel_Regular_monthly_subscription_single = 0.96;
    private Double pricingModel_Regular_monthly_subscription_multiple = 0.9;
    private Double pricingModel_Full_monthly_subscription = 0.97;

    //currentPrice details
    private Double fuelAmount;

    public static Double fuelAmountOfPreviousMonth;
    private FuelTypes fuelType;
    private PurchasePlanTypes purchasePlan;

    private PricingModelTypes pricingModelType;
    private static Double totalPrice;
    private Costumer correctCostumer;
    private ShippingMethod sm;
    private String companyName;
    private Double discountOfPricingModel = 1.0;

    public static Double marketingCapmeignDiscount = 1.0;

    public PricesController myController = new PricesController(this);
    private String CampaignID;

    public Prices() {

    }

    GeneralDashBoardController generalDashBoardController = new GeneralDashBoardController();

    /**
     * constructor for home heating purchase
     *
     * @param fuelAmount
     * @param fueltype
     * @param shippingMethod
     */
    public Prices(Costumer correctCostumer, Double fuelAmount, FuelTypes fueltype, ShippingMethod shippingMethod) {
        this.correctCostumer = correctCostumer;
        this.fuelAmount = fuelAmount;
        this.fuelType = fueltype;
        this.purchasePlan = correctCostumer.getPurchasePlanAsEnum();
        this.pricingModelType = correctCostumer.getPricingModelTypeAsEnum();/////////
        this.sm = shippingMethod;
        this.totalPrice = 0.0;
        for (String correctCompanyName : correctCostumer.getFuelCompany()) {
            if (!correctCompanyName.equals("NULL")) {
                companyName = correctCompanyName;
            }
        }
        getDicsount();
    }

    /**
     * constructor for fast fuel purchase
     *
     * @param fuelAmount
     * @param fuelType
     */
    public Prices(Costumer correctCostumer, Double fuelAmount, FuelTypes fuelType) {
        this.correctCostumer = correctCostumer;
        this.fuelAmount = fuelAmount;
        this.fuelType = fuelType;
        this.purchasePlan = correctCostumer.getPurchasePlanAsEnum();
        this.pricingModelType = correctCostumer.getPricingModelTypeAsEnum();
        this.totalPrice = 0.0;
        for (String correctCompanyName : correctCostumer.getFuelCompany()) {
            if (!correctCompanyName.equals("NULL")) {
                companyName = correctCompanyName;
            }
        }
        myController.GET_CURRENT_MARKETING_CAMPEIGN_fromDB();
        getDicsount();
    }


    public void backFromCurrentMarketingCampeign(ArrayList<String> a) {
       CampaignID = a.get(0);
        String TemplateName = a.get(1);
        String campaignFuelType = a.get(2);
        String DiscountPercentages = a.get(3);
        marketingCapmeignDiscount = Double.valueOf(DiscountPercentages);
        if (FuelTypes.contains(campaignFuelType)) {
            switch (campaignFuelType) {
                case "HomeHeatingFuel":
                    break;
                case "ScooterFuel":
                    if (fuelType == (FuelTypes.ScooterFuel))
                        Prices.totalPrice = Prices.totalPrice * marketingCapmeignDiscount;
                    break;
                case "Diesel":
                    if (fuelType == (FuelTypes.Diesel))
                        Prices.totalPrice = Prices.totalPrice * marketingCapmeignDiscount;
                    break;
                case "Gasoline95":
                    if (fuelType == (FuelTypes.Gasoline95))
                        Prices.totalPrice = Prices.totalPrice * marketingCapmeignDiscount;
                    break;
            }
        }
    }

    public void marketingCapmeignDiscount(ArrayList<String> resArr) {
        backFromCurrentMarketingCampeign(resArr);
    }

    public Double calculateTotalPrice() {

        if (correctCostumer == null || fuelAmount == null || fuelType == null || purchasePlan == null || pricingModelType.isEmpty() || totalPrice == null) {
            return null;
        }
        System.out.println("first: " + totalPrice);

        //fuel amount
        totalPrice = getFuelPriceByFuelType(fuelType) * fuelAmount;
        System.out.println("after amount*6.2: " + totalPrice);
        System.out.println("fuelAmountOfPreviousMonth:  "+fuelAmountOfPreviousMonth);
        //pricing model
        switch (pricingModelType) {
            case Casual_fueling:
                totalPrice *= 1.0;
                break;
            case Regular_monthly_subscription_single:
                totalPrice *= pricingModel_Regular_monthly_subscription_single;
                break;
            case Regular_monthly_subscription_multiple:
                totalPrice = (totalPrice *= pricingModel_Regular_monthly_subscription_single) * pricingModel_Regular_monthly_subscription_multiple;
                break;
            case Full_monthly_subscription:
                totalPrice = (totalPrice *= pricingModel_Regular_monthly_subscription_single) * pricingModel_Regular_monthly_subscription_multiple* pricingModel_Full_monthly_subscription;
                //totalPrice = (((totalPrice * generalDashBoardController.getFuelAmountOfPreMonthForCurrentUser()) * pricingModel_Regular_monthly_subscription_single)) * pricingModel_Full_monthly_subscription;
                break;
            default:
                System.err.println("error in pricingModelType - > number between 1-4");
        }
        // calculate purchase plan influence on price
        switch (purchasePlan) {
            case EXCLUSIVE:
                totalPrice = (totalPrice * purchasePlanDiscount_exclusive);
                break;
            case MULTIPLE_STATIONS:
                totalPrice = (totalPrice * purchasePlanDiscount_multiple);
                break;
            case NONE:
                break;
            default:
                System.err.println("error in purchasePlan type");
        }

        //marketing campeign - fast fuel
        if (fuelType.name().equals(FuelTypes.Diesel) || fuelType.name().equals(FuelTypes.Gasoline95) || fuelType.name().equals(FuelTypes.ScooterFuel)) {
            System.out.println("fast fuel");
            setMarketingCampaignDiscountOnTotalPrice();
        }
        //home heating
        if (fuelType.name().equals(FuelTypes.HomeHeatingFuel.toString())) {
            System.out.println("home heating");
            addHomeHeatingPricesAndDiscounts(sm);//Shipping method of Home heating order + amount discount
        }
        return totalPrice;

    }//end of calculateTotalPrice


    public Double getFuelPriceByFuelType(FuelTypes ft) {
        switch (ft) {
            case Diesel:
                return basePrice_diesel;
            case Gasoline95:
                return basePrice_95;
            case ScooterFuel:
                return basePrice_scooter;
            case HomeHeatingFuel:
                return basePrice_homeHeating;
        }
        return null;
    }


    public void addHomeHeatingPricesAndDiscounts(ShippingMethod sm) {
        switch (sm) {
            case FAST:
                totalPrice = totalPrice * 1.02;
                break;
            case STANDARD:
                //  totalPrice = totalPrice + standardShipping;
                break;
            default:
                break;
        }
        //amount discount:
        Double fuelQuantityNum = Double.valueOf(fuelAmount);

        if (fuelQuantityNum >= 600.0 && fuelQuantityNum <= 800.0) {
            totalPrice = totalPrice * 0.97;
        }
        if (fuelQuantityNum > 800.0) {
            totalPrice = totalPrice * 0.96;
        }
        System.out.println(totalPrice);
    }


    public void getDicsount() {
        if (!pricingModelType.getPricingModelString().equals(PricingModelTypes.Casual_fueling.getPricingModelString())) {
            ArrayList<String> varArray = new ArrayList<>();
            varArray.add(pricingModelType.getPricingModelString());
            varArray.add(companyName);
            myController.getPricingModelDiscount(varArray);
        } else {
            discountOfPricingModel = 1.0;
        }
    }

    public void setPricingModelDiscount(ArrayList<String> resArr) {
        switch (pricingModelType){
            case Casual_fueling:
                pricingModel_Casual_fueling = (100 - Double.parseDouble(resArr.get(0))) / 100;
                break;
            case Regular_monthly_subscription_single:
                pricingModel_Regular_monthly_subscription_single = (100 - Double.parseDouble(resArr.get(0))) / 100;
                break;
            case Regular_monthly_subscription_multiple:
                pricingModel_Regular_monthly_subscription_multiple = (100 - Double.parseDouble(resArr.get(0))) / 100;
                break;
            case Full_monthly_subscription:
                pricingModel_Full_monthly_subscription = (100 - Double.parseDouble(resArr.get(0))) / 100;
                break;

        };
    }

    /* *****************getters and setter*******************
     * *****************getters and setter*******************
     * *****************getters and setter*******************
     * */

    public String getCampaignID() {
        return CampaignID;
    }

    public void setCampaignID(String campaignID) {
        CampaignID = campaignID;
    }

    public Double getBasePrice_95() {
        return basePrice_95;
    }

    public Double getBasePrice_scooter() {
        return basePrice_scooter;
    }

    public Double getBasePrice_diesel() {
        return basePrice_diesel;
    }

    public Double getBasePrice_homeHeating() {
        return basePrice_homeHeating;
    }

    public Double getPurchasePlanDiscount_exclusive() {
        return purchasePlanDiscount_exclusive;
    }

    public Double getPurchasePlanDiscount_multiple() {
        return purchasePlanDiscount_multiple;
    }

    public static void setBasePrice_95(Double basePrice_95) {
        Prices.basePrice_95 = basePrice_95;
    }

    public static void setBasePrice_scooter(Double basePrice_scooter) {
        Prices.basePrice_scooter = basePrice_scooter;
    }

    public static void setBasePrice_diesel(Double basePrice_diesel) {
        Prices.basePrice_diesel = basePrice_diesel;
    }

    public static void setBasePrice_homeHeating(Double basePrice_homeHeating) {
        Prices.basePrice_homeHeating = basePrice_homeHeating;
    }

    public Double getPricingModel_Casual_fueling() {
        return pricingModel_Casual_fueling;
    }

    public void setPricingModel_Casual_fueling(Double pricingModel_Casual_fueling) {
        this.pricingModel_Casual_fueling = pricingModel_Casual_fueling;
    }

    public Double getPricingModel_Regular_monthly_subscription_single() {
        return pricingModel_Regular_monthly_subscription_single;
    }

    public void setPricingModel_Regular_monthly_subscription_single(Double pricingModel_Regular_monthly_subscription_single) {
        this.pricingModel_Regular_monthly_subscription_single = pricingModel_Regular_monthly_subscription_single;
    }

    public Double getPricingModel_Regular_monthly_subscription_multiple() {
        return pricingModel_Regular_monthly_subscription_multiple;
    }

    public void setPricingModel_Regular_monthly_subscription_multiple(Double pricingModel_Regular_monthly_subscription_multiple) {
        this.pricingModel_Regular_monthly_subscription_multiple = pricingModel_Regular_monthly_subscription_multiple;
    }

    public Double getPricingModel_Full_monthly_subscription() {
        return pricingModel_Full_monthly_subscription;
    }

    public void setPricingModel_Full_monthly_subscription(Double pricingModel_Full_monthly_subscription) {
        this.pricingModel_Full_monthly_subscription = pricingModel_Full_monthly_subscription;
    }

    public ShippingMethod getSm() {
        return sm;
    }

    public void setSm(ShippingMethod sm) {
        this.sm = sm;
    }

    public Double getFuelAmountOfPreviousMonth() {
        return fuelAmountOfPreviousMonth;
    }

    public void setFuelAmountOfPreviousMonth(Double fuelAmountOfPreviousMonth) {
        this.fuelAmountOfPreviousMonth = fuelAmountOfPreviousMonth;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //
    private void setMarketingCampaignDiscountOnTotalPrice() {

    }

    public void setFastShippingTotalPrice() {
//????????
    }
    public PricingModelTypes getPricingModelType() {
        return pricingModelType;
    }

    public void setFuelAmount(Double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }


}//End of class

