package client.boundary;

import client.logic.FormValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmDiscountRatesController implements Initializable {
    private static ConfirmDiscountRatesController Instance = null;
    private client.logic.ConfirmDiscountRatesController confirmDiscountRatesLogic;
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
    private TableView<?> TableSubscriptionType;

    @FXML
    private Button btnApprovedRates;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.confirmDiscountRatesLogic = client.logic.ConfirmDiscountRatesController.getInstance();
        this.formValidation = FormValidation.getValidator();

        btnApprovedRates.setDisable(true);
        /*ChooseSubscriptionTypeCombo.setItems(SubscriptionType);
        ShowCurrentRateTXT.setVisible(false);
        ShowNewRateTXT.setVisible(false);
        btnSetNewRate.setVisible(false);
        //ChooseSubscriptionTypeCombo.set
        //this.shippingIndicatorTAB1.setVisible(false);
        /*  set all fields validators */
        formValidation();
        /* set form items */
        //setShippingTab();
    }

    /**
     * ConfirmDiscountRatesController Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of controller class
     */
    public static ConfirmDiscountRatesController getInstance() {
        if (Instance == null)
            Instance = new ConfirmDiscountRatesController();
        return Instance;
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

    @FXML
    void handleApprovedRates(ActionEvent event) {

    }

    @FXML
    void handleClicks(ActionEvent event) {

    }


}