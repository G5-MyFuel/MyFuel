package boundary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * @author Adi Lampert
 * @see ManagerSupplyConfirmationBoundary - the from's logic class
 */

public class ManagerSupplyConfirmationBoundary {

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

}