package Contollers;

import client.ClientConsole;
import boundary.CostumerManagmentTablePageBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.Costumer;
import entity.CreditCard;
import entity.Vehicle;
import javafx.application.Platform;

import java.util.ArrayList;

public class CostumerManagementController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private CostumerManagmentTablePageBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public CostumerManagementController(CostumerManagmentTablePageBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }


    public void getCostumerTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_COSTUMER_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }


    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_COSTUMER_TABLE:
                    ArrayList<Costumer> resultList = new ArrayList<>();
                    resultList.addAll(this.changeResultToCostumer(result));
                    myBoundary.setCostumerTable(resultList);
                    break;

                default:
                    break;
            }
        });
    }

    /**
     * This method create array list of costumers from the data base result.
     *
     * @param result the result
     * @return Array list of costumers
     */
    private ArrayList<Costumer> changeResultToCostumer(SqlResult result){
        ArrayList<Costumer> resultList=new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            Costumer cos = new Costumer((Integer) a.get(0), (String)a.get(1),(String)a.get(2),
                    (String)a.get(3),(String)a.get(4),(String)a.get(5),null,(boolean)a.get(9),null,(String)a.get(12));
            CreditCard card = new CreditCard(cos,(String)a.get(6),(String)a.get(7),(String)a.get(8));
            Vehicle vehicle = new Vehicle(cos.getID().toString(),(String)a.get(10),(String)a.get(11)); //here i need to find a way to get all vehicles and not just 1 of them.
            cos.setCostumerCreditCard(card);
            cos.addCostumerVehicle(vehicle);
            resultList.add(cos);
        }
        return resultList;
    }

}
