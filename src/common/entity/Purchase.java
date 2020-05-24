package common.entity;

import java.util.Date;

public class Purchase {
    String purchaseID;
    String customerID;
    Date purchaseDate;
    double fuelAmount;
    double totalPrice;

    public Purchase(String purchaseID, String customerID, Date purchaseDate, double fuelAmount) {
        this.purchaseID = purchaseID;
        this.customerID = customerID;
        this.purchaseDate = purchaseDate;
        this.fuelAmount = fuelAmount;
        this.totalPrice = 0.0;
    }

    public String getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(String purchaseID) {
        this.purchaseID = purchaseID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
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
