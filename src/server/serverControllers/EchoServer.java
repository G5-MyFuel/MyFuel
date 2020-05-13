package server.serverControllers;

import common.ocsf.server.AbstractServer;
import common.ocsf.server.ConnectionToClient;
import common.tools.Message;
import common.tools.OperationType;
import common.tools.QueryToArrayList;

import java.sql.ResultSet;

public class EchoServer extends AbstractServer {
    // Class variables *************************************************
    /**
     * The default port to listen on.
     */
    final public static int DEFAULT_PORT = 5555;
    public static int portNumber = 0;
    MySqlConnection mysql = MySqlConnection.Instance;

    // Constructors ****************************************************

    /**
     * Constructs an instance of the echo server.
     *
     * @param port The port number to connect on.
     */
    public EchoServer(int port) {
        super(port);
    }

    // Instance methods ************************************************

    /**
     * This method handles any messages received from the client.
     *
     * @param msg    The message received from the client.
     * @param client The connection from which the message originated.
     */
    @Override
    public void handleMessageFromClient(Object msg, ConnectionToClient client) {
        Message m = (Message) msg;
        System.out.println("Message received: " + msg + " from " + client);
        try {
            switch ((m.getOperationType())) {
                case getRequirementData:
                    ResultSet rs = mysql.getQuery(m.getObject().toString());
                    sendToClient(new Message(OperationType.getRequirementData, QueryToArrayList.ResultSetToArrayList(rs)), client);
                    rs.close();
                    break;
                case updateRequirement:
                    boolean res = mysql.insertOrUpdate(m.getObject().toString());
                    sendToClient(new Message(OperationType.updateRequirement, res), client);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method overrides the one in the superclass. Called when the server
     * starts listening for connections.
     */
    protected void serverStarted() {
        System.out.println("Server listening for connections on port " + getPort());
    }

    /**
     * This method overrides the one in the superclass. Called when the server stops
     * listening for connections.
     */
    protected void serverStopped() {
        System.out.println("Server has stopped listening for connections.");
    }

    // Class methods ***************************************************


    public boolean getDBStatus() {
        if (MySqlConnection.con != null)
            return true;
        return false;
    }
}
