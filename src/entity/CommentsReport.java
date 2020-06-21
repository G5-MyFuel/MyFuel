package entity;

/**
 *Comments Report class
 *
 * @author Nir Asulin
 */
public class CommentsReport {

    private String customerID;
    private String customerTotalSum;

    /**
     * empty Constructor
     */
    public CommentsReport() {
    }

    /**
     *
     * Constructor
     *
     * @param customerID
     * @param customerTotalSum
     */
    public CommentsReport(String customerID, String customerTotalSum) {
        this.customerID = customerID;
        this.customerTotalSum = customerTotalSum;
    }

    /**
     * get Customer ID method
     * @return String - CustomerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * set customer ID method
     *
     * @param customerID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * get Customer Total Sum method
     * @return String - CustomerTotalSum
     */
    public String getCustomerTotalSum() {
        return customerTotalSum;
    }

    /**
     * set customer Total Sum method
     *
     * @param customerTotalSum
     */
    public void setCustomerTotalSum(String customerTotalSum) {
        this.customerTotalSum = customerTotalSum;
    }

    /**
     *
     * toString
     *
     * @return String - Produces a class string
     */
    @Override
    public String toString() {
        return "\nCommentsReport{" +
                "customerID='" + customerID + '\'' +
                ", customerTotalSum='" + customerTotalSum + '\'' +
                '}';
    }
}