package boundary;

import Contollers.CustomerRegistrationController;
import Contollers.FormValidation;
import Contollers.PagingController;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import common.assets.ProjectPages;
import entity.Costumer;
import entity.CreditCard;
import entity.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * the class costumer registration boundary
 *
 * @author itay
 * @see CustomerRegistrationBoundary - the form's Boundary class
 */

public class CustomerRegistrationBoundary implements DataInitializable {

    private ArrayList<Vehicle> tempVehicleArray;
    private CreditCard tempCreditCard = null;

    private CustomerRegistrationController myController = new CustomerRegistrationController(this);
    private FormValidation formValidation;
    private boolean CardClickFlag;
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);


    /*Gui variables:
     * * */
    @FXML
    private Tab personalInfoTAB;
    @FXML
    private JFXTextField CostumerIDtxt;
    @FXML
    private JFXTextField FirstNametxt;
    @FXML
    private JFXTextField LastNametxt;
    @FXML
    private JFXTextField EmailAdresstxt;
    @FXML
    private Tab vehicleMangTAB;
    @FXML
    private TableView<Vehicle> VehicleTable;
    @FXML
    private JFXTextField VehicleIDtxt;
    @FXML
    private Pane VehicleInformationPane;
    @FXML
    private JFXComboBox<String> GasTypeChoiseBox;
    @FXML
    private Tab planInfoTAB;
    @FXML
    private JFXComboBox<String> CostumertypeChoiceBox;
    @FXML
    private JFXComboBox<String> PurchasePlanChoiseBox;
    @FXML
    private TableColumn<Vehicle, String> VehicleIdColom;
    @FXML
    private TableColumn<Vehicle, String> GasTypeColom;
    @FXML
    private ImageView CostumerTypeInfo;
    @FXML
    private ImageView PurchasePlanInffo;
    @FXML
    private ImageView ServicePlanInfo;
    @FXML
    private ImageView CreditCardLinkTT;
    @FXML
    private ImageView StationsInfo;
    @FXML
    private Pane stationPane;
    @FXML
    private Label choseStationLable;
    @FXML
    private JFXRadioButton PAZbtn;
    @FXML
    private JFXRadioButton YELLOWbtn;
    @FXML
    private JFXRadioButton SONOLbtn;
    @FXML
    private JFXComboBox<String> PricingModelChoiseBox1;
    @FXML
    private VBox exclusiveVbox;
    @FXML
    private VBox MultipleVbox;
    @FXML
    private Label NumberOfVehcles;
    @FXML
    private JFXCheckBox pazCheckBox;
    @FXML
    private JFXCheckBox yellowCheckBox;
    @FXML
    private JFXCheckBox sonolCheckBox;


    /*
    Initialize ObservableList in order to display does strings
    with combo box object.
     * * */
    private ObservableList<String> CostumerType = FXCollections.observableArrayList("Private", "Company");

    private ObservableList<String> PurchasePlanType = FXCollections.observableArrayList("Exclusive", "Multiple Stations", "None");

    private ObservableList<String> GasType = FXCollections.observableArrayList("Gasoline-95", "Diesel", "Scooter Fuel");

    private ObservableList<String> SingelVehicle;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formValidation = new FormValidation();
        giValidation();
        //
        tempVehicleArray = new ArrayList<Vehicle>();
        //
        //Tooltip initialize Section:
        Tooltip.install(StationsInfo, createToolTip("Please Chose Station according to your plan."));
        Tooltip.install(CostumerTypeInfo, createToolTip("There are two Customer Type, Private and Company, you may chose one."));
        Tooltip.install(PurchasePlanInffo, createToolTip("Chose weather you have purchase plan or not"));
        Tooltip.install(ServicePlanInfo, createToolTip("Chose your service plan type"));
        Tooltip.install(CreditCardLinkTT, createToolTip("You can add your credit card info by clicking this link. "));
        //
        VehicleInformationPane.setVisible(false);
        vehicleMangTAB.setDisable(true);
        planInfoTAB.setDisable(true);
        //
        //set Observable items into combo box:
        GasTypeChoiseBox.setItems(GasType);
        CostumertypeChoiceBox.setItems(CostumerType);
        PurchasePlanChoiseBox.setItems(PurchasePlanType);
        //
        //set vehicle table cells
        VehicleIdColom.setCellValueFactory(new PropertyValueFactory<>("VehicleID"));
        GasTypeColom.setCellValueFactory(new PropertyValueFactory<>("GasType"));
        //
        CardClickFlag = false;
        //get all costumer from DB for validations:
        myController.getCostumerTable();
        //
        //clear all fields section:
        VehicleInformationPane.setVisible(false);
        tempCreditCard = null;
        stationPane.setVisible(false);


        PurchasePlanChoiseBox.valueProperty().addListener((composant, oldValue, newValue) -> {
            PAZbtn.setSelected(false);
            YELLOWbtn.setSelected(false);
            SONOLbtn.setSelected(false);
            stationPane.setVisible(true);
            if (PurchasePlanChoiseBox.getValue().equals("Multiple Stations")) {
                exclusiveVbox.setVisible(false);
                MultipleVbox.setVisible(true);
                choseStationLable.setText("You may chose 2 or 3 stations.");
            } else if (PurchasePlanChoiseBox.getValue().equals("Exclusive")) {
                exclusiveVbox.setVisible(true);
                MultipleVbox.setVisible(false);
                ToggleGroup q1 = new ToggleGroup();
                PAZbtn.setToggleGroup(q1);
                YELLOWbtn.setToggleGroup(q1);
                SONOLbtn.setToggleGroup(q1);
                choseStationLable.setText("You may chose only one station.");
            } else {
                stationPane.setVisible(false);
            }
        });
    }

    @Override
    public void initData(Object data) {
    }

    @FXML
    void FirstForwardButtonOnClick(MouseEvent event) {
        if (myController.isCostumerExist(CostumerIDtxt.getText())) { //check if costumer is on system already
            ErrorAlert.setTitle("ID Error");
            ErrorAlert.setHeaderText("Costumer ID already Exists,\nPlease Chose Different ID.");
            ErrorAlert.showAndWait();
        } else if (!validateInputFirstPhase()) {//check fields
            ErrorAlert.setTitle("Fields Error");
            ErrorAlert.setHeaderText("Make sure all fields are correct");
            ErrorAlert.showAndWait();
        } else {//build costumer
            Costumer costumer = new Costumer(CostumerIDtxt.getText(), CostumerIDtxt.getText(), "", FirstNametxt.getText(),
                    LastNametxt.getText(), EmailAdresstxt.getText(), null, "", null, null);
            if (CardClickFlag) {
                costumer.setCostumerCreditCard(tempCreditCard);
                if (tempCreditCard != null)
                    tempCreditCard.setCardOwner(costumer);
            }
            myController.setCostumerFirstPhase(costumer);
            myController.getVehicleTable();
            vehicleMangTAB.setDisable(false);
            personalInfoTAB.setDisable(true);
            vehicleMangTAB.getTabPane().getSelectionModel().selectNext();
        }
    }


    @FXML
    void SecondForwardButtonOnClick(MouseEvent event) {
        myController.setCostumerSecoundPhase(tempVehicleArray);
        planInfoTAB.setDisable(false);
        vehicleMangTAB.setDisable(true);
        vehicleMangTAB.getTabPane().getSelectionModel().selectNext();
        Integer numberOfCars = tempVehicleArray.size();
        if (numberOfCars > 1)
            SingelVehicle = FXCollections.observableArrayList("Casual fueling", "Regular monthly subscription (multiple)");
        else
            SingelVehicle = FXCollections.observableArrayList("Casual fueling", "Regular monthly subscription (single)", "Full monthly subscription");
        PricingModelChoiseBox1.setItems(SingelVehicle);
        NumberOfVehcles.setText("The Total number of vehicles that " + FirstNametxt.getText() + " have is: " + numberOfCars.toString());
    }

    /**
     * This method saves the temp costumer i created in the phases
     * into the DB.
     */
    @FXML
    void ClickFinishButton(MouseEvent event) {
        Costumer tempCos = myController.getTempCostumer();
        boolean flag = false;
        ArrayList<String> stations = new ArrayList<>();
        stations.add("NULL");
        stations.add("NULL");
        stations.add("NULL");

        tempCos.setCostumerVehicle(tempVehicleArray);
        tempCos.setCostumerCreditCard(tempCreditCard);
        if (tempCos.getPurchasePlan().equals("Exclusive")) {
            if (YELLOWbtn.isSelected())
                stations.add(0, "YELLOW");
            if (PAZbtn.isSelected())
                stations.add(1, "PAZ");
            if (SONOLbtn.isSelected())
                stations.add(2, "SONOL");
        } else {
            int counter = 0;
            if (yellowCheckBox.isSelected()) {
                stations.add(0, "YELLOW");
                counter++;
            }
            if (pazCheckBox.isSelected()) {
                stations.add(1, "PAZ");
                counter++;
            }
            if (sonolCheckBox.isSelected()) {
                stations.add(2, "SONOL");
                counter++;
            }
            if (counter < 2) {
                ErrorAlert.setTitle("Internal Error");
                ErrorAlert.setHeaderText("Your plan is Multiple Stations.\nplease select 2 or 3 stations.");
                ErrorAlert.showAndWait();
                flag = true;
            }
        }
        if (flag || CostumertypeChoiceBox.getSelectionModel().isEmpty() || PurchasePlanChoiseBox.getSelectionModel().isEmpty() || PricingModelChoiseBox1.getSelectionModel().isEmpty()) {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("One or more of the fields is empty.");
            ErrorAlert.showAndWait();
        } else {
            //set costumer final details.
            tempCos.setCostumerType(CostumertypeChoiceBox.getSelectionModel().getSelectedItem());
            tempCos.setPurchasePlan(PurchasePlanChoiseBox.getSelectionModel().getSelectedItem());
            tempCos.setPricingModel(PricingModelChoiseBox1.getValue());
            tempCos.setFuelCompany(stations);
            myController.setCostumerInDB(tempCos);
            planInfoTAB.setDisable(true);
            personalInfoTAB.setDisable(false);
            vehicleMangTAB.getTabPane().getSelectionModel().selectPrevious();
            vehicleMangTAB.getTabPane().getSelectionModel().selectPrevious();
            //clear all fields section:
            CostumerIDtxt.clear();
            FirstNametxt.clear();
            LastNametxt.clear();
            EmailAdresstxt.clear();
            VehicleIDtxt.clear();
            VehicleTable.getItems().clear();
            CardClickFlag = false;
            tempVehicleArray.clear();
        }

    }

    /**
     * This method saves the vehicle information after clicking save button
     * in temp array and display that vehicle in vehicle table.
     */
    @FXML
    void ClickSaveVehicleButton(MouseEvent event) {
        VehicleTable.setEditable(true);
        if (myController.isVehicleExistInDb(VehicleIDtxt.getText()) || isVehicleExistInTempVehicleArr()) {//in case of vehicle id error
            ErrorAlert.setTitle("Vehicle ID Error");
            ErrorAlert.setHeaderText("Vehicle ID exists in system");
            ErrorAlert.showAndWait();
        } else if (validateVehicleIDField()) {
            Vehicle vehicle = new Vehicle(CostumerIDtxt.getText(), VehicleIDtxt.getText(), GasTypeChoiseBox.getValue());
            tempVehicleArray.add(vehicle);
            ObservableList<Vehicle> data = FXCollections.observableArrayList(tempVehicleArray);
            VehicleTable.setItems(data);
        } else {
            ErrorAlert.setTitle("Fields Error");
            ErrorAlert.setHeaderText("Please make sure Vehicle ID is correct.");
            ErrorAlert.showAndWait();
        }
    }

    @FXML
    void setVehicleInfoVisible(MouseEvent event) {
        VehicleInformationPane.setVisible(true);
    }

    @FXML
    void creditCardLinkOnClick(MouseEvent event) {
        PagingController pc = new PagingController();
        pc.loadAdditionalStage(ProjectPages.CREDIT_CARD_DIALOG_PAGE.getPath(), this);
    }

    @FXML
    void FirstBackwardButtonOnClick(MouseEvent event) {
        personalInfoTAB.setDisable(false);
        vehicleMangTAB.setDisable(true);
        vehicleMangTAB.getTabPane().getSelectionModel().selectPrevious();
    }

    @FXML
    void SecondBackwardButtonOnClick(MouseEvent event) {
        planInfoTAB.setDisable(true);
        vehicleMangTAB.setDisable(false);
        vehicleMangTAB.getTabPane().getSelectionModel().selectPrevious();
    }

    @FXML
    void RemoveSelectedVe(MouseEvent event) {
        tempVehicleArray.remove(VehicleTable.getSelectionModel().getSelectedItem());
        ObservableList<Vehicle> data = FXCollections.observableArrayList(tempVehicleArray);
        VehicleTable.setItems(data);
    }


    private boolean isVehicleExistInTempVehicleArr() {
        for (Vehicle v : tempVehicleArray) {
            if (v.getVehicleID().toString().equals(VehicleIDtxt.getText()))
                return true;
        }
        return false;
    }

    public void setTempCreditCard(CreditCard tempCreditCard) {
        this.tempCreditCard = tempCreditCard;
    }

    public void setCardClickFlag(boolean cardClickFlag) {
        CardClickFlag = cardClickFlag;
    }

    private Tooltip createToolTip(String htmlStr) {
        Tooltip thisToolTip = new Tooltip();

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.loadContent(htmlStr);

        thisToolTip.setStyle("\n"
               /* + "    -fx-border-color: black;\n"
                + "    -fx-border-width: 1px;\n"
                + "    -fx-font: normal bold 1pt \"Times New Roman\" ;\n"
                + "    -fx-background-color: #2d4578;\n"
                + "    -fx-text-fill: black;\n"
                + "    -fx-background-radius: 20;\n"
                + "    -fx-border-radius: 15px;\n"
                + "    -fx-opacity: 1.0;");*/

                + "-fx-background: rgba(30,30,30);\n"
                + "-fx-text-fill:  white;\n"
                + "-fx-font: normal 1.5pt \"Times New Roman\" ;\n"
                + "-fx-background-color: #2d4578;\n"
                + "-fx-background-radius: 6px;\n"
                + "-fx-text-alignment: center;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-font-weight: bold;\n"
                + "-fx-padding: 0.667em 0.75em 0.600em 0.75em;\n"
                + "-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.5) , 10, 0.0 , 0 , 3 );\n"
                + "-fx-font-size: 3em;");

        thisToolTip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        thisToolTip.setGraphic(browser);
        thisToolTip.setAutoHide(false);
        thisToolTip.setMaxWidth(250);
        thisToolTip.setMaxHeight(100);
        thisToolTip.setGraphicTextGap(0.0);

        return thisToolTip;
    }


    private void giValidation() {
        //costumer ID field check
        formValidation.isOnlyNumbers(CostumerIDtxt, "Costumer ID");
        formValidation.ExactlyInLengthValidation(CostumerIDtxt, "Costumer ID", 9);
        formValidation.isEmptyFieldValidation(CostumerIDtxt, "Costumer ID");

        //first Name field check
        formValidation.isContainsOnlyLetters(FirstNametxt, "First Name");
        formValidation.isEmptyFieldValidation(FirstNametxt, "First Name");

        //last Name field check
        formValidation.isContainsOnlyLetters(LastNametxt, "Last Name");
        formValidation.isEmptyFieldValidation(LastNametxt, "Last Name");


        //email adress field check
        formValidation.emailAddressValidation(EmailAdresstxt, "Email Address");
        formValidation.isEmptyFieldValidation(EmailAdresstxt, "Email Address");

        //Vehicle fields check
        formValidation.isOnlyNumbers(VehicleIDtxt, "Vehicle ID");
        formValidation.ExactlyInLengthValidation(VehicleIDtxt, "Vehicle ID", 7);
        formValidation.isEmptyFieldValidation(VehicleIDtxt, "Vehicle ID");


    }

    private boolean validateVehicleIDField() {
        if (formValidation.isEmptyField() && formValidation.isExactlyInLength() && formValidation.isOnlyNumbers())
            return true;
        else
            return false;
    }

    private boolean validateInputFirstPhase() {
        if (formValidation.isOnlyNumbers() && formValidation.isExactlyInLength() && formValidation.isOnlyLetters() && formValidation.isEmailAddress() && formValidation.isEmptyField())
            return true;
        else
            return false;
    }

}
