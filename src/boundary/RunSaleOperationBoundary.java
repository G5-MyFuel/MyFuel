package boundary;

import Contollers.FormValidation;
import Contollers.RunSaleOperationController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.sun.xml.internal.ws.api.message.saaj.SAAJMessageHeaders;
import entity.SaleOperation;
import entity.SaleOperationTemplate;
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
 * @author Hana Wiener
 * @see Contollers.RunSaleOperationController - the form's logic class
 */

public class RunSaleOperationBoundary implements Initializable {
    /**
     * The supervisor boundary controller.
     */
    private RunSaleOperationController myController = new RunSaleOperationController(this);

    private FormValidation formValidation;

    /*
    Gui variables:
    * */
    @FXML
    private TableView<SaleOperation> saleOperationTableView;

    @FXML
    private TableColumn<SaleOperation, String> saleIDColumn;

    @FXML
    private TableColumn<SaleOperation, String> TemplateNameColumn;

    @FXML
    private TableColumn<SaleOperation, String> StartDateColumn;

    @FXML
    private TableColumn<SaleOperation, String> EndDateColumn;


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


    String choosenTemplate = new String();
    boolean flagSale = true;
    SaleOperation newSale = new SaleOperation();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.detailsPane.setVisible(false);
        ERRORalreadyPassedDate.setVisible(false);
        ERRORendBeforStart.setVisible(false);
        ERRORoverlap.setVisible(false);
        ERRORoverlap1.setVisible(false);

        this.formValidation = FormValidation.getValidator();
        FormValidation();   // check all required fields are'nt empty

        myController.getSalesTable(); //start the process that will ask server to execute quarry and get the table details
        myController.getTemplateList(); //start the process that will ask server to execute quarry and get the template list
    }


    /**
     * this method will set the templates table when we will initialize the page.
     */
    public void setSalesTable(ArrayList<SaleOperation> cosArray) {
        saleIDColumn.setCellValueFactory(new PropertyValueFactory<>("SaleOperationID"));
        TemplateNameColumn.setCellValueFactory(new PropertyValueFactory<>("TemplateName"));
        StartDateColumn.setCellValueFactory(new PropertyValueFactory<>("BeginDate"));
        EndDateColumn.setCellValueFactory(new PropertyValueFactory<>("EndDate"));

        ObservableList<SaleOperation> data = FXCollections.observableArrayList(cosArray);
        saleOperationTableView.setItems(data);
    }


    @FXML
    void handleBtnAddSale(ActionEvent event) {
        this.templateDetaildVBOX.setVisible(false);
        this.endDatePicker.setVisible(false);
        this.startDatePicker.setVisible(false);
        btnADDnewSaleOperation1.setVisible(false);

        detailsPane.setVisible(true);
    }

    @FXML
    void handleBtnRunSale(ActionEvent event) {
         newSale = new SaleOperation(String.valueOf(myController.getSaleCounter() + 1), (String) ChooseTemplateCombo.getValue(), Date.valueOf(startDatePicker.getValue()),
                Date.valueOf(endDatePicker.getValue()));
        // chack if sale can run is this dates:
        myController.chackIfSaleCanRun(newSale);
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
        choosenTemplate = ChooseTemplateCombo.getValue();
        //Query to get from the db the chosen template information:
        myController.getChoosenTemplateDetails(); //start the process that will ask server to execute quarry and get the template details

        this.templateDetaildVBOX.setVisible(true);
        this.endDatePicker.setVisible(true);
        this.startDatePicker.setVisible(true);
    }

    public String getChoosenTemplate() {
        return choosenTemplate;
    }

    public void setChosenTemplateDetails(ArrayList<SaleOperationTemplate> cosArray) {
        SaleOperationTemplate my = new SaleOperationTemplate();
        my = cosArray.get(0);
        idFromDB.setText(my.getTemplateID());
        typeFromDB.setText(my.getFuelType());
        discountFromDB.setText(String.valueOf(my.getDiscountPercentages()) + "%");
        dayFromDB.setText(my.getDay());
        beginHourFromDB.setText(String.valueOf(my.getBeginHour()));
        endHourFromDB.setText(String.valueOf(my.getEndHour()));

    }


    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleStartDate(ActionEvent event) {
        ERRORoverlap.setVisible(false);
        ERRORoverlap1.setVisible(false);
        //cant choose date before today:
        if (startDatePicker.getValue().isBefore(java.time.LocalDate.now()) == true) {
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
        /*  Template Name validation */
        formValidation.isEmptyDateField(startDatePicker, "Start Date");
        formValidation.isEmptyDateField(endDatePicker, "End Date");
    }

}