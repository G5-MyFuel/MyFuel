package Contollers;

import boundary.CostumerManagmentTablePageBoundary;
import boundary.CustomerRegistrationBoundary;
import client.ClientApp;
import entity.Costumer;
import entity.CreditCard;
import entity.Vehicle;
import server.MysqlConnection;

import java.util.ArrayList;

/**
 * @author itay
 * @see CustomerRegistrationController - the form's logic class
 */

public class CustomerRegistrationController {

    /**
     * The boundary controlled by this controller
     */
    private CustomerRegistrationBoundary myBoundary;


    MysqlConnection mySqlConnector;
    private boolean insertCostumerFlag;
    private Costumer tempCostumer;




    public CustomerRegistrationController(CustomerRegistrationBoundary myBoundary){
        this.myBoundary = myBoundary;
    }

    /*Logic Methods*/

    public void setCostumerInDB(Costumer costumer) {
        //Building Quarry :
        Integer ID = costumer.getID();
        String Password = costumer.getCustomerPassword();
        Password = "Aa" + Password;
        String Type = costumer.getCostumerType();

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
