package client;

import client.boundary.LoginToSystemController;
import client.logic.LoginToSystemLogic;
import common.ocsf.client.AbstractClient;
import common.tools.Message;

import java.io.IOException;

public class Client extends AbstractClient {
    public static Client Instant;
    /** all of the below are objects of each client controllers that we created */


    /**
     * Client is execute the connection to the server
     *
     * @param host is the host ip number
     * @param port is the port number
     * @throws IOException if IO problems occurs
     */
    public Client(String host, int port) throws IOException {
        super(host, port);
        Instant = this;
        openConnection();
    }

    public static Client getInstant() {
        return Instant;
    }

    /**
     * handleMessageFromServer is handling the respond from the server due to client request
     *
     * @param msg is the message that received from the server
     */
    @Override
    protected void handleMessageFromServer(Object msg) {
        System.out.println("--> handleMessageFromServer");
        try {
            ClientMessages.messageFromServer((Message) msg);
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Could not handle message from server. " + e);
        }

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
            e.printStackTrace();
            System.out.println("Could not send message to server.  Terminating client." + e);
        }
    }

    /***
     * messageFromServer - The next method receives 1 Object and , divides all
     * actions into cases according to Operationtype and use the Object to perform
     * the operation and after send a msg to the client with the appropriate details
     */

    public static void messageFromServer(Message msg) {
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
}
