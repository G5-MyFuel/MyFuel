package boundary;

import client.ChatClient;
import client.ClientApp;
import client.ClientConsole;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainForTestsNirSettingDiscountRatesBoundary extends Application {


    //File test for SettingDiscountRatesController
    String pageName = "SettingDiscountRatesFXML.fxml";   //שם קובץ הfxml

    private AnchorPane root;
    private SettingDiscountRatesBoundary settingDiscountRatesController;    //אינסטנס של מחלקת הboundary

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        //set server connection -
        String serverIp = "127.0.0.1";
        String [] args = {serverIp};
        ClientConsole.connection(args);

        Pane root = null;

        try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource(pageName + ""));
            root = pageLoader.load();
            settingDiscountRatesController = pageLoader.getController();//קבלת גישה לקונטרולר
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