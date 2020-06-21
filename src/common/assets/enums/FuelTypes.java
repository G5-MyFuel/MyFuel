package common.assets.enums;

public enum FuelTypes {
    Gasoline95 ("Gasoline95"),
    Diesel("Diesel"),
    ScooterFuel ("ScooterFuel"),
    HomeHeatingFuel ("HomeHeatingFuel");

    private String fuelTypeString;

    FuelTypes(String s){
        this.fuelTypeString = s;
    }

    public static FuelTypes fromString(String text) {
        for (FuelTypes b : FuelTypes.values()) {
            if (b.fuelTypeString.equalsIgnoreCase(text)) {
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
