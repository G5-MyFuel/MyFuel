package entity;

public class CommentsReport {

    private String customerID;
    private String customerTotalSum;
    private String TotalSum;

    public CommentsReport() {
    }

    public CommentsReport(String customerID, String customerTotalSum, String totalSum) {
        this.customerID = customerID;
        this.customerTotalSum = customerTotalSum;
        TotalSum = totalSum;
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

    public String getTotalSum() {
        return TotalSum;
    }

    public void setTotalSum(String totalSum) {
        TotalSum = totalSum;
    }
}
