package client.boundary;

import client.logic.*;
import client.logic.FormValidation;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.entity.Costumer;
import common.entity.CreditCard;
import common.entity.Employee;
import common.entity.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


/**
 * @author itay
 * @see CustomerRegistrationLogic - the form's logic class
 */

public class CustomerRegistrationController implements Initializable {

    private ArrayList<Vehicle> tempVehicleArray;
    private CreditCard tempCreditCard;
    private static CustomerRegistrationController Instance = null;
    private CustomerRegistrationLogic CustomerRegistrationLogic;
    private FormValidation formValidation;

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


    private ObservableList<String> CostumerType = FXCollections.observableArrayList("Private", "Company");

    private ObservableList<String> ServicePlanType = FXCollections.observableArrayList("Exclusive", "Multiple Stations");

    private ObservableList<String> GasType = FXCollections.observableArrayList("Gasoline-95", "Diesel", "Scooter Fuel");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.CustomerRegistrationLogic = CustomerRegistrationLogic.getInstance();
        formValidation = FormValidation.getValidator();
        PersonalInfoValidation();
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

    /**
     * CustomerRegistrationFXML1Controller Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of controller class
     */
    public static CustomerRegistrationController getInstance() {
        if (Instance == null)
            Instance = new CustomerRegistrationController();
        return Instance;
    }

    @FXML
    void addNewCostumerOnClick(MouseEvent event) {
        mainPane.setVisible(true);
        mainPane.setDisable(false);
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
        if (tempVehicleArray.equals(null))
            tempVehicleArray = new ArrayList<Vehicle>();
        tempVehicleArray.add(vehicle);


        VehicleIdColom.setCellValueFactory(new PropertyValueFactory<>("VehicleID"));
        GasTypeColom.setCellValueFactory(new PropertyValueFactory<>("GasType"));
        ObservableList<Vehicle> data = FXCollections.observableArrayList(tempVehicleArray);
        VehicleTable.setItems(data);
    }

    @FXML
    void ClickFinishButton(MouseEvent event) {

    }


    @FXML
    void setVehicleInfoVisible(MouseEvent event) {
        VehicleInformationPane.setVisible(true);
    }

    @FXML
    void creditCardLinkOnClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Payment Window.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Insert Credit Card Details");
        stage.show();
    }

    @FXML
    void FirstForwardButtonOnClick(MouseEvent event) {
        Costumer costumer = new Costumer(Integer.parseInt(CostumerIDtxt.getText()), CostumerIDtxt.getText(), 0, FirstNametxt.getText(), LastNametxt.getText(), EmailAdresstxt.getText(), null, true, null);
        CustomerRegistrationLogic.setCostumerFirstPhase(costumer);
        vehicleMangTAB.setDisable(false);
        personalInfoTAB.setDisable(true);
        vehicleMangTAB.getTabPane().getSelectionModel().selectNext();

    }

    @FXML
    void SecoundForwardButtonOnClick(MouseEvent event) {
        CustomerRegistrationLogic.setCostumerSecoundPhase(tempVehicleArray);
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

    private void PersonalInfoValidation() {
        //costumer ID field check
        formValidation.isDoubleNumberValidation(CostumerIDtxt, "Costumer ID");
        formValidation.maxLengthValidation(CostumerIDtxt, "Costumer ID", 9);
        //have to add minimum 9 ID len

        //first Name field check
        formValidation.isContainsOnlyLetters(FirstNametxt, "First Name");
        formValidation.isContainsOnlyLetters(FirstNametxt, "First Name");

        //last Name field check
        formValidation.isContainsOnlyLetters(LastNametxt, "Last Name");
        formValidation.isContainsOnlyLetters(LastNametxt, "Last Name");

        //email adress field check
        formValidation.emailAddressValidation(EmailAdresstxt, "Email Adress");
    }

}
