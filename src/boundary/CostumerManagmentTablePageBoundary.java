package boundary;

import Contollers.CostumerManagementController;
import Contollers.FormValidation;
import Contollers.PagingController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.assets.ProjectPages;
import common.assets.SqlQueryType;
import entity.Costumer;
import entity.EditingCell;
import entity.Vehicle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

/**
 * the class Costumer Management Table Page Boundary
 *
 * @author Itay Ziv
 * @see CostumerManagmentTablePageBoundary - the form's Boundary class
 */

public class CostumerManagmentTablePageBoundary implements DataInitializable {


    private ArrayList<Vehicle> Vehicles = new ArrayList<>();
    private ArrayList<Costumer> costumers = new ArrayList<>();
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    private Costumer cos;
    private FormValidation myValidator;

    /**
     * The supervisor boundary controller.
     */
    private CostumerManagementController myController = new CostumerManagementController(this);


    @FXML
    private JFXTextField CostumerIDtxt;


    @FXML
    private Pane VehicleInformationPane;

    @FXML
    private JFXTextField VehicleIDtxt;

    @FXML
    private JFXTextField OwnerIDtxt1;

    @FXML
    private JFXComboBox<String> GasTypeChoiseBox;


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
    private TableColumn pricingModelCol;

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

    /**
     * initialize observable arrays to set in view objects.
     */
    private ObservableList<String> CostumerType = FXCollections.observableArrayList("Private", "Company");
    private ObservableList<String> GasType = FXCollections.observableArrayList("Gasoline-95", "Diesel", "Scooter Fuel");
    /**
     * initialize this will start in the initialize of the boundary.
     * sends parameters from anther pages
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VehicleInformationPane.setVisible(false);
        GasTypeChoiseBox.setItems(GasType);
        myController.getVehicleTable();
        myController.getCostumerTable(); //start the process that will ask server to execute quarry and get the table details
        tableStyle();
        myValidator = new FormValidation();
    }



    /**
     * this method will set the costumer table and the cell edit functions
     * when the page initialized.
     */
    public void setCostumerTable(ArrayList<Costumer> cosArray) {
        costumers.clear();
        costumers.addAll(cosArray);
        setColomsCells();
        ObservableList<Costumer> data = FXCollections.observableArrayList(cosArray);
        CosManageTbale.setItems(data);
        CosManageTbale.refresh();
        CosManageTbale.setEditable(true);

        //Modifying the firstName property
        firstNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = t.getTableView().getItems().get(t.getTablePosition().getRow());
                if (t.getNewValue().isEmpty() || t.getNewValue().matches(".*\\d.*")) {
                    ErrorAlert.setTitle("Fields Error");
                    ErrorAlert.setHeaderText("First Name field Error. Please try again.");
                    ErrorAlert.showAndWait();
                    CosManageTbale.refresh();
                } else {
                    temp.setUserFirstName(t.getNewValue());
                    myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_FNAME, temp.getUserID(), temp.getUserFirstName());
                }
            }
        });
        //Modifying the lastName property
        LastNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = t.getTableView().getItems().get(t.getTablePosition().getRow());
                if (t.getNewValue().isEmpty() || t.getNewValue().matches(".*\\d.*")) {
                    ErrorAlert.setTitle("Fields Error");
                    ErrorAlert.setHeaderText("Last Name field Error. Please try again.");
                    ErrorAlert.showAndWait();
                    CosManageTbale.refresh();
                } else {
                    temp.setUserLastName(t.getNewValue());
                    myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_LNAME, temp.getUserID(), temp.getUserLastName());
                }
            }
        });
        //Modifying the Emailadress property
        EmailAdressCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = t.getTableView().getItems().get(t.getTablePosition().getRow());
                if (t.getNewValue().isEmpty()) {
                    ErrorAlert.setTitle("Fields Error");
                    ErrorAlert.setHeaderText("Email field Error. Please try again.");
                    ErrorAlert.showAndWait();
                    CosManageTbale.refresh();
                } else {
                    temp.setUserEmail(t.getNewValue());
                    myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_EMAIL, temp.getUserID(), temp.getUserEmail());
                }
            }
        });
        //Modifying the CostumerType property
        CostumerTypeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = t.getTableView().getItems().get(t.getTablePosition().getRow());
                temp.setCostumerType(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_TYPE, temp.getUserID(), temp.getCostumerType());
            }
        });
        //Modifying the PricingModel property
        pricingModelCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = ((Costumer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                if(checkSubscriptionType(t.getNewValue(),temp)){
                    temp.setPricingModel(t.getNewValue());
                    myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_PRICING_MODEL, temp.getUserID(), temp.getPricingModel());
                }
            }
        });
        //Modifying the purchasePlan property
        PurchasePlanCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Costumer, String> t) {
                Costumer temp = t.getTableView().getItems().get(t.getTablePosition().getRow());
                temp.setPurchasePlan(t.getNewValue());
                myController.updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_PURCHASE_PLAN, temp.getUserID(), temp.getPurchasePlan());
                openStationPage();
            }
        });
    }

    /**
     * this method checks the costumer subscription type and return
     * true if costumer can have certen subscription from the selected subscriptions.
     * @param subToCheck
     * @param tempCos
     * @return boolean
     */
    public boolean checkSubscriptionType(String subToCheck, Costumer tempCos) {
        if (subToCheck.equals("Regular monthly subscription(singel)") || subToCheck.equals("Full monthly subscription")) {
            if (tempCos.getCostumerVehicle().size() > 1) {
                ErrorAlert.setTitle("Subscription Type Error");
                ErrorAlert.setHeaderText("Costumer has more then 1 vehicle.\nYou may chose different subscription plan.");
                ErrorAlert.showAndWait();
                return false;
            }
        } else if (subToCheck.equals("Regular monthly subscription(multiple)")) {
            if (tempCos.getCostumerVehicle().size() < 2) {
                ErrorAlert.setTitle("Subscription Type Error");
                ErrorAlert.setHeaderText("Costumer has 1 or less vehicles.\nYou may chose different subscription plan.");
                ErrorAlert.showAndWait();
                return false;
            }
        }
        return true;
    }

    /**
     *the following method will program the table cell.
     * each cell has its own listener and functionality.
     */
    private void setColomsCells() {

        //Create a customer cell factory so that cells can support editing.
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new EditingCell();
            }
        };

        PurchasePlanCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Costumer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Costumer, String> param) {
                Costumer costumer = param.getValue();
                String temp = costumer.getPurchasePlan();
                return new SimpleObjectProperty<String>(temp);
            }
        });

        pricingModelCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Costumer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Costumer, String> param) {
                Costumer costumer = param.getValue();
                String temp = costumer.getPricingModel();
                return new SimpleObjectProperty<String>(temp);
            }
        });
        CostumerTypeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Costumer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Costumer, String> param) {
                Costumer costumer = param.getValue();
                String temp = costumer.getCostumerType();
                return new SimpleObjectProperty<String>(temp);
            }
        });

        CostumerIDCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("userID"));
        CostumerIDCol.setEditable(false);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("userFirstName"));
        firstNameCol.setCellFactory(cellFactory);
        LastNameCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("userLastName"));
        LastNameCol.setCellFactory(cellFactory);
        EmailAdressCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("userEmail"));
        EmailAdressCol.setCellFactory(cellFactory);

        CostumerTypeCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("CostumerType"));
        CostumerTypeCol.setCellFactory(ComboBoxTableCell.forTableColumn(CostumerType));

        pricingModelCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("pricingModel"));
        ObservableList<String> pricingModelType = FXCollections.observableArrayList("Casual fueling", "Regular monthly subscription(singel)", "Regular monthly subscription(multiple)", "Full monthly subscription");
        pricingModelCol.setCellFactory(ComboBoxTableCell.forTableColumn(pricingModelType));

        PurchasePlanCol.setCellValueFactory(new PropertyValueFactory<Costumer, String>("purchasePlan"));
        ObservableList<String> purchasePlan = FXCollections.observableArrayList("Exclusive", "Multiple Stations");
        PurchasePlanCol.setCellFactory(ComboBoxTableCell.forTableColumn(purchasePlan));

    }

    /**
     * this method initialize vehicle table view with a spesific costumer
     * vehicles.
     * @param vArr
     */
    public void setVehicleTable(ArrayList<Vehicle> vArr) {
        Vehicles.addAll(vArr);
        VehicleIDCol.setCellValueFactory(new PropertyValueFactory<>("VehicleID"));
        GasTypeCol.setCellValueFactory(new PropertyValueFactory<>("GasType"));
        ObservableList<Vehicle> data = FXCollections.observableArrayList(vArr);
        VehicleTable.setItems(data);
    }


    /**
     * this method simply refresh costumer table view.
     */
    @FXML
    public void refreshTable() {
        myController.getVehicleTable();
        myController.getCostumerTable();
    }

    /**
     * the following method search for specific costumer and display it
     * in the costumer table view.
     * @param event
     */
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
        boolean flag = false;
        boolean length = VehicleIDtxt.getText().length() != 7 ? true:false;
        if (OwnerIDtxt1.getText().isEmpty() || VehicleIDtxt.getText().isEmpty() || GasTypeChoiseBox.getSelectionModel().isEmpty()) {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("One of the required fields is empty.\nPlease insert required information to proceed.");
            ErrorAlert.showAndWait();
        } else if (myController.isVehicleExistInDb(VehicleIDtxt.getText())) {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("Vehicle ID already exists.\nPlease chose different ID.");
            ErrorAlert.showAndWait();
        }else if(length){
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("Vehicle ID must contain only numbers.");
            ErrorAlert.showAndWait();
        }
        else {
            if (VehicleTable.getItems().size() == 1)
                flag = true;
            myController.addVehicleToDB(OwnerIDtxt1.getText(), VehicleIDtxt.getText(), GasTypeChoiseBox.getValue());
            Vehicle insertItem = new Vehicle(OwnerIDtxt1.getText(), VehicleIDtxt.getText(), GasTypeChoiseBox.getValue());
            Costumer cos = searchCostumerWithID(OwnerIDtxt1.getText());
            costumers.remove(cos);
            cos.getCostumerVehicle().add(insertItem);
            costumers.add(cos);
            VehicleTable.getItems().add(insertItem);
            VehicleTable.refresh();
            OwnerIDtxt1.clear();
            VehicleIDtxt.clear();
            VehicleInformationPane.setVisible(false);
            if (VehicleTable.getItems().size() > 1 && flag) {
                ArrayList<Object> obj = new ArrayList<>();
                obj.add("multiple");
                obj.add(this);
                PagingController pc = new PagingController();
                pc.loadAdditionalStage(ProjectPages.CHOOSE_SUBSCRIPTION_TYPE.getPath(), obj);
            }
        }

    }

    /**
     * this method removes selected costumer when user click "YES"
     * costumer will be removed both from db and costumer tableview.
     */
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
            if (!CosManageTbale.getSelectionModel().isEmpty()) {
                Costumer selectedItem = CosManageTbale.getSelectionModel().getSelectedItem();
                myController.getCostumerVehicles(selectedItem.getUserID());
                myController.removeCostumer(CosManageTbale.getSelectionModel().getSelectedItem().getUserID());
                costumers.remove(selectedItem);
                CosManageTbale.getItems().remove(selectedItem);
                CosManageTbale.refresh();
                VehicleTable.getItems().clear();
                VehicleTable.refresh();
                try {//wait for the quarry to execute
                    sleep(2000);
                } catch (InterruptedException ex) {
                    //...
                }
                for (Vehicle v : Vehicles) {
                    myController.removeVehicle(v.getVehicleID());
                }
            }
        }
    }

    /**
     * this method simply refresh vehicle table view.
     */
    @FXML
    void refreshVehicleTable() {
        if (!VehicleTable.getItems().isEmpty()) {
            VehicleTable.getItems().clear();
            VehicleSearchCosIDtxt.clear();
        }
        VehicleTable.refresh();

    }

    /**
     * the following method will start a process that will ask
     * data base to get all vehicle of specific costumer.
     * @param event
     */
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

    /**
     * the following method will remove selected vehicle by click remove button.
     * both from table and from db.
     * @param event
     */
    @FXML
    void removeSelectedVehicle(MouseEvent event) {

        if (!VehicleTable.getSelectionModel().isEmpty()) {
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
                Costumer cos = searchCostumerWithID(VehicleSearchCosIDtxt.getText());
                costumers.remove(cos);
                cos.getCostumerVehicle().remove(VehicleTable.getSelectionModel().getSelectedItem());
                costumers.add(cos);
                if (VehicleTable.getItems().size() <= 1) {
                    ArrayList<Object> obj = new ArrayList<>();
                    obj.add("single");
                    obj.add(this);
                    PagingController pc = new PagingController();
                    pc.loadAdditionalStage(ProjectPages.CHOOSE_SUBSCRIPTION_TYPE.getPath(), obj);
                }
            }
        }
    }

    /**
     * the following method set vehicle info visible if the user
     * has chosen to add a vehicle
     * @param event
     */
    @FXML
    void setVehicleInfoVisible(MouseEvent event) {
        if (!(VehicleSearchCosIDtxt.getText().isEmpty())) {
            OwnerIDtxt1.setText(VehicleSearchCosIDtxt.getText());
        }
        if (VehicleInformationPane.isVisible())
            VehicleInformationPane.setVisible(false);
        else
            VehicleInformationPane.setVisible(true);
    }

    /**
     * the following method is a listener that opens the credit card window
     * of selcted costumer from the costumer table
     * @param event
     */
    @FXML
    void showCreditCard(MouseEvent event) {
        PagingController pc = new PagingController();
        if (CosManageTbale.getSelectionModel().isEmpty()) {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("Please Select Costumer.");
            ErrorAlert.showAndWait();
        } else {
            cos = CosManageTbale.getSelectionModel().getSelectedItem();
            pc.loadAdditionalStage(ProjectPages.CREDIT_CARD_DIALOG_PAGE.getPath(), this);
        }
    }

    /**
     * the following function opens the station pages
     * that allowed the user to change costumer stations.
     */
    @FXML
    void openStationPage() {
        if (CosManageTbale.getSelectionModel().isEmpty()) {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("Please Select Costumer.");
            ErrorAlert.showAndWait();
        } else {
            CosManageTbale.setDisable(true);
            PagingController pc = new PagingController();
            pc.loadAdditionalStage(ProjectPages.STATION_SELECTION_PAGE.getPath(), this);
        }
    }

    /**
     * pick costumer from the costumer table and change his stations
     * according to the giving parameters
     * @param station1
     * @param station2
     * @param station3
     */
    public void changeSelectedCostumerStations(String station1, String station2, String station3) {
        Costumer cos = CosManageTbale.getSelectionModel().getSelectedItem();
        ArrayList<String> temp = new ArrayList<>();
        temp.add(station1);
        temp.add(station2);
        temp.add(station3);
        cos.setFuelCompany(temp);
        myController.setCostumerStation(cos);
        refreshTable();
    }

    public Costumer getCos() {
        return cos;
    }

    public void setCos(Costumer cos) {
        this.cos = cos;
    }

    /**
     * this method search specific costumer in the costumers array
     * that initialized from db.
     * @param costumerID
     * @return
     */
    private Costumer searchCostumerWithID(String costumerID) {
        for (Costumer cos : costumers) {
            if (cos.getUserID().equals(costumerID))
                return cos;
        }
        return null;
    }

    /**
     * set table style css
     */
    private void tableStyle() {
        CosManageTbale.setStyle("-fx-font-weight: bold;");
    }

    /**
     * get costumers tableview
     * @return CosManageTbale
     */
    public TableView<Costumer> getCosManageTbale() {
        return CosManageTbale;
    }

    /**
     * get my controller
     * @return myController
     */
    public CostumerManagementController getMyController() {
        return myController;
    }

    public JFXTextField getVehicleSearchCosIDtxt() {
        return VehicleSearchCosIDtxt;
    }

    @Override
    public void initData(Object data) {

    }
}
