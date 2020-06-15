package boundary;

import Contollers.VieReportsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class VieReportsBoundary implements Initializable {

    String managerID;
    String managerCompany;
    String managerStation;

    /**
     * The supervisor boundary controller.
     */

    private final VieReportsController myController = new VieReportsController(this);
    //private FormValidation formValidation;
    //private final Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    //ArrayList<PurchasesReport> PurchasesArray = new ArrayList<>();

    @FXML
    private JFXButton btnViewQuarterlyRevenueReport;

    @FXML
    private JFXButton btnViewPurchasesReport;

    @FXML
    private JFXButton btnViewQuantityItemsInStockReport;

    @FXML
    private JFXComboBox<String> ChooseReportYearCombo;

    @FXML
    private JFXComboBox<String> ChooseReportQuarterCombo;

    @FXML
    private Label QuantityItemsStockTxt;

    @FXML
    private TableView<?> ViewPurchasesReportTable;

    @FXML
    private TableColumn<?, ?> FuelTypeColumn;

    @FXML
    private TableColumn<?, ?> QuantityPurchasedColumn;

    @FXML
    private TableColumn<?, ?> SalesAmountColumn;

    @FXML
    private TableView<?> ViewQuantityReportTable;

    @FXML
    private TableColumn<?, ?> FuelTypeQuantityReportColumn;

    @FXML
    private TableColumn<?, ?> AvailableInventoryColumn;

    @FXML
    private JFXTextField EnterStationNumberTXT;

    @FXML
    private JFXTextField ViewTotalRevenueTXT;

    @FXML
    private ImageView imgViewQuantityItemsInStockReport;

    @FXML
    private ImageView imgViewPurchasesReport;

    @FXML
    private ImageView imgViewQuarterlyRevenueReport;

    private final ObservableList<String> ReportsType = FXCollections.observableArrayList("Quarterly revenue report",
            "Purchases report", "Quantity of items in stock report");
    private final ObservableList<String> YearList = FXCollections.observableArrayList("2020", "2019", "2018", "2017", "2016", "2015",
            "2014", "2013", "2012", "2011", "2010");
    private final ObservableList<String> quarterList = FXCollections.observableArrayList("First", "Second", "Third", "Fourth");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        managerCompany = "YELLOW";
        EnterStationNumberTXT.setVisible(true);
        btnViewQuarterlyRevenueReport.setVisible(false);
        btnViewPurchasesReport.setVisible(false);
        btnViewQuantityItemsInStockReport.setVisible(false);
        ChooseReportYearCombo.setVisible(false);
        ChooseReportQuarterCombo.setVisible(false);
        QuantityItemsStockTxt.setVisible(false);
        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);
        imgViewQuantityItemsInStockReport.setVisible(false);
        imgViewPurchasesReport.setVisible(false);
        imgViewQuarterlyRevenueReport.setVisible(false);
    }

    @FXML
    void handleEnterStationNumber(ActionEvent event) {

        managerStation = EnterStationNumberTXT.getText();
        btnViewQuarterlyRevenueReport.setVisible(true);
        btnViewPurchasesReport.setVisible(true);
        btnViewQuantityItemsInStockReport.setVisible(true);
        imgViewQuantityItemsInStockReport.setVisible(true);
        imgViewPurchasesReport.setVisible(true);
        imgViewQuarterlyRevenueReport.setVisible(true);
        ChooseReportYearCombo.setVisible(false);
        ChooseReportQuarterCombo.setVisible(false);
        QuantityItemsStockTxt.setVisible(false);
        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);

    }

    @FXML
    void handleViewQuarterlyRevenueReport(MouseEvent event) {

        ChooseReportYearCombo.setItems(YearList);
        ChooseReportYearCombo.setVisible(true);
        ChooseReportQuarterCombo.setVisible(false);
        QuantityItemsStockTxt.setVisible(false);
        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);

    }

    @FXML
    void handleChooseReportYear(ActionEvent event) {

        ChooseReportQuarterCombo.setItems(quarterList);
        ChooseReportQuarterCombo.setVisible(true);
        QuantityItemsStockTxt.setVisible(false);
        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);

    }

    @FXML
    void handleChooseReportQuarter(ActionEvent event) {

        /*String startDate = "";
        String endDate = "";*/

        QuantityItemsStockTxt.setVisible(false);
        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("View Quarterly revenue report");
        paramArray.add(managerCompany);
        paramArray.add(managerStation);
        /*paramArray.add(ChooseReportYearCombo.getValue());
        paramArray.add(ChooseReportQuarterCombo.getValue());*/
        switch (ChooseReportQuarterCombo.getValue()) {
            case "First":
                paramArray.add(ChooseReportYearCombo.getValue() + "-" + "01-01");
                /*startDate = "01-01";
                endDate = "03-31";*/
                break;
            case "Second":
                paramArray.add(ChooseReportYearCombo.getValue() + "-" + "04-01");
                /*startDate = "04-01";
                endDate = "06-30";*/
                break;
            case "Third":
                paramArray.add(ChooseReportYearCombo.getValue() + "-" + "07-01");
               /*startDate = "07-01";
                endDate = "09-30";*/
                break;
            case "Fourth":
                paramArray.add(ChooseReportYearCombo.getValue() + "-" + "10-01");
                /*startDate = "10-01";
                endDate = "12-31";*/
                break;
            default:
                break;
        }
        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
    }

    public void setQuarterlyData(String revenue) {

        System.out.println(revenue);
        ViewTotalRevenueTXT.setText(revenue);
        ViewTotalRevenueTXT.setVisible(true);
    }


    @FXML
    void handleViewPurchasesReport(MouseEvent event) {

    }

    @FXML
    void handleViewQuantityItemsInStockReport(MouseEvent event) {

    }

    @FXML
    void handleViewTotalRevenue(ActionEvent event) {

    }
}