package boundary;

import Contollers.ConfirmDiscountRatesController;
import entity.DiscountRate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class ConfirmDiscountRatesBoundary implements DataInitializable {

    String managerID;
    String managerCompany;
    String managerStation;
    /**
     * The supervisor boundary controller.
     */
    private final ConfirmDiscountRatesController myController = new ConfirmDiscountRatesController(this);
    private final Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    ArrayList<DiscountRate> sendDiscountRates = new ArrayList<>();

    @FXML
    private TableView<DiscountRate> TableSubscriptionType;

    @FXML
    private TableColumn<DiscountRate, String> SubscriptionTypeColumn;

    @FXML
    private TableColumn<DiscountRate, String> CurrentPriceColumnColumn;

    @FXML
    private TableColumn<DiscountRate, String> NewPriceColumn;

    @FXML
    private Button btnApprovedRates;

    @FXML
    private Button btnRemoveNewRate;

    @FXML
    private Label NoNewRatePendingLabel;

    @FXML
    private Label InstructionsLabel;

    @Override
    public void initData(Object data) {

        this.managerID = (String) data;
        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Get Manager data");
        paramArray.add(managerID);
        myController.GetDiscountRatesData(paramArray); //start the process that will ask server to execute quarry and get the table details

        /*btnApprovedRates.setDisable(true);
        btnRemoveNewRate.setDisable(true);*/
        NoNewRatePendingLabel.setVisible(false);
        TableSubscriptionType.setVisible(false);
        TableSubscriptionType.setEditable(true);
        btnApprovedRates.setVisible(false);
        btnRemoveNewRate.setVisible(false);
        InstructionsLabel.setVisible(false);
    }

    public void setManagerData(ArrayList<String> resultList) {
        managerCompany = resultList.get(0);
        managerStation = resultList.get(1);
        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Get Discount Rates Data");
        paramArray.add(managerCompany);
        myController.GetDiscountRatesData(paramArray);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setDiscountRatesData(ArrayList<DiscountRate> resultList) {

        TableSubscriptionType.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        SubscriptionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("subscriptionType"));
        CurrentPriceColumnColumn.setCellValueFactory(new PropertyValueFactory<>("currentDiscountRate"));
        NewPriceColumn.setCellValueFactory(new PropertyValueFactory<>("newDiscountRate"));

        ObservableList<DiscountRate> data = FXCollections.observableArrayList(resultList);
        if (resultList.size() == 0) {
            NoNewRatePendingLabel.setVisible(true);
            TableSubscriptionType.setVisible(false);
            btnApprovedRates.setVisible(false);
            btnRemoveNewRate.setVisible(false);
            InstructionsLabel.setVisible(false);
        } else {
            NoNewRatePendingLabel.setVisible(false);
            TableSubscriptionType.setVisible(true);
            btnApprovedRates.setDisable(true);
            btnApprovedRates.setVisible(true);
            btnRemoveNewRate.setDisable(true);
            btnRemoveNewRate.setVisible(true);
            InstructionsLabel.setVisible(true);
            TableSubscriptionType.setItems(data);
        }
    }

    @FXML
    void TableClicked(MouseEvent event) {

        btnApprovedRates.setDisable(true);
        btnRemoveNewRate.setDisable(true);
        sendDiscountRates.clear();
        ArrayList<DiscountRate> discountArray = new ArrayList<DiscountRate>(TableSubscriptionType.getSelectionModel().getSelectedItems());
        sendDiscountRates.addAll(discountArray);
        if (discountArray.size() > 0) {
            btnApprovedRates.setDisable(false);
            btnRemoveNewRate.setDisable(false);
        }
    }

    /**
     * this method listen to remove button and start update function
     * @param event
     */
    @FXML
    void handleApprovedRates(ActionEvent event) {

        updateDiscountRate("Update New Discount Rate");
    }

    /**
     * this method listen to remove button and start update function
     * @param event
     */
    @FXML
    void handleRemoveNewRate(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Remove Discount Rate Row");
        alert.setContentText(String.format("Are you sure you want to remove this row?\n"));
        Optional<ButtonType> res = alert.showAndWait();
        if (res.get().getText().equals("Yes")) {
            updateDiscountRate("Remove New Discount Rate");
            if (!TableSubscriptionType.getSelectionModel().isEmpty()) {
                DiscountRate selectedItem = TableSubscriptionType.getSelectionModel().getSelectedItem();
                TableSubscriptionType.getItems().remove(selectedItem);
                TableSubscriptionType.refresh();
                try {//wait for the quarry to execute
                    sleep(2000);
                } catch (InterruptedException ex) {
                    //...
                }
            }
        }
    }

    /**
     * @param queryName
     */
    void updateDiscountRate(String queryName) {

        ArrayList<String> paramArray = new ArrayList<>();
        for (DiscountRate temp : sendDiscountRates) {
            paramArray.clear();
            paramArray.add(queryName);
            paramArray.add(managerCompany);
            paramArray.add(temp.getSubscriptionType());
            myController.GetDiscountRatesData(paramArray);
        }
        paramArray.clear();
        paramArray.add("Get Discount Rates Data");
        paramArray.add(managerCompany);
        myController.GetDiscountRatesData(paramArray);
    }

}