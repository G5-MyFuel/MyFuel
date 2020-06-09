package boundary;

import Contollers.FormValidation;
import Contollers.GeneratingReportsMarketingManagerController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class GeneratingReportsMarketingManagerBoundary implements Initializable {

    /**
     * The supervisor boundary controller.
     */
    private final GeneratingReportsMarketingManagerController myController = new GeneratingReportsMarketingManagerController(this);
    private FormValidation formValidation;
    private final Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnGenerateReport;

    @FXML
    private JFXComboBox<?> ChooseReportToGenerateCombo;

    @FXML
    private JFXTextField EnterOperationSaleTXT;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.formValidation = FormValidation.getValidator();

        //btnApprovedRates.setDisable(true);
        /*ChooseSubscriptionTypeCombo.setItems(SubscriptionType);
        ShowCurrentRateTXT.setVisible(false);

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

    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleEnterOperationSale(ActionEvent event) {

    }

    @FXML
    void handleGenerateReportBtn(ActionEvent event) {

    }

}