package Contollers;

import boundary.LoginToSystemBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.User;
import javafx.application.Platform;

import java.util.ArrayList;

public class LoginToSystemController extends BasicController {
    public static ArrayList<User> usersArrayList;
    private LoginToSystemBoundary myBoundary;


    public LoginToSystemController(LoginToSystemBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    public ArrayList<User> getUsersArrayList() {
        return usersArrayList;
    }

    public void setUsersArrayList(ArrayList<User> usersArrayList) {
        this.usersArrayList = usersArrayList;
    }

    public boolean searchUserIdAndUserTypeInArrayList(String userId, int userType) {
        if (userType == 0) {
            System.err.println("user type not detected");
        }
        for (User u : usersArrayList) {
            if (u.getUserID() == userId && u.getUserType() == userType)
                return true;
        }
        return false;
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_USERS_TABLE:
                    System.out.println("LoginToSystemController -> myController.getUsersTable();");
                    ArrayList<User> resultListUsers = new ArrayList<>();
                    resultListUsers.addAll(this.changeResultToUsers(result));
                    myBoundary.setAllDBUsersArrayList(resultListUsers);
                    //System.out.println(myBoundary.getAllDBUsersArrayList()); //test
                    break;
                case UPDATE_USER_FIELD:
                    System.out.println("UPDATE_USER_FIELD");
                    break;

                //
                default:
                    break;
            }
        });
    }

    private ArrayList<User> changeResultToUsers(SqlResult result) {
        ArrayList<User> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            User user = new User((String) a.get(0), (int) a.get(1), (String) a.get(2), (String) a.get(4), (String) a.get(5), (String) a.get(6),(int)a.get(7));
            resultList.add(user);
        }
        return resultList;
    }

    public void getUsersTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_USERS_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    public void setNewUserFieldValue(String theFieldName,String theNewValueAsString,String userId,String userType){
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(theFieldName);
        varArray.add(theNewValueAsString);
        varArray.add(userId);
        varArray.add(userType);

        SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_USER_FIELD,varArray);
        super.sendSqlActionToClient(sqlAction);
    }
}
