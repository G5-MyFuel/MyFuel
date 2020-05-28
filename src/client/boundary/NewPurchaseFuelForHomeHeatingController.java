package client.boundary;

import client.logic.FormValidation;
import client.logic.NewPurchaseFuelForHomeHeatingLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author daniel
 * @see NewPurchaseFuelForHomeHeatingLogic - the form's logic class
 */
public class NewPurchaseFuelForHomeHeatingController implements Initializable {
    private static NewPurchaseFuelForHomeHeatingController Instance = null;
    private NewPurchaseFuelForHomeHeatingLogic newPurchaseFuelForHomeHeatingLogic;
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
    private ComboBox<?> shippingMethodComboBOX;

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
    private ImageView shippingIndicatorTAB;

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
    //
//    private ImageView xImg = new ImageView(new Image("src\\media\\PurchaseMedia\\X.png"));
    private String vImgUrl = "src/media/PurchaseMedia/V.png";
    private String xImgUrl = "src/media/PurchaseMedia/X.png";

    //
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.newPurchaseFuelForHomeHeatingLogic = NewPurchaseFuelForHomeHeatingLogic.getInstance();
        this.formValidation = FormValidation.getValidator();
//        this.orderDetailsIndicatorTAB = new ImageView(xImgUrl);

        /*  set all fields validators */
       // formValidation();   //

    }

    /**
     * NewPurchaseFuelForHomeHeatingController Instance getter using SingleTone DesignPatterns
     * @return Instance of controller class
     */
    public NewPurchaseFuelForHomeHeatingController getInstance() {
        if (Instance == null)
            Instance = new NewPurchaseFuelForHomeHeatingController();
        return Instance;
    }

    //
    private void formValidation() {
        /*  fuel quantity validation */
        formValidation.isEmptyField(fuelQuantityTXT, "Fuel Quantity");
        formValidation.isDoubleNumberValidation(fuelQuantityTXT, "fuel Quantity");
        formValidation.numberPositiveValidation(fuelQuantityTXT, "Fuel Quantity");
        /*  email address validation */
        formValidation.emailAddressValidation(emailAddressTXT, "Email");
        /*  street name validation */
        formValidation.isEmptyField(streetNameTXT, "Street Name");
        formValidation.maxLengthValidation(streetNameTXT, "Street Name", 25);
        /*  apartment number validation */
        formValidation.isEmptyField(ApartmentNumberTXT, "Apartment Number");
        formValidation.maxLengthValidation(ApartmentNumberTXT, "Street Name", 5);
        formValidation.isContainsOnlyNumbers(ApartmentNumberTXT,"Apartment Number");
        /* city name validation */
        formValidation.isEmptyField(cityTXT, "City");
        formValidation.maxLengthValidation(cityTXT, "City", 15);
        formValidation.isContainsOnlyLetters(cityTXT,"City");
        /* zip code validation */
        formValidation.isEmptyField(zipCodeTXT, "Zip code");
        formValidation.maxLengthValidation(zipCodeTXT, "Zip code", 7);
        formValidation.isContainsOnlyNumbers(zipCodeTXT,"Zip code");
        /* phone number validation */
        formValidation.phoneNumberValidation(anotherContactPhoneNumberTXT,"");
        /* note validation */
        formValidation.maxLengthValidationTextArea(noteTXT,"Note",150);
        //todo:בדיקת תקינות קלט לאחר מעבר כל מסך (מסך ראשון יהיה פה) לבדוק שכל הפונקציות מחזירות אינדיקציה שמה שנדרש תקין ואז האינדיקטור יהיה V אחרת X
    }

    public void checkOrderDetails(){
        ArrayList<Object> guiObjects = new ArrayList<Object>();
        guiObjects.add(fuelQuantityTXT);
        guiObjects.add(streetNameTXT);
        guiObjects.add(ApartmentNumberTXT);
        guiObjects.add(cityTXT);
        guiObjects.add(zipCodeTXT);
        //
        for(Object guiObj:guiObjects){
            if(!newPurchaseFuelForHomeHeatingLogic.validatePage(guiObj)){

            }
        }
    }
}
