package boundary;

import Contollers.ManagerNotificationPageController;
import entity.ManagerNotifications;
import entity.OrderFuelFromSupplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Adi Lampert
 * @see ManagerNotificationsPageBoundary - the from's logic class
 */

public class ManagerNotificationsPageBoundary implements DataInitializable {

    private ManagerNotificationPageController myController = new ManagerNotificationPageController(this);
    private String ManagerID = "";
    private ObservableList<ManagerNotifications> tableData;
    private generalDashBoardBoundary generalDashBoardBoundary;

    @FXML
    private TableView<ManagerNotifications> tableView;

    @FXML
    private TableColumn<TableView<ManagerNotifications>, String> OrderCol;

    @FXML
    private TableColumn<TableView<ManagerNotifications>, Integer> StationCol;

    @FXML
    private Text explanationTxt;

    @FXML
    private ImageView bellGif;

    @FXML
    private Button CleanBtn;

    /**
     * After the manager saw his notifications- change the status in DB to viewed and clean the tableView
     **/
    @FXML
    void clickCleanBtn(MouseEvent event) {
        ManagerNotifications temp= tableView.getSelectionModel().getSelectedItem();
        myController.setNewStatus(temp.getOrderNumber());
        tableView.getItems().remove(temp);
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
        myController.getOrdersFromDB();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CleanBtn.setDisable(true);
        System.out.println("Notifications Page Is Open");
    }

    public void setOrdersInTableView(ArrayList<ManagerNotifications> OrderArray) {
        OrderCol.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
        StationCol.setCellValueFactory(new PropertyValueFactory<>("StationNumber"));
        tableData = FXCollections.observableArrayList(OrderArray);
        tableView.setEditable(true);
        tableView.setItems(tableData);
    }

    public void getOrderClick() {
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ManagerNotifications temp = tableView.getSelectionModel().getSelectedItem();
                if(temp.equals(null))
                    CleanBtn.setDisable(true);
                else CleanBtn.setDisable(false);
            }
        });
    }

    /**
     * Function to update the amount of the manager's notifications every 5 minutes
     */
    /*private void notificationFunction() {
        int AmountOfNotifi = tableView.getItems().size();
        Thread notifi = new Thread() {
            public void run() {
                for (; ; ) {
                    //TODO update the number of notification

                    try {
                        sleep(300000);
                    } catch (InterruptedException ex) {
                        //...
                    }
                }
            }
        };
        notifi.start();
    }*/
}
