package boundary;

import Contollers.FormValidation;
import Contollers.RunSaleOperationController;
import Contollers.SaleOperationTemplateController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import entity.SaleOperation;
import entity.SaleOperationTemplate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
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
    private TableView<SaleOperation> saleOperationTableView;

    @FXML
    private TableColumn<SaleOperation, String> saleIDColumn;

    @FXML
    private TableColumn<SaleOperation, String> TemplateNameColumn;

    @FXML
    private TableColumn<SaleOperation, String> BeginHourColumn;

    @FXML
    private TableColumn<SaleOperation, String> EndHourColumn;

    @FXML
    private Pane detailsPane;

    @FXML
    private JFXComboBox<String> ChooseTemplateCombo;

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
    private JFXDatePicker startDatePicker;

    @FXML
    private JFXDatePicker endDatePicker;

    @FXML
    private Button btnRunSaleOperation;

    @FXML
    private Button btnAddNewSale;

    @FXML
    private Label EndHourTXT;


 private ObservableList<String> TemplateID = FXCollections.observableArrayList("0001","0002","0003","0004","0005","0006");
 //TODO: Change to template name from DB^

    @Override
       public void initialize(URL location, ResourceBundle resources) {
        this.detailsPane.setVisible(false);
        ChooseTemplateCombo.setItems(TemplateID);///to do from db

        this.formValidation = FormValidation.getValidator();
        FormValidation();   // check all required fields are'nt empty

        myController.getSalesTable(); //start the process that will ask server to execute quarry and get the table details

    }


    /**
     this method will set the templates table when we will initialize the page.
     */
     public void setSalesTable(ArrayList<SaleOperation> cosArray) {
         saleIDColumn.setCellValueFactory(new PropertyValueFactory<>("SaleOperationID"));
         TemplateNameColumn.setCellValueFactory(new PropertyValueFactory<>("TemplateName"));
         BeginHourColumn.setCellValueFactory(new PropertyValueFactory<>("BeginDate"));
         EndHourColumn.setCellValueFactory(new PropertyValueFactory<>("EndDate"));

         ObservableList<SaleOperation> data = FXCollections.observableArrayList(cosArray);
         saleOperationTableView.setItems(data);
     }


    @FXML
    public void handleChooseTemplate(javafx.event.ActionEvent actionEvent) {//no need

    }

    @FXML
    void handleBtnAddSale(ActionEvent event) {
        detailsPane.setVisible(true);
        btnAddNewSale.setVisible(false);

     }

    @FXML
    void handleBtnRunSale(ActionEvent event) {
        SaleOperation newSale = new SaleOperation(String.valueOf(myController.getSaleCounter()+1), (String)ChooseTemplateCombo.getValue(),  Date.valueOf(startDatePicker.getValue()), Date.valueOf(endDatePicker.getValue()));
        myController.setSaleOperationInDB(newSale);
        myController.getSalesTable(); //start the process that will ask server to execute quarry and get the table details//refresh
        detailsPane.setVisible(false);//?

        //clear all fileds:
        ChooseTemplateCombo.getSelectionModel().clearSelection();
        startDatePicker.getEditor().clear();
        startDatePicker.getEditor().clear();
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