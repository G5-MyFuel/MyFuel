package client.logic;

import client.ClientApp;
import common.entity.User;
import common.tools.Message;
import common.tools.OperationType;

import java.util.ArrayList;

public class LoginToSystemLogic {
    private static LoginToSystemLogic Instance = null;
    public static ArrayList<User> usersArrayList;

    public static LoginToSystemLogic getInstance() {
        if (Instance == null)
            Instance = new LoginToSystemLogic();
        return Instance;
    }

    //בקשה לשליפת כל היוזרים במערכת - מקבלת את כל הנתונים בarraylist של יוזרים שיחכה בlogic
    public void importAllUsersToArrayListInLogicClass() {
        ClientApp.client.handleMessageFromClientUI(new Message(OperationType.getAllUsersTable, "SELECT * FROM Users as U;"));
    }

    public ArrayList<User> getUsersArrayList() {
        return usersArrayList;
    }

    public void setUsersArrayList(ArrayList<User> usersArrayList) {
        this.usersArrayList = usersArrayList;
    }

    public boolean searchUserIdAndUserTypeInArrayList(int userId, int userType) {
        if(userType==0){
            System.err.println("user type not detected");
        }
        for (User u : usersArrayList) {
            if (u.getUserID() == userId && u.getUserType() == userType)
                return true;
        }
        return false;
    }
}
