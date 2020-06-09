package boundary;

import Contollers.CustomerRegistrationController;
import Contollers.FormValidation;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import entity.Costumer;
import entity.CreditCard;
import entity.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author itay
 * @see CustomerRegistrationBoundary - the form's Boundary class
 */

public class CustomerRegistrationBoundary implements Initializable {

    private ArrayList<Vehicle> tempVehicleArray;
    CreditCard tempCreditCard = null;

    private CustomerRegistrationController myController = new CustomerRegistrationController(this);
    private FormValidation formValidation;
    private boolean CardClickFlag = false;
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    private ArrayList<Costumer> allDBCostumerArray;

    @FXML
    private FXMLLoader PRCLoader;
    @FXML
    private pymentWindowControllerForRegistarBoundary PRC;

    /*Gui variables:
     * */

    @FXML
    private Button btnOverview;

    @FXML
    private Button addCostumerBtn;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnSignout;

    @FXML
    private Tab personalInfoTAB;

    @FXML
    private ImageView forwardButtonPersonalToVehicle;

    @FXML
    private JFXTextField CostumerIDtxt;

    @FXML
    private JFXTextField FirstNametxt;

    @FXML
    private JFXTextField LastNametxt;

    @FXML
    private JFXTextField EmailAdresstxt;

    @FXML
    private Hyperlink CreditCardLink;

    @FXML
    private Tab vehicleMangTAB;

    @FXML
    private TableView<Vehicle> VehicleTable;

    @FXML
    private ImageView addVehicleButton;

    @FXML
    private Button saveVehicleButton;

    @FXML
    private JFXTextField VehicleIDtxt;

    @FXML
    private Pane VehicleInformationPane;

    @FXML
    private JFXComboBox<String> GasTypeChoiseBox;

    @FXML
    private ImageView backButtonVehicalToPersonal;

    @FXML
    private ImageView forwardButtonVehicleToPlan;

    @FXML
    private Tab planInfoTAB;

    @FXML
    private JFXComboBox<String> CostumertypeChoiceBox;

    @FXML
    private JFXComboBox<String> ServicePlanChoiseBox;

    @FXML
    private Pane PurchhasePlanChoseBox;

    @FXML
    private ToggleGroup q;

    @FXML
    private ImageView backButtonPlanToVehicle;

    @FXML
    private Button FinishButton;

    @FXML
    private Pane mainPane;

    @FXML
    private TableColumn<Vehicle, String> VehicleIdColom;

    @FXML
    private TableColumn<Vehicle, String> GasTypeColom;

    @FXML
    private JFXRadioButton noButton;

    @FXML
    private JFXRadioButton yesButton;

    @FXML
    private Pane CreditCardWindow;
    @FXML
    private JFXTextField creditCardNumbertxt;

    @FXML
    private JFXTextField experationDatetxt;

    @FXML
    private JFXTextField CVVtxt;

    @FXML
    private ImageView addCardButton;
    @FXML
    private Tooltip typeTT;
    @FXML
    private ImageView CostumerTypeInfo;
    @FXML
    private ImageView PurchasePlanInffo;
    @FXML
    private ImageView ServicePlanInfo;
    @FXML
    private ImageView CreditCardLinkTT;


    private ObservableList<String> CostumerType = FXCollections.observableArrayList("Private", "Company");

    private ObservableList<String> ServicePlanType = FXCollections.observableArrayList("Exclusive", "Multiple Stations");

    private ObservableList<String> GasType = FXCollections.observableArrayList("Gasoline-95", "Diesel", "Scooter Fuel");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formValidation = FormValidation.getValidator();
        PersonalInfoValidation();
        CreditCardWindow.setVisible(false);
        CreditCardWindow.setDisable(true);
        tempVehicleArray = new ArrayList<Vehicle>();
        tempCreditCard = null;
        CostumerIDtxt.clear();
        FirstNametxt.clear();
        LastNametxt.clear();
        EmailAdresstxt.clear();
        creditCardNumbertxt.clear();
        experationDatetxt.clear();
        CVVtxt.clear();
        VehicleIDtxt.clear();
        VehicleInformationPane.setVisible(false);
        VehicleTable.getItems().clear();
        typeTT = createToolTip("There are two Customer Type, Private and Company, you may chose one.");
        Tooltip.install(CostumerTypeInfo, typeTT);
        Tooltip.install(PurchasePlanInffo, createToolTip("Chose weather you have purchase plan or not"));
        Tooltip.install(ServicePlanInfo, createToolTip("Chose your service plan type"));
        Tooltip.install(CreditCardLinkTT, createToolTip("You can add your credit card info by clicking this link. "));


        //
        //
        mainPane.setVisible(false);
        mainPane.setDisable(true);

        vehicleMangTAB.setDisable(true);
        planInfoTAB.setDisable(true);

        //disable vehicle information window
        VehicleInformationPane.setVisible(false);

