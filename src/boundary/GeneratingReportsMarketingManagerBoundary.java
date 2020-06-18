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
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.time.LocalDate;
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

        /*  set all fields validators */
        formValidation();
    }

    private void formValidation() {

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
        //System.out.println(StartDateBox.getEditor());
        StartDateBox.getEditor().clear();
        //StartDateBox.setValue(LocalDate.parse("null"));
        EndDateBox.getEditor().clear();
        //EndDateBox.setValue(null);
        System.out.println(EndDateBox.getValue());
        StartDateTxt.setVisible(true);
        StartDateBox.setVisible(true);
        EndDateTxt.setVisible(true);
        EndDateBox.setVisible(true);

        if (ChooseReportToGenerateCombo.getValue().equals("Customer Periodic Characterization Report"))
            EnterOperationSaleTXT.setVisible(false);

        btnGenerateReport.setVisible(false);
        ShowReportMarketingCampaignTxt.setVisible(false);
        CommentsReportForMarketingCampaignTable.setVisible(false);
        CustomerPeriodicCharacterizationReportTable.setVisible(false);

    }

    /*LocalDate parsedDate = parseDate("2016-08-16"); // you can also enter an empty string


    private LocalDate parseDate(final String dateAsString) {
        if (StringUtils.isEmpty(dateAsString)) { //isEmpty() will check if the string is empty or null
            return null; // here you can return the current date as well with LocalDate.now();
        }
        //default, ISO_LOCAL_DATE
        return LocalDate.parse(dateAsString);
    }*/

    @FXML
    void handleStartDateBox(ActionEvent event) {

        checkValidDateForStartDate();
    }


    @FXML
    void handleEndDateBox(ActionEvent event) {

        checkValidDateForEndDate();
    }

    @FXML
    void handleEnterOperationSale(ActionEvent event) {

        String str = EnterOperationSaleTXT.getText();
        System.out.println(str);
        //
    }

    @FXML
    void handleGenerateReportBtn(ActionEvent event) {


    }

    boolean isEndDateBoxNull() {
        System.out.println("isEndDateBoxNull:");
        System.out.println("EndDateBox:" + EndDateBox);
        System.out.println("EndDateBox.getValue:" + EndDateBox.getValue());
        System.out.println();
        //return (EndDateBox.getValue() == null);
        return (EndDateBox.getEditor().equals(null));
    }

    boolean isStartDateBoxNull() {
        return (StartDateBox.getValue() == null);
    }

    boolean isStartDateBoxBeforeLocalDate() {
        return StartDateBox.getValue().isBefore(java.time.LocalDate.now());
    }

    boolean isEndDateBoxBeforeLocalDate() {
        return EndDateBox.getValue().isBefore(java.time.LocalDate.now());
    }

    boolean isEndDateBoxBeforeStartDateBox() {
        return EndDateBox.getValue().isBefore(StartDateBox.getValue());
    }

    void checkValidDateForStartDate() {


        if (isStartDateBoxBeforeLocalDate())
            ERRORStartAlreadyPassedDate.setVisible(true);
        else
            ERRORStartAlreadyPassedDate.setVisible(false);

        if (!(isEndDateBoxNull())) {
            if (isEndDateBoxBeforeStartDateBox())
                ERRORendBeforStart.setVisible(true);
            else
                ERRORendBeforStart.setVisible(false);
            if (isEndDateBoxBeforeLocalDate())
                ERROREndAlreadyPassedDate.setVisible(true);
            else
                ERROREndAlreadyPassedDate.setVisible(false);
        }


        if (/*isStartDateBoxNull() && */!(isStartDateBoxBeforeLocalDate()) && !(isEndDateBoxNull()) && !(isEndDateBoxBeforeStartDateBox())) {
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

    void checkValidDateForEndDate() {

        if (!(isStartDateBoxNull()))
            if (isStartDateBoxBeforeLocalDate())
                ERRORStartAlreadyPassedDate.setVisible(true);
            else
                ERRORStartAlreadyPassedDate.setVisible(false);

        if (!(isStartDateBoxNull())) {
            if (isEndDateBoxBeforeStartDateBox())
                ERRORendBeforStart.setVisible(true);
            else
                ERRORendBeforStart.setVisible(false);
        }
        if (isEndDateBoxBeforeLocalDate())
            ERROREndAlreadyPassedDate.setVisible(true);
        else
            ERROREndAlreadyPassedDate.setVisible(false);


        if (!(isStartDateBoxNull())) {
            if (!(isEndDateBoxBeforeStartDateBox()) && !(isStartDateBoxBeforeLocalDate())) {
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

    }

}