package entity;


import java.util.ArrayList;
/**
 * @see Employee - the form's entity class
 */
public class Employee extends User {
    String fuelCompanyName;
    String gasStatinID;
    public Employee(String userID, String userType, String userPassword, int isLoginIndicator, String userFirstName, String userLastName, String userEmail, ArrayList<String> fuelCompany, String fuelCompanyName, String gasStatinID) {
        super(userID, userType, userPassword, isLoginIndicator, userFirstName, userLastName, userEmail, fuelCompany);
        this.fuelCompanyName = fuelCompanyName;
        this.gasStatinID = gasStatinID;
    }

}