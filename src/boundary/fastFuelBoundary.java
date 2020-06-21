package boundary;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class fastFuelBoundary implements DataInitializable {

    @FXML
    private Button getVehicleButoon;

    @FXML
    private Label pricaeCounter;

    @FXML
    private Label literCounter;

    @FXML
    private JFXTextField literAmountTxt;

    @FXML
    private JFXRadioButton gasolinRbtn;

    @FXML
    private JFXRadioButton diselRbtn;

    @FXML
    private JFXRadioButton scooterRbtn;

    @FXML
    private JFXRadioButton pump1;

    @FXML
    private JFXRadioButton pump2;

    @FXML
    private JFXRadioButton pump3;

    @FXML
    private Text subscriptionInfo;

    @FXML
    private Label vehicleInfoLabel;


    @Override
    public void initData(Object data) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
