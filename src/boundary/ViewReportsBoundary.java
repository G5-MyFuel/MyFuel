package boundary;

import Contollers.ViewReportsController;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This department is responsible for controlling "ViewReportsFXML" page
 * Allows administrator to view Quarterly Revenue Report, Purchases Report
 * And Quantity Items In Stock Report
 *
 * @author Nir Asulin
 * @see ViewReportsController - the form's logic class
 */
public class ViewReportsBoundary implements DataInitializable {

    /**
     * A parameters that represents who enters the page
     */
    String managerID;
    String managerCompany;
    String managerStation;
    String ReportYearAndQuarter;

    /**
     * The supervisor boundary controller.
     */
    private final ViewReportsController myController = new ViewReportsController(this);

    /**
     * Gui variables:
     */
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

    @FXML
    private Label NoDataLabel;

    /**
     * For enter data to combo-boxes
     */
    private ObservableList<String> YearList;
    private final ObservableList<String> quarterList = FXCollections.observableArrayList("First", "Second", "Third", "Fourth");

    /**
     * This method allows to save information sent when uploading the page (user id)
     * In addition initializes the variables, fields, and combo-boxes
     * What is initialized will appear when the screen is raised
     *
     * @param data - The data sent to the boundary
     */
    @Override
    public void initData(Object data) {

        this.managerID = (String) data;
        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Get Manager data");
        paramArray.add(managerID);
        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details


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
        NoDataLabel.setVisible(false);

        //set tool tips
        Tooltip.install(QuestionMark1, createToolTip("Each station got a unique number that identify the station."));
        Tooltip.install(QuestionMark2, createToolTip("Generate report for annual quarter revenue and display total revenues."));
        Tooltip.install(QuestionMark3, createToolTip("Generate detailed report for total purchases in your station."));
        Tooltip.install(QuestionMark4, createToolTip("Generate report of item amount that correct in stock.\nSave button will save the report so the company manager could see it."));
    }

    /**
     * This method will set the Manager Data when we will initialize the page.
     *
     * @param resultList
     */
    public void setManagerData(ArrayList<String> resultList) {

        managerCompany = resultList.get(0);
        managerStation = resultList.get(1);
    }

    /**
     * This method will send a query to bring year list Data for the selected station.
     */
    public void getYearListData() {

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Get YearList data");
        paramArray.add(managerCompany);
        paramArray.add(managerStation);
        paramArray.add(managerCompany);
        paramArray.add(managerStation);
        paramArray.add(managerCompany);
        paramArray.add(managerStation);
        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
    }

    /**
     * This method will set the year list Data for the selected station.
     *
     * @param resultList - The result received from the DB
     */
    public void setYearListData(ArrayList<String> resultList) {

        YearList = FXCollections.observableArrayList(resultList);
        System.out.println(YearList);
        ObservableList<String> emptyList = FXCollections.observableArrayList("");
        ChooseReportQuarterCombo.setItems(emptyList);
        ChooseReportYearCombo.setItems(YearList);
        if (resultList.size() == 0) {
            makePageInvisible();
            NoDataLabel.setText("No info available for this station number!");
        } else
            ChooseReportYearCombo.setVisible(true);
    }

    private void makePageInvisible() {
        ChooseReportYearCombo.setVisible(false);
        ChooseReportQuarterCombo.setVisible(false);
        QuantityItemsStockTxt.setVisible(false);
        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);
        btnViewQuarterlyRevenueReport.setVisible(false);
        btnViewPurchasesReport.setVisible(false);
        btnViewQuantityItemsInStockReport.setVisible(false);
        imgViewQuantityItemsInStockReport.setVisible(false);
        imgViewPurchasesReport.setVisible(false);
        imgViewQuarterlyRevenueReport.setVisible(false);
        QuestionMark2.setVisible(false);
        QuestionMark3.setVisible(false);
        QuestionMark4.setVisible(false);
        NoDataLabel.setLayoutX(38);
        NoDataLabel.setLayoutY(195);
        NoDataLabel.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void handleEnterStationNumber(ActionEvent event) {

    }

