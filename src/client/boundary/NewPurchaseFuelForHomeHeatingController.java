package client.boundary;

import client.logic.NewPurchaseFuelForHomeHeatingLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.newPurchaseFuelForHomeHeatingLogic = NewPurchaseFuelForHomeHeatingLogic.getInstance();
        /*  check all required fields are'nt empty:*/
        isEmptyField(fuelQuantityTXT, "Fuel Quantity");
        isEmptyField(streetNameTXT, "Street Name");
        isEmptyField(ApartmentNumberTXT, "Apartment Number");
        isEmptyField(cityTXT, "City");
        isEmptyField(zipCodeTXT, "Zip code");
        /*  check form input validation */
        numberPositiveValidator(fuelQuantityTXT, "Fuel Quantity");
    }


    /**
     * Required Input field Validation mathod
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of field
     */
    private void isEmptyField(JFXTextField theField, String fieldName) {
        RequiredFieldValidator reqInputValidator = new RequiredFieldValidator();
        reqInputValidator.setMessage(fieldName + " field is Required!");
        theField.getValidators().add(reqInputValidator);
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) theField.validate();
        });
    }

    /**
     * A method that checks if a field contains a positive number greater than 0
     *
     * @param theField  - the field to validate
     * @param fieldName - the name of field
     */
    private void numberPositiveValidator(JFXTextField theField, String fieldName) {
        NumberValidator numberValidator = new NumberValidator();
        theField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                numberValidator.setMessage(fieldName + " contains only numbers");
                theField.getValidators().add(numberValidator);
                theField.validate();
            }
            if (newVal) {
                if (Double.valueOf(theField.getText()) <= 0) {
                    numberValidator.setMessage("The amount of fuel must be positive");
                    theField.getValidators().add(numberValidator);
                    theField.validate();
                }
            }
        });
    }


    void CheckFuelQuantityValidation() {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        fuelQuantityTXT.getValidators().add(validator);
        validator.setMessage("Fuel amount is Required!");

        fuelQuantityTXT.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    System.out.println("The fuelAmount field is empty");
                    fuelQuantityTXT.validate();
                } else {
                    double fuelAmount = Double.valueOf(fuelQuantityTXT.getText());
                    if (fuelAmount <= 0) {
                        System.out.println("The amount of fuel must be greater than 0");
                        validator.setMessage("The amount of fuel must be positive");
                        fuelQuantityTXT.validate();
                    }
                }
            }
        });
    }

    @FXML
    void CheckEmailValidation() {
        boolean validator;
        validator = newPurchaseFuelForHomeHeatingLogic.isValidEmail(emailAddressTXT.getText());
        System.out.println("The Email address " + emailAddressTXT.getText() + " is " + (validator ? "valid" : "invalid"));
        incorrectEmailMSG.setVisible(!validator);
    }


}
