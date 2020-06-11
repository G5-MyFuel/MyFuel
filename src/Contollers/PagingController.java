package Contollers;


import java.io.IOException;
import java.util.ArrayList;

import boundary.mainProjectFX;
import boundary.DataInitializable;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * The Class PagingController.
 *
 * @author Itay Ziv
 */
@SuppressWarnings("serial")
public class PagingController extends BasicController {

    /**
     * Load boundary.
     *
     * @param path the path
     */
    @SuppressWarnings("static-access")
    public void loadBoundary(String path) {
        FXMLLoader loader = new FXMLLoader();
        Pane root;
        try {
            root = loader.load(getClass().getResource(path));
            Scene scene = new Scene(root);
            mainProjectFX.mainStage.setScene(scene);
            mainProjectFX.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load boundary.
     *
     * @param path the path
     * @param obj  the obj
     */
    public void loadBoundary(String path, Object obj) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Pane root;
        try {
            root = loader.load();
            DataInitializable boundary = loader.getController();
            boundary.initData(obj);
            Scene scene = new Scene(root);
            mainProjectFX.mainStage.setScene(scene);
            mainProjectFX.mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load additional stage.
     *
     * @param path the path
     * @return the stage
     */
    public Stage loadAdditionalStage(String path) {
        Stage stage = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root;
            root = (Parent) loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stage;
    }

    /**
     * Load additional stage.
     *
     * @param path the path
     * @param data the data
     * @return the stage
     */
    public Stage loadAdditionalStage(String path, Object data) {
        Stage stage = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root;
            root = (Parent) loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root));
            DataInitializable boundary = loader.getController();
            stage.show();
            boundary.initData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stage;
    }

    /**
     * Load boundary in pane.
     *
     * @param path the path
     * @return the pane
     */
    public Pane loadBoundaryInPane(String path) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Pane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    /**
     * User logout.
     */
    public void userLogout() {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(false);
        varArray.add(mainProjectFX.currentUser.getUserID());
        SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_USER_LOGIN_STATUS, varArray);

        this.sendSqlActionToClient(sqlAction);
    }

    /* (non-Javadoc)
     * @see controllers.BasicController#getResultFromClient(assets.SqlResult)
     */
    @Override
    public void getResultFromClient(SqlResult result) {
//        Platform.runLater(() -> {
//            switch(result.getActionType())
//            {
//                case UPDATE_USER_LOGIN_STATUS:
//                    int affectedRows = (int)result.getResultData().get(0).get(0);
//                    this.unsubscribeFromClientDeliveries();
//
//                    if (affectedRows == 0)
//                    {
//                        System.out.println("Error with user logout");
//                    }
//                    else
//                    {
//                        ProjectFX.currentUser = null;
//                    }
//                    break;
//                default:
//                    break;
//            }
//        });
        return;

    }


}
