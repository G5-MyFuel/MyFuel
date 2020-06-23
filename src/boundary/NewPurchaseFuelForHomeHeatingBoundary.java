package boundary;

import Contollers.FormValidation;
import Contollers.NewPurchaseFuelForHomeHeatingController;
import com.jfoenix.controls.*;
import common.assets.enums.FuelTypes;
import common.assets.enums.ShippingMethod;
import entity.*;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 *
 * @author Daniel Gabbay
 * @see NewPurchaseFuelForHomeHeatingController - the form's boundary class
 */
public class NewPurchaseFuelForHomeHeatingBoundary implements DataInitializable {
    private NewPurchaseFuelForHomeHeatingController myController = new NewPurchaseFuelForHomeHeatingController(this);
    generalDashBoardBoundary myDashBoundary;
    Costumer currentCostumerDetailsFromDB;
    /**
     * temp variable to store
     */
    public String currentCustomerId = null;
    private String dateAndDayPattern = null;
    private boolean allShippingDetailsAdded = false;
    private ImageView iconFastShipping = null;
    private PurchaseFuelForHomeHeating currentPurchaseHomeHeating;
    private String shippingMethodTXT = null;
    private Double totalPrice;
    private Prices thisOrderPrice;
    private String campaignID;
    private FormValidation formValidation;

    /**
     * gui variables
     */
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
    private AnchorPane topAnchorPane;

    @FXML
    private VBox mainVbox;

    @FXML
    private HBox hboxOfStandart;

    @FXML
    private JFXComboBox<String> shippingMethodComboBOX;

    @FXML
    private ImageView WrongQuantity111111;

    @FXML
    private VBox whenPane;

    @FXML
    private HBox dayAndDateComboBoxHboxLine;

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
    private VBox whenPane1;

    @FXML
    private Label whenLable1;

    @FXML
    private HBox dayAndDateComboBoxHboxLine1;

    @FXML
    private JFXDatePicker shippingDatePicker1;

    @FXML
    private Label ShippingHouresAvailableOnThisDateLABLE1;

    @FXML
    private GridPane optionalDatesForShippingGridPane1;

    @FXML
    private JFXButton t9to11BTN1;

    @FXML
    private JFXButton t11to13BTN1;

    @FXML
    private JFXButton t15to17BTN1;

    @FXML
    private JFXButton t7to9BTN1;

    @FXML
    private JFXButton t17to19BTN1;

    @FXML
    private JFXButton t13to15BTN1;

    @FXML
    private VBox shippingOverviewPane;

    @FXML
    private Text firstOverviewText;

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

    @FXML
    private Text confirmMassage;

