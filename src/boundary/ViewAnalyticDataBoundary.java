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
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * @author Hana Wiener
 * @see ViewAnalyticDataController - the form's logic class
 */

public class ViewAnalyticDataBoundary implements DataInitializable {
    /** The supervisor boundary controller. */
    private ViewAnalyticDataController myController = new ViewAnalyticDataController(this);
    //private AnalyticDataCreator analyticDataCreator = new AnalyticDataCreator(this);
    private generalDashBoardBoundary myDashBoard;
    private String marketingManager;
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
    private JFXButton btnCustomerTypeShow;

    @FXML
    private Label selectedCustomerType;

    @FXML
    private Pane paneByFuelType;

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

    @FXML
    private JFXButton btnTimePickerShow;

    @FXML
    private JFXButton btnFuelTypeShow;


    private ObservableList<String> CustomerType = FXCollections.observableArrayList("private" , "Company");
    private ObservableList<String> FuelType = FXCollections.observableArrayList("Gasoline95", "Diesel", "ScooterFuel", "HomeHeatingFuel");

    @Override
    public void initData(Object data) {
         myDashBoard = (generalDashBoardBoundary) data;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneByCustomerType.setVisible(false);
        paneByFuelType.setVisible(false);
        paneByTime.setVisible(false);
        customerTypeCombo.setItems(CustomerType);
        fuelTypeCombo1.setItems(FuelType);
        btnCustomerTypeShow.setDisable(true);

    }

    private void formValidation() {
    }

    @FXML
    void handleGenerateData(ActionEvent event) {
        myController.deletePreviosData();
        myController.getCustomerXPurchaseTable();
    }

    @FXML
    void handleBtnCustomerType(MouseEvent event) {
        paneByCustomerType.setVisible(true);
    }

    @FXML
    void handleBtnFuleType(ActionEvent event) {
        paneByFuelType.setVisible(true);
    }

    @FXML
    void handleBtnRefuelHour(ActionEvent event) {
        paneByTime.setVisible(true);
    }

    @FXML
    void handleFuelTypeCombo(ActionEvent event) {
    }

    @FXML
    void handleTimeRangeCombo(ActionEvent event) {
    }

    @FXML
    void handleCustomerTypeComboClick(ActionEvent event) {
        btnCustomerTypeShow.setDisable(false);
        paneByCustomerType.setVisible(true);

    }

    @FXML
    void handlecustomerTypeCombo(ActionEvent event) {
        String str = customerTypeCombo.getValue();
        myController.getRatingForCustomerTypeTable(str);
        customerTypeCombo.setVisible(true);


    }
    public void setRatingForCustomerTypeTable(ArrayList<Rating> cosArray){
        ratingCulomn1.setCellValueFactory(new PropertyValueFactory<>("rating"));
        customerIdCulomn1.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        ObservableList<Rating> data = FXCollections.observableArrayList(cosArray);
        RatingTableForCustomerType.setItems(data);
    }

    public void setRatingForTimeRangeTable(ArrayList<Rating> cosArray){
        ratingCulomn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        customerIdCulomn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        ObservableList<Rating> data = FXCollections.observableArrayList(cosArray);
        RatingTableForTimeRange.setItems(data);
    }

    public void handleTimePickerShow(ActionEvent actionEvent) {
         myController.getRatingForTimeRangeTable(Time.valueOf(TimeRangeStart.getValue()),Time.valueOf(TimeRangeEnd.getValue()));

    }

    public void handleFuelTypeComboShow(ActionEvent actionEvent) {

    }


    @FXML
    void handleBtnCustomerType(ActionEvent event) {

    }

    @FXML
    void handleBtnCustomerTypeImage(MouseEvent event) {

    }



}