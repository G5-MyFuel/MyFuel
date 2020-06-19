package boundary;

import Contollers.OrderFromSupplierController;
import common.assets.EmailHandler;
import entity.OrderFuelFromSupplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * @author Adi Lampert
 * @see OrderExecutionBoundary - the from's logic class
 */
public class OrderExecutionBoundary implements DataInitializable {

    private OrderExecutionBoundary OrderExecutionController;
    private OrderFromSupplierController myController = new OrderFromSupplierController(this);
    private String SupplierID = "";


    @FXML
    private Button MenuHomePageBtn;

    @FXML
    private Button MenuOrderConfirmationBtn;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnSignout;

    @FXML
    private ImageView arrowImage;

    @FXML
    private Text OrderViewTitle;

    @FXML
    private AnchorPane OrderViewAnchorPane;

    @FXML
    private TableView<OrderFuelFromSupplier> tableView;

    @FXML
    private TableColumn<TableView<OrderFuelFromSupplier>, String> orderCol;

    @FXML
    private TableColumn<TableView<OrderFuelFromSupplier>, String> statusCol;

    @FXML
    private Label QuantityField;

    @FXML
    private Label StationManagerField;

    @FXML
    private Label CompanyNameField;

    @FXML
    private Label StationNumberField;

    @FXML
    private Label OrderDateField;

    @FXML
    private Label FuelTypeField;

    @FXML
    private Button DoneBtn;

    @FXML
    private HBox hboxOrderConfirmation;

    @FXML
    private Text DoneMsgTxt;

    @FXML
    private Text noOrdersTxt;

    @FXML
    private ImageView checkBoxImage;

    @FXML
    private CheckBox confirmationCheckBox;

    private ObservableList<OrderFuelFromSupplier> tableData;

    @FXML
    void OrderConfirmationCheck(MouseEvent event) {
        /******* If there is a 'check' sign *******/
        if (confirmationCheckBox.isSelected())
            DoneBtn.setDisable(false);
        else DoneBtn.setDisable(true);
    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void ClickDoneBtn(MouseEvent event) {
        EmailHandler sender = new EmailHandler();
        tableView.getSelectionModel().getSelectedItem().setOrderStatus("done");

        OrderFuelFromSupplier temp = tableView.getSelectionModel().getSelectedItem();
        temp.setOrderStatus("done");
        myController.setNewStatus(temp.getOrderNumber());
        hboxOrderConfirmation.setVisible(false);
        DoneMsgTxt.setVisible(true);
        DoneBtn.setVisible(false);
        tableView.refresh();

        addToStock(temp.getQuantity(), temp.getFuelType(), temp.getStationNumber(), temp.getManagerID());
        /** Send an email to the station manager that the order arrived **/
        sender.sendMessage(temp.getUserEmail(),"Order update","Dear "+temp.getUserFirstName()+" "+temp.getUserLastName()+
                ", Order number "+temp.getOrderNumber()+" has arrived to your station.\n Have a nice day.\n\n *message from MyFuel");
    }


    @Override
    public void initData(Object data) {
        this.SupplierID = (String) data;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hboxOrderConfirmation.setVisible(false);
        tableView.setVisible(true);
        DoneBtn.setVisible(false);
        DoneBtn.setDisable(true);
        DoneMsgTxt.setVisible(false);
        OrderViewAnchorPane.setVisible(false);
        noOrdersTxt.setVisible(false);
        myController.getOrdersFromDB();
        System.out.println("Order Execution Page Is Open");
    }

    /**
     * This function set the details from DB to TableView
     **/
    public void setOrderFuelFromSupplierTableView(ArrayList<OrderFuelFromSupplier> OrderArray) {
        orderCol.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("OrderStatus"));
        tableData = FXCollections.observableArrayList(OrderArray);
        tableView.setEditable(true);
        tableView.setItems(tableData);
        if(tableView.getItems().isEmpty())
            noOrdersTxt.setVisible(true);
    }

    /**
     * Get DB details for Order View after we choose an order from the table
     **/
    public void getOrderDetailsFromTableView() {
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OrderFuelFromSupplier temp = tableView.getSelectionModel().getSelectedItem();

                if (temp.getOrderStatus().equals("done")) {
                    DoneMsgTxt.setVisible(true);
                    hboxOrderConfirmation.setVisible(false);
                    DoneBtn.setVisible(false);
                    tableView.refresh();
                } else {
                    confirmationCheckBox.setSelected(false);
                    DoneMsgTxt.setVisible(false);
                    OrderViewAnchorPane.setVisible(true);
                    arrowImage.setVisible(true);
                    hboxOrderConfirmation.setVisible(true);
                    hboxOrderConfirmation.setDisable(false);
                    DoneBtn.setVisible(true);
                }
                /************   Show details to the User  ***************/
                StationManagerField.setText(temp.getUserFirstName() + " " + temp.getUserLastName());
                CompanyNameField.setText(temp.getGasCompanyName());
                OrderDateField.setText(temp.getOrderDate().toString());
                FuelTypeField.setText(temp.getFuelType());
                System.out.println(temp.getStationNumber());
                StationNumberField.setText(temp.getStationNumber().toString());
                QuantityField.setText(temp.getQuantity().toString());
            }
        });
    }

    /**
     * Update the inventory after the supplier confirm
     *
     */
    public void addToStock(int quantity, String fuelType, int stationNumber, String managerID) {
        if (fuelType.equals("Gasoline95"))
            myController.setNewInventory95(quantity, managerID, stationNumber);
        else if (fuelType.equals("diesel"))
            myController.setNewInventoryDiesel(quantity, managerID, stationNumber);
        else if (fuelType.equals("scooterFuel"))
            myController.setNewInventoryScooter(quantity, managerID, stationNumber);
    }

}