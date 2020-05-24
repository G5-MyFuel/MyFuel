package common.entity;


import java.util.Date;

public class PurchaseFuelForHomeHeating extends Purchase {
    private String emailForInvoice;
    private String phoneNumberForContact;
    private String noteForPurchase;
    private PurchaseDeliveryStatus deliveryStatus;

    public PurchaseFuelForHomeHeating(String purchaseID, String customerID, Date purchaseDate, double fuelAmount, String emailForInvoice, String phoneNumberForContact, String noteForPurchase) {
        super(purchaseID, customerID, purchaseDate, fuelAmount);
        this.emailForInvoice = emailForInvoice;
        this.phoneNumberForContact = phoneNumberForContact;
        this.noteForPurchase = noteForPurchase;
        this.deliveryStatus = PurchaseDeliveryStatus.confirmedOrder;
    }

    public String getEmailForInvoice() {
        return emailForInvoice;
    }

    public void setEmailForInvoice(String emailForInvoice) {
        this.emailForInvoice = emailForInvoice;
    }

    public String getPhoneNumberForContact() {
        return phoneNumberForContact;
    }

    public void setPhoneNumberForContact(String phoneNumberForContact) {
        this.phoneNumberForContact = phoneNumberForContact;
    }

    public String getNoteForPurchase() {
        return noteForPurchase;
    }

    public void setNoteForPurchase(String noteForPurchase) {
        this.noteForPurchase = noteForPurchase;
    }

    public PurchaseDeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(PurchaseDeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
