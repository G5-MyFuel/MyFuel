package client;

import Contollers.BasicController;
import common.assets.SqlResult;
import ocsf.client.*;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ChatClient extends AbstractClient {
    // Instance variables **********************************************

    /**
     * The interface type variable. It allows the implementation of the display
     * method in the client.
     */
    ChatIF clientUI;

    /* Static variables */
    /**
     * all of the below are objects of each client controllers that we created
     */

    private static List<BasicController> deliverySubscribers = new ArrayList<BasicController>();
    // Constructors ****************************************************

    /**
     * Constructs an instance of the chat client.
     *
     * @param host     The server to connect to.
     * @param port     The port number to connect on.
     * @param clientUI The interface type variable.
     */

    public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
        super(host, port); // Call the superclass constructor
        this.clientUI = clientUI;
        openConnection();
    }


    // Instance methods ************************************************

    /**
     * handleMessageFromServer is handling the respond from the server due to client request
     *
     * @param msg is the message that received from the server
     */

    protected void handleMessageFromServer(Object msg) {
        SqlResult result = (SqlResult) msg;
        System.out.println("--> handleMessageFromServer");
        System.out.println(result.getResultData().toString()+ "///////");////////////////////////////
        Platform.runLater(() -> {
            for (BasicController bc : deliverySubscribers) {
                bc.getResultFromClient(result);
            }
        });
    }

    /**
     * handleMessageFromClientUI is channeling the client's request to the server
     *
     * @param message is the message that need to be sent to the server
     */
    public void handleMessageFromClientUI(Object message) {
        try {
            sendToServer(message);
        } catch (IOException e) {
            clientUI.display("Could not send message to server.  Terminating client.");
            quit();
        }
    }

    /**
     * This method terminates the client.
     */
    public void quit() {
        try {
            closeConnection();
        } catch (IOException e) {
        }
        System.exit(0);
    }

    public static void joinSubscription(BasicController basicController) {
        if (!deliverySubscribers.contains(basicController)) {
            deliverySubscribers.add(basicController);
        }
    }

    public static void unSubscribe(BasicController basicController) {
        if (deliverySubscribers.contains(basicController)) {
            deliverySubscribers.remove(basicController);
        }
    }

}
//End of ChatClient class