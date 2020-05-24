package common.entity;


import java.util.Date;

public class PurchaseFuelForHomeHeating extends Purchase {
    String emailForInvoice;
    String phoneNumberForContact;
    String noteForPurchase;


    public PurchaseFuelForHomeHeating(String purchaseID, String customerID, Date purchaseDate, double fuelAmount) {
        super(purchaseID, customerID, purchaseDate, fuelAmount);
    }


}
