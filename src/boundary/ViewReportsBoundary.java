package boundary;

import Contollers.ViewReportsController;
import entity.PurchasesReport;
import entity.QuantityItemsStockReport;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;

public class ViewReportsBoundary implements Initializable {

    String managerID;
    String managerCompany;
    String managerStation;
    String ReportYearAndQuarter;

    /**
     * The supervisor boundary controller.
     */

    private final ViewReportsController myController = new ViewReportsController(this);
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
    private TableView<PurchasesReport> ViewPurchasesReportTable;

    @FXML
    private TableColumn<PurchasesReport, String> FuelTypeColumn;

    @FXML
    private TableColumn<PurchasesReport, String> QuantityPurchasedColumn;

    @FXML
    private TableColumn<PurchasesReport, String> SalesAmountColumn;

    @FXML
    private TableView<QuantityItemsStockReport> ViewQuantityReportTable;

    @FXML
    private TableColumn<QuantityItemsStockReport, String> FuelTypeQuantityReportColumn;

    @FXML
    private TableColumn<QuantityItemsStockReport, String> AvailableInventoryColumn;

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

    @FXML
    private ImageView QuestionMark1;

    @FXML
    private ImageView QuestionMark2;

    @FXML
    private ImageView QuestionMark3;

    @FXML
    private ImageView QuestionMark4;

    @FXML
    private Button btnOK;

    private final ObservableList<String> YearList = FXCollections.observableArrayList("2020", "2019", "2018", "2017", "2016", "2015",
            "2014", "2013", "2012", "2011", "2010");
    private final ObservableList<String> quarterList = FXCollections.observableArrayList("First", "Second", "Third", "Fourth");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        managerCompany = "YELLOW";
        EnterStationNumberTXT.setVisible(true);
        QuestionMark1.setVisible(true);

        btnViewQuarterlyRevenueReport.setVisible(false);
        btnViewPurchasesReport.setVisible(false);
        btnViewQuantityItemsInStockReport.setVisible(false);
        imgViewQuantityItemsInStockReport.setVisible(false);
        imgViewPurchasesReport.setVisible(false);
        imgViewQuarterlyRevenueReport.setVisible(false);

        ChooseReportYearCombo.setVisible(false);
        ChooseReportQuarterCombo.setVisible(false);

        QuantityItemsStockTxt.setVisible(false);

        ViewPurchasesReportTable.setVisible(false);

        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);

        QuestionMark2.setVisible(false);
        QuestionMark3.setVisible(false);
        QuestionMark4.setVisible(false);
    }

    @FXML
    void handleEnterStationNumber(ActionEvent event) {

    }

    @FXML
    void handleOKbtn(ActionEvent event) {

        managerStation = EnterStationNumberTXT.getText();
        ChooseReportYearCombo.setItems(YearList);
        ChooseReportYearCombo.setVisible(true);
        ChooseReportQuarterCombo.setItems(quarterList);
        ChooseReportQuarterCombo.setVisible(true);

        QuantityItemsStockTxt.setVisible(false);
        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);
    }

    @FXML
    void handleViewQuarterlyRevenueReport(MouseEvent event) {

        QuantityItemsStockTxt.setVisible(false);
        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("View Quarterly revenue report");
        paramArray.add(managerCompany);
        paramArray.add(managerStation);
        GetReportYearAndQuarter();
        paramArray.add(ReportYearAndQuarter);
        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
    }

    public void setQuarterlyData(String revenue) {

        System.out.println(revenue);
        ViewTotalRevenueTXT.setText(revenue);
        ViewTotalRevenueTXT.setVisible(true);
    }

    @FXML
    void handleViewPurchasesReport(MouseEvent event) {

        QuantityItemsStockTxt.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("View Purchases report");
        paramArray.add(managerCompany);
        paramArray.add(managerStation);
        GetReportYearAndQuarter();
        paramArray.add(ReportYearAndQuarter);
        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
    }

    public void setPurchasesData(ArrayList<PurchasesReport> resultList) {

        System.out.println(resultList);
        FuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        QuantityPurchasedColumn.setCellValueFactory(new PropertyValueFactory<>("quantityPurchased"));
        SalesAmountColumn.setCellValueFactory(new PropertyValueFactory<>("salesAmount"));

        ObservableList<PurchasesReport> data = FXCollections.observableArrayList(resultList);
        ViewPurchasesReportTable.setItems(data);
        ViewPurchasesReportTable.setVisible(true);
    }

    @FXML
    void handleViewQuantityItemsInStockReport(MouseEvent event) {

        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);
        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("View Quantity of items in stock report");
        paramArray.add(managerCompany);
        paramArray.add(managerStation);
        GetReportYearAndQuarter();
        paramArray.add(ReportYearAndQuarter);
        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details

    }

    public void setQuantityItemsStockData(ArrayList<QuantityItemsStockReport> resultList) {

        resultList.get(0).setFuelType("Gasoline 95");
        resultList.get(1).setFuelType("Diesel");
        resultList.get(2).setFuelType("Scooter fuel");
        FuelTypeQuantityReportColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        AvailableInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("availableInventory"));

        ObservableList<QuantityItemsStockReport> data = FXCollections.observableArrayList(resultList);
        ViewQuantityReportTable.setItems(data);
        ViewQuantityReportTable.setVisible(true);
        QuantityItemsStockTxt.setVisible(true);
    }

    @FXML
    void handleChooseReportYear(ActionEvent event) {

        //ReportYear = ChooseReportYearCombo.getValue();

        QuantityItemsStockTxt.setVisible(false);
        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);
    }

    @FXML
    void handleChooseReportQuarter(ActionEvent event) {

        QuantityItemsStockTxt.setVisible(false);
        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);

        btnViewQuarterlyRevenueReport.setVisible(true);
        btnViewPurchasesReport.setVisible(true);
        btnViewQuantityItemsInStockReport.setVisible(true);
        imgViewQuantityItemsInStockReport.setVisible(true);
        imgViewPurchasesReport.setVisible(true);
        imgViewQuarterlyRevenueReport.setVisible(true);
        QuestionMark1.setVisible(true);
        QuestionMark2.setVisible(true);
        QuestionMark3.setVisible(true);
        QuestionMark4.setVisible(true);
    }

    @FXML
    void handleViewTotalRevenue(ActionEvent event) {

    }

    public void GetReportYearAndQuarter() {

        switch (ChooseReportQuarterCombo.getValue()) {
            case "First":
                ReportYearAndQuarter = ChooseReportYearCombo.getValue() + "-" + "01-01";
                break;
            case "Second":
                ReportYearAndQuarter = ChooseReportYearCombo.getValue() + "-" + "04-01";
                break;
            case "Third":
                ReportYearAndQuarter = ChooseReportYearCombo.getValue() + "-" + "07-01";
                break;
            case "Fourth":
                ReportYearAndQuarter = ChooseReportYearCombo.getValue() + "-" + "10-01";
                break;
            default:
                break;
        }
    }
}