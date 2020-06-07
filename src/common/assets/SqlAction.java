package common.assets;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * This class is used to send sql queries to the database.
 *
 * @author Hana Wiener
 */
@SuppressWarnings("serial")
public class SqlAction implements Serializable
{

    /** The action type. */
    private SqlQueryType actionType;

    /** The action vars. */
    private ArrayList<Object> actionVars;

    /**
     * Instantiates a new sql action.
     *
     * @param actionType the action type
     * @param actionVars the action vars
     */
    public SqlAction(SqlQueryType actionType, ArrayList<Object> actionVars) {
        this.actionType = actionType;
        this.actionVars = actionVars;
    }

    /**
     * Instantiates a new sql action.
     *
     * @param actionType the action type
     */
    public SqlAction(SqlQueryType actionType)
    {
        this.actionType = actionType;
        actionVars = new ArrayList<Object>();
    }

    /**
     * Gets the action type.
     *
     * @return the action type
     */
    public SqlQueryType getActionType() {
        return actionType;
    }

    /**
     * Gets the action vars.
     *
     * @return the action vars
     */
    public ArrayList<Object> getActionVars() {
        return actionVars;
    }

}
