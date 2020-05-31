package client.boundary;

import client.logic.*;
import client.logic.FormValidation;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.entity.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.net.URL;
import java.time.Clock;
import java.util.ResourceBundle;


/**
 * @author itay
 * @see CustomerRegistrationLogic - the form's logic class
 */

public class CustomerRegistrationController implements Initializable {

    private static CustomerRegistrationController Instance = null;
    private CustomerRegistrationLogic CustomerRegistrationFXML1Logic;
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
    private SplitPane VehicleInformationSplitPane;

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


    private ObservableList<String> CostumerType = FXCollections.observableArrayList("Private", "Company");

    private ObservableList<String> ServicePlanType = FXCollections.observableArrayList("EXLUSIVE", "MULTIPLE_STATIONS");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.CustomerRegistrationFXML1Logic = CustomerRegistrationFXML1Logic.getInstance();
        //
        //

        //disable vehicle information window
        VehicleInformationSplitPane.setVisible(false);

        CostumertypeChoiceBox.setItems(CostumerType);
        ServicePlanChoiseBox.setItems(ServicePlanType);

    }

    /**
     * CustomerRegistrationFXML1Controller Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of controller class
     */
    public CustomerRegistrationController getInstance() {
        if (Instance == null)
            Instance = new CustomerRegistrationController();
        return Instance;
    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void addVehicleButton(MouseEvent event) {
        VehicleInformationSplitPane.setVisible(true);

    }

    @FXML
    void forwardButtonOnClick(MouseEvent event) {
        vehicleMangTAB.getTabPane().getSelectionModel().selectNext();
    }

    @FXML
    void backwardButtonOnClick(MouseEvent event) {
        vehicleMangTAB.getTabPane().getSelectionModel().selectPrevious();
    }


}
