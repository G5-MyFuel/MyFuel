package boundary;

import common.assets.ProjectPages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class generalDashBoardBoundary implements DataInitializable {

    @FXML
    private Label userPermissionsTitel;

    @FXML
    private Label userFirstName;

    @FXML
    private Button btnHomePage;

    @FXML
    private Button addCostumerBtn;

    @FXML
    private Button btnMenagment;

    @FXML
    private Button btnSignout;

    @FXML
    private ImageView myFuelLogo;

    @FXML
    private Pane currentPagePane;


    @Override
    public void initData(Object data) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void addCostumerClick(MouseEvent event) {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.COSTUMER_REGISTRATION_PAGE.getPath()));
        myFuelLogo.setVisible(false);
    }

    @FXML
    void costumerMenagmentClick(MouseEvent event) {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.COSTUMER_MANAGEMENT_TABLE_PAGE.getPath()));
        myFuelLogo.setVisible(false);
    }

    @FXML
    void homePageClick(MouseEvent event) {
        currentPagePane.setVisible(false);
        myFuelLogo.setVisible(true);
    }

    @FXML
    void signOutClick(MouseEvent event) {
            mainProjectFX.pagingController.loadBoundary(ProjectPages.LOGIN_PAGE.getPath());
    }




}
