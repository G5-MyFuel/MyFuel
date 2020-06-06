package client.boundary;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CostumerManagmentTablePageController implements Initializable {

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnCustomers1;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnSignout;

    @FXML
    private JFXTextField CostumerIDtxt;

    @FXML
    private Button FinishButton;

    @FXML
    private Pane VehicleInformationPane;

    @FXML
    private Button saveVehicleButton;

    @FXML
    private JFXTextField VehicleIDtxt;

    @FXML
    private JFXComboBox<?> GasTypeChoiseBox;

    @FXML
    private Button FinishButton1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void ClickFinishButton(MouseEvent event) {

    }

    @FXML
    void ClickSaveVehicleButton(MouseEvent event) {

    }

    @FXML
    void handleClicks(ActionEvent event) {

    }
}
