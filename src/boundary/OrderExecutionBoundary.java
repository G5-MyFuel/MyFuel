package boundary;

import Contollers.OrderFromSupplierController;
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
import java.util.ResourceBundle;

/**
 * @author Adi Lampert
 * @see OrderExecutionBoundary - the from's logic class
 */
public class OrderExecutionBoundary implements Initializable {

    private OrderExecutionBoundary OrderExecutionController;
    private OrderFromSupplierController myController = new OrderFromSupplierController(this);

    private ArrayList<OrderFuelFromSupplier> OFFS;

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
        for (int i = 0; i < myController.resultList.size(); i++) {
            if (myController.resultList.get(i).getOrderNumber().equals(tableView.getSelectionModel().getSelectedItem().getOrderNumber())) {
                myController.resultList.get(i).setOrderStatus("done");
                myController.setNewStatus(myController.resultList.get(i).getOrderNumber());
                tableData = FXCollections.observableArrayList(myController.resultList);
                tableView.setItems(tableData);
                DoneMsgTxt.setVisible(true);
                DoneBtn.setDisable(true);
                tableView.refresh();
                break;
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hboxOrderConfirmation.setVisible(false);
        tableView.setVisible(true);
        DoneBtn.setVisible(false);
        DoneBtn.setDisable(true);
        DoneMsgTxt.setVisible(false);
        OrderViewAnchorPane.setVisible(false);
        myController.getOrdersFromDB();
        System.out.println("Order Execution Page Is Open");

    }
     /**
      This function set the details from DB to TabeView
      **/
    public void setOrderFuelFromSupplierTableView(ArrayList<OrderFuelFromSupplier> OrderArray) {
        orderCol.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("OrderStatus"));
        tableData = FXCollections.observableArrayList(OrderArray);
        tableView.setEditable(true);
        tableView.setItems(tableData);
    }

    /**
     Get DB details for Order View after we choose an order from the table
     **/
    public void getOrderDetailsFromTableView() {
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OrderFuelFromSupplier temp = null;
                for (int i = 0; i < myController.resultList.size(); i++) {
                    if (myController.resultList.get(i).getOrderNumber().equals(tableView.getSelectionModel().getSelectedItem().getOrderNumber()))
                        temp = myController.resultList.get(i);
                    if (temp != null) {
                        OrderViewAnchorPane.setVisible(true);
                        arrowImage.setVisible(true);
                        hboxOrderConfirmation.setVisible(true);
                        hboxOrderConfirmation.setDisable(false);
                        DoneBtn.setVisible(true);
                        /************   Show details to the User  ***************/
                        StationManagerField.setText(temp.getStationManagerName().toString());
                        StationNumberField.setText(temp.getStationNum().toString());
                        OrderDateField.setText("null");
                        FuelTypeField.setText(temp.getFuelType().toString());
                        QuantityField.setText(temp.getQuantity().toString());

                        /******* 'Done' status or 'In treatment' status *********/
                        while (temp.getOrderStatus().equals("Done")) {
                            DoneMsgTxt.setVisible(true);
                            hboxOrderConfirmation.setDisable(true);
                        }
                        while (temp.getOrderStatus().equals("In treatment"))
                            DoneMsgTxt.setVisible(false);
                    }
                }
            }
        });
    }

    /******  set new status for in DB and tableView  ********/
    public void handleSetNewStatus() {

    }

}