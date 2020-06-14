package common.assets;


import Contollers.BasicController;
import boundary.generalDashBoardBoundary;
import boundary.mainProjectFX;
import com.jfoenix.controls.JFXButton;
import entity.Employee;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PermissionsManagement extends BasicController {
    private PermissionsManagement Instance = null;
//    private ArrayList<Employee> employeeArrayList = new ArrayList<>();
    private int userTypeAsNumber;
    private UserType userType;
    private String userID;
    private generalDashBoardBoundary generalDashBoardBoundaryInstance;
    public PermissionsManagement() {

    }

    public PermissionsManagement(String userID, int userType) throws InterruptedException {
        this.userID = userID;
        this.userTypeAsNumber = userType;
//        this.userType = getUserTypeByNumber(userTypeAsNumber);

    }

    public PermissionsManagement getInstance() {
        if (Instance == null)
            Instance = new PermissionsManagement();
        return Instance;
    }

//    private UserType getUserTypeByNumber(int userTypeAsNumber) {
//        switch (userTypeAsNumber) {
//            case 1:
//                return UserType.CUSTOMER;
//            case 2:
//                for (Employee e : employeeArrayList) {
//                    if (e.getUserID() == userID) {
//                        if (UserType.asUserType(e.getJobTitle()) != null) {
//                            return UserType.asUserType(e.getJobTitle());
//                        } else System.err.println("employee jobTitle doesn't exist");
//                    } else System.err.println("Employee doesn't exist");
//                }
//                break;
//            case 3:
//                return UserType.SUPPLIER;
//        }
//        return null;
//    }

//    public void getEmployeeTable() {
//        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_EMPLOYEE_TABLE);
//        super.sendSqlActionToClient(sqlAction);
//    }

//    @Override
//    public void getResultFromClient(SqlResult result) {
//        Platform.runLater(() -> {
//            switch (result.getActionType()) {
//                case GET_EMPLOYEE_TABLE:
//                    System.out.println("PermissionsManagement -> GET_EMPLOYEE_TABLE query");
//                    employeeArrayList.addAll(this.changeResultToEmployees(result));
//                    System.out.println(employeeArrayList);
//                    break;
//                default:
//                    System.out.println("PermissionsManagement -> default");
//                    break;
//            }
//        });
//
//    }

//    private ArrayList<Employee> changeResultToEmployees(SqlResult result) {
//        ArrayList<Employee> resultList = new ArrayList<>();
//        for (ArrayList<Object> a : result.getResultData()) {
//            //id,jobTitle,fuelCompany,userFirstName,userLastName,userEmail
//            String fc;
//            switch((int)a.get(6)){
//                case 1:
//                    fc = "PAZ";
//                    break;
//                case 2:
//                    fc = "SONOL";
//                    break;
//                case 3:
//                    fc = "YELLOW";
//                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + (int) a.get(6));
//            }
//            Employee employee = new Employee((String) a.get(0), "", (String)a.get(2), (String) a.get(3),(String)a.get(4),(String)a.get(1),fc);
//            resultList.add(employee);
//        }
//        return resultList;
//    }

//    private Button getButton() {
//
//    }


//    public ArrayList<Employee> getEmployeeArrayList() {
//        return employeeArrayList;
//    }
//
//    public void setEmployeeArrayList(ArrayList<Employee> employeeArrayList) {
//        this.employeeArrayList = employeeArrayList;
//    }

    public int getUserTypeAsNumber() {
        return userTypeAsNumber;
    }

    public void setUserTypeAsNumber(int userTypeAsNumber) {
        this.userTypeAsNumber = userTypeAsNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<JFXButton> openDashBoard() {
        ArrayList<JFXButton> buttonArrayList = new ArrayList<>();
//        UserType userType = getUserTypeByNumber(userTypeAsNumber);
        switch (userType) {
            case CUSTOMER:
                //homepage,
                EventHandler event1 = new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        Pane currentPagePane = generalDashBoardBoundaryInstance.getInstance().currentPagePane;
                        currentPagePane.setVisible(true);
                        currentPagePane.getChildren().clear();
                        currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.PURCHASE_FUEL_FOR_HOME_HEATING.getPath()));
                        generalDashBoardBoundaryInstance.getInstance().myFuelLogo.setVisible(false);
                    }
                };
                JFXButton purchase_fuel_for_home_heating_button = getButton("New purchase fuel for home heating", event1, null);
                buttonArrayList.add(purchase_fuel_for_home_heating_button);
                break;
            case SUPPLIER:

                break;
            case DALKAN:

                break;
            case COMPANY_MANAGER:

                break;
            case STATION_MANAGER:

                break;
            case MARKETING_MANAGER:

                break;
            case MARKETING_REPRESENTATIVE:

                break;
            case MARKETING_DEPARTMENT_WORKER:

                break;

            default:
                System.err.println("user type err - > openDashBoard failed!");
        }
        return buttonArrayList;
    }

    private JFXButton getButton(String title, EventHandler eventHandler, Image buttonIcon) {
        JFXButton b = new JFXButton(title);
        b.setGraphic(new ImageView(buttonIcon));
        b.setOnAction(eventHandler);
        return b;
    }

    @Override
    public void getResultFromClient(SqlResult result) {

    }
}
