package entity;

public class CustomerPeriodicCharacterizationReport {

    private String customerID;
    private String Yellow;
    private String Sonol;
    private String Paz;
    private String Total;

    public CustomerPeriodicCharacterizationReport() {
    }

    public CustomerPeriodicCharacterizationReport(String customerID, String total) {
        this.customerID = customerID;
        Total = total;
    }

    public CustomerPeriodicCharacterizationReport(String yellow, String sonol, String paz) {
        Yellow = yellow;
        Sonol = sonol;
        Paz = paz;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getYellow() {
        return Yellow;
    }

    public void setYellow(String yellow) {
        Yellow = yellow;
    }

    public String getSonol() {
        return Sonol;
    }

    public void setSonol(String sonol) {
        Sonol = sonol;
    }

    public String getPaz() {
        return Paz;
    }

    public void setPaz(String paz) {
        Paz = paz;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
