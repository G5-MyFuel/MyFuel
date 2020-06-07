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
//קובץ בדיקות עבור SaleOperationTemplateController

public class MainForTestsHani extends Application {
    /**
     * The default port to connect on.
     */
    final public static int DEFAULT_PORT = 5555;
    public static ChatClient chatClient;
    public static String server_ip = "";
    public static String server_port = "";

       String pageName = "SaleOperationTemplate.fxml";   //שם קובץ הfxml
    // String pageName = "RunSaleOperationFXML.fxml";   //שם קובץ הfxml

    private AnchorPane root;

     private SaleOperationTemplateController saleOperationTemplateController;    //אינסטנס של מחלקת הboundary
    // private RunSaleOperationController runSaleOperationController;    //אינסטנס של מחלקת הboundary

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

    public void start(Stage primaryStage) throws Exception {
        ClientApp.server_port = "5555";
        ClientApp.startClient();

        Pane root = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/client/boundary/SaleOperationTemplate.fxml"));// RunSaleOperationFXML  //
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