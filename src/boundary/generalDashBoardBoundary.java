package boundary;

import common.assets.ProjectPages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class generalDashBoardBoundary implements DataInitializable {
    private generalDashBoardBoundary Instance = null;
    public generalDashBoardBoundary getInstance(){
        if(Instance==null){
            Instance = new generalDashBoardBoundary();
        }
        return Instance;
    }
    @FXML
    private VBox allDashButtons;

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
        ArrayList<Button> buttonArrayList = (ArrayList<Button>)data;
        for(Button b:buttonArrayList){
            allDashButtons.getChildren().add(b);
            System.out.println(b.getText() + "added");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    void addCostumerClick(MouseEvent event) {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.COSTUMER_REGISTRATION_PAGE.getPath()));
        myFuelLogo.setVisible(false);
    }


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


    private void clockFuntion(){
        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    Calendar cal = Calendar.getInstance();

                    second = cal.get(Calendar.SECOND);
                    minute = cal.get(Calendar.MINUTE);
                    hour = cal.get(Calendar.HOUR);
                    //System.out.println(hour + ":" + (minute) + ":" + second);
                    time.setText(hour + ":" + (minute) + ":" + second);

                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        //...
                    }
                }
            }
        };
        clock.start();
    }



}
