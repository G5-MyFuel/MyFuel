package boundary;

import Contollers.MarketingCampaignTemplateController;
import Contollers.FormValidation;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import entity.Day;
import entity.FuelTypes;
import entity.MarketingCampaignTemplate;

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
 * @see MarketingCampaignTemplateController - the form's logic class
 */
public class MarketingCampaignTemplateBoundary implements Initializable {

    /** The supervisor boundary controller. */
    private MarketingCampaignTemplateController myController = new MarketingCampaignTemplateController(this);

    private FormValidation formValidation;
    private boolean flagValidation=true;

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

   /* @FXML
    private TableColumn<SaleOperationTemplate, String> MarketingAdColumn;
*/
    @FXML
    private JFXComboBox<String> ChooseGasTypeComboSpecialization;

    @FXML
    private JFXComboBox<String> DayComboSpecialization1;

    private ObservableList<String> DayType = FXCollections.observableArrayList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "All");
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
    public void setTemplateTable(ArrayList<MarketingCampaignTemplate> cosArray){
        //col oms parameters
        TemplateIDColumn.setCellValueFactory(new PropertyValueFactory<>("templateID"));
        TemplateNameColumn.setCellValueFactory(new PropertyValueFactory<>("templateName"));
        FuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        DiscountPercentagesColumn.setCellValueFactory(new PropertyValueFactory<>("DiscountPercentages"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("Day"));
        BeginHourColumn.setCellValueFactory(new PropertyValueFactory<>("beginHour"));
        EndHourColumn.setCellValueFactory(new PropertyValueFactory<>("endHour"));
     //   MarketingAdColumn.setCellValueFactory(new PropertyValueFactory<>("Marketing ad"));

        ObservableList<MarketingCampaignTemplate> data = FXCollections.observableArrayList(cosArray);
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
        MarketingCampaignTemplate newTemplate = new MarketingCampaignTemplate
                (String.valueOf(myController.getTemplateCounter()+1), TemplateName.getText(), (String)ChooseGasTypeComboSpecialization.getValue(), (String)DiscountPercentages.getText(), (String)DayComboSpecialization1.getValue(), Time.valueOf(StartHour.getValue()), Time.valueOf(EndHour.getValue()));
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


    private void FormValidation() {
        /*  Template Name validation */
        formValidation.isEmptyField(TemplateName, "Template Name");
        formValidation.maxLengthValidationShort(TemplateName, "Template Name", 45);

        /*  Discount Percentages validation */
        formValidation.isEmptyField(DiscountPercentages, "Discount Percentages");
        formValidation.isContainsOnlyNumbers(DiscountPercentages, "Discount Percentages");
        formValidation.numberPositiveValidation(DiscountPercentages, "Discount Percentages");

        /*  Start Hour validation */ // פתרון זמני בלבד !!
        if (StartHour.getValue() == null) {
            flagValidation = false;

        }

      //  formValidation.isEmptyTimeField(StartHour, "Start Hour");

        /*  End Hour validation */
       // formValidation.isEmptyTimeField(EndHour, "End Hour");

        /*  Marketing Ad For Template validation */
        formValidation.isEmptyField(MarketingAdForTemplate, "Marketing Ad For Template");

        //TODO: add more validation.. לבדוק אורך השדה בשעות ואת הטקסט שקשור
    }

}



