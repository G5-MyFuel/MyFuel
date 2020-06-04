package common.entity;


import java.util.ArrayList;

/**
 * @author itay
 * @see Costumer - the form's entity class
 */

public class Costumer extends User {

    private CreditCard CostumerCreditCard;
    private ArrayList<Vehicle> CostumerVehicle = new ArrayList<Vehicle>();

    private String servicePlan;
    private boolean purchasePlan;

    public Costumer(int ID, String CustomerPassword, int  CostumerType, String Fname, String Lname, String emailAdress, CreditCard customerCreditCard, boolean purchasePlan, Vehicle CostumerVehicle,String servicePlan) {
        super(ID,CostumerType, CustomerPassword, Fname, Lname, emailAdress);
        this.CostumerCreditCard = customerCreditCard;
        this.purchasePlan = purchasePlan;
        this.CostumerVehicle.add(CostumerVehicle);
        this.servicePlan = servicePlan;
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
}
