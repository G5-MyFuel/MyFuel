package client.boundary;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import sun.font.TrueTypeFont;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderConfirmationController implements Initializable {
    private OrderConfirmationController OrderConfirmationController;

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
        //TODO: return to the an empty page with the logo
    }

    @FXML
    void signOut(MouseEvent event) {
        //TODO: exit myFuel
    }

    @FXML
    void supplyFormPage(MouseEvent event) {
        //TODO: show the page OrderExecution
    }

    public void initialize(URL location, ResourceBundle resources) {
        arrowImage.setVisible(false);
        hboxOrderConfirmation.setVisible(false);
        btnDone.setVisible(false);
        btnDone.setDisable(true);
        tableView.setVisible(true);
    }




}