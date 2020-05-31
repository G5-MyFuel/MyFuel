package common.entity;


/**
 * @author itay
 * @see Customer - the form's entity class
 */

public class Customer extends User {

    private CreditCard CustomerCreditCard;
    private boolean purchasePlan;

    public Customer(int ID,String CustomerPassword, String Fname, String Lname, String emailAdress, CreditCard customerCreditCard,boolean purchasePlan) {
        super(ID,1, CustomerPassword, Fname, Lname, emailAdress);
        this.CustomerCreditCard = customerCreditCard;
        this.purchasePlan = purchasePlan;
    }

    public CreditCard getCustomerCreditCard() {
        return CustomerCreditCard;
    }

    public void setCustomerCreditCard(CreditCard customerCreditCard) {
        CustomerCreditCard = customerCreditCard;
    }

    public boolean isPurchasePlan() {
        return purchasePlan;
    }

    public void setPurchasePlan(boolean purchasePlan) {
        this.purchasePlan = purchasePlan;
    }
}
