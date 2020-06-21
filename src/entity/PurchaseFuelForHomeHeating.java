package entity;


import Contollers.NewPurchaseFuelForHomeHeatingController;
import common.assets.enums.ShippingMethod;
import javafx.scene.text.Text;
import org.joda.time.LocalDateTime;

/**
 * @author Daniel
 * @see PurchaseFuelForHomeHeating - the from's Entity class
 */

public class PurchaseFuelForHomeHeating extends Purchase {
    private String emailForInvoice;
    private Address addressForShipping;
    private String phoneNumberForContact;
    private String noteForPurchase;
    private OrderDeliveryStatus deliveryStatus;
    private ShippingMethod shippingMethod;
    private String ShippingDateAndTime;
    private Prices priceOfOrder;

    /**
     * constructor
     */
    public PurchaseFuelForHomeHeating(String customerID, LocalDateTime purchaseDate, double fuelAmount, String emailForInvoice, String phoneNumberForContact, String noteForPurchase, OrderDeliveryStatus deliveryStatus, ShippingMethod shippingMethod, String shippingDateAndTime) {
        super(customerID, purchaseDate, fuelAmount);
        this.emailForInvoice = emailForInvoice;
        this.phoneNumberForContact = phoneNumberForContact;
        this.noteForPurchase = noteForPurchase;
        this.deliveryStatus = deliveryStatus;
        this.shippingMethod = shippingMethod;
        ShippingDateAndTime = shippingDateAndTime;
    }

    public PurchaseFuelForHomeHeating(String customerID, LocalDateTime purchaseDate, double fuelAmount) {
        super(customerID,purchaseDate,fuelAmount);
    }

    /**
     * Getters and setters
     * @return
     */
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

    public OrderDeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(OrderDeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getShippingDateAndTime() {
        return ShippingDateAndTime;
    }

    public void setShippingDateAndTime(String shippingDateAndTime) {
        ShippingDateAndTime = shippingDateAndTime;
    }

    public Address getAddressForShipping() {
        return addressForShipping;
    }

    public void setAddressForShipping(Address addressForShipping) {
        this.addressForShipping = addressForShipping;
    }

    public Prices getPriceOfOrder() {
        return priceOfOrder;
    }

    public void setPriceOfOrder(Prices priceOfOrder) {
        this.priceOfOrder = priceOfOrder;
    }

    public static class Address {
        String streetName;
        String ApartmentNumberTXT;
        String city;
        String ZipCode;

        /**
         *
         * constructor
         */
        public Address(String streetName, String apartmentNumberTXT, String city, String zipCode) {
            this.streetName = streetName;
            ApartmentNumberTXT = apartmentNumberTXT;
            this.city = city;
            ZipCode = zipCode;
        }

        /**
         * Getters and setters
         * @return
         */
        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public String getApartmentNumberTXT() {
            return ApartmentNumberTXT;
        }

        public void setApartmentNumberTXT(String apartmentNumberTXT) {
            ApartmentNumberTXT = apartmentNumberTXT;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipCode() {
            return ZipCode;
        }

        public void setZipCode(String zipCode) {
            ZipCode = zipCode;
        }

    }
}
