package boundary;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPageBoundary {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userId_txt;

    @FXML
    private PasswordField password_passField;

    @FXML
    private Button login_Btn;

    @FXML
    void initialize() {
        assert userId_txt != null : "fx:id=\"userId_txt\" was not injected: check your FXML file 'loginPageInterface.fxml'.";
        assert password_passField != null : "fx:id=\"password_passField\" was not injected: check your FXML file 'loginPageInterface.fxml'.";
        assert login_Btn != null : "fx:id=\"login_Btn\" was not injected: check your FXML file 'loginPageInterface.fxml'.";

    }

	public ResourceBundle getResources() {
		return resources;
	}

	public URL getLocation() {
		return location;
	}

	public TextField getUserId_txt() {
		return userId_txt;
	}

	public PasswordField getPassword_passField() {
		return password_passField;
	}

	public Button getLogin_Btn() {
		return login_Btn;
	}
    
    
}
