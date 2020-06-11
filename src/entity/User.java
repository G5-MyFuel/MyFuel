package entity;

import java.io.Serializable;

public class User implements Serializable {
    private String userID;
    private int userType;
    private String userPassword;
    private int isLoginIndicator;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private int loginAttempts;

    public User(String userID, int userType, String userFirstName, String userLastName, String userEmail) {
        this.userID = userID;
        this.userType = userType;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = this.userID; //the default password is the userID
    }

    public User(String userID, int userType, String userPassword, String userFirstName, String userLastName, String userEmail,int loginAttempts) {
        this.userID = userID;
        this.userType = userType;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.loginAttempts = loginAttempts;
    }


    public String getUserID() {
        return userID;
    }

    public int getUserType() {
        return userType;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public int getIsLoginIndicator() {
        return isLoginIndicator;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setIsLoginIndicator(int isLoginIndicator) {
        this.isLoginIndicator = isLoginIndicator;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userType=" + userType +
                ", userPassword='" + userPassword + '\'' +
                ", isLoginIndicator=" + isLoginIndicator +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", loginAttempts=" + loginAttempts +
                '}';
    }
}
