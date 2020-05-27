package client.boundary;

import client.logic.FormValidation;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderExecutionController {
    private OrderExecutionController OrderExecutionController;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnSignout;

    @FXML
    private ImageView arrowImage;

    @FXML
    private Text OrderViewTitle;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private VBox vboxOrderView;

    @FXML
    private JFXTextField StationManagerField;

    @FXML
    private JFXTextField StationNumberField;

    @FXML
    private JFXTextField OrderDateField;

    @FXML
    private JFXTextField FuelTypeField;

    @FXML
    private JFXTextField QuantityField;

    @FXML
    private Button DoneBtn;

    @FXML
    private HBox hboxOrderConfirmation;

    @FXML
    private ImageView checkBoxImage;

    @FXML
    private CheckBox confirmationCheckBox;

    @FXML
    void OrderConfirmationCheck(MouseEvent event) {

    }

    @FXML
    void handleBookDeleteOption(ActionEvent event) {

    }

    @FXML
    void handleBookEditOption(ActionEvent event) {

    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

    public void initialize(URL location, ResourceBundle resources) {
        arrowImage.setVisible(false);
        tableView.setVisible(true);
        DoneBtn.setVisible(false);
        DoneBtn.setDisable(true);
    }

    private void ShowOrderDetailsView () {
        //TODO: show details from table
    }
}