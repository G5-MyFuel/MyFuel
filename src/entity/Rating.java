package entity;

/**
 *Customer Rating class
 *
 * @author Hana Wiener
 */
public class Rating {

    private Integer rating;
    private String customerID;
    private String customerType;

    /**
     * Constructor
     *
     * @param rating
     * @param customerID
     * @param customerType
     */
    public Rating(Integer rating, String customerID, String customerType){
        this.rating = rating;
        this.customerID = customerID;
        this.customerType = customerType;

    }

    /**
     *  get  Rating method
     * @return rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * set Rating method
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     *  get  CustomerID method
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
     * get  customerType method
     * @return customerType
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * set customerType method
     * @param customerType
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}
