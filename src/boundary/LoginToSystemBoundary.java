package boundary;

import Contollers.FormValidation;
import Contollers.LoginToSystemController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entity.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginToSystemBoundary extends Application {
    /* variables: */
    private static LoginToSystemBoundary Instance;
    private ActionEvent event = null;
    Contollers.LoginToSystemController loginToSystemLogic; //logic instance
    FormValidation formValidation;
    private ArrayList<User> allDBUsersArrayList;
    private LoginToSystemController myController = new LoginToSystemController(this);
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    int userType = 0;

    /*  fxml file object variables: */
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXComboBox<String> loginAsComboBox;

    @FXML
    private JFXTextField userIDTextField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    public Stage primaryStage;


    public static LoginToSystemBoundary getInstance() {
        if (Instance == null)
            Instance = new LoginToSystemBoundary();
        return Instance;
    }

    @FXML
    void initialize() {
        myController.getUsersTable();   //start the process that will ask server to execute query and get the Users table details
        formValidation = FormValidation.getValidator(); //for form validation instance
        loginAsComboBox.getItems().addAll("Customer", "Employee", "Supplier");  //set the user types
        LoginValidation();
    }

    //מתודה שמאתחלת את בדיקות הקלט לטופס הלוגין
    public void LoginValidation() {
        //username validation
        formValidation.isEmptyField(userIDTextField, "User ID");
        formValidation.isContainsOnlyNumbers(userIDTextField, "user ID");
        formValidation.maxLengthValidation(userIDTextField, "user ID", 9);
        //password validation
        System.out.println(allDBUsersArrayList);
        formValidation.isEmptyPasswordField(passwordField, "Password");
    }

    @FXML
    void clickLoginBtn() {
        System.out.println("-->clickLoginBtn method");
        System.out.println(allDBUsersArrayList);
        checkInputs();
    }

    public void checkInputs() {
        if (!loginAsComboBox.getSelectionModel().isEmpty()) {
            switch (loginAsComboBox.getValue()) {
                case "Customer":
                    userType = 1;
                    break;
                case "Employee":
                    userType = 2;
                    break;
                case "Supplier":
                    userType = 3;
                    break;
                default:
                    userType = 0;
                    break;
            }
        }

        //check if user type selected
        if (userType == 0) {
            ErrorAlert.setTitle("Login Error");
            ErrorAlert.setHeaderText("User type cannot be empty, Please select user type");
            ErrorAlert.showAndWait();
        }

        //check if userID exist in DB
        if (checkIfUserNameAndUserTypeInDb(userIDTextField.getText(), userType)) {
            if (checkIfPasswordMatches(userIDTextField.getText(), passwordField.getText())) {
                //todo: כאן נגדיר מה יקרה כאשר המשתמש הזין נתונים נכונים ויכול להתחבר למערכת
                System.out.println("ther userid and password matches!");
            }
        }
    }

    private boolean checkIfUserNameAndUserTypeInDb(String userID, int UserType) {
        for (User u : allDBUsersArrayList) {
            if (u.getUserID().equals(userID) && userType == u.getUserType()) {
                System.out.println("the user and its type correct");//tet
                return true;
            }
        }
        ErrorAlert.setTitle("User ID does'nt exist in the system");
        ErrorAlert.setHeaderText("User " + userIDTextField.getText() + " does not exist on the system, please try again!");
        ErrorAlert.showAndWait();
        return false;
    }

    private int loginAttempts = 3;

    private boolean checkIfPasswordMatches(String userID, String password) {
        if (loginAttempts > 0) {
            for (User u : allDBUsersArrayList) {
                if (u.getUserID().equals(userID)) {
                    if (u.getUserPassword().equals(password)) {
                        System.out.println("the userId & password matches");
                        return true;
                    } else break;
                }
            }
            ErrorAlert.setTitle("The password is incorrect! try again");
            loginAttempts--;
            ErrorAlert.setHeaderText("Login failed! There are " + loginAttempts + " login attempts left");
            ErrorAlert.showAndWait();
            return false;
        } else {
            myController.setNewUserFieldValue("loginAttempts", "0", userIDTextField.getText(), Integer.toString(userType));
            ErrorAlert.setTitle("Account blocked!");
            ErrorAlert.setHeaderText("Contact a marketing representative to unblock the user");
            ErrorAlert.showAndWait();
        }
        return false;
    }

    public static Optional<ButtonType> messageWindow(Alert.AlertType alertType, String win, String mes, String mes1) {
        Alert alert = new Alert(alertType);
        alert.setTitle(win);
        alert.setHeaderText(mes);
        alert.setContentText(mes1);
        return alert.showAndWait();
    }

    public TextField getUserIDTextField() {
        return userIDTextField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane newRoot = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/boundary/LoginToSystemFXML.fxml"));
            newRoot = loader.load();
            Scene s1 = new Scene(newRoot, 807, 600);

            this.primaryStage.setScene(s1);
            this.primaryStage.show();
        } catch (IOException e) {
            System.err.println("IOException - open LoginToSystemFXML.fxml file!!!");
            e.printStackTrace();
        }
    }

    public JFXComboBox<String> getLoginAsComboBox() {
        return loginAsComboBox;
    }

    public ArrayList<User> getAllDBUsersArrayList() {
        return allDBUsersArrayList;
    }

    public void setAllDBUsersArrayList(ArrayList<User> allDBUsersArrayList) {
        this.allDBUsersArrayList = allDBUsersArrayList;
    }

}

