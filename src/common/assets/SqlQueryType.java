package common.assets;
/**
 *
 * @author Hana wiener
 * This enum will represent all the queries in our project
 */
public enum SqlQueryType {


    /* *****************************************
     * ********** Costumer Management Queries ****************
     * *****************************************/
    GET_ALL_COSTUMER_TABLE(SqlExecutionType.EXECUTE_QUERY),
    GET_ALL_TEMPLATES_TABLE(SqlExecutionType.EXECUTE_QUERY),




    /* *****************************************
     * ********** Costumer Registration Queries ****************
     * *****************************************/
    INSERT_NEW_COSTUMER(SqlExecutionType.UPDATE_QUERY),

    /* *****************************************
     * ********** Order From Supplier Queries ****************
     * *****************************************/
    GET_ALL_ORDERS_FROM_SUPPLIER_TABLE(SqlExecutionType.EXECUTE_QUERY),

    /* DO NOT CHANGE THE LOCATION OF MAX_SQL_QUERY!!! */
    /* DO NOT CHANGE THE LOCATION OF MAX_SQL_QUERY!!! */
    /* DO NOT CHANGE THE LOCATION OF MAX_SQL_QUERY!!! */
    /* DO NOT CHANGE THE LOCATION OF MAX_SQL_QUERY!!! */
    MAX_SQL_QUERY(SqlExecutionType.NOT_QUERY);  /* Number of queries */


    private SqlExecutionType executionType;

    private SqlQueryType(SqlExecutionType executionType) {
        this.executionType = executionType;
    }

    public SqlExecutionType getExecutionType() {
        return executionType;
    }


    public int getCode() {
        return ordinal();
    }

    /**
     *
     * @author hana weiner
     * This enum will represent which execution method need to
     * be used EXECUTE QUERY - will represent PreparedStatement ".executeQuery" method
     * Applicable for select queries
     * UPDATE QUERY - will represent PreparedStatement ".updateQuery" method
     * Applicable for delete, update and insert queries
     */

    public enum SqlExecutionType {
        NOT_QUERY, EXECUTE_QUERY, UPDATE_QUERY, INSERT_GET_AUTO_INCREMENT_ID;
    }

}