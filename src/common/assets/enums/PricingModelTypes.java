package common.assets.enums;

public enum PricingModelTypes {
    Casual_fueling("Casual fueling"),
    Regular_monthly_subscription_single("Regular monthly subscription (single)"),
    Regular_monthly_subscription_multiple("Regular monthly subscription (multiple)"),
    Full_monthly_subscription("Full monthly subscription");

    private String pricingModelString;

    PricingModelTypes(String pmt){
        this.pricingModelString = pmt;
    }

    public String getPricingModelString(){
        return pricingModelString;
    }

    public boolean isEmpty(){
        return pricingModelString.isEmpty();
    }
}
