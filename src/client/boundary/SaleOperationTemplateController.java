package client.boundary;

import client.logic.FormValidation;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import common.entity.Day;
import common.entity.FuelTypes;
import common.entity.SaleOperationTemplate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

/**
 * @author hani
 * @see client.logic.SaleOperationTemplateController - the form's logic class
 */
public class SaleOperationTemplateController implements Initializable {
    private SaleOperationTemplateController Instance = null;
    private client.logic.SaleOperationTemplateController saleOperationTemplateLogic;
    private FormValidation formValidation;

    /* db:
    boolean updateExist;
    SaleOperationTemplate saleOperationTemplateToInsert;
    ObservableList<SaleOperationTemplate> data1;
    ArrayList<SaleOperationTemplate> employeeArrayList;
    */

    //gui variables:
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
    private Button btnAddNewTemplate;

    @FXML
    private Pane detailsPane;

    @FXML
    private SplitPane newTemplateDetails;

    @FXML
    private Button btnNewTemplate;

    @FXML
    private JFXTextField TemplateName;

    @FXML
    private JFXTextField MarketingAdForTemplate;

    @FXML
    private JFXTextField DiscountPercentages;

    @FXML
    private JFXTimePicker StartHour;

    @FXML
    private JFXTimePicker EndHour;

     @FXML
    private TableView<SaleOperationTemplate> TemplateTableView;

    @FXML
    private TableColumn<SaleOperationTemplate, String> TemplateIDColumn;

    @FXML
    private TableColumn<SaleOperationTemplate, String> TemplateNameColumn;

    @FXML
    private TableColumn<SaleOperationTemplate, FuelTypes> FuelTypeColumn;

    @FXML
    private TableColumn<SaleOperationTemplate, Float> DiscountPercentagesColumn;

    @FXML
    private TableColumn<SaleOperationTemplate, Day> dayColumn;

    @FXML
    private TableColumn<SaleOperationTemplate, Time> BeginHourColumn;

    @FXML
    private TableColumn<SaleOperationTemplate, Time> EndHourColumn;

    @FXML
    private TableColumn<SaleOperationTemplate, String> MarketingAdColumn;

    @FXML
    private JFXComboBox<String> ChooseGasTypeComboSpecialization;

    @FXML
    private JFXComboBox<String> DayComboSpecialization1;

/*  TableColumn templateIDColumn = new TableColumn("Template Number");
    TableColumn templateNameColumn = new TableColumn("Template Name");
    TableColumn fuelTypeColumn = new TableColumn("Fuel Type");
    TableColumn DiscountPercentagesColumn = new TableColumn("Discount Percentages");
    TableColumn MarketingAdForTemplateColumn = new TableColumn("Marketing Ad For Template");
    TableColumn dayColumn = new TableColumn("Day");
    TableColumn beginHourColumn = new TableColumn("Beginning Hour");
    TableColumn endHourColumn = new TableColumn("End Hour");
*/
    private ObservableList<String> DayType = FXCollections.observableArrayList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
    private ObservableList<String> FuelType = FXCollections.observableArrayList("Gasoline", "Diesel", "ScooterFuel", "HomeHeatingFuel");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.saleOperationTemplateLogic = saleOperationTemplateLogic.getInstance();
        this.formValidation = FormValidation.getValidator();
        ChooseGasTypeComboSpecialization.setItems(FuelType);
        DayComboSpecialization1.setItems(DayType);
        this.detailsPane.setVisible(false);
        //TODO: formValidation();   set all fields validators
        FormValidation();
        /* check all required fields are'nt empty:*/
        /*  check form input validation */

        /*   db
        updateExist = false;
        // request data from server
        saleOperationTemplateLogic.getInstance().getTemplateTable();    //first initialize of employees table
        */
    }

    /**
     * SaleOperationTemplateController Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of controller class
     */
    public SaleOperationTemplateController getInstance() {
        if (Instance == null)
            Instance = new SaleOperationTemplateController();
        return Instance;
    }
    @FXML
    void handleBtnAddTemplate(ActionEvent event) {
        this.detailsPane.setVisible(true);

    }

    @FXML
    void handleChoseDayType(ActionEvent event) {

    }

    @FXML
    void handleSaveTemplate(ActionEvent event) {
//TODO: save it in DB
        detailsPane.setVisible(false);
    }


    private void FormValidation() {
        /*  Template Name validation */
        formValidation.isEmptyField(TemplateName, "Template Name");
        formValidation.maxLengthValidationShort(TemplateName, "Template Name", 45);

        /*  Discount Percentages validation */
        formValidation.isEmptyField(DiscountPercentages, "Discount Percentages");
        formValidation.isContainsOnlyNumbers(DiscountPercentages, "Discount Percentages");
        formValidation.numberPositiveValidation(DiscountPercentages, "Discount Percentages");

        /*  Start Hour validation */
        formValidation.isEmptyTimeField(StartHour, "Start Hour");

        /*  End Hour validation */
        formValidation.isEmptyTimeField(EndHour, "End Hour");

        /*  Marketing Ad For Template validation */
        formValidation.isEmptyField(MarketingAdForTemplate, "Marketing Ad For Template");

        //TODO: add more validation.. לבדוק אורך השדה בשעות ואת הטקסט שקשור
    }

/*
    public void setDataTable(Object object) {///????????????
        System.out.println("--> setDataTable");
        SaleOperationTemplateLogic.getInstance().setTemplateArrayList((ArrayList<SaleOperationTemplate>) object);
        //setTemplatesTableColumns();//???????????????
        ObservableList<SaleOperationTemplate> data = FXCollections.observableArrayList(SaleOperationTemplateLogic.getInstance().getTemplatesArrayList());
        TemplateTableView.setEditable(true);
        TemplateTableView.setItems(data);
      //  TemplateTableView.getColumns().addAll(EmployeeIdCol, FirstNameCol, lastNameCol, emailAddressCol, jobTitleCol, fuelCompanyNameCol);
    }



*/

 /*
    public void setTemplatesTableColumns() {
        TemplateIDColumn.setCellValueFactory(new PropertyValueFactory<SaleOperationTemplate, String>("Template ID"));
        TemplateNameColumn.setCellValueFactory(new PropertyValueFactory<SaleOperationTemplate, String>("Template Name"));
        FuelTypeColumn.setCellValueFactory(new PropertyValueFactory<SaleOperationTemplate, FuelTypes>("Fuel Type"));
       DiscountPercentagesColumn.setCellValueFactory(new PropertyValueFactory<SaleOperationTemplate, String>("emailAddress"));
        MarketingAdForTemplateColumn.setCellValueFactory(new PropertyValueFactory<SaleOperationTemplate, String>("jobTitle"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<SaleOperationTemplate, String>("fuelCompanyName"));
        beginHourColumn.setCellValueFactory(new PropertyValueFactory<SaleOperationTemplate, String>("fuelCompanyName"));
        endHourColumn.setCellValueFactory(new PropertyValueFactory<SaleOperationTemplate, String>("fuelCompanyName"));

        */

}



