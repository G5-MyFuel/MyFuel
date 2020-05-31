//package client.boundary;
//
//import client.logic.FormValidation;
//import client.logic.NewPurchaseFuelForHomeHeatingLogic;
//import com.jfoenix.controls.JFXComboBox;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.SplitPane;
//import javafx.scene.control.TableView;
//import javafx.scene.image.ImageView;
//import client.logic.SaleOperationTemplateLogic;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.MouseEvent;
//import java.net.URL;
//import java.util.ResourceBundle;
//
///**
// * @author hani
// * @see SaleOperationTemplateLogic - the form's logic class
// */
//
//public class SaleOperationTemplateController implements Initializable {
//    private SaleOperationTemplateLogic newSaleOperationTemplateLogic;
//    private FormValidation formValidation;
//
//    //gui variables:
//    @FXML
//    private Button btnOverview;
//
//    @FXML
//    private Button btnCustomers;
//
//    @FXML
//    private Button btnMenus;
//
//    @FXML
//    private Button btnPackages;
//
//    @FXML
//    private Button btnSignout;
//
//    @FXML
//    private TableView<?> employeesTableView;
//
//    @FXML
//    private ImageView btnAddTemplate;
//
//    @FXML
//    private Label txtAddTemplate;
//
//    @FXML
//    private SplitPane newTemplateDetails;
//
//    @FXML
//    private Button btnNewTemplate;
//
//    @FXML
//    private TextField TemplateName;
//
//    @FXML
//    private ComboBox<?> ChooseGasTypeComboSpecialization;
//
//    @FXML
//    private ComboBox<?> DayComboSpecialization1;
//
//    @FXML
//    private  TextField StartHour;
//
//    @FXML
//    private  TextField EndHour;
//
//    @FXML
//    private  TextField MarketingAdForTemplate;
//
//    @FXML
//    private TextField DiscountPercentages;
//
//
//
//    @FXML
//    void handleClicks(ActionEvent event) {
//
//    }
//
//    @FXML
//    void handleAddTemplate(MouseEvent event) {
//        newTemplateDetails.setVisible(true);
//        btnAddTemplate.setVisible(false);
//        txtAddTemplate.setVisible(false);
//
//    }
//
//    @FXML
//    void handleSaveTemplate(ActionEvent event) {
//        newTemplateDetails.setVisible(false);
//        btnAddTemplate.setVisible(true);
//        txtAddTemplate.setVisible(true);
//
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        newTemplateDetails.setVisible(false);
//
//        this.newSaleOperationTemplateLogic = SaleOperationTemplateLogic.getInstance();
//        this.formValidation = FormValidation.getValidator();
//        //
//        //TODO: formValidation();   //set all fields validators
//
//        /*  check all required fields are'nt empty:*/
//
//        /*  check form input validation */
//
//    }
//
//    private void formValidation() {
//        /*  Template Name validation */
//        formValidation.isEmptyField(TemplateName, "Template Name");
//        formValidation.maxLengthValidation(TemplateName, "Template Name", 45);
//
//        /*  Discount Percentages validation */
//        formValidation.isEmptyField(DiscountPercentages, "Discount Percentages");
//        formValidation.isContainsOnlyNumbers(DiscountPercentages, "Discount Percentages");
//        formValidation.numberPositiveValidation(DiscountPercentages, "Discount Percentages");
//
//        /*  Gas Type validation */
//        formValidation.isEmptyField(ChooseGasTypeComboSpecialization, "Choose Gas Type");
//
//        /*  Day validation */
//        formValidation.isEmptyField(DayComboSpecialization1, "Day");
//
//        /*  Start Hour validation */
//        formValidation.isEmptyField(StartHour, "Start Hour");
//        formValidation.isContainsOnlyNumbers(StartHour, "Start Hour");
//        formValidation.numberPositiveValidation(StartHour, "Start Hour");
//
//        /*  End Hour validation */
//        formValidation.isEmptyField(EndHour, "End Hour");
//        formValidation.isContainsOnlyNumbers(EndHour, "End Hour");
//        formValidation.numberPositiveValidation(EndHour, "End Hour");
//
//        /*  Marketing Ad For Template validation */
//        formValidation.isEmptyField(MarketingAdForTemplate, "Marketing Ad For Template");
//
//    }
//
//}
