package client.logic;

import common.entity.Costumer;
import common.entity.CreditCard;
import common.entity.Vehicle;

import java.util.ArrayList;

/**
 * @author itay
 * @see CustomerRegistrationLogic - the form's logic class
 */

public class CustomerRegistrationLogic {

    /*Logic Variables*/
    private static CustomerRegistrationLogic Instance = null;
    private Vehicle costumerVehicle;
    private CreditCard costumerCreditCard;

    /*Logic Methods*/


    /**
     * CustomerRegistrationFXML1Logic Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of logic class
     */
    public static CustomerRegistrationLogic getInstance() {
        if (Instance == null)
            Instance = new CustomerRegistrationLogic();
        return Instance;
    }


    public void createCostumer(Costumer costumer) {
        costumer.setCostumerCreditCard(costumerCreditCard);
        costumer.setCostumerVehicle(costumerVehicle);
        Costumer newCostumer = costumer;

    }

    public void createVehicle(String VehicleID, String GasType) {
        costumerVehicle = new Vehicle(VehicleID, GasType);
    }
    public void createCreditCard(String ID,String exDate,String CVV){
        costumerCreditCard = new CreditCard(null,ID,exDate,CVV);
    }

    public Vehicle getCostumerVehicle() {
        return costumerVehicle;
    }

    public void setCostumerVehicle(Vehicle costumerVehicle) {
        this.costumerVehicle = costumerVehicle;
    }
}
