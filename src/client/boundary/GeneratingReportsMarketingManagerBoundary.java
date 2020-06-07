package client.boundary;

import client.logic.FormValidation;
import client.logic.GeneratingReportsMarketingManagerLogic;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class GeneratingReportsMarketingManagerBoundary implements Initializable {
    private static GeneratingReportsMarketingManagerBoundary Instance = null;
    private GeneratingReportsMarketingManagerLogic generatingReportsMarketingManagerLogic;
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

    @FXML
    private JFXTextField EnterOperationSaleTXT;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.generatingReportsMarketingManagerLogic = GeneratingReportsMarketingManagerLogic.getInstance();
        this.formValidation = FormValidation.getValidator();

        EnterOperationSaleTXT.setDisable(true);
        //EnterOperationSaleTXT.setEditable(false);
        btnGenerateReport.setDisable(true);
        /*ChooseSubscriptionTypeCombo.setItems(SubscriptionType);
        ShowCurrentRateTXT.setVisible(false);
        ShowNewRateTXT.setVisible(false);
        btnSetNewRate.setVisible(false);*/
        //ChooseSubscriptionTypeCombo.set
        //this.shippingIndicatorTAB1.setVisible(false);
        /*  set all fields validators */
        formValidation();
        /* set form items */
        //setShippingTab();
    }

    /**
     * GeneratingReportsMarketingManagerController Instance getter using SingleTone DesignPatterns
     * @return Instance of controller class
     */
    public static GeneratingReportsMarketingManagerBoundary getInstance() {
        if (Instance == null)
            Instance = new GeneratingReportsMarketingManagerBoundary();
        return Instance;
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