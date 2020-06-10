package Contollers;

import boundary.NewPurchaseFuelForHomeHeatingBoundary;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
                ArrayList<User> resultListUsers = new ArrayList<>();
                resultListUsers.addAll(this.changeResultToUsers(result));
                myBoundary.setAllDBUsersArrayList(resultListUsers);
                break;
                //
                
            }
        });
    }

    private ArrayList<User> changeResultToUsers(SqlResult result){
        ArrayList<User> resultList = new ArrayList<>();
        for(ArrayList<Object> a:result.getResultData()){
            User user = new User((int)a.get(0),(int)a.get(1),(String)a.get(4),(String)a.get(5),(String)a.get(6));
            resultList.add(user);
        }
        return resultList;
    }
}
