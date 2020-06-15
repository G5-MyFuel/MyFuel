package entity;

import common.assets.EmailHandler;

public class GasStation {

    private Integer gasStationID;
    private Integer companyID;
    private String gasStationName;
    private String managerID;
    private String inventory_95, inventoryScooter, inventoryDiesel;
    private Thread fuelAmountLimitCheck;
    private Integer limit;
    private EmailHandler emailSender;


    public GasStation(Integer gasStationID, Integer companyID, String gasStationName, String managerID, String inventory_95, String inventoryScooter, String inventoryDiesel) {
        this.gasStationID = gasStationID;
        this.companyID = companyID;
        this.gasStationName = gasStationName;
        this.managerID = managerID;
        this.inventory_95 = inventory_95;
        this.inventoryScooter = inventoryScooter;
        this.inventoryDiesel = inventoryDiesel;
        emailSender = new EmailHandler();
        fuelAmountLimitCheck.start();
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

                    if (Integer.parseInt(inventory_95) < limit) {
                        emailSender.sendMessage("stationManagerEmailAdress@gmail.com", "Fuel Amount - Gasoline_95", "Fuel amount has been decrees below the define limit.");
                    }
                    if (Integer.parseInt(inventoryScooter) < limit) {
                        emailSender.sendMessage("stationManagerEmailAdress@gmail.com", "Fuel Amount - Scooter", "Fuel amount has been decrees below the define limit.");

                    }
                    if (Integer.parseInt(inventoryDiesel) < limit) {
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
    public Integer getGasStationID() {
        return gasStationID;
    }

    public void setGasStationID(Integer gasStationID) {
        this.gasStationID = gasStationID;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
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
    //


}
