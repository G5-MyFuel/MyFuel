package client.boundary;

import  client.logic.*;
import client.logic.FormValidation;
import client.logic.NewPurchaseFuelForHomeHeatingLogic;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.entity.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author itay
 * @see CustomerRegistrationFXML1Logic - the form's logic class
 */

public class CustomerRegistrationFXML1Controller {

    private static CustomerRegistrationFXML1Controller Instance = null;
    private CustomerRegistrationFXML1Logic CustomerRegistrationFXML1Logic;
    private FormValidation formValidation; //daniel implementation have to look before use** ~~~~~~~~~~~~~

    /*Gui variables:
    * */

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnCustomers;

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
    private JFXComboBox<?> GasTypeChoiseBox;

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


    private ObservableList<String> CostumerType= FXCollections.observableArrayList("Private", "Regular");

   @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.CustomerRegistrationFXML1Logic = CustomerRegistrationFXML1Logic.getInstance();
        //
       //
       //


       CostumertypeChoiceBox.setItems(CostumerType);

    }

    /**
     * CustomerRegistrationFXML1Controller Instance getter using SingleTone DesignPatterns
     * @return Instance of controller class
     */
    public CustomerRegistrationFXML1Controller getInstance() {
        if (Instance == null)
            Instance = new CustomerRegistrationFXML1Controller();
        return Instance;
    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

}
