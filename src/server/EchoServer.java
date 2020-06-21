package server;

import common.assets.SqlAction;
import common.assets.SqlFileAction;
import common.assets.SqlResult;
import entity.MyFile;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * A department responsible for communicating with the server
 *
 * @author itay ziv
 */
public class EchoServer extends AbstractServer {
    // Class variables *************************************************
    /**
     * The default port to listen on.
     */
    final public static int DEFAULT_PORT = 5555;
    final public static String FILLE_DIRECTORY = "C:\\Users\\itay_\\IdeaProjects\\MyFuel\\src\\server\\serverFiles";
    public static int portNumber = 0;

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
        if(msg instanceof SqlFileAction)
        {
            SqlFileAction sqlFileAction = (SqlFileAction)msg;
            if (sqlFileAction.getUpload() == true)
            {
                MyFile uploadedFile = sqlFileAction.getMyFile();
                int fileIndex = ((BigInteger) (sqlResult.getResultData().get(0).get(0))).intValue();
                String fileExtension = (String)sqlFileAction.getActionVars().get(1);
                String filePath = FILLE_DIRECTORY+fileIndex+"."+fileExtension;

                System.out.println("File path is: " + filePath);
                try (FileOutputStream fileOuputStream = new FileOutputStream(filePath))
                {
                    fileOuputStream.write(uploadedFile.getMybytearray());
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                ArrayList<Object> newResultData = new ArrayList<Object>();
                /* Create my file from path */
                if (!sqlResult.getResultData().isEmpty())
                {
                    for(ArrayList<Object> resultRow : sqlResult.getResultData())
                    {
                        String fileName = ((Integer)resultRow.get(0)).toString();
                        String fileExtension = (String)resultRow.get(1);
                        String path = FILLE_DIRECTORY+fileName+"."+fileExtension;
                        MyFile myFile = MyFile.parseToMyFile(path);
                        newResultData.add(myFile);
                    }

                }
                sqlResult.setResultData(newResultData);
            }
        }


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
     *             if no argument is entered.
     */
    public static void startServer(String[] args) {
        MysqlConnection.initSqlArray();
        int port = 0; //Port to listen on

        try {
            port = Integer.parseInt(args[0]); //Get port from command line
        } catch (Throwable t) {
            port = DEFAULT_PORT; //Set port to 5555
        }

        EchoServer sv = new EchoServer(port);
        setSv(sv);

        try {
            sv.listen(); //Start listening for connections
        } catch (Exception ex) {
            System.out.println("ERROR - Could not listen for clients!");
        }
    }

    public static EchoServer temp;

    public static void setSv(EchoServer sv) {
        temp = sv;
    }

}


//End of EchoServer class