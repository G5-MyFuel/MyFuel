package boundary;

import Contollers.ViewAnalyticDataController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
import entity.Rating;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 *  This class This department is responsible for controlling "ViewAnalyticDataFXML" page
 *  This page display customer ratings (analytic data) based on three different parameters
 *  And, if necessary, allows data re-creation
 *
 *  * @author Hana Wiener
 * @see ViewAnalyticDataController - the form's logic class
 */
public class ViewAnalyticDataBoundary implements DataInitializable {
    /**
     *  The supervisor boundary controller.
     *  */
    private ViewAnalyticDataController myController = new ViewAnalyticDataController(this);
    /**
     *  The boundary of the side menu
     */
    private generalDashBoardBoundary myDashBoard;
    /**
     * A parameter that represents who enters the page
     */
    private String marketingManager;
    /**
     * gui variables
     */
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
    private Label lastCalcDateTXT;

    @FXML
    private JFXTimePicker TimeRangeStart;

    @FXML
    private JFXTimePicker TimeRangeEnd;

    @FXML
    private JFXButton btnTimePickerShow;

    @FXML
    private JFXButton btnFuelTypeShow;

    @FXML
    private PieChart pieChartCustomerType;

    @FXML
    private Text selectedCustomerTypeTXT;

    @FXML
    private PieChart pieChartHouers;

    @FXML
    private Text selectedhouresTXT;

    @FXML
    private PieChart pieChartFuelType;
    @FXML
    private Text selectedFuelTypeTXT;

    /*
      Initialize ObservableList in order to display does strings
      with combo box object.
       * * */
    private ObservableList<String> CustomerType = FXCollections.observableArrayList("private" , "Company");
    private ObservableList<String> FuelType = FXCollections.observableArrayList("Gasoline95", "Diesel", "ScooterFuel", "HomeHeatingFuel");

    /**
     *  This method allows to save information sent when uploading the page (user id)
     *
     * @param data - The data sent to the boundary
     */
    @Override
    public void initData(Object data) {
         myDashBoard = (generalDashBoardBoundary) data;

    }

    /**
     * This method initializes the variables, fields, and combo-boxes
     * What is initialized will appear when the screen is raised
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneByCustomerType.setVisible(false);
        paneByFuelType.setVisible(false);
        paneByTime.setVisible(false);
        customerTypeCombo.setItems(CustomerType);
        fuelTypeCombo1.setItems(FuelType);
        btnCustomerTypeShow.setDisable(true);
    }

    /**
     * This method will only be activated if the "Generate Analitic Data" button is pressed
     * The method calls for a query that will get customer and purchases data ,
     *  calculate their ratings, and save to DB.
     *
     * @param event
     */
    @FXML
    void handleGenerateData(ActionEvent event) {
        paneByFuelType.setVisible(false);
        paneByCustomerType.setVisible(false);
        paneByTime.setVisible(false);
        myController.deletePreviosData();
        myController.getCustomerXPurchaseTable();
    }

    /**
     *  This method will only be activated if the "Segmentation by fuel type" button is pressed
     *  The method will display the window that includes fuel type rating.
     *
     * @param event
     */
    @FXML
    void handleBtnFuleType(ActionEvent event) {
        paneByCustomerType.setVisible(false);
        paneByTime.setVisible(false);
        paneByFuelType.setVisible(true);
    }

    /**
     * This method will only be activated if the "Segmentation by fuel type" button is pressed
     * The method will display the window that includes fuel type rating.
     *
     * @param event
     */
    @FXML
    void handleBtnFuleTypeImage(MouseEvent event) {
        paneByCustomerType.setVisible(false);
        paneByTime.setVisible(false);
        paneByFuelType.setVisible(true);
    }

    /**
     *This method will only be activated if the "Segmentation by refueling hours" button is pressed
     * The method will display the window that includes hourly rankings
     *
     * @param event
     */
    @FXML
    void handleBtnRefuelHour(ActionEvent event) {
        paneByFuelType.setVisible(false);
        paneByCustomerType.setVisible(false);
        paneByTime.setVisible(true);
    }

