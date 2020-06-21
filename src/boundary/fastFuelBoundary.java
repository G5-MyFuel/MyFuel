package boundary;

import Contollers.FastFuelController;
import Contollers.NewPurchaseFuelForHomeHeatingController;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import entity.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

/**
 * @author Daniel Gabbay
 */

public class fastFuelBoundary implements DataInitializable {
    private FastFuelController myController = new FastFuelController(this);
    private int SaleNumber;
    @FXML
    private Button getVehicleButoon;

    @FXML
    private Label pricaeCounter;

    @FXML
    private Label literCounter;

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

    @Override
    public void initData(Object data) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //בחירת לקוח
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
        // gs.StationNumber, companyName,inventory_95 , inventory_scooter, inventory_diesel

        Random r = new Random();
        int low = 0;
        int high = 2;
        int result = r.nextInt(high-low) + low;
        stationNumberLable.setText(resultList.get(result).getStationNumber().toString());

    }


    public void setCostumerTable(ArrayList<Costumer> costumerTable) {
        customerIdLable.setText(costumerTable.get(0).getUserID());


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
    }

    private void savePurchaseInDB(){
            //purchase:
        myController.savePurchase(new Purchase());
            //ff:
        myController.saveFFPurchase(new FastFuel);

    }
*/
    /*
   private String purchaseID;
    private String customerID;
    private LocalDateTime purchaseDate;
    private double fuelAmount;
    private double totalPrice;
    private Prices prices;
        private String saleId;

     */

}
