package common.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int userID;
    private int userType;
    private String userPassword;
    private int isLoginIndicator;
    private String userFirstName;
    private String userLastName;
    private String userEmail;

    public User(int userID, int userType, String userFirstName, String userLastName, String userEmail) {
        this.userID = userID;
        this.userType = userType;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = Integer.toString(userID);
    }

    public User(int userID, int userType, String userPassword, String userFirstName, String userLastName, String userEmail) {
        this.userID = userID;
        this.userType = userType;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
    }

    public User() {

    }

    public int getUserID() {
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
                '}';
    }
}
