package boundary;

import Contollers.ConfirmDiscountRatesController;
import Contollers.FormValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmDiscountRatesBoundary implements Initializable {

    /**
     * The supervisor boundary controller.
     */
    private final ConfirmDiscountRatesController myController = new ConfirmDiscountRatesController(this);
    private FormValidation formValidation;
    private final Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private TableView<?> TableSubscriptionType;

    @FXML
    private Button btnApprovedRates;

    @FXML
    private Button btnRemoveNewRate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.formValidation = FormValidation.getValidator();

        btnApprovedRates.setDisable(true);
        /*ChooseSubscriptionTypeCombo.setItems(SubscriptionType);
        ShowCurrentRateTXT.setVisible(false);

        /*  set all fields validators */
        formValidation();
    }

    private void formValidation() {

        /*  New price validation */
/*
        //formValidation.isContainsOnlyNumbers(ShowNewRateTXT, "New price");
        formValidation.numberPositiveValidation(ShowNewRateTXT, "New price");
        formValidation.isEmptyField(ShowNewRateTXT, "New price");
        //formValidation.maxLengthValidation(ShowNewRateTXT, "New price", 3);
        formValidation.maxSizeValidation(ShowNewRateTXT, "New price", 100);
        formValidation.minSizeValidation(ShowNewRateTXT, "New price", 1);
        */

    }

    public void setDiscountRatesData(String revenue) {


    }

    @FXML
    void handleApprovedRates(ActionEvent event) {

    }

    @FXML
    void handleRemoveNewRate(ActionEvent event) {

    }

}