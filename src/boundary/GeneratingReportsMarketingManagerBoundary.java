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

import java.net.URL;
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
        StartDateBox.getEditor().clear();
        EndDateBox.getEditor().clear();
        StartDateTxt.setVisible(true);
        StartDateBox.setVisible(true);
        EndDateTxt.setVisible(true);
        EndDateBox.setVisible(true);

        if(ChooseReportToGenerateCombo.getValue().equals("Comments Report for Marketing Campaign"))
            EnterOperationSaleTXT.setVisible(true);
        else
            EnterOperationSaleTXT.setVisible(false);

        btnGenerateReport.setVisible(true);
        ShowReportMarketingCampaignTxt.setVisible(false);
        CommentsReportForMarketingCampaignTable.setVisible(false);
        CustomerPeriodicCharacterizationReportTable.setVisible(false);

    }

    @FXML
    void handleStartDateBox(ActionEvent event) {

        if(StartDateBox.getValue().isBefore(java.time.LocalDate.now()) == true)
            //ERRORalreadyPassedDate.setVisible(true);
            //Toast.makeText(mainProjectFX.mainStage,"Wellcom to MyFuel ",1000,1500,1500);
        Toast.makeText(mainProjectFX.mainStage,"MSG",1000,1500,1500,5,45);
    }

    @FXML
    void handleEndDateBox(ActionEvent event) {

        /*if(EndDateBox.getValue().isBefore(StartDateBox.getValue()) == true)
            ERRORalreadyPassedDate.setVisible(true);*/

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


}