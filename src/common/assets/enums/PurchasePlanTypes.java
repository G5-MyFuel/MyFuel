package common.assets.enums;

public enum PurchasePlanTypes {
    EXCLUSIVE("Exclusive"),
    MULTIPLE_STATIONS("Multiple Stations"),
    NONE("None");

    private String purchasePlanString;

    PurchasePlanTypes(String p){
        this.purchasePlanString=p;
    }


    public String getPurchasePlanString() {
        return purchasePlanString;
    }
}
