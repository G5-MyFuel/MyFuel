package common.entity;


import java.util.ArrayList;

/**
 * @author itay
 * @see Costumer - the form's entity class
 */

public class Costumer {

    private CreditCard CostumerCreditCard;
    private ArrayList<Vehicle> CostumerVehicle = new ArrayList<Vehicle>();
    private String emailAdress;
    private String Lname;
    private String Fname;
    private String CustomerPassword;
    private Integer CostumerType;
    private Integer ID;
    private String servicePlan;
    private boolean purchasePlan;

    public Costumer(int ID, String CustomerPassword, int  CostumerType, String Fname, String Lname, String emailAdress, CreditCard customerCreditCard, boolean purchasePlan, Vehicle CostumerVehicle,String servicePlan) {
        this.CostumerCreditCard = customerCreditCard;
        this.purchasePlan = purchasePlan;
        this.CostumerVehicle.add(CostumerVehicle);
        this.servicePlan = servicePlan;
        this.emailAdress = emailAdress;
        this.Lname = Lname;
        this.Fname = Fname;
        this.CustomerPassword = CustomerPassword;
        this.CostumerType = CostumerType;
        this.ID = ID;
    }

    public void setPurchasePlan(boolean purchasePlan) {
        this.purchasePlan = purchasePlan;
    }
    public boolean getPurchasePlan() {
        return this.purchasePlan;
    }

    public CreditCard getCostumerCreditCard() {
        return CostumerCreditCard;
    }

    public void setCostumerCreditCard(CreditCard costumerCreditCard) {
        this.CostumerCreditCard = costumerCreditCard;
    }

    public boolean isPurchasePlan() {
        return purchasePlan;
    }

    public String getServicePlan() {
        return servicePlan;
    }

    public void setServicePlan(String servicePlan) {
        this.servicePlan = servicePlan;
    }

    public ArrayList<Vehicle> getCostumerVehicle() {
        return CostumerVehicle;
    }
    public void addCostumerVehicle(Vehicle costumerVehicle) {
        this.CostumerVehicle.add(costumerVehicle);
    }
    public void setCostumerVehicle(ArrayList<Vehicle> costumerVehicle) {
        CostumerVehicle = costumerVehicle;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getCustomerPassword() {
        return CustomerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        CustomerPassword = customerPassword;
    }

    public Integer getCostumerType() {
        return CostumerType;
    }

    public void setCostumerType(Integer costumerType) {
        CostumerType = costumerType;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}
