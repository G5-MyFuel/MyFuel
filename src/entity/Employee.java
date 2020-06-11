package entity;


public class Employee extends User {
    private String jobTitle;
    private String fuelCompany;

    public Employee(String userID, String userPassword, String userFirstName, String userLastName, String userEmail,String jobTitle,String fuelCompany) {
        super(userID, 2, userPassword, userFirstName, userLastName, userEmail);
        this.jobTitle = jobTitle;
        this.fuelCompany = fuelCompany;
    }


}
