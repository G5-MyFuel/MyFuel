package entity;

public class Rating {

    private int rating;
    private int customerID;
    private String customerType;


    public Rating(int rating, int customerID, String customerType){
        this.rating = rating;
        this.customerID = customerID;
        this.customerType = customerType;

    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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
}
