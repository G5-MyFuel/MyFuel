package entity;

public class PurchasesReport {

    private String fuelType;
    private String quantityPurchased;
    private String salesAmount;

    public PurchasesReport() {
    }

    public PurchasesReport(String fuelType, String quantityPurchased, String salesAmount) {
        this.fuelType = fuelType;
        this.quantityPurchased = quantityPurchased;
        this.salesAmount = salesAmount;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(String quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public String getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(String salesAmount) {
        this.salesAmount = salesAmount;
    }

    @Override
    public String toString() {
        return "PurchasesReport{" +
                "FuelType='" + fuelType + '\'' +
                ", QuantityPurchased='" + quantityPurchased + '\'' +
                ", SalesAmount='" + salesAmount + '\'' +
                '}';
    }
}