package entity;


import common.assets.UserType;

public class Employee extends User {
    private String jobTitle;
    private String fuelCompany;

    public Employee(String userID, String userPassword, String userFirstName, String userLastName, String userEmail,String jobTitle,String fuelCompany) {
        super(userID, 2, userPassword, userFirstName, userLastName, userEmail);
        this.jobTitle = jobTitle;
        this.fuelCompany = fuelCompany;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFuelCompany() {
        return fuelCompany;
    }

    public void setFuelCompany(String fuelCompany) {
        this.fuelCompany = fuelCompany;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "jobTitle='" + jobTitle + '\'' +
                ", fuelCompany='" + fuelCompany + '\'' +
                '}';
    }
}
