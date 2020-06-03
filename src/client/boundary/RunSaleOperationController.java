package client.boundary;

import client.logic.FormValidation;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import common.entity.Costumer;
import common.entity.SaleOperation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author hani
 * @see client.logic.RunSaleOperationLogic - the form's logic class
 */

public class RunSaleOperationController implements Initializable {
        private static RunSaleOperationController Instance = null;
        private client.logic.RunSaleOperationLogic RunSaleOperationLogic;
        private FormValidation formValidation;

    /*
    Gui variables:
         * */
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnRunSaleOperation;

    @FXML
    private Label startSaleDateTXT;

    @FXML
    private JFXDatePicker startDatePicker;

    @FXML
    private Label endSaleDateTXT;

    @FXML
    private JFXDatePicker endDatePicker;

    @FXML
    private JFXComboBox<String> ChooseTemplateCombo;

    @FXML
    private Label ChooseTemplateTXT;

    @FXML
    private Label TemplateIDTXT;

    @FXML
    private Label FuelTypeTXT;

    @FXML
    private Label DiscountTXT;

    @FXML
    private Label DayTXT;

    @FXML
    private Label BeginHourTXT;

    @FXML
    private Label EndHourTXT;

 private ObservableList<String> TemplateID = FXCollections.observableArrayList("0001","0002","0003","0004","0005","0006");
 //TODO: Change to template name from DB^

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.RunSaleOperationLogic = RunSaleOperationLogic.getInstance();
        ChooseTemplateCombo.setItems(TemplateID);
        this.formValidation = FormValidation.getValidator();
        // TODO: formValidation();
        FormValidation();   // check all required fields are'nt empty
    }

    /**
     * SaleOperationTemplateController Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of controller class
     */
    public RunSaleOperationController getInstance() {
        if (Instance == null)
            Instance = new RunSaleOperationController();
        return Instance;
    }

    @FXML
    public void handleChooseTemplate(javafx.event.ActionEvent actionEvent) {

    }

    @FXML
    public void handleBtnRunSaleOperation(javafx.event.ActionEvent actionEvent) {
       // SaleOperation saleOperation = new SaleOperation(ChooseTemplateCombo.getValue() , "0001" ,Date.valueOf(startDatePicker.getValue()),Date.valueOf(endDatePicker.getValue()));
        //RunSaleOperationLogic.setSaleOperationInDB(saleOperation);
    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    private void FormValidation() {
        /*  Template Name validation */
        formValidation.isEmptyDateField(startDatePicker, "Start Date");
        formValidation.isEmptyDateField(endDatePicker, "End Date");

    }
}