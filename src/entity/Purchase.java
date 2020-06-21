package entity;
import common.assets.enums.FuelTypes;
import org.joda.time.LocalDateTime;

/**
 * Purchase entity
 *
 * @author Hana Wiener
 */
public class Purchase {
    private String purchaseID;
    private String customerID;
    private LocalDateTime purchaseDate;
    private double fuelAmount;
    private double totalPrice;
    private Prices prices;
    private String saleId;

    /**
     * constructor - part of the parameters
     * @param customerID
     * @param purchaseDate
     * @param fuelAmount
     */
    public Purchase(String customerID, LocalDateTime purchaseDate, double fuelAmount) {
        this.purchaseID = customerID+"O";
        this.customerID = customerID;
        this.purchaseDate = purchaseDate;
        this.fuelAmount = fuelAmount;
        this.totalPrice = 0.0;
    }

    /**
     * constructor - all the parameters
     * @param purchaseID
     * @param customerID
     * @param purchaseDate
     * @param fuelAmount
     * @param totalPrice
     * @param prices
     * @param saleId
     */
    public Purchase(String purchaseID, String customerID, LocalDateTime purchaseDate, double fuelAmount, double totalPrice, Prices prices, String saleId) {
        this.purchaseID = purchaseID;
        this.customerID = customerID;
        this.purchaseDate = purchaseDate;
        this.fuelAmount = fuelAmount;
        this.totalPrice = totalPrice;
        this.prices = prices;
        this.saleId = saleId;
    }

    /**
     * get Purchase ID method
     * @return purchaseID
     */
    public String getPurchaseID() {
        return purchaseID;
    }

    /**
     * get Customer ID method
     * @return customerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * set CustomerID method
     * @param customerID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * get Purchase Date method
     * @return purchaseDate
     */
    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    /**
     *set PurchaseDate method
     * @param purchaseDate
     */
    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * get FuelAmount method
     * @return fuelAmount
     */
    public double getFuelAmount() {
        return fuelAmount;
    }

    /**
     * set FuelAmount method
     * @param fuelAmount
     */
    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    /**
     * get TotalPrice method
     * @return totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * set TotalPrice method
     * @param totalPrice
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * get SaleId  method
     * @return saleId
     */
    public String getSaleId() {
        return saleId;
    }

    /**
     * setSaleId method
     * @param saleId
     */
    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    /**
     * to string method
     * @return string of Purchase
     */
    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseID='" + purchaseID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", fuelAmount=" + fuelAmount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
