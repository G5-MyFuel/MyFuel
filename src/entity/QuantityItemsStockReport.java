package entity;

/**
 *Quantity Items in Stock Report class
 *
 * @author Nir Asulin
 */
public class QuantityItemsStockReport {

    private String fuelType;
    private String availableInventory;

    /**
     * empty Constructor
     */
    public QuantityItemsStockReport() {
    }

    /**
     *
     * Constructor
     *
     * @param fuelType
     * @param availableInventory
     */
    public QuantityItemsStockReport(String fuelType, String availableInventory) {
        this.fuelType = fuelType;
        this.availableInventory = availableInventory;
    }

    /**
     *
     * Constructor
     *
     * @param availableInventory
     */
    public QuantityItemsStockReport(String availableInventory) {
        this.availableInventory = availableInventory;
    }

    /**
     * get Fuel Type method
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
     * get Available Inventory method
     * @return String - AvailableInventory
     */
    public String getAvailableInventory() {
        return availableInventory;
    }

    /**
     * set Available Inventory method
     *
     * @param availableInventory
     */
    public void setAvailableInventory(String availableInventory) {
        this.availableInventory = availableInventory;
    }

    /**
     *
     * toString
     *
     * @return String - Produces a class string
     */
    @Override
    public String toString() {
        return "\nQuantityItemsStockReport{" +
                "fuelType='" + fuelType + '\'' +
                ", availableInventory='" + availableInventory + '\'' +
                '}';
    }
}