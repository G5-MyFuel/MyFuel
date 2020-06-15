package entity;


import java.util.ArrayList;

/**
 * @author itay
 * @see Costumer - the form's entity class
 */

public class Costumer extends User {

    private CreditCard CostumerCreditCard;
    private ArrayList<Vehicle> CostumerVehicle = new ArrayList<Vehicle>();
    private String CostumerType;
    private String servicePlan;
    private String purchasePlan;

    public Costumer(String ID, String CustomerPassword, String CostumerType, String Fname, String Lname, String emailAdress, CreditCard customerCreditCard, String purchasePlan, Vehicle CostumerVehicle,String servicePlan) {
        super(ID,CostumerType,CustomerPassword,0,Fname,Lname,emailAdress,null);
        this.CostumerCreditCard = customerCreditCard;
        this.purchasePlan = purchasePlan;
        this.CostumerVehicle.add(CostumerVehicle);
        this.servicePlan = servicePlan;
        this.CostumerType = CostumerType;
        //
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

    public String getCostumerType() {
        return CostumerType;
    }

    public void setCostumerType(String costumerType) {
        CostumerType = costumerType;
    }


}
