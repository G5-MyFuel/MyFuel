package entity;

/**
 * Customer Periodic Characterization Report class
 *
 * @author Nir Asulin
 */
public class CustomerPeriodicCharacterizationReport {

    private String customerID;
    private String yellow;
    private String sonol;
    private String paz;
    private String total;

    /**
     * empty Constructor
     */
    public CustomerPeriodicCharacterizationReport() {
    }

    /**
     * Constructor
     *
     * @param customerID
     * @param total
     */
    public CustomerPeriodicCharacterizationReport(String customerID, String total) {
        this.customerID = customerID;
        this.total = total;
    }

    /**
     * Constructor
     *
     * @param customerID
     * @param yellow
     * @param sonol
     * @param paz
     */
    public CustomerPeriodicCharacterizationReport(String customerID, String yellow, String sonol, String paz) {
        this.customerID = customerID;
        this.yellow = yellow;
        this.sonol = sonol;
        this.paz = paz;
    }

    /**
     * get Customer ID method
     *
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
     * get Yellow method
     *
     * @return String - getYellow
     */
    public String getYellow() {
        return yellow;
    }

    /**
     * set yellow method
     *
     * @param yellow
     */
    public void setYellow(String yellow) {
        this.yellow = yellow;
    }

    /**
     * get Sonol method
     *
     * @return String - getSonol
     */
    public String getSonol() {
        return sonol;
    }

    /**
     * set sonol method
     *
     * @param sonol
     */
    public void setSonol(String sonol) {
        this.sonol = sonol;
    }

    /**
     * get get Paz method
     *
     * @return String - getPaz
     */
    public String getPaz() {
        return paz;
    }

    /**
     * set paz method
     *
     * @param paz
     */
    public void setPaz(String paz) {
        this.paz = paz;
    }

    /**
     * get get Total method
     *
     * @return String - getTotal
     */
    public String getTotal() {
        return total;
    }

    /**
     * set total method
     *
     * @param total
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * toString
     *
     * @return String - Produces a class string
     */
    @Override
    public String toString() {
        return "\nCustomerPeriodicCharacterizationReport{" +
                "customerID='" + customerID + '\'' +
                ", yellow='" + yellow + '\'' +
                ", sonol='" + sonol + '\'' +
                ", paz='" + paz + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}