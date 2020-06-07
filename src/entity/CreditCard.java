package entity;


/**
 * @author itay
 * @see CreditCard - the form's entity class
 */


public class CreditCard {

    private Costumer cardOwner;
    private String cardNumber;
    private String ExperationDate;
    private String cardSecurityNumber;


    public CreditCard(Costumer cardOwner, String cardNumber, String cardExparation, String cardSecurityNumber) {
        this.cardOwner = cardOwner;
        this.cardNumber = cardNumber;
        this.ExperationDate = cardExparation;
        this.cardSecurityNumber = cardSecurityNumber;
    }

    public CreditCard() {

    }

    public Costumer getCardOwner() {
        return cardOwner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExperationDate() {
        return ExperationDate;
    }

    public String getCardSecurityNumber() {
        return cardSecurityNumber;
    }

    public void setCardOwner(Costumer cardOwner) {
        this.cardOwner = cardOwner;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExperationDate(String experationDate) {
        this.ExperationDate = experationDate;
    }

    public void setCardSecurityNumber(String cardSecurityNumber) {
        this.cardSecurityNumber = cardSecurityNumber;
    }
}
