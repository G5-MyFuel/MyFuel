package boundary;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginPageBoundary {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userIDTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    private ChoiceBox<String> loginAsChoiceBox;

    @FXML
    void initialize() {
        assert userIDTextField != null : "fx:id=\"userIDTextField\" was not injected: check your FXML file 'loginPageInterface.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'loginPageInterface.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'loginPageInterface.fxml'.";
        assert loginAsChoiceBox != null : "fx:id=\"loginAsListView\" was not injected: check your FXML file 'loginPageInterface.fxml'.";

    }

    public ResourceBundle getResources() {
        return resources;
    }

    public URL getLocation() {
        return location;
    }

    public TextField getUserIDTextField() {
        return userIDTextField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginBtn() {
        return loginBtn;
    }

    public ChoiceBox<String> getLoginAsChoiceBox() {
        return loginAsChoiceBox;
    }
}
