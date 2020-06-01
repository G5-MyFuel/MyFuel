package client.logic;

import common.entity.Costumer;
import common.entity.CreditCard;
import common.entity.Vehicle;
import server.serverControllers.MySqlConnection;

import java.util.ArrayList;

/**
 * @author itay
 * @see CustomerRegistrationLogic - the form's logic class
 */

public class CustomerRegistrationLogic {

    MySqlConnection mySqlConnector;

    /*Logic Variables*/
    private static CustomerRegistrationLogic Instance = null;
    private Costumer costumer = new Costumer();

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

    //get Costumer details and send them to
    public void setCostumerInDB(Costumer costumer) {



    }




    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }
}
