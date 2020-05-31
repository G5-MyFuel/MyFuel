package client.boundary;

import client.logic.NewPurchaseFuelForHomeHeatingLogic;
import client.logic.SaleOperationTemplateLogic;
import client.logic.FormValidation;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.entity.Day;
import common.entity.Employee;
import common.entity.FuelTypes;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Hana Wiener
 * @see SaleOperationTemplateLogic - the form's logic class
 */

public class SaleOperationTemplateController implements Initializable {

    private SaleOperationTemplateLogic newSaleOperationTemplateLogic;
    private FormValidation formValidation;

    ObservableList<Employee> data1;
    ArrayList<Employee> employeeArrayList;
    //
    TableColumn templateIDColumn = new TableColumn("Template Number");
    TableColumn templateNameColumn = new TableColumn("Template Name");
    TableColumn fuelTypeColumn = new TableColumn("Fuel Type");
    TableColumn DiscountPercentagesColumn = new TableColumn("Discount Percentages");
    TableColumn MarketingAdForTemplateColumn = new TableColumn("Marketing Ad For Template");
    TableColumn dayColumn = new TableColumn("Day");
    TableColumn beginHourColumn = new TableColumn("Beginning Hour");
    TableColumn endHourColumn = new TableColumn("End Hour");
    //

    //gui variables:
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
    private TableView<?> employeesTableView;

    @FXML
    private ImageView btnAddTemplate;

    @FXML
    private Label txtAddTemplate;

    @FXML
    private SplitPane newTemplateDetails;

    @FXML
    private Button btnNewTemplate;

    @FXML
    private JFXTextField TemplateName;

    @FXML
    private JFXComboBox<?> ChooseGasTypeComboSpecialization;

    @FXML
    private JFXComboBox<?> DayComboSpecialization1;

    @FXML
    private JFXTextField StartHour;

    @FXML
    private JFXTextField EndHour;

    @FXML
    private JFXTextField MarketingAdForTemplate;

    @FXML
    private JFXTextField DiscountPercentages;

    @FXML
    private Text errorMassageEmptyFileds;


     @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.newSaleOperationTemplateLogic = newSaleOperationTemplateLogic.getInstance();
        this.formValidation = FormValidation.getValidator();
        this.newTemplateDetails.setVisible(false);
        this.errorMassageEmptyFileds.setVisible(false);
        //TODO: formValidation();   set all fields validators
        //formValidation();   //
        /*  check all required fields are'nt empty:*/

        /*  check form input validation */

    }
    @FXML
    void handleAddTemplate(MouseEvent event) {
        newTemplateDetails.setVisible(true);
        btnAddTemplate.setVisible(false);
        txtAddTemplate.setVisible(false);

    }

    @FXML
    void handleSaveTemplate(ActionEvent event) {
        //TODO: save it in DB
        newTemplateDetails.setVisible(false);
        btnAddTemplate.setVisible(true);
        txtAddTemplate.setVisible(true);

/*   boolean thereIsEmptyFiled = false;
        for ( boolean b : IfEmptyFileds) {
            if (b) {
                thereIsEmptyFiled = true;
            }
        }
        */
    }

    private void formValidation() {
        /*  Template Name validation */
        formValidation.isEmptyField(TemplateName, "Template Name");
        formValidation.maxLengthValidation(TemplateName, "Template Name", 45);

        /*  Discount Percentages validation */
        formValidation.isEmptyField(DiscountPercentages, "Discount Percentages");
        formValidation.isContainsOnlyNumbers(DiscountPercentages, "Discount Percentages");
        formValidation.numberPositiveValidation(DiscountPercentages, "Discount Percentages");

        /*  Start Hour validation */
        formValidation.isEmptyField(StartHour, "Start Hour");
        formValidation.isContainsOnlyNumbers(StartHour, "Start Hour");
        formValidation.numberPositiveValidation(StartHour, "Start Hour");

        /*  End Hour validation */
        formValidation.isEmptyField(EndHour, "End Hour");
        formValidation.isContainsOnlyNumbers(EndHour, "End Hour");
        formValidation.numberPositiveValidation(EndHour, "End Hour");

        /*  Marketing Ad For Template validation */
        formValidation.isEmptyField(MarketingAdForTemplate, "Marketing Ad For Template");

    }

}
