package common.entity;


/**
 * @author itay
 * @see CreditCard - the form's entity class
 */


public class CreditCard {

    private Customer cardOwner;
    private String cardNumber;
    private String cardExparation;
    private String cardSecurityNumber;


    public CreditCard(Customer cardOwner, String cardNumber, String cardExparation, String cardSecurityNumber) {
        this.cardOwner = cardOwner;
        this.cardNumber = cardNumber;
        this.cardExparation = cardExparation;
        this.cardSecurityNumber = cardSecurityNumber;
    }

    public Customer getCardOwner() {
        return cardOwner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardExparation() {
        return cardExparation;
    }

    public String getCardSecurityNumber() {
        return cardSecurityNumber;
    }

    public void setCardOwner(Customer cardOwner) {
        this.cardOwner = cardOwner;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardExparation(String cardExparation) {
        this.cardExparation = cardExparation;
    }

    public void setCardSecurityNumber(String cardSecurityNumber) {
        this.cardSecurityNumber = cardSecurityNumber;
    }
}
