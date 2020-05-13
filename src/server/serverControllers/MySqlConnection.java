package server.serverControllers;

import common.tools.DbDetails;

import java.sql.*;

public class MySqlConnection {
    public static Connection con;
    public static MySqlConnection Instance = new MySqlConnection();

    public static void openConnection(DbDetails dbDetails) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.err.println("Driver definition failed");
        }
        try {
            // Parameters class contains DB Details
            con = DriverManager.getConnection("jdbc:mysql://" + dbDetails.DB_HOST + ":3306/" + dbDetails.DB_SCHEME + "?serverTimezone=IST", dbDetails.DB_USERNAME, dbDetails.DB_PASSWORD);
            System.out.println("SQL connection succeed");
        } catch (SQLException ex) {/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public MySqlConnection getInstance() {
        return Instance;
    }


    public ResultSet getQuery(String query) {
        System.out.println("getQuery");
        System.out.println(con);
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean insertOrUpdate(String query) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("my sql: " + query + " executed!");
            return true;
        } catch (SQLException e) {
            System.out.println("insert or update exception !");
            return false;
        }
    }

    public static Connection getConnection() throws SQLException {
        return con;
    }

    public static void closeConnection() throws SQLException {
        if (con != null) {
            con.close();
            System.out.println("Connection to MySql closed");
        }

    }
}

