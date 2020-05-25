package client.boundary;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderConfirmationController implements Initializable {

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnSupplyForm;

    @FXML
    private Button btnOrderConfirmation;

    @FXML
    private Button btnSignout;

    @FXML
    private ImageView arrowImage;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private HBox hboxOrderConfirmation;

    @FXML
    private ImageView checkBoxImage;

    @FXML
    private CheckBox confirmationCheckBox;

    @FXML
    private Button btnDone;

    @FXML
    void OrderConfirmationCheck(MouseEvent event) {

    }

    @FXML
    void finalOrderConfirmation(MouseEvent event) {

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

    @FXML
    void orderConfirmationPage(MouseEvent event) {

    }

    @FXML
    void returnToHomePage(MouseEvent event) {

    }

    @FXML
    void signOut(MouseEvent event) {

    }

    @FXML
    void supplyFormPage(MouseEvent event) {

    }

    public void initialize(URL location, ResourceBundle resources) {
        arrowImage.setVisible(false);
        hboxOrderConfirmation.setVisible(false);
        btnDone.setVisible(false);
        btnDone.setDisable(true);
    }


}