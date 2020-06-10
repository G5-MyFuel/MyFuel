package boundary;

import Contollers.CostumerManagementController;
import Contollers.PagingController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.assets.ProjectPages;
import common.assets.SqlQueryType;
import entity.Costumer;
import entity.EditingCell;
import entity.Vehicle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.ComboBoxTreeTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CostumerManagmentTablePageBoundary implements Initializable {


    private ArrayList<Vehicle> Vehicles = new ArrayList<>();
    private ArrayList<Costumer> costumers = new ArrayList<Costumer>();
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    /**
     * The supervisor boundary controller.
     */
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
    private JFXTextField OwnerIDtxt1;

    @FXML
    private JFXComboBox<String> GasTypeChoiseBox;

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

    @FXML
    private TreeTableColumn<?, ?> CostumerTypeTreeCol;

    @FXML
    private TreeTableColumn<?, ?> ServicePlanTreeCol;

    @FXML
    private TreeTableColumn<?, ?> PurchasePlanTreeCol;


    private JFXComboBox<String> CostumertypeChoiceBox = new JFXComboBox<>();


    private JFXComboBox<String> ServicePlanChoiseBox;

    private ObservableList<String> CostumerType = FXCollections.observableArrayList("Private", "Company");
    private ObservableList<String> ServicePlanType = FXCollections.observableArrayList("Exclusive", "Multiple Stations");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VehicleInformationPane.setVisible(false);
        myController.getCostumerTable(); //start the process that will ask server to execute quarry and get the table details
        myController.getVehicleTable();
    }

    /**
     * this method will set the costumer table and the cell edit functions
     * when the page initialized.
     */
    public void setCostumerTable(ArrayList<Costumer> cosArray) {
        costumers.addAll(cosArray);

        setColomsCells();
        ObservableList<Costumer> data = FXCollections.observableArrayList(cosArray);
        CosManageTbale.setItems(data);
        CosManageTbale.setEditable(true);

        //Modifying the firstName property
        firstNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = ((Costumer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                temp.setFname(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_FNAME, temp.getID(), temp.getFname());
            }
        });
        //Modifying the lastName property
        LastNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = ((Costumer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                temp.setLname(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_LNAME, temp.getID(), temp.getLname());
            }
        });
        //Modifying the Emailadress property
        EmailAdressCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = ((Costumer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                temp.setEmailAdress(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_EMAIL, temp.getID(), temp.getEmailAdress());
            }
        });
        //Modifying the CostumerType property
        CostumerTypeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = ((Costumer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                temp.setCostumerType(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_TYPE, temp.getID(), temp.getCostumerType());
            }
        });
        //Modifying the servicePlan property
        ServicePlanCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = ((Costumer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                temp.setServicePlan(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_SERVICE_PLAN, temp.getID(), temp.getServicePlan());
            }
        });
        //Modifying the purchasePlan property
        PurchasePlanCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = ((Costumer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                temp.setPurchasePlan(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_PURCHASE_PLAN, temp.getID(), temp.getPurchasePlan());
            }
        });
    }

    private void setColomsCells(){

        //Create a customer cell factory so that cells can support editing.
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new EditingCell();
            }
        };


        PurchasePlanCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Costumer, String>,ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Costumer, String> param) {
                Costumer treeItem = param.getValue();
                Costumer costumer = treeItem;

                String temp = costumer.getPurchasePlan();
                return new SimpleObjectProperty<String>(temp);
            }
        });

        ServicePlanCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Costumer, String>,ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Costumer, String> param) {
                Costumer treeItem = param.getValue();
                Costumer costumer = treeItem;

                String temp = costumer.getServicePlan();
                return new SimpleObjectProperty<String>(temp);
            }
        });
        CostumerTypeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Costumer, String>,ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Costumer, String> param) {
                Costumer treeItem = param.getValue();
                Costumer costumer = treeItem;

                String temp = costumer.getCostumerType();
                return new SimpleObjectProperty<String>(temp);
            }
        });



        CostumerIDCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("ID"));
        CostumerIDCol.setEditable(false);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("Fname"));
        firstNameCol.setCellFactory(cellFactory);
        LastNameCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("Lname"));
        LastNameCol.setCellFactory(cellFactory);
        EmailAdressCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("EmailAdress"));
        EmailAdressCol.setCellFactory(cellFactory);

        CostumerTypeCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("CostumerType"));
        ObservableList<String> CostumerType = FXCollections.observableArrayList("Private", "Company");
        CostumerTypeCol.setCellFactory(ComboBoxTableCell.forTableColumn(CostumerType));

        ServicePlanCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("servicePlan"));
        ObservableList<String> ServicePlanType = FXCollections.observableArrayList("Exclusive", "Multiple Stations");
        ServicePlanCol.setCellFactory(ComboBoxTableCell.forTableColumn(ServicePlanType));

        PurchasePlanCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("purchasePlan"));
        ObservableList<String> purchasePlan = FXCollections.observableArrayList("true","false");
        PurchasePlanCol.setCellFactory(ComboBoxTableCell.forTableColumn(purchasePlan));





    }

    public void setVehicleTable(ArrayList<Vehicle> vArr) {
        VehicleIDCol.setCellValueFactory(new PropertyValueFactory<>("VehicleID"));
        GasTypeCol.setCellValueFactory(new PropertyValueFactory<>("GasType"));
        ObservableList<Vehicle> data = FXCollections.observableArrayList(vArr);
        VehicleTable.setItems(data);
    }


    @FXML
    void ClickSearchCostumer(MouseEvent event) {
        Costumer cos = searchCostumerWithID(CostumerIDtxt.getText());

        if (CostumerIDtxt.getText().isEmpty()) {
            ObservableList<Costumer> data = FXCollections.observableArrayList(costumers);
            CosManageTbale.setItems(data);
        } else if (cos != null) {
            ObservableList<Costumer> data = FXCollections.observableArrayList(cos);
            CosManageTbale.setItems(data);
        } else {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("Costumer not found.");
            ErrorAlert.showAndWait();
        }

    }


    @FXML
    void ClickSaveVehicleButton(MouseEvent event) {
        if (OwnerIDtxt1.getText().isEmpty() || VehicleIDtxt.getText().isEmpty() || GasTypeChoiseBox.getSelectionModel().isEmpty()) {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("One of the required fields is empty.\nPlease insert required information to proceed.");
            ErrorAlert.showAndWait();
        } else if (myController.isVehicleExistInDb(VehicleIDtxt.getText())) {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("Vehicle ID already exists.\nPlease chose different ID.");
            ErrorAlert.showAndWait();
        } else {
            myController.addVehicleToDB(OwnerIDtxt1.getText(), VehicleIDtxt.getText(), GasTypeChoiseBox.getValue());
            Vehicle insertItem = new Vehicle(OwnerIDtxt1.getText(), VehicleIDtxt.getText(), GasTypeChoiseBox.getValue());
            VehicleTable.getItems().add(insertItem);
            VehicleTable.refresh();
        }
    }


    @FXML
    void removeSelectedCostumer() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Remove Costumer");
        alert.setContentText(String.format("Are you sure you want to remove this Costumer?\nonce you click YES Costumer will be removed permanently!"));
        Optional<ButtonType> res = alert.showAndWait();

        if (res.get().getText().equals("Yes")) {
            myController.removeCostumer(CosManageTbale.getSelectionModel().getSelectedItem().getID());
            Costumer selectedItem = CosManageTbale.getSelectionModel().getSelectedItem();
            CosManageTbale.getItems().remove(selectedItem);
            CosManageTbale.refresh();
        }


    }


    @FXML
    void ClickSearchCostumerVehicles(MouseEvent event) {
        Costumer cos = searchCostumerWithID(VehicleSearchCosIDtxt.getText());

        if (VehicleSearchCosIDtxt.getText().isEmpty()) {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("Please insert Costumer ID");
            ErrorAlert.showAndWait();
            VehicleTable.getItems().clear();
        } else if (cos == null) {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("Costumer NOT found.\nPlease try different ID.");
            ErrorAlert.showAndWait();
        } else {
            myController.getCostumerVehicles(VehicleSearchCosIDtxt.getText());
        }
    }

    @FXML
    void removeSelectedVehicle(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Remove Vehicle");
        alert.setContentText(String.format("Are you sure you want to remove this Vehicle?\nonce you click YES vehicle will be removed permanently!"));
        Optional<ButtonType> res = alert.showAndWait();

        if (res.get().getText().equals("Yes")) {
            myController.removeVehicle(VehicleTable.getSelectionModel().getSelectedItem().getVehicleID());
            Vehicle selectedItem = VehicleTable.getSelectionModel().getSelectedItem();
            VehicleTable.getItems().remove(selectedItem);
        }

    }

    @FXML
    void setVehicleInfoVisible(MouseEvent event) {
        VehicleInformationPane.setVisible(true);
    }

    @FXML
    void showCreditCard(MouseEvent event) {
        PagingController pc = new PagingController();
        if (CosManageTbale.getSelectionModel().isEmpty()) {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("Please Select Costumer.");
            ErrorAlert.showAndWait();
        } else {
            Costumer cos = CosManageTbale.getSelectionModel().getSelectedItem();
            pc.loadAdditionalStage(ProjectPages.CREDIT_CARD_DIALOG_PAGE.getPath(), cos);
        }
    }


    private Costumer searchCostumerWithID(String costumerID) {
        for (Costumer cos : costumers) {
            if (cos.getID().equals(costumerID))
                return cos;
        }
        return null;
    }
}
