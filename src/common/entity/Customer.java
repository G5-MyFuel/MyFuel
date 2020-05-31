package common.entity;


/**
 * @author itay
 * @see Customer - the form's entity class
 */

public class Customer extends User {

    private CreditCard CustomerCreditCard;

    public Customer(int ID, String CustomerPassword, String Fname, String Lname, String emailAdress, CreditCard customerCreditCard) {
        super(ID, 1, CustomerPassword, Fname, Lname, emailAdress);
        this.CustomerCreditCard = customerCreditCard;
    }

    public CreditCard getCustomerCreditCard() {
        return CustomerCreditCard;
    }

    public void setCustomerCreditCard(CreditCard customerCreditCard) {
        CustomerCreditCard = customerCreditCard;
    }
}
