package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApp extends Application {

    /**
     * The default port to connect on.
     */
    final public static int DEFAULT_PORT = 5555;
    public static Client client;
    public static String server_ip = "";
    public static String server_port = "3306";


    /**
     * main method
     */
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
    public void start(Stage primaryStage) throws Exception {
        Pane root = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/client/boundary/ClientConnectionToServerFXML.fxml"));
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
