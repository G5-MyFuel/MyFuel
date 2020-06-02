package client.boundary;

import client.logic.FormValidation;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author itay
 * @see client.logic.RunSaleOperationLogic - the form's logic class
 */

public class RunSaleOperationController implements Initializable {
        private static RunSaleOperationController Instance = null;
        private client.logic.RunSaleOperationLogic RunSaleOperationLogic;
        private FormValidation formValidation;

    /*Gui variables:
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
    private Button btnRunSaleOperation;

    @FXML
    private JFXDatePicker startDatePicker;

    @FXML
    private JFXDatePicker startDatePicker1;

    @FXML
    private JFXComboBox<String> ChooseTemplateCombo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.RunSaleOperationLogic = RunSaleOperationLogic.getInstance();
        this.formValidation = FormValidation.getValidator();
        //TODO: formValidation();   set all fields validators
       // FormValidation();   //
        /* check all required fields are'nt empty:*/

        /*  check form input validation */

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

    public void handleChooseTemplate(ActionEvent actionEvent) {
    }

    public void handleBtnRunSale(ActionEvent actionEvent) {
    }
}