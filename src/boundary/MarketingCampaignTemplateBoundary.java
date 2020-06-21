package boundary;

import Contollers.MarketingCampaignTemplateController;
import Contollers.FormValidation;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import common.assets.enums.FuelTypes;
import entity.Day;
import entity.MarketingCampaignTemplate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This department is responsible for controlling "MarketingCampaignTemplateFXML" page
 * Allows marketing department worker to view current templates and add new ones
 *
 * @author Hana Wiener
 * @see MarketingCampaignTemplateController - the form's logic class
 */

public class MarketingCampaignTemplateBoundary implements DataInitializable {

    /**
     * The supervisor boundary controller.
     */
    private MarketingCampaignTemplateController myController = new MarketingCampaignTemplateController(this);
    /**
     * A parameter that represents who enters the page
     */
    private String marketingDepartmentWorker;
    /**
     * For proper input testing
     */
    private FormValidation formValidation;
    private boolean flagValidation = true;

    /**
    * Gui variables:
     */
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
    private TableView<MarketingCampaignTemplate> TemplateTableView;

    @FXML
    private TableColumn<MarketingCampaignTemplate, String> TemplateIDColumn;

    @FXML
    private TableColumn<MarketingCampaignTemplate, String> TemplateNameColumn;

    @FXML
    private TableColumn<MarketingCampaignTemplate, FuelTypes> FuelTypeColumn;

    @FXML
    private TableColumn<MarketingCampaignTemplate, Float> DiscountPercentagesColumn;

    @FXML
    private TableColumn<MarketingCampaignTemplate, Day> dayColumn;

    @FXML
    private TableColumn<MarketingCampaignTemplate, Time> BeginHourColumn;

    @FXML
    private TableColumn<MarketingCampaignTemplate, Time> EndHourColumn;

    @FXML
    private JFXComboBox<String> ChooseGasTypeComboSpecialization;

    @FXML
    private JFXComboBox<String> DayComboSpecialization1;

    private ObservableList<String> DayType = FXCollections.observableArrayList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "All");
    private ObservableList<String> FuelType = FXCollections.observableArrayList("Gasoline95", "Diesel", "ScooterFuel");

    /**
     * This method allows to save information sent when uploading the page (user id)
     *
     * @param data - The data sent to the boundary
     */
    @Override
    public void initData(Object data) {
        this.marketingDepartmentWorker = (String) data;
    }

    /**
     * This method initializes the variables, fields, and combo-boxes
     * What is initialized will appear when the screen is raised
     * Initializes existing tables on the page - by sending a request
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChooseGasTypeComboSpecialization.setItems(FuelType);
        DayComboSpecialization1.setItems(DayType);
        this.detailsPane.setVisible(false);
        MarketingAdForTemplate.setVisible(false);

        this.formValidation = FormValidation.getValidator();
        FormValidation();        //TODO: formValidation();   set all fields validators

        myController.getTemplatesTable(); //start the process that will ask server to execute quarry and get the table details
    }

    /**
     * this method will set the templates table when we will initialize the page.
     *
     * @param cosArray
     */
    public void setTemplateTable(ArrayList<MarketingCampaignTemplate> cosArray) {
        //
        TemplateIDColumn.setCellValueFactory(new PropertyValueFactory<>("templateID"));
        TemplateNameColumn.setCellValueFactory(new PropertyValueFactory<>("templateName"));
        FuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        DiscountPercentagesColumn.setCellValueFactory(new PropertyValueFactory<>("DiscountPercentages"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("Day"));
        BeginHourColumn.setCellValueFactory(new PropertyValueFactory<>("beginHour"));
        EndHourColumn.setCellValueFactory(new PropertyValueFactory<>("endHour"));

        ObservableList<MarketingCampaignTemplate> data = FXCollections.observableArrayList(cosArray);
        TemplateTableView.setItems(data);
    }

    /**
     *The method displays a window that allows you to add a template, when click the Add button
     * @param event
     */
    @FXML
    void handleBtnAddTemplate(ActionEvent event) {
        this.detailsPane.setVisible(true);
    }

    /**
     *When you click the "Save Template" button
     * The method saves the new template in DB
     * and refreshes the table for the template to appear in it
     *
     * @param event
     */
    @FXML
    void handleSaveTemplate(ActionEvent event) {
        MarketingCampaignTemplate newTemplate = new MarketingCampaignTemplate
                (String.valueOf(myController.getTemplateCounter() + 1), TemplateName.getText(), (String) ChooseGasTypeComboSpecialization.getValue(), (String) DiscountPercentages.getText(), (String) DayComboSpecialization1.getValue(), Time.valueOf(StartHour.getValue()), Time.valueOf(EndHour.getValue()));
        myController.setTemplateInDB(newTemplate);
        myController.getTemplatesTable(); //start the process that will ask server to execute quarry and get the table details//refresh
        detailsPane.setVisible(false);

        //clear all fileds:
        TemplateName.clear();
        MarketingAdForTemplate.clear();
        DiscountPercentages.clear();
        DayComboSpecialization1.getSelectionModel().clearSelection();
        ChooseGasTypeComboSpecialization.getSelectionModel().clearSelection();
        StartHour.getEditor().clear();
        EndHour.getEditor().clear();
    }

    /**
     *A method responsible for input validity checks
     */
    private void FormValidation() {
        /*  Template Name validation */
        formValidation.isEmptyFieldValidation(TemplateName, "Template Name");
        formValidation.maxLengthValidationShort(TemplateName, "Template Name", 45);

        /*  Discount Percentages validation */
        formValidation.isEmptyFieldValidation(DiscountPercentages, "Discount Percentages");
        formValidation.isOnlyNumbers(DiscountPercentages, "Discount Percentages");
        formValidation.numberPositiveValidation(DiscountPercentages, "Discount Percentages");

        /*  Start Hour validation */
        if (StartHour.getValue() == null) {
            flagValidation = false;
        }
    }

}



