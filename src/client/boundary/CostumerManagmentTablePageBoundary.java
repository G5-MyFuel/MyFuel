package client.boundary;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.deploy.security.SelectableSecurityManager;
import common.entity.Costumer;
import common.entity.CreditCard;
import common.entity.Vehicle;
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

    private ArrayList<Costumer> cosArray;
    private ArrayList<Vehicle> Vehicles;


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
        cosArray = new ArrayList<Costumer>();
        Vehicles = new ArrayList<Vehicle>();
        Vehicles.add(new Vehicle("1321321", "Gasoline"));
        Vehicles.add(new Vehicle("333333", "Disel"));
        cosArray.add(new Costumer(1231321, "Aa1234567", 1, "FirstName", "LastName", "email adress", new CreditCard(), true, Vehicles.get(0), "multiple Stations"));
        cosArray.add(new Costumer(32133, "Aa1234567", 1, "FirstName2", "LastName2", "email adress2", new CreditCard(), true, Vehicles.get(1), "multiple Stations"));
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

        ArrayList<Vehicle> tempV = new ArrayList<Vehicle>();
        boolean flag = false;
        for (int i = 0; i < cosArray.size(); i++) {
            if (cosArray.get(i).getID().toString().equals(VehicleSearchCosIDtxt.getText())) {
                tempV = cosArray.get(i).getCostumerVehicle();
                flag = true;
            }
        }
        if(!flag)
            System.out.println("Costumer not found");
        else{
            VehicleIDCol.setCellValueFactory(new PropertyValueFactory<>("VehicleID"));
            GasTypeCol.setCellValueFactory(new PropertyValueFactory<>("GasType"));


            ObservableList<Vehicle> data = FXCollections.observableArrayList(tempV);
            VehicleTable.setItems(data);
        }

    }

    @FXML
    void handleClicks(ActionEvent event) {

    }
}
