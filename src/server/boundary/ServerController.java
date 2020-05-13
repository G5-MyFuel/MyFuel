package server.boundary;

import com.jfoenix.controls.JFXTextField;
import common.tools.DbDetails;
import common.tools.LocalDataStore;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import server.ServerApp;
import server.serverControllers.EchoServer;
import server.serverControllers.MySqlConnection;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    private DbDetails MySQLWorkbench = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                appendText(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(out, true));
        MySQLWorkbench = new DbDetails(host_field.getText(), scheme_field.getText(), username_field.getText(), password_field.getText(), "");
        printFormFields(MySQLWorkbench);

    }

    public void appendText(String str) {
        Platform.runLater(() -> textBox.appendText(str));
    }

    @FXML
    void initialize() {
        portFieldEmptyAlert.setVisible(false);
//        RequiredFieldValidator validator = new RequiredFieldValidator();
//        validator.setMessage("Required");
//        vffCheck.getValidators().add(validator);
//        vffCheck.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                if(!newValue){
//                    vffCheck.validate();
//                }
//            }
//        });

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
    void startServer() throws FileNotFoundException {
        if (port_field.getText().equals("")) {
            portFieldEmptyAlert.setVisible(true);
        } else {
            portFieldEmptyAlert.setVisible(false);
            // EchoServer.mainServer(args);
            setDBDetailsFromGUI();
            int port = 0; // Port to listen on
            try {
                port = Integer.parseInt(port_field.getText()); // Get port from command line
            } catch (Throwable t) {
                port = EchoServer.DEFAULT_PORT; // Set port to 5555
            }
            EchoServer sv = new EchoServer(port);
            ServerApp.echoserver = sv;
            EchoServer.portNumber = Integer.parseInt(port_field.getText());
            //
            if (MySqlConnection.con == null)
                MySqlConnection.openConnection(MySQLWorkbench);
            else
                System.out.println("DB already connected");
            //
            if (!ServerApp.echoserver.isListening()) {
                try {
                    ServerApp.echoserver.listen();
                    serverConnectImg.setVisible(true);
                    serverDisconnectImg.setVisible(false);
                } catch (IOException e) {
                    System.err.println("Server con err");
                }
            } else {
                System.out.println("Server connected");
                serverConnectImg.setVisible(true);
                serverDisconnectImg.setVisible(false);
                if (rememberMeCheckBox.isSelected()) {
                    //todo: לייצר בדיקה ושמירה לוקאלית של פרטי הסרבר אם לחץ כל שמור על פרטיי במחשב זה
//                    LocalDataStore localDataStore = new LocalDataStore();
//                    List<DbDetails> list = new ArrayList<DbDetails>();
//                    list.add(MySQLWorkbench);
//                    localDataStore.setList(list);
////                    String FILENAME = "ObjectDB";
//                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
//                        oos.writeObject(localDataStore);
//                    }
//
//                    try {
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }

            if (MySqlConnection.con != null) {
                dbConnectImg.setVisible(true);
                dbDisconnectImg.setVisible(false);
            } else {
                System.out.println("DB connection failed");
            }
        }
//            if (!ServerApp.echoserver.isListening()) {
//                try {
//                    ServerApp.echoserver.listen(); // Start listening for
//                    // connections
//                    boolean show = true;
//                    if (ServerApp.echoserver.isListening()) {
//                        serverConnectImg.setVisible(show);
//                        serverDisconnectImg.setVisible(!show);
//                    } else {
//                        serverConnectImg.setVisible(!show);
//                        serverDisconnectImg.setVisible(show);
//                    }
//                } catch (IOException e) {
////                    Alert alert = new Alert(AlertType.ERROR);
////                    alert.setTitle("Failed to Start Server");
////                    alert.setHeaderText("error");
////                    alert.showAndWait();
//                    if (ServerApp.echoserver.isListening()) {
//                        System.out.println("here");
//                    } else {
//                        System.out.println("already opened");
//                    }
//                }
//
//            } else {
//                Alert alert = new Alert(AlertType.INFORMATION);
//                alert.setTitle("Server already Connected");
//                alert.setHeaderText("Server already Connected"); // ADD MORE
//                // INFORAMTION
//                alert.show();
//            }
//            boolean show = true;
//            if (ServerApp.echoserver.getDBStatus()) {
//                dbConnectImg.setVisible(show);
//                dbDisconnectImg.setVisible(!show);
//            } else {
//                dbConnectImg.setVisible(!show);
//                dbDisconnectImg.setVisible(show);
//            }

    }

    @FXML
    void stopServer() throws SQLException {
        if (ServerApp.echoserver.isListening()) {
            ServerApp.echoserver.stopListening();
            serverConnectImg.setVisible(false);
            serverDisconnectImg.setVisible(true);
        } else {
            System.out.println("Server connection already closed");
        }
        if (MySqlConnection.con != null) {
            MySqlConnection.closeConnection();
            dbConnectImg.setVisible(false);
            dbDisconnectImg.setVisible(true);
        } else {
            System.out.println("DB connection already closed");
        }
    }

}
