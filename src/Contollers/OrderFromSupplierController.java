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

    private OrderExecutionBoundary myBoundary;


    /*private static OrderFromSupplierController Instance= null;
    private ArrayList<OrderFuelFromSupplier> orderSet;
    private ResultSet rs;*/

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
                    try {
                        myBoundary.setOrderFuelFromSupplierTableView(resultList);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;

                default:
                    break;
            }
        });
    }

    private ArrayList<OrderFuelFromSupplier> changeResultToOrder(SqlResult result){
        ArrayList<OrderFuelFromSupplier> resultList=new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            OrderFuelFromSupplier OFFS = new OrderFuelFromSupplier((String)a.get(1),(String)a.get(2),
                    (int)a.get(3),(Date)a.get(4),(int)a.get(5),(String)a.get(6),(String)a.get(7));

        }
        return resultList;
    }
    /*public ArrayList<OrderFuelFromSupplier> getOrderFromSupplierArrayList() {
        return orderSet;
    }

     public void setOrder(ResultSet rs) throws SQLException {
        while(rs.next()){
            this.orderSet.add(new OrderFuelFromSupplier(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5),rs.getInt(6),rs.getString(7)));
        }
         System.out.println(orderSet);
    }


    public static void setOrderFromSupplierArrayList(ArrayList<User> usersArrayList) {
        LoginToSystemController.usersArrayList = usersArrayList;
    }
    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public ArrayList<OrderFuelFromSupplier> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(ArrayList<OrderFuelFromSupplier> orderSet) {
        this.orderSet = orderSet;
    }*/
}
