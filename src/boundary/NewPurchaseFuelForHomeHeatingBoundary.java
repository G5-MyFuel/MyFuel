package boundary;

import Contollers.FormValidation;
import Contollers.NewPurchaseFuelForHomeHeatingController;
import com.jfoenix.controls.*;
import entity.ShippingDay;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * @author daniel
 * @see NewPurchaseFuelForHomeHeatingController - the form's logic class
 */
public class NewPurchaseFuelForHomeHeatingBoundary implements DataInitializable {
    private NewPurchaseFuelForHomeHeatingController myController = new NewPurchaseFuelForHomeHeatingController(this);
    private String currentCustomerId;
    private String dateAndDayPattern = "";
    private boolean allShippingDetailsAdded = false;
    private StringBuilder shippingDeatilsStr;
    private String shippingMethodAndDetails = "";
    private ArrayList<String> shippingDetailsArrayList = new ArrayList<>();
    private FormValidation formValidation;

    //gui variables:

    @FXML
    private JFXTabPane mainTabPaneOfOrder;

    @FXML
    private Tab orderDetailsTab;

    @FXML
    private JFXTextField anotherContactPhoneNumberTXT;

    @FXML
    private JFXTextArea noteTXT;

    @FXML
    private ImageView orderDetailsToShipping;

    @FXML
    private JFXTextField emailAddressTXT;

    @FXML
    private JFXTextField fuelQuantityTXT;

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
    private Tab shippingDetailsTab;

    @FXML
    private JFXComboBox<String> shippingMethodComboBOX;

    @FXML
    private ImageView WrongQuantity111111;

    @FXML
    private VBox whenPane;

    @FXML
    private JFXDatePicker shippingDatePicker;

    @FXML
    private Label ShippingHouresAvailableOnThisDateLABLE;

    @FXML
    private GridPane optionalDatesForShippingGridPane;

    @FXML
    private JFXButton t9to11BTN;

    @FXML
    private JFXButton t11to13BTN;

    @FXML
    private JFXButton t15to17BTN;

    @FXML
    private JFXButton t7to9BTN;

    @FXML
    private JFXButton t17to19BTN;

    @FXML
    private JFXButton t13to15BTN;

    @FXML
    private VBox shippingOverviewPane;

    @FXML
    private Text shippingSummeryDetailsTXT;

    @FXML
    private ImageView shippingIndicatorTAB1;