    /**
     * This method will save the Station Number that admin enter
     *
     * @param event
     */
    @FXML
    void handleOKbtn(ActionEvent event) {

        if (!(EnterStationNumberTXT.getText().equals(""))) {
            NoDataLabel.setVisible(false);
            NoDataLabel.setLayoutX(38);
            NoDataLabel.setLayoutY(514);
            managerStation = EnterStationNumberTXT.getText();
            getYearListData();

            QuantityItemsStockTxt.setVisible(false);
            ViewPurchasesReportTable.setVisible(false);
            ViewQuantityReportTable.setVisible(false);
            ViewTotalRevenueTXT.setVisible(false);

        } else {
            makePageInvisible();
            NoDataLabel.setText("You must enter a station number!");
        }
    }

    /**
     * This method will appears appropriate buttons for Quarterly Revenue Report
     * And sends a query to show this report
     *
     * @param event
     */
    @FXML
    void handleViewQuarterlyRevenueReport(MouseEvent event) {


        QuantityItemsStockTxt.setVisible(false);
        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Check if exists Quarterly revenue report");
        paramArray.add(managerCompany);
        paramArray.add(managerStation);
        GetReportYearAndQuarter();
        paramArray.add(ReportYearAndQuarter);
        paramArray.add(ReportYearAndQuarter);
        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
    }

    /**
     * This method check if Quarterly Report is exists for a given quarter from the data base result.
     *
     * @param isExsits - The result received from the DB
     */
    public void checkIfExistsQuarterly(String isExsits) {

        if (isExsits.equals("1")) {
            ArrayList<String> paramArray = new ArrayList<>();
            paramArray.add("View Quarterly revenue report");
            paramArray.add(managerCompany);
            paramArray.add(managerStation);
            GetReportYearAndQuarter();
            paramArray.add(ReportYearAndQuarter);
            paramArray.add(ReportYearAndQuarter);
            myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
        } else {
            NoDataLabel.setText("*There is no data for this station currently!");
            NoDataLabel.setVisible(true);
            ViewTotalRevenueTXT.setVisible(false);
        }

    }


    /**
     * This method will set Quarterly revenue Data
     *
     * @param revenue
     */
    public void setQuarterlyData(String revenue) {

        NoDataLabel.setVisible(false);
        ViewTotalRevenueTXT.setText(revenue);
        ViewTotalRevenueTXT.setVisible(true);
    }

    /**
     * This method will appears appropriate buttons for Purchases Report
     * And sends a query to show this report
     *
     * @param event
     */
    @FXML
    void handleViewPurchasesReport(MouseEvent event) {

        QuantityItemsStockTxt.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Check if exists Purchases report");
        paramArray.add(managerCompany);
        paramArray.add(managerStation);
        GetReportYearAndQuarter();
        paramArray.add(ReportYearAndQuarter);
        paramArray.add(ReportYearAndQuarter);
        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
    }

    /**
     * This method check if Purchases Report is exists for a given quarter from the data base result.
     *
     * @param isExsits - The result received from the DB
     */
    public void checkIfExistsPurchases(String isExsits) {

        if (isExsits.equals("1")) {
            ArrayList<String> paramArray = new ArrayList<>();
            paramArray.add("View Purchases report");
            paramArray.add(managerCompany);
            paramArray.add(managerStation);
            GetReportYearAndQuarter();
            paramArray.add(ReportYearAndQuarter);
            paramArray.add(ReportYearAndQuarter);
            myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
        } else {
            NoDataLabel.setText("*There is no data for this station currently!");
            NoDataLabel.setVisible(true);
            ViewTotalRevenueTXT.setVisible(false);
        }

    }

