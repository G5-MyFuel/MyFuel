package boundary;

import Contollers.FormValidation;
import Contollers.ViewAnalyticDataController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewAnalyticDataBoundary implements Initializable {
    private static ViewAnalyticDataBoundary Instance = null;
    private ViewAnalyticDataController viewAnalyticDataLogic;
    private FormValidation formValidation;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.viewAnalyticDataLogic = ViewAnalyticDataController.getInstance();
        this.formValidation = FormValidation.getValidator();

        /*  set all fields validators */
        formValidation();
        /* set form items */
        //setShippingTab();
    }

    /**
     * ViewAnalyticDataController Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of controller class
     */
    public static ViewAnalyticDataBoundary getInstance() {
        if (Instance == null)
            Instance = new ViewAnalyticDataBoundary();
        return Instance;
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
    void handleGenerateReportBtn(ActionEvent event) {

    }

}