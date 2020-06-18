package boundary;

import Contollers.FormValidation;
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

        /*ratingCulomn.setStyle( "-fx-alignment: CENTER;");
        ratingCulomn1.setStyle( "-fx-alignment: CENTER-RIGHT;");
        ratingCulomn11.setStyle( "-fx-alignment: CENTER-RIGHT;");
        customerIdCulomn.setStyle( "-fx-alignment: CENTER-RIGHT;");
        customerIdCulomn1.setStyle( "-fx-alignment: CENTER-RIGHT;");
        customerIdCulomn11.setStyle( "-fx-alignment: CENTER-RIGHT;");
*/
    }

    private void formValidation() {
    }

    @FXML
    void handleGenerateData(ActionEvent event) {
        myController.deletePreviosData();
        myController.getCustomerXPurchaseTable();
    }

    @FXML
    void handleBtnFuleType(ActionEvent event) {
        paneByCustomerType.setVisible(false);
        paneByTime.setVisible(false);
        paneByFuelType.setVisible(true);
    }

    @FXML
    void handleBtnFuleTypeImage(MouseEvent event) {
        paneByCustomerType.setVisible(false);
        paneByTime.setVisible(false);
        paneByFuelType.setVisible(true);
    }

    @FXML
    void handleBtnRefuelHour(ActionEvent event) {
        paneByFuelType.setVisible(false);
        paneByCustomerType.setVisible(false);
        paneByTime.setVisible(true);
    }

    @FXML
    void handleBtnRefuelHourImage(MouseEvent event) {
        paneByFuelType.setVisible(false);
        paneByCustomerType.setVisible(false);
        paneByTime.setVisible(true);
    }

    @FXML
    void handleFuelTypeCombo(ActionEvent event) {
        btnFuelTypeShow.setDisable(false);
        paneByFuelType.setVisible(true);

    }

    public void handleFuelTypeComboShow(ActionEvent actionEvent) {
        String str = fuelTypeCombo1.getValue();
        myController.getRatingForFuelTypeTable(str);
        RatingTableForFuelType.setVisible(true);
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
        selectedCustomerTypeTXT.setText(customerTypeCombo.getValue());
        showPieChartCustomerType (data);
        RatingTableForCustomerType.setItems(data);
    }



    public void setRatingForFuelTypeTable(ArrayList<Rating> cosArray){
        ratingCulomn11.setCellValueFactory(new PropertyValueFactory<>("rating"));
        customerIdCulomn11.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        ObservableList<Rating> data = FXCollections.observableArrayList(cosArray);
        selectedFuelTypeTXT.setText(fuelTypeCombo1.getValue());
        showPieChartFuelType (data);
        RatingTableForFuelType.setItems(data);
    }


    public void setRatingForTimeRangeTable(ArrayList<Rating> cosArray){
        ratingCulomn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        customerIdCulomn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        ObservableList<Rating> data = FXCollections.observableArrayList(cosArray);
        selectedhouresTXT.setText(TimeRangeStart.getValue() + " to " + TimeRangeEnd.getValue());
        showPieChartHouers (data);
        RatingTableForTimeRange.setItems(data);
    }

    public void handleTimePickerShow(ActionEvent actionEvent) {
         myController.getRatingForTimeRangeTable(Time.valueOf(TimeRangeStart.getValue()),Time.valueOf(TimeRangeEnd.getValue()));

    }



    @FXML
    void handleBtnCustomerType(ActionEvent event) {
        paneByFuelType.setVisible(false);
        paneByTime.setVisible(false);
        paneByCustomerType.setVisible(true);

    }

    @FXML
    void handleBtnCustomerTypeImage(MouseEvent event) {
        paneByFuelType.setVisible(false);
        paneByTime.setVisible(false);
        paneByCustomerType.setVisible(true);

    }
//pai chart methodes :
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

    private void showPieChartFuelType(ObservableList<Rating> data) {
        int[] monim = myController.monim;
        ObservableList<PieChart.Data> piedata1 = FXCollections.observableArrayList(
                new PieChart.Data("Rating 1", monim[1]), new PieChart.Data("Rating 2", monim[2]),
                new PieChart.Data("Rating 3", monim[3]),new PieChart.Data("Rating 4", monim[4]),
                new PieChart.Data("Rating 5", monim[5]),new PieChart.Data("Rating 6", monim[6]),
                new PieChart.Data("Rating 7", monim[7]),new PieChart.Data("Rating 8", monim[8]),
                new PieChart.Data("Rating 9", monim[9]),new PieChart.Data("Rating 10", monim[10]));
        pieChartFuelType.setData(piedata1);

    }

    private void showPieChartHouers(ObservableList<Rating> data) {
        int[] monim = myController.monim;
        ObservableList<PieChart.Data> piedata2 = FXCollections.observableArrayList(
                new PieChart.Data("Rating 1", monim[1]), new PieChart.Data("Rating 2", monim[2]),
                new PieChart.Data("Rating 3", monim[3]),new PieChart.Data("Rating 4", monim[4]),
                new PieChart.Data("Rating 5", monim[5]),new PieChart.Data("Rating 6", monim[6]),
                new PieChart.Data("Rating 7", monim[7]),new PieChart.Data("Rating 8", monim[8]),
                new PieChart.Data("Rating 9", monim[9]),new PieChart.Data("Rating 10", monim[10]));
        pieChartHouers.setData(piedata2);

    }

}