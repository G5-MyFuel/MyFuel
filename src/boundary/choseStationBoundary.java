package boundary;

import com.jfoenix.controls.JFXCheckBox;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class choseStationBoundary implements DataInitializable {

    CostumerManagmentTablePageBoundary managmentBoundary;

    @FXML
    private JFXCheckBox pazCheckBox;

    @FXML
    private JFXCheckBox yellowCheckBox;

    @FXML
    private JFXCheckBox sonolCheckBox;

    @FXML
    private Button confirmBtn;


    @Override
    public void initData(Object data) {
        Stage primStage = (Stage) confirmBtn.getScene().getWindow();

        if (data instanceof CostumerManagmentTablePageBoundary)
            managmentBoundary = (CostumerManagmentTablePageBoundary) data;

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
                else{
                    primStage.close();
                    managmentBoundary.getCosManageTbale().setDisable(false);
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void confirmFunction(MouseEvent event) {
        Stage primStage = (Stage) confirmBtn.getScene().getWindow();
        String station1 = "NULL", station2 = "NULL", station3 = "NULL";
        if (pazCheckBox.isSelected())
            station1 = "PAZ";
        if (yellowCheckBox.isSelected())
            station2 = "YELLOW";
        if (sonolCheckBox.isSelected())
            station3 = "SONOL";
        managmentBoundary.changeSelectedCostumerStations(station1, station2, station3);
        managmentBoundary.getCosManageTbale().setDisable(false);
        primStage.close();
    }

}
