package boundary;

import Contollers.GeneralDashBoardController;
import Contollers.ViewAnalyticDataController;
import common.assets.ProjectPages;
import entity.Costumer;
import entity.Prices;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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

/**
 *
 */
public class generalDashBoardBoundary implements DataInitializable {

    private GeneralDashBoardController myController = new GeneralDashBoardController(this);
    private ViewAnalyticDataController analiticData = new ViewAnalyticDataController();
    private ArrayList<Prices> resultListFuelPrices = new ArrayList<>();
    private ArrayList<Costumer> resultListCustomers = new ArrayList<>();
    private Image image;
    private ImageView imageView;


    //global variables:
    private String userID;
    private String fuelCompany;

    @FXML
    private Label userPermissionsTitel;

    @FXML
    public Label userFirstName;

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
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Text time;

    @FXML
    private Text currectDate;

    @FXML
    private Text notificationCounter;

    @FXML
    private Text currectDay;

    @FXML
    private Pane HomePagePane;

    @FXML
    public ImageView myFuelLogo;

    @FXML
    public Pane currentPagePane;
    @FXML
    private Button button4;

    /**
     * initData this will start in the initialize of the boundary.
     * sends parameters from anther pages
     *
     * @param data - The data sent to the boundary
     */
    @Override
    public void initData(Object data) {
        ArrayList<String> pageNameArrayList = (ArrayList<String>) data;
        myController.setCurrentUserID(userID);
        //get all updated prices
        myController.getAllUpdatedPricesFromDB();
        //
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
        /**
         * the following loop will set the buttons and pages according to the user premissions.
         */
        for (String pn : pageNameArrayList) {
            if (button1.getText().equals("b1")) currentBtn = button1;
            else if (button2.getText().equals("b2")) currentBtn = button2;
            else if (button3.getText().equals("b3")) currentBtn = button3;
            else if (button4.getText().equals("b4")) currentBtn = button4;
            //
            switch (pn) {
                case "COSTUMER_MANAGEMENT_TABLE_PAGE":
                    image = new Image(getClass().getResourceAsStream("../media/CostumerRegisterationMedia/managment.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(27);
                    imageView.setFitWidth(25);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("Customer\nManagement");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            costumerMenagmentClick();
                        }
                    });
                    break;

                case "COSTUMER_REGISTRATION_PAGE":
                    image = new Image(getClass().getResourceAsStream("../media/CostumerRegisterationMedia/addCostumer.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("Add Costumer");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            COSTUMER_REGISTRATION_PAGE();
                        }
                    });
                    break;

                case "FAST_FUEL_PAGE":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/fastfuel.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("Fast fueling");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            FAST_FUEL_PAGE();
                        }
                    });
                    break;

                case "SALE_OPERATION_TEMPLATE_PAGE":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/sale.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("Sale operation template");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            SALE_OPERATION_TEMPLATE_PAGE();
                        }
                    });
                    break;

                case "RUN_MARKETING_CAMPAIGN_PAGE":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/sale.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("Run SALE Operation");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            RUN_SALE_OPERATION_PAGE();
                        }
                    });
                    break;

                case "SETTING_DISCOUNT_RATES_PAGE":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/discountRatesBtn.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("Setting Discount Rate");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            SETTING_DISCOUNT_RATES_PAGE();
                        }
                    });
                    break;

                case "CONFIRM_DISCOUNT_RATES_PAGE":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/discountRatesBtn.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("Confirm discount rates");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            CONFIRM_DISCOUNT_RATES_PAGE();
                        }
                    });
                    break;

                case "GENERATING_REPORTS_STATION_MANAGER_PAGE":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/reports.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("Generating reports");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            GENERATING_REPORTS_STATION_MANAGER_PAGE();
                        }
                    });
                    break;

                case "GENERATING_REPORTS_MARKETING_MANAGER_PAGE":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/reports.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("Generating reports");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            GENERATING_REPORTS_MARKETING_MANAGER_PAGE();
                        }
                    });
                    break;

                case "MANAGER_SUPPLY_CONFIRMATION_PAGE":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/approveOrder.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("Approve Stock Order");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            MANAGER_SUPPLY_CONFIRMATION_PAGE();
                        }
                    });
                    break;

                case "VIEW_ANALITIC_DATA":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/analiticData.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("View Analytic Data");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            VIEW_ANALITIC_DATA();
                        }
                    });
                    break;

                case "PURCHASE_FUEL_FOR_HOME_HEATING":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/newFuelOrder.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("New Order fuel\nfor home heating");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            newPurchaseFuelForHomeHeatingClick();
                        }
                    });
                    break;

                case "SUPPLY_ORDER_EXECUTION":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/approveOrder.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    button2.setVisible(false);
                    button4.setVisible(false);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("Approve orders");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            SUPPLY_ORDER_EXECUTION();
                        }
                    });
                    break;

                case "MANAGER_NOTIFICATION_PAGE":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/alarm.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("Notifications");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            MANAGER_NOTIFICATION_PAGE();
                        }
                    });
                    break;

                case "FUEL_MANAGMENT_PAGE":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/fuelManagement.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("Fuel Management");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            FUEL_MANAGMENT_PAGE();
                        }
                    });
                    break;
                case "VIEW_REPORTS_PAGE":
                    image = new Image(getClass().getResourceAsStream("../media/ButtonsDashBoardMedia/reports.png"));
                    imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    currentBtn.setGraphic(imageView);
                    currentBtn.setText("View reports");
                    currentBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            VIEW_REPORTS_PAGE();
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
            button4.setVisible(false);
        } else if (button4.getText().equals("b4")) {
            button4.setVisible(false);
        }

    }

    /**
     * initialize pase.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clockFuntion();
        weekDay();
    }

    /**
     * set SUPPLY_ORDER_EXECUTION page
     */
    void SUPPLY_ORDER_EXECUTION() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.SUPPLY_ORDER_EXECUTION.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    /**
     * set GENERATING_REPORTS_MARKETING_MANAGER_PAGE page
     */
    void GENERATING_REPORTS_MARKETING_MANAGER_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.GENERATING_REPORTS_MARKETING_MANAGER_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    /**
     * set CONFIRM_DISCOUNT_RATES_PAGE page
     */
    void CONFIRM_DISCOUNT_RATES_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.CONFIRM_DISCOUNT_RATES_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    /**
     * set SETTING_DISCOUNT_RATES_PAGE page.
     */
    void SETTING_DISCOUNT_RATES_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.SETTING_DISCOUNT_RATES_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    /**
     * set RUN_SALE_OPERATION_PAGE page
     */
    void RUN_SALE_OPERATION_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.RUN_MARKETING_CAMPAIGN_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    /**
     * set COSTUMER_REGISTRATION_PAGE page
     */
    void COSTUMER_REGISTRATION_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.COSTUMER_REGISTRATION_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    /**
     * set SALE_OPERATION_TEMPLATE_PAGE page
     */
    void SALE_OPERATION_TEMPLATE_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.SALE_OPERATION_TEMPLATE_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    /**
     * set FAST_FUEL_PAGE page
     */
    void FAST_FUEL_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.FAST_FUEL_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    /**
     * set GENERATING_REPORTS_STATION_MANAGER_PAGE page
     */
    void GENERATING_REPORTS_STATION_MANAGER_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.GENERATING_REPORTS_STATION_MANAGER_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    /**
     * set MANAGER_SUPPLY_CONFIRMATION_PAGE page
     *
     */
    void MANAGER_SUPPLY_CONFIRMATION_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.MANAGER_SUPPLY_CONFIRMATION_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    /**
     * set MANAGER_NOTIFICATION_PAGE page
     */
    void MANAGER_NOTIFICATION_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.MANAGER_NOTIFICATION_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    /**
     * set VIEW_ANALYTIC_DATA page
     */
    void VIEW_ANALITIC_DATA() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        //   currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.VIEW_ANALITIC_DATA.getPath(), this));
        myFuelLogo.setVisible(false);
    }

    /**
     * set newPurchaseFuelForHomeHeatingClick page
     */
    void newPurchaseFuelForHomeHeatingClick() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(userID);
        varArray.add(this);
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.PURCHASE_FUEL_FOR_HOME_HEATING.getPath(), varArray));
        myFuelLogo.setVisible(false);
    }

    /**
     * set costumerMenagmentClick page
     */
    void costumerMenagmentClick() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.COSTUMER_MANAGEMENT_TABLE_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    /**
     * set FUEL_MANAGMENT_PAGE page
     */
    void FUEL_MANAGMENT_PAGE() {
        currentPagePane.setVisible(true);
        mainProjectFX.pagingController.loadAdditionalStage(ProjectPages.FUEL_MANAGMENT_PAGE.getPath(), userID);
        myFuelLogo.setVisible(false);
    }

    /**
     * set VIEW_REPORTS_PAGE page
     */
    void VIEW_REPORTS_PAGE() {
        currentPagePane.setVisible(true);
        currentPagePane.getChildren().clear();
        currentPagePane.getStylesheets().add("../boudary/BGcss.css");
        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.VIEW_REPORTS_PAGE.getPath(), userID));
        myFuelLogo.setVisible(false);
    }

    /**
     * set homePageClick button
     *
     * @param event
     */
    @FXML
    void homePageClick(MouseEvent event) {
        currentPagePane.setVisible(false);
        myFuelLogo.setVisible(true);
    }

    /**
     * set signOut button
     *
     * @param event
     */
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
                    if (time.getText().equals("00:00:00") && (LocalDate.now().getDayOfWeek().equals("SUNDAY"))) {
                        analiticData.startCalculate();
                    }

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


    /**
     * get all updated prices from database.
     *
     * @return
     */
    public ArrayList<Prices> getResultListFuelPrices() {
        return resultListFuelPrices;
    }

    public void setResultListFuelPrices(ArrayList<Prices> resultListFuelPrices) {
        this.resultListFuelPrices = resultListFuelPrices;
    }

    public ArrayList<Costumer> getResultListCustomers() {
        return resultListCustomers;
    }

    public void setResultListCustomers(ArrayList<Costumer> resultListCustomers) {
        this.resultListCustomers = resultListCustomers;
    }
}
