package entity;

public class Rating {


    private int customerID;
    private int rating;

    public Rating(int customerID, int rating) {
        this.customerID = customerID;
        this.rating = rating;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
