package boundary;

import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
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
        if (data instanceof CostumerManagmentTablePageBoundary)
            managmentBoundary = (CostumerManagmentTablePageBoundary) data;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void confirmFunction(MouseEvent event) {
        Stage primStage = (Stage) confirmBtn.getScene().getWindow();
        String station1 = null, station2 = null, station3 = null;
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
