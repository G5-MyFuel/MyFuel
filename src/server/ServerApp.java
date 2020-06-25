package server;

import common.assets.ProjectPages;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.Optional;


public class ServerApp extends Application {
    /**
     * The newargs.
     */
    public static String[] newargs;

    /**
     * The sql connection.
     */
    private MysqlConnection sqlConnection = new MysqlConnection();

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    public static void main(String[] args) {
        newargs = args;
        initTimeWatcher();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getResource(ProjectPages.SERVER_START_PAGE.getPath());
        Parent pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        //setting the stage and showing server app
        primaryStage.setScene(scene);
        primaryStage.setTitle("Start Server");
        primaryStage.show();
        primaryStage.setResizable(false);

        //on close server
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getButtonTypes().remove(ButtonType.OK);
                alert.getButtonTypes().add(ButtonType.CANCEL);
                alert.getButtonTypes().add(ButtonType.YES);
                alert.setTitle("Quit application");
                alert.setContentText(String.format("Are you sure you want to quit?"));
                Optional<ButtonType> res = alert.showAndWait();

                if (res.isPresent()) {
                    if (res.get().equals(ButtonType.CANCEL)) {
                        e.consume();
                    } else {
                        sqlConnection.disconnectAllLoggedUsers();
                    }
                }
                System.exit(0);
            }
        });
    }

    /**
     * Inits the time watcher.
     */
    private static void initTimeWatcher() {
        Thread t = new Thread(new TimeWatcher());
        t.start();
    }

}