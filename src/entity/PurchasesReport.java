package entity;

/**
 * Purchases Report class
 *
 * @author Nir Asulin
 */
public class PurchasesReport {

    private String fuelType;
    private String quantityPurchased;
    private String salesAmount;

    /**
     * empty Constructor
     */
    public PurchasesReport() {
    }

    /**
     * Constructor
     *
     * @param fuelType
     * @param quantityPurchased
     * @param salesAmount
     */
    public PurchasesReport(String fuelType, String quantityPurchased, String salesAmount) {
        this.fuelType = fuelType;
        this.quantityPurchased = quantityPurchased;
        this.salesAmount = salesAmount;
    }

    /**
     * Constructor
     *
     * @param fuelType
     */
    public PurchasesReport(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * get Fuel Type method
     *
     * @return String - FuelType
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * set fuel Type method
     *
     * @param fuelType
     */
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * get Quantity Purchased method
     *
     * @return String - QuantityPurchased
     */
    public String getQuantityPurchased() {
        return quantityPurchased;
    }

    /**
     * set quantity Purchased method
     *
     * @param quantityPurchased
     */
    public void setQuantityPurchased(String quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    /**
     * get getSales Amount method
     *
     * @return String - getSalesAmount
     */
    public String getSalesAmount() {
        return salesAmount;
    }

    /**
     * set sales Amount method
     *
     * @param salesAmount
     */
    public void setSalesAmount(String salesAmount) {
        this.salesAmount = salesAmount;
    }

    /**
     * toString
     *
     * @return String - Produces a class string
     */
    @Override
    public String toString() {
        return "PurchasesReport{" +
                "FuelType='" + fuelType + '\'' +
                ", QuantityPurchased='" + quantityPurchased + '\'' +
                ", SalesAmount='" + salesAmount + '\'' +
                '}';
    }
}