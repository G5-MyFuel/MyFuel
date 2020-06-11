package boundary;

import Contollers.FormValidation;
import Contollers.GeneratingReportsStationManagerController;
import com.jfoenix.controls.JFXComboBox;
import entity.PurchasesReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GeneratingReportsStationManagerBoundary implements Initializable {

    /**
     * The supervisor boundary controller.
     */
    private final GeneratingReportsStationManagerController myController = new GeneratingReportsStationManagerController(this);
    private FormValidation formValidation;
    private final Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    ArrayList<PurchasesReport> PurchasesArray = new ArrayList<>();

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSignout;

    @FXML
    private JFXButton btnQuarterlyRevenueReport;

    @FXML
    private JFXButton btnPurchasesReport;

    @FXML
    private JFXButton btnQuantityItemsInStockReport;

    @FXML
    private JFXComboBox<String> ChooseReportYearCombo;

    @FXML
    private JFXComboBox<String> ChooseReportQuarterCombo;

    @FXML
    private Label TotalRevenueLabel;

    @FXML
    private JFXTextField ShowTotalRevenueTXT;

    @FXML
    private TableView<PurchasesReport> PurchasesReportTable;

    @FXML
    private TableColumn<PurchasesReport, String> FuelTypeColumn;

    @FXML
    private TableColumn<PurchasesReport, String> QuantityPurchasedColumn;

    @FXML
    private TableColumn<PurchasesReport, String> SalesAmountColumn;


    private final ObservableList<String> ReportsType = FXCollections.observableArrayList("Quarterly revenue report",
            "Purchases report", "Quantity of items in stock report");
    private final ObservableList<String> YearList = FXCollections.observableArrayList("2020", "2019", "2018", "2017", "2016", "2015",
            "2014", "2013", "2012", "2011", "2010");
    private final ObservableList<String> quarterList = FXCollections.observableArrayList("First", "Second", "Third", "Fourth");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.formValidation = FormValidation.getValidator();
        ChooseReportYearCombo.setVisible(false);
        ChooseReportQuarterCombo.setVisible(false);
        TotalRevenueLabel.setVisible(false);
        ShowTotalRevenueTXT.setVisible(false);
        PurchasesReportTable.setVisible(false);

        /*  set all fields validators */
        formValidation();
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

    @FXML
    void handleChooseReportYear(ActionEvent event) {

        ChooseReportQuarterCombo.setVisible(true);
        ChooseReportQuarterCombo.setItems(quarterList);
    }

    @FXML
    void handleChooseReportQuarter(ActionEvent event) {

        String startDate = ChooseReportYearCombo.getValue();
        String endDate = ChooseReportYearCombo.getValue();
        switch (ChooseReportQuarterCombo.getValue()) {
            case "First":
                startDate = startDate + "-01-01";
                endDate = endDate + "-03-31";
                break;
            case "Second":
                startDate = startDate + "-04-01";
                endDate = endDate + "-06-30";
                break;
            case "Third":
                startDate = startDate + "-07-01";
                endDate = endDate + "-09-30";
                break;
            case "Fourth":
                startDate = startDate + "-10-01";
                endDate = endDate + "-12-31";
                break;
            default:
                break;
        }
        myController.GetReportData("Quarterly revenue report", startDate, endDate); //start the process that will ask server to execute quarry and get the table details
    }

    public void setQuarterlyData(String revenue) {
        TotalRevenueLabel.setVisible(true);
        ShowTotalRevenueTXT.setVisible(true);
        ShowTotalRevenueTXT.setText(revenue);
    }

    public void setPurchasesData(PurchasesReport resultList) {

        PurchasesArray.add(resultList);
        System.out.println(resultList);
        PurchasesReportTable.setVisible(true);
        FuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("FuelType"));
        QuantityPurchasedColumn.setCellValueFactory(new PropertyValueFactory<>("QuantityPurchased"));
        SalesAmountColumn.setCellValueFactory(new PropertyValueFactory<>("SalesAmount"));

        ObservableList<PurchasesReport> data = FXCollections.observableArrayList(PurchasesArray);
        PurchasesReportTable.setItems(data);
    }


    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handlePurchasesReport(MouseEvent event) {

        PurchasesReportTable.setVisible(true);
        myController.GetReportData("Purchases report","Diesel",""); //start the process that will ask server to execute quarry and get the table details
        myController.GetReportData("Purchases report","Gasoline 95",""); //start the process that will ask server to execute quarry and get the table details
        myController.GetReportData("Purchases report","Scooter fuel",""); //start the process that will ask server to execute quarry and get the table details
    }

    @FXML
    void handleQuantityItemsInStockReport(MouseEvent event) {

    }

    @FXML
    void handleQuarterlyRevenueReport(MouseEvent event) {

        ChooseReportYearCombo.setVisible(true);
        ChooseReportYearCombo.setItems(YearList);
    }

    @FXML
    void handleTotalRevenue(ActionEvent event) {

    }
}