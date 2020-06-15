package boundary;

import Contollers.FormValidation;
import Contollers.MarketingCampaignTemplateController;
import Contollers.ViewAnalyticDataController;
import com.jfoenix.controls.JFXButton;
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

public class ViewAnalyticDataBoundary implements Initializable {
    /** The supervisor boundary controller. */
    private ViewAnalyticDataController myController = new ViewAnalyticDataController(this);
    private FormValidation formValidation;

    @FXML
    private Label lastCalcDateTXT;

    @FXML
    private Button btnGenerateAnalyticData;

    @FXML
    private JFXButton btnCustomerType;

    @FXML
    private TableView<Rating> RatingTable;

    @FXML
    private TableColumn<Rating, Integer> customerIdCulomn;

    @FXML
    private TableColumn<Rating, Integer> ratingCulomn;

    @FXML
    private TableColumn<Rating, String> customerTypeCulomn;

    @FXML
    private ImageView imageRefuelHour;

    @FXML
    private ImageView imageFuelType;

    @FXML
    private JFXButton btnRefuelHour;

    @FXML
    private JFXButton btnFuleType;

    @FXML
    private Pane paneChart;

    @FXML
    private ImageView imageCustomerType;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.paneChart.setVisible(false);
        //this.RatingTable.setVisible(false);

        myController.getRatingTable();  //start the process that will ask server to execute quarry and get the table details

    }



    private void formValidation() {
        /**
         *
         *
         */

    }

    @FXML
    void handleClicks(ActionEvent event) {

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

    @FXML
    void handleGenerateData(ActionEvent event) {

    }
    /**
     this method will set the templates table when we will initialize the page.
     */
    public void setRatingTable(ArrayList<Rating> resultList) {


    }

    public void setTemplateTable(ArrayList<Rating> cosArray){
        //col oms parameters
        customerIdCulomn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        ratingCulomn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        customerTypeCulomn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        ObservableList<Rating> data = FXCollections.observableArrayList(cosArray);
        RatingTable.setItems(data);
    }
}