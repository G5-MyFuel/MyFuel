package entity;

public class QuantityItemsStockReport {

    private String fuelType;
    private String availableInventory;

    public QuantityItemsStockReport() {
    }

    public QuantityItemsStockReport(String fuelType, String availableInventory) {
        this.fuelType = fuelType;
        this.availableInventory = availableInventory;
    }

    public QuantityItemsStockReport(String availableInventory) {
        this.availableInventory = availableInventory;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getAvailableInventory() {
        return availableInventory;
    }

    public void setAvailableInventory(String availableInventory) {
        this.availableInventory = availableInventory;
    }

    @Override
    public String toString() {
        return "\nQuantityItemsStockReport{" +
                "fuelType='" + fuelType + '\'' +
                ", availableInventory='" + availableInventory + '\'' +
                '}';
    }
}