package client.boundary;

import client.logic.FormValidation;
import client.logic.NewPurchaseFuelForHomeHeatingLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author daniel
 * @see NewPurchaseFuelForHomeHeatingLogic - the form's logic class
 */
public class NewPurchaseFuelForHomeHeatingController implements Initializable {
    private NewPurchaseFuelForHomeHeatingLogic newPurchaseFuelForHomeHeatingLogic;
    private FormValidation formValidation;
    //gui variables:
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    //

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.newPurchaseFuelForHomeHeatingLogic = NewPurchaseFuelForHomeHeatingLogic.getInstance();
        this.formValidation = FormValidation.getValidator();
        //
        formValidation();
        /*  check all required fields are'nt empty:*/

        formValidation.isEmptyField(streetNameTXT, "Street Name");
        formValidation.isEmptyField(ApartmentNumberTXT, "Apartment Number");
        formValidation.isEmptyField(cityTXT, "City");
        formValidation.isEmptyField(zipCodeTXT, "Zip code");
        /*  check form input validation */

    }

    private void formValidation() {
        //fuel quantity validation
        formValidation.isEmptyField(fuelQuantityTXT, "Fuel Quantity");
        formValidation.isDoubleNumberValidation(fuelQuantityTXT,"fuel Quantity");
        formValidation.numberPositiveValidator(fuelQuantityTXT, "Fuel Quantity");
        //clie
    }



    @FXML
    void CheckEmailValidation() {
        boolean validator;
        validator = newPurchaseFuelForHomeHeatingLogic.isValidEmail(emailAddressTXT.getText());
        System.out.println("The Email address " + emailAddressTXT.getText() + " is " + (validator ? "valid" : "invalid"));
        incorrectEmailMSG.setVisible(!validator);
    }


}