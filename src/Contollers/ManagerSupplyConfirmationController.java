package Contollers;

import boundary.ManagerSupplyConfirmationBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.ManagerSupplyConfirmation;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 * @author Adi Lampert
 * @see ManagerSupplyConfirmationController - the form's Controller class
 */
public class ManagerSupplyConfirmationController extends BasicController {

    private ManagerSupplyConfirmationBoundary myBoundary;
    public ArrayList<ManagerSupplyConfirmation> resultList = new ArrayList<>();

    public ManagerSupplyConfirmationController(ManagerSupplyConfirmationBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /**
     * To get all the orders related to this station manager
     * @param stationManagerID
     */
    public void getOrdersFromDB(String stationManagerID) {
        ArrayList<Object> al = new ArrayList<>();
        al.add(stationManagerID);
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_ORDER_TO_SUPPLY_FOR_STATION_MANAGER,al);
        System.out.println(al);
        super.sendSqlActionToClient(sqlAction);
    }

    /**
     * Update order status from "New" to "In treatment"
     * @param OrderNumber
     */
    public void setNewStatus(String OrderNumber){
        ArrayList<Object> varArray=new ArrayList<>();
        varArray.add(OrderNumber);
        SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_STATUS_TO_IN_TREATMENT, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_ORDER_TO_SUPPLY_FOR_STATION_MANAGER:
                    ArrayList<ManagerSupplyConfirmation> resultList = new ArrayList<>();
                    resultList.addAll(this.changeResultToOrder(result));
                    System.out.println(resultList);
                    myBoundary.setOrderForManagerTableView(resultList);
                    break;

                default:
                    break;
            }
        });
    }

    private ArrayList<ManagerSupplyConfirmation> changeResultToOrder(SqlResult result) {
        for (ArrayList<Object> a : result.getResultData()) {
            ManagerSupplyConfirmation x = new ManagerSupplyConfirmation((String) a.get(0), (String) a.get(1),
                    (Integer) a.get(2), (String) a.get(3), (Integer) a.get(4),(String)a.get(5),(String)a.get(6));
            resultList.add(x);
        }
        return resultList;
    }

}
