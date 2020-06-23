package boundary;

import Contollers.FastFuelController;
import Contollers.FormValidation;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import common.assets.enums.FuelTypes;
import entity.Costumer;
import entity.GasStation;
import entity.Prices;
import entity.Vehicle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * A department in charge of the fast refueling screen
 * Simulation of approaching vehicle approach, NFC sensor detects it, chooses a fuel pump and refuels
 *
 * @author Hana Wiener
 * @see FastFuelController -  the form's logic class
 */

public class fastFuelBoundary implements DataInitializable {
    /**
     * The supervisor boundary controller.
     */
    private FastFuelController myController = new FastFuelController(this);
    /**
     * temp  variables
     */
    private ToggleGroup group;
    private FormValidation LiterAmountValidato,vehicleNumberValidator;
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    private Costumer owner;
    private Vehicle correctVehicleFueling;
    private String correctCompanyName = "NULL";
    private Image image;
    private ArrayList<GasStation> allStation = new ArrayList<>();
    private GasStation correctStation;
    private ArrayList<Vehicle> allVehicleArray;
    /**
     * gui variables
     */

    @FXML
    private Text priceCounterLabel;

    @FXML
    private JFXTextField literAmountInput;

    @FXML
    private JFXTextField vehicleIDinput;

    @FXML
    private JFXRadioButton pump1;

    @FXML
    private JFXRadioButton pump2;

    @FXML
    private JFXRadioButton pump3;

    @FXML
    private Text costumerSubscriptionType;

    @FXML
    private Text ownerIDtxt;

    @FXML
    private Text stationNameTxt;

    @FXML
    private Button startFuelingBtn;

    @FXML
    private Text vehicleIDtxt;
    @FXML
    private Text fuelTypeTxt;

    @FXML
    private Pane paneFinishRefuel;

    @FXML
    private Text literCountertxt;
    @FXML
    private ImageView companyImage;
    @FXML
    private ImageView zikok;



    @Override
    public void initData(Object data) {

    }

