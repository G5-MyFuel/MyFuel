package client.logic;

import client.ClientApp;
import common.entity.Employee;
import common.entity.OrderFuelFromSupplier;
import common.entity.User;
import common.tools.Message;
import common.tools.OperationType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderFromSupplierLogic {
    private static OrderFromSupplierLogic Instance= null;
    private ArrayList<OrderFuelFromSupplier> orderSet;
    private ResultSet rs;



    public static OrderFromSupplierLogic getInstance() {
        if (Instance == null)
            Instance = new OrderFromSupplierLogic();
        return Instance;
    }

    public void getOrderFuelFromSupplierTable(){
        ClientApp.client.handleMessageFromClientUI(new Message(OperationType.getRequirementData, "SELECT * FROM `OrderForStock`"));
    }

    public ArrayList<OrderFuelFromSupplier> getOrderFromSupplierArrayList() {
        return orderSet;
    }

     public void setOrder(ResultSet rs) throws SQLException {
        while(rs.next()){
            this.orderSet.add(new OrderFuelFromSupplier(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5),rs.getInt(6),rs.getString(7)));
        }
         System.out.println(orderSet);
    }


    public static void setOrderFromSupplierArrayList(ArrayList<User> usersArrayList) {
        LoginToSystemLogic.usersArrayList = usersArrayList;
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
    }
}