    @FXML
    private Tab orderReviewTab;

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
        //setDayAndDateComboBox();
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
    }

    @FXML
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
        shippingOverviewPane.setVisible(false);
        whenPane.setVisible(false);
        shippingMethodComboBOX.getItems().addAll("Fast Shipping (40$)", "Standard Shipping (15$)");
        shippingMethodComboBOX.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch (shippingMethodComboBOX.getValue()) {
                    case "Fast Shipping (40$)":
                        System.out.println("Fast Shipping selected");
                        FastShippingSelected();

                        break;
                    case "Standard Shipping (15$)":
                        System.out.println("Standard Shipping selected");
                        try {
                            StandardShippingSelected();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });

    }

    private void FastShippingSelected() {
        whenPane.setVisible(false);
        optionalDatesForShippingGridPane.setVisible(false);
        shippingOverviewPane.setVisible(false);
        ImageView fastShippingLogo = new ImageView("media/FuelForHomeHeating/fastShippingLogo.jpg");

    }

    private void StandardShippingSelected() throws InterruptedException {
        whenPane.setVisible(true);
        optionalDatesForShippingGridPane.setVisible(false);

        // disable past dates of DatePicker gui obj
        Callback<DatePicker, DateCell> callB = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                        LocalDate today = LocalDate.now();
                        setDisable(empty || item.compareTo(today) < 0);
                    }
                };
            }

        };
        shippingDatePicker.setDayCellFactory(callB);
    }


    //after selected shipping date
    public void SetAvailableTimesOfDateSelected(Date localDate) {
        shippingDatePicker.getValue();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        ArrayList<ShippingDay> allDatesAvailableAsArrayList = myController.getAvailableTimesInDate();
    }


    @FXML
    void GoToOrderDetailsPage(MouseEvent event) {
        //orderDetailsTab
    }

    @FXML
    void GoToReviewPage(MouseEvent event) {

    }

    @FXML
    void GoToShippingPage(MouseEvent event) {

    }

    /**
     * set action for {@link JFXDatePicker} object
     *
     * @param event
     */
    @FXML
    void handleJFXDatePicker(ActionEvent event) {
        myController.GetShippingOptionalDatesTableFromDB(); //get the available shipping dates and times range
        ShippingHouresAvailableOnThisDateLABLE.setVisible(false);

        java.sql.Date dateSelectedAsDate = java.sql.Date.valueOf(shippingDatePicker.getValue());
        SetAvailableTimesOfDateSelected(dateSelectedAsDate);
    }

    public void setAllShippingDetailsAdded(boolean allShippingDetailsAdded) {
        this.allShippingDetailsAdded = allShippingDetailsAdded;
    }


    /**
     * Set the available dates and times for shipping
     */
    boolean b = false;

    public void setAvailableTimesForShipping() throws ParseException {
        ShippingHouresAvailableOnThisDateLABLE.setVisible(true);
        optionalDatesForShippingGridPane.setVisible(true);
        ArrayList<ShippingDay> shippingDayArrayList = myController.getAvailableTimesInDate();

        for (ShippingDay sd : shippingDayArrayList) {
            String s1 = sd.getDate();
            String s2 = String.valueOf(shippingDatePicker.getValue());
            if (s1.equals(s2)) {
                if (sd.getT1() == 1) {
                    t7to9BTN.setDisable(true);
                } else {
                    t7to9BTN.setDisable(false);
                }

                if (sd.getT2() == 1) {
                    t9to11BTN.setDisable(true);
                } else {
                    t11to13BTN.setDisable(false);
                }

                if (sd.getT3() == 1) {
                    t11to13BTN.setDisable(true);
                } else {
                    t11to13BTN.setDisable(false);
                }

                if (sd.getT4() == 1) {
                    t13to15BTN.setDisable(true);
                } else {
                    t13to15BTN.setDisable(false);
                }

                if (sd.getT5() == 1) {
                    t15to17BTN.setDisable(true);
                } else {
                    t15to17BTN.setDisable(false);
                }

                if (sd.getT6() == 1) {
                    t17to19BTN.setDisable(true);
                } else {
                    t17to19BTN.setDisable(false);
                }

            } else {
                //ניצור את התאריך בDB
                if (!b) {
                    System.out.println("new row inserted in shipping dates table");
                    myController.InsertNewAvailableDateToDB(s2);
                    b = true;
                }

            }
            setShippingTimesBtnActions(t7to9BTN);
            setShippingTimesBtnActions(t9to11BTN);
            setShippingTimesBtnActions(t11to13BTN);
            setShippingTimesBtnActions(t13to15BTN);
            setShippingTimesBtnActions(t15to17BTN);
            setShippingTimesBtnActions(t17to19BTN);
        }
    }

    /**
     * Set actions for the optional times for shipping
     *
     * @param btn
     */
    private void setShippingTimesBtnActions(JFXButton btn) {
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //build the shipping details string
                shippingDeatilsStr = new StringBuilder();
                shippingDeatilsStr.append(StringUtils.capitalize(shippingDatePicker.getValue().getDayOfWeek().toString().toLowerCase()) + ", ");
                shippingDeatilsStr.append(shippingDatePicker.getValue().toString() + " between ");
                shippingDeatilsStr.append(btn.getText());
                shippingSummeryDetailsTXT.setText(shippingDeatilsStr.toString());
                //Show the Label if it is hidden
                if (!shippingOverviewPane.isVisible())
                    shippingOverviewPane.setVisible(true);
                //save all shipping details for purchase
                shippingDetailsArrayList.add("Standard Shipping");
                shippingDetailsArrayList.add(shippingDatePicker.getValue().getDayOfWeek().toString().toLowerCase());
                shippingDetailsArrayList.add(shippingDatePicker.getValue().toString());
                shippingDetailsArrayList.add(btn.getText());
                //


            }
        });
    }


}

