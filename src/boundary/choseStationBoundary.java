package boundary;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * the class chose Station Boundary
 *
 * @author Itay Ziv
 * @see choseStationBoundary - the form's Boundary class
 */

public class choseStationBoundary implements DataInitializable {

    public CostumerManagmentTablePageBoundary managmentBoundary;
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);

    /**
     * ****** FXML PARAMETERS *******
     */
    @FXML
    private JFXCheckBox pazCheckBox;

    @FXML
    private JFXCheckBox yellowCheckBox;

    @FXML
    private JFXCheckBox sonolCheckBox;

    @FXML
    private Button confirmBtn;
    @FXML
    private JFXRadioButton PAZbtn;
    @FXML
    private JFXRadioButton YELLOWbtn;
    @FXML
    private JFXRadioButton SONOLbtn;
    @FXML
    private VBox exclusiveVbox;
    @FXML
    private VBox MultipleVbox;

    /**
     * initData this will start in the initialize of the boundary.
     * sends parameters from anther pages
     *
     * @param data - The data sent to the boundary
     */
    @Override
    public void initData(Object data) {
        Stage primStage = (Stage) confirmBtn.getScene().getWindow();
        primStage.setTitle("Chose your favourite stations");
        if (data instanceof CostumerManagmentTablePageBoundary)
            managmentBoundary = (CostumerManagmentTablePageBoundary) data;
        managmentBoundary.getCosManageTbale().setDisable(false);
        if (managmentBoundary.getCosManageTbale().getSelectionModel().getSelectedItem().getPurchasePlan().equals("Exclusive")) {
            exclusiveVbox.setVisible(true);
            MultipleVbox.setVisible(false);
        } else {
            exclusiveVbox.setVisible(false);
            MultipleVbox.setVisible(true);
        }
        managmentBoundary.getCosManageTbale().setDisable(true);


        primStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getButtonTypes().remove(ButtonType.OK);
                alert.getButtonTypes().add(ButtonType.CANCEL);
                alert.getButtonTypes().add(ButtonType.YES);
                alert.setTitle("Quit application");
                alert.setContentText(String.format("Are you sure you want to quit?\nBy clicking YES all new inserted data will be lost"));
                Optional<ButtonType> res = alert.showAndWait();
                if (res.get().equals(ButtonType.CANCEL))
                    e.consume();
                else {
                    primStage.close();
                    managmentBoundary.getCosManageTbale().setDisable(false);
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * confirm button function. this method is the listener of the confirm button.
     *
     * @param event
     */
    @FXML
    public void confirmFunction(MouseEvent event) {
        boolean unSelectedFlag = true;
        int counter = 0;
        Stage primStage = (Stage) confirmBtn.getScene().getWindow();
        String station1 = "NULL", station2 = "NULL", station3 = "NULL";
        if (MultipleVbox.isVisible()) {
            if (pazCheckBox.isSelected()) {
                station1 = "PAZ";
                counter++;
            }
            if (yellowCheckBox.isSelected()) {
                station2 = "YELLOW";
                counter++;
            }
            if (sonolCheckBox.isSelected()) {
                station3 = "SONOL";
                counter++;
            }
            if (counter < 2) {
                ErrorAlert.setTitle("Selection Error");
                ErrorAlert.setHeaderText("According to your plan you must select 2 or 3 stations.");
                ErrorAlert.showAndWait();
            } else {
                managmentBoundary.changeSelectedCostumerStations(station1, station2, station3);
                managmentBoundary.getCosManageTbale().setDisable(false);
                primStage.close();
            }
        } else {
            if (PAZbtn.isSelected()) {
                station1 = "PAZ";
                unSelectedFlag = false;
            }
            if (YELLOWbtn.isSelected()) {
                station2 = "YELLOW";
                unSelectedFlag = false;
            }
            if (SONOLbtn.isSelected()) {
                station3 = "SONOL";
                unSelectedFlag = false;
            }
            if (unSelectedFlag) {
                ErrorAlert.setTitle("Selection Error");
                ErrorAlert.setHeaderText("According to your plan you must select 1 station.");
                ErrorAlert.showAndWait();
            } else {
                managmentBoundary.changeSelectedCostumerStations(station1, station2, station3);
                managmentBoundary.getCosManageTbale().setDisable(false);
                primStage.close();
            }
        }
    }
}//end of choseStationBoundary.
