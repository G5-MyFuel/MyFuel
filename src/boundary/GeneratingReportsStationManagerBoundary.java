package boundary;

import Contollers.FormValidation;
import Contollers.GeneratingReportsMarketingManagerController;
import Contollers.GeneratingReportsStationManagerController;
import com.jfoenix.controls.JFXButton;
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
 * This department is responsible for controlling "GeneratingReportsStationManagerFXML" page
 * Allows station manager to generate Quarterly Revenue Report, Purchases Report
 * And Quantity Items In Stock Report
 *
 * @author Nir Asulin
 * @see GeneratingReportsStationManagerController - the form's logic class
 */
public class GeneratingReportsStationManagerBoundary implements DataInitializable {

    /**
     * A parameters that represents who enters the page
     */
    String managerID;
    String managerCompany;
    String managerStation;

    /**
     * The supervisor boundary controller.
     */
    private final GeneratingReportsStationManagerController myController = new GeneratingReportsStationManagerController(this);

    /**
     * For proper validation
     */
    private final Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);

    /**
     * For saving Quarterly revenue Data
     */
    String sendRevenue;

    /**
     * For saving Purchases Data
     */
    ArrayList<PurchasesReport> sendPurchases = new ArrayList<>();

    /**
     * For saving Quantity Items Stock Data
     */
    ArrayList<QuantityItemsStockReport> sendQuantityItemsStockReport = new ArrayList<>();

    /**
     * Gui variables:
     */
    @FXML
    private JFXButton btnQuarterlyRevenueReport;

    @FXML
    private JFXButton btnPurchasesReport;

    @FXML
    private JFXButton btnQuantityItemsInStockReport;

    @FXML
    private JFXTextField ShowTotalRevenueTXT;

    @FXML
    private Label QuantityItemsStockTxt;

    @FXML
    private Label ReportSentMessageLabel;

    @FXML
    private TableView<PurchasesReport> PurchasesReportTable;

    @FXML
    private TableColumn<PurchasesReport, String> FuelTypeColumn;

    @FXML
    private TableColumn<PurchasesReport, String> QuantityPurchasedColumn;

    @FXML
    private TableColumn<PurchasesReport, String> SalesAmountColumn;

    @FXML
    private TableView<QuantityItemsStockReport> QuantityReportTable;

    @FXML
    private TableColumn<QuantityItemsStockReport, String> FuelTypeQuantityReportColumn;

    @FXML
    private TableColumn<QuantityItemsStockReport, String> AvailableInventoryColumn;

    @FXML
    private Button btnSaveQuarterlyReport;

    @FXML
    private Button btnSaveQuantityReport;

    @FXML
    private Button btnSavePurchasesReport;
    @FXML
    private ImageView quartInfo;

    @FXML
    private ImageView purchaseInfo;

    @FXML
    private ImageView quantityInfo;

    /**
     * This method allows to save information sent when uploading the page (user id)
     * In addition initializes the variables and fields
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

        ShowTotalRevenueTXT.setVisible(false);
        PurchasesReportTable.setVisible(false);
        QuantityItemsStockTxt.setVisible(false);
        QuantityReportTable.setVisible(false);
        ReportSentMessageLabel.setVisible(false);
        btnSaveQuarterlyReport.setVisible(false);
        btnSavePurchasesReport.setVisible(false);
        btnSaveQuantityReport.setVisible(false);

        //set tool tips
        Tooltip.install(quartInfo, createToolTip("Generate report for annual quarter revenue and display total revenues."));
        Tooltip.install(purchaseInfo, createToolTip("Generate detailed report for total purchases in your station."));
        Tooltip.install(quantityInfo, createToolTip("Generate report of item amount that correct in stock.\nSave button will save the report so the company manager could see it."));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * This method will appears appropriate buttons for Quarterly Revenue Report
     * And sends a query to generate this report
     *
     * @param event
     */
    @FXML
    void handleQuarterlyRevenueReport(MouseEvent event) {

        ShowTotalRevenueTXT.setVisible(false);
        PurchasesReportTable.setVisible(false);
        QuantityItemsStockTxt.setVisible(false);
        QuantityReportTable.setVisible(false);
        ReportSentMessageLabel.setVisible(false);
        btnSaveQuarterlyReport.setVisible(false);
        btnSavePurchasesReport.setVisible(false);
        btnSaveQuantityReport.setVisible(false);

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Quarterly revenue report");
        paramArray.add(managerStation);
        paramArray.add(managerCompany);
        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
    }


    /**
     * This method will set Quarterly revenue Data
     *
     * @param revenue
     */
    public void setQuarterlyData(String revenue) {
        ShowTotalRevenueTXT.setText(revenue);
        sendRevenue = revenue;
        ShowTotalRevenueTXT.setVisible(true);
        btnSaveQuarterlyReport.setVisible(true);
    }

    /**
     * This method will appears appropriate buttons for Purchases Report
     * And sends a query to generate this report
     *
     * @param event
     */
    @FXML
    void handlePurchasesReport(MouseEvent event) {

        ShowTotalRevenueTXT.setVisible(false);
        PurchasesReportTable.setVisible(false);
        QuantityItemsStockTxt.setVisible(false);
        QuantityReportTable.setVisible(false);
        ReportSentMessageLabel.setVisible(false);
        btnSaveQuarterlyReport.setVisible(false);
        btnSavePurchasesReport.setVisible(false);
        btnSaveQuantityReport.setVisible(false);

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Purchases report");
        paramArray.add(managerStation);
        paramArray.add(managerCompany);
        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
    }

    /**
     * This method will set Purchases Data
     *
     * @param resultList
     */
    public void setPurchasesData(ArrayList<PurchasesReport> resultList) {

        sendPurchases.addAll(resultList);
        FuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        QuantityPurchasedColumn.setCellValueFactory(new PropertyValueFactory<>("quantityPurchased"));
        SalesAmountColumn.setCellValueFactory(new PropertyValueFactory<>("salesAmount"));

        ObservableList<PurchasesReport> data = FXCollections.observableArrayList(resultList);
        PurchasesReportTable.setItems(data);
        PurchasesReportTable.setVisible(true);
        btnSavePurchasesReport.setVisible(true);
    }

    /**
     * This method will appears appropriate buttons for Quantity Items In Stock Report
     * And sends a query to generate this report
     *
     * @param event
     */
    @FXML
    void handleQuantityItemsInStockReport(MouseEvent event) {

        ShowTotalRevenueTXT.setVisible(false);
        PurchasesReportTable.setVisible(false);
        QuantityItemsStockTxt.setVisible(false);
        QuantityReportTable.setVisible(false);
        ReportSentMessageLabel.setVisible(false);
        btnSaveQuarterlyReport.setVisible(false);
        btnSavePurchasesReport.setVisible(false);
        btnSaveQuantityReport.setVisible(false);

        QuantityItemsStockTxt.setText("Quantity of items in stock for " + managerCompany + " in Station #" + managerStation + ":");
        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Quantity of items in stock report");
        paramArray.add(managerCompany);
        paramArray.add(managerStation);
        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
    }

    /**
     * This method will set Quantity Items In Stock Data
     *
     * @param resultList
     */
    public void setQuantityItemsStockData(ArrayList<QuantityItemsStockReport> resultList) {

        System.out.println(resultList.size());
        if (resultList.size() > 0) {
            resultList.get(0).setFuelType("Gasoline 95");
            resultList.get(1).setFuelType("Diesel");
            resultList.get(2).setFuelType("Scooter fuel");
            sendQuantityItemsStockReport.addAll(resultList);
            FuelTypeQuantityReportColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
            AvailableInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("availableInventory"));

            ObservableList<QuantityItemsStockReport> data = FXCollections.observableArrayList(resultList);
            QuantityReportTable.setItems(data);
            QuantityReportTable.setVisible(true);
            QuantityItemsStockTxt.setVisible(true);
            btnSaveQuantityReport.setVisible(true);
        } else {
            QuantityItemsStockTxt.setText("No data is available for Station # " + managerStation + " at " + managerCompany + " Company");
            QuantityItemsStockTxt.setVisible(true);
        }

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

    @FXML
    void handleClicks(ActionEvent event) {

    }

    /**
     * This method will Save the Quarterly report for the administrator
     *
     * @param event
     */
    @FXML
    void handleSaveQuarterlyReportBtn(ActionEvent event) {

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Send Quarterly revenue report");
        paramArray.add(managerCompany);
        paramArray.add(managerStation);
        paramArray.add(sendRevenue);

        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
        ReportSentMessageLabel.setVisible(true);
    }

    /**
     * This method will Save the Purchases report for the administrator
     *
     * @param event
     */
    @FXML
    void handleSavePurchasesReportBtn(ActionEvent event) {


        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Send Purchases report");
        for (PurchasesReport a : sendPurchases) {
            paramArray.add(managerCompany);
            paramArray.add(managerStation);
            paramArray.add(a.getFuelType());
            paramArray.add(a.getQuantityPurchased());
            paramArray.add(a.getSalesAmount());
        }

        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
        ReportSentMessageLabel.setVisible(true);
    }

    /**
     * This method will Save the Quantity report for the administrator
     *
     * @param event
     */
    @FXML
    void handleSaveQuantityReportBtn(ActionEvent event) {

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Send Quantity of items in stock report");
        for (QuantityItemsStockReport a : sendQuantityItemsStockReport) {
            paramArray.add(managerCompany);
            paramArray.add(managerStation);
            paramArray.add(a.getFuelType());
            paramArray.add(a.getAvailableInventory());
        }

        myController.GetReportData(paramArray); //start the process that will ask server to execute quarry and get the table details
        ReportSentMessageLabel.setVisible(true);
    }

    @FXML
    void handleTotalRevenue(ActionEvent event) {

    }

}