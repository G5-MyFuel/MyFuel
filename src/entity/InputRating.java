package entity;

/**
 * @see InputRating - the form's entity class
 */
public class InputRating {

    //private int rating;
    private String customerID;
    private String customerType;
    private String purchaseID;
    private String fuelType;
    private String purchaseHour;
    private boolean flag;

    public InputRating(String customerID, String customerType, String purchaseID, String fuelType, String purchaseHour) {
        this.customerID = customerID;
        this.customerType = customerType;
        this.purchaseID = purchaseID;
        this.fuelType = fuelType;
        this.purchaseHour = purchaseHour;
        flag = false;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
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
