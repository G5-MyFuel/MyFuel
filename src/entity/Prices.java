package entity;

import common.assets.enums.FuelTypes;

public class Prices {
    //Misrad Hatahbura prices
    private static Double basePrice_95 = 4.7;
    private static Double basePrice_scooter = 3.8;
    private static Double basePrice_diesel = 5.1;
    private static Double basePrice_homeHeating = 6.2;

    //Purchase plan discount
    private final Double purchasePlanDiscount_exclusive = 0.95;
    private final Double purchasePlanDiscount_multiple = 0.92;

    //Pricing model discount
    private Double pricingModel_type1 = 1.0;
    private Double pricingModel_type2 = 0.96;
    private Double pricingModel_type3 = 0.9;
    private Double pricingModel_type4 = 0.97;

    //currentPrice details
    private Double fuelAmount;
    private Double fuelAmountOfPreviousMonth;
    private FuelTypes fuelType;
    private String purchasePlan;
    private int pricingModelType = 0;
    private Double totalPrice;
    private String userID;

    public Prices() {

    }

    public Prices(String userId, Double fuelAmount, FuelTypes fueltype, String purchasePlan, int pricingModelType) {
        //לקחת מהד"ב מחירים עדכניים - ממה שניר עשה - מודל תמחור
        //להריץ שאילתא שמקבלת את כמות הדלק של כל ההזמנות בטווח התאריכים של חודש קודם - עבור מנוי (4)
        this.userID = userId;
        // this.fuelAmountOfPreviousMonth = getAllUserPurchaseAmountInLastMonth();
        this.fuelAmount = fuelAmount;
        this.fuelType = fueltype;
        this.purchasePlan = purchasePlan;
        this.pricingModelType = pricingModelType;
        this.totalPrice = 0.0;
        calculateTotalPrice();
    }


    public Double calculateTotalPrice() {
        if (userID.isEmpty() || fuelAmount == null || fuelType == null || purchasePlan == null || pricingModelType == 0 || totalPrice == null)
            return null;
        totalPrice = getFuelPriceByFuelType(fuelType) * fuelAmount;
        switch (pricingModelType) {
            case 1:
                totalPrice *= pricingModel_type1;
                break;

            case 2:
                totalPrice *= pricingModel_type2;
                break;

            case 3:
                totalPrice = (totalPrice *= pricingModel_type2) * pricingModel_type3;
                break;

            case 4:

                //((((totalPrice*previousMonthFuelAmount)*0.96)*0.9))*0.97
                //totalPrice = ;
                break;
            default:
                System.err.println("error in pricingModelType - > number between 1-4");
        }

        //
        //
        switch (purchasePlan) {
            case "Exclusive":

                break;
            case "Multiple Stations":

                break;

            case "None":

                break;
        }
        return null;//??
    }

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

    public Double getDiscountPricingModel_type1() {
        return pricingModel_type1;
    }

    public Double getDiscountPricingModel_type2() {
        return pricingModel_type1;
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

    public Double getPricingModel_type1() {
        return pricingModel_type1;
    }

    public Double getPricingModel_type2() {
        return pricingModel_type2;
    }

    public void setPricingModel_type2(Double pricingModel_type2) {
        this.pricingModel_type2 = pricingModel_type2;
    }

    public Double getPricingModel_type3() {
        return pricingModel_type3;
    }

    public void setPricingModel_type3(Double pricingModel_type3) {
        this.pricingModel_type3 = pricingModel_type3;
    }

    public Double getPricingModel_type4() {
        return pricingModel_type4;
    }

    public void setPricingModel_type4(Double pricingModel_type4) {
        this.pricingModel_type4 = pricingModel_type4;
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

    @Override
    public String toString() {
        return "diesel " + this.getBasePrice_diesel() + " 95 " + this.getBasePrice_95() + " scooter " + this.getBasePrice_scooter() + " homeHeating " + this.getBasePrice_homeHeating();
    }
}