    /**
     * This method will set Purchases Data
     *
     * @param resultList
     */
    public void setPurchasesData(ArrayList<PurchasesReport> resultList) {

        NoDataLabel.setVisible(false);
        FuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        QuantityPurchasedColumn.setCellValueFactory(new PropertyValueFactory<>("quantityPurchased"));
        SalesAmountColumn.setCellValueFactory(new PropertyValueFactory<>("salesAmount"));

        ObservableList<PurchasesReport> data = FXCollections.observableArrayList(resultList);
        ViewPurchasesReportTable.setItems(data);
        ViewPurchasesReportTable.setVisible(true);
    }

    /**
     * This method will appears appropriate buttons for Quantity Items In Stock Report
     * And sends a query to show this report
     *
     * @param event
     */
    @FXML
    void handleViewQuantityItemsInStockReport(MouseEvent event) {

        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);
        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Check if exists Quantity of items in stock report");
        paramArray.add(managerCompany);
        paramArray.add(managerStation);
        GetReportYearAndQuarter();
        paramArray.add(ReportYearAndQuarter);
        paramArray.add(ReportYearAndQuarter);
        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details

    }

    /**
     * This method check if Purchases Report is exists for a given quarter from the data base result.
     *
     * @param isExsits - The result received from the DB
     */
    public void checkIfExistsQuantityItemsStock(String isExsits) {

        if (isExsits.equals("1")) {
            ArrayList<String> paramArray = new ArrayList<>();
            paramArray.add("View Quantity of items in stock report");
            paramArray.add(managerCompany);
            paramArray.add(managerStation);
            GetReportYearAndQuarter();
            paramArray.add(ReportYearAndQuarter);
            paramArray.add(ReportYearAndQuarter);
            myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
        } else {
            NoDataLabel.setText("*There is no data for this station currently!");
            NoDataLabel.setVisible(true);
            ViewTotalRevenueTXT.setVisible(false);
        }
    }

    /**
     * This method will set Quantity Items In Stock Data
     *
     * @param resultList
     */
    public void setQuantityItemsStockData(ArrayList<QuantityItemsStockReport> resultList) {

        NoDataLabel.setVisible(false);
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

    /**
     * This method will hide report selection until quarter selection
     *
     * @param event
     */
    @FXML
    void handleChooseReportYear(ActionEvent event) {

        ChooseReportQuarterCombo.setItems(quarterList);
        NoDataLabel.setVisible(false);
        QuantityItemsStockTxt.setVisible(false);
        ViewPurchasesReportTable.setVisible(false);
        ViewQuantityReportTable.setVisible(false);
        ViewTotalRevenueTXT.setVisible(false);
        ChooseReportQuarterCombo.setVisible(true);
    }

    /**
     * This method will show report selection
     *
     * @param event
     */
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

    /**
     * This method will info When standing with the cursor on the question mark
     *
     * @param htmlStr
     * @return
     */
    private Tooltip createToolTip(String htmlStr) {
        Tooltip thisToolTip = new Tooltip();

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.loadContent(htmlStr);

        thisToolTip.setFont(Font.font("Arial", FontPosture.ITALIC, 1.5));
        thisToolTip.setTextAlignment(TextAlignment.CENTER);
        thisToolTip.setStyle("\n"
                + "-fx-background: rgba(30,30,30);"
                + "-fx-text-fill: white;"
                + " -fx-background-color: rgba(30,30,30,0.8);"
                + " -fx-background-radius: 6px;"
                + " -fx-background-insets: 0;"
                + " -fx-padding: 0.667em 0.75em 0.667em 0.75em; /* 10px */"
                + " -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.5) , 10, 0.0 , 0 , 3 );"
                + " -fx-font-size: 0.85em;");


        thisToolTip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        thisToolTip.setGraphic(browser);
        thisToolTip.setAutoHide(false);
        thisToolTip.setMaxWidth(250);
        thisToolTip.setMaxHeight(100);
        thisToolTip.setGraphicTextGap(1.5);

        return thisToolTip;
    }

    /**
     * This method will save the chosen Year And Quarter
     */
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