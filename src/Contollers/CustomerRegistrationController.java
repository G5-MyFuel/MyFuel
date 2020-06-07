package Contollers;

import boundary.CostumerManagmentTablePageBoundary;
import boundary.CustomerRegistrationBoundary;
import client.ClientApp;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.Costumer;
import entity.CreditCard;
import entity.Vehicle;
import server.MysqlConnection;

import java.util.ArrayList;

/**
 * @author itay
 * @see CustomerRegistrationController - the form's logic class
 */

public class CustomerRegistrationController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private CustomerRegistrationBoundary myBoundary;


    MysqlConnection mySqlConnector;
    private boolean insertCostumerFlag;
    private Costumer tempCostumer;


    public CustomerRegistrationController(CustomerRegistrationBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /*Logic Methods*/

    public void setCostumerInDB(Costumer costumer) {
        //set Costumer data into varArray
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(costumer.getID());
        varArray.add("Aa" + costumer.getCustomerPassword());
        varArray.add(costumer.getCostumerType());
        varArray.add(costumer.getFname());
        varArray.add(costumer.getLname());
        varArray.add(costumer.getEmailAdress());
        if (costumer.getCostumerCreditCard() != null) {
            varArray.add(costumer.getCostumerCreditCard().getCardNumber());
            varArray.add(costumer.getCostumerCreditCard().getExperationDate());
            varArray.add(costumer.getCostumerCreditCard().getCardSecurityNumber());
        } else {
            varArray.add("No Card Exists");
            varArray.add("No Card Exists");
            varArray.add("No Card Exists");
        }
        varArray.add(costumer.getPurchasePlan());
        varArray.add(costumer.getCostumerVehicle().get(0).getVehicleID());
        varArray.add(costumer.getCostumerVehicle().get(0).getGasType());
        varArray.add(costumer.getServicePlan());
        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_COSTUMER, varArray);
        super.sendSqlActionToClient(sqlAction);
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

    @Override
    public void getResultFromClient(SqlResult result) {

    }
}
