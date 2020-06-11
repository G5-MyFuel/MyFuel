package boundary;


import client.ClientConsole;
import common.assets.ProjectPages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;


public class ClientConnectionToServerBoundary {


    @FXML
    private TextField ServerIp;

    @FXML
    void initialize() {

    }

    @FXML
    void setServerIP(ActionEvent event) throws IOException {
        //initialize ip & port of MyFuel Server
        String[] args = {ServerIp.getText()};
        ClientConsole.connection(args);
        mainProjectFX.pagingController.loadBoundary(ProjectPages.GENERAL_DASH_BOARD.getPath());
    }
}
