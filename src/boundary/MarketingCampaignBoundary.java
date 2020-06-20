package boundary;

import Contollers.FormValidation;
import Contollers.MarketingCampaignController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import entity.MarketingCampaign;
import entity.MarketingCampaignTemplate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *  This class This department is responsible for controlling "MarketingCampaignFXML" page
 *Allows marketing manager to view current campaigns that are running and their dates
 * And running a new campaign by template existing on the system
 *
 * @author Hana Wiener
 * @see MarketingCampaignController - the form's logic class
 */
public class MarketingCampaignBoundary implements DataInitializable {
    /**
     * The supervisor boundary controller.
     */
    private MarketingCampaignController myController = new MarketingCampaignController(this);
    /**
     * A parameter that represents who enters the page
     */
    private String marketingManager;
    /**
     *  For proper input testing
     */
    private FormValidation formValidation;
    /**
     * choosen Template to send to query
     */
    String choosenTemplate = new String();
    /**
     * Flag - Can the campaign run
     */
    boolean flagSale = true;
    MarketingCampaign newSale = new MarketingCampaign();

    /*
    Gui variables:
    * */
    @FXML
    private TableView<MarketingCampaign> saleOperationTableView;

    @FXML
    private TableColumn<MarketingCampaign, String> saleIDColumn;

    @FXML
    private TableColumn<MarketingCampaign, String> TemplateNameColumn;

    @FXML
    private TableColumn<MarketingCampaign, String> StartDateColumn;

    @FXML
    private TableColumn<MarketingCampaign, String> EndDateColumn;


    @FXML
    private JFXComboBox<String> ChooseTemplateCombo;

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
    private Button btnADDnewSaleOperation1;

    @FXML
    private Pane detailsPane;

    @FXML
    private SplitPane newTemplateDetails;

    @FXML
    private JFXDatePicker startDatePicker;

    @FXML
    private JFXDatePicker endDatePicker;

    @FXML
    private Label idFromDB;

    @FXML
    private Label typeFromDB;

    @FXML
    private Label discountFromDB;

    @FXML
    private Label dayFromDB;

    @FXML
    private Label beginHourFromDB;

    @FXML
    private Label endHourFromDB;

    @FXML
    private Button btnRunSaleOperation;

    @FXML
    private HBox templateDetaildVBOX;

    @FXML
    private Label ERRORalreadyPassedDate;

    @FXML
    private Label ERRORendBeforStart;

    @FXML
    private Label ERRORoverlap;

    @FXML
    private Label ERRORoverlap1;

    @FXML
    private Label ERRORnoTemplate;


    /**
     *  This method allows to save information sent when uploading the page (user id)
     *
     * @param data - The data sent to the boundary
     */
    @Override
    public void initData(Object data) {
        this.marketingManager = (String) data;
    }

    /**
     * This method initializes the variables, fields, and combo-boxes
     * What is initialized will appear when the screen is raised
     *Initializes existing tables on the page - by sending a request
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("******************************************");
        this.detailsPane.setVisible(false);
        ERRORalreadyPassedDate.setVisible(false);
        ERRORendBeforStart.setVisible(false);
        ERRORoverlap.setVisible(false);
        ERRORoverlap1.setVisible(false);
        ERRORnoTemplate.setVisible(false);

        this.formValidation = FormValidation.getValidator();
        FormValidation();   // check all required fields are'nt empty

        myController.getSalesTable(); //start the process that will ask server to execute quarry and get the table details
        myController.getTemplateList(); //start the process that will ask server to execute quarry and get the template list
    }


    /**
     *This method receives from the controller an array with the values obtained from the DB
     * And places them in the appropriate table of sales - saleOperationTableView
     *
     * * @param cosArray
     */
    public void setSalesTable(ArrayList<MarketingCampaign> cosArray) {

        saleIDColumn.setCellValueFactory(new PropertyValueFactory<>("CampaignID"));
        TemplateNameColumn.setCellValueFactory(new PropertyValueFactory<>("TemplateName"));
        StartDateColumn.setCellValueFactory(new PropertyValueFactory<>("BeginDate"));
        EndDateColumn.setCellValueFactory(new PropertyValueFactory<>("EndDate"));

        ObservableList<MarketingCampaign> data = FXCollections.observableArrayList(cosArray);
        saleOperationTableView.setItems(data);
        System.out.println("finish with table");

    }

