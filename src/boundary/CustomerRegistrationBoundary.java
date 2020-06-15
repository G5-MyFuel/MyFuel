package boundary;

import Contollers.CustomerRegistrationController;
import Contollers.FormValidation;
import Contollers.PagingController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import common.assets.ProjectPages;
import entity.Costumer;
import entity.CreditCard;
import entity.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class CustomerRegistrationBoundary implements DataInitializable {

    private ArrayList<Vehicle> tempVehicleArray = new ArrayList<>();
    private ArrayList<Vehicle> allVehicleArray;
    private CreditCard tempCreditCard = null;

    private CustomerRegistrationController myController = new CustomerRegistrationController(this);
    private FormValidation formValidation;
    private boolean CardClickFlag;
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    private ArrayList<Costumer> allDBCostumerArray;


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
    private JFXComboBox<String> ServicePlanChoiseBox;
    @FXML
    private Pane mainPane;
    @FXML
    private TableColumn<Vehicle, String> VehicleIdColom;
    @FXML
    private TableColumn<Vehicle, String> GasTypeColom;
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
    private Tooltip typeTT;
    @FXML
    private ImageView CostumerTypeInfo;
    @FXML
    private ImageView PurchasePlanInffo;
    @FXML
    private ImageView ServicePlanInfo;
    @FXML
    private ImageView CreditCardLinkTT;

    /*
    Initialize ObservableList in order to display does strings
    with combo box object.
     * * */
    private ObservableList<String> CostumerType = FXCollections.observableArrayList("Private", "Company");

    private ObservableList<String> ServicePlanType = FXCollections.observableArrayList("Exclusive", "Multiple Stations");

    private ObservableList<String> GasType = FXCollections.observableArrayList("Gasoline-95", "Diesel", "Scooter Fuel");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formValidation = FormValidation.getValidator();
        PersonalInfoValidation();
        //
        tempVehicleArray = new ArrayList<Vehicle>();
        //clear all fields section:
        CostumerIDtxt.clear();
        FirstNametxt.clear();
        LastNametxt.clear();
        EmailAdresstxt.clear();
        VehicleIDtxt.clear();
        VehicleTable.getItems().clear();
        //
        //Tooltip initialize Section:
        Tooltip.install(CostumerTypeInfo, createToolTip("There are two Customer Type, Private and Company, you may chose one."));
        Tooltip.install(PurchasePlanInffo, createToolTip("Chose weather you have purchase plan or not"));
        Tooltip.install(ServicePlanInfo, createToolTip("Chose your service plan type"));
        Tooltip.install(CreditCardLinkTT, createToolTip("You can add your credit card info by clicking this link. "));
        //
        mainPane.setVisible(false);
        mainPane.setDisable(true);
        VehicleInformationPane.setVisible(false);
        vehicleMangTAB.setDisable(true);
        planInfoTAB.setDisable(true);
        //
        //set Observable items into combo box:
        GasTypeChoiseBox.setItems(GasType);
        CostumertypeChoiceBox.setItems(CostumerType);
        ServicePlanChoiseBox.setItems(ServicePlanType);
        //
        //set vehicle table cells
        VehicleIdColom.setCellValueFactory(new PropertyValueFactory<>("VehicleID"));
        GasTypeColom.setCellValueFactory(new PropertyValueFactory<>("GasType"));
        //
        CardClickFlag = false;
        //get all costumer from DB for validations:
        myController.getCostumerTable();
        //
        mainPane.setVisible(true);
        mainPane.setDisable(false);
        tempVehicleArray.clear();
        //clear all fields section:
        CostumerIDtxt.clear();
        FirstNametxt.clear();
        LastNametxt.clear();
        EmailAdresstxt.clear();
        VehicleIDtxt.clear();
        VehicleInformationPane.setVisible(false);
        VehicleTable.getItems().clear();
        CardClickFlag = false;
        tempCreditCard = null;
    }

    @Override
    public void initData(Object data) {
        if(data instanceof String)
            myController.setCompanyName((String)data);
    }


    /**
     * This method saves the vehicle information after clicking save button
     * in temp array and display that vehicle in vehicle table.
     */
    @FXML
    void ClickSaveVehicleButton(MouseEvent event) {
        VehicleTable.setEditable(true);
        if (isVehicleExistInDb() || isVehicleExistInTempVehicleArr()) {//in case of vehicle id error
            ErrorAlert.setTitle("Vehicle ID Error");
            ErrorAlert.setHeaderText("Vehicle ID exists in system");
            ErrorAlert.showAndWait();
        } else {
            Vehicle vehicle = new Vehicle(CostumerIDtxt.getText(), VehicleIDtxt.getText(), GasTypeChoiseBox.getValue());
            tempVehicleArray.add(vehicle);
            ObservableList<Vehicle> data = FXCollections.observableArrayList(tempVehicleArray);
            VehicleTable.setItems(data);
        }
    }

    /**
     * This method saves the temp costumer i created in the phases
     * into the DB.
     */
    @FXML
    void ClickFinishButton(MouseEvent event) {
        Costumer tempCos = myController.getTempCostumer();
        if (yesButton.isSelected())
            tempCos.setPurchasePlan("true");
        else
            tempCos.setPurchasePlan("false");
        //set costumer final details.
        tempCos.setCostumerType(CostumertypeChoiceBox.getSelectionModel().getSelectedItem());
        tempCos.setServicePlan(ServicePlanChoiseBox.getSelectionModel().getSelectedItem());
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
    void setVehicleInfoVisible(MouseEvent event) {
        VehicleInformationPane.setVisible(true);
    }

    @FXML
    void creditCardLinkOnClick(MouseEvent event) {
        PagingController pc = new PagingController();
        pc.loadAdditionalStage(ProjectPages.CREDIT_CARD_DIALOG_PAGE.getPath(), this);
    }


    @FXML
    void FirstForwardButtonOnClick(MouseEvent event) {

        if (CostumerIDtxt.getText().isEmpty() || FirstNametxt.getText().isEmpty() || LastNametxt.getText().isEmpty() || EmailAdresstxt.getText().isEmpty()) {//check fields
            ErrorAlert.setTitle("Fields Error");
            ErrorAlert.setHeaderText("Please fill all require information");
            ErrorAlert.showAndWait();
        } else {
            if (isCostumerExist()) { //check if costumer is on system already
                ErrorAlert.setTitle("ID Error");
                ErrorAlert.setHeaderText("Costumer ID already Exists,\nPlease Chose Different ID.");
                ErrorAlert.showAndWait();
            } else {//build costumer
                Costumer costumer = new Costumer(CostumerIDtxt.getText(), CostumerIDtxt.getText(), "", FirstNametxt.getText(),
                        LastNametxt.getText(), EmailAdresstxt.getText(), null, "true", null, null);
                if (CardClickFlag) {
                    costumer.setCostumerCreditCard(tempCreditCard);
                    if(tempCreditCard != null)
                        tempCreditCard.setCardOwner(costumer);
                }
                myController.setCostumerFirstPhase(costumer);
                myController.getVehicleTable();
                vehicleMangTAB.setDisable(false);
                personalInfoTAB.setDisable(true);
                vehicleMangTAB.getTabPane().getSelectionModel().selectNext();
            }
        }
}

    @FXML
    void SecondForwardButtonOnClick(MouseEvent event) {
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
    void SecondBackwardButtonOnClick(MouseEvent event) {
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

    private boolean isCostumerExist() {
        for (Costumer cos : allDBCostumerArray) {
            if (cos.getUserID().toString().equals(CostumerIDtxt.getText()))
                return true;
        }
        return false;
    }

    private boolean isVehicleExistInDb() {
        for (Vehicle v : allVehicleArray) {
            if (v.getVehicleID().equals(VehicleIDtxt.getText()))
                return true;
        }
        return false;
    }

    private boolean isVehicleExistInTempVehicleArr() {
        for (Vehicle v : tempVehicleArray) {
            if (v.getVehicleID().toString().equals(VehicleIDtxt.getText()))
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

        //Vehicle fields check
        formValidation.isDoubleNumberValidation(VehicleIDtxt, "Vehicle ID");
        formValidation.maxLengthValidation(VehicleIDtxt, "Vehicle ID", 7);
        formValidation.minLengthValidationShort(VehicleIDtxt, "Vehicle ID", 7);

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

    public ArrayList<Vehicle> getAllVehicleArray() {
        return allVehicleArray;
    }

    public void setAllVehicleArray(ArrayList<Vehicle> allVehicleArray) {
        this.allVehicleArray = allVehicleArray;
    }


}
