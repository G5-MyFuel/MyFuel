package boundary;

import Contollers.CostumerManagementController;
import Contollers.FormValidation;
import Contollers.SettingDiscountRatesController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.assets.ReturnMsgType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingDiscountRatesBoundary implements Initializable {

    /**
     * The supervisor boundary controller.
     */
    private final SettingDiscountRatesController myController = new SettingDiscountRatesController(this);
    private FormValidation formValidation;
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSignout;

    @FXML
    private JFXComboBox<String> ChooseSubscriptionTypeCombo;

    @FXML
    private JFXTextField ShowCurrentRateTXT;

    @FXML
    private Button btnSetNewRate;

    @FXML
    private JFXTextField ShowNewRateTXT;

    private final ObservableList<String> SubscriptionType = FXCollections.observableArrayList("Regular monthly subscription - single vehicle",
            "Full monthly subscription (for single vehicle)", "Regular monthly subscription - number of vehicles");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.formValidation = FormValidation.getValidator();
        ChooseSubscriptionTypeCombo.setItems(SubscriptionType);
        ShowCurrentRateTXT.setVisible(false);
        ShowNewRateTXT.setVisible(false);
        btnSetNewRate.setVisible(false);

        /*  set all fields validators */
        formValidation();
    }

    private void formValidation() {

        /*  New price validation */

        //formValidation.isContainsOnlyNumbers(ShowNewRateTXT, "New price");
        //formValidation.numberPositiveValidation(ShowNewRateTXT, "New price");
        FormValidation.isEmptyField(ShowNewRateTXT, "New price");
        //formValidation.maxLengthValidation(ShowNewRateTXT, "New price", 3);
        formValidation.maxSizeValidation(ShowNewRateTXT, "New price", 100);
        formValidation.minSizeValidation(ShowNewRateTXT, "New price", 1);
    }

    @FXML
    void handleChoseSubscriptionType(ActionEvent event) {

        myController.getDiscountRatesTable(ChooseSubscriptionTypeCombo.getValue()); //start the process that will ask server to execute quarry and get the table details
        ShowNewRateTXT.clear();
        ShowCurrentRateTXT.setVisible(true);
        ShowCurrentRateTXT.setEditable(false);
        ShowNewRateTXT.setVisible(true);
        btnSetNewRate.setVisible(true);
    }

    public void setData(String currentRate) {
        if(currentRate.isEmpty())
             ShowCurrentRateTXT.setText(ShowNewRateTXT.getText());
        else
            ShowCurrentRateTXT.setText(currentRate);

    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleSetNewRate(ActionEvent event) {

        Float NewRate = Float.parseFloat(ShowNewRateTXT.getText());
        if (NewRate < 0 || NewRate > 100) {
            ErrorAlert.setTitle("Price rate ERROR");
            ErrorAlert.setHeaderText("Please insert between 0-100");
            ErrorAlert.showAndWait();
        } else {
            myController.setNewPriceInDB(ShowNewRateTXT.getText(), ChooseSubscriptionTypeCombo.getValue());
             //גם ככה יש שימוש בפונקציה סט דאטה. שם העדכון קורה אחרי שהשאליתה בוצעה
        }
    }

    @FXML
    void handleShowCurrentRate(ActionEvent event) {

    }

    @FXML
    void handleShowNewRate(ActionEvent event) {

    }
}