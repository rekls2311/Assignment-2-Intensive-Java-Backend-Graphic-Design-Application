package com.furthurprogramming.assignment2.util;

import javax.sql.rowset.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DBUtil {
    //Connection
    private static Connection conn = null;
    //Connection String
    private static final String connStr = "jdbc:sqlite:/Users/longnguyen/Desktop/Java/Assignment2/src/main/resources/database/SQLite";
    //Connect to DB
    public static boolean dbConnect(){
        //Establish the Oracle Connection using Connection String
        try {
            conn = DriverManager.getConnection(connStr);
            System.out.println("Connection established!");
            return true;
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            return false;
        }
    }
    //Close Connection
    public static boolean dbDisconnect() {
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
    public static ResultSet dbExecuteQuery(String query) {
        if (!dbConnect())
            return null;
        ResultSet result = null;
        try {
             result = conn.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