    /**
     *This method will only be activated if the "Segmentation by refueling hours" button is pressed
     * The method will display the window that includes hourly rankings
     *
     * @param event
     */
    @FXML
    void handleBtnRefuelHourImage(MouseEvent event) {
        paneByFuelType.setVisible(false);
        paneByCustomerType.setVisible(false);
        paneByTime.setVisible(true);
    }

    /**
     *This method will only be activated if the "Select Fuel Type:" combo is pressed
     * The method will not allow clicking "Display Data" until a fuel type is selected
     *
     * @param event
     */
    @FXML
    void handleFuelTypeCombo(ActionEvent event) {
        btnFuelTypeShow.setDisable(false);
        paneByFuelType.setVisible(true);

    }

    /**
     *  This method will only be activated if the "Select Customer Type:" combo is pressed
     *  The method will not allow clicking "Display Data" until a Customer type is selected
     *
     * @param event
     */
    @FXML
    void handleCustomerTypeComboClick(ActionEvent event) {
        btnCustomerTypeShow.setDisable(false);
        paneByCustomerType.setVisible(true);

    }

    /**
     *This method will only be activated if the "Show Segmentation by customer type" button is pressed
     * The method will display the window that show ranking by customer type parameter
     *
     * @param event
     */
    @FXML
    void handleBtnCustomerType(ActionEvent event) {
        paneByFuelType.setVisible(false);
        paneByTime.setVisible(false);
        paneByCustomerType.setVisible(true);

    }

    /**
     *  This method will only be activated if the "Show Segmentation by fuel type" button is pressed
     *  The method will begin the process of sending a query that will bring DB to the ratings table
     *
     * @param actionEvent
     */
    public void handleFuelTypeComboShow(ActionEvent actionEvent) {
        String str = fuelTypeCombo1.getValue();
        myController.getRatingForFuelTypeTable(str);
        RatingTableForFuelType.setVisible(true);
    }

    /**
     *  This method will only be activated if the "Show Segmentation by customer type" button is pressed
     *  The method will begin the process of sending a query that will bring DB to the ratings table
     *
     * @param event
     */
    @FXML
    void handlecustomerTypeCombo(ActionEvent event) {
        String str = customerTypeCombo.getValue();
        myController.getRatingForCustomerTypeTable(str);
        customerTypeCombo.setVisible(true);

    }

    /**
     *  This method will only be activated if the "Segmentation by refueling hours" button is pressed
     *  The method will begin the process of sending a query that will bring DB to the ratings table
     *
     *  * @param actionEvent
     */
    public void handleTimePickerShow(ActionEvent actionEvent) {
        myController.getRatingForTimeRangeTable(Time.valueOf(TimeRangeStart.getValue()),Time.valueOf(TimeRangeEnd.getValue()));

    }

    //TODO: continue java doc
    /**
     *This method will only be activated if the "customer type" image is pressed
     * The method will display the window that show ranking by customer type parameter
     *
     * @param event
     */
    @FXML
    void handleBtnCustomerTypeImage(MouseEvent event) {
        paneByFuelType.setVisible(false);
        paneByTime.setVisible(false);
        paneByCustomerType.setVisible(true);

    }

    /**
     *This method receives from the controller an array with the values obtained from the DB
     * And places them in the appropriate table of ratings- CustomerTypeTable
     * And send to another function to place them in a pie chart
     *
     * @param cosArray  An array of ratings and customer IDs received from DB
     */
    public void setRatingForCustomerTypeTable(ArrayList<Rating> cosArray){
        ratingCulomn1.setCellValueFactory(new PropertyValueFactory<>("rating"));
        customerIdCulomn1.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        ObservableList<Rating> data = FXCollections.observableArrayList(cosArray);
        selectedCustomerTypeTXT.setText(customerTypeCombo.getValue());
        showPieChartCustomerType (data);
        RatingTableForCustomerType.setItems(data);
    }

