package Contollers;

import boundary.CustomerRegistrationBoundary;
import boundary.OrderExecutionBoundary;
import client.ClientApp;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.*;
import javafx.application.Platform;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Adi Lampert
 * @see OrderFromSupplierController - the form's logic class
 */

public class OrderFromSupplierController extends BasicController {
    public ArrayList<OrderFuelFromSupplier> resultList=new ArrayList<>();
    private OrderExecutionBoundary myBoundary;

    public OrderFromSupplierController(OrderExecutionBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    public void getOrdersFromDB() {
            SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_ORDERS_FROM_SUPPLIER_TABLE);
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

    private ArrayList<OrderFuelFromSupplier> changeResultToOrder(SqlResult result){
        /*ArrayList<OrderFuelFromSupplier> resultList=new ArrayList<>();*/
        for(ArrayList<Object> a: result.getResultData()) {
            OrderFuelFromSupplier x= new OrderFuelFromSupplier((String)a.get(0),(String)a.get(1),
                    (int)a.get(2),(Date)a.get(3),(int)a.get(4),(String)a.get(5),(String)a.get(6));
            resultList.add(x);
        }
        return resultList;
    }

}
