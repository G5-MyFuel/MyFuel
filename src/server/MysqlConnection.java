package server;

import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;

import java.sql.*;

/**
 * A department that is responsible for communicating with the DB
 * in a concentrated manner, containing all the queries
 *
 * @author Hana Wiener
 */
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
                if (obj instanceof Byte) {
                    Byte byt = (byte) obj;
                    ps.setByte(i, byt);
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
        System.out.println(sqlArray[sqlAction.getActionType().getCode()]);
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
        sqlArray[SqlQueryType.GET_ALL_USERS_TABLE.getCode()] = "SELECT * FROM User;";
        sqlArray[SqlQueryType.UPDATE_USER_FIELD.getCode()] = "UPDATE `Users` SET `loginAttempts` = '5' WHERE `Users`.`userID` = 601983543 AND `Users`.`userType` = 1;";
        sqlArray[SqlQueryType.GET_EMPLOYEE_TABLE.getCode()] = "select employeeID,jobTitle,userFirstName,userLastName,userEmail,companyID,gasStationID from Users as u , Employee as e where u.userID=e.employeeID";

        /* *****************************************************
         * *************** Common Queries ****************
         * *****************************************************/
        sqlArray[SqlQueryType.GET_ALL_VEHICLE_TABLE.getCode()] = "SELECT * FROM `Vehicle`";
        sqlArray[SqlQueryType.GET_ALL_UPDATED_PRICES.getCode()] = "SELECT * FROM Prices;";

        /* *****************************************************
         * *************** Fuel Management Queries ****************
         * *****************************************************/
        sqlArray[SqlQueryType.GET_ALL_MANAGER_STATIONS.getCode()] = "SELECT * FROM `GasStation` WHERE managerID = ?";
        sqlArray[SqlQueryType.UPDATE_FUEL_LIMIT_STOCK.getCode()] = "UPDATE `GasStation` SET `FUEL_LIMIT`= ? WHERE StationNumber = ?";

        sqlArray[SqlQueryType.GET_OPTIONAL_STATIONS.getCode()] = "Select gs.StationNumber, companyName,inventory_95 , inventory_scooter, inventory_diesel, FUEL_LIMIT " +
                "from GasStation as gs , (SELECT u.userID,u.FuelCompany1,u.FuelCompany2,u.FuelCompany3 FROM User AS u where u.userID = ?)" +
                "as ins WHERE gs.companyName = ins.FuelCompany1 or gs.companyName = ins.FuelCompany2 or gs.companyName = ins.FuelCompany3";
        sqlArray[SqlQueryType.INSERT_FASTFUEL_PURCHES.getCode()] = "INSERT INTO `Purchase`(`purchaseID`, `customerID`, `purchaseDate`," +
                " `fuelAmount`, `totalPrice`, `purchaseHour`, `CampaignID`) VALUES (?,?,?,?,?,?,?)";
        sqlArray[SqlQueryType.INSERT_FASTFUEL_PURCHES_TO_FASTFUEL_TABLE.getCode()] = "INSERT INTO `FastFuel`(`purchaseID`, `FuelType`," +
                " `StationNumber`, `companyName`, `PAZ`, `SONOL`, `YELLOW`, `vehicleID`) VALUES (?,?,?,?,?,?,?,?)";
        sqlArray[SqlQueryType.UPDATE_95_INVENTORY_CUSTOMER_PURCHASE.getCode()] = "UPDATE `GasStation` SET `inventory_95`=? WHERE `StationNumber`=?";
        sqlArray[SqlQueryType.UPDATE_DIESEL_INVENTORY_CUSTOMER_PURCHASE.getCode()] = "UPDATE `GasStation` SET `inventory_diesel`=? WHERE `StationNumber`=?";
        sqlArray[SqlQueryType.UPDATE_SCOOTER_INVENTORY_CUSTOMER_PURCHASE.getCode()] = "UPDATE `GasStation` SET `inventory_scooter`=? WHERE `StationNumber`=?";


    /* *****************************************
         * ********** Costumer Management Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.GET_ALL_COSTUMER_TABLE.getCode()] = "SELECT* FROM Costumer AS C, User AS U WHERE C.ID = U.userID";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_FNAME.getCode()] = "UPDATE `User` SET `userFirstName` = ? WHERE userID =?";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_LNAME.getCode()] = "UPDATE `User` SET `userLastName` = ? WHERE userID =?";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_EMAIL.getCode()] = "UPDATE `User` SET `userEmail` = ? WHERE userID =?";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_PRICING_MODEL.getCode()] = "UPDATE `Costumer` SET `Pricing Model` = ? WHERE ID =?";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_PURCHASE_PLAN.getCode()] = "UPDATE `Costumer` SET `Purchase Plan` = ? WHERE ID =?";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_TYPE.getCode()] = "UPDATE `Costumer` SET `customerType` = ? WHERE ID =?";
        sqlArray[SqlQueryType.GET_ALL_COSTUMER_VEHICLES.getCode()] = "SELECT `Vehicle ID`, `Fuel Type`, `Owner ID` FROM `Vehicle` WHERE `Owner ID` = ?";
        sqlArray[SqlQueryType.REMOVE_VEHICLE.getCode()] = "DELETE FROM `Vehicle` WHERE `Vehicle ID` = ?";
        sqlArray[SqlQueryType.REMOVE_COSTUMER.getCode()] =
                "DELETE `Costumer`, `User` FROM `Costumer` INNER JOIN `User` ON Costumer.ID = User.userID WHERE `Costumer`.ID = ?";
        sqlArray[SqlQueryType.UPDATE_COSTUMER_CREDIT_CARD.getCode()] = "UPDATE `Costumer` SET `Credit Card Number` = ? ,`CreditCardExperationDate` = ? ,`CVV` = ?" +
                "WHERE ID =?";
        sqlArray[SqlQueryType.UPDATE_USER_STATIONS.getCode()] = "UPDATE `User` SET `FuelCompany1`= ? ,`FuelCompany2`= ?,`FuelCompany3`= ? WHERE userID = ?";



        /* *****************************************
         * ********** Costumer Registration Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.INSERT_NEW_COSTUMER.getCode()] =
                "INSERT INTO `Costumer`(`ID`, `Credit Card Number`, `CreditCardExperationDate`, `CVV`, `customerType`, `Purchase Plan`, `Pricing Model`) VALUES (?,?,?,?,?,?,?)";
        sqlArray[SqlQueryType.INSERT_NEW_COSTUMER_USER.getCode()] =
                "INSERT INTO `User`(`userID`, `userType`, `userPassword`, `isLoginIndicator`, `userFirstName`, `userLastName`, `userEmail`, `FuelCompany1`, `FuelCompany2`, `FuelCompany3`) VALUES (?,'CUSTOMER',?,0,?,?,?,?,?,?)";
        sqlArray[SqlQueryType.INSERT_NEW_VEHICLE.getCode()] = "INSERT INTO `Vehicle`(`Vehicle ID`, `Fuel Type`, `Owner ID`) VALUES (?,?,?);";

        /* *****************************************************
         * *************** Station Manager Queries ****************
         * *****************************************************/
        sqlArray[SqlQueryType.GET_ALL_ORDER_TO_SUPPLY_FOR_STATION_MANAGER.getCode()] = "SELECT OrderNumber,companyName,StationNum,FuelType,Quantity,OrderStatus,managerID FROM OrderForStock as ofs, GasStation as gs, User as u WHERE ofs.StationNum=gs.StationNumber and ofs.GasCompanyName=gs.companyName and ofs.OrderStatus=\"New\" and u.userID=gs.managerID and gs.managerID=?";
        sqlArray[SqlQueryType.UPDATE_STATUS_TO_IN_TREATMENT.getCode()] = "UPDATE `OrderForStock` SET `OrderStatus` = \"In treatment\" WHERE `OrderNumber` = ?";
        sqlArray[SqlQueryType.GET_ALL_ORDER_WITH_STATUS_DONE.getCode()] = "SELECT OrderNumber,StationNumber FROM OrderForStock as ofs, GasStation as gs, User as u WHERE ofs.StationNum=gs.StationNumber and ofs.GasCompanyName=gs.companyName and ofs.OrderStatus=\"Done\" and gs.managerID=u.userID and gs.managerID=?";
        sqlArray[SqlQueryType.UPDATE_STATUS_TO_VIEWED.getCode()] = "UPDATE `OrderForStock` SET `OrderStatus` = \"Viewed\" WHERE `OrderNumber` = ?";

        /* *****************************************
         * ********** Orders From Supplier Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.GET_ALL_ORDERS_FROM_SUPPLIER_TABLE.getCode()] = "SELECT OrderNumber,OrderStatus,userFirstName,userLastName,StationNumber,OrderDate,FuelType,Quantity,GasCompanyName,managerID,userEmail FROM OrderForStock as ofs, GasStation as gs, User as u WHERE ofs.StationNum=gs.StationNumber and ofs.GasCompanyName=gs.companyName and ofs.OrderStatus=\"In treatment\" and gs.managerID=u.userID ";
        sqlArray[SqlQueryType.UPDATE_STATUS_TO_DONE.getCode()] = "UPDATE `OrderForStock` SET `OrderStatus`= \"Done\" WHERE `OrderNumber`= ?";
        sqlArray[SqlQueryType.UPDATE_95_INVENTORY.getCode()] = "UPDATE `GasStation` SET `inventory_95`=? WHERE `managerID`=? and `StationNumber`=?";
        sqlArray[SqlQueryType.UPDATE_DIESEL_INVENTORY.getCode()] = "UPDATE `GasStation` SET `inventory_diesel`=? WHERE `managerID`=? and `StationNumber`=?";
        sqlArray[SqlQueryType.UPDATE_SCOOTER_INVENTORY.getCode()] = "UPDATE `GasStation` SET `inventory_scooter`=? WHERE `managerID`=? and `StationNumber`=?";

        /* *****************************************
         * ********** Templates+Campaigns Management Queries ***************
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
         * ********** Analytic system Queries *********
         * *****************************************/
        sqlArray[SqlQueryType.GET_ALL_RATING_TABLE.getCode()] = "SELECT * FROM `Rating`";
        sqlArray[SqlQueryType.GET_CUSTOMER_X_PURCHASE_TABLE.getCode()] = "select p.customerID, c.customerType, p.purchaseID, ff.FuelType, p.purchaseHour " +
                "from Costumer as c, Purchase as p, FastFuel as ff " +
                "WHERE p.customerID LIKE c.ID AND p.purchaseID LIKE ff.purchaseID";
        sqlArray[SqlQueryType.INSERT_RATING.getCode()] = "INSERT INTO `Rating`(`CustomerID`, `Rating`, `CustomerType`) VALUES (?,?,?);";
        sqlArray[SqlQueryType.DELETE_ALL_RATINGS_ROWS.getCode()] = "DELETE FROM `Rating` WHERE 1";
        sqlArray[SqlQueryType.GET_RATING_FOR_CUSTUMER_TYPE.getCode()] = "SELECT * FROM `Rating` WHERE `CustomerType`= ? ";
        sqlArray[SqlQueryType.GET_RATING_FOR_TIME_RANGE.getCode()] = "SELECT r.Rating, p.customerID, p.purchaseHour from " +
                "Rating as r, Purchase as p WHERE p.customerID LIKE r.CustomerID";
        sqlArray[SqlQueryType.GET_RATING_FOR_FUEL_TYPE.getCode()] = "SELECT r.Rating, p.customerID, ff.FuelType from " +
                "Rating as r, Purchase as p, FastFuel as ff WHERE p.customerID LIKE r.CustomerID AND ff.purchaseID LIKE p.purchaseID AND FuelType= ? ";



        /* *****************************************
         * ********** Discount Rates Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.GET_RegularSubscriptionSingleVehicle_PRICE.getCode()] = "SELECT * FROM `DiscountRates` " +
                "WHERE `Subscription type` LIKE \"Regular monthly subscription (single)\" AND `companyName` = ?";
        sqlArray[SqlQueryType.GET_FullSubscriptionSingleVehicle_PRICE.getCode()] = "SELECT * FROM `DiscountRates` " +
                "WHERE `Subscription type` LIKE \"Full monthly subscription\" AND `companyName` = ?";
        sqlArray[SqlQueryType.GET_RegularSubscriptionMultiVehicle_PRICE.getCode()] = "SELECT * FROM `DiscountRates` " +
                "WHERE `Subscription type` LIKE \"Regular monthly subscription (multiple)\" AND `companyName` = ?";
        sqlArray[SqlQueryType.INSERT_NEW_PRICE.getCode()] = "UPDATE `DiscountRates` SET `NewDiscountRate`= ?,`Status`= \"Pending approval\" " +
                "WHERE `Subscription type` LIKE ? AND `companyName` = ?";

        /* *****************************************
         * ********** Station Manager Reports Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.GET_Quarterly_Revenue.getCode()] = "select p.totalPrice from FastFuel as ff, Purchase as p " +
                "WHERE ff.purchaseID LIKE p.purchaseID AND ff.StationNumber LIKE ? AND ff.companyName LIKE ? " +
                "AND quarter(p.purchaseDate) LIKE quarter(curdate()) AND YEAR(p.purchaseDate) = YEAR(curdate())";
        sqlArray[SqlQueryType.GET_Purchases_Report.getCode()] = "select ff.FuelType, p.fuelAmount from FastFuel as ff, Purchase as p " +
                "WHERE ff.purchaseID LIKE p.purchaseID " +
                "AND ff.StationNumber LIKE ? " +
                "AND ff.companyName LIKE ? " +
                "AND quarter(p.purchaseDate) LIKE quarter(curdate()) AND YEAR(p.purchaseDate) = YEAR(curdate())";
        sqlArray[SqlQueryType.GET_QuantityItemsStock_Report.getCode()] = "SELECT gs.inventory_95, gs.inventory_diesel, gs.inventory_scooter from GasStation as gs " +
                "WHERE gs.companyName LIKE ?" +
                "AND gs.StationNumber LIKE ?";
        sqlArray[SqlQueryType.GET_Manager_Data.getCode()] = "SELECT * FROM Employee WHERE Employee.employeeID LIKE ?";
        sqlArray[SqlQueryType.INSERT_NEW_Quarterly_Report.getCode()] = "REPLACE INTO `ViewQuarterlyReportsForAdmin`" +
                "(`companyName`, `StationNumber`, `Date`, `Quarterly`, `TotalRevenue`) " +
                "VALUES (?,?,YEAR(curdate()),quarter(curdate()),?)";
        sqlArray[SqlQueryType.INSERT_NEW_Purchases_Report.getCode()] = "REPLACE INTO `ViewPurchasesReportsForAdmin`" +
                "(`companyName`, `StationNumber`, `Date`, `Quarterly`, `FuelType`, `LitersPurchased`, `SalesAmount`) " +
                "VALUES (?,?,YEAR(curdate()),quarter(curdate()),?,?,?)," +
                "(?,?,YEAR(curdate()),quarter(curdate()),?,?,?)," +
                "(?,?,YEAR(curdate()),quarter(curdate()),?,?,?)";

        sqlArray[SqlQueryType.INSERT_NEW_QuantityItemsStock_Report.getCode()] = "REPLACE INTO `ViewQuantityItemsStockReportsForAdmin`" +
                "(`companyName`, `StationNumber`, `Date`, `Quarterly`, `FuelType`, `AvailableInventory`) " +
                "VALUES (?,?,YEAR(curdate()),quarter(curdate()),?,?)," +
                "(?,?,YEAR(curdate()),quarter(curdate()),?,?)," +
                "(?,?,YEAR(curdate()),quarter(curdate()),?,?)";

        /* *****************************************
         * ********** Admin View Reports Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.Get_YearList.getCode()] = "SELECT `Date` FROM `ViewQuarterlyReportsForAdmin` " +
                "WHERE companyName = ? AND StationNumber = ? " +
                "UNION SELECT `Date` FROM `ViewPurchasesReportsForAdmin` " +
                "WHERE companyName = ? AND StationNumber = ? " +
                "UNION SELECT `Date` FROM `ViewQuantityItemsStockReportsForAdmin` " +
                "WHERE companyName = ? AND StationNumber = ?";
                sqlArray[SqlQueryType.CheckIfExists_Quarterly_Report.getCode()] = "SELECT IF( EXISTS" +
                "(SELECT `TotalRevenue` FROM `ViewQuarterlyReportsForAdmin` WHERE `companyName` = ? AND `StationNumber` = ? " +
                "AND `Quarterly` = quarter(?) AND `Date` = YEAR(?)), 1, 0)";
        sqlArray[SqlQueryType.View_Quarterly_Report.getCode()] = "SELECT `TotalRevenue` FROM `ViewQuarterlyReportsForAdmin` " +
                "WHERE `companyName` = ? AND `StationNumber` = ? AND `Quarterly` = quarter(?) AND `Date` = YEAR(?)";
        sqlArray[SqlQueryType.CheckIfExists_Purchases_Report.getCode()] = "SELECT IF( EXISTS " +
                "(SELECT `LitersPurchased`, `SalesAmount` FROM `ViewPurchasesReportsForAdmin` " +
                "WHERE `companyName` = ? AND `StationNumber` = ? AND `Quarterly` = quarter(?) AND `Date` = YEAR(?)), 1, 0)";
        sqlArray[SqlQueryType.View_Purchases_Report.getCode()] = "SELECT `FuelType`, `LitersPurchased`, `SalesAmount` FROM `ViewPurchasesReportsForAdmin` " +
                "WHERE `companyName` = ? AND `StationNumber` = ? AND `Quarterly` = quarter(?) AND " +
                "(`FuelType` = \"Diesel\" OR `FuelType` = \"Gasoline 95\" OR `FuelType` = \"Scooter fuel\") AND `Date` = YEAR(?)";
        sqlArray[SqlQueryType.CheckIfExists_QuantityItemsStock_Report.getCode()] = "SELECT IF( EXISTS " +
                "(SELECT `FuelType`, `AvailableInventory` FROM `ViewQuantityItemsStockReportsForAdmin` " +
                "WHERE `companyName` = ? AND `StationNumber` = ? " +
                "AND `Quarterly` = quarter(?) AND (`FuelType` = \"Diesel\" OR `FuelType` = \"Gasoline 95\" OR `FuelType` = \"Scooter fuel\") AND `Date` = YEAR(?)), 1, 0)";
        sqlArray[SqlQueryType.View_QuantityItemsStock_Report.getCode()] = "SELECT `FuelType`, `AvailableInventory` FROM `ViewQuantityItemsStockReportsForAdmin` " +
                "WHERE `companyName` = ? AND `StationNumber` = ? AND `Quarterly` = quarter(?) " +
                "AND (`FuelType` = \"Diesel\" OR `FuelType` = \"Gasoline 95\" OR `FuelType` = \"Scooter fuel\") AND `Date` = YEAR(?)";

        /* *****************************************
         * ********** Admin Confirm Discount Rates Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.Get_DiscountRates_Table.getCode()] = "SELECT * FROM `DiscountRates` WHERE `NewDiscountRate` != \"-\"";
        sqlArray[SqlQueryType.UPDATE_NEW_DiscountRate.getCode()] = "UPDATE `DiscountRates` " +
                "SET `CurrentDiscountRate`= `NewDiscountRate`, `Status`= \"Approved\",`NewDiscountRate`= \"-\" WHERE `Subscription type` = ?";
        sqlArray[SqlQueryType.Remove_NEW_DiscountRate.getCode()] = "UPDATE `DiscountRates` " +
                "SET `Status`= \"Approved\",`NewDiscountRate`= \"-\" WHERE `Subscription type` = ?";

        /* *****************************************
         * ********** Marketing Manager Reports Queries ****************
         * *****************************************/
        sqlArray[SqlQueryType.GET_Comments_Report.getCode()] = "select customerID,SUM(totalPrice) as TotalSum " +
                "from(SELECT totalPrice,customerID from Purchase where CampaignID = ?) as t group by customerID";
        sqlArray[SqlQueryType.GET_Customers_List.getCode()] = "SELECT customerID, sum(totalPrice) from Purchase AS p, FastFuel AS f " +
                "where p.purchaseID = f.purchaseID AND p.purchaseDate BETWEEN ? AND ? GROUP by customerID ORDER BY sum(totalPrice) DESC";
        sqlArray[SqlQueryType.GET_CustomerPeriodicCharacterization_Report.getCode()] = "SELECT p.customerID, SUM(p.totalPrice) AS TotalSum, f.companyName from Purchase AS p, FastFuel AS f " +
                "where p.purchaseID = f.purchaseID AND p.purchaseDate BETWEEN ? AND ? group by customerID, companyName";

        /* **********************************************************
         * ********** Purchase fuel for home heating ****************
         * **********************************************************
         */
        sqlArray[SqlQueryType.GET_ALL_SHIPPING_DATES_AVAILABLE.getCode()] = "SELECT * FROM ShippingOptionalDates;";
        sqlArray[SqlQueryType.GET_SPECIFIC_CUSTOMER_DETAILS.getCode()] = " select c.id,userFirstName,userLastName,userEmail,'Credit Card Number',CreditCardExperationDate,CVV,customerType,'Pricing Model','Purchase Plan' from User as u,Costumer as c where u.userID=c.ID and c.ID= ?;";
        sqlArray[SqlQueryType.INSERT_NEW_AVAILABLE_DATE_FOR_SHIPPING.getCode()] = "INSERT INTO ShippingOptionalDates (`DayAndDate`) VALUES (?);";
        sqlArray[SqlQueryType.INSERT_NEW_PURCHASE_FUEL_FOR_HOME_HEATING.getCode()] = "INSERT INTO `bpsdc8o22sikrlpvvxqm`.`PurchaseFuelForHomeHeating`\n" +
                "(`purchaseID`,\n" +
                "`emailForInvoice`,\n" +
                "`phoneNumberForContact`,\n" +
                "`noteForPurchase`,\n" +
                "`status`,\n" +
                "`shippingMethod`,\n" +
                "`shippingDateAndTime`)\n" +
                "VALUES(?,?,?,?,\"CONFIRMED_ORDER\",?,?);\n";
        sqlArray[SqlQueryType.INSERT_NEW_PURCHASE_FUEL_FOR_HOME_HEATING1.getCode()] = "INSERT INTO `bpsdc8o22sikrlpvvxqm`.`Purchase`\n" +
                "(`purchaseID`,\n" +
                "`customerID`,\n" +
                "`purchaseDate`,\n" +
                "`fuelAmount`,\n" +
                "`totalPrice`,\n" +
                "`purchaseHour`,\n" +
                "`CampaignID`)\n" +
                "VALUES\n" +
                "(?,?,curdate(),?,?,?,?);\n";
        sqlArray[SqlQueryType.GET_CUSTOMER_PFH_TABLE.getCode()] = "SELECT p.purchaseDate as 'Order date',p.purchaseHour as 'Order time', ph.status as 'Order status',ph.shippingDateAndTime as 'Expected delivery date'\n" +
                "FROM Purchase AS p , PurchaseFuelForHomeHeating AS ph\n" +
                "WHERE p.purchaseID = ph.purchaseID and p.customerID= ?;";

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

