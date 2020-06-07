package client.boundary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewAnalyticDataController {

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnGenerateAnalyticData;

    @FXML
    private TableView<?> AnalyticDataTable;

    @FXML
    private TableColumn<?, ?> CustomerTypeColumn;

    @FXML
    private TableColumn<?, ?> RefuelingHoursColumn;

    @FXML
    private TableColumn<?, ?> FuelTypeColumn;

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleGenerateReportBtn(ActionEvent event) {

    }

}