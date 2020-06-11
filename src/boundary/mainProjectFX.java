package boundary;


import java.io.IOException;
import java.util.Optional;

import Contollers.PagingController;
import common.assets.ProjectPages;

import entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

// TODO: Auto-generated Javadoc
/**
 * This is the main project application
 * Used in order to start the client side and also to contain the following information:.
 *
 * @author Itay Ziv
 */
public class mainProjectFX extends Application {

    /** The main stage. */
    public static Stage mainStage;

    /** The current user. */
    public static User currentUser;

    /** The paging controller. */
    public static PagingController pagingController;

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ProjectPages.CLIENT_CONNECT_TO_SERVER_PAGE.getPath()));
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(pane);
        stage.setTitle("MyFuel");
        stage.setScene(scene);
        stage.show();
        mainStage = stage;
        pagingController = new PagingController();
        mainStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    /**
     * Close window event.
     *
     * @param event the event
     */
    private void closeWindowEvent(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Quit application");
        alert.setContentText(String.format("Are you sure you want to quit?"));
        alert.initOwner(mainStage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();

        if(res.isPresent())
        {
            if(res.get().equals(ButtonType.CANCEL))
            {
                event.consume();
            }
            else if (currentUser != null)
            {
                pagingController.userLogout();
            }
        }
    }

}
