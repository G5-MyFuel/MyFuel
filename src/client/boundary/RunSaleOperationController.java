package client.boundary;

import client.logic.FormValidation;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author hani
 * @see client.logic.RunSaleOperationLogic - the form's logic class
 */

public class RunSaleOperationController implements Initializable {
        private static RunSaleOperationController Instance = null;
        private client.logic.RunSaleOperationLogic RunSaleOperationLogic;
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
    private JFXComboBox<?> ChooseTemplateCombo;

    @FXML
    private Label ChooseTemplateTXT;

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

   //* private ObservableList<String> TemplateName = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.RunSaleOperationLogic = RunSaleOperationLogic.getInstance();

        //this.formValidation = FormValidation.getValidator();
        // TODO: formValidation();
        //FormValidation();   // check all required fields are'nt empty
    }

    /**
     * SaleOperationTemplateController Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of controller class
     */
    public RunSaleOperationController getInstance() {
        if (Instance == null)
            Instance = new RunSaleOperationController();
        return Instance;
    }

    @FXML
    public void handleChooseTemplate(javafx.event.ActionEvent actionEvent) {
    }

    @FXML
    public void handleBtnRunSale(javafx.event.ActionEvent actionEvent) {
    }

    @FXML
    void handleClicks(ActionEvent event) {

    }
}