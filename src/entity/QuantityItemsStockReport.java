package entity;

public class QuantityItemsStockReport {

    private String companyName;
    private String 	fuelType;
    private String 	availableInventory;

    public QuantityItemsStockReport() {
    }

    public QuantityItemsStockReport(String companyName, String fuelType, String availableInventory) {
        this.companyName = companyName;
        this.fuelType = fuelType;
        this.availableInventory = availableInventory;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
}
