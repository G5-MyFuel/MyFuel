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
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingDiscountRatesBoundary implements Initializable {

    /**
     * The supervisor boundary controller.
     */
    private final SettingDiscountRatesController myController = new SettingDiscountRatesController(this);
    private FormValidation formValidation;

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
        formValidation.numberPositiveValidation(ShowNewRateTXT, "New price");
        FormValidation.isEmptyField(ShowNewRateTXT, "New price");
        //formValidation.maxLengthValidation(ShowNewRateTXT, "New price", 3);
        formValidation.maxSizeValidation(ShowNewRateTXT, "New price", 100);
        formValidation.minSizeValidation(ShowNewRateTXT, "New price", 1);
        //לא לאפשר עדכון מחיר חדש אם הבדיקת לא תקינה!!

    }

    @FXML
    void handleChoseSubscriptionType(ActionEvent event) {

        myController.getDiscountRatesTable(ChooseSubscriptionTypeCombo.getValue()); //start the process that will ask server to execute quarry and get the table details
        //ShowCurrentRateTXT.setText(SubscriptionType);
        ShowCurrentRateTXT.setVisible(true);
        ShowCurrentRateTXT.setEditable(false);
        ShowNewRateTXT.setVisible(true);
        btnSetNewRate.setVisible(true);
    }

    public void setData(String currentRate) {
        ShowCurrentRateTXT.setText(currentRate);
    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleSetNewRate(ActionEvent event) {

        myController.setNewPriceInDB(ShowNewRateTXT.getText(), ChooseSubscriptionTypeCombo.getValue());
        //לרענן את שורת המחיר הנוכחי!!
    }

    @FXML
    void handleShowCurrentRate(ActionEvent event) {

    }

    @FXML
    void handleShowNewRate(ActionEvent event) {

    }

}