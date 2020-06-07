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
//קובץ בדיקות עבור SaleOperationTemplateController

public class MainForTestsHani extends Application {

       String pageName = "SaleOperationTemplate.fxml";   //שם קובץ הfxml
    // String pageName = "RunSaleOperationFXML.fxml";   //שם קובץ הfxml

    private AnchorPane root;

     private SaleOperationTemplateBoundary saleOperationTemplateController;    //אינסטנס של מחלקת הboundary
    // private RunSaleOperationController runSaleOperationController;    //אינסטנס של מחלקת הboundary

    public static void main(String[] args) {
        launch(args);
    }



    public void start(Stage primaryStage) throws Exception {
        //set server connection -
        String serverIp = "127.0.0.1";
        String [] args = {serverIp};
        ClientConsole.connection(args);

        Pane root = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/boundary/SaleOperationTemplate.fxml"));// RunSaleOperationFXML  //
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