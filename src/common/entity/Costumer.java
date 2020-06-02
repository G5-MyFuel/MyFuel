package common.entity;


import java.util.ArrayList;

/**
 * @author itay
 * @see Costumer - the form's entity class
 */

public class Costumer extends User {

    private CreditCard CostumerCreditCard;
    private ArrayList<Vehicle> CostumerVehicle = new ArrayList<Vehicle>();

    private boolean purchasePlan;

    public Costumer(int ID, String CustomerPassword, int  CostumerType, String Fname, String Lname, String emailAdress, CreditCard customerCreditCard, boolean purchasePlan, Vehicle CostumerVehicle) {
        super(ID,CostumerType, CustomerPassword, Fname, Lname, emailAdress);
        this.CostumerCreditCard = customerCreditCard;
        this.purchasePlan = purchasePlan;
        this.CostumerVehicle.add(CostumerVehicle);
    }


    public CreditCard getCostumerCreditCard() {
        return CostumerCreditCard;
    }

    public void setCostumerCreditCard(CreditCard costumerCreditCard) {
        CostumerCreditCard = costumerCreditCard;
    }

    public boolean isPurchasePlan() {
        return purchasePlan;
    }

    public void setPurchasePlan(boolean purchasePlan) {
        this.purchasePlan = purchasePlan;
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
