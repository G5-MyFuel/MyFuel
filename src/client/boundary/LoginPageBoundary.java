package client.boundary;

import client.logic.LoginPageLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginPageBoundary {
    /*  fxml file object variables: */
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> loginAsChoiceBox;

    @FXML
    private TextField userIDTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginBtn;

    /* other gui variables:  */
    private ObservableList<String> userTypes = FXCollections.observableArrayList("Client", "Worker", "Supplier");

    /*  other variables: */
    private ActionEvent event = null;
    LoginPageLogic loginPageController;

    public LoginPageBoundary() {
    }

    @FXML
    void initialize() {
        assert userIDTextField != null : "fx:id=\"userIDTextField\" was not injected: check your FXML file 'loginPageInterface.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'loginPageInterface.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'loginPageInterface.fxml'.";
        assert loginAsChoiceBox != null : "fx:id=\"loginAsListView\" was not injected: check your FXML file 'loginPageInterface.fxml'.";
        this.loginPageController = new LoginPageLogic(); //set connection to the controller
        loginAsChoiceBox.setItems(userTypes);   //set the user types in the 'loginAsChoiceBox'
        clickLoginBtn();    //set the operations that will happen when the "Login" btn pressed
    }


    @FXML
    void clickLoginBtn() {
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //for tests
                System.out.println("LoginBtn Clicked");
                System.out.println("The 'Login As' choice is: " + loginAsChoiceBox.getValue());
                System.out.println("UserId field is: " + userIDTextField.getText());
                System.out.println("The password is:" + passwordField.getText());
                //
                checkInputs();
            }
        });
    }

    public void checkInputs() {
        int flag = loginPageController.checkIfThereAreEmptyFields(this);
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
        } else if (!loginPageController.checkUserIdValidation(this)) {
            //יוצג alertWindow שיגיד שהרכב שם המשתמש שגוי
        } else if (!loginPageController.checkIfUserExistInDB()) {
            //יוצג חלון שיגיד שהמשתמש לא קיים במערכת
        } else if (!loginPageController.checkIfPasswordCorrect()) {
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

    public ChoiceBox<String> getLoginAsChoiceBox() {
        return loginAsChoiceBox;
    }

    public TextField getUserIDTextField() {
        return userIDTextField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }
}
