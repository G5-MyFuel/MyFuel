package client.boundary;

import client.logic.FormValidation;
import client.logic.NewPurchaseFuelForHomeHeatingLogic;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import client.logic.SaleOperationTemplateLogic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author hani
 * @see SaleOperationTemplateLogic - the form's logic class
 */

public class SaleOperationTemplateController implements Initializable {
    private SaleOperationTemplateLogic newSaleOperationTemplateLogic;
    private FormValidation formValidation;

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
    private JFXComboBox<?> comboSpecialization;

    @FXML
    private JFXComboBox<?> comboSpecialization1;
    private SaleOperationTemplateLogic SaleOperationTemplateLogic;

    @FXML
    void handleAddTemplate(MouseEvent event) {
        newTemplateDetails.setVisible(true);
        btnAddTemplate.setVisible(false);
        txtAddTemplate.setVisible(false);

    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleSaveTemplate(ActionEvent event) {
        newTemplateDetails.setVisible(false);
        btnAddTemplate.setVisible(true);
        txtAddTemplate.setVisible(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newTemplateDetails.setVisible(false);

        this.newSaleOperationTemplateLogic = SaleOperationTemplateLogic.getInstance();
        this.formValidation = FormValidation.getValidator();
        //
        //TODO: formValidation();   //set all fields validators

        /*  check all required fields are'nt empty:*/

        /*  check form input validation */

    }
}
