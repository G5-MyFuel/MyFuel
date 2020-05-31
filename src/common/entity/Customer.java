package common.entity;

public class Customer {

    private String ID;
    private String Fname;
    private String Lname;
    private String emailAdress;
    private CreditCard CustomerCreditCard;

    public Customer(String ID, String fname, String lname, String emailAdress, CreditCard customerCreditCard) {
        this.ID = ID;
        Fname = fname;
        Lname = lname;
        this.emailAdress = emailAdress;
        CustomerCreditCard = customerCreditCard;
    }
}
