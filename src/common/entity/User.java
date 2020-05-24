package common.entity;

public class User {
    private String userID;
    private String userPassword;
    private boolean isLoginIndicator;
    private String userFirstName;
    private String userLastName;
    private String userEmail;

    public User(String userID, String userPassword, String userFirstName, String userLastName, String userEmail) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.isLoginIndicator = false;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isLoginIndicator() {
        return isLoginIndicator;
    }

    public void setLoginIndicator(boolean loginIndicator) {
        isLoginIndicator = loginIndicator;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
