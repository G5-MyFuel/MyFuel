package common.assets;

/**
 * @author Hana Wiener
 * This enum will represent all the queries in our project
 */
public enum SqlQueryType {

    /* *****************************************
     * ********** Common Queries ****************
     * *****************************************/
    GET_ALL_COSTUMER_TABLE(SqlExecutionType.EXECUTE_QUERY),
    GET_ALL_VEHICLE_TABLE(SqlExecutionType.EXECUTE_QUERY),
    UPDATE_USER_LOGIN_STATUS(SqlExecutionType.UPDATE_QUERY),
    GET_EMPLOYEE_TABLE(SqlExecutionType.EXECUTE_QUERY),
    //for Price class
    GET_ALL_UPDATED_PRICES(SqlExecutionType.EXECUTE_QUERY),
    GET_ALL_PURCHASE_FUEL_AMOUNT_OF_USER(SqlExecutionType.EXECUTE_QUERY),

    /* *****************************************
     * ********** Fuel Management Queries ****************
     * *****************************************/
    GET_ALL_MANAGER_STATIONS(SqlExecutionType.EXECUTE_QUERY),
    UPDATE_FUEL_LIMIT_STOCK(SqlExecutionType.UPDATE_QUERY),


    /* *****************************************
     * ********** PurchaseFuelForHomeHeating ****************
     * *****************************************/
    GET_ALL_SHIPPING_DATES_AVAILABLE(SqlExecutionType.EXECUTE_QUERY),
    INSERT_NEW_AVAILABLE_DATE_FOR_SHIPPING(SqlExecutionType.UPDATE_QUERY),

    /* *****************************************
     * ********** Discount Rates Queries ****************
     * *****************************************/
    GET_RegularSubscriptionSingleVehicle_PRICE(SqlExecutionType.EXECUTE_QUERY),
    GET_FullSubscriptionSingleVehicle_PRICE(SqlExecutionType.EXECUTE_QUERY),
    GET_RegularSubscriptionMultiVehicle_PRICE(SqlExecutionType.EXECUTE_QUERY),
    INSERT_NEW_PRICE(SqlExecutionType.UPDATE_QUERY),

    /* *****************************************
     * ********** Station Manager Reports Queries ****************
     * *****************************************/
    GET_Quarterly_Revenue(SqlExecutionType.EXECUTE_QUERY),
    GET_Purchases_Report(SqlExecutionType.EXECUTE_QUERY),
    GET_QuantityItemsStock_Report(SqlExecutionType.EXECUTE_QUERY),
    GET_Manager_Data(SqlExecutionType.EXECUTE_QUERY),
    INSERT_NEW_Quarterly_Report(SqlExecutionType.UPDATE_QUERY),
    INSERT_NEW_Purchases_Report(SqlExecutionType.UPDATE_QUERY),
    INSERT_NEW_QuantityItemsStock_Report(SqlExecutionType.UPDATE_QUERY),

    /* *****************************************
     * ********** Admin View Reports Queries ****************
     * *****************************************/
    View_Quarterly_Report(SqlExecutionType.EXECUTE_QUERY),
    View_Purchases_Report(SqlExecutionType.EXECUTE_QUERY),
    View_QuantityItemsStock_Report(SqlExecutionType.EXECUTE_QUERY),

    /* *****************************************
     * ********** Admin Confirm Discount Rates Queries ****************
     * *****************************************/
    Get_DiscountRates_Table(SqlExecutionType.EXECUTE_QUERY),
    UPDATE_NEW_DiscountRate(SqlExecutionType.UPDATE_QUERY),
    Remove_NEW_DiscountRate(SqlExecutionType.UPDATE_QUERY),

    /* *****************************************
     * ********** Marketing Manager Reports Queries ****************
     * *****************************************/
    GET_Comments_Report(SqlExecutionType.EXECUTE_QUERY),
    GET_Customers_List(SqlExecutionType.EXECUTE_QUERY),
    GET_CustomerPeriodicCharacterization_Report(SqlExecutionType.EXECUTE_QUERY),