    /**
     * This method initializes the variables, fields, and combo-boxes
     * What is initialized will appear when the screen is raised
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
        group = new ToggleGroup();
        pump1.setToggleGroup(group);
        pump2.setToggleGroup(group);
        pump3.setToggleGroup(group);
        //
        LiterAmountValidato = new FormValidation();
        vehicleNumberValidator = new FormValidation();
        validateFields();
        //
        paneFinishRefuel.setVisible(false);
        priceCounterLabel.setVisible(false);
        startFuelingBtn.setDisable(true);
        zikok.setVisible(false);
        //get necessary details from db.
        myController.getAllStations();
        myController.getVehicleTable();

        //setting a listener to the vehicle text area:
        vehicleIDinput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    String text = vehicleIDinput.getText();
                    boolean length = text.length() != 7 ? false:true;
                    boolean isOnlyNumber = text.matches("\\d*");
                    if (isOnlyNumber && length && !vehicleIDinput.getText().isEmpty()) {
                        myController.getVehicleDetails(text);
                        literAmountInput.requestFocus();
                    } else if(!isVehicleExistInDb(text)){
                        ErrorAlert.setTitle("Internal Error");
                        ErrorAlert.setHeaderText("Vehicle does not exists in data base.");
                        ErrorAlert.showAndWait();
                    } else{
                        ErrorAlert.setTitle("Internal Error");
                        ErrorAlert.setHeaderText("Wrong Vehicle ID");
                        ErrorAlert.showAndWait();
                    }
                }
            }
        });

    }

    /**
     * A method that simulates the refueling process
     *
     * @param event
     */
    @FXML
    void startFuelingProccess(MouseEvent event) {
        //TODO: after daniel write the function that calculate the price , need to show user the price and update details.
        if (group.getSelectedToggle() == null) {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("You need to chose a pump first.");
            ErrorAlert.showAndWait();
        } else if (LiterAmountValidato.isEmptyField() && LiterAmountValidato.isNumberPositive() && LiterAmountValidato.isOnlyNumbers()) {
            paneFinishRefuel.setVisible(true);
            Thread fuelingCounterThread = new Thread() {
                public void run() {
                    Integer fuelAmountToFueling = Integer.parseInt(literAmountInput.getText());
                    Integer literCounter = 0;
                    for (; ; ) {
                        startFuelingBtn.setDisable(true);
                        if (fuelAmountToFueling == 0) {
                            costumerSubscriptionType.setVisible(true);
                            break;
                        }
                        literCounter++;
                        literCountertxt.setText(literCounter.toString());
                        fuelAmountToFueling--;
                        try {
                            sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    zikok.setVisible(true);
                    priceCounterLabel.setText("Daniel need to calculate me :D");
                    priceCounterLabel.setVisible(true);
                    startFuelingBtn.setDisable(false);
                    try {
                        sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    zikok.setVisible(false);
                    paneFinishRefuel.setVisible(false);
                    startFuelingBtn.setDisable(true);
                }
            };
            fuelingCounterThread.start();
        } else {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("One or more of the fields is empty.");
            ErrorAlert.showAndWait();
        }

    }

    /**
     * the following method will calculate price of purchase by giving the owner,
     * amount of liters and fuelType.
     *
     * @param owner
     * @param amountOfLiters
     * @param fuelType
     * @return Double total price.
     */
    private Double calculatePrice(Costumer owner, Double amountOfLiters, FuelTypes fuelType) {
        Prices p = new Prices(owner.getUserID(), amountOfLiters, fuelType, owner.getPurchasePlanAsEnum(), owner.getPricingModelTypeAsEnum());
        return p.calculateTotalPrice();
    }

    public void setCorrectVehicleFueling(Vehicle v) {
        correctVehicleFueling = v;
    }


    public void setOwner(Costumer owner) {
        this.owner = owner;
        //setting owner details
        ownerIDtxt.setText(owner.getUserID());
        vehicleIDtxt.setText(correctVehicleFueling.getVehicleID());
        fuelTypeTxt.setText(correctVehicleFueling.getGasType());
        costumerSubscriptionType.setText(owner.getPricingModel());
        //get random company from owner companies.
        Random rand = new Random();
        while (correctCompanyName.equals("NULL")) {
            correctCompanyName = owner.getFuelCompany().get(rand.nextInt(3));
        }
        //set the image of the company.
        if (correctCompanyName.equals("PAZ")) {
            image = new Image(getClass().getResourceAsStream("../media/CostumerRegisterationMedia/pazLogoimg.png"));
            companyImage.setImage(image);
        }
        else if (correctCompanyName.equals("SONOL")) {
            image = new Image(getClass().getResourceAsStream("../media/CostumerRegisterationMedia/sonollogoimg.png"));
            companyImage.setImage(image);
        }
        else if (correctCompanyName.equals("YELLOW")) {
            image = new Image(getClass().getResourceAsStream("../media/CostumerRegisterationMedia/yellowLogo.png"));
            companyImage.setImage(image);
        }
        //chose the first station that fit the client company name.
        for (GasStation station : allStation) {
            if (station.getCompanyName().equals(correctCompanyName)) {
                correctStation = station;
            }
        }
        stationNameTxt.setText(correctStation.getGasStationName());
        startFuelingBtn.setDisable(false);

    }

    public void setAllStation(ArrayList<GasStation> stations) {
        allStation = stations;
    }

    private void validateFields() {
        LiterAmountValidato.isOnlyNumbers(literAmountInput, "Liter Amount");
        LiterAmountValidato.isEmptyFieldValidation(literAmountInput, "Liter Amount");
        LiterAmountValidato.numberPositiveValidation(literAmountInput, "Liter Amount");
        LiterAmountValidato.maxFloatSizeValidation(literAmountInput, "Liter Amount", 200);
        //
    }
    /**
     * this method will check if vehicle exist in db
     *
     * @param vehicleID
     * @return boolean
     */
    public boolean isVehicleExistInDb(String vehicleID) {
        for (Vehicle v : allVehicleArray) {
            if (v.getVehicleID().equals(vehicleID))
                return true;
        }
        return false;
    }

    public void setAllVehicleArray(ArrayList<Vehicle> allVehicleArray) {
        this.allVehicleArray = allVehicleArray;
    }
}//end of the class


/*
    /**
     * A method responsible for keeping the purchase information in DB
     * And updating of available inventory

    public void updateInvatoryInDB() {
        //array of station number, fuel type ולשלוח
        //עדכון של ההזמנה ב2 טבלאות

      myController.insertPurcheseToDB();
      myController.insertPurcheseFastFuelToDB();

        //עדכון של המלאי ובדיקה אם הוא ירד מתחת ללימיט

        myController.updateInvetory();
    }

 */
    /*
נתונים שצריך לשמור:
     String purchaseID;//דניאל
     //String customerID;//
     //LocalDateTime purchaseDate;// להשתמש בנוכחי
     //double fuelAmount;//מוכנס מהמשתמש
     double totalPrice;//דניאל
     Prices prices; //דניאל
     String saleId;//דניאל


     //String FuelType;//
     //String StationNumber;
     //String companyName;
     ?
     //String PAZ;
     //String SONOL;
     //String YELLOW;

*/
/*
    public ArrayList<Costumer> getCostumerTable() {
       // return costumerTable;
    }*/
 /*while(true)
        {//ברגע שמגיעים לסף הדלק שביקשו - לעדכן את ההזמנה בDB
            if(literCounter.getText().equals(literAmountTxt.getText())) {
                this.updateInvatoryInDB();
            }
        }*/