    /**
     *This method receives from the controller an array with the values obtained from the DB
     * And places them in the appropriate table of ratings - FuelTypeTable
     * And send to another function to place them in a pie chart
     *
     * @param cosArray
     */
    public void setRatingForFuelTypeTable(ArrayList<Rating> cosArray){
        ratingCulomn11.setCellValueFactory(new PropertyValueFactory<>("rating"));
        customerIdCulomn11.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        ObservableList<Rating> data = FXCollections.observableArrayList(cosArray);
        selectedFuelTypeTXT.setText(fuelTypeCombo1.getValue());
        showPieChartFuelType ();
        RatingTableForFuelType.setItems(data);
    }

    /**
     *This method receives from the controller an array with the values obtained from the DB
     * And places them in the appropriate table of ratings - TimeRangeTable
     * And send to another function to place them in a pie chart
     *
     * @param cosArray
     */
    public void setRatingForTimeRangeTable(ArrayList<Rating> cosArray){
        ratingCulomn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        customerIdCulomn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        ObservableList<Rating> data = FXCollections.observableArrayList(cosArray);
        selectedhouresTXT.setText(TimeRangeStart.getValue() + " to " + TimeRangeEnd.getValue());
        showPieChartHours();
        RatingTableForTimeRange.setItems(data);
    }

    /**
     *This function receives an array of data and displays it in a rating pie chart
     *Segmented by customer type
     *
     * @param data   An array with ratings
     */
    private void showPieChartCustomerType(ObservableList<Rating> data) {
        int[] monim = new int[11];
        for(int i=0;i<10;i++)
            monim[i] = 0;
        for (int i=0;i<data.size();i++) {
            monim[data.get(i).getRating()] ++;
        }
        ObservableList<PieChart.Data> piedata = FXCollections.observableArrayList(
                new PieChart.Data("Rating 1", monim[1]), new PieChart.Data("Rating 2", monim[2]),
                new PieChart.Data("Rating 3", monim[3]),new PieChart.Data("Rating 4", monim[4]),
                new PieChart.Data("Rating 5", monim[5]),new PieChart.Data("Rating 6", monim[6]),
                new PieChart.Data("Rating 7", monim[7]),new PieChart.Data("Rating 8", monim[8]),
                new PieChart.Data("Rating 9", monim[9]),new PieChart.Data("Rating 10", monim[10]));
        pieChartCustomerType.setData(piedata);
    }

    /**
     *This function receives an array of data and displays it in a rating pie chart
     * Segmented by fuel type
     *
     */
    private void showPieChartFuelType() {
        int[] monim = myController.counters;
        ObservableList<PieChart.Data> piedata1 = FXCollections.observableArrayList(
                new PieChart.Data("Rating 1", monim[1]), new PieChart.Data("Rating 2", monim[2]),
                new PieChart.Data("Rating 3", monim[3]),new PieChart.Data("Rating 4", monim[4]),
                new PieChart.Data("Rating 5", monim[5]),new PieChart.Data("Rating 6", monim[6]),
                new PieChart.Data("Rating 7", monim[7]),new PieChart.Data("Rating 8", monim[8]),
                new PieChart.Data("Rating 9", monim[9]),new PieChart.Data("Rating 10", monim[10]));
        pieChartFuelType.setData(piedata1);

    }

    /**
     * This function receives an array of data and displays it in a rating pie chart
     *Segmented by refueling hours
     *
     */
    private void showPieChartHours() {
        int[] monim = myController.counters;
        ObservableList<PieChart.Data> piedata2 = FXCollections.observableArrayList(
                new PieChart.Data("Rating 1", monim[1]), new PieChart.Data("Rating 2", monim[2]),
                new PieChart.Data("Rating 3", monim[3]),new PieChart.Data("Rating 4", monim[4]),
                new PieChart.Data("Rating 5", monim[5]),new PieChart.Data("Rating 6", monim[6]),
                new PieChart.Data("Rating 7", monim[7]),new PieChart.Data("Rating 8", monim[8]),
                new PieChart.Data("Rating 9", monim[9]),new PieChart.Data("Rating 10", monim[10]));
        pieChartHouers.setData(piedata2);

    }


}