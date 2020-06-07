package client.boundary;

import client.ChatClient;
import client.ClientApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainForTestsNirSettingDiscountRatesBoundary extends Application {

    /**
     * The default port to connect on.
     */
    final public static int DEFAULT_PORT = 5555;
    public static ChatClient chatClient;
    public static String server_ip = "";
    public static String server_port = "";

    //File test for SettingDiscountRatesController
    String pageName = "SettingDiscountRatesFXML.fxml";   //שם קובץ הfxml

    private AnchorPane root;
    private SettingDiscountRatesBoundary settingDiscountRatesController;    //אינסטנס של מחלקת הboundary

    public static void main(String[] args) {
        launch(args);
    }

    public static boolean startClient() throws IOException {
        String host = "";
        int port = 0; // The port number
        if (server_port == "")
            port = DEFAULT_PORT;
        else
            port = Integer.parseInt(server_port);

        host = server_ip;


        chatClient = new ChatClient(host, port);
        System.out.println("Client setup connection! " + host + ": " + port);

        return true;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ClientApp.server_port = "5555";
        ClientApp.startClient();
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