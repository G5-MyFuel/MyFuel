package boundary;

import Contollers.GeneralDashBoardController;
import common.assets.ProjectPages;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class generalDashBoardBoundary implements DataInitializable {

    private GeneralDashBoardController myController = new GeneralDashBoardController(this);

    //global variables:
    private String userID;
    private String fuelCompany;

    @FXML
    private Label userPermissionsTitel;

    @FXML
    private Label userFirstName;

    @FXML
    private GridPane allDashButtonsGridPane;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnHomePage;

    @FXML
    private ImageView homePageIconImage;

    @FXML
    private Button button1;

    @FXML
    private ImageView button1img;

    @FXML
    private Button button2;

    @FXML
    private ImageView button2img;

    @FXML
    private Button button3;

    @FXML
    private ImageView button3img;

    @FXML
    private Text time;

    @FXML
    private Text currectDate;

    @FXML
    private Text currectDay;

    @FXML
    private Pane HomePagePane;

    @FXML
    public ImageView myFuelLogo;

    @FXML
    public Pane currentPagePane;


    @Override
    public void initData(Object data) {

        ArrayList<String> pageNameArrayList = (ArrayList<String>) data;
        String userPermission = pageNameArrayList.get(0);
        this.userID = pageNameArrayList.get(1);
        String userFullName = pageNameArrayList.get(2);
        this.fuelCompany = pageNameArrayList.get(3);
        userPermissionsTitel.setText(userPermission);
        userFirstName.setText(userFullName);
        Button currentBtn = null;
        pageNameArrayList.remove(0);
        pageNameArrayList.remove(0);
        pageNameArrayList.remove(0);
        pageNameArrayList.remove(0);
        for (String pn : pageNameArrayList) {
            if (button1.getText().equals("b1")) currentBtn = button1;
            else if (button2.getText().equals("b2")) currentBtn = button2;
            else if (button3.getText().equals("b3")) currentBtn = button3;
            //
            switch (pn) {
                case "CONFIRM_DISCOUNT_RATE_PAGE":
                    currentBtn.setText("Confirm discount rate");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            CONFIRM_DISCOUNT_RATE_PAGE();
                        }
                    });
                    break;

                case "COSTUMER_MANAGEMENT_TABLE_PAGE":
                    currentBtn.setText("Customer management");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            costumerMenagmentClick();
                        }
                    });
                    break;

                case "COSTUMER_REGISTRATION_PAGE":
                    currentBtn.setText("New customer registration");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            addCostumerClick();
                        }
                    });
                    break;

                case "FAST_FUEL_PAGE":
                    currentBtn.setText("Fast fueling");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            FAST_FUEL_PAGE();
                        }
                    });
                    break;

                case "SALE_OPERATION_TEMPLATE_PAGE":
                    currentBtn.setText("Sale operation template");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            SALE_OPERATION_TEMPLATE_PAGE();
                        }
                    });
                    break;

                case "RUN_SALE_OPERATION_PAGE":
                    currentBtn.setText("Run sale operation page");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            RUN_SALE_OPERATION_PAGE();
                        }
                    });
                    break;

                case "SETTING_DISCOUNT_RATES_PAGE":
                    currentBtn.setText("Setting discount rate page");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            SETTING_DISCOUNT_RATES_PAGE();
                        }
                    });
                    break;

                case "CONFIRM_DISCOUNT_RATES_PAGE":
                    currentBtn.setText("Confirm discount rates");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            CONFIRM_DISCOUNT_RATES_PAGE();
                        }
                    });
                    break;

                case "GENERATING_REPORTS_STATION_MANAGER_PAGE":
                    currentBtn.setText("Generating reports");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            GENERATING_REPORTS_STATION_MANAGER_PAGE();
                        }
                    });
                    break;


                case "GENERATING_REPORTS_MARKETING_MANAGER_PAGE":
                    currentBtn.setText("Generating reports");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            GENERATING_REPORTS_MARKETING_MANAGER_PAGE();
                        }
                    });
                    break;

                case "PURCHASE_FUEL_FOR_HOME_HEATING":
                    currentBtn.setText("New Order fuel\nfor home heating");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            newPurchaseFuelForHomeHeatingClick();
                        }
                    });
                    break;

                default:
                    System.err.println("Page incorrect!");
                    break;
            }
        }
        if (button1.getText().equals("b1")) {
            button1 = btnSignout;
            button2.setVisible(false);
            button3.setVisible(false);
        } else if (button2.getText().equals("b2")) {
            button2 = btnSignout;
            button3.setVisible(false);
        } else if (button3.getText().equals("b3")) {
            button3.setText(btnSignout.getText());
            button3.setOnMouseClicked(btnSignout.getOnMouseClicked());
            button3.setGraphic(btnSignout.getGraphic());
            btnSignout.setVisible(false);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clockFuntion();
        weekDay();
    }

    void GENERATING_REPORTS_MARKETING_MANAGER_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.GENERATING_REPORTS_MARKETING_MANAGER_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    void CONFIRM_DISCOUNT_RATES_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.CONFIRM_DISCOUNT_RATES_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    void SETTING_DISCOUNT_RATES_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.SETTING_DISCOUNT_RATES_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    void RUN_SALE_OPERATION_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.RUN_SALE_OPERATION_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    void COSTUMER_REGISTRATION_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.COSTUMER_REGISTRATION_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    void CONFIRM_DISCOUNT_RATE_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.CONFIRM_DISCOUNT_RATE_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    void SALE_OPERATION_TEMPLATE_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.SALE_OPERATION_TEMPLATE_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    void FAST_FUEL_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.FAST_FUEL_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    void GENERATING_REPORTS_STATION_MANAGER_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.GENERATING_REPORTS_STATION_MANAGER_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    void newPurchaseFuelForHomeHeatingClick() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.PURCHASE_FUEL_FOR_HOME_HEATING.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    void addCostumerClick() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        String marketingRepCompanyName = null;
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.COSTUMER_REGISTRATION_PAGE.getPath(), marketingRepCompanyName));
        myFuelLogo.setVisible(false);
    }


    void costumerMenagmentClick() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.COSTUMER_MANAGEMENT_TABLE_PAGE.getPath(), userID));
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
     *
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
                    if (minute < 10)
                        time.setText(hour + ":" + "0" + (minute) + ":" + second);
                    else
                        time.setText(hour + ":" + (minute) + ":" + second);
                    //Calculate Analytical Data every sunday at 00:00:00:
                    if (time.getText().equals("00:00:00") && (LocalDate.now().getDayOfWeek().equals("SUNDAY")))
                        CalculateAnalyticalData();

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
     *
     * @return void
     */
    private void weekDay() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        LocalDate date = LocalDate.now();

        int dayOfMonth = date.getDayOfMonth();
        //Note: +1 the month for current month
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        //set the day into string
        switch (date.getDayOfWeek()) {
            case SUNDAY:
                currectDay.setText("Sunday");
                break;
            case MONDAY:
                currectDay.setText("Monday");
                break;
            case TUESDAY:
                currectDay.setText("Tuesday");
                break;
            case WEDNESDAY:
                currectDay.setText("Wednesday");
                break;
            case THURSDAY:
                currectDay.setText("Thursday");
                break;
            case FRIDAY:
                currectDay.setText("Friday");
                break;
            case SATURDAY:
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    //ANALITIC SYSTEM CALC :
    private void CalculateAnalyticalData() {


    }

}
