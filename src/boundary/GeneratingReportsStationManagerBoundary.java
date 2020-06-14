package boundary;

import Contollers.FormValidation;
import Contollers.GeneratingReportsStationManagerController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.PurchasesReport;
import entity.QuantityItemsStockReport;
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

public class GeneratingReportsStationManagerBoundary implements DataInitializable {

    String managerID;
    String managerCompany;
    String managerStation;
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

    @FXML
    private Label QuantityItemsStockTxt;

    @FXML
    private Label ReportSentMessageLabel;

    @FXML
    private TableView<QuantityItemsStockReport> QuantityReportTable;

    @FXML
    private TableColumn<QuantityItemsStockReport, String> FuelTypeQuantityReportColumn;

    @FXML
    private TableColumn<QuantityItemsStockReport, String> AvailableInventoryColumn;

    private final ObservableList<String> ReportsType = FXCollections.observableArrayList("Quarterly revenue report",
            "Purchases report", "Quantity of items in stock report");
    private final ObservableList<String> YearList = FXCollections.observableArrayList("2020", "2019", "2018", "2017", "2016", "2015",
            "2014", "2013", "2012", "2011", "2010");
    private final ObservableList<String> quarterList = FXCollections.observableArrayList("First", "Second", "Third", "Fourth");

    @Override
    public void initData(Object data) {
        this.managerID = (String) data;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        managerID = "109268386";
        myController.GetReportData("Get Manager data", managerID, ""); //start the process that will ask server to execute quarry and get the table details
        //SELECT * FROM Employee WHERE Employee.employeeID LIKE "109268386"
        this.formValidation = FormValidation.getValidator();
        ChooseReportYearCombo.setVisible(false);
        ChooseReportQuarterCombo.setVisible(false);
        TotalRevenueLabel.setVisible(false);
        ShowTotalRevenueTXT.setVisible(false);
        PurchasesReportTable.setVisible(false);
        QuantityItemsStockTxt.setVisible(false);
        QuantityReportTable.setVisible(false);
        ReportSentMessageLabel.setVisible(false);

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
    void handleQuarterlyRevenueReport(MouseEvent event) {

        PurchasesReportTable.setVisible(false);
        QuantityItemsStockTxt.setVisible(false);
        QuantityReportTable.setVisible(false);
        ChooseReportYearCombo.setVisible(false);
        ChooseReportQuarterCombo.setVisible(false);
        TotalRevenueLabel.setVisible(false);
        ShowTotalRevenueTXT.setVisible(false);
        ReportSentMessageLabel.setVisible(false);
        ShowTotalRevenueTXT.clear();
        //ChooseReportYearCombo.getItems().clear();
        //ChooseReportQuarterCombo.getItems().clear();
        //ChooseReportYearCombo.setPromptText("Please select a year");
        ChooseReportYearCombo.setItems(YearList);
        ChooseReportYearCombo.setVisible(true);

    }

    @FXML
    void handleChooseReportYear(ActionEvent event) {

        //ChooseReportQuarterCombo.setPromptText("Please Select a quarter");
        ChooseReportQuarterCombo.setItems(quarterList);
        ChooseReportQuarterCombo.setVisible(true);
        if (ChooseReportQuarterCombo.getValue() != null)
            GetQuarterlyData();

    }

    @FXML
    void handleChooseReportQuarter(ActionEvent event) {

        GetQuarterlyData();
    }

    void GetQuarterlyData() {

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
        ShowTotalRevenueTXT.setText(revenue);
        ShowTotalRevenueTXT.setVisible(true);
        ReportSentMessageLabel.setVisible(true);
    }

    @FXML
    void handlePurchasesReport(MouseEvent event) {

        PurchasesReportTable.setVisible(false);
        PurchasesReportTable.getItems().clear();
        ReportSentMessageLabel.setVisible(false);
        ChooseReportYearCombo.setVisible(false);
        ChooseReportQuarterCombo.setVisible(false);
        TotalRevenueLabel.setVisible(false);
        ShowTotalRevenueTXT.setVisible(false);
        QuantityItemsStockTxt.setVisible(false);
        QuantityReportTable.setVisible(false);
        myController.GetReportData("Purchases report", "Diesel", ""); //start the process that will ask server to execute quarry and get the table details
        myController.GetReportData("Purchases report", "Gasoline 95", ""); //start the process that will ask server to execute quarry and get the table details
        myController.GetReportData("Purchases report", "Scooter fuel", ""); //start the process that will ask server to execute quarry and get the table details
    }

    private void cleanArray(ArrayList<PurchasesReport> purchasesArray) {

        for (int i = 0; i < 3; i++)
            PurchasesArray.remove(0);

    }

    public void setPurchasesData(PurchasesReport resultList) {

        PurchasesArray.add(resultList);
        //PurchasesReportTable.setVisible(true);
        if (PurchasesArray.size() == 3) {

            FuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
            QuantityPurchasedColumn.setCellValueFactory(new PropertyValueFactory<>("quantityPurchased"));
            SalesAmountColumn.setCellValueFactory(new PropertyValueFactory<>("salesAmount"));
            ObservableList<PurchasesReport> data = FXCollections.observableArrayList(PurchasesArray);
            PurchasesReportTable.setItems(data);
            PurchasesReportTable.setVisible(true);
            ReportSentMessageLabel.setVisible(true);
            cleanArray(PurchasesArray);
        }
    }

    @FXML
    void handleQuantityItemsInStockReport(MouseEvent event) {

        QuantityReportTable.setVisible(false);
        QuantityItemsStockTxt.setVisible(false);
        QuantityReportTable.getItems().clear();
        ReportSentMessageLabel.setVisible(false);
        ChooseReportYearCombo.setVisible(false);
        ChooseReportQuarterCombo.setVisible(false);
        TotalRevenueLabel.setVisible(false);
        ShowTotalRevenueTXT.setVisible(false);
        PurchasesReportTable.setVisible(false);
        QuantityItemsStockTxt.setText("Quantity of items in stock for" + managerCompany + " in Station " + managerStation + ":");
        myController.GetReportData("Quantity of items in stock report", managerCompany, ""); //start the process that will ask server to execute quarry and get the table details
    }

    public void setQuantityItemsStockData(ArrayList<QuantityItemsStockReport> resultList) {

        QuantityReportTable.setVisible(true);
        FuelTypeQuantityReportColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        AvailableInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("availableInventory"));

        ObservableList<QuantityItemsStockReport> data = FXCollections.observableArrayList(resultList);
        QuantityReportTable.setItems(data);
        QuantityItemsStockTxt.setVisible(true);
        ReportSentMessageLabel.setVisible(true);
    }

    public void setManagerData(ArrayList<String> resultList) {

        managerCompany = resultList.get(0);
        managerStation = resultList.get(1);
    }


    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleTotalRevenue(ActionEvent event) {

    }


}