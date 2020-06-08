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
    private SettingDiscountRatesController myController = new SettingDiscountRatesController(this);
    private FormValidation formValidation;

    /*private static SettingDiscountRatesBoundary Instance = null;
    private SettingDiscountRatesController settingDiscountRatesLogic;
    private FormValidation formValidation;*/

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
        //this.settingDiscountRatesLogic = SettingDiscountRatesController.getInstance();
        this.formValidation = FormValidation.getValidator();
        ChooseSubscriptionTypeCombo.setItems(SubscriptionType);
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
     * SettingDiscountRatesController Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of controller class
     */
    /*public static SettingDiscountRatesBoundary getInstance() {
        if (Instance == null)
            Instance = new SettingDiscountRatesBoundary();
        return Instance;
    }*/
    private void formValidation() {

        /*  New price validation */

        //formValidation.isContainsOnlyNumbers(ShowNewRateTXT, "New price");
        formValidation.numberPositiveValidation(ShowNewRateTXT, "New price");
        formValidation.isEmptyField(ShowNewRateTXT, "New price");
        //formValidation.maxLengthValidation(ShowNewRateTXT, "New price", 3);
        formValidation.maxSizeValidation(ShowNewRateTXT, "New price", 100);
        formValidation.minSizeValidation(ShowNewRateTXT, "New price", 1);

    }

    @FXML
    void handleChoseSubscriptionType(ActionEvent event) {

        /*צריך להשוות את המנוי הנבחר עם המידע ב-DB ולהציג את המחיר הקיים.todo:*/

        ShowCurrentRateTXT.setText("Check");
        //String SubscriptionType = "SELECT * FROM `bpsdc8o22sikrlpvvxqm`.`DiscountRates` WHERE `Subscription type` LIKE \"" + ChooseSubscriptionTypeCombo.getValue() + "\";";
        myController.getDiscountRatesTable(); //start the process that will ask server to execute quarry and get the table details
        //SettingDiscountRatesLogic settingDiscountRatesLogic = new SettingDiscountRatesLogic();
        //System.out.println(SubscriptionType);
        //settingDiscountRatesLogic.getDiscountRatesTable(SubscriptionType);
        //ClientApp.client.handleMessageFromClientUI(new Message(OperationType.getRequirementData, (Object)SubscriptionType));  //send the new employee jobTitle to DB

        //ShowCurrentRateTXT.setText(SubscriptionType);
        ShowCurrentRateTXT.setVisible(true);
        ShowCurrentRateTXT.setEditable(false);
        ShowNewRateTXT.setVisible(true);
        btnSetNewRate.setVisible(true);
    }

    public void setData(float currentRate) {

        Float current = currentRate;
        /*ReturnMsgType currentPrice = (ReturnMsgType) object;
        System.out.println(ReturnMsgType.values());*/
        //System.out.println(currentPrice);
        ShowCurrentRateTXT.setText(current.toString());

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