package client.boundary;

import client.logic.FormValidation;
import client.logic.NewPurchaseFuelForHomeHeatingLogic;
import client.logic.SettingDiscountRatesLogic;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingDiscountRatesController implements Initializable {
    private static SettingDiscountRatesController Instance = null;
    private SettingDiscountRatesLogic settingDiscountRatesLogic;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.settingDiscountRatesLogic = SettingDiscountRatesLogic.getInstance();
        //this.formValidation = FormValidation.getValidator();
        ShowCurrentRateTXT.setVisible(false);
        ShowNewRateTXT.setVisible(false);
        //ChooseSubscriptionTypeCombo.set
        //this.shippingIndicatorTAB1.setVisible(false);
        /*  set all fields validators */
        //formValidation();   //
        /* set form items */
        //setShippingTab();
    }

    /*private void formValidation() {

        
    }*/

    @FXML
    void handleChoseSubscriptionType(ActionEvent event) {

    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleSetNewRate(ActionEvent event) {

    }

    @FXML
    void handleShowCurrentRate(ActionEvent event) {

    }

    @FXML
    void handleShowNewRate(ActionEvent event) {

    }

}