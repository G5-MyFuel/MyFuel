package entity;


import common.assets.enums.PricingModelTypes;
import common.assets.enums.PurchasePlanTypes;

import java.util.ArrayList;

/**
 * @author itay
 * @see Costumer - the form's entity class
 */

public class Costumer extends User {

    private CreditCard CostumerCreditCard;
    private ArrayList<Vehicle> CostumerVehicle;
    private String CostumerType;
    private String pricingModel;
    private String purchasePlan;
    private PurchasePlanTypes purchasePlanType;
    private PricingModelTypes pricingModelType;

    public Costumer(String ID, String CustomerPassword, String CostumerType, String Fname, String Lname, String emailAdress, CreditCard customerCreditCard, String purchasePlan, String pricingModel) {
        super(ID, CostumerType, CustomerPassword, 0, Fname, Lname, emailAdress, null);
        this.CostumerCreditCard = customerCreditCard;
        this.purchasePlan = purchasePlan;
        this.purchasePlanType = PurchasePlanTypes.fromString(purchasePlan);
        this.pricingModel = pricingModel;
        this.pricingModelType = PricingModelTypes.fromString(pricingModel);
        this.CostumerType = CostumerType;
        this.CostumerVehicle = new ArrayList<Vehicle>();
        //
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "CostumerCreditCard=" + CostumerCreditCard +
                ", CostumerVehicle=" + CostumerVehicle +
                ", CostumerType='" + CostumerType + '\'' +
                ", pricingModel='" + pricingModel + '\'' +
                ", purchasePlan='" + purchasePlan + '\'' +
                ", purchasePlanType=" + purchasePlanType +
                ", pricingModelType=" + pricingModelType +
                '}';
    }

    public void setPurchasePlan(String purchasePlan) {
        this.purchasePlan = purchasePlan;
    }


    public String getPurchasePlan() {
        return this.purchasePlan;
    }

    public PurchasePlanTypes getPurchasePlanAsEnum() {
        return purchasePlanType;
    }

    public PricingModelTypes getPricingModelTypeAsEnum(){
        return pricingModelType;
    }

    public CreditCard getCostumerCreditCard() {
        return CostumerCreditCard;
    }

    public void setCostumerCreditCard(CreditCard costumerCreditCard) {
        this.CostumerCreditCard = costumerCreditCard;
    }


    public String getPricingModel() {
        return pricingModel;
    }

    public void setPricingModel(String pricingModel) {
        this.pricingModel = pricingModel;
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
