package entity;

import java.sql.Time;

public class InputRating {

    //private int rating;
    private int customerID;
    private String customerType;
    private String purchaseID;
    private String fuelType;
    private String purchaseHour;
    private boolean flag;

    public InputRating(int customerID, String customerType, String purchaseID, String fuelType, String purchaseHour) {
        this.customerID = customerID;
        this.customerType = customerType;
        this.purchaseID = purchaseID;
        this.fuelType = fuelType;
        this.purchaseHour = purchaseHour;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(String purchaseID) {
        this.purchaseID = purchaseID;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getPurchaseHour() {
        return purchaseHour;
    }

    public void setPurchaseHour(String purchaseHour) {
        this.purchaseHour = purchaseHour;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
