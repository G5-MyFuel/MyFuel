package boundary;

import Contollers.ManagerSupplyConfirmationController;
import entity.ManagerSupplyConfirmation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private String stationManagerID="";

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
    private Text explanationTxt;

    @FXML
    private Text noOrdersTxt;

    @FXML
    private Text OrderNumberTxt;

    @FXML
    private Label orderNumberField;


    @FXML
    void OrderConfirmationCheck(MouseEvent event) {
        /**  If there is a check sign   **/
        if (confirmationCheckBox.isSelected())
            SendBtn.setDisable(false);
        else SendBtn.setDisable(true);
    }

    @FXML
    void clickSendBtn(MouseEvent event) {
        ManagerSupplyConfirmation temp = tableView.getSelectionModel().getSelectedItem();
        myController.setNewStatus(temp.getOrderNumber());
        temp.setOrderStatus("In treatment");

        orderNumberField.setText(temp.getOrderNumber());
        OrderNumberTxt.setVisible(true);
        orderNumberField.setVisible(true);
        hboxOrderConfirmation.setDisable(true);
        SendBtn.setDisable(true);
        ApprovalTxt.setVisible(true);
        tableData = FXCollections.observableArrayList(myController.resultList);
        tableView.setItems(tableData);
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
    public void initData(Object data) {
        stationManagerID = (String) data;
        myController.getOrdersFromDB(stationManagerID);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hboxOrderConfirmation.setVisible(false);
        tableView.setVisible(true);
        ApprovalTxt.setVisible(false);
        SendBtn.setVisible(false);
        SendBtn.setDisable(true);
        explanationTxt.setVisible(false);
        orderNumberField.setVisible(false);
        OrderNumberTxt.setVisible(false);
        noOrdersTxt.setVisible(false);
        System.out.println("Manager Supply Confirmation Page Is Open");
    }

    public void setOrderForManagerTableView(ArrayList<ManagerSupplyConfirmation> OrderArray) {
        OrderCol.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
        CompanyCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        StationCol.setCellValueFactory(new PropertyValueFactory<>("StationNum"));
        FuelTypeCol.setCellValueFactory(new PropertyValueFactory<>("FuelType"));
        AmountCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        tableData = FXCollections.observableArrayList(OrderArray);
        tableView.setEditable(true);
        tableView.setItems(tableData);
        if(tableView.getItems().isEmpty())
            noOrdersTxt.setVisible(true);
    }

    public void getDetailsFromTableView() {
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ManagerSupplyConfirmation temp = tableView.getSelectionModel().getSelectedItem();
                if (temp.getOrderStatus().equals("New")) {
                    orderNumberField.setText(temp.getOrderNumber());
                    OrderNumberTxt.setVisible(true);
                    orderNumberField.setVisible(true);
                    hboxOrderConfirmation.setVisible(true);
                    hboxOrderConfirmation.setDisable(false);
                    SendBtn.setVisible(true);
                    SendBtn.setDisable(true);
                    explanationTxt.setVisible(true);
                    ApprovalTxt.setVisible(false);
                    confirmationCheckBox.setSelected(false);
                } else {
                    orderNumberField.setText(temp.getOrderNumber());
                    OrderNumberTxt.setVisible(true);
                    orderNumberField.setVisible(true);
                    hboxOrderConfirmation.setDisable(true);
                    SendBtn.setDisable(true);
                    ApprovalTxt.setVisible(true);
                    confirmationCheckBox.setSelected(true);
                }
            }
        });
    }
}