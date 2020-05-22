package client.boundary;

import client.ClientApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientConnectionGuiController {
    //GuiController variables:
//    EmployeesManagementGuiController employeesManagementGuiController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ap;

    @FXML
    private TextField ipAddress;

    @FXML
    private Button btnConnect;

    @FXML
    private TextField port;

    @FXML
    void initialize() {
        assert ap != null : "fx:id=\"ap\" was not injected: check your FXML file 'ClientConnectionToServerFXML.fxml'.";
        assert ipAddress != null : "fx:id=\"ipAddress\" was not injected: check your FXML file 'ClientConnectionToServerFXML.fxml'.";
        assert btnConnect != null : "fx:id=\"btnConnect\" was not injected: check your FXML file 'ClientConnectionToServerFXML.fxml'.";
        assert port != null : "fx:id=\"port\" was not injected: check your FXML file 'ClientConnectionToServerFXML.fxml'.";

    }

    @FXML
    void setServerIP(ActionEvent event) throws IOException {
        //initialize ip & port of MyFuel Server
        ClientApp.server_ip = ipAddress.getText();
        ClientApp.server_port = port.getText();

        //check server connection status
        boolean res = ClientApp.startClient();

        //if the connection to the server was successful - open the main App screen
        if (res) {
            //hide current window
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).hide();

            //show employeesManagement gui
//            employeesManagementGuiController = new EmployeesManagementGuiController();
//            employeesManagementGuiController.Instance.primaryStage = new Stage();
//            employeesManagementGuiController.Instance.start(null);

        } else System.err.println("Can't open EmployeesManagementGUI screen");
    }
}
