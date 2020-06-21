package boundary;

import Contollers.FormValidation;
import Contollers.LoginToSystemController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import common.assets.PermissionsManagement;
import common.assets.ProjectPages;
import entity.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * login to system Boundary class- Handle user connection to system - gui
 * @author Daniel Gabbay
 */
public class LoginToSystemBoundary extends Application {
    /* variables: */
    private static LoginToSystemBoundary Instance;
    private PermissionsManagement permissionsManagement = new PermissionsManagement();
    private ActionEvent event = null;
    Contollers.LoginToSystemController loginToSystemLogic; //logic instance
    FormValidation formValidation;
    private ArrayList<User> allDBUsersArrayList;
    private LoginToSystemController myController = new LoginToSystemController(this);
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    String userType = "";

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

    @FXML
    private ImageView imageView;


    public static LoginToSystemBoundary getInstance() {
        if (Instance == null)
            Instance = new LoginToSystemBoundary();
        return Instance;
    }

    /**
     *
     */
    @FXML
    void initialize() {
        myController.getUsersTable();   //start the process that will ask server to execute query and get the Users table details
        formValidation = FormValidation.getValidator(); //for form validation instance
        loginAsComboBox.getItems().addAll("Customer", "Employee", "Supplier");  //set the user types
        LoginValidation();
        Image img = new Image("/media/loginBeautiful.png");
        imageView.setImage(img);
    }

    public void LoginValidation() {
        //username validation
        formValidation.isEmptyFieldValidation(userIDTextField, "User ID");
        formValidation.isOnlyNumbers(userIDTextField, "user ID");
        formValidation.maxLengthValidation(userIDTextField, "user ID", 9);
        //password validation
        System.out.println(allDBUsersArrayList);
        formValidation.isEmptyPasswordField(passwordField, "Password");
    }

    @FXML
    void clickLoginBtn() {
        System.out.println("-->clickLoginBtn method");
        //System.out.println(allDBUsersArrayList);
        if (checkInputs()) {    //if the user exist in the db
            ArrayList<String> allButtons = myController.getUserButtons(this, userType);
            mainProjectFX.pagingController.loadBoundary(ProjectPages.GENERAL_DASH_BOARD.getPath(), allButtons);
        }
    }

    public boolean checkInputs() {
        try {
            if (!loginAsComboBox.getSelectionModel().isEmpty()) {
                userType = loginAsComboBox.getValue().toUpperCase();
            }

            //check if user type selected
            else {
                ErrorAlert.setTitle("Login Error");
                ErrorAlert.setHeaderText("User type cannot be empty, Please select user type");
                ErrorAlert.showAndWait();
            }
            //check if userID exist in DB
            if (checkIfUserNameAndUserTypeInDb(userIDTextField.getText(), loginAsComboBox.getValue().toUpperCase())) {
                if (checkIfPasswordMatches(userIDTextField.getText(), passwordField.getText())) {
                    System.out.println("ther userid and password matches!");
                    return true;
                }
            }
            return false;
        }catch (RuntimeException l){
            return false;
        }
    }

    private boolean checkIfUserNameAndUserTypeInDb(String userID, String UserType) {
        for (User u : allDBUsersArrayList) {
            if (u.getUserID().equals(userID)) {
                if (u.getUserType().equals("CUSTOMER") && userType.equals("CUSTOMER")) {
                    return true;
                } else if (u.getUserType().equals("SUPPLIER") && userType.equals("SUPPLIER")) {
                    return true;
                } else if (userType.equals("EMPLOYEE")) {
                    if (u.getUserType().equals("COMPANY_MANAGER") || u.getUserType().equals("MARKETING_DEPARTMENT_WORKER") || u.getUserType().equals("MARKETING_MANAGER") || u.getUserType().equals("MARKETING_REPRESENTATIVE") || u.getUserType().equals("STATION_MANAGER")) {
                        userType = u.getUserType();
                        return true;
                    } else break;
                } else {
                    break;
                }
            }
        }

        ErrorAlert.setTitle("User ID does'nt exist in the system");
        ErrorAlert.setHeaderText("User " + userIDTextField.getText() + " does not exist on the system, please try again!");
        ErrorAlert.showAndWait();
        return false;
    }

    private boolean checkIfPasswordMatches(String userID, String password) {
        for (User u : allDBUsersArrayList) {
            if (u.getUserID().equals(userID)) {
                if (u.getUserPassword().equals(password)) {
                    System.out.println("the userId & password matches");
                    return true;
                } else {
                        ErrorAlert.setTitle("Login Error");
                        ErrorAlert.setHeaderText("User ID and password do not match");
                        ErrorAlert.showAndWait();
                    return false;
                }
            }
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

