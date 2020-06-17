package boundary;

import Contollers.ConfirmDiscountRatesController;
import Contollers.FormValidation;
import entity.Costumer;
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
import java.util.ResourceBundle;

public class ConfirmDiscountRatesBoundary implements DataInitializable {

    /**
     * The supervisor boundary controller.
     */
    private final ConfirmDiscountRatesController myController = new ConfirmDiscountRatesController(this);
    private FormValidation formValidation;
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

    @Override
    public void initData(Object data) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.formValidation = FormValidation.getValidator();

        btnApprovedRates.setDisable(true);
        btnRemoveNewRate.setDisable(true);
        NoNewRatePendingLabel.setVisible(false);
        TableSubscriptionType.setVisible(false);
        btnApprovedRates.setVisible(false);
        btnRemoveNewRate.setVisible(false);

        /*  set all fields validators */
        formValidation();
        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Get Discount Rates Data");
        myController.GetDiscountRatesData(paramArray);

    }

    private void formValidation() {

        /*  New price validation */
/*
        //formValidation.isContainsOnlyNumbers(ShowNewRateTXT, "New price");
        formValidation.numberPositiveValidation(ShowNewRateTXT, "New price");
        formValidation.isEmptyField(ShowNewRateTXT, "New price");
        //formValidation.maxLengthValidation(ShowNewRateTXT, "New price", 3);
        formValidation.maxSizeValidation(ShowNewRateTXT, "New price", 100);
        formValidation.minSizeValidation(ShowNewRateTXT, "New price", 1);
        */

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
        } else {
            NoNewRatePendingLabel.setVisible(false);
            TableSubscriptionType.setVisible(true);
            btnApprovedRates.setVisible(true);
            btnRemoveNewRate.setVisible(true);
            TableSubscriptionType.setItems(data);
        }
    }

    @FXML
    void TableClicked(MouseEvent event) {

        btnApprovedRates.setDisable(true);
        btnRemoveNewRate.setDisable(true);
        sendDiscountRates.clear();
        ArrayList<DiscountRate> discountArray = new ArrayList<DiscountRate>(TableSubscriptionType.getSelectionModel().getSelectedItems());
        System.out.println(discountArray);
        System.out.println(TableSubscriptionType.getSelectionModel().getSelectedItems());
        sendDiscountRates.addAll(discountArray);

        System.out.println(discountArray.size());
        if (discountArray.size() > 0) {
            btnApprovedRates.setDisable(false);
            btnRemoveNewRate.setDisable(false);
        }
    }

    @FXML
    void handleApprovedRates(ActionEvent event) {

        updateDiscountRate("Update New Discount Rate");
    }

    @FXML
    void handleRemoveNewRate(ActionEvent event) {

        updateDiscountRate("Remove New Discount Rate");
    }

    void updateDiscountRate(String queryName) {

        ArrayList<String> paramArray = new ArrayList<>();
        for (DiscountRate temp : sendDiscountRates) {
            paramArray.clear();
            paramArray.add(queryName);
            paramArray.add(temp.getSubscriptionType());
            myController.GetDiscountRatesData(paramArray);
        }
        paramArray.clear();
        paramArray.add("Get Discount Rates Data");
        myController.GetDiscountRatesData(paramArray);
    }

}