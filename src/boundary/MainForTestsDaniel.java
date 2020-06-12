package boundary;

import client.ClientConsole;
import common.assets.PermissionsManagement;
import common.assets.ProjectPages;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainForTestsDaniel extends Application {
    private AnchorPane root;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //set server connection -
        String serverIp = "127.0.0.1";
        String [] args = {serverIp};
        ClientConsole.connection(args);
     //   PermissionsManagement pm = new PermissionsManagement("201718668",2);

        Pane root = null;
        try {
            FXMLLoader loader = new FXMLLoader();
        //    loader.setLocation(getClass().getResource(ProjectPages.PURCHASE_FUEL_FOR_HOME_HEATING.getPath()));
            loader.setLocation(getClass().getResource(ProjectPages.LOGIN_PAGE.getPath()));
            root = loader.load();
            Scene s1 = new Scene(root);
            primaryStage.setScene(s1);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERR at App.Start");
        }
    }

}
//public class MainForTestsDaniel extends Application {
//    //קובץ בדיקות עבור SaleOperationTemplateController
//    String pageName = "NewPurchaseFuelForHomeHeatingFXML.fxml";   //שם קובץ הfxml
//
//    private AnchorPane root;
//    private NewPurchaseFuelForHomeHeatingControllerBoundary newPurchaseFuelForHomeHeatingController;    //אינסטנס של מחלקת הboundary
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        try {
//            FXMLLoader pageLoader = new FXMLLoader();
//            pageLoader.setLocation(getClass().getResource(pageName + ""));
//            root = pageLoader.load();
//            newPurchaseFuelForHomeHeatingController = pageLoader.getController();//קבלת גישה לקונטרולר
//            //
//
//            //
//            Scene scene = new Scene(root, 1280, 800);
//          //  scene.getStylesheets().add("/client/LoginPageCSS.css");
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//            //xxx
//        }
//    }
//}
