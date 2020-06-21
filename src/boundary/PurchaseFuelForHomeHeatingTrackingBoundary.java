package boundary;

import Contollers.BasicController;
import Contollers.PurchaseFuelForHomeHeatingTrackingController;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import common.assets.SqlResult;
import entity.EditingCell;
import entity.HomeHeatingOrderTracking;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * @author Daniel
 */
public class PurchaseFuelForHomeHeatingTrackingBoundary implements DataInitializable{
    /**
     * Gloable Variables:
     */
    private PurchaseFuelForHomeHeatingTrackingController myController = new PurchaseFuelForHomeHeatingTrackingController(this);
    private String currentCustomer = null;
    ArrayList<HomeHeatingOrderTracking> arrayListOfCustomerOrders = null;

    /**
     *     GUI variables:
     */


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<HomeHeatingOrderTracking> treeTableOfALlCustomerOrders;

    @FXML
    private ProgressBar progressBarShipping;

    @FXML
    private Text shippingStatusLable;

    @FXML
    private Text expectedLable;

    @FXML
    private TableColumn<HomeHeatingOrderTracking, String> orderDateCol;

    @FXML
    private TableColumn<HomeHeatingOrderTracking, String> orderTimeCol;

    @FXML
    private TableColumn<HomeHeatingOrderTracking, String> statusCol;

    @FXML
    private TableColumn<HomeHeatingOrderTracking, String> expectedDeliveryDate;


    @Override
    public void initData(Object data) {
        this.currentCustomer = (String) data;
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(currentCustomer);
        myController.PURCHASE_FUEL_FOR_HOME_HEATING_TRACKING_fromDB(varArray);
        System.out.println(arrayListOfCustomerOrders);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public ArrayList<HomeHeatingOrderTracking> getArrayListOfCustomerOrders() {
        return arrayListOfCustomerOrders;
    }

    public void setArrayListOfCustomerOrders(ArrayList<HomeHeatingOrderTracking> arrayListOfCustomerOrders) {
        this.arrayListOfCustomerOrders = arrayListOfCustomerOrders;
        System.out.println(arrayListOfCustomerOrders);
        treeTableOfALlCustomerOrders.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<HomeHeatingOrderTracking> data = FXCollections.observableArrayList(arrayListOfCustomerOrders);
        orderDateCol.setCellValueFactory(new PropertyValueFactory<HomeHeatingOrderTracking,String>("OrderDate"));
        orderDateCol.setText("Order Date");
                //
        orderTimeCol.setCellValueFactory(new PropertyValueFactory<HomeHeatingOrderTracking,String>("OrderTime"));
        orderTimeCol.setText("Order Time");
        statusCol.setCellValueFactory(new PropertyValueFactory<HomeHeatingOrderTracking,String>("statusStr"));
        statusCol.setText("Shipping Status");
        expectedDeliveryDate.setCellValueFactory(new PropertyValueFactory<HomeHeatingOrderTracking,String>("expectedDeliveryDate"));
        expectedDeliveryDate.setText("expected Delivery Date");
        treeTableOfALlCustomerOrders.setItems(data);

    }
}
