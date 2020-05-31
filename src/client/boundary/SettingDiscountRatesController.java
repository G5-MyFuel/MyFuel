package client.boundary;

import client.logic.FormValidation;
import client.logic.NewPurchaseFuelForHomeHeatingLogic;
import client.logic.SettingDiscountRatesLogic;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private ObservableList<String> SubscriptionType = FXCollections.observableArrayList("Regular monthly subscription - single vehicle",
            "Full monthly subscription (for single vehicle)", "Regular monthly subscription - number of vehicles");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.settingDiscountRatesLogic = SettingDiscountRatesLogic.getInstance();
        //this.formValidation = FormValidation.getValidator();
        ChooseSubscriptionTypeCombo.setItems(SubscriptionType);
        ShowCurrentRateTXT.setVisible(false);
        ShowNewRateTXT.setVisible(false);
        btnSetNewRate.setVisible(false);
        //ChooseSubscriptionTypeCombo.set
        //this.shippingIndicatorTAB1.setVisible(false);
        /*  set all fields validators */
        //formValidation();   //
        /* set form items */
        //setShippingTab();
    }

    /**
     * NewPurchaseFuelForHomeHeatingController Instance getter using SingleTone DesignPatterns
     * @return Instance of controller class
     */
    public SettingDiscountRatesController getInstance() {
        if (Instance == null)
            Instance = new SettingDiscountRatesController();
        return Instance;
    }

    /*private void formValidation() {

        
    }*/

    @FXML
    void handleChoseSubscriptionType(ActionEvent event) {

        /*צריך להשוות את המנוי הנבחר עם המידע ב-DB ולהציג את המחיר הקיים.todo:*/
        ShowCurrentRateTXT.setVisible(true);
        ShowCurrentRateTXT.setEditable(false);
        ShowNewRateTXT.setVisible(true);
        btnSetNewRate.setVisible(true);
    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleSetNewRate(ActionEvent event) {

    }

    @FXML
    void handleShowCurrentRate(ActionEvent event) {

        /* מחזיר מה-DB את המחיר הנוכחי של התוכנית המתאימה.todo:*/

    }

    @FXML
    void handleShowNewRate(ActionEvent event) {

        /* מעדכן את ה-DB במחיר החדש של התוכנית המתאימה + בדיקת תקינות.todo:*/


    }

}