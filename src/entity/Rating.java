package entity;

public class Rating {

    private Integer rating;
    private String customerID;
    private String customerType;


    public Rating(Integer rating, String customerID, String customerType){
        this.rating = rating;
        this.customerID = customerID;
        this.customerType = customerType;

    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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
}
