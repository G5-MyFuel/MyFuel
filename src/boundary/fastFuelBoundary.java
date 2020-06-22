package boundary;

import Contollers.FastFuelController;
import Contollers.FormValidation;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import common.assets.enums.FuelTypes;
import entity.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
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
    private ToggleGroup group = new ToggleGroup();
    private FormValidation formValidation;
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    private Costumer chosenCos;
    private Vehicle costumerVehicles;
    /**
     * gui variables
     */
    @FXML
    private Button getVehicleButoon;

    @FXML
    private Label pricaeCounter;


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
    private Text literCountertxt;

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
        pump1.setToggleGroup(group);
        pump2.setToggleGroup(group);
        pump3.setToggleGroup(group);
        formValidation = new FormValidation();
        getVehicleButoon.setVisible(false);
        paneFinishRefuel.setVisible(false);
        subscriptionInfo1.setVisible(false);
        myController.getCustomer();
        validateFields();
        pricaeCounter.setVisible(false);
        startFuelingBtn.setDisable(true);
    }
    private void validateFields(){
        formValidation.isOnlyNumbers(literAmountTxt, "Liter Amount");
        formValidation.isEmptyFieldValidation(literAmountTxt, "Liter Amount");
        formValidation.numberPositiveValidation(literAmountTxt,"Liter Amount");
        formValidation.maxFloatSizeValidation(literAmountTxt,"Liter Amount",200);
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

        if(!group.getSelectedToggle().isSelected()){
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("need to chose pump.");
            ErrorAlert.showAndWait();
        }
        else if(formValidation.isEmptyField() && formValidation.isNumberPositive() && formValidation.isOnlyNumbers() ){




            Prices p = new Prices(chosenCos.getUserID(),Double.valueOf(literAmountTxt.getText()),FuelTypes.Gasoline95,chosenCos.getPurchasePlanAsEnum(),chosenCos.getPricingModelTypeAsEnum());
            Double totalPrice = p.calculateTotalPrice();
        paneFinishRefuel.setVisible(true);
        Thread fuelingCounterThread = new Thread() {
            public void run() {
                Integer fuelCounter = Integer.parseInt(literAmountTxt.getText());
                Integer literCounter = 0;
                for (; ; ) {
                    startFuelingBtn.setDisable(true);
                    if (fuelCounter == 0) {
                        subscriptionInfo1.setVisible(true);
                        break;
                    }
                    literCounter++;
                    literCountertxt.setText(literCounter.toString());
                    fuelCounter--;
                    try {
                        sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(totalPrice.toString());
                pricaeCounter.setText(totalPrice.toString());
                pricaeCounter.setVisible(true);
                startFuelingBtn.setDisable(false);
            }
        };
        fuelingCounterThread.start();}else{
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("One or more of the fields is empty.");
            ErrorAlert.showAndWait();
        }

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
     * A method that gets a list of customers,
     * Selects one at random and sends a request for a query that will return the vehicles of the selected customer
     *
     * @param costumerTable
     */
    public void setCostumersArry(ArrayList<Costumer> costumerTable) {
        Random r = new Random();
        int low = 0;
        int high = costumerTable.size();
        int result = r.nextInt(high - low);
        chosenCos = costumerTable.get(result);

        customerIdLable.setText(chosenCos.getUserID());
        myController.getCarsForCustomer(chosenCos.getUserID());
    }
    /**
     * A method that gets a list of vehicles,
     * Randomly selects one and sends a query to return the selected customer's possible stations
     *
     * @param VehicalList
     */
    public void setCarOfCustomer(ArrayList<Vehicle> VehicalList) {
        Random r = new Random();
        int low = 1;
        int high = VehicalList.size();
        try {
            int result = r.nextInt(high - low) + low;
            costumerVehicles = VehicalList.get(result);
            carNumberLable.setText(VehicalList.get(result).getVehicleID());
            CarFuelType = VehicalList.get(result).getGasType();
        } catch (IllegalArgumentException ex) {
            customerIdLable.setText("305286965");
            carNumberLable.setText("6549875");
        }

        String customerID = customerIdLable.getText();
        //choose random station to refuel:
        myController.getOptionalStationForCustomer(customerID);
        startFuelingBtn.setDisable(false);

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