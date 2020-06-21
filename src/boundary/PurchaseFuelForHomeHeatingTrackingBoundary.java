package boundary;

import Contollers.BasicController;
import Contollers.PurchaseFuelForHomeHeatingTrackingController;
import com.jfoenix.controls.JFXTreeTableView;
import common.assets.SqlResult;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
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
    ArrayList<String[]> arrayListOfCustomerOrders = null;

    /**
     *     GUI variables:
     */
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTreeTableView<?> treeTableOfALlCustomerOrders;

    @FXML
    private ProgressBar progressBarOfShipping;

    @FXML
    private Text orderStatisLable;

    @FXML
    private Text expectedLable;

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

    public ArrayList<String[]> getArrayListOfCustomerOrders() {
        return arrayListOfCustomerOrders;
    }

    public void setArrayListOfCustomerOrders(ArrayList<String[]> arrayListOfCustomerOrders) {
        this.arrayListOfCustomerOrders = arrayListOfCustomerOrders;
    }
}
