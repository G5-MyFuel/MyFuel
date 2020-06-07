package server;

import common.assets.SqlAction;
import common.assets.SqlResult;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class EchoServer extends AbstractServer {
    // Class variables *************************************************
    /**
     * The default port to listen on.
     */
    final public static int DEFAULT_PORT = 5555;
    public static int portNumber = 0;
    MysqlConnection mysql = MysqlConnection.Instance;

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

        SqlAction sqlAction = (SqlAction) msg;
        MysqlConnection sqlConnection = new MysqlConnection();
        SqlResult sqlResult = sqlConnection.getResult(sqlAction);

        System.out.println("Message received: " + sqlAction.getActionType() + " from " + client);

        /* If it is a file request also upload it to the server */
//        if(msg instanceof "SqlFileAction"){
//            //we will have to implement it later.
//        }

        this.sendToClient(sqlResult, client);
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
    /**
     * This method is responsible for the creation of
     * the server instance (there is no UI in this phase).
     *
     * @param args The port number to listen on.  Defaults to 5555
     *          if no argument is entered.
     */
    public static void startServer(String[] args)
    {
        MysqlConnection.initSqlArray();
        int port = 0; //Port to listen on

        try
        {
            port = Integer.parseInt(args[0]); //Get port from command line
        }
        catch(Throwable t)
        {
            port = DEFAULT_PORT; //Set port to 5555
        }

        EchoServer sv = new EchoServer(port);
        setSv(sv);

        try
        {
            sv.listen(); //Start listening for connections
        }
        catch (Exception ex)
        {
            System.out.println("ERROR - Could not listen for clients!");
        }
    }

    public static EchoServer temp;

    public static void setSv(EchoServer sv) {
        temp = sv;
    }

}


//End of EchoServer class