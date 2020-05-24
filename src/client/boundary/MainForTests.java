package client.boundary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainForTests extends Application {
    //להגדיר בהתאם לדף:
    //השורות שיש לשנות אם מחליפים דף הן עם הערה סופית
    String pageName = "NewPurchaseFuelForHomeHeatingFXML.fxml";   //שם קובץ הfxml

    private AnchorPane root;
    private NewPurchaseFuelForHomeHeatingController newPurchaseFuelForHomeHeatingController;    //אינסטנס של מחלקת הboundary

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource(pageName + ""));
            root = pageLoader.load();
            newPurchaseFuelForHomeHeatingController = pageLoader.getController();//קבלת גישה לקונטרולר
            //

            //
            Scene scene = new Scene(root, 1280, 800);
          //  scene.getStylesheets().add("/client/LoginPageCSS.css");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            //xxx
        }
    }
}
