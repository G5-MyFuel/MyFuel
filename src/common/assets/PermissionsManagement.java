package common.assets;


import Contollers.BasicController;
import entity.Employee;
import javafx.application.Platform;

import java.util.ArrayList;

public class PermissionsManagement extends BasicController {
    private ArrayList<Employee> employeeArrayList;
    private int userType;
    private String userID;

    public PermissionsManagement(String userID, int userType) {
        this.userID = userID;
        this.userType = userType;
        getEmployeeTable();
    }

    private UserType getUserTypeByNumber(int userTypeAsNumber) {
        switch (userTypeAsNumber) {
            case 1:
                return UserType.CUSTOMER;
            case 2:
                for (Employee e : employeeArrayList) {
                    if (e.getUserID() == userID) {
                         if(UserType.asUserType(e.getJobTitle())!=null){
                             return UserType.asUserType(e.getJobTitle());
                         }else System.err.println("employee jobTitle doesn't exist");
                    } else System.err.println("Employee doesn't exist");
                }
                break;
            case 3:
                return UserType.SUPPLIER;
        }
        return null;
    }

    public void getEmployeeTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_EMPLOYEE_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_EMPLOYEE_TABLE:
                    System.out.println("PermissionsManagement -> GET_EMPLOYEE_TABLE query");
                    this.employeeArrayList = new ArrayList<>();
                    employeeArrayList.addAll(this.changeResultToEmployees(result));
                    System.out.println(employeeArrayList);
                    break;
                default:
                    break;
            }
        });

    }

    private ArrayList<Employee> changeResultToEmployees(SqlResult result) {
        ArrayList<Employee> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            //id,jobTitle,fuelCompany,userFirstName,userLastName,userEmail
            Employee employee = new Employee((String) a.get(0), "", (String) a.get(3), (String) a.get(4), (String) a.get(5), (String) a.get(1), (String) a.get(2));
            resultList.add(employee);
        }
        return resultList;
    }

//    private Button getButton() {
//
//    }
}
