package boundary;

import Contollers.AnalyticDataCreator;
import Contollers.FormValidation;
import Contollers.MarketingCampaignTemplateController;
import Contollers.ViewAnalyticDataController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
import com.sun.deploy.panel.RuleSetViewerDialog;
import entity.MarketingCampaignTemplate;
import entity.Rating;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * @author Hana Wiener
 * @see ViewAnalyticDataController - the form's logic class
 */

public class ViewAnalyticDataBoundary implements Initializable {
    /** The supervisor boundary controller. */
    private ViewAnalyticDataController myController = new ViewAnalyticDataController(this);
    private AnalyticDataCreator analyticDataCreator = new AnalyticDataCreator(this);

    private FormValidation formValidation;

    @FXML
    private Button btnGenerateAnalyticData;

    @FXML
    private JFXButton btnCustomerType;

    @FXML
    private ImageView imageRefuelHour;

    @FXML
    private ImageView imageFuelType;

    @FXML
    private JFXButton btnRefuelHour;

    @FXML
    private JFXButton btnFuleType;

    @FXML
    private ImageView imageCustomerType;

    @FXML
    private Pane paneByTime;

    @FXML
    private TableView<Rating> RatingTableForTimeRange;

    @FXML
    private TableColumn<Rating, String> customerIdCulomn;

    @FXML
    private TableColumn<Rating, Integer> ratingCulomn;

    @FXML
    private Label selectedTimeRange;

    @FXML
    private Pane paneByCustomerType;

    @FXML
    private TableView<Rating> RatingTableForCustomerType;

    @FXML
    private TableColumn<Rating, String> customerIdCulomn1;

    @FXML
    private TableColumn<Rating, Integer> ratingCulomn1;

    @FXML
    private JFXComboBox<String> customerTypeCombo;

    @FXML
    private Label selectedCustomerType;

    @FXML
    private Pane paneByCustomerType1;

    @FXML
    private TableView<Rating> RatingTableForFuelType;

    @FXML
    private TableColumn<Rating, String> customerIdCulomn11;

    @FXML
    private TableColumn<Rating, Integer> ratingCulomn11;

    @FXML
    private JFXComboBox<String> fuelTypeCombo1;

    @FXML
    private Label selectedFuelTypeTXT;

    @FXML
    private Label lastCalcDateTXT;

    @FXML
    private JFXTimePicker TimeRangeStart;

    @FXML
    private JFXTimePicker TimeRangeEnd;

    private ObservableList<String> CustomerType = FXCollections.observableArrayList("PRIVATE" , "COMPANY");
    private ObservableList<String> FuelType = FXCollections.observableArrayList("Gasoline95", "Diesel", "ScooterFuel", "HomeHeatingFuel");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerTypeCombo.setItems(CustomerType);
        fuelTypeCombo1.setItems(FuelType);

    }


    private void formValidation() {
        /**
         *
         *
         */

    }

    @FXML
    void handleGenerateData(ActionEvent event) {
        analyticDataCreator.getCustomerXPurchaseTable();
        myController.getRatingTable(); //start the process that will ask server to execute quarry and get the table details
    }

    @FXML
    void handleBtnCustomerType(MouseEvent event) { // chack mouse event import

    }

    @FXML
    void handleBtnFuleType(ActionEvent event) {

    }

    @FXML
    void handleBtnRefuelHour(ActionEvent event) {

    }



    /**
     this method will set the templates table when we will initialize the page.
     */
    public void setRatingTable(ArrayList<Rating> cosArray){
        ratingCulomn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        customerIdCulomn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        ObservableList<Rating> data = FXCollections.observableArrayList(cosArray);
        RatingTableForCustomerType.setItems(data); //באופן זמני !!!!

    }


    @FXML
    void handleFuelTypeCombo(ActionEvent event) {

    }


    @FXML
    void handleTimeRangeCombo(ActionEvent event) {

    }

    @FXML
    void handlecustomerTypeCombo(ActionEvent event) {

    }

}