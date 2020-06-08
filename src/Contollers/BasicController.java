package Contollers;

import client.ChatClient;
import client.ClientConsole;
import common.assets.SqlAction;
import common.assets.SqlResult;

import java.io.Serializable;
/*
import assets.SqlAction;
import assets.SqlResult;
 */

/**
 * The Class BasicController.
 *  * @author hani
 */
@SuppressWarnings("serial")
public abstract class BasicController implements Serializable {

    /** The subscription counter. */
    private Integer subscriptionCounter = 0;//?????

    /**
     * Gets the result from client.
     *
     * @param result - The result recieved from the DB
     * Handles the result as implemented by the non abstract class, should activate the boundary
     */
    public abstract void getResultFromClient(SqlResult result);

    /**
     * Subscribe to client deliveries.
     */
    protected void subscribeToClientDeliveries() {

        synchronized(subscriptionCounter)
        {
            if (0 == subscriptionCounter)
            {
                ChatClient.joinSubscription(this);
            }
            subscriptionCounter++;
        }
    }

    /**
     * Unsubscribe from client deliveries.
     */
    protected void unsubscribeFromClientDeliveries() {

        synchronized(subscriptionCounter)
        {
            if (1 == subscriptionCounter)
            {
                ChatClient.unSubscribe(this);
            }

            if (subscriptionCounter != 0)
            {
                subscriptionCounter--;
            }

        }
    }

    /**
     * Send sql action to client.
     *
     * @param sqlAction the sql action
     */
    protected void sendSqlActionToClient(SqlAction sqlAction)
    {
        this.subscribeToClientDeliveries();
        ClientConsole.client.handleMessageFromClientUI(sqlAction);
    }

}
