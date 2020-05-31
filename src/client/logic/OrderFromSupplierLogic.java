package client.logic;

import client.ClientApp;
import common.entity.OrderFuelFromSupplier;
import common.entity.User;
import common.tools.Message;
import common.tools.OperationType;

import java.util.ArrayList;

public class OrderFromSupplierLogic {
    private static OrderFromSupplierLogic Instance= null;
    public static ArrayList<OrderFuelFromSupplier> OrderFromSupplierArrayList;

    public static OrderFromSupplierLogic getInstance() {
        if (Instance == null)
            Instance = new OrderFromSupplierLogic();
        return Instance;
    }

    public void getOrderFuelFromSupplierTable(){
        ClientApp.client.handleMessageFromClientUI(new Message(OperationType.getAllUsersTable, "SELECT * FROM OrderForStock as O;"));
    }

    public static ArrayList<OrderFuelFromSupplier> getOrderFromSupplierArrayList() {
        return OrderFromSupplierArrayList;
    }

    public static void setUsersArrayList(ArrayList<User> usersArrayList) {
        LoginToSystemLogic.usersArrayList = usersArrayList;
    }


}