    /**
     *This method will only be activated if button "Add Sale" is pressed
     *Makes the "Add Campaign window appear
     *
     * @param event
     */
    @FXML
    void handleBtnAddSale(ActionEvent event) {
        this.templateDetaildVBOX.setVisible(false);
        this.endDatePicker.setVisible(false);
        this.startDatePicker.setVisible(false);
        btnADDnewSaleOperation1.setVisible(false);

        detailsPane.setVisible(true);
    }
//////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param event
     */
    @FXML
    void handleBtnRunSale(ActionEvent event) {
        if (ChooseTemplateCombo.getSelectionModel().isEmpty()) {
            ERRORnoTemplate.setVisible(true);
        }
         else {
            newSale = new MarketingCampaign(String.valueOf(myController.getSaleCounter() + 1), (String) ChooseTemplateCombo.getValue(), Date.valueOf(startDatePicker.getValue()),
                    Date.valueOf(endDatePicker.getValue()));
            // chack if sale can run in this dates:
            myController.chackIfSaleCanRun(newSale);
        }
    }

    public void setFlagSale(boolean flagSale)
    {
        this.flagSale = flagSale;
        System.out.println("set function");

        //flagSale = myController.getFlagSale();
        if (flagSale==false) {
            System.out.println("inside if");
            ERRORoverlap.setVisible(true);
            ERRORoverlap1.setVisible(true);
            startDatePicker.getEditor().clear();
            endDatePicker.getEditor().clear();
        }
        else {
            System.out.println("inside else");
            myController.setSaleOperationInDB(newSale);  //insert new sale to db
            myController.getSalesTable(); //refresh
            detailsPane.setVisible(false);
            ERRORoverlap.setVisible(false);
            ERRORoverlap1.setVisible(false);
            //clear all fileds:
            ChooseTemplateCombo.getSelectionModel().clearSelection();
            startDatePicker.getEditor().clear();
            endDatePicker.getEditor().clear();
            btnADDnewSaleOperation1.setVisible(true);
        }
    }
    /**
     * this method will set the templates list to the combo choose
     */
    public void setTemplateList(ArrayList<String> cosArray) {
        ObservableList<String> TemplateName = FXCollections.observableArrayList(cosArray);
        ChooseTemplateCombo.setItems(TemplateName);
    }

    @FXML
    void handleChooseTemplate(ActionEvent event) {
        ERRORnoTemplate.setVisible(false);
        choosenTemplate = ChooseTemplateCombo.getValue();
        //Query to get from the db the chosen template information:
        myController.getChoosenTemplateDetails(); //start the process that will ask server to execute quarry and get the template details

        btnRunSaleOperation.setDisable(false);
        this.templateDetaildVBOX.setVisible(true);
        this.endDatePicker.setVisible(true);
        this.startDatePicker.setVisible(true);
    }

    public String getChoosenTemplate() {
        return choosenTemplate;
    }

    public void setChosenTemplateDetails(ArrayList<MarketingCampaignTemplate> cosArray) {
        MarketingCampaignTemplate my = new MarketingCampaignTemplate();
        my = cosArray.get(0);
        idFromDB.setText(my.getTemplateID());
        typeFromDB.setText(my.getFuelType());
        discountFromDB.setText(String.valueOf(my.getDiscountPercentages()) + "%");
        dayFromDB.setText(my.getDay());
        beginHourFromDB.setText(String.valueOf(my.getBeginHour()));
        endHourFromDB.setText(String.valueOf(my.getEndHour()));

    }


    @FXML
    void handleStartDate(ActionEvent event) {
        ERRORoverlap.setVisible(false);
        ERRORoverlap1.setVisible(false);
        //cant choose date before today:
        if (startDatePicker.getValue().isBefore(java.time.LocalDate.now()) == true ) {
            ERRORalreadyPassedDate.setVisible(true);
        }
    }

    @FXML
    void handleStartDateNew(MouseEvent event) {
        ERRORalreadyPassedDate.setVisible(false);
        ERRORoverlap.setVisible(false);
        ERRORoverlap1.setVisible(false);
    }

    @FXML
    void handleEndDate(ActionEvent event) {
        ERRORoverlap.setVisible(false);
        ERRORoverlap1.setVisible(false);
        //cant choose end date earlier then start date
        if (endDatePicker.getValue().isBefore(startDatePicker.getValue()) == true) {
            ERRORendBeforStart.setVisible(true);
        }
    }

    @FXML
    void handleEndDateNew(MouseEvent event) {
        ERRORendBeforStart.setVisible(false);
        ERRORoverlap.setVisible(false);
        ERRORoverlap1.setVisible(false);
    }


    private void FormValidation() {
        /*  Template Date validation */ //not work so well
      //  formValidation.isEmptyDateField(startDatePicker, "Start Date");
        //formValidation.isEmptyDateField(endDatePicker, "End Date");


    }

    @FXML
    void handleClicks(ActionEvent event) {

    }


}