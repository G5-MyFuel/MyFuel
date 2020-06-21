package entity;


import common.assets.enums.FuelTypes;
import org.joda.time.LocalDateTime;

public class Purchase {
    private String purchaseID;
    private String customerID;
    private LocalDateTime purchaseDate;
    private double fuelAmount;
    private double totalPrice;
    private Prices prices;
    private String saleId;

    public Purchase(String customerID, LocalDateTime purchaseDate, double fuelAmount) {
        this.purchaseID = customerID+"O";
        this.customerID = customerID;
        this.purchaseDate = purchaseDate;
        this.fuelAmount = fuelAmount;
        this.totalPrice = 0.0;
    }

    public Purchase(String purchaseID, String customerID, LocalDateTime purchaseDate, double fuelAmount, double totalPrice, Prices prices, String saleId) {
        this.purchaseID = purchaseID;
        this.customerID = customerID;
        this.purchaseDate = purchaseDate;
        this.fuelAmount = fuelAmount;
        this.totalPrice = totalPrice;
        this.prices = prices;
        this.saleId = saleId;
    }

    public String getPurchaseID() {
        return purchaseID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

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
