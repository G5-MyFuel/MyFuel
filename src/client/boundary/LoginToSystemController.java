package client.boundary;

import client.ClientApp;
import client.logic.FormValidation;
import client.logic.LoginToSystemLogic;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import common.entity.User;
import common.tools.Message;
import common.tools.OperationType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class LoginToSystemController extends Application {
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

    private static LoginToSystemController Instance;

    /*  other variables: */
    private ActionEvent event = null;
    LoginToSystemLogic loginPageController; //logic instance
    FormValidation formValidation;


    public static LoginToSystemController getInstance() {
        if (Instance == null)
            Instance = new LoginToSystemController();
        return Instance;
    }

    @FXML
    void initialize() {
        formValidation = FormValidation.getValidator();
        loginAsComboBox.getItems().addAll("Customer", "Employee", "Supplier");
        LoginValidation();
    }

    public void LoginValidation() {
        //username validation
        formValidation.isEmptyField(userIDTextField, "User ID");
        formValidation.isContainsOnlyNumbers(userIDTextField, "user ID");
        formValidation.maxLengthValidation(userIDTextField, "user ID", 9);
        //password validation
        formValidation.isEmptyPasswordField(passwordField, "Password");
    }

    @FXML
    void clickLoginBtn() {
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //מה קורה לאחר לחיצה על לוגין
                checkInputs();
            }
        });
    }

    public void checkInputs() {
        int userType = 0;
        System.out.println(loginAsComboBox.getValue().toString());
        switch (loginAsComboBox.getValue().toString()) {
            case "Customer":
                userType = 1;
                break;
            case "Employee":
                userType = 2;
                break;
            case "Supplier":
                userType = 3;
                break;
        }

        //check if userID exist in DB
        String getAllUsers = "Select * from Users";
        ClientApp.client.handleMessageFromClientUI(new Message(OperationType.getAllUsersTable, getAllUsers));
        while (true) {
            if (!LoginToSystemLogic.getInstance().getUsersArrayList().isEmpty()) {
                break;
            }
        }
        boolean result = LoginToSystemLogic.getInstance().searchUserIdAndUserTypeInArrayList(Integer.parseInt(userIDTextField.getText()), Integer.parseInt(passwordField.getText()));
        System.out.println("result of Login is:" + result);
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
            loader.setLocation(getClass().getResource("/client/boundary/LoginToSystemFXML.fxml"));
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

    public void SetUsersTable(Object object) {
        LoginToSystemLogic.getInstance().setUsersArrayList((ArrayList<User>) object);
    }
}

