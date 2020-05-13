package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.serverControllers.EchoServer;


public class ServerApp extends Application {
    public static EchoServer echoserver;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/server/boundary/serverGUI.fxml"));
            root = loader.load();
            Scene s1 = new Scene(root);
            primaryStage.setScene(s1);
            primaryStage.show();
            primaryStage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERR");
        }
    }

}