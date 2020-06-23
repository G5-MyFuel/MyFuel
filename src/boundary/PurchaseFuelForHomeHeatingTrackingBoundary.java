package boundary;

import Contollers.PurchaseFuelForHomeHeatingTrackingController;
import entity.HomeHeatingOrderTracking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author  Daniel Gabbay
 * @see PurchaseFuelForHomeHeatingTrackingBoundary - the from's boundary class
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
    @FXML
    void showSelectedRowDetails(MouseEvent event){
        HomeHeatingOrderTracking tempOrder = treeTableOfALlCustomerOrders.getSelectionModel().getSelectedItem();
    }

    public ArrayList<HomeHeatingOrderTracking> getArrayListOfCustomerOrders() {
        return arrayListOfCustomerOrders;
    }

    /**
     * Update the tableView with costumer orders
     * @param arrayListOfCustomerOrders
     */
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

    public void showSelectedRowDetails(javafx.scene.input.MouseEvent mouseEvent) {
    }
}
