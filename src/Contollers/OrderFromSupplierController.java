package Contollers;

import boundary.OrderExecutionBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.*;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Adi Lampert
 * @see OrderFromSupplierController - the form's logic class
 */

public class OrderFromSupplierController extends BasicController {
    public ArrayList<OrderFuelFromSupplier> resultList = new ArrayList<>();
    private OrderExecutionBoundary myBoundary;

    public OrderFromSupplierController(OrderExecutionBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    public void getOrdersFromDB() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_ORDERS_FROM_SUPPLIER_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }


    /**
     This function sets a new status after we done take care of the order
     */
    public void setNewStatus(String OrderNumber) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(OrderNumber);
        SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_STATUS_TO_DONE, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_ORDERS_FROM_SUPPLIER_TABLE:
                    ArrayList<OrderFuelFromSupplier> resultList = new ArrayList<>();
                    resultList.addAll(this.changeResultToOrder(result));
                    myBoundary.setOrderFuelFromSupplierTableView(resultList);
                    break;

                default:
                    break;
            }
        });
    }

    private ArrayList<OrderFuelFromSupplier> changeResultToOrder(SqlResult result) {
        for (ArrayList<Object> a : result.getResultData()) {
            OrderFuelFromSupplier x = new OrderFuelFromSupplier((String) a.get(0), (String) a.get(1),
                    (String) a.get(2), (String) a.get(3), (int) a.get(4), (Date) a.get(5), (String) a.get(6),(int)a.get(7),(String)a.get(8));
            resultList.add(x);
        }
        return resultList;
    }


}
