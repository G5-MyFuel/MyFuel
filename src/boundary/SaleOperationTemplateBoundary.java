package boundary;

import Contollers.SaleOperationTemplateController;
import Contollers.FormValidation;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import entity.Costumer;
import entity.Day;
import entity.FuelTypes;
import entity.SaleOperationTemplate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Hana Wiener
 * @see SaleOperationTemplateController - the form's logic class
 */
public class SaleOperationTemplateBoundary implements Initializable {

    /** The supervisor boundary controller. */
    private SaleOperationTemplateController myController = new SaleOperationTemplateController(this);

    private FormValidation formValidation;

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

   /* @FXML
    private TableColumn<SaleOperationTemplate, String> MarketingAdColumn;
*/
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
        ChooseGasTypeComboSpecialization.setItems(FuelType);
        DayComboSpecialization1.setItems(DayType);
        this.detailsPane.setVisible(false);

        this.formValidation = FormValidation.getValidator();
        FormValidation();        //TODO: formValidation();   set all fields validators

        myController.getTemplatesTable(); //start the process that will ask server to execute quarry and get the table details
    }

    /**
     this method will set the templates table when we will initialize the page.
     */
    public void setTemplateTable(ArrayList<SaleOperationTemplate> cosArray){
        //col oms parameters
        TemplateIDColumn.setCellValueFactory(new PropertyValueFactory<>("templateID"));
        TemplateNameColumn.setCellValueFactory(new PropertyValueFactory<>("templateName"));
        FuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        DiscountPercentagesColumn.setCellValueFactory(new PropertyValueFactory<>("DiscountPercentages"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("Day"));
        BeginHourColumn.setCellValueFactory(new PropertyValueFactory<>("beginHour"));
        EndHourColumn.setCellValueFactory(new PropertyValueFactory<>("endHour"));
     //   MarketingAdColumn.setCellValueFactory(new PropertyValueFactory<>("Marketing ad"));

        ObservableList<SaleOperationTemplate> data = FXCollections.observableArrayList(cosArray);
        TemplateTableView.setItems(data);
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
      //  formValidation.isEmptyTimeField(StartHour, "Start Hour");

        /*  End Hour validation */
       // formValidation.isEmptyTimeField(EndHour, "End Hour");

        /*  Marketing Ad For Template validation */
        formValidation.isEmptyField(MarketingAdForTemplate, "Marketing Ad For Template");

        //TODO: add more validation.. לבדוק אורך השדה בשעות ואת הטקסט שקשור
    }

}



