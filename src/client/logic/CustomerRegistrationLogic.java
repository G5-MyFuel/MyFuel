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
    private boolean insertCostumerFlag;
    private Costumer tempCostumer;


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

    public void setCostumerInDB(Costumer costumer) {
        //Building Quarry :
        Integer ID = costumer.getID();
        String Password = costumer.getCustomerPassword();
        Password = "Aa" + Password;
        String Type;
        if (costumer.getCostumerType() == 0)
            Type = "Private";
        else
            Type = "Company";

        String Fname = costumer.getFname();
        String Lname = costumer.getLname();
        String Email = costumer.getEmailAdress();
        String CreditCardnum;
        if(costumer.getCostumerCreditCard() != null)
            CreditCardnum = costumer.getCostumerCreditCard().getCardNumber();
        else
            CreditCardnum = "No Card exists";

        String purchasePlan;
        if (costumer.getPurchasePlan())
            purchasePlan = "true";
        else
            purchasePlan = "false";
        String mainVehicle = costumer.getCostumerVehicle().get(0).getVehicleID().toString();
        String ServicePlan = costumer.getServicePlan().toString();
        String quarry = "INSERT INTO `bpsdc8o22sikrlpvvxqm`.`Costumer`(`ID`, `Password`, `Type`, `First Name`, `Last Name`, `Email Adress`, `Credit Card Number`, `Purchase Plan`, `Vehicle ID`, `Service Plan`)";
        String Values = " VALUES (\""+ ID.toString() + "\",\"" + Password + "\",\"" + Type + "\",\"" + Fname + "\",\"" + Lname + "\",\"" + Email + "\",\"" + CreditCardnum + "\",\"" + purchasePlan + "\",\"" + mainVehicle + "\",\"" + ServicePlan +"\");";
        quarry += Values;
        ClientApp.chatClient.handleMessageFromClientUI(new Message(OperationType.updateRequirement, quarry));

    }

    public void addCostumerCreditCard(CreditCard card) {
        this.tempCostumer.setCostumerCreditCard(card);
    }

    public void setCostumerFirstPhase(Costumer costumer) {
        this.tempCostumer = costumer;
    }

    public void setCostumerSecoundPhase(ArrayList<Vehicle> vehicles) {
        tempCostumer.setCostumerVehicle(vehicles);
    }

    public void setCostumerThirdPhase(Costumer costumer) {

    }

    public Costumer getTempCostumer() {
        return tempCostumer;
    }

    public void setTempCostumer(Costumer tempCostumer) {
        this.tempCostumer = tempCostumer;
    }
}
