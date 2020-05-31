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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.Clock;
import java.util.ResourceBundle;


/**
 * @author itay
 * @see CustomerRegistrationLogic - the form's logic class
 */

public class CustomerRegistrationController implements Initializable {

    private static CustomerRegistrationController Instance = null;
    private CustomerRegistrationLogic CustomerRegistrationLogic;
    private FormValidation formValidation; //daniel implementation have to look before use** ~~~~~~~~~~~~~

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


    private ObservableList<String> CostumerType = FXCollections.observableArrayList("Private", "Company");

    private ObservableList<String> ServicePlanType = FXCollections.observableArrayList("EXLUSIVE", "MULTIPLE_STATIONS");

    private ObservableList<String> GasType = FXCollections.observableArrayList("Solar", "Benzin");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.CustomerRegistrationLogic = CustomerRegistrationLogic.getInstance();
        //
        //
        mainPane.setVisible(false);
        mainPane.setDisable(true);

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
    public CustomerRegistrationController getInstance() {
        if (Instance == null)
            Instance = new CustomerRegistrationController();
        return Instance;
    }

    @FXML
    void addCostumerOnClick(MouseEvent event) {
        mainPane.setVisible(true);
        mainPane.setDisable(false);
    }

    @FXML
    void ClickSaveVehicleButton(MouseEvent event) {
        CustomerRegistrationLogic.createVehicle(VehicleIDtxt.getText(),GasTypeChoiseBox.getValue());
        ObservableList<Vehicle> VehicleToTbale = FXCollections.observableArrayList(CustomerRegistrationLogic.getCostumerVehicle());


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
    void forwardButtonOnClick(MouseEvent event) {
        vehicleMangTAB.getTabPane().getSelectionModel().selectNext();
    }

    @FXML
    void backwardButtonOnClick(MouseEvent event) {
        vehicleMangTAB.getTabPane().getSelectionModel().selectPrevious();
    }

    @FXML
    void handleClicks(ActionEvent event) {

    }


}
