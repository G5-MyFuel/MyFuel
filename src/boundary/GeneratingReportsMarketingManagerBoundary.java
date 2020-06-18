package boundary;

import Contollers.FormValidation;
import Contollers.GeneratingReportsMarketingManagerController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.assets.Toast;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class GeneratingReportsMarketingManagerBoundary implements DataInitializable {

    String managerID;
    String managerCompany;

    /**
     * The supervisor boundary controller.
     */
    private final GeneratingReportsMarketingManagerController myController = new GeneratingReportsMarketingManagerController(this);
    private FormValidation formValidation;
    private final Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);

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
    private TableView<?> CommentsReportForMarketingCampaignTable;

    @FXML
    private TableColumn<?, ?> CommentsReport_CustomerIDColumn;

    @FXML
    private TableColumn<?, ?> TotalAmountSpentColumn;

    @FXML
    private TableView<?> CustomerPeriodicCharacterizationReportTable;

    @FXML
    private TableColumn<?, ?> CustomerPeriodicCharacterizationReport_CustomerIDCustomerPeriodicCharacterizationReportColumn;

    @FXML
    private TableColumn<?, ?> YellowColumn;

    @FXML
    private TableColumn<?, ?> SonolColumn;

    @FXML
    private TableColumn<?, ?> PazColumn;

    @FXML
    private TableColumn<?, ?> TotalColumn;

    @FXML
    private Label ERRORStartAlreadyPassedDate;

    @FXML
    private Label ERROREndAlreadyPassedDate;

    @FXML
    private Label ERRORendBeforStart;

    @FXML
    private Label ERRORnoOperation;

    @Override
    public void initData(Object data) {

    }

    private final ObservableList<String> ReportsType = FXCollections.observableArrayList("Comments Report for Marketing Campaign",
            "Customer Periodic Characterization Report");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.formValidation = FormValidation.getValidator();

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
        ERRORStartAlreadyPassedDate.setVisible(false);
        ERROREndAlreadyPassedDate.setVisible(false);

        ERRORendBeforStart.setVisible(false);
        ERRORnoOperation.setVisible(false);

        disablePastDates();
        EndDateBox.setValue(LocalDate.now());
        StartDateBox.setValue(LocalDate.now());

        /*  set all fields validators */
        formValidation();
    }

    private void formValidation() {

        formValidation.isEmptyFieldValidation(EnterOperationSaleTXT, "Operation Sale");
        /*  EnterOperationSaleTXT validation */
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
    void handleChooseReportToGenerate(ActionEvent event) {

        ChooseReportToGenerateCombo.setPrefWidth(340);
        StartDateTxt.setVisible(true);
        StartDateBox.setVisible(true);
        EndDateTxt.setVisible(true);
        EndDateBox.setVisible(true);

        if (ChooseReportToGenerateCombo.getValue().equals("Customer Periodic Characterization Report"))
            EnterOperationSaleTXT.setVisible(false);

        //btnGenerateReport.setVisible(false);
        checkValidDateForEndDate();
        ShowReportMarketingCampaignTxt.setVisible(false);
        CommentsReportForMarketingCampaignTable.setVisible(false);
        CustomerPeriodicCharacterizationReportTable.setVisible(false);

    }

    @FXML
    void handleStartDateBox(ActionEvent event) {

        disableBeforeStartDate();
        //checkValidDateForStartDate();
        checkValidDateForEndDate();
    }


    @FXML
    void handleEndDateBox(ActionEvent event) {

        checkValidDateForEndDate();
    }

    @FXML
    void handleEnterOperationSale(ActionEvent event) {

    }

    @FXML
    void handleGenerateReportBtn(ActionEvent event) {

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add(ChooseReportToGenerateCombo.getValue());
        if (ChooseReportToGenerateCombo.getValue().equals("Comments Report for Marketing Campaign")) {
            //ShowReportMarketingCampaignTxt.setText("Comments Report for Marketing Campaign #" + ChooseReportToGenerateCombo.getValue());
            ShowReportMarketingCampaignTxt.setVisible(true);
            paramArray.add(EnterOperationSaleTXT.getText());
        }

        myController.GetReportData(paramArray);
    }

    /*boolean isStartDateBoxBeforeLocalDate() {
        return StartDateBox.getValue().isBefore(java.time.LocalDate.now());
    }*/

    boolean isEndDateBoxBeforeStartDateBox() {
        return EndDateBox.getValue().isBefore(StartDateBox.getValue());
    }

    void checkValidDateForStartDate() {

        disableBeforeStartDate();
        /*if(isEndDateBoxBeforeStartDateBox())
            ERROREndAlreadyPassedDate.setVisible(true);
        else
            ERROREndAlreadyPassedDate.setVisible(false);

        if (!(isStartDateBoxBeforeLocalDate()) && !(isEndDateBoxBeforeStartDateBox())) {
            btnGenerateReport.setVisible(true);
            if (ChooseReportToGenerateCombo.getValue().equals("Comments Report for Marketing Campaign"))
                EnterOperationSaleTXT.setVisible(true);
            else
                EnterOperationSaleTXT.setVisible(false);
        } else {
            btnGenerateReport.setVisible(false);
            EnterOperationSaleTXT.setVisible(false);
        }*/
    }

    void checkValidDateForEndDate() {

        if (isEndDateBoxBeforeStartDateBox())
            ERROREndAlreadyPassedDate.setVisible(true);
        else
            ERROREndAlreadyPassedDate.setVisible(false);

        if (!(isEndDateBoxBeforeStartDateBox()) /*&& !(isStartDateBoxBeforeLocalDate())*/) {
            btnGenerateReport.setVisible(true);
            if (ChooseReportToGenerateCombo.getValue().equals("Comments Report for Marketing Campaign"))
                EnterOperationSaleTXT.setVisible(true);
            else
                EnterOperationSaleTXT.setVisible(false);
        } else {
            btnGenerateReport.setVisible(false);
            EnterOperationSaleTXT.setVisible(false);
        }

    }

    void disablePastDates() {

        // disable past dates of DatePicker gui obj
        Callback<DatePicker, DateCell> callB = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                        LocalDate today = LocalDate.now();
                        setDisable(empty || item.compareTo(today) < 0);
                    }
                };
            }

        };
        StartDateBox.setDayCellFactory(callB);
        EndDateBox.setDayCellFactory(callB);

    }

    void disableBeforeStartDate() {

        // disable past dates of DatePicker gui obj
        Callback<DatePicker, DateCell> callB = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                        LocalDate startDate = StartDateBox.getValue();
                        setDisable(empty || item.compareTo(startDate) < 0);
                    }
                };
            }

        };
        EndDateBox.setDayCellFactory(callB);
    }

}