    /* *****************************************
     * ********** Costumer Management Queries ****************
     * *****************************************/
    UPDATE_COSTUMER_FNAME(SqlExecutionType.UPDATE_QUERY),
    UPDATE_COSTUMER_LNAME(SqlExecutionType.UPDATE_QUERY),
    UPDATE_COSTUMER_EMAIL(SqlExecutionType.UPDATE_QUERY),
    UPDATE_COSTUMER_TYPE(SqlExecutionType.UPDATE_QUERY),
    UPDATE_COSTUMER_PRICING_MODEL(SqlExecutionType.UPDATE_QUERY),
    UPDATE_COSTUMER_PURCHASE_PLAN(SqlExecutionType.UPDATE_QUERY),
    GET_ALL_COSTUMER_VEHICLES(SqlExecutionType.EXECUTE_QUERY),
    REMOVE_VEHICLE(SqlExecutionType.UPDATE_QUERY),
    REMOVE_COSTUMER(SqlExecutionType.UPDATE_QUERY),
    UPDATE_COSTUMER_CREDIT_CARD(SqlExecutionType.UPDATE_QUERY),
    UPDATE_USER_STATIONS(SqlExecutionType.UPDATE_QUERY),

    /* *****************************************
     * ********** Users Management Queries ****************
     * *****************************************/
    GET_ALL_USERS_TABLE(SqlExecutionType.EXECUTE_QUERY),
    UPDATE_USER_FIELD(SqlExecutionType.UPDATE_QUERY),

    /* *****************************************
     * ********** Sales and Template Management Queries ****************
     * *****************************************/
    GET_ALL_TEMPLATES_TABLE(SqlExecutionType.EXECUTE_QUERY),
    INSERT_NEW_TEMPLATE(SqlExecutionType.UPDATE_QUERY),
    GET_ALL_SALES_TABLE(SqlExecutionType.EXECUTE_QUERY),
    INSERT_NEW_SALE(SqlExecutionType.UPDATE_QUERY),
    GET_CHOSEN_TEMPLATE_DETAILS(SqlExecutionType.EXECUTE_QUERY),
    GET_ALL_SALES_TO_CHACK_SALE(SqlExecutionType.EXECUTE_QUERY),

    /* *****************************************
     * ********** Analitic Data Queries ****************
     * *****************************************/
    GET_ALL_RATING_TABLE(SqlExecutionType.EXECUTE_QUERY),
    GET_CUSTOMER_X_PURCHASE_TABLE(SqlExecutionType.EXECUTE_QUERY),
    INSERT_RATING(SqlExecutionType.UPDATE_QUERY),
    DELETE_ALL_RATINGS_ROWS(SqlExecutionType.UPDATE_QUERY),
    GET_RATING_FOR_CUSTUMER_TYPE(SqlExecutionType.EXECUTE_QUERY),
    GET_RATING_FOR_TIME_RANGE(SqlExecutionType.EXECUTE_QUERY),
    GET_RATING_FOR_FUEL_TYPE(SqlExecutionType.EXECUTE_QUERY),

    /* *****************************************
     * ********** Costumer Registration Queries ****************
     * *****************************************/
    INSERT_NEW_COSTUMER(SqlExecutionType.UPDATE_QUERY),
    INSERT_NEW_VEHICLE(SqlExecutionType.UPDATE_QUERY),
    INSERT_NEW_COSTUMER_USER(SqlExecutionType.UPDATE_QUERY),

    /* *****************************************************
     * *************** Station Manager Confirmation Queries ****************
     * *****************************************************/
    GET_ALL_ORDER_TO_SUPPLY_FOR_STATION_MANAGER(SqlExecutionType.EXECUTE_QUERY),
    UPDATE_STATUS_TO_IN_TREATMENT(SqlExecutionType.UPDATE_QUERY),
    GET_ALL_ORDER_WITH_STATUS_DONE(SqlExecutionType.EXECUTE_QUERY),
    UPDATE_STATUS_TO_VIEWED(SqlExecutionType.UPDATE_QUERY),

    /* *****************************************
     * ********** Order From Supplier Queries ****************
     * *****************************************/
    GET_ALL_ORDERS_FROM_SUPPLIER_TABLE(SqlExecutionType.EXECUTE_QUERY),
    UPDATE_STATUS_TO_DONE(SqlExecutionType.UPDATE_QUERY),
    UPDATE_95_INVENTORY(SqlExecutionType.UPDATE_QUERY),
    UPDATE_DIESEL_INVENTORY(SqlExecutionType.UPDATE_QUERY),
    UPDATE_SCOOTER_INVENTORY(SqlExecutionType.UPDATE_QUERY),

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
     * @author Hana Wiener
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
