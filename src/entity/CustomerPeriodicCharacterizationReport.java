package entity;

public class CustomerPeriodicCharacterizationReport {

    private String customerID;
    private String yellow;
    private String sonol;
    private String paz;
    private String total;

    public CustomerPeriodicCharacterizationReport() {
    }

    public CustomerPeriodicCharacterizationReport(String customerID, String total) {
        this.customerID = customerID;
        this.total = total;
    }

    public CustomerPeriodicCharacterizationReport(String customerID, String yellow, String sonol, String paz) {
        this.customerID = customerID;
        this.yellow = yellow;
        this.sonol = sonol;
        this.paz = paz;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getYellow() {
        return yellow;
    }

    public void setYellow(String yellow) {
        this.yellow = yellow;
    }

    public String getSonol() {
        return sonol;
    }

    public void setSonol(String sonol) {
        this.sonol = sonol;
    }

    public String getPaz() {
        return paz;
    }

    public void setPaz(String paz) {
        this.paz = paz;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
