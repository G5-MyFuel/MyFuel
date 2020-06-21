package common.assets.enums;

public enum PurchasePlanTypes {
    EXCLUSIVE("Exclusive"),
    MULTIPLE_STATIONS("Multiple Stations"),
    NONE("None");

    private String purchasePlanString;

    PurchasePlanTypes(String s){
        this.purchasePlanString = s;
    }

    public static PurchasePlanTypes fromString(String text) {
        for (PurchasePlanTypes b : PurchasePlanTypes.values()) {
            if (b.purchasePlanString.equalsIgnoreCase(text)) {
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
