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
    private ImageView checkBoxImage;

    @FXML
    private CheckBox confirmationCheckBox;

    @FXML
    void OrderConfirmationCheck(MouseEvent event) {
        //TODO: if there a check mark - DontBtn is not disable + update status

    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hboxOrderConfirmation.setVisible(false);
        arrowImage.setVisible(false);
        tableView.setVisible(true);
        DoneBtn.setVisible(false);
        DoneBtn.setDisable(true);
        OrderViewAnchorPane.setVisible(false);
        myController.getOrdersFromDB();
        System.out.println("Order Execution Page Is Open");

    }


    public void setOrderFuelFromSupplierTableView( ArrayList<OrderFuelFromSupplier> OrderArray) {
        orderCol.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("OrderStatus"));
        System.out.println(OrderArray);
        ObservableList<OrderFuelFromSupplier> data = FXCollections.observableArrayList(OrderArray);
        tableView.setEditable(true);
        tableView.setItems(data);
    }


    public void getOrderDetailsFromTableView() {
        /*  get DB details for tableView  */
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OrderFuelFromSupplier temp = null;
                for (int i = 0; i < OFFS.size(); i++) {
                    if (OFFS.get(i).getOrderNumber().equals(tableView.getSelectionModel().getSelectedItem().getOrderNumber()))
                        temp = OFFS.get(i);
                    if (temp != null) {
                        OrderViewAnchorPane.setVisible(true);
                        arrowImage.setVisible(true);
                        hboxOrderConfirmation.setVisible(true);
                        hboxOrderConfirmation.setDisable(false);
                        DoneBtn.setVisible(true);
                        StationManagerField.setText(temp.getStationManagerName().toString());
                        StationNumberField.setText(temp.getStationNum().toString());
                        OrderDateField.setText("null");
                        FuelTypeField.setText(temp.getFuelType().toString());
                        QuantityField.setText(temp.getQuantity().toString());
                    }
                }
            }
        });
    }
}