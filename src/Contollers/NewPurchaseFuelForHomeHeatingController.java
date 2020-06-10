package Contollers;

import boundary.NewPurchaseFuelForHomeHeatingBoundary;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.User;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class NewPurchaseFuelForHomeHeatingController extends BasicController{
    /* Variables*/
    private NewPurchaseFuelForHomeHeatingBoundary myBoundary;
    private static NewPurchaseFuelForHomeHeatingController Instance = null;



    /* Methods*/

    public NewPurchaseFuelForHomeHeatingController(NewPurchaseFuelForHomeHeatingBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /**
     * NewPurchaseFuelForHomeHeatingLogic Instance getter using SingleTone DesignPatterns
     *
     * @return Instance of logic class
     */
//    public static NewPurchaseFuelForHomeHeatingController getInstance() {
//        if (Instance == null)
//            Instance = new NewPurchaseFuelForHomeHeatingController();
//        return Instance;
//    }

    public boolean validatePage(Object guiObj) {
        if (guiObj instanceof JFXTextField) {
            JFXTextField jfxTextField = (JFXTextField) guiObj;
            if (jfxTextField.getText().isEmpty())
                return false;
            return true;
        }
        if (guiObj instanceof JFXTextArea) {
            JFXTextArea jfxTextArea = (JFXTextArea) guiObj;
            if (jfxTextArea.getText().isEmpty())
                return false;
            return true;
        }
        return false;
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(()->{
            switch(result.getActionType()){
                case GET_ALL_USERS_TABLE:
                    System.out.println("NewPurchaseFuelForHomeHeatingController -> myController.getUsersTable();");
                ArrayList<User> resultListUsers = new ArrayList<>();
//                resultListUsers.addAll(this.changeResultToUsers(result));
//                myBoundary.setAllDBUsersArrayList(resultListUsers);
                break;
                //

            }
        });
    }
}
