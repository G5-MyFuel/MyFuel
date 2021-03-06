package boundary;

import Contollers.ManagerNotificationPageController;
import common.assets.Toast;
import entity.ManagerNotifications;
import entity.OrderFuelFromSupplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Adi Lampert
 * @see ManagerNotificationsPageBoundary - the from's boundary class
 */

public class ManagerNotificationsPageBoundary implements DataInitializable {

    private ManagerNotificationPageController myController = new ManagerNotificationPageController(this);
    private String ManagerID = "";
    private ObservableList<ManagerNotifications> tableData;
    private generalDashBoardBoundary generalDashBoardBoundary;

    /**
     * FXML
     */
    @FXML
    private TableView<ManagerNotifications> tableView;

    @FXML
    private TableColumn<TableView<ManagerNotifications>, String> OrderCol;

    @FXML
    private TableColumn<TableView<ManagerNotifications>, Integer> StationCol;

    @FXML
    private Text explanationTxt;

    @FXML
    private Text noNotificationTxt;

    @FXML
    private ImageView bellGif;

    @FXML
    private Button CleanBtn;

    @FXML
    private AnchorPane OrderViewAnchorPane;

    @FXML
    private Label FuelTypeField;

    @FXML
    private Label QuantityField;

    /**
     * After the manager saw his notifications- change the status in DB to "viewed" and clean the notification from tableView
     * @param event
     */
    @FXML
    void clickCleanBtn(MouseEvent event) {
        ManagerNotifications temp = tableView.getSelectionModel().getSelectedItem();
        myController.setNewStatus(temp.getOrderNumber());
        OrderViewAnchorPane.setVisible(false);
        tableView.getItems().remove(temp);
        Toast.makeText(mainProjectFX.mainStage, "Deleted", 1000, 1500, 1500, 150, 700);
    }

    @FXML
    void handleBookDeleteOption(ActionEvent event) {

    }

    @FXML
    void handleBookEditOption(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

    @Override
    public void initData(Object data) {
        this.ManagerID = (String) data;
        myController.getOrdersFromDB(ManagerID);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CleanBtn.setDisable(true);
        OrderViewAnchorPane.setVisible(false);
        noNotificationTxt.setVisible(false);
        explanationTxt.setVisible(false);
    }
    /**
     * Show details in tableView
     * @param OrderArray
     */
    public void setOrdersInTableView(ArrayList<ManagerNotifications> OrderArray) {
        OrderCol.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
        StationCol.setCellValueFactory(new PropertyValueFactory<>("StationNumber"));
        tableData = FXCollections.observableArrayList(OrderArray);
        tableView.setEditable(true);
        tableView.setItems(tableData);

        if (tableView.getItems().isEmpty()) {
            noNotificationTxt.setVisible(true);
            explanationTxt.setVisible(false);
        }
        else explanationTxt.setVisible(true);
    }

    /** This function works when we press an order on tableView and show details **/
    public void getOrderClick() {
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ManagerNotifications temp = tableView.getSelectionModel().getSelectedItem();
                OrderViewAnchorPane.setVisible(true);
                CleanBtn.setDisable(false);
                /**Update Fields **/
                FuelTypeField.setText(temp.getFuelType().toString());
                QuantityField.setText(temp.getQuantity().toString());
            }
        });
    }

}
