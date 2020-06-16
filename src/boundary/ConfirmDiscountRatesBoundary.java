package boundary;

import Contollers.ConfirmDiscountRatesController;
import Contollers.FormValidation;
import entity.Costumer;
import entity.DiscountRate;
import entity.EditingCell;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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

    @FXML
    private TableView<DiscountRate> TableSubscriptionType;

    @FXML
    private TableColumn CheckboxColumn;

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

    @Override
    public void initData(Object data) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.formValidation = FormValidation.getValidator();

        btnApprovedRates.setDisable(true);
        /*ChooseSubscriptionTypeCombo.setItems(SubscriptionType);
        ShowCurrentRateTXT.setVisible(false);

        /*  set all fields validators */
        formValidation();
        myController.GetDiscountRatesData(/*"Get Discount Rates Data"*/);

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

    /**
     * this method will set the costumer table and the cell edit functions
     * when the page initialized.
     */
    public void setSubscriptionTypeTable(ArrayList<Costumer> cosArray) {


    }

    /*private void setColomsCells() {

        //Create a customer cell factory so that cells can support editing.
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new EditingCell();
            }
        };
        CheckboxColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Costumer, String> param) {
                Costumer treeItem = param.getValue();
                Costumer costumer = treeItem;

                String temp = costumer.getPurchasePlan();
                return new SimpleObjectProperty<String>(temp);
            }
        });
    }*/

    public void setDiscountRatesData(ArrayList<DiscountRate> resultList) {

        SubscriptionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("newDiscountRate"));
        CurrentPriceColumnColumn.setCellValueFactory(new PropertyValueFactory<>("currentDiscountRatecurrentDiscountRate"));
        NewPriceColumn.setCellValueFactory(new PropertyValueFactory<>("newDiscountRate"));

        ObservableList<DiscountRate> data = FXCollections.observableArrayList(resultList);
        TableSubscriptionType.setItems(data);
    }

    @FXML
    void handleApprovedRates(ActionEvent event) {

    }

    @FXML
    void handleRemoveNewRate(ActionEvent event) {

    }

}