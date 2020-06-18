package entity;

import common.assets.EmailHandler;
/**
 * the class Gas Station
 *
 * @author Itay Ziv
 * @see GasStation - the form's entity class
 *
 */
public class GasStation {

    private Integer stationNumber;
    private String companyName;
    private String gasStationName;
    private String managerID;
    private String inventory_95, inventoryScooter, inventoryDiesel;
    private Thread fuelAmountLimitCheck;
    private Double fuelLimit;
    private EmailHandler emailSender;


    public GasStation(Integer stationNumber, String companyName, String gasStationName, String managerID, String inventory_95, String inventoryScooter, String inventoryDiesel,Double fuelLimit) {
        this.stationNumber = stationNumber;
        this.companyName = companyName;
        this.gasStationName = gasStationName;
        this.managerID = managerID;
        this.inventory_95 = inventory_95;
        this.inventoryScooter = inventoryScooter;
        this.inventoryDiesel = inventoryDiesel;
        this.fuelLimit = fuelLimit;
    }


    /**
     * This method run threads to check if station inventory is below limit.
     * in case its below limit the thread sends alert to station manager.
     *
     * @return void
     */
    //maybie we will check only after some1 purchase the amount of fuel and not in that way ************
    private void checkLimit() {
        Thread fuelAmountLimitCheck = new Thread() {
            public void run() {
                for (; ; ) {

                    if (Integer.parseInt(inventory_95) < fuelLimit) {
                        emailSender.sendMessage("stationManagerEmailAdress@gmail.com", "Fuel Amount - Gasoline_95", "Fuel amount has been decrees below the define limit.");
                    }
                    if (Integer.parseInt(inventoryScooter) < fuelLimit) {
                        emailSender.sendMessage("stationManagerEmailAdress@gmail.com", "Fuel Amount - Scooter", "Fuel amount has been decrees below the define limit.");

                    }
                    if (Integer.parseInt(inventoryDiesel) < fuelLimit) {
                        emailSender.sendMessage("stationManagerEmailAdress@gmail.com", "Fuel Amount - Diesel", "Fuel amount has been decrees below the define limit.");

                    }

                    try {
                        sleep(10000);
                    } catch (InterruptedException ex) {
                        //...
                    }
                }
            }
        };


    }


    //Getters and Setters
    //
    public Integer getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(Integer stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getGasStationName() {
        return gasStationName;
    }

    public void setGasStationName(String gasStationName) {
        this.gasStationName = gasStationName;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getInventory_95() {
        return inventory_95;
    }

    public void setInventory_95(String inventory_95) {
        this.inventory_95 = inventory_95;
    }

    public String getInventoryScooter() {
        return inventoryScooter;
    }

    public void setInventoryScooter(String inventoryScooter) {
        this.inventoryScooter = inventoryScooter;
    }

    public String getInventoryDiesel() {
        return inventoryDiesel;
    }

    public void setInventoryDiesel(String inventoryDiesel) {
        this.inventoryDiesel = inventoryDiesel;
    }

    public Double getFuelLimit() {
        return fuelLimit;
    }

    public void setFuelLimit(Double fuelLimit) {
        this.fuelLimit = fuelLimit;
    }

    //


}
