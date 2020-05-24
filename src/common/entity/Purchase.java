package common.entity;

import java.util.Date;

public class Purchase {
    String purchaseID;
    String customerID;
    Date purchaseDate;
    double fuelAmount;
    double totalPrice;

    public Purchase(String purchaseID, String customerID, Date purchaseDate, double fuelAmount) {
        this.purchaseID = purchaseID;
        this.customerID = customerID;
        this.purchaseDate = purchaseDate;
        this.fuelAmount = fuelAmount;
        this.totalPrice = 0.0;
    }


}
