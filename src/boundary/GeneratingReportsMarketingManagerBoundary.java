package boundary;

import Contollers.FormValidation;
import Contollers.GeneratingReportsMarketingManagerController;
import Contollers.SettingDiscountRatesController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.assets.Toast;
import entity.CommentsReport;
import entity.CustomerPeriodicCharacterizationReport;
import entity.PurchasesReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * This department is responsible for controlling "GeneratingReportsMarketingManagerFXML" page
 * Allows marketing manager to generate Comments Report for Marketing Campaign
 * And Customer Periodic Characterization Report
 *
 * @author Nir Asulin
 * @see GeneratingReportsMarketingManagerController - the form's logic class
 */
public class GeneratingReportsMarketingManagerBoundary implements DataInitializable {

    /**
     * A parameters that represents who enters the page
     */
    String managerID;
    String managerCompany;
    String managerStation;

    /**
     * The supervisor boundary controller.
     */
    private final GeneratingReportsMarketingManagerController myController = new GeneratingReportsMarketingManagerController(this);

    /**
     * For proper validation
     */
    private final Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);

    /**
     * For saving customer Total Sum
     */
    ArrayList<CustomerPeriodicCharacterizationReport> customerWithTotalSumList = new ArrayList<>();

    /**
     * For saving ShowReportMarketingCampaignTxt font and paint
     */
    Font font;
    Paint paint;

    /**
     * Gui variables:
     */
    @FXML
    private Button btnGenerateReport;

    @FXML
    private JFXComboBox<String> ChooseReportToGenerateCombo;

    @FXML
    private JFXTextField EnterOperationSaleTXT;

    @FXML
    private DatePicker EndDateBox;

    @FXML
    private DatePicker StartDateBox;

    @FXML
    private Label EndDateTxt;

    @FXML
    private Label StartDateTxt;

    @FXML
    private Label ShowReportMarketingCampaignTxt;

    @FXML
    private TableView<CommentsReport> CommentsReportForMarketingCampaignTable;

    @FXML
    private TableColumn<CommentsReport, String> CommentsReport_CustomerIDColumn;

    @FXML
    private TableColumn<CommentsReport, String> TotalAmountSpentColumn;

    @FXML
    private TableView<CustomerPeriodicCharacterizationReport> CustomerPeriodicCharacterizationReportTable;

    @FXML
    private TableColumn<CustomerPeriodicCharacterizationReport, String> CustomerPeriodicCharacterizationReport_CustomerIDCustomerPeriodicCharacterizationReportColumn;

    @FXML
    private TableColumn<CustomerPeriodicCharacterizationReport, String> YellowColumn;

    @FXML
    private TableColumn<CustomerPeriodicCharacterizationReport, String> SonolColumn;

    @FXML
    private TableColumn<CustomerPeriodicCharacterizationReport, String> PazColumn;

    @FXML
    private TableColumn<CustomerPeriodicCharacterizationReport, String> TotalColumn;

    @FXML
    private Label ERRORendBeforStart;

    @FXML
    private Label ERRORnoOperation;

    /**
     * For enter data to combo-boxes
     */
    private final ObservableList<String> ReportsType = FXCollections.observableArrayList("Comments Report for Marketing Campaign",
            "Customer Periodic Characterization Report");

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

        ChooseReportToGenerateCombo.setItems(ReportsType);

        ChooseReportToGenerateCombo.setVisible(true);
        StartDateTxt.setVisible(false);
        StartDateBox.setVisible(false);
        EndDateTxt.setVisible(false);
        EndDateBox.setVisible(false);
        EnterOperationSaleTXT.setVisible(false);
        btnGenerateReport.setVisible(false);
        ShowReportMarketingCampaignTxt.setVisible(false);
        CommentsReportForMarketingCampaignTable.setVisible(false);
        CustomerPeriodicCharacterizationReportTable.setVisible(false);

        ERRORendBeforStart.setVisible(false);
        ERRORnoOperation.setVisible(false);

        disableFutureDates();
        EndDateBox.setValue(LocalDate.now());
        StartDateBox.setValue(LocalDate.now());

        font = ShowReportMarketingCampaignTxt.getFont();
        paint = ShowReportMarketingCampaignTxt.getTextFill();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * This method will appears appropriate buttons depending on the report selection
     * And make date validation
     *
     * @param event
     */
    @FXML
    void handleChooseReportToGenerate(ActionEvent event) {

        ChooseReportToGenerateCombo.setPrefWidth(340);


        if (ChooseReportToGenerateCombo.getValue().equals("Customer Periodic Characterization Report")) {
            StartDateTxt.setVisible(true);
            StartDateBox.setVisible(true);
            EndDateTxt.setVisible(true);
            EndDateBox.setVisible(true);
            EnterOperationSaleTXT.setVisible(false);
            ERRORnoOperation.setVisible(false);
        } else {
            EnterOperationSaleTXT.clear();
            StartDateTxt.setVisible(false);
            StartDateBox.setVisible(false);
            EndDateTxt.setVisible(false);
            EndDateBox.setVisible(false);
            EnterOperationSaleTXT.setVisible(true);
        }

        checkValidDateForEndDate();
        ShowReportMarketingCampaignTxt.setVisible(false);
        CommentsReportForMarketingCampaignTable.setVisible(false);
        CustomerPeriodicCharacterizationReportTable.setVisible(false);

    }

    /**
     * This method will make date validation when manager
     * enter Start Date Box value
     *
     * @param event
     */
    @FXML
    void handleStartDateBox(ActionEvent event) {

        checkValidDateForEndDate();
    }

    /**
     * This method will make date validation when manager
     * enter End Date Box value
     *
     * @param event
     */
    @FXML
    void handleEndDateBox(ActionEvent event) {

        checkValidDateForEndDate();
    }

    @FXML
    void handleEnterOperationSale(ActionEvent event) {

    }

    /**
     * This method will which report was chosen
     * And sends a query to generate this report
     *
     * @param event
     */
    @FXML
    void handleGenerateReportBtn(ActionEvent event) {

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add(ChooseReportToGenerateCombo.getValue());
        CommentsReportForMarketingCampaignTable.setVisible(false);
        CustomerPeriodicCharacterizationReportTable.setVisible(false);
        switch (ChooseReportToGenerateCombo.getValue()) {
            case "Comments Report for Marketing Campaign":
                if (EnterOperationSaleTXT.getText().equals("")) {
                    ERRORnoOperation.setVisible(true);
                    ShowReportMarketingCampaignTxt.setVisible(false);
                } else {
                    ERRORnoOperation.setVisible(false);
                    paramArray.add(EnterOperationSaleTXT.getText());
                    paramArray.add(managerCompany);
                    ShowReportMarketingCampaignTxt.setVisible(false);
                    myController.GetReportData(paramArray);
                }
                break;
            case "Customer Periodic Characterization Report":
                paramArray.add(StartDateBox.getValue().toString());
                paramArray.add(EndDateBox.getValue().toString());
                myController.GetReportData(paramArray);
                break;
            default:
                break;
        }
    }

    /**
     * This method will set Comments Report table Data
     *
     *
     * @param resultList
     */
    public void setCommentsReportData(ArrayList<CommentsReport> resultList) {

        if (resultList.size() > 0) {
            Float totalSum = new Float(0);
            for (CommentsReport temp : resultList) {
                totalSum += Float.parseFloat(temp.getCustomerTotalSum());
            }
            CommentsReport_CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            TotalAmountSpentColumn.setCellValueFactory(new PropertyValueFactory<>("customerTotalSum"));
            ObservableList<CommentsReport> data = FXCollections.observableArrayList(resultList);
            CommentsReportForMarketingCampaignTable.setItems(data);

            ShowReportMarketingCampaignTxt.setText("In marketing campaign #" + EnterOperationSaleTXT.getText() + ", " + resultList.size() + " customers were acquired,\n" +
                    "their total purchases being " + totalSum.toString() + "₪:");
            //₪
            ShowReportMarketingCampaignTxt.setFont(font);
            ShowReportMarketingCampaignTxt.setTextFill(paint);
            ShowReportMarketingCampaignTxt.setVisible(true);
            CommentsReportForMarketingCampaignTable.setVisible(true);
        } else {
            ShowReportMarketingCampaignTxt.setVisible(true);
            ShowReportMarketingCampaignTxt.setText("No information found for Marketing campaign #" + EnterOperationSaleTXT.getText() + " !");
        }

    }

    /**
     * This method will save Customers total sum in customerWithTotalSumList
     *
     * @param resultList
     */
    public void setCustomersListData(ArrayList<CustomerPeriodicCharacterizationReport> resultList) {

        customerWithTotalSumList.addAll(resultList);
    }

    /**
     * This method will save Customers total sum for each company
     * And will show all data table for Customer Periodic Characterization Report
     *
     * @param resultList
     */
    public void setCustomerPeriodicCharacterizationReportData(ArrayList<CustomerPeriodicCharacterizationReport> resultList) {

        if (customerWithTotalSumList.size() > 0) {
            ShowReportMarketingCampaignTxt.setVisible(false);
            CustomerPeriodicCharacterizationReport_CustomerIDCustomerPeriodicCharacterizationReportColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            TotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
            YellowColumn.setCellValueFactory(new PropertyValueFactory<>("yellow"));
            SonolColumn.setCellValueFactory(new PropertyValueFactory<>("sonol"));
            PazColumn.setCellValueFactory(new PropertyValueFactory<>("paz"));
            for (int i = 0; i < customerWithTotalSumList.size(); i++) {
                for (CustomerPeriodicCharacterizationReport a : resultList) {

                    if (customerWithTotalSumList.get(i).getCustomerID().equals(a.getCustomerID())) {
                        if (customerWithTotalSumList.get(i).getYellow() == null || customerWithTotalSumList.get(i).getYellow().equals("-"))
                            customerWithTotalSumList.get(i).setYellow(a.getYellow());
                        if (customerWithTotalSumList.get(i).getSonol() == null || customerWithTotalSumList.get(i).getSonol().equals("-"))
                            customerWithTotalSumList.get(i).setSonol(a.getSonol());
                        if (customerWithTotalSumList.get(i).getPaz() == null || customerWithTotalSumList.get(i).getPaz().equals("-"))
                            customerWithTotalSumList.get(i).setPaz(a.getPaz());
                    }
                }
            }
            ObservableList<CustomerPeriodicCharacterizationReport> data = FXCollections.observableArrayList(customerWithTotalSumList);
            CustomerPeriodicCharacterizationReportTable.setItems(data);
            CustomerPeriodicCharacterizationReportTable.setVisible(true);
        } else {
            ShowReportMarketingCampaignTxt.setText("No information found for the selected time period!");
            ShowReportMarketingCampaignTxt.setVisible(true);

        }
        customerWithTotalSumList.clear();
    }

    /**
     * This method will make date validation if End Date is Before Start Date
     * @return
     */
    boolean isEndDateBoxBeforeStartDateBox() {
        return EndDateBox.getValue().isBefore(StartDateBox.getValue());
    }

    /**
     * This method will show error message if isEndDateBoxBeforeStartDateBox
     *
     */
    void checkValidDateForEndDate() {

        if (isEndDateBoxBeforeStartDateBox()) {
            ERRORendBeforStart.setVisible(true);
            btnGenerateReport.setVisible(false);
        } else {
            ERRORendBeforStart.setVisible(false);
            btnGenerateReport.setVisible(true);
        }
    }

    /**
     * This method wil turns off the manager to choose a future date
     *
     */
    void disableFutureDates() {

        // disable past dates of DatePicker gui obj
        Callback<DatePicker, DateCell> callB = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                        LocalDate today = LocalDate.now();
                        setDisable(empty || item.compareTo(today) > 0);
                    }
                };
            }

        };
        StartDateBox.setDayCellFactory(callB);
        EndDateBox.setDayCellFactory(callB);

    }

}