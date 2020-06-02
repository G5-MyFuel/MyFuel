package client.logic;

import client.ClientApp;
import common.entity.Costumer;
import common.entity.CreditCard;
import common.entity.Vehicle;
import common.tools.Message;
import common.tools.OperationType;
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


    public void setCostumerInDBFirstPhase(Costumer costumer) {
        int ID = costumer.getUserID();
        String Password = costumer.getUserPassword();
        String FirstName = costumer.getUserFirstName();
        String LastName = costumer.getUserLastName();
        String EmailAdress = costumer.getUserEmail();
        String nullTemp= "1";
        String Quarry = "INSERT INTO `Costumer` (`ID`, `Password`, `Type`, `First Name`, `Last Name`, `Email Adress`, `Credit Card Number`," + " `Purchase Plan`, `Vehicle ID`) " +
                "VALUES ( "+ ID +", "+Password+", "+nullTemp+", "+FirstName+", "+LastName+", "+EmailAdress+", "+nullTemp+", "+nullTemp+", "+nullTemp+");";
        ClientApp.client.handleMessageFromClientUI(new Message(OperationType.updateRequirement,Quarry));
    }
    public void setCostumerInDBSecoundPhase(Costumer costumer){

    }
    public void setCostumerInDBThirdPhase(Costumer costumer){

    }

}
