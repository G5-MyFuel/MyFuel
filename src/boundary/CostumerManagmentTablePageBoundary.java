package boundary;

import Contollers.CostumerManagementController;
import client.ClientConsole;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CostumerManagmentTablePageBoundary implements Initializable {


    private ArrayList<Vehicle> Vehicles;

    /** The supervisor boundary controller. */
    private CostumerManagementController myController = new CostumerManagementController(this);


    @FXML
    private Button btnOverview;

    @FXML
    private Button btnCustomers1;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnSignout;

    @FXML
    private JFXTextField CostumerIDtxt;

    @FXML
    private Button FinishButton;

    @FXML
    private Pane VehicleInformationPane;

    @FXML
    private Button saveVehicleButton;

    @FXML
    private JFXTextField VehicleIDtxt;

    @FXML
    private JFXComboBox<?> GasTypeChoiseBox;

    @FXML
    private Button FinishButton1;

    @FXML
    private TableColumn<Costumer, String> CostumerIDCol;

    @FXML
    private TableColumn<Costumer, String> firstNameCol;

    @FXML
    private TableColumn<Costumer, String> LastNameCol;

    @FXML
    private TableColumn<Costumer, String> EmailAdressCol;

    @FXML
    private TableColumn<Costumer, String> CostumerTypeCol;

    @FXML
    private TableColumn<Costumer, String> ServicePlanCol;

    @FXML
    private TableColumn<Costumer, String> PurchasePlanCol;

    @FXML
    private TableView<Costumer> CosManageTbale;

    @FXML
    private TableColumn<Vehicle, String> VehicleIDCol;

    @FXML
    private TableColumn<Vehicle, String> GasTypeCol;

    @FXML
    private TableView<Vehicle> VehicleTable;

    @FXML
    private JFXTextField VehicleSearchCosIDtxt;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VehicleInformationPane.setVisible(false);
        myController.getCostumerTable(); //start the process that will ask server to execute quarry and get the table details
    }

    /**
     this method will set the costumer table
     when we will initialize the page.
     */
    public void setCostumerTable(ArrayList<Costumer> cosArray){
        //col oms parameters
        CostumerIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("Fname"));
        LastNameCol.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        EmailAdressCol.setCellValueFactory(new PropertyValueFactory<>("EmailAdress"));
        CostumerTypeCol.setCellValueFactory(new PropertyValueFactory<>("CostumerType"));
        ServicePlanCol.setCellValueFactory(new PropertyValueFactory<>("servicePlan"));
        PurchasePlanCol.setCellValueFactory(new PropertyValueFactory<>("purchasePlan"));
        ObservableList<Costumer> data = FXCollections.observableArrayList(cosArray);
        CosManageTbale.setItems(data);
    }
    public void setVehicleTable(){

    }



    @FXML
    void ClickFinishButton(MouseEvent event) {

    }

    @FXML
    void ClickSaveVehicleButton(MouseEvent event) {

    }

    @FXML
    void onEditClick(ActionEvent event) {
        System.out.println("need to edit that field");
        CosManageTbale.setEditable(true);
    }

    @FXML
    void ClickSearchCostumerVehicles(MouseEvent event) {



//            VehicleIDCol.setCellValueFactory(new PropertyValueFactory<>("VehicleID"));
//            GasTypeCol.setCellValueFactory(new PropertyValueFactory<>("GasType"));
//
//
//            ObservableList<Vehicle> data = FXCollections.observableArrayList(tempV);
//            VehicleTable.setItems(data);

    }

    @FXML
    void handleClicks(ActionEvent event) {

    }
}
