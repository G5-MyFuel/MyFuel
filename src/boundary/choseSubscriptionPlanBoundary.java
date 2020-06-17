package boundary;

import com.jfoenix.controls.JFXComboBox;
import common.assets.SqlQueryType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class choseSubscriptionPlanBoundary implements DataInitializable {

    private CostumerManagmentTablePageBoundary managmentBoundary;
    private Stage primStage;


    @FXML
    private JFXComboBox<String> PricingModelChoiseBox1;

    @FXML
    private ImageView ServicePlanInfo;

    @FXML
    private Button ConfirmBtn;

    private ObservableList<String> SingelVehicle;


    @FXML
    void confirmBtnClicked(MouseEvent event) {
        managmentBoundary.getMyController().updateCostumerDetailsInDb(SqlQueryType.UPDATE_COSTUMER_PRICING_MODEL,managmentBoundary.getVehicleSearchCosIDtxt().getText(), PricingModelChoiseBox1.getValue());
        primStage.close();
    }

    @Override
    public void initData(Object data) {
        primStage = (Stage) ConfirmBtn.getScene().getWindow();
        String type = ((ArrayList<String>) data).get(0);
         managmentBoundary = ((ArrayList<CostumerManagmentTablePageBoundary>)data).get(1);

        if (type.equals("single"))
            SingelVehicle = FXCollections.observableArrayList("Casual fueling", "Regular monthly subscription (single)", "Full monthly subscription");
        else
            SingelVehicle = FXCollections.observableArrayList("Casual fueling", "Regular monthly subscription (multiple)");
        PricingModelChoiseBox1.setItems(SingelVehicle);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
