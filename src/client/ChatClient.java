package client;

import common.assets.BasicController;
import common.assets.SqlResult;
import common.ocsf.client.AbstractClient;
import common.tools.Message;
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
    /** all of the below are objects of each client controllers that we created */

    private static List<BasicController> deliverySubscribers = new ArrayList<BasicController>();
    // Constructors ****************************************************

    public static ChatClient Instant;//ours

//שלנו:
    /**
     * Client is execute the connection to the server
     *
     * @param host is the host ip number
     * @param port is the port number
     * @throws IOException if IO problems occurs
     */
    public ChatClient(String host, int port) throws IOException {
        super(host, port);
        Instant = this;
        openConnection();
    }

//שלהם:

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

    public static ChatClient getInstant() {
        return Instant;
    }

    // Instance methods ************************************************

    /**
     * handleMessageFromServer is handling the respond from the server due to client request
     *
     * @param msg is the message that received from the server
     */
    @Override
    protected void handleMessageFromServer(Object msg) {
        SqlResult result = (SqlResult) msg;

        Platform.runLater(() -> {
            for (BasicController bc : deliverySubscribers) {
                bc.getResultFromClient(result);
            }
        });
        //שלנו הישן:
       /* System.out.println("--> handleMessageFromServer");
        try {
            ClientMessages.messageFromServer((Message) msg);
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Could not handle message from server. " + e);
        }*/
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

            /*שלנו
            e.printStackTrace();
            System.out.println("Could not send message to server.  Terminating client." + e);
            */
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

    /***
     * messageFromServer - The next method receives 1 Object and , divides all
     * actions into cases according to Operationtype and use the Object to perform
     * the operation and after send a msg to the client with the appropriate details
     */
//לא צריך !!!!!! ?????
    public static void messageFromServer(Message msg)  {
        Message m = msg;

        switch (m.getOperationType()) {
            case getRequirementData:
//                EmployeesManagementGuiController.Instance.setDataTable(m.getObject());
                break;
            case updateRequirement:
//                EmployeesManagementGuiController.Instance.afterUpdateJobTitleInDb(m.getObject());
                break;
            case getAllUsersTable:

                break;
            default:
                break;

        }
    }


//check if we need this:
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
//


}
