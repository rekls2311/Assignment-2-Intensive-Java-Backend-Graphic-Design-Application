package com.furthurprogramming.assignment2.util;

import com.furthurprogramming.assignment2.Main;

import java.sql.*;

public class DBUtil {
    //Connection String
    private static final String connStr = "jdbc:sqlite:" + Main.class.getResource("database/database.sqlite");
    //Connect to DB
    public static Connection connect(){
        //Establish the Oracle Connection using Connection String
        try {
            Connection conn = DriverManager.getConnection(connStr);
            System.out.println("Connection established!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            return null;
        }
    }
    //Close Connection
    public static boolean disconnect(Connection conn) {
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

    // Execute query
    public static ResultSet executeQuery(String query) {
        ResultSet result = null;
        try {
            try (var conn = connect()){
                result = conn.createStatement().executeQuery(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean update(String sql) {
        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
