package common.assets.enums;

public enum ShippingMethod {
    STANDARD("Standard Shipping"),
    FAST("Fast Shipping");

    private String shippingMethodStr;

    ShippingMethod(String str){
        this.shippingMethodStr=str;
    }

    public String getShippingMethodStr() {
        return shippingMethodStr;
    }
}
