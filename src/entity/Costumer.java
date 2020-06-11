package entity;


import javafx.scene.control.CustomMenuItem;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author itay
 * @see Costumer - the form's entity class
 */

public class Costumer extends User{

    private CreditCard CostumerCreditCard;
    private ArrayList<Vehicle> CostumerVehicle = new ArrayList<Vehicle>();
    private String emailAdress;
    private String Lname;
    private String Fname;
    private String CustomerPassword;
    private String CostumerType;
    private String ID;
    private String servicePlan;
    private String purchasePlan;

    public Costumer(String ID, String CustomerPassword, String CostumerType, String Fname, String Lname, String emailAdress, CreditCard customerCreditCard, String purchasePlan, Vehicle CostumerVehicle,String servicePlan) {
        super(ID,1, CustomerPassword,Fname,Lname,emailAdress);
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

    public void setPurchasePlan(String purchasePlan) {
        this.purchasePlan = purchasePlan;
    }
    public String getPurchasePlan() {
        return this.purchasePlan;
    }

    public CreditCard getCostumerCreditCard() {
        return CostumerCreditCard;
    }

    public void setCostumerCreditCard(CreditCard costumerCreditCard) {
        this.CostumerCreditCard = costumerCreditCard;
    }

    public String isPurchasePlan() {
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

    public String getCostumerType() {
        return CostumerType;
    }

    public void setCostumerType(String costumerType) {
        CostumerType = costumerType;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Costumer costumer = (Costumer) o;
        return ID.equals(costumer.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
