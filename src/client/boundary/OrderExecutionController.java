package client.boundary;

import client.logic.FormValidation;
import client.logic.OrderFromSupplierLogic;
import client.logic.SaleOperationTemplateLogic;
import com.jfoenix.controls.JFXTextField;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import common.entity.Employee;
import common.entity.OrderFuelFromSupplier;
import common.tools.Message;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.w3c.dom.ls.LSOutput;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author Adi Lampert
 * @see OrderExecutionController - the from's logic class
 */
public class OrderExecutionController implements Initializable {

    public static OrderExecutionController Instance = null;
    private OrderExecutionController OrderExecutionController;
    private OrderFromSupplierLogic OFSLogic = OrderFromSupplierLogic.getInstance(); //logic


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

    public OrderExecutionController() {
        Instance = this;
    }

    /**
     * OrderExecutionController Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of controller class
     */
    public static OrderExecutionController getInstance() {
        if (Instance == null)
            Instance = new OrderExecutionController();
        return Instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hboxOrderConfirmation.setVisible(false);
        arrowImage.setVisible(false);
        tableView.setVisible(true);
        DoneBtn.setVisible(false);
        DoneBtn.setDisable(true);
        OrderViewAnchorPane.setVisible(false);
        OFSLogic.getOrderFuelFromSupplierTable();
        System.out.println(OFSLogic.getOrderSet());

        OFFS = new ArrayList<OrderFuelFromSupplier>();
        OFFS.add(new OrderFuelFromSupplier("1111", "Paz", "Valeria Chapman", 5, null, 800, "done"));
        OFFS.add(new OrderFuelFromSupplier("2222", "pazosh", "Valeria qweqwe", 6, null, 800, "done"));
        OFFS.add(new OrderFuelFromSupplier("221231222", "pazosasdasdh", "Valeria qweqwe", 6, null, 800, "done"));
        OFFS.add(new OrderFuelFromSupplier("21321", "paasdzosh", "Valeria qweqwe", 6, null, 800, "done"));
        OFFS.add(new OrderFuelFromSupplier("22345235222", "pazodccdsh", "Valeria qweqwe", 6, null, 800, "done"));
    }


    public void setOrderFuelFromSupplierTableView(MouseEvent event) throws SQLException {
        orderCol.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("OrderStatus"));

        ObservableList<OrderFuelFromSupplier> data = FXCollections.observableArrayList(OFFS);
        tableView.setEditable(true);
        tableView.setItems(data);
    }


    public void getOrderDetailsFromTableView() {

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