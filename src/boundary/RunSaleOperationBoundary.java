package boundary;

import Contollers.FormValidation;
import Contollers.RunSaleOperationController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import entity.SaleOperation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    /** The supervisor boundary controller. */
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

    String choosenTemplate = new String();

    @Override
       public void initialize(URL location, ResourceBundle resources) {
        this.detailsPane.setVisible(false);
        //ChooseTemplateCombo.setItems(TemplateName);///to do from db

        this.formValidation = FormValidation.getValidator();
        FormValidation();   // check all required fields are'nt empty

        myController.getSalesTable(); //start the process that will ask server to execute quarry and get the table details
        myController.getTemplateList(); //start the process that will ask server to execute quarry and get the template list
    }


    /**
     this method will set the templates table when we will initialize the page.
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
        SaleOperation newSale = new SaleOperation(String.valueOf(myController.getSaleCounter()+1),
                (String)ChooseTemplateCombo.getValue(),
                Date.valueOf(startDatePicker.getValue()),
                Date.valueOf(endDatePicker.getValue()));
        myController.setSaleOperationInDB(newSale);
        myController.getSalesTable(); //start the process that will ask server to execute quarry and get the table details//refresh
        detailsPane.setVisible(false);

        //clear all fileds:
        ChooseTemplateCombo.getSelectionModel().clearSelection();
        startDatePicker.getEditor().clear();
        startDatePicker.getEditor().clear();
    }

    /**
     this method will set the templates list to the combo choose
     */
    public void setTemplateList(ArrayList<String> cosArray) {
        ObservableList<String> TemplateName = FXCollections.observableArrayList(cosArray);
        ChooseTemplateCombo.setItems(TemplateName);
    }


    @FXML
    void handleChooseTemplate(ActionEvent event) {
        //שאילתה להביא את פרטי התבנית
        myController.getTemplateList(); //start the process that will ask server to execute quarry and get the template details
        choosenTemplate = ChooseTemplateCombo.getValue();

        this.templateDetaildVBOX.setVisible(true);
        this.endDatePicker.setVisible(true);
        this.startDatePicker.setVisible(true);
        btnADDnewSaleOperation1.setVisible(true);


    }




    @FXML//?????????
    public void handleBtnRunSaleOperation(javafx.event.ActionEvent actionEvent) {

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