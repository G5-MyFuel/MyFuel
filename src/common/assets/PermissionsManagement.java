package common.assets;


import Contollers.BasicController;
import boundary.generalDashBoardBoundary;
import boundary.mainProjectFX;
import com.jfoenix.controls.JFXButton;
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
    private String userID;
    private String userType;
    //private UserType userType;
    private generalDashBoardBoundary generalDashBoardBoundaryInstance;

    public PermissionsManagement() {

    }

    public PermissionsManagement(String userID, String userType) throws InterruptedException {
        this.userID = userID;
//        this.userType = getUserTypeByNumber(userTypeAsNumber);

    }

    public PermissionsManagement getInstance() {
        if (Instance == null)
            Instance = new PermissionsManagement();
        return Instance;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<Button> openDashBoard() {
        ArrayList<Button> buttonArrayList = new ArrayList<>();
        switch (userType) {
            case "CUSTOMER":
                //homepage,
                EventHandler event1 = new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        generalDashBoardBoundaryInstance.currentPagePane.setVisible(true);
                        generalDashBoardBoundaryInstance.currentPagePane.getChildren().clear();
                        generalDashBoardBoundaryInstance.currentPagePane.getChildren().setAll(mainProjectFX.pagingController.loadBoundaryInPane(ProjectPages.COSTUMER_MANAGEMENT_TABLE_PAGE.getPath()));
                        generalDashBoardBoundaryInstance.myFuelLogo.setVisible(false);
                    }
                };
                Button purchase_fuel_for_home_heating_button = getButton("New purchase fuel for home heating", event1, null);
                buttonArrayList.add(purchase_fuel_for_home_heating_button);
                break;
            case "SUPPLIER":

                break;
            case "DALKAN":

                break;
            case "COMPANY_MANAGER":

                break;
            case "STATION_MANAGER":

                break;
            case "MARKETING_MANAGER":

                break;
            case "MARKETING_REPRESENTATIVE":

                break;
            case "MARKETING_DEPARTMENT_WORKER":

                break;

            default:
                System.err.println("user type err - > openDashBoard failed!");
        }
        return buttonArrayList;
    }

    private Button getButton(String title, EventHandler eventHandler, Image buttonIcon) {
        Button b = new JFXButton(title);
        b.setGraphic(new ImageView(buttonIcon));
        b.setOnAction(eventHandler);
        return b;
    }

    @Override
    public void getResultFromClient(SqlResult result) {

    }
}
