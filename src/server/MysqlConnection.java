package server;

import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;

import java.sql.*;

public class MysqlConnection {
    /* Initialize database constants */
    /**
     * The Constant DATABASE_DRIVER.
     */
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * The Constant DATABASE_URL.
     */
    private static final String DATABASE_URL = "jdbc:mysql://uec30klrdxwlktiw:tc3wfAIidXugUM7hr3nK@bpsdc8o22sikrlpvvxqm-mysql.services.clever-cloud.com:3306/bpsdc8o22sikrlpvvxqm"; // URL requires Update

    /**
     * The Constant USERNAME.
     */
    private static final String USERNAME = "uec30klrdxwlktiw";  // UserName requires update

    /**
     * The Constant PASSWORD.
     */
    private static final String PASSWORD = "tc3wfAIidXugUM7hr3nK";        // Password requires update

    /**
     * The connection.
     */
    /* Private variables declaration */
    private Connection connection;

    public static MysqlConnection Instance = new MysqlConnection();

    /**
     * The sql array.
     */
    private static String[] sqlArray;

    public Connection connect() {
        try {
            Class.forName(DATABASE_DRIVER).newInstance();
        } catch (Exception ex) {
            System.err.println("Driver definition failed");
        }
        try {
            // Parameters class contains DB Details
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            //+ "?serverTimezone=IST"
            System.out.println("SQL connection succeed");
        } catch (SQLException ex) {/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return connection;
    }


    /**
     * Gets the result.
     *
     * @param sqlAction the sql action
     * @return the result
     */
    public SqlResult getResult(SqlAction sqlAction) {
        SqlResult sqlResult = null;
        PreparedStatement ps = null;

        this.connect();
        try {
            if (sqlAction.getActionType().getExecutionType() == SqlQueryType.SqlExecutionType.INSERT_GET_AUTO_INCREMENT_ID) {
                ps = connection.prepareStatement(sqlArray[sqlAction.getActionType().getCode()], Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = connection.prepareStatement(sqlArray[sqlAction.getActionType().getCode()]);
            }
            for (int i = 1; i <= sqlAction.getActionVars().size(); i++) {
                /* In Array List we start from 0 */
                Object obj = sqlAction.getActionVars().get(i - 1);
                if (obj instanceof Integer) {
                    Integer num = (Integer) obj;
                    ps.setInt(i, num);
                }
                if (obj instanceof Double) {
                    Double dNum = (Double) obj;
                    ps.setDouble(i, dNum);
                }
                if (obj instanceof Float) {
                    Float fNum = (Float) obj;
                    ps.setFloat(i, fNum);
                }
                if (obj instanceof String) {
                    String string = (String) obj;
                    ps.setString(i, string);
                }
                if (obj instanceof Date) {
                    Date date = (Date) obj;
                    ps.setDate(i, date);
                }
                if (obj instanceof Boolean) {
                    Boolean bool = (Boolean) obj;
                    ps.setBoolean(i, bool);
                }
            }

            switch (sqlAction.getActionType().getExecutionType()) {
                case EXECUTE_QUERY:
                    sqlResult = new SqlResult(ps.executeQuery(), sqlAction.getActionType());
                    break;
                case UPDATE_QUERY:
                    sqlResult = new SqlResult(ps.executeUpdate(), sqlAction.getActionType());
                    break;
                case INSERT_GET_AUTO_INCREMENT_ID:
                    if (ps.executeUpdate() != 0) {
                        sqlResult = new SqlResult(ps.getGeneratedKeys(), sqlAction.getActionType());
                    }
                default:
                    break;
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            this.disconnect();
        }
        System.out.println(sqlArray[sqlAction.getActionType().getCode()].toString());
        return sqlResult;
    }


    /**
     * Inits the sql array.
     */
    public static void initSqlArray() {
        sqlArray = new String[SqlQueryType.MAX_SQL_QUERY.getCode()];


        /* *****************************************************
         * *************** Login Queries ****************
         * *****************************************************/
        sqlArray[SqlQueryType.UPDATE_USER_LOGIN_STATUS.getCode()] = "UPDATE `Users` "
                + "SET isLoginIndicator = ? WHERE userID = ? AND userType = ?";




        /* *****************************************************
         * *************** Common Queries ****************
         * *****************************************************/
        sqlArray[SqlQueryType.GET_ALL_VEHICLE_TABLE.getCode()] = "SELECT * FROM `Vehicle`";



        /* *****************************************
         * ********** Costumer Management Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.GET_ALL_COSTUMER_TABLE.getCode()] = "SELECT * FROM `Costumer`";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_FNAME.getCode()] = "UPDATE `Costumer` SET `First Name` = ? WHERE ID =?";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_LNAME.getCode()] = "UPDATE `Costumer` SET `Last Name` = ? WHERE ID =?";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_EMAIL.getCode()] = "UPDATE `Costumer` SET `Email Adress` = ? WHERE ID =?";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_SERVICE_PLAN.getCode()] = "UPDATE `Costumer` SET `Service Plan` = ? WHERE ID =?";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_PURCHASE_PLAN.getCode()] = "UPDATE `Costumer` SET `Purchase Plan` = ? WHERE ID =?";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_TYPE.getCode()] = "UPDATE `Costumer` SET `Costumer Type` = ? WHERE ID =?";
        sqlArray[SqlQueryType.GET_ALL_COSTUMER_VEHICLES.getCode()] = "SELECT `Vehicle ID`, `Fuel Type`, `Owner ID` FROM `Vehicle` WHERE `Owner ID` = ?";
        sqlArray[SqlQueryType.REMOVE_VEHICLE.getCode()] = "DELETE FROM `Vehicle` WHERE `Vehicle ID` = ?";
        sqlArray[SqlQueryType.REMOVE_COSTUMER.getCode()] = "DELETE FROM `Costumer` WHERE `ID` = ?";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_CREDIT_CARD.getCode()] = "UPDATE `Costumer` SET `Credit Card Number` = ? ,`CreditCardExperationDate` = ? ,`CVV` = ?" +
                "WHERE ID =?";





        /* *****************************************
         * ********** Costumer Registration Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.INSERT_NEW_COSTUMER.getCode()] =
                "INSERT INTO `Costumer`(`ID`, `Password`, `Costumer Type`, `First Name`, `Last Name`, `Email Adress`, `Credit Card Number`," +
                        " `CreditCardExperationDate`, `CVV`, `Purchase Plan`, `Service Plan`) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        sqlArray[SqlQueryType.INSERT_NEW_VEHICLE.getCode()] = "INSERT INTO `Vehicle`(`Vehicle ID`, `Fuel Type`, `Owner ID`) VALUES (?,?,?);";


        /* *****************************************
         * ********** Orders From Supplier Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.GET_ALL_ORDERS_FROM_SUPPLIER_TABLE.getCode()] = "SELECT * FROM `OrderForStock`";
        sqlArray[SqlQueryType.UPDATE_STATUS_TO_DONE.getCode()] = "UPDATE `OrderForStock` SET `OrderStatus`= \"Done\" WHERE `OrderNumber`= ?";
        sqlArray[SqlQueryType.GET_FUEL_AMOUNT.getCode()]=

        /* *****************************************
         * ********** Templates+Campaigns Management Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.GET_ALL_TEMPLATES_TABLE.getCode()] = "SELECT * FROM `CampaignTemplates`";
        sqlArray[SqlQueryType.INSERT_NEW_TEMPLATE.getCode()] =
                "INSERT INTO `CampaignTemplates`(`templateID`, `templateName`, `fuelType`, `DiscountPercentages`, `day`," +
                        " `beginHour`, `endHour`) VALUES (?,?,?,?,?,?,?);";
        sqlArray[SqlQueryType.GET_CHOSEN_TEMPLATE_DETAILS.getCode()] = "SELECT * FROM `CampaignTemplates`";


        sqlArray[SqlQueryType.GET_ALL_SALES_TABLE.getCode()] = "SELECT * FROM `MarketingCampaign`";
        sqlArray[SqlQueryType.INSERT_NEW_SALE.getCode()] =
                "INSERT INTO  `MarketingCampaign`(`CampaignID`, `TemplateName`, `BeginDate`, `EndDate`) VALUES (?,?,?,?);";
        sqlArray[SqlQueryType.GET_ALL_SALES_TO_CHACK_SALE.getCode()] = "SELECT * FROM `MarketingCampaign`";


        /* *****************************************
         * ********** Discount Rates Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.GET_RegularSubscriptionSingleVehicle_PRICE.getCode()] = "SELECT * FROM `DiscountRates` WHERE `Subscription type` LIKE \"Regular monthly subscription - single vehicle\"";
        sqlArray[SqlQueryType.GET_FullSubscriptionSingleVehicle_PRICE.getCode()] = "SELECT * FROM `DiscountRates` WHERE `Subscription type` LIKE \"Full monthly subscription (for single vehicle)\"";
        sqlArray[SqlQueryType.GET_RegularSubscriptionMultiVehicle_PRICE.getCode()] = "SELECT * FROM `DiscountRates` WHERE `Subscription type` LIKE \"Regular monthly subscription - number of vehicles\"";
        sqlArray[SqlQueryType.INSERT_NEW_PRICE.getCode()] = "UPDATE `DiscountRates` SET `Current price`= ? WHERE `Subscription type` LIKE ?";

        /* *****************************************
         * ********** Station Manager Reports Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.GET_Quarterly_Revenue.getCode()] = "SELECT * FROM `Purchase` WHERE `purchaseDate` BETWEEN ? AND ?";
        sqlArray[SqlQueryType.GET_Purchases_Report.getCode()] = "SELECT * FROM `Purchase` WHERE `Fuel type` LIKE ?";
        sqlArray[SqlQueryType.GET_QuantityItemsStock_Report.getCode()] = "SELECT * FROM `FuelInventory` WHERE `Company Name` LIKE ?";

        /* *****************************************
         * ********** Login Page Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.GET_ALL_USERS_TABLE.getCode()] = "SELECT * FROM Users;";
        sqlArray[SqlQueryType.UPDATE_USER_FIELD.getCode()] = "UPDATE `Users` SET `loginAttempts` = '5' WHERE `Users`.`userID` = 601983543 AND `Users`.`userType` = 1;";
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Disconnect.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Disconnect all logged users.
     */
    public void disconnectAllLoggedUsers() {
        this.connect();
        try {
            Statement statement = connection.createStatement();
            statement.execute("UPDATE icm.user SET IsLogged = '0'");
        } catch (SQLException e) {
        } finally {
            this.disconnect();
        }
    }

}

