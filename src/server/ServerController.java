package server;

import com.jfoenix.controls.JFXTextField;
import common.assets.DbDetails;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {

    @FXML
    private TextArea textBox;

    @FXML
    private TextField host_field;

    @FXML
    private TextField scheme_field;

    @FXML
    private TextField password_field;

    @FXML
    private TextField username_field;

    @FXML
    private TextField port_field;

    @FXML
    private ImageView dbConnectImg;

    @FXML
    private ImageView dbDisconnectImg;

    @FXML
    private ImageView serverConnectImg;

    @FXML
    private ImageView serverDisconnectImg;

    @FXML
    private Text portFieldEmptyAlert;

    @FXML
    private CheckBox rememberMeCheckBox;

    @FXML
    private JFXTextField vffCheck;

    @FXML
    private Button onBtn;

    @FXML
    private Button offBtn;

    private DbDetails MySQLWorkbench = null;

    /**
     * The is connected.
     */
    private static boolean isConnected = false;

    /**
     * The sql connection.
     */
    private MysqlConnection sqlConnection = new MysqlConnection();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                appendText(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(out, true));
        MySQLWorkbench = new DbDetails(host_field.getText(), scheme_field.getText(), username_field.getText(), password_field.getText(), "5555");
        printFormFields(MySQLWorkbench);
    }

    public void appendText(String str) {
        Platform.runLater(() -> textBox.appendText(str));
    }

    @FXML
    void initialize() {
        portFieldEmptyAlert.setVisible(false);

    }

    private void printFormFields(DbDetails dbDetails) {
        host_field.setText(dbDetails.getDB_HOST());
        scheme_field.setText(dbDetails.getDB_SCHEME());
        password_field.setText(dbDetails.getDB_PASSWORD());
        username_field.setText(dbDetails.getDB_USERNAME());
        port_field.setText(dbDetails.getDB_PORT());
    }

    private void setDBDetailsFromGUI() {
        DbDetails dbDetails = MySQLWorkbench;
        dbDetails.setDB_HOST(host_field.getText());
        dbDetails.setDB_PASSWORD(password_field.getText());
        dbDetails.setDB_PORT(port_field.getText());
        dbDetails.setDB_SCHEME(scheme_field.getText());
        dbDetails.setDB_USERNAME(username_field.getText());
    }

    @FXML
    void startServer() {

        EchoServer.startServer(ServerApp.newargs);
        isConnected = true;
        serverConnectImg.setVisible(true);
        serverDisconnectImg.setVisible(false);
        System.out.println("Server connected");
        dbConnectImg.setVisible(true);
        dbDisconnectImg.setVisible(false);
        onBtn.setDisable(true);
        offBtn.setDisable(false);
    }


    @FXML
    void stopServer() throws IOException {

        if (isConnected == true) {
            isConnected = false;
            sqlConnection.disconnectAllLoggedUsers();
            EchoServer.temp.close();
            offBtn.setDisable(true);
            onBtn.setDisable(false);
            serverConnectImg.setVisible(false);
            serverDisconnectImg.setVisible(true);
            dbConnectImg.setVisible(false);
            dbDisconnectImg.setVisible(true);
        } else System.out.println("Server already disconnected");

    }


}


