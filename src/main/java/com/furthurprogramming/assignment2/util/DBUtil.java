package com.furthurprogramming.assignment2.util;

import com.furthurprogramming.assignment2.Main;
import java.sql.*;

public class DBUtil {
    private static Connection conn;
    //Connection String
    private static final String connStr = "jdbc:sqlite:" + Main.class.getResource("database/database.sqlite");
    //Connect to DB
    public static void connect(){
        //Establish the Oracle Connection using Connection String
        try {
            conn = DriverManager.getConnection(connStr);
            System.out.println("Connection established!");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        return conn;
    }

    //Close Connection
    public static boolean disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed!");
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    // Execute query, remember to close conn
    public static ResultSet executeQuery(String query) {
        ResultSet result = null;
        try {
                result = conn.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean update(String sql) {
        try {
             Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
