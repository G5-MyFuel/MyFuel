package Contollers;

import boundary.LoginToSystemBoundary;
import client.ChatClient;
import client.ClientConsole;
import common.assets.SqlAction;
import common.assets.SqlResult;
import entity.User;
import javafx.scene.control.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
/*
import assets.SqlAction;
import assets.SqlResult;
 */

/**
 * The Class BasicController.
 *  * @author Hana Wiener
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
