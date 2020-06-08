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

        return sqlResult;
    }


    /**
     * Inits the sql array.
     */
    public static void initSqlArray() {
        sqlArray = new String[SqlQueryType.MAX_SQL_QUERY.getCode()];

        /* *****************************************************
         * *************** Common Queries ****************
         * *****************************************************/




        /* *****************************************
         * ********** Costumer Management Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.GET_ALL_COSTUMER_TABLE.getCode()] = "SELECT * FROM `Costumer`";




        /* *****************************************
         * ********** Costumer Registration Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.INSERT_NEW_COSTUMER.getCode()] =
                "INSERT INTO `Costumer`(`ID`, `Password`, `Type`, `First Name`, `Last Name`, `Email Adress`, `Credit Card Number`, `CreditCardExperationDate`, `CVV`," +
                        " `purchasePlan`, `Vehicle ID`, `Vehicale GasType`, `Service Plan`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";


        /* *****************************************
         * ********** Template Management Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.GET_ALL_TEMPLATES_TABLE.getCode()] = "SELECT * FROM `CampaignTemplates`";



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

