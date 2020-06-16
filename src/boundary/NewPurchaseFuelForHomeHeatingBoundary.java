package boundary;

import Contollers.FormValidation;
import Contollers.NewPurchaseFuelForHomeHeatingController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author daniel
 * @see NewPurchaseFuelForHomeHeatingController - the form's logic class
 */
public class NewPurchaseFuelForHomeHeatingBoundary implements DataInitializable {
    private NewPurchaseFuelForHomeHeatingController myController = new NewPurchaseFuelForHomeHeatingController(this);
    private String currentCustomerId;
    private String dateAndDayPattern = "";

    private FormValidation formValidation;

    //gui variables:

    @FXML
    private JFXTextField anotherContactPhoneNumberTXT;

    @FXML
    private JFXTextArea noteTXT;

    @FXML
    private ImageView nextOrderDetailsBtn;

    @FXML
    private JFXTextField emailAddressTXT;

    @FXML
    private JFXTextField fuelQuantityTXT;

    @FXML
    private Text fuelLimitForOrder;

    @FXML
    private JFXTextField streetNameTXT;

    @FXML
    private JFXTextField ApartmentNumberTXT;

    @FXML
    private JFXTextField cityTXT;

    @FXML
    private JFXTextField zipCodeTXT;

    @FXML
    private ImageView orderDetailsIndicatorTAB;

    @FXML
    private JFXComboBox<String> shippingMethodComboBOX;

    @FXML
    private ImageView WrongQuantity111111;

    @FXML
    private VBox whenPane;

    @FXML
    private JFXComboBox<String> dayAndDateComboBox;

    @FXML
    private GridPane optionalDatesForShippingGridPane;

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
    private VBox shippingOverviewPane;

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


    @Override
    public void initData(Object data) {
        this.currentCustomerId = (String) data;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDayAndDateComboBox();
        this.formValidation = FormValidation.getValidator();
        this.orderDetailsIndicatorTAB.setVisible(false);
        this.shippingIndicatorTAB1.setVisible(false);
        /*  set all fields validators */
        FormValidation();   //
        /* set form items */
        SetShippingTab();
    }


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
        formValidation.isOnlyNumbers(ApartmentNumberTXT, "Apartment Number");
        /* city name validation */
        formValidation.isEmptyFieldValidation(cityTXT, "City");
        formValidation.maxLengthValidation(cityTXT, "City", 15);
        formValidation.isContainsOnlyLetters(cityTXT, "City");
        /* zip code validation */
        formValidation.isEmptyFieldValidation(zipCodeTXT, "Zip code");
        formValidation.maxLengthValidation(zipCodeTXT, "Zip code", 7);
        formValidation.isOnlyNumbers(zipCodeTXT, "Zip code");
        /* phone number validation */
        formValidation.phoneNumberValidation(anotherContactPhoneNumberTXT, "Phone Number");
        /* note validation */
        formValidation.maxLengthValidationTextArea(noteTXT, "Note", 150);
        //order details page - end
        //
        //shipping - start
        // InitialAndResetAllShippingDates();

    }

    private void setDayAndDateComboBox() {
        //get current day and date
        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("EEEE dd/MM/yyyy");

        //build arraylist with the days and dates of the next two weeks
        ArrayList<String> twoWeeksDaysAndDates = new ArrayList<>();
        twoWeeksDaysAndDates.add(fmt.withLocale(Locale.ENGLISH).print(dt));
        for(int i=1;i<15;i++) {
            twoWeeksDaysAndDates.add(fmt.withLocale(Locale.ENGLISH).print(dt.plusDays(i)));
        }

        //adds the arraylist values to the comboBox of optional dates
        dayAndDateComboBox.getItems().addAll(twoWeeksDaysAndDates);
    }

    public void CheckOrderDetails() {
        ArrayList<Object> guiObjects = new ArrayList<Object>();
        guiObjects.add(fuelQuantityTXT);
        guiObjects.add(streetNameTXT);
        guiObjects.add(ApartmentNumberTXT);
        guiObjects.add(cityTXT);
        guiObjects.add(zipCodeTXT);
        //
        for (Object guiObj : guiObjects) {
            if (!myController.validatePage(guiObj)) {
                orderDetailsIndicatorTAB.setImage(new Image("media/PurchaseMedia/cancel.png"));
                break;
            }
        }
        orderDetailsIndicatorTAB.setVisible(true);
    }

    //todo: להמשיך להגדיר את התאריכים האופציונלים שיובאו מהדטהבייס מטבלת ShippingOptionalDates
    public void SetShippingTab() {
        shippingMethodComboBOX.getItems().addAll("Fast Shipping (40$)", "Standard Shipping (15$)");
        whenPane.setVisible(true);
        shippingOverviewPane.setVisible(false);
        optionalDatesForShippingGridPane.setVisible(false);
        //get available dates for shipping from DB
        String getAllAvailableDatesFromDbQUERY = "";    //todo:  query
//        ClientApp.client.handleMessageFromClientUI(new Message(OperationType.getRequirementData,""));
    }

    public void InitialAndResetAllShippingDates() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        String initialShippingDatesQUERY = "INSERT INTO `bpsdc8o22sikrlpvvxqm`.`ShippingOptionalDates` (`DayAndDate`, `T1`, `T2`, `T3`, `T4`, `T5`, `T6`) VALUES ('2020-05-31', '1', '1', '1', '1', '1', '1');";
        // ClientApp.chatClient.handleMessageFromClientUI(new Message(OperationType.updateRequirement,initialShippingDatesQUERY));
    }
}
