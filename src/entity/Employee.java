package entity;


public class Employee extends User {


    public Employee(int userID, String userPassword, String userFirstName, String userLastName, String userEmail) {
        super(userID, 2, userPassword, userFirstName, userLastName, userEmail);
    }


}