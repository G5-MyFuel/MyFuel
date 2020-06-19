package boundary;

import Contollers.FormValidation;
import Contollers.GeneratingReportsMarketingManagerController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.assets.Toast;
import entity.CommentsReport;
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
    Font font;
    Paint paint;

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

        ERRORendBeforStart.setVisible(false);
        ERRORnoOperation.setVisible(false);

        disablePastDates();
        EndDateBox.setValue(LocalDate.now());
        StartDateBox.setValue(LocalDate.now());

        font = ShowReportMarketingCampaignTxt.getFont();
        paint = ShowReportMarketingCampaignTxt.getTextFill();

        /*  set all fields validators */
        formValidation();
    }

    private void formValidation() {

        //formValidation.isEmptyFieldValidation(EnterOperationSaleTXT, "Operation Sale");
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


        if (ChooseReportToGenerateCombo.getValue().equals("Customer Periodic Characterization Report")) {
            StartDateTxt.setVisible(true);
            StartDateBox.setVisible(true);
            EndDateTxt.setVisible(true);
            EndDateBox.setVisible(true);
            EnterOperationSaleTXT.setVisible(false);
        } else {
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

    public void setCustomersListData(ArrayList<String> resultList){




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
            ERRORendBeforStart.setVisible(true);
        else
            ERRORendBeforStart.setVisible(false);

        if (!(isEndDateBoxBeforeStartDateBox()) /*&& !(isStartDateBoxBeforeLocalDate())*/) {
            btnGenerateReport.setVisible(true);
            /*if (ChooseReportToGenerateCombo.getValue().equals("Comments Report for Marketing Campaign"))
                EnterOperationSaleTXT.setVisible(true);
            else
                EnterOperationSaleTXT.setVisible(false);*/
        } else {
            btnGenerateReport.setVisible(false);
            //.setVisible(false);
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