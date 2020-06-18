package entity;

public class CommentsReport {

    private String customerID;
    private String customerTotalSum;

    public CommentsReport() {
    }

    public CommentsReport(String customerID, String customerTotalSum) {
        this.customerID = customerID;
        this.customerTotalSum = customerTotalSum;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerTotalSum() {
        return customerTotalSum;
    }

    public void setCustomerTotalSum(String customerTotalSum) {
        this.customerTotalSum = customerTotalSum;
    }

    @Override
    public String toString() {
        return "\nCommentsReport{" +
                "customerID='" + customerID + '\'' +
                ", customerTotalSum='" + customerTotalSum + '\'' +
                '}';
    }
}