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

    @Override
    public void initData(Object data) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.formValidation = FormValidation.getValidator();

        btnApprovedRates.setDisable(true);
        btnRemoveNewRate.setDisable(true);
        /*ChooseSubscriptionTypeCombo.setItems(SubscriptionType);
        ShowCurrentRateTXT.setVisible(false);

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

        TableSubscriptionType.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        SubscriptionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("subscriptionType"));
        CurrentPriceColumnColumn.setCellValueFactory(new PropertyValueFactory<>("currentDiscountRate"));
        NewPriceColumn.setCellValueFactory(new PropertyValueFactory<>("newDiscountRate"));

        ObservableList<DiscountRate> data = FXCollections.observableArrayList(resultList);
        TableSubscriptionType.setItems(data);

        //ArrayList<DiscountRate> discountArray = new ArrayList<DiscountRate>(TableSubscriptionType.getSelectionModel().getSelectedItems());

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

        /*for (DiscountRate temp : sendDiscountRates)
            if(temp.get)    סטטוס*/

        System.out.println(discountArray.size());
        if (discountArray.size() > 0) {
            btnApprovedRates.setDisable(false);
            btnRemoveNewRate.setDisable(false);
        }

    }

    @FXML
    void handleApprovedRates(ActionEvent event) {

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Update New Discount Rate");
        for (DiscountRate temp : sendDiscountRates)
            paramArray.add(temp.getSubscriptionType());
        myController.GetDiscountRatesData(paramArray);
        paramArray.clear();
        paramArray.add("Get Discount Rates Data");
        myController.GetDiscountRatesData(paramArray);
    }

    @FXML
    void handleRemoveNewRate(ActionEvent event) {

    }

}