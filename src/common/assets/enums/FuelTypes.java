package common.assets.enums;

public enum FuelTypes {
    Gasoline95("Gasoline-95"),
    Diesel("Diesel"),
    ScooterFuel("Scooter Fuel"),
    HomeHeatingFuel("HomeHeatingFuel");

    private String fuelTypeString;

    FuelTypes(String s) {
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

    public static  boolean contains(String fuelTypeString) {
        for (FuelTypes f : FuelTypes.values()) {
            if (f.name().equals(fuelTypeString)) return true;
        }
        return false;
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

    public String getFuelTypeString() {
        return fuelTypeString;
    }


}
