package boundary;

import Contollers.FastFuelController;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import entity.Costumer;
import entity.GasStation;
import entity.User;
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

import static java.lang.Thread.sleep;

/**
 *
 * @author Hana Wiener
 */

public class fastFuelBoundary implements DataInitializable {
    private FastFuelController myController = new FastFuelController(this);
    private int SaleNumber;

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


    @Override
    public void initData(Object data) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        myController.getCustomer();
    }


    public void updateInvatoryInDB() {
        //array of station number, fuel type ולשלוח
        //עדכון של ההזמנה ב2 טבלאות
        //עדכון של המלאי ובדיקה אם הוא ירד מתחת ללימיט
        /*
        myController.updateInvetory();
*/
    }


    public void setStationsInArrayAndChooseRandomly(ArrayList<GasStation> resultList) {
        User myUser = new User();
        // gs.StationNumber, companyName, inventory_95 , inventory_scooter, inventory_diesel

       Random r = new Random();
      int low = 0;
       int high = 30;
        int result = r.nextInt(high-low) + low;
       stationNumberLable.setText(resultList.get(result).getStationNumber().toString());

    }

    @FXML
    void startFuelingProccess(MouseEvent event){
        Thread fuelingCounterThread = new Thread() {
            public void run() {
                for (; ; ) {
                    startFuelingBtn.setDisable(true);
                    //TODO: check literAmount input fields (validate) - need to chose pump .
                    Integer fuelCounter = Integer.parseInt(literAmountTxt.getText());
                    Integer literCounter = 0;
                    if(fuelCounter == 0){
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

    }


    public void setCostumerTable(ArrayList<Costumer> costumerTable) {
        customerIdLable.setText(costumerTable.get(0).getUserID());
        carNumberLable.setText("עוד לא מומש");

        String customerID =  customerIdLable.getText();
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