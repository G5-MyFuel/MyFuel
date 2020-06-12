package boundary;

import common.assets.ProjectPages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class generalDashBoardBoundary implements DataInitializable {
    private generalDashBoardBoundary Instance = null;

    public generalDashBoardBoundary getInstance() {
        if (Instance == null) {
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
    public ImageView myFuelLogo;

    @FXML
    public Pane currentPagePane;

    @FXML
    private Text time;

    @FXML
    private Text currectDay;

    @FXML
    private Text currectDate;


    @Override
    public void initData(Object data) {
        ArrayList<Button> buttonArrayList = (ArrayList<Button>) data;
        for (Button b : buttonArrayList) {
            allDashButtons.getChildren().add(b);
            System.out.println(b.getText() + "added");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clockFuntion();
        weekDay();
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

    /**
     * This method run a thread that count and update the correct time
     * @return void
     */
    private void clockFuntion() {
        Thread clock = new Thread() {
            int second, minute, hour;

            public void run() {
                for (; ; ) {
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    Calendar cal = Calendar.getInstance();

                    second = cal.get(Calendar.SECOND);
                    minute = cal.get(Calendar.MINUTE);
                    hour = cal.get(Calendar.HOUR_OF_DAY);
                    System.out.println(hour + ":" + (minute) + ":" + second);
                    if (minute < 10)
                        time.setText(hour + ":" + "0" + (minute) + ":" + second);
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

    /**
     * This method get and day and the correct date and update it.
     * @return void
     */
    private void weekDay() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        LocalDate date = LocalDate.now();

        int dayOfMonth = calendar.get(Calendar.DATE);
        //Note: +1 the month for current month
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        //set the day into string
        switch (date.getDayOfWeek().getValue()) {
            case 0:
                currectDay.setText("Sunday");
                break;
            case 1:
                currectDay.setText("Monday");
                break;
            case 2:
                currectDay.setText("Tuesday");
                break;
            case 3:
                currectDay.setText("Wednesday");
                break;
            case 4:
                currectDay.setText("Thursday");
                break;
            case 5:
                currectDay.setText("Friday");
                break;
            case 6:
                currectDay.setText("Saturday");
                break;
            default:
                break;
        }
        if (month < 10)
            currectDate.setText(String.valueOf(dayOfMonth) + "/0" + String.valueOf(month) + "/" + String.valueOf(year));
        else
            currectDate.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
    }


}
