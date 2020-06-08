package boundary;

import Contollers.FormValidation;
import Contollers.RunSaleOperationController;
import Contollers.SaleOperationTemplateController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import entity.SaleOperation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
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
    private Button btnRunSaleOperation;

    @FXML
    private Label startSaleDateTXT;

    @FXML
    private JFXDatePicker startDatePicker;

    @FXML
    private Label endSaleDateTXT;

    @FXML
    private JFXDatePicker endDatePicker;

    @FXML
    private JFXComboBox<String> ChooseTemplateCombo;

    @FXML
    private Label ChooseTemplateTXT;

    @FXML
    private VBox templateDetailsVBOX;

    @FXML
    private Label TemplateIDTXT;

    @FXML
    private Label FuelTypeTXT;

    @FXML
    private Label DiscountTXT;

    @FXML
    private Label DayTXT;

    @FXML
    private Label BeginHourTXT;

    @FXML
    private Label EndHourTXT;
 private ObservableList<String> TemplateID = FXCollections.observableArrayList("0001","0002","0003","0004","0005","0006");
 //TODO: Change to template name from DB^

    @Override
       public void initialize(URL location, ResourceBundle resources) {
        ChooseTemplateCombo.setItems(TemplateID);///to do ^
        this.formValidation = FormValidation.getValidator();
        // TODO: formValidation();
        FormValidation();   // check all required fields are'nt empty
        myController.insertNewSaleOperation();

        templateDetailsVBOX.setVisible(false);
        startSaleDateTXT.setVisible(false);
        startDatePicker.setVisible(false);
        endSaleDateTXT.setVisible(false);
        endDatePicker.setVisible(false);
        btnRunSaleOperation.setVisible(false);
        btnRunSaleOperation.setDisable(false);//???

    }

    @FXML
    public void handleChooseTemplate(javafx.event.ActionEvent actionEvent) {
        //save the chosen one
        String chosenTemplate = new String(ChooseTemplateCombo.getValue());

        templateDetailsVBOX.setVisible(true);
        startSaleDateTXT.setVisible(true);
        startDatePicker.setVisible(true);
        endSaleDateTXT.setVisible(true);
        endDatePicker.setVisible(true);
        btnRunSaleOperation.setVisible(true);
    }

    @FXML
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