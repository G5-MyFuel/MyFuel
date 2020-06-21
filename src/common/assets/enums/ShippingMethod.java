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

    public static ShippingMethod fromString(String text) {
        for (ShippingMethod b : ShippingMethod.values()) {
            if (b.shippingMethodStr.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if( c != null && string != null ) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch(IllegalArgumentException ex) {
            }
        }
        return null;
    }
}
