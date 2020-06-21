package common.assets.enums;

public enum PricingModelTypes {
    Casual_fueling("Casual fueling"),
    Regular_monthly_subscription_single("Regular monthly subscription (single)"),
    Regular_monthly_subscription_multiple("Regular monthly subscription multiple)"),
    Full_monthly_subscription("Full monthly subscription");

    private String pricingModelString;

    PricingModelTypes(String pmt) {
        this.pricingModelString = pmt;
    }

    public String getPricingModelString() {
        return pricingModelString;
    }

    public boolean isEmpty() {
        return pricingModelString.isEmpty();
    }

    public static PricingModelTypes fromString(String text) {
        for (PricingModelTypes b : PricingModelTypes.values()) {
            if (b.pricingModelString.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if (c != null && string != null) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch (IllegalArgumentException ex) {
            }
        }
        return null;
    }
}