        GasTypeChoiseBox.setItems(GasType);
        CostumertypeChoiceBox.setItems(CostumerType);
        ServicePlanChoiseBox.setItems(ServicePlanType);

    }


    @FXML
    void addNewCostumerOnClick(MouseEvent event) {
        myController.getTempCostumer();
        mainPane.setVisible(true);
        mainPane.setDisable(false);
        tempVehicleArray = new ArrayList<Vehicle>();
        tempCreditCard = null;
        CostumerIDtxt.clear();
        FirstNametxt.clear();
        LastNametxt.clear();
        EmailAdresstxt.clear();
        creditCardNumbertxt.clear();
        experationDatetxt.clear();
        CVVtxt.clear();
        VehicleIDtxt.clear();
        VehicleInformationPane.setVisible(false);
        VehicleTable.getItems().clear();
    }

    @FXML
    void ClickSaveVehicleButton(MouseEvent event) {
        VehicleTable.setEditable(true);
        /**
         * Here i have to validate Vehicle information before adding this vehicle.
         *
         * have to add msg to client - validation = true = successes msg
         * ELSE: failed msg and do no continue to add that vehicle!
         */
        Vehicle vehicle = new Vehicle(VehicleIDtxt.getText(), GasTypeChoiseBox.getValue());
        tempVehicleArray.add(vehicle);

        VehicleIdColom.setCellValueFactory(new PropertyValueFactory<>("VehicleID"));
        GasTypeColom.setCellValueFactory(new PropertyValueFactory<>("GasType"));

        ObservableList<Vehicle> data = FXCollections.observableArrayList(tempVehicleArray);
        VehicleTable.setItems(data);
    }

    @FXML
    void ClickFinishButton(MouseEvent event) {
        Costumer tempCos = myController.getTempCostumer();
        if (yesButton.isSelected())
            tempCos.setPurchasePlan(true);
        else
            tempCos.setPurchasePlan(false);

        tempCos.setCostumerType(CostumertypeChoiceBox.getSelectionModel().getSelectedItem().toString());
        tempCos.setServicePlan(ServicePlanChoiseBox.getSelectionModel().getSelectedItem().toString());
        tempCos.setCostumerVehicle(tempVehicleArray);
        tempCos.setCostumerCreditCard(tempCreditCard);
        myController.setCostumerInDB(tempCos);
        mainPane.setVisible(false);
        mainPane.setDisable(true);
        planInfoTAB.setDisable(true);
        personalInfoTAB.setDisable(false);
        vehicleMangTAB.getTabPane().getSelectionModel().selectPrevious();
        vehicleMangTAB.getTabPane().getSelectionModel().selectPrevious();
    }


    @FXML
    void addCreditCardLinkOnClick(MouseEvent event) {

        if (creditCardNumbertxt.getText().isEmpty() || experationDatetxt.getText().isEmpty() || CVVtxt.getText().isEmpty()) {
            ErrorAlert.setTitle("Credit Card Fields Error");
            ErrorAlert.setHeaderText("Please insert all Credit Card Information.");
            ErrorAlert.showAndWait();
        } else {
            tempCreditCard = new CreditCard(null, creditCardNumbertxt.getText(), experationDatetxt.getText(), CVVtxt.getText());
            CreditCardWindow.setVisible(false);
            CardClickFlag = true;
        }
    }

    @FXML
    void setVehicleInfoVisible(MouseEvent event) {
        VehicleInformationPane.setVisible(true);
    }

    @FXML
    void creditCardLinkOnClick(MouseEvent event) {
        if (CreditCardWindow.isVisible()) {
            CreditCardWindow.setDisable(true);
            CreditCardWindow.setVisible(false);
        } else {
            CreditCardWindow.setDisable(false);
            CreditCardWindow.setVisible(true);
        }
    }


    @FXML
    void FirstForwardButtonOnClick(MouseEvent event) {

        if (CostumerIDtxt.getText().isEmpty() || FirstNametxt.getText().isEmpty() || LastNametxt.getText().isEmpty() || EmailAdresstxt.getText().isEmpty()) {//check fields
            ErrorAlert.setTitle("Fields Error");
            ErrorAlert.setHeaderText("Please fill all require information");
            ErrorAlert.showAndWait();
        } else {
            if (isCostumerExist()) { //check if costumer is on system already
                ErrorAlert.setTitle("Costumer ID already Exists");
                ErrorAlert.setHeaderText("Please Chose Different ID");
                ErrorAlert.showAndWait();
            } else {//build costumer
                Costumer costumer = new Costumer(Integer.parseInt(CostumerIDtxt.getText()), CostumerIDtxt.getText(), "", FirstNametxt.getText(),
                        LastNametxt.getText(), EmailAdresstxt.getText(), null, true, null, null);

                if (CardClickFlag)
                    if (CreditCardWindow.isVisible()) {//have to popUp User
                        ErrorAlert.setTitle("Credit Card Fields Error");
                        ErrorAlert.setHeaderText("Please insert all Credit Card Information.");
                        ErrorAlert.showAndWait();
                    } else {
                        tempCreditCard.setCardOwner(costumer);
                        costumer.setCostumerCreditCard(tempCreditCard);
                    }
                myController.setCostumerFirstPhase(costumer);
                vehicleMangTAB.setDisable(false);
                personalInfoTAB.setDisable(true);
                vehicleMangTAB.getTabPane().getSelectionModel().selectNext();
            }
        }


    }

    @FXML
    void SecoundForwardButtonOnClick(MouseEvent event) {
        myController.setCostumerSecoundPhase(tempVehicleArray);
        planInfoTAB.setDisable(false);
        vehicleMangTAB.setDisable(true);
        vehicleMangTAB.getTabPane().getSelectionModel().selectNext();
    }

    @FXML
    void FirstBackwardButtonOnClick(MouseEvent event) {
        personalInfoTAB.setDisable(false);
        vehicleMangTAB.setDisable(true);
        vehicleMangTAB.getTabPane().getSelectionModel().selectPrevious();
    }

    @FXML
    void SecoundBackwardButtonOnClick(MouseEvent event) {
        planInfoTAB.setDisable(true);
        vehicleMangTAB.setDisable(false);
        vehicleMangTAB.getTabPane().getSelectionModel().selectPrevious();
    }


    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void RemoveSelectedVe(MouseEvent event) {
        tempVehicleArray.remove(VehicleTable.getSelectionModel().getSelectedItem());
        ObservableList<Vehicle> data = FXCollections.observableArrayList(tempVehicleArray);
        VehicleTable.setItems(data);
    }

    public boolean isCostumerExist() {
        for (Costumer cos : allDBCostumerArray) {
            if (cos.getID().toString().equals(CostumerIDtxt.getText()))
                return true;
        }
        return false;
    }

    private void PersonalInfoValidation() {
        //costumer ID field check
        formValidation.isDoubleNumberValidation(CostumerIDtxt, "Costumer ID");
        formValidation.maxLengthValidation(CostumerIDtxt, "Costumer ID", 9);
        formValidation.minLengthValidationShort(CostumerIDtxt, "Costumer ID", 9);
        //have to add minimum 9 ID len

        //first Name field check
        formValidation.isContainsOnlyLetters(FirstNametxt, "First Name");
        formValidation.isContainsOnlyLetters(FirstNametxt, "First Name");

        //last Name field check
        formValidation.isContainsOnlyLetters(LastNametxt, "Last Name");
        formValidation.isContainsOnlyLetters(LastNametxt, "Last Name");

        //email adress field check
        formValidation.emailAddressValidation(EmailAdresstxt, "Email Adress");
        //credit card field check
        formValidation.isDoubleNumberValidation(creditCardNumbertxt, "Card number");
        formValidation.maxLengthValidation(creditCardNumbertxt, "Card number", 16);
        formValidation.minLengthValidationShort(creditCardNumbertxt, "Card number", 16);
        formValidation.isDoubleNumberValidation(experationDatetxt, "failed");
        formValidation.isDoubleNumberValidation(CVVtxt, "CVV");
        formValidation.maxLengthValidation(CVVtxt, "CVV", 3);
        formValidation.minLengthValidationShort(CVVtxt, "CVV", 3);
        //Vehicle fields check
        formValidation.isDoubleNumberValidation(VehicleIDtxt, "Vehicle ID");
        formValidation.maxLengthValidation(VehicleIDtxt, "Vehicle ID", 6);
        formValidation.minLengthValidationShort(VehicleIDtxt, "Vehicle ID", 6);


    }

    public void setTempCreditCard(CreditCard tempCreditCard) {
        this.tempCreditCard = tempCreditCard;
    }

    private Tooltip createToolTip(String htmlStr) {
        Tooltip thisToolTip = new Tooltip();

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.loadContent(htmlStr);

        thisToolTip.setStyle("\n"
                + "    -fx-border-color: black;\n"
                + "    -fx-border-width: 1px;\n"
                + "    -fx-font: normal bold 1pt \"Times New Roman\" ;\n"
                + "    -fx-background-color: #2d4578;\n"
                + "    -fx-text-fill: black;\n"
                + "    -fx-background-radius: 20;\n"
                + "    -fx-border-radius: 15px;\n"
                + "    -fx-opacity: 1.0;");


        thisToolTip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        thisToolTip.setGraphic(browser);
        thisToolTip.setAutoHide(false);
        thisToolTip.setMaxWidth(250);
        thisToolTip.setMaxHeight(100);
        thisToolTip.setGraphicTextGap(0.0);

        return thisToolTip;
    }


    public ArrayList<Costumer> getAllDBCostumerArray() {
        return allDBCostumerArray;
    }

    public void setAllDBCostumerArray(ArrayList<Costumer> allDBCostumerArray) {
        this.allDBCostumerArray = allDBCostumerArray;
    }
}
