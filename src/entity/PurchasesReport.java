package entity;

public class PurchasesReport {

    private String FuelType;
    private String QuantityPurchased;
    private String SalesAmount;

    public PurchasesReport() {
    }

    public PurchasesReport(String fuelType, String quantityPurchased, String salesAmount) {
        this.FuelType = fuelType;
        this.QuantityPurchased = quantityPurchased;
        this.SalesAmount = salesAmount;
    }

    public String getFuelType() {
        return FuelType;
    }

    public void setFuelType(String fuelType) {
        this.FuelType = fuelType;
    }

    public String getQuantityPurchased() {
        return QuantityPurchased;
    }

    public void setQuantityPurchased(String quantityPurchased) {
        this.QuantityPurchased = quantityPurchased;
    }

    public String getSalesAmount() {
        return SalesAmount;
    }

    public void setSalesAmount(String salesAmount) {
        this.SalesAmount = salesAmount;
    }

    @Override
    public String toString() {
        return "PurchasesReport{" +
                "FuelType='" + FuelType + '\'' +
                ", QuantityPurchased='" + QuantityPurchased + '\'' +
                ", SalesAmount='" + SalesAmount + '\'' +
                '}';
    }
}