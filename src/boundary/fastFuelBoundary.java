package boundary;

import Contollers.FastFuelController;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import entity.Costumer;
import entity.GasStation;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private int SaleNumber;
private String CarFuelType;
    /**
     * gui variables
     */
    @FXML
    private Button getVehicleButoon;

    @FXML
    private Label pricaeCounter;

    @FXML
    private Label literCountertxt;

    @FXML
    private JFXTextField literAmountTxt;

    @FXML
    private JFXRadioButton gasolinRbtn;

    @FXML
    private JFXRadioButton diselRbtn;

    @FXML
    private JFXRadioButton scooterRbtn;

    @FXML
    private JFXRadioButton pump1;

    @FXML
    private JFXRadioButton pump2;

    @FXML
    private JFXRadioButton pump3;

    @FXML
    private Text subscriptionInfo;

    @FXML
    private Label vehicleInfoLabel;

    @FXML
    private Label customerIdLable;

    @FXML
    private Label stationNumberLable;
    @FXML
    private Button startFuelingBtn;

    @FXML
    private Label carNumberLable;

    @FXML
    private Pane paneFinishRefuel;

    @FXML
    private Label literCounter;

    @FXML
    private Text subscriptionInfo1;

    @FXML
    void getVehicleHandler(ActionEvent event) {

    }


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
        getVehicleButoon.setVisible(false);
        paneFinishRefuel.setVisible(false);
        myController.getCustomer();
    }

    /**
     * A method responsible for keeping the purchase information in DB
     * And updating of available inventory
     */
    public void updateInvatoryInDB() {
        //array of station number, fuel type ולשלוח
        //עדכון של ההזמנה ב2 טבלאות
      /*
      myController.insertPurcheseToDB();
      myController.insertPurcheseFastFuelToDB();

        //עדכון של המלאי ובדיקה אם הוא ירד מתחת ללימיט
        /*
        myController.updateInvetory();
*/
    }

    /**
     * A method that simulates the refueling process
     *
     * @param event
     */
    @FXML
    void startFuelingProccess(MouseEvent event) {
        //calculatePrice();

       /* Thread fuelingCounterThread = new Thread() {
            public void run() {
                for (; ; ) {
                    startFuelingBtn.setDisable(true);
                    //TODO: check literAmount input fields (validate) - need to chose pump .
                    Integer fuelCounter = Integer.parseInt(literAmountTxt.getText());
                    Integer literCounter = 0;
                    if (fuelCounter == 0) {
                        break;
                    }
                    literCounter++;
                    literCountertxt.setText(literCounter.toString());
                    fuelCounter--;

                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                startFuelingBtn.setDisable(false);
            }
        };
        fuelingCounterThread.start();
*/
    }

    /**
     * A method that gets a list of gas stations,
     * Selects one at random and places it as a current station
     *
     * @param resultList
     */
    public void setStationsInArrayAndChooseRandomly(ArrayList<GasStation> resultList) {
        User myUser = new User();
        // gs.StationNumber, companyName, inventory_95 , inventory_scooter, inventory_diesel

        Random r = new Random();
        int low = 1;
        int high = resultList.size();
        int result = r.nextInt(high - low) + low;
        stationNumberLable.setText(resultList.get(result).getStationNumber().toString());

    }

    /**
     *A method that gets a list of customers,
     * Selects one at random and sends a request for a query that will return the vehicles of the selected customer
     * @param costumerTable
     */
    public void setCostumerTable(ArrayList<Costumer> costumerTable) {
        Random r = new Random();
        int low = 0;
        int high = costumerTable.size();
        int result = r.nextInt(high - low) + low;

        customerIdLable.setText(costumerTable.get(result).getUserID());
        myController.getCarsForCustomer(costumerTable.get(result).getUserID());///???
        myController.getCarsForCustomer("305286965");
    }

    /**
     * A method that gets a list of vehicles,
     *   Randomly selects one and sends a query to return the selected customer's possible stations
     * @param VehicalList
     */
    public void setCarOfCustomer(ArrayList<Vehicle> VehicalList) {
        Random r = new Random();
        int low = 1;
        int high = VehicalList.size();
        try {
            int result = r.nextInt(high - low) + low;
            carNumberLable.setText(VehicalList.get(result).getVehicleID());
            CarFuelType =VehicalList.get(result).getGasType();
        }catch (IllegalArgumentException ex){
            customerIdLable.setText("305286965");
            carNumberLable.setText("6549875");
        }

        String customerID = customerIdLable.getText();
        //choose random station to refuel:
        myController.getOptionalStationForCustomer(customerID);

        /*while(true)
        {//ברגע שמגיעים לסף הדלק שביקשו - לעדכן את ההזמנה בDB
            if(literCounter.getText().equals(literAmountTxt.getText())) {
                this.updateInvatoryInDB();
            }
        }*/
    }


/*
    public ArrayList<Costumer> getCostumerTable() {
       // return costumerTable;
    }*/
}

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