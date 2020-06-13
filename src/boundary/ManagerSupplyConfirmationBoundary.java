package boundary;

import Contollers.ManagerSupplyConfirmationController;
import Contollers.OrderFromSupplierController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Adi Lampert
 * @see ManagerSupplyConfirmationBoundary - the from's logic class
 */

public class ManagerSupplyConfirmationBoundary implements Initializable {

    private ManagerSupplyConfirmationController myController = new ManagerSupplyConfirmationController(this);


    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrderConfirmation;

    @FXML
    private Button btnSignout;

    @FXML
    private ImageView arrowImage;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> OrderCol;

    @FXML
    private TableColumn<?, ?> FuelTypeCol;

    @FXML
    private TableColumn<?, ?> AmountCol;

    @FXML
    private TableColumn<?, ?> StatusCol;

    @FXML
    private HBox hboxOrderConfirmation;

    @FXML
    private ImageView checkBoxImage;

    @FXML
    private CheckBox confirmationCheckBox;

    @FXML
    private Button SendBtn;

    @FXML
    void OrderConfirmationCheck(MouseEvent event) {

    }

    @FXML
    void SendConfirmationToSupplier(MouseEvent event) {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hboxOrderConfirmation.setVisible(false);
        tableView.setVisible(true);
        SendBtn.setVisible(false);
        SendBtn.setDisable(true);
        /*myController.getOrdersFromDB();*/
        System.out.println("Manager Supply Confirmation Page Is Open");
    }

}