    @Override
    public void initData(Object data) {
        ArrayList<Object> varArray = (ArrayList<Object>) data;
        String currentCustomerId1 = (String) varArray.get(0);
        this.currentCustomerId = currentCustomerId1;
        myDashBoundary = (generalDashBoardBoundary) varArray.get(1);
        String[] words = myDashBoundary.userFirstName.getText().split("\\s+");
        firstNameTXT.setText(words[0]);
        lastNameTXT.setText(words[1]);
        LocalDate.now().toString();
        myController.GET_SPECIFIC_CUSTOMER_DETAILS(this.currentCustomerId);
        this.currentPurchaseHomeHeating = new PurchaseFuelForHomeHeating(currentCustomerId, LocalDateTime.now(), 0.0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.formValidation = FormValidation.getValidator();
        this.orderDetailsIndicatorTAB.setVisible(false);
        this.confirmMassage.setVisible(false);
        this.shippingIndicatorTAB1.setVisible(false);
        /*  set all fields validators */
        FormValidation();   //
        /* set form items */
        SetShippingTab();

        shippingMethodComboBOX.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
            boolean flag = true;
            switch (shippingMethodComboBOX.getValue()) {
                case "Fast Shipping - Extra 2% per liter of fuel":
                    this.shippingMethodTXT = "Fast Shipping";
                    this.currentPurchaseHomeHeating.setShippingMethod(ShippingMethod.FAST);
                    break;
                case "Standard Shipping":
                    shippingMethodTXT = "Standard Shipping";
                    this.currentPurchaseHomeHeating.setShippingMethod(ShippingMethod.STANDARD);
                    break;
                default:
                    flag = false;
                    break;
            }
            if(flag)
                thisOrderPrice = new Prices(currentCostumerDetailsFromDB,Double.parseDouble(fuelQuantityTXT.getText()), FuelTypes.HomeHeatingFuel,currentPurchaseHomeHeating.getShippingMethod());
                }
        );
    }

    /**
     *
     * @param event
     */
    @FXML
    void afterOrderDetails(MouseEvent event) {
        CheckOrderDetails();
        //TODO: here need to check if all order details are correct and if and only if correct allowed to move to shipping.
        orderDetailsTab.getTabPane().getSelectionModel().selectNext();
    }

    /**
     *
     * @param event
     */
    @FXML
    void afterShipping(MouseEvent event) {
        shippingDetailsTab.getTabPane().getSelectionModel().selectNext();

    }

    /**
     *
     * @param event
     */
    @FXML
    void beforeReview(MouseEvent event) {
        orderReviewTab.getTabPane().getSelectionModel().selectPrevious();
    }

    /**
     *
     * @param event
     */
    @FXML
    void beforeShipping(MouseEvent event) {
        shippingDetailsTab.getTabPane().getSelectionModel().selectPrevious();
    }

    /**
     * To validate all the details that was entered to the fields
     */
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

    /**
     *
     */
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

    /**
     *
     */
    public void SetShippingTab() {
        shippingOverviewPane.setVisible(false);
        whenPane.setVisible(false);
        shippingMethodComboBOX.getItems().addAll("Fast Shipping - Extra 2% per liter of fuel", "Standard Shipping");
        shippingMethodComboBOX.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch (shippingMethodComboBOX.getValue()) {
                    case "Fast Shipping - Extra 2% per liter of fuel":
                        System.out.println("Fast Shipping selected");
                        FastShippingSelected();

                        break;
                    case "Standard Shipping":
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

    /**
     * To insert all the details of this purchase to DB
     */
    public void INSERT_NEW_PURCHASE_FUEL_FOR_HOME_HEATING() {
        ArrayList<Object> varArray = new ArrayList<>();
        Random rand = new Random();
        Integer p_id = rand.nextInt(9000)+1000;
        varArray.add(p_id);
        varArray.add(emailAddressTXT.getText());
        varArray.add(anotherContactPhoneNumberTXT.getText());
        varArray.add(noteTXT.getText());
        varArray.add(shippingMethodTXT);
        varArray.add(shippingSummeryDetailsTXT.getText());
        myController.INSERT_NEW_PURCHASE_FUEL_FOR_HOME_HEATING(varArray);
        //
        ArrayList<Object> varArray1 = new ArrayList<>();
        varArray1.add(p_id);
        varArray1.add(currentCostumerDetailsFromDB.getUserID());
//        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
//        varArray1.add(timeStamp);
        varArray1.add(fuelQuantityTXT.getText());
        varArray1.add(totalPrice);
        String timeStamp1 = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        varArray1.add(timeStamp1);
        varArray1.add("0");
        System.out.println(varArray1);
        System.out.println("******purches******");
        varArray1.toString();

        myController.INSERT_NEW_PURCHASE_FUEL_FOR_HOME_HEATING1(varArray1);
    }

    /**
     * Show specific things after we select fast shipping
     */
    private void FastShippingSelected() {
        whenPane.setVisible(false);
        whenPane1.setVisible(true);
        //
        firstOverviewText.setText("You chose a fast shipping method!");
        shippingSummeryDetailsTXT.setText("You will receive your invitation to the address you entered in the next 6 hours");
        shippingOverviewPane.setVisible(true);
    }

    /**
     *
     * @throws InterruptedException
     */
    private void StandardShippingSelected() throws InterruptedException {
        whenPane1.setVisible(false);
        shippingOverviewPane.setVisible(false);
        whenPane.setVisible(true);
        //
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
        shippingDatePicker.setEditable(true);
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

    }

    @FXML
    void GoToReviewPage(MouseEvent event) {

    }

    @FXML
    void GoToShippingPage(MouseEvent event) {

    }

    /**
     *
     */
    @FXML
    void ClickOnREVIEWtab() {
        if (checkAndSetShippingMethod()) {
            //shipping details setters
            currentPurchaseHomeHeating.setCustomerID(currentCustomerId);
            //
            StringBuilder deliveryAddressFormat = new StringBuilder();
            deliveryAddressFormat.append(streetNameTXT.getText());
            deliveryAddressFormat.append(" | ");
            deliveryAddressFormat.append(ApartmentNumberTXT.getText());
            deliveryAddressFormat.append(" | ");
            deliveryAddressFormat.append(cityTXT.getText());
            deliveryAddressFormat.append(" | ");
            deliveryAddressFormat.append(zipCodeTXT.getText());
            //
            deliveryAddressTXT.setText(deliveryAddressFormat.toString());
            //
            if (currentPurchaseHomeHeating.getShippingMethod().equals(ShippingMethod.STANDARD)) {
                scheduledDeliveryDateTXT.setText(" " + shippingSummeryDetailsTXT.getText());
                DeliveryFeeTXT.setText("Fast Shipping");
            } else {
                scheduledDeliveryDateTXT.setText(" You will receive the shipment in the next 6 hours");
                DeliveryFeeTXT.setText("Standard Shipping");
            }

            //Prices sets
            String fuelQuantityStr = fuelQuantityTXT.getText() + " Liters";
            totalPrice = calculateOrderPrice();
            fuelQuantityInLitersTXT.setText(fuelQuantityStr);
            totalPricesOfAlllInUsdTXT.setText(new DecimalFormat("##.##").format(totalPrice) + "$");
            subTotalPriceInUsdTXT.setText(String.valueOf(Prices.basePrice_homeHeating * Double.parseDouble(fuelQuantityTXT.getText())));

        }
    }

    /**
     *
     * @return
     */
    private boolean checkAndSetShippingMethod() {
        //set shipping method
        //check if shipping method selected
        switch (shippingMethodComboBOX.getValue()) {
            case "Fast Shipping - Extra 2% per liter of fuel":
                this.shippingMethodTXT = "Fast Shipping";
                this.currentPurchaseHomeHeating.setShippingMethod(ShippingMethod.FAST);
                return true;
            case "Standard Shipping":
                shippingMethodTXT = "Standard Shipping";
                this.currentPurchaseHomeHeating.setShippingMethod(ShippingMethod.STANDARD);
                return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    private Double calculateOrderPrice() {
        totalPrice = 0.0;
        thisOrderPrice.calculateTotalPrice();
        currentPurchaseHomeHeating.setPriceOfOrder(thisOrderPrice);
        totalPrice = thisOrderPrice.getTotalPrice();
        return totalPrice;
    }


    /**
     *
     * @param event
     */
    @FXML
    void handleJFXDatePicker(ActionEvent event) {
        myController.GetShippingOptionalDatesTableFromDB(); //get the available shipping dates and times range
        //
        java.sql.Date dateSelectedAsDate = java.sql.Date.valueOf(shippingDatePicker.getValue());
        SetAvailableTimesOfDateSelected(dateSelectedAsDate);
    }

    public void setAllShippingDetailsAdded(boolean allShippingDetailsAdded) {
        this.allShippingDetailsAdded = allShippingDetailsAdded;
    }

    /**
     *
     * @param shippingDayArrayList
     */
    public void setAvailableTimesForShipping(ArrayList<ShippingDay> shippingDayArrayList) {
        ShippingHouresAvailableOnThisDateLABLE.setVisible(true);
        optionalDatesForShippingGridPane.setVisible(true);

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

            }
            setShippingTimes(t7to9BTN);
            setShippingTimes(t9to11BTN);
            setShippingTimes(t11to13BTN);
            setShippingTimes(t13to15BTN);
            setShippingTimes(t15to17BTN);
            setShippingTimes(t17to19BTN);

        }
    }

    /**
     *
     * @param btn
     */
    private void setShippingTimes(JFXButton btn) {
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!shippingOverviewPane.isVisible())
                    shippingOverviewPane.setVisible(true);
                ///

                StringBuilder str = new StringBuilder();
                str.append(StringUtils.capitalize(shippingDatePicker.getValue().getDayOfWeek().toString().toLowerCase()) + ", ");
                str.append(shippingDatePicker.getValue().toString() + " between ");
                str.append(btn.getText());
                shippingSummeryDetailsTXT.setText(str.toString());
                //

//                DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
                StringBuilder date = new StringBuilder();
                date.append(shippingDatePicker.getValue().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                date.append(" " + btn.getText());
                currentPurchaseHomeHeating.setShippingDateAndTime(date.toString());
            }
        });
    }

    /**
     * review order tab setting
     */
    private void SetReviewOrderPane() {
        this.currentPurchaseHomeHeating.setFuelAmount(Double.valueOf(fuelQuantityTXT.getText()));
        this.currentPurchaseHomeHeating.setEmailForInvoice(emailAddressTXT.getText());
        PurchaseFuelForHomeHeating.Address address = new PurchaseFuelForHomeHeating.Address(streetNameTXT.getText(), ApartmentNumberTXT.getText(), cityTXT.getText(), zipCodeTXT.getText());
        this.currentPurchaseHomeHeating.setAddressForShipping(address);
        this.currentPurchaseHomeHeating.setPhoneNumberForContact(anotherContactPhoneNumberTXT.getText());
        this.currentPurchaseHomeHeating.setNoteForPurchase(noteTXT.getText());
        this.currentPurchaseHomeHeating.setDeliveryStatus(OrderDeliveryStatus.CONFIRMED_ORDER);
    }

    public Costumer getCurrentCostumerDetailsFromDB() {
        return currentCostumerDetailsFromDB;
    }

    public void setCurrentCostumerDetailsFromDB(Costumer currentCostumerDetailsFromDB) {
        this.currentCostumerDetailsFromDB = currentCostumerDetailsFromDB;
    }

    //
    @FXML
    public void beforeReview() {

    }

    /**
     * To insert details in DB
     * @param event
     */
    @FXML
    void confirmOrder(ActionEvent event) {
        this.INSERT_NEW_PURCHASE_FUEL_FOR_HOME_HEATING();
       /* ArrayList<String> myPurchase = new ArrayList<>();
        ArrayList<String> myHHPurchase = new ArrayList<>();
        Random rand = new Random();
        Integer p_id = rand.nextInt(9000)+1000;

        myPurchase.add(String.valueOf(p_id));
        myPurchase.add(this.currentCustomerId);
        myPurchase.add(fuelQuantityTXT.getText());
        myPurchase.add(String.valueOf(totalPrice));
        myPurchase.add(LocalTime.now().toString());
        myPurchase.add(this.thisOrderPrice.getCampaignID());
        myPurchase.toString();
        myController.INSERT_NEW_PURCHES_toDB(myPurchase);

        myHHPurchase.add(String.valueOf(p_id));
        myHHPurchase.add(emailAddressTXT.getText());
        myHHPurchase.add(anotherContactPhoneNumberTXT.getText());
        myHHPurchase.add(noteTXT.getText());
        myHHPurchase.add("CONFIRMED_ORDER");
        myHHPurchase.add(shippingMethodComboBOX.getValue());
        myHHPurchase.add(t9to11BTN.getText());
        myController.INSERT_NEW_PURCHES_TO_HOME_HEATING_toDB(myHHPurchase);
        confirmMassage.setVisible(true);
*/
    }

    public String getCampaignID() {
        return campaignID;
    }

    public void setCampaignID(String campaignID) {
        this.campaignID = campaignID;
    }
}