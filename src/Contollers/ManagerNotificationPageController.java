package Contollers;

import boundary.ManagerNotificationsPageBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.ManagerNotifications;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 * @author Adi Lampert
 * @see ManagerNotificationPageController - the form's Controller class
 */
@SuppressWarnings("serial")
public class ManagerNotificationPageController extends BasicController {
    public ArrayList<ManagerNotifications> resultList = new ArrayList<>();
    private ManagerNotificationsPageBoundary myBoundary;

    public ManagerNotificationPageController(ManagerNotificationsPageBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }


    /**
     * To get all the orders with status "Done" to show the manager that the order arrived
     * @param managerID
     */
    public void getOrdersFromDB(String managerID) {
        ArrayList<Object> varArray= new ArrayList<>();
        varArray.add(managerID);
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_ORDER_WITH_STATUS_DONE,varArray);
        super.sendSqlActionToClient(sqlAction);
    }


    /**
     * This function sets a new status after the manager saw his notification
     * @param OrderNumber
     */
    public void setNewStatus(String OrderNumber) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(OrderNumber);
        SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_STATUS_TO_VIEWED, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_ORDER_WITH_STATUS_DONE:
                    ArrayList<ManagerNotifications> resultList = new ArrayList<>();
                    resultList.addAll(this.changeResultToOrder(result));
                    myBoundary.setOrdersInTableView(resultList);
                    break;

                default:
                    break;
            }
        });
    }

    private ArrayList<ManagerNotifications> changeResultToOrder(SqlResult result) {
        for (ArrayList<Object> a : result.getResultData()) {
            ManagerNotifications x = new ManagerNotifications((String)a.get(0),(Integer)a.get(1),(String)a.get(2),(Integer) a.get(3));
            resultList.add(x);
        }
        return resultList;
    }

}
