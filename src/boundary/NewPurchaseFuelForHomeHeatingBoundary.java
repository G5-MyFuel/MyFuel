package boundary;

import Contollers.FormValidation;
import Contollers.NewPurchaseFuelForHomeHeatingController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * @author daniel
 * @see NewPurchaseFuelForHomeHeatingController - the form's logic class
 */
public class NewPurchaseFuelForHomeHeatingBoundary implements Initializable {
    private NewPurchaseFuelForHomeHeatingController myController = new NewPurchaseFuelForHomeHeatingController(this);


    private static NewPurchaseFuelForHomeHeatingBoundary Instance = null;
    private FormValidation formValidation;

    //gui variables:
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSignout;

    @FXML
    private JFXTextField anotherContactPhoneNumberTXT;

    @FXML
    private ImageView WrongQuantity1111141;

    @FXML
    private JFXTextArea noteTXT;

    @FXML
    private JFXTextField emailAddressTXT;

    @FXML
    private ImageView WrongQuantity11111211;

    @FXML
    private JFXTextField fuelQuantityTXT;

    @FXML
    private ImageView WrongQuantity1111111;

    @FXML
    private Text incorrectFuelQtyMSG;

    @FXML
    private ImageView WrongQuantity111112111;

    @FXML
    private Text incorrectEmailMSG;

    @FXML
    private JFXTextField customerGenericDetailsTXT;

    @FXML
    private JFXTextField streetNameTXT;

    @FXML
    private JFXTextField ApartmentNumberTXT;

    @FXML
    private JFXTextField cityTXT;

    @FXML
    private JFXTextField zipCodeTXT;

    @FXML
    private ImageView WrongQuantity111111;

    @FXML
    private ImageView WrongQuantity111112;

    @FXML
    private ImageView WrongQuantity111113;

    @FXML
    private ImageView WrongQuantity111114;

    @FXML
    private Text EmptyStreetMSG;

    @FXML
    private ImageView orderDetailsIndicatorTAB;

    @FXML
    private JFXComboBox<String> shippingMethodComboBOX;

    @FXML
    private ComboBox<?> dayAndDateComboBox;

    @FXML
    private JFXButton t17to19BTN;

    @FXML
    private JFXButton t9to11BTN;

    @FXML
    private JFXButton t11to13BTN;

    @FXML
    private JFXButton t13to15BTN;

    @FXML
    private JFXButton t15to17BTN;

    @FXML
    private JFXButton t7to9BTN;

    @FXML
    private Text shippingSummeryDetailsTXT;

    @FXML
    private ImageView shippingIndicatorTAB1;

    @FXML
    private Text totalPricesOfAlllInUsdTXT;

    @FXML
    private Text DeliveryFeeTXT;

    @FXML
    private Text subTotalPriceInUsdTXT;

    @FXML
    private Text fuelQuantityInLitersTXT;

    @FXML
    private Text firstNameTXT;

    @FXML
    private Text lastNameTXT;

    @FXML
    private Text scheduledDeliveryDateTXT;

    @FXML
    private Text deliveryAddressTXT;

    @FXML
    private ImageView nextOrderDetails;

    @FXML
    private ImageView reviewIndicatorTAB;

    @FXML
    private VBox whenPane;

    @FXML
    private VBox shippingOverviewPane;

    @FXML
    private GridPane optionalDatesForShippingGridPane;
    //
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.formValidation = FormValidation.getValidator();
        this.orderDetailsIndicatorTAB.setVisible(false);
        this.shippingIndicatorTAB1.setVisible(false);
        /*  set all fields validators */
        FormValidation();   //
        /* set form items */
        SetShippingTab();
    }

    /**
     * NewPurchaseFuelForHomeHeatingController Instance getter using SingleTone DesignPatterns
     * @return Instance of controller class
     */
    public NewPurchaseFuelForHomeHeatingBoundary getInstance() {
        if (Instance == null)
            Instance = new NewPurchaseFuelForHomeHeatingBoundary();
        return Instance;
    }

    //
    private void FormValidation() {
        //order details page - start
        /*  fuel quantity validation */
        formValidation.isEmptyFieldValidation(fuelQuantityTXT, "Fuel Quantity");
        formValidation.isDoubleNumberValidation(fuelQuantityTXT, "fuel Quantity");
        formValidation.numberPositiveValidation(fuelQuantityTXT, "Fuel Quantity");
        /*  email address validation */
        formValidation.emailAddressValidation(emailAddressTXT, "Email");
        /*  street name validation */
        formValidation.isEmptyFieldValidation(streetNameTXT, "Street Name");
        formValidation.maxLengthValidation(streetNameTXT, "Street Name", 25);
        /*  apartment number validation */
        formValidation.isEmptyFieldValidation(ApartmentNumberTXT, "Apartment Number");
        formValidation.maxLengthValidation(ApartmentNumberTXT, "Street Name", 5);
        formValidation.isOnlyNumbers(ApartmentNumberTXT,"Apartment Number");
        /* city name validation */
        formValidation.isEmptyFieldValidation(cityTXT, "City");
        formValidation.maxLengthValidation(cityTXT, "City", 15);
        formValidation.isContainsOnlyLetters(cityTXT,"City");
        /* zip code validation */
        formValidation.isEmptyFieldValidation(zipCodeTXT, "Zip code");
        formValidation.maxLengthValidation(zipCodeTXT, "Zip code", 7);
        formValidation.isOnlyNumbers(zipCodeTXT,"Zip code");
        /* phone number validation */
        formValidation.phoneNumberValidation(anotherContactPhoneNumberTXT,"Phone Number");
        /* note validation */
        formValidation.maxLengthValidationTextArea(noteTXT,"Note",150);
        //order details page - end
        //
        //shipping - start
       // InitialAndResetAllShippingDates();

    }


    public void CheckOrderDetails(){
        ArrayList<Object> guiObjects = new ArrayList<Object>();
        guiObjects.add(fuelQuantityTXT);
        guiObjects.add(streetNameTXT);
        guiObjects.add(ApartmentNumberTXT);
        guiObjects.add(cityTXT);
        guiObjects.add(zipCodeTXT);
        //
        for(Object guiObj:guiObjects){
            if(!myController.validatePage(guiObj)){
                orderDetailsIndicatorTAB.setImage(new Image("media/PurchaseMedia/cancel.png"));
                break;
            }
        }
        orderDetailsIndicatorTAB.setVisible(true);
    }

    //todo: להמשיך להגדיר את התאריכים האופציונלים שיובאו מהדטהבייס מטבלת ShippingOptionalDates
    public void SetShippingTab(){
        shippingMethodComboBOX.getItems().addAll("Fast Shipping (40$)","Standard Shipping (15$)");
        whenPane.setVisible(false);
        shippingOverviewPane.setVisible(false);
        optionalDatesForShippingGridPane.setVisible(false);
        //get available dates for shipping from DB
        String getAllAvailableDatesFromDbQUERY = "";    //todo:  query
//        ClientApp.client.handleMessageFromClientUI(new Message(OperationType.getRequirementData,""));
    }

    public void InitialAndResetAllShippingDates(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        String initialShippingDatesQUERY = "INSERT INTO `bpsdc8o22sikrlpvvxqm`.`ShippingOptionalDates` (`DayAndDate`, `T1`, `T2`, `T3`, `T4`, `T5`, `T6`) VALUES ('2020-05-31', '1', '1', '1', '1', '1', '1');";
       // ClientApp.chatClient.handleMessageFromClientUI(new Message(OperationType.updateRequirement,initialShippingDatesQUERY));
    }

}
