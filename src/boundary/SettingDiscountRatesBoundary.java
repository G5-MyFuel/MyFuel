package boundary;

import Contollers.FormValidation;
import Contollers.SettingDiscountRatesController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SettingDiscountRatesBoundary implements Initializable {

    /**
     * The supervisor boundary controller.
     */
    private final SettingDiscountRatesController myController = new SettingDiscountRatesController(this);
    private FormValidation formValidation;
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private JFXComboBox<String> ChooseSubscriptionTypeCombo;

    @FXML
    private JFXTextField ShowCurrentRateTXT;

    @FXML
    private Button btnSetNewRate;

    @FXML
    private JFXTextField ShowNewRateTXT;

    @FXML
    private Label RequestSentMessageLabel;

    private final ObservableList<String> SubscriptionType = FXCollections.observableArrayList("Regular monthly subscription - single vehicle",
            "Full monthly subscription (for single vehicle)", "Regular monthly subscription - number of vehicles");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.formValidation = new FormValidation();
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
        formValidation.isEmptyFieldValidation(ShowNewRateTXT, "New price");
        //formValidation.maxLengthValidation(ShowNewRateTXT, "New price", 3);
        formValidation.maxFloatSizeValidation(ShowNewRateTXT, "New price", 100);
        formValidation.minFloatSizeValidation(ShowNewRateTXT, "New price", 0);
    }

    @FXML
    void handleChoseSubscriptionType(ActionEvent event) {

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add(ChooseSubscriptionTypeCombo.getValue());
        myController.getDiscountRatesTable(paramArray); //start the process that will ask server to execute quarry and get the table details
        ShowNewRateTXT.clear();
        ShowCurrentRateTXT.setVisible(true);
        ShowCurrentRateTXT.setEditable(false);
        ShowNewRateTXT.setVisible(true);
        btnSetNewRate.setVisible(true);
        RequestSentMessageLabel.setVisible(false);
    }

    public void setData(String currentRate) {
        ShowCurrentRateTXT.setText(currentRate + "%");
    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleSetNewRate(ActionEvent event) {

        RequestSentMessageLabel.setVisible(false);
        Float NewRate = Float.parseFloat(ShowNewRateTXT.getText());
        if (NewRate < 0 || NewRate > 100) {
            ErrorAlert.setTitle("Price rate ERROR");
            ErrorAlert.setHeaderText("Please insert between 0-100");
            ErrorAlert.showAndWait();
        } else {
            ArrayList<String> paramArray = new ArrayList<>();
            paramArray.add("Insert NewRate");
            paramArray.add(ShowNewRateTXT.getText());
            paramArray.add(ChooseSubscriptionTypeCombo.getValue());
            System.out.println(paramArray);
            myController.getDiscountRatesTable(paramArray); //start the process that will ask server to execute quarry and get the table details
            RequestSentMessageLabel.setVisible(true);
        }
    }

    @FXML
    void handleShowCurrentRate(ActionEvent event) {

    }

    @FXML
    void handleShowNewRate(ActionEvent event) {

    }
}