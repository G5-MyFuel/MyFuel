package client.boundary;

import client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainForTestAdi extends Application {
    //קובץ בדיקות עבור SaleOperationTemplateController
    String pageName = "supplier-OrderExecution-adi.fxml";   //שם קובץ הfxml
    final public static int DEFAULT_PORT = 5555;
    public static Client client;
    public static String server_ip = "";
    public static String server_port = "";

    private AnchorPane root;
    private OrderExecutionController OrderExecutionController;    //אינסטנס של מחלקת הboundary

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


        client = new Client(host, port);
        System.out.println("Client setup connection! " + host + ": " + port);

        return true;

    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(getClass().getResource(pageName + ""));
            root = pageLoader.load();
            OrderExecutionController = pageLoader.getController();//קבלת גישה לקונטרולר
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
