package boundary;

import client.ChatClient;
import client.ClientApp;
import client.ClientConsole;
import common.assets.ProjectPages;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainForTestAdi extends Application {



    private AnchorPane root;
    private OrderExecutionBoundary OrderExecutionController;    //אינסטנס של מחלקת הboundary

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
            FXMLLoader loader = new FXMLLoader();
            /*loader.setLocation(getClass().getResource("/boundary/supplierOrderExecution.fxml"));*/
            loader.setLocation(getClass().getResource(ProjectPages.MANAGER_SUPPLY_CONFIRMATION_PAGE.getPath()));
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