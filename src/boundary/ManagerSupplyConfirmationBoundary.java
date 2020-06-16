package boundary;

import Contollers.ManagerSupplyConfirmationController;
import entity.ManagerSupplyConfirmation;
import entity.OrderFuelFromSupplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Adi Lampert
 * @see ManagerSupplyConfirmationBoundary - the from's logic class
 */

public class ManagerSupplyConfirmationBoundary implements DataInitializable {

    private ManagerSupplyConfirmationController myController = new ManagerSupplyConfirmationController(this);
    private ObservableList<ManagerSupplyConfirmation> tableData;
    private String stationManagerID = "800300579";

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrderConfirmation;

    @FXML
    private Button btnSignout;

    @FXML
    private ImageView arrowImage;

    @FXML
    private TableView<ManagerSupplyConfirmation> tableView;

    @FXML
    private TableColumn<?, ?> OrderCol;

    @FXML
    private TableColumn<?, ?> CompanyCol;

    @FXML
    private TableColumn<?, ?> StationCol;

    @FXML
    private TableColumn<?, ?> FuelTypeCol;

    @FXML
    private TableColumn<?, ?> AmountCol;

    @FXML
    private HBox hboxOrderConfirmation;

    @FXML
    private ImageView checkBoxImage;

    @FXML
    private CheckBox confirmationCheckBox;

    @FXML
    private Button SendBtn;

    @FXML
    private Text ApprovalTxt;

    @FXML
    void OrderConfirmationCheck(MouseEvent event) {
        /**  If there is a check sign   **/
        if(confirmationCheckBox.isSelected())
            SendBtn.setDisable(false);
        else SendBtn.setDisable(true);
    }

    @FXML
    void SendConfirmationToSupplier(MouseEvent event) {
        for (int i = 0; i < myController.resultList.size(); i++) {
            if (myController.resultList.get(i).getOrderNumber().equals(tableView.getSelectionModel().getSelectedItem().getOrderNumber())) {
                myController.setNewStatus(myController.resultList.get(i).getOrderNumber());
                myController.resultList.get(i).setOrderStatus("In treatment");
                tableData = FXCollections.observableArrayList(myController.resultList);
                tableView.setItems(tableData);
            }
        }
        //TODO : make sure we see the changes in the other table (supplier)
    }

    @FXML
    void handleBookDeleteOption(ActionEvent event) {

    }

    @FXML
    void handleBookEditOption(ActionEvent event) {

    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void orderConfirmationPage(MouseEvent event) {

    }

    @Override
    public void initData(Object data) { stationManagerID = (String)data; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hboxOrderConfirmation.setVisible(false);
        tableView.setVisible(true);
        ApprovalTxt.setVisible(false);
        SendBtn.setVisible(false);
        SendBtn.setDisable(true);
        myController.getOrdersFromDB(stationManagerID);
        System.out.println("Manager Supply Confirmation Page Is Open");
    }

    public void setOrderForManagerTableView(ArrayList<ManagerSupplyConfirmation> OrderArray) {
        OrderCol.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
        CompanyCol.setCellValueFactory(new PropertyValueFactory<>("GasCompanyName"));
        StationCol.setCellValueFactory(new PropertyValueFactory<>("StationNum"));
        FuelTypeCol.setCellValueFactory(new PropertyValueFactory<>("FuelType"));
        AmountCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        tableData = FXCollections.observableArrayList(OrderArray);
        tableView.setEditable(true);
        tableView.setItems(tableData);
    }

    public void getDetailsFromTableView() {
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            ManagerSupplyConfirmation temp = null;
            @Override
            public void handle(MouseEvent event) {
                for (int i = 0; i < myController.resultList.size(); i++) {
                    if (myController.resultList.get(i).getOrderNumber().equals(tableView.getSelectionModel().getSelectedItem().getOrderNumber()))
                        temp = myController.resultList.get(i);
                    if (temp != null) {
                        hboxOrderConfirmation.setVisible(true);
                        SendBtn.setVisible(true);

                    }

                }
            }
        });
    }




}