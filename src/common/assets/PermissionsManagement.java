package common.assets;


import entity.Employee;

import java.util.ArrayList;

public class PermissionsManagement {
    private ArrayList<Employee> employeeArrayList = new ArrayList<>();
    private int userType;
    private String userID;

    public PermissionsManagement(String userID, int userType) {
        this.userID = userID;
        this.userType = userType;
    }

    private UserType getUserTypeByNumber(int userTypeAsNumber) {
        switch (userTypeAsNumber) {
            case 1:
                return UserType.CUSTOMER;
            case 2:

                break;
            case 3:

                break;
        }
        return null;
    }

//    private UserType getEmployeeJobTitle(String userID) {
//
//    }

//    private Button getButton() {
//
//    }
}
