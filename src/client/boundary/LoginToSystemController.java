package client.boundary;

import client.logic.FormValidation;
import client.logic.LoginPageLogic;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
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
    LoginPageLogic loginPageController; //logic instance
    FormValidation formValidation;


    public static LoginToSystemController getInstance(){
        if(Instance==null)
            Instance = new LoginToSystemController();
        return Instance;
    }

    @FXML
    void initialize() {
        formValidation = FormValidation.getValidator();
        //this.loginPageController = new LoginPageLogic(); //set connection to the controller
        // clickLoginBtn();    //set the operations that will happen when the "Login" btn pressed
        loginAsComboBox.getItems().addAll("Customer","Employee","Supplier");
        LoginValidation();
    }

    public void LoginValidation(){
        
    }

    @FXML
    void clickLoginBtn() {
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checkInputs();
            }
        });
    }

    public void checkInputs() {
        int flag = loginPageController.getInstance().checkIfThereAreEmptyFields(this);
        if (flag > 0) {
            switch (flag) {
                case 1: //"login as" field is empty
                    messageWindow(Alert.AlertType.ERROR, "Input Error!", "already connected", "");
                    break;
                case 2: //"userID field is empty
                    messageWindow(Alert.AlertType.ERROR, "Input 2 Error!", "already connected", "");
                    break;
                case 3: //"password field is empty
                    messageWindow(Alert.AlertType.ERROR, "Input 3 Error!", "already connected", "");
                    break;
            }
        } else if (!loginPageController.getInstance().checkUserIdValidation(this)) {
            //יוצג alertWindow שיגיד שהרכב שם המשתמש שגוי
        } else if (!loginPageController.getInstance().checkIfUserExistInDB()) {
            //יוצג חלון שיגיד שהמשתמש לא קיים במערכת
        } else if (!loginPageController.getInstance().checkIfPasswordCorrect()) {
            //יוצג חלון שהסיסמה לא תקינה
        }
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
            Scene s1 = new Scene(newRoot,807,600);

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
}

