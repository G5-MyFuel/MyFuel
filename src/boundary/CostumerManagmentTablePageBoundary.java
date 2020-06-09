package boundary;

import Contollers.CostumerManagementController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.assets.SqlQueryType;
import entity.Costumer;
import entity.EditingCell;
import entity.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CostumerManagmentTablePageBoundary implements Initializable {


    private ArrayList<Vehicle> Vehicles;
    private ArrayList<Costumer> costumers = new ArrayList<Costumer>();
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
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
    private TableColumn CostumerIDCol;

    @FXML
    private TableColumn firstNameCol;

    @FXML
    private TableColumn LastNameCol;

    @FXML
    private TableColumn EmailAdressCol;

    @FXML
    private TableColumn CostumerTypeCol;

    @FXML
    private TableColumn ServicePlanCol;

    @FXML
    private TableColumn PurchasePlanCol;

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





    private JFXComboBox<String> CostumertypeChoiceBox = new JFXComboBox<>();


    private JFXComboBox<String> ServicePlanChoiseBox;

    private ObservableList<String> CostumerType = FXCollections.observableArrayList("Private", "Company");
    private ObservableList<String> ServicePlanType = FXCollections.observableArrayList("Exclusive", "Multiple Stations");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VehicleInformationPane.setVisible(false);
        myController.getCostumerTable(); //start the process that will ask server to execute quarry and get the table details

    }

    /**
     this method will set the costumer table and the cell edit functions
     when the page initialized.
     */
    public void setCostumerTable(ArrayList<Costumer> cosArray){
        costumers.addAll(cosArray);
        //Create a customer cell factory so that cells can support editing.
        Callback<TableColumn,TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new EditingCell();
            }
        };

        CostumerIDCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("ID"));
        CostumerIDCol.setEditable(false);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("Fname"));
        firstNameCol.setCellFactory(cellFactory);
        LastNameCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("Lname"));
        LastNameCol.setCellFactory(cellFactory);
        EmailAdressCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("EmailAdress"));
        EmailAdressCol.setCellFactory(cellFactory);
        CostumerTypeCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("CostumerType"));
        CostumerTypeCol.setCellFactory(cellFactory);
        ServicePlanCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("servicePlan"));
        ServicePlanCol.setCellFactory(cellFactory);
        PurchasePlanCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("purchasePlan"));
        PurchasePlanCol.setCellFactory(cellFactory);


        ObservableList<Costumer> data = FXCollections.observableArrayList(cosArray);
        CosManageTbale.setItems(data);
        CosManageTbale.setEditable(true);

        //Modifying the firstName property
        firstNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = ((Costumer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                temp.setFname(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_FNAME,temp.getID(),temp.getFname());
            }
        });
        //Modifying the lastName property
        LastNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = ((Costumer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                temp.setLname(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_LNAME,temp.getID(),temp.getLname());
            }
        });
        //Modifying the Emailadress property
        EmailAdressCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = ((Costumer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                temp.setEmailAdress(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_EMAIL,temp.getID(),temp.getEmailAdress());
            }
        });
        //Modifying the CostumerType property
        CostumerTypeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = ((Costumer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                temp.setCostumerType(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_TYPE,temp.getID(),temp.getCostumerType());
            }
        });
        //Modifying the servicePlan property
        ServicePlanCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = ((Costumer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                temp.setServicePlan(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_SERVICE_PLAN,temp.getID(),temp.getServicePlan());
            }
        });
        //Modifying the purchasePlan property
        PurchasePlanCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = ((Costumer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                temp.setPurchasePlan(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_PURCHASE_PLAN,temp.getID(),temp.getPurchasePlan());
            }
        });
    }



    public void setVehicleTable(){

    }
    @FXML
    void ClickSearchCostumer(MouseEvent event) {
        Costumer cos = searchCostumerWithID(CostumerIDtxt.getText());

        if(CostumerIDtxt.getText().isEmpty()){
            ObservableList<Costumer> data = FXCollections.observableArrayList(costumers);
            CosManageTbale.setItems(data);
        }
       else if( cos != null){
           ObservableList<Costumer> data = FXCollections.observableArrayList(cos);
           CosManageTbale.setItems(data);
        }
       else{
           ErrorAlert.setTitle("Internal Error");
           ErrorAlert.setHeaderText("Costumer not found.");
           ErrorAlert.showAndWait();
       }

    }



    @FXML
    void ClickFinishButton(MouseEvent event) {

    }

    @FXML
    void ClickSaveVehicleButton(MouseEvent event) {

    }

    @FXML
    void onEditClick(ActionEvent event) {
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


    private Costumer searchCostumerWithID(String costumerID) {
        for (Costumer cos : costumers) {
            if (cos.getID().toString().equals(costumerID))
                return cos;
        }
        return null;
    }
}
