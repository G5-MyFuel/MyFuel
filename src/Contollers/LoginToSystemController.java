package Contollers;

import boundary.LoginToSystemBoundary;
import boundary.mainProjectFX;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import common.assets.Toast;
import entity.User;
import javafx.application.Platform;

import java.util.ArrayList;

public class LoginToSystemController extends BasicController {
    public static ArrayList<User> usersArrayList;
    private LoginToSystemBoundary myBoundary;
    private String userID;


    public LoginToSystemController(LoginToSystemBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    public ArrayList<User> getUsersArrayList() {
        return usersArrayList;
    }

    public void setUsersArrayList(ArrayList<User> usersArrayList) {
        this.usersArrayList = usersArrayList;
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
                case GET_EMPLOYEE_TABLE:
                    System.out.println("PermissionsManagement -> GET_EMPLOYEE_TABLE query");
//                    employeeArrayList.addAll(this.changeResultToEmployees(result));
//                    System.out.println(employeeArrayList);
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
            ArrayList<String> fuelCompany = new ArrayList<>();
            fuelCompany.add((String) a.get(7));
            fuelCompany.add((String) a.get(8));
            fuelCompany.add((String) a.get(9));
            User user = new User((String) a.get(0), (String) a.get(1), (String) a.get(2), (int) a.get(3), (String) a.get(4), (String) a.get(5), (String) a.get(6), fuelCompany);
            resultList.add(user);
        }
        return resultList;
    }

    public void getUsersTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_USERS_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    public void setNewUserFieldValue(String theFieldName, String theNewValueAsString, String userId, String userType) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(theFieldName);
        varArray.add(theNewValueAsString);
        varArray.add(userId);
        varArray.add(userType);

        SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_USER_FIELD, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullUserNameByUserId(String userID) {
        for (User u : myBoundary.getAllDBUsersArrayList()) {
            if (u.getUserID().equals(userID)) {
                return u.getUserFirstName() + " " + u.getUserLastName();
            }
        }
        return "failed full user name";
    }

    public String getFuelCompanyBuUserID(String userID){
        for (User u : myBoundary.getAllDBUsersArrayList()) {
            if (u.getUserID().equals(userID)) {
                return u.getFuelCompany().get(0);
            }
        }
        return "failed full user name";
    }

    public ArrayList<String> getUserButtons(LoginToSystemBoundary loginToSystemBoundary, String userType) {
        ArrayList<String> buttonNameArrayList = new ArrayList<>();
        String temp;
        switch (userType) {
            case "CUSTOMER":
                buttonNameArrayList.add("Customer");
                temp = myBoundary.getUserIDTextField().getText();
                buttonNameArrayList.add(temp);
                buttonNameArrayList.add(getFullUserNameByUserId(temp));
                buttonNameArrayList.add(getFuelCompanyBuUserID(temp));
                buttonNameArrayList.add("PURCHASE_FUEL_FOR_HOME_HEATING");
//                buttonNameArrayList.add("COSTUMER_MANAGEMENT_TABLE_PAGE");
//                buttonNameArrayList.add("GENERATING_REPORTS_STATION_MANAGER_PAGE");
                Toast.makeText(mainProjectFX.mainStage,"Wellcom to MyFuel "+getFullUserNameByUserId(temp),1000,1500,1500,40,380);
                break;
            case "SUPPLIER":
                buttonNameArrayList.add("Supplier");
                temp = myBoundary.getUserIDTextField().getText();
                buttonNameArrayList.add(temp);
                buttonNameArrayList.add(getFullUserNameByUserId(temp));
                buttonNameArrayList.add(getFuelCompanyBuUserID(temp));
                //buttonNameArrayList.add("");
                Toast.makeText(mainProjectFX.mainStage,"Wellcom to MyFuel "+getFullUserNameByUserId(temp),1000,1500,1500,40,380);
                break;
            case "DALKAN":
                buttonNameArrayList.add("Dalkan");
                temp = myBoundary.getUserIDTextField().getText();
                buttonNameArrayList.add(temp);
                buttonNameArrayList.add(getFullUserNameByUserId(temp));
                buttonNameArrayList.add(getFuelCompanyBuUserID(temp));
                buttonNameArrayList.add("FAST_FUEL_PAGE");
                Toast.makeText(mainProjectFX.mainStage,"Wellcom to MyFuel "+getFullUserNameByUserId(temp),1000,1500,1500,40,380);
                break;
            case "COMPANY_MANAGER":

                break;
            case "STATION_MANAGER":
                buttonNameArrayList.add("Station manager");
                temp = myBoundary.getUserIDTextField().getText();
                buttonNameArrayList.add(temp);
                buttonNameArrayList.add(getFullUserNameByUserId(temp));
                buttonNameArrayList.add(getFuelCompanyBuUserID(temp));
                //
                buttonNameArrayList.add("GENERATING_REPORTS_STATION_MANAGER_PAGE");
                buttonNameArrayList.add("PURCHASE_FUEL_FOR_HOME_HEATING");

                //
                Toast.makeText(mainProjectFX.mainStage,"Wellcom to MyFuel "+getFullUserNameByUserId(temp),1000,1500,1500,40,380);
                break;
            case "MARKETING_MANAGER":
                buttonNameArrayList.add("Marketing manager");
                temp = myBoundary.getUserIDTextField().getText();
                buttonNameArrayList.add(temp);
                buttonNameArrayList.add(getFullUserNameByUserId(temp));
                buttonNameArrayList.add(getFuelCompanyBuUserID(temp));
                buttonNameArrayList.add("SALE_OPERATION_TEMPLATE_PAGE");
                Toast.makeText(mainProjectFX.mainStage,"Wellcom to MyFuel "+getFullUserNameByUserId(temp),1000,1500,1500,40,380);
                break;
            case "MARKETING_REPRESENTATIVE":
                buttonNameArrayList.add("Marketing representative");
                temp = myBoundary.getUserIDTextField().getText();
                buttonNameArrayList.add(temp);
                buttonNameArrayList.add(getFullUserNameByUserId(temp));
                buttonNameArrayList.add(getFuelCompanyBuUserID(temp));
                buttonNameArrayList.add("COSTUMER_MANAGEMENT_TABLE_PAGE");
                buttonNameArrayList.add("COSTUMER_REGISTRATION_PAGE");
                Toast.makeText(mainProjectFX.mainStage,"Wellcom to MyFuel "+getFullUserNameByUserId(temp),1000,1500,1500,40,380);
                break;
            case "MARKETING_DEPARTMENT_WORKER":
                buttonNameArrayList.add("Marketing department worker");
                temp = myBoundary.getUserIDTextField().getText();
                buttonNameArrayList.add(temp);
                buttonNameArrayList.add(getFullUserNameByUserId(temp));
                buttonNameArrayList.add(getFuelCompanyBuUserID(temp));
                buttonNameArrayList.add("SALE_OPERATION_TEMPLATE_PAGE");
//                buttonNameArrayList.add("COSTUMER_MANAGEMENT_TABLE_PAGE");
//                buttonNameArrayList.add("COSTUMER_REGISTRATION_PAGE");
                Toast.makeText(mainProjectFX.mainStage,"Wellcom to MyFuel "+getFullUserNameByUserId(temp),1000,1500,1500,40,380);
                break;

            default:
                System.err.println("user type err - > openDashBoard failed!");
        }
        return buttonNameArrayList;
    }
}
