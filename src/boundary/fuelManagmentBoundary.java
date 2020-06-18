package boundary;

import Contollers.FormValidation;
import Contollers.fuelManagmentController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.assets.Toast;
import entity.GasStation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class fuelManagmentBoundary implements DataInitializable {


    private fuelManagmentController myController = new fuelManagmentController(this);
    private String managerID;
    private ArrayList<GasStation> stationsOfManager = new ArrayList<>();
    private Integer currectStationNumber;
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    private FormValidation myValidator = new FormValidation();
    private Stage primStage;


    @FXML
    private Pane dataPane;

    @FXML
    private JFXTextField newStockLimitArea;

    @FXML
    private Label gasoliseAmount;

    @FXML
    private Label diselAmount;

    @FXML
    private Label scooterAmount;

    @FXML
    private Button confirmBtn;

    @FXML
    private Text stockLimitNumber;

    @FXML
    private Label headLineLabel;

    @FXML
    private JFXComboBox<String> stationBox;


    @Override
    public void initData(Object data) {
        primStage = (Stage) confirmBtn.getScene().getWindow();
        primStage.setAlwaysOnTop(true);
        primStage.setTitle("Fuel management window");
        managerID = (String) data;
        myController.getAllManagerStations(managerID);
        dataPane.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handleComboBox();
        myValidator.isOnlyNumbers(newStockLimitArea, "Stock Limit");
        myValidator.isEmptyFieldValidation(newStockLimitArea, "Stock Limit");
    }

    @FXML
    void confirmFunction(MouseEvent event) {
        if (myValidator.isEmptyField() && myValidator.isOnlyNumbers()) {
            myController.updateFuelLimit(Double.parseDouble(newStockLimitArea.getText()), currectStationNumber);
        } else {
            ErrorAlert.setTitle("Field Error");
            ErrorAlert.setHeaderText("Stock limit field error.");
            ErrorAlert.showAndWait();
        }
    }

    public void setGasStations(ArrayList<GasStation> resultList) {
        stationsOfManager.addAll(resultList);
        //setting the stations into the combo box for the manager to chose.
        ArrayList<String> Stations = new ArrayList<>();
        for (GasStation station : resultList) {
            Stations.add(station.getGasStationName());
        }
        ObservableList<String> observableStations = FXCollections.observableArrayList(Stations);
        stationBox.setItems(observableStations);
    }

    private void handleComboBox() {
        stationBox.valueProperty().addListener((component, oldValue, newValue) -> {
            //
            dataPane.setVisible(true);
            GasStation tempStation = searchStation(stationBox.getValue());
            currectStationNumber = tempStation.getStationNumber();
            gasoliseAmount.setText(tempStation.getInventory_95());
            diselAmount.setText(tempStation.getInventoryDiesel());
            scooterAmount.setText(tempStation.getInventoryScooter());
            stockLimitNumber.setText(tempStation.getFuelLimit().toString());
            //

        });

    }

    public void stockLimitHasBeenUpdate() {
        stockLimitNumber.setText(newStockLimitArea.getText());
        Toast.makeText(primStage, "Fuel stock limit has been successfully updated.", 1000, 800, 1500, 10, 150);
    }

    private GasStation searchStation(String stationName) {
        for (GasStation s : stationsOfManager) {
            if (s.getGasStationName().equals(stationName)) {
                return s;
            }
        }
        return null;
    }

}
