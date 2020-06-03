package client.boundary;

import client.logic.SaleOperationTemplateLogic;
import client.logic.FormValidation;

import client.logic.SettingDiscountRatesLogic;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.awt.Label;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Hana Wiener
 * @see SaleOperationTemplateLogic - the form's logic class
 */

public class SaleOperationTemplateController implements Initializable {
    private SaleOperationTemplateController Instance = null;
    private SaleOperationTemplateLogic saleOperationTemplateLogic;
    private FormValidation formValidation;
/*
    ObservableList<SaleOperationTemplate> data1;
    ArrayList<SaleOperationTemplate> employeeArrayList;

    TableColumn templateIDColumn = new TableColumn("Template Number");
    TableColumn templateNameColumn = new TableColumn("Template Name");
    TableColumn fuelTypeColumn = new TableColumn("Fuel Type");
    TableColumn DiscountPercentagesColumn = new TableColumn("Discount Percentages");
    TableColumn MarketingAdForTemplateColumn = new TableColumn("Marketing Ad For Template");
    TableColumn dayColumn = new TableColumn("Day");
    TableColumn beginHourColumn = new TableColumn("Beginning Hour");
    TableColumn endHourColumn = new TableColumn("End Hour");
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
    private Pane detailsPane;

    @FXML
    private SplitPane newTemplateDetails;

    @FXML
    private Button btnNewTemplate;

    @FXML
    private JFXTextField TemplateName;

    @FXML
    private JFXComboBox<String> ChooseGasTypeComboSpecialization;

    @FXML
    private JFXComboBox<String> DayComboSpecialization1;

    @FXML
    private JFXTimePicker StartHour;

    @FXML
    private JFXTimePicker EndHour;

    @FXML
    private JFXTextField MarketingAdForTemplate;

    @FXML
    private JFXTextField DiscountPercentages;

    @FXML
    private Button btnAddNewTemplate;


    private ObservableList<String> DayType = FXCollections.observableArrayList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
    private ObservableList<String> FuelType = FXCollections.observableArrayList("Gasoline", "Diesel", "ScooterFuel", "HomeHeatingFuel");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.saleOperationTemplateLogic = saleOperationTemplateLogic.getInstance();
        ChooseGasTypeComboSpecialization.setItems(FuelType);
        DayComboSpecialization1.setItems(DayType);
        this.formValidation = FormValidation.getValidator();
        this.detailsPane.setVisible(false);
        //TODO: formValidation();   set all fields validators
        FormValidation();   //
        /* check all required fields are'nt empty:*/

        /*  check form input validation */

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
    public void handleChoseDayType(javafx.event.ActionEvent actionEvent) {

    }

    @FXML
    void handleBtnAddTemplate(javafx.event.ActionEvent actionEvent) {
        this.detailsPane.setVisible(true);

    }


    @FXML
    public void handleSaveTemplate(javafx.event.ActionEvent actionEvent) {
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

        /*  Start Hour validation
        formValidation.isEmptyField(StartHour, "Start Hour");
        formValidation.isContainsOnlyNumbers(StartHour, "Start Hour");
        formValidation.numberPositiveValidation(StartHour, "Start Hour");
 */
        /*  End Hour validation */
       /* formValidation.isEmptyField(EndHour, "End Hour");
        formValidation.isContainsOnlyNumbers(EndHour, "End Hour");
        formValidation.numberPositiveValidation(EndHour, "End Hour");
*/
        /*  Marketing Ad For Template validation */
        formValidation.isEmptyField(MarketingAdForTemplate, "Marketing Ad For Template");

        //TODO: add more validation.. לבדוק אורך השדה בשעות ואת הטקסט שקשור
    }

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

/*
    public void setDataTable(Object object) {///????????????
        System.out.println("--> setDataTable");
        SaleOperationTemplateLogic.getInstance().setTemplatesArrayList((ArrayList<SaleOperationTemplate>) object);
        setTemplatesTableColumns();
        ObservableList<SaleOperationTemplate> data = FXCollections.observableArrayList(SaleOperationTemplateLogic.getInstance().getTemplatesArrayList());
        employeesTableView.setEditable(true);
        employeesTableView.setItems(data);
        employeesTableView.getColumns().addAll(EmployeeIdCol, FirstNameCol, lastNameCol, emailAddressCol, jobTitleCol, fuelCompanyNameCol);
    }
    }
    */


