package entity;

public class Rating {

    private int rating;
    private int customerID;


    public Rating( int rating,int customerID) {
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
