package boundary;

import Contollers.FormValidation;
import Contollers.GeneratingReportsStationManagerController;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class GeneratingReportsStationManagerBoundary implements Initializable {
    private static GeneratingReportsStationManagerBoundary Instance = null;
    private GeneratingReportsStationManagerController generatingReportsStationManagerLogic;
    private FormValidation formValidation;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.generatingReportsStationManagerLogic = GeneratingReportsStationManagerController.getInstance();
        this.formValidation = FormValidation.getValidator();

        btnGenerateReport.setDisable(true);
        /*ChooseSubscriptionTypeCombo.setItems(SubscriptionType);
        ShowCurrentRateTXT.setVisible(false);
        ShowNewRateTXT.setVisible(false);
        btnSetNewRate.setVisible(false);
        //ChooseSubscriptionTypeCombo.set
        //this.shippingIndicatorTAB1.setVisible(false);
        /*  set all fields validators */
        formValidation();
        /* set form items */
        //setShippingTab();
    }

    /**
     * GeneratingReportsStationManagerController Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of controller class
     */
    public static GeneratingReportsStationManagerBoundary getInstance() {
        if (Instance == null)
            Instance = new GeneratingReportsStationManagerBoundary();
        return Instance;
    }

    private void formValidation() {

        /*  New price validation */
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
    void handleGenerateReport(ActionEvent event) {

    }

}