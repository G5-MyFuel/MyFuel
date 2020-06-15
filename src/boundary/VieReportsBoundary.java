package boundary;

import Contollers.VieReportsController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class VieReportsBoundary implements Initializable {

    /**
     * The supervisor boundary controller.
     */

    private final VieReportsController myController = new VieReportsController(this);
    //private FormValidation formValidation;
    //private final Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    //ArrayList<PurchasesReport> PurchasesArray = new ArrayList<>();

    @FXML
    private JFXButton btnViewQuarterlyRevenueReport;

    @FXML
    private JFXButton btnViewPurchasesReport;

    @FXML
    private JFXButton btnViewQuantityItemsInStockReport;

    @FXML
    private JFXComboBox<?> ChooseReportYearCombo;

    @FXML
    private JFXComboBox<?> ChooseReportQuarterCombo;

    @FXML
    private Label TotalRevenueLabel;

    @FXML
    private JFXTextField ViewTotalRevenueTXT;

    @FXML
    private Label QuantityItemsStockTxt;

    @FXML
    private TableView<?> ViewPurchasesReportTable;

    @FXML
    private TableColumn<?, ?> FuelTypeColumn;

    @FXML
    private TableColumn<?, ?> QuantityPurchasedColumn;

    @FXML
    private TableColumn<?, ?> SalesAmountColumn;

    @FXML
    private TableView<?> ViewQuantityReportTable;

    @FXML
    private TableColumn<?, ?> FuelTypeQuantityReportColumn;

    @FXML
    private TableColumn<?, ?> AvailableInventoryColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    @FXML
    void handleChooseReportQuarter(ActionEvent event) {

    }

    @FXML
    void handleChooseReportYear(ActionEvent event) {

    }

    @FXML
    void handleViewPurchasesReport(MouseEvent event) {

    }

    @FXML
    void handleViewQuantityItemsInStockReport(MouseEvent event) {

    }

    @FXML
    void handleViewQuarterlyRevenueReport(MouseEvent event) {

    }

    @FXML
    void handleViewTotalRevenue(ActionEvent event) {

    }
}
