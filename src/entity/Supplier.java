package entity;

import java.util.ArrayList;

public class Supplier extends User {


    public Supplier(String userID, String userType, String userPassword, int isLoginIndicator, String userFirstName, String userLastName, String userEmail, ArrayList<String> fuelCompany) {
        super(userID, userType, userPassword, isLoginIndicator, userFirstName, userLastName, userEmail, fuelCompany);
    }
}
