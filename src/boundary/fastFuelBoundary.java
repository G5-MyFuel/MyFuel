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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private Prices price;
    private Costumer owner;
    private Image image;
    private Double totalPrice;
    private ToggleGroup group;
    private GasStation correctStation;
    private Vehicle correctVehicleFueling;
    private String correctCompanyName = "NULL";
    private FormValidation LiterAmountValidato;
    private ArrayList<Vehicle> allVehicleArray;
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    private ArrayList<GasStation> allStation = new ArrayList<>();

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
    private ImageView loadingImage;
    @FXML
    private VBox detailsBox;




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
        loadingImage.setVisible(false);
        detailsBox.setVisible(false);
        group = new ToggleGroup();
        pump1.setToggleGroup(group);
        pump2.setToggleGroup(group);
        pump3.setToggleGroup(group);
        //
        LiterAmountValidato = new FormValidation();
        validateFields();
        //
        paneFinishRefuel.setVisible(false);
        priceCounterLabel.setVisible(false);
        startFuelingBtn.setDisable(true);
        //get necessary details from db.
        myController.getAllStations();
        myController.getVehicleTable();

        //setting a listener to the vehicle text area:
        vehicleIDinput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    loadingImage.setVisible(true);
                    detailsBox.setVisible(false);
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
        if (group.getSelectedToggle() == null) {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("You need to chose a pump first.");
            ErrorAlert.showAndWait();
        } else if (LiterAmountValidato.isEmptyField() && LiterAmountValidato.isNumberPositive() && LiterAmountValidato.isOnlyNumbers()) {
            priceCounterLabel.setText("Your price being calculate.");
            Double newInventory =  correctFuelStationInventory() - Double.parseDouble(literAmountInput.getText());
            ArrayList<Object> varArrayForPurchase = new ArrayList<>();
            Random rand = new Random();
            Integer n = rand.nextInt(9000)+1000;
            if(newInventory < correctStation.getFuelLimit()){
                ArrayList<Object> varArray = new ArrayList<>();
                varArray.add(n.toString());
                varArray.add(correctStation.getStationNumber());
                varArray.add(1000);
                varArray.add(fuelTypeTxt.getText());
                varArray.add(correctCompanyName);
                myController.insertNewOrderForStock(varArray);
            }
            myController.updateFuelInventory(newInventory.toString(),correctStation.getStationNumber(),fuelTypeTxt.getText());
            price = new Prices(owner,Double.parseDouble(literAmountInput.getText()), FuelTypes.fromString(correctVehicleFueling.getGasType()));

            paneFinishRefuel.setVisible(true);
            Thread fuelingCounterThread = new Thread() {
                public void run() {
                    Integer fuelAmountToFueling = Integer.parseInt(literAmountInput.getText());
                    Integer literCounter = 0;
                    for (; ; ) {
                        startFuelingBtn.setDisable(true);
                        if (fuelAmountToFueling == 0) {
                            costumerSubscriptionType.setVisible(true);
                            totalPrice = price.calculateTotalPrice();
                            totalPrice = Double.parseDouble(new DecimalFormat("#####.##").format(totalPrice));
                            priceCounterLabel.setText(totalPrice.toString());
                            varArrayForPurchase.add(n.toString());
                            varArrayForPurchase.add(owner.getUserID());
                            varArrayForPurchase.add(Double.parseDouble(literAmountInput.getText()));
                            varArrayForPurchase.add(totalPrice);

                            Format f = new SimpleDateFormat("HH:mm:ss");
                            String strResult = f.format(new Date());
                            varArrayForPurchase.add(strResult);

                            varArrayForPurchase.add(price.getCampaignID());
                            myController.updatePurchase(varArrayForPurchase);
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
                    //priceCounterLabel.setText(totalPrice.toString());
                    priceCounterLabel.setVisible(true);
                    startFuelingBtn.setDisable(false);
                    try {
                        sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    paneFinishRefuel.setVisible(false);
                    startFuelingBtn.setDisable(true);
                    //TODO: here i need to update DB about the results.
                }
            };
            fuelingCounterThread.start();
        } else {
            ErrorAlert.setTitle("Internal Error");
            ErrorAlert.setHeaderText("One or more of the fields is empty.");
            ErrorAlert.showAndWait();
        }

    }

    private void updatePurchase(ArrayList<Object> varArrayForPurchase){
    }

    private Double correctFuelStationInventory(){

        switch (fuelTypeTxt.getText()){
            case "Scooter Fuel":
                return Double.parseDouble(correctStation.getInventoryScooter());
            case "Diesel":
                return Double.parseDouble(correctStation.getInventoryDiesel());
            case "Gasoline-95":
                return Double.parseDouble(correctStation.getInventory_95());
            default:
                break;
        }
        return 0.0;
    }


    public void setCorrectVehicleFueling(Vehicle v) {
        correctVehicleFueling = v;
    }


    public void setOwnerAndDetails(Costumer owner) {
        this.owner = owner;
        //setting owner details
        ownerIDtxt.setText(owner.getUserID());
        vehicleIDtxt.setText(correctVehicleFueling.getVehicleID());
        fuelTypeTxt.setText(correctVehicleFueling.getGasType());
        costumerSubscriptionType.setText(owner.getPricingModel());
        //get random company from owner companies.
        correctCompanyName = "NULL";
        Random rand = new Random();
        while (correctCompanyName.equals("NULL")) {
            correctCompanyName = owner.getFuelCompany().get(rand.nextInt(3));
        }
        //set the image of the company.
        if (correctCompanyName.equals("PAZ")) {
            image = new Image(getClass().getResource("/media/CostumerRegisterationMedia/pazLogoimg.png").toString());
            companyImage.setImage(image);
        }
        else if (correctCompanyName.equals("SONOL")) {
            image = new Image(getClass().getResource("/media/CostumerRegisterationMedia/sonollogoimg.png").toString());
            companyImage.setImage(image);
        }
        else if (correctCompanyName.equals("YELLOW")) {
            image = new Image(getClass().getResource("/media/CostumerRegisterationMedia/yellowLogo.png").toString());
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
        loadingImage.setVisible(false);
        detailsBox.setVisible(true);

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