package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * user in the system entity
 * @author itay ziv
 */
public class User implements Serializable {
    private String userID;
    private String userType;
    private String userPassword;
    private int isLoginIndicator;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private ArrayList<String> fuelCompany;

    /**
     * constructor with default password
     * @param userID
     * @param userType
     * @param isLoginIndicator
     * @param userFirstName
     * @param userLastName
     * @param userEmail
     * @param fuelCompany
     */
    public User(String userID, String userType, int isLoginIndicator, String userFirstName, String userLastName, String userEmail, ArrayList<String> fuelCompany) {
        this.userID = userID;
        this.userType = userType;
        this.isLoginIndicator = isLoginIndicator;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = this.userID; //the default password is the userID
        this.fuelCompany = fuelCompany;

    }

    /**
     * constructor
     * @param userID
     * @param userType
     * @param userPassword
     * @param isLoginIndicator
     * @param userFirstName
     * @param userLastName
     * @param userEmail
     * @param fuelCompany
     */
    public User(String userID, String userType, String userPassword, int isLoginIndicator, String userFirstName, String userLastName, String userEmail, ArrayList<String> fuelCompany) {
        this.userID = userID;
        this.userType = userType;
        this.userPassword = userPassword;
        this.isLoginIndicator = isLoginIndicator;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.fuelCompany = fuelCompany;
    }

    /**
     * get User ID userID
     * @return userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * get User Type
     * @return userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * get User Password
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     *  get Is Login Indicator
     * @return isLoginIndicator
     */
    public int getIsLoginIndicator() {
        return isLoginIndicator;
    }

    /**
     * get User First Name
     * @return userFirstName
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * get User Last Name
     * @return userLastName
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * get User Email
     * @return userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     *  set User ID
     * @param userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * set User Type
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * setUserPassword
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * set IsLoginIndicator
     * @param isLoginIndicator
     */
    public void setIsLoginIndicator(int isLoginIndicator) {
        this.isLoginIndicator = isLoginIndicator;
    }

    /**
     * set User FirstName
     * @param userFirstName
     */
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    /**
     * set User LastName
     * @param userLastName
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     * set User Email
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * get FuelCompany
     * @return fuelCompany
     */
    public ArrayList<String> getFuelCompany() {
        return fuelCompany;
    }

    /**
     * set FuelCompany
     * @param fuelCompany
     */
    public void setFuelCompany(ArrayList<String> fuelCompany) {
        this.fuelCompany = fuelCompany;
    }

    /**
     * to string method
     * @return string of user details
     */
    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", userType='" + userType + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", isLoginIndicator=" + isLoginIndicator +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

    /**
     * @param o object
     * @return boolean parameter - if user are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userType == user.userType &&
                isLoginIndicator == user.isLoginIndicator &&
                userID.equals(user.userID) &&
                userPassword.equals(user.userPassword) &&
                Objects.equals(userFirstName, user.userFirstName) &&
                Objects.equals(userLastName, user.userLastName) &&
                Objects.equals(userEmail, user.userEmail);
    }

    /**
     * hashCode function
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(userID, userType, userPassword, isLoginIndicator, userFirstName, userLastName, userEmail);
    }
}
