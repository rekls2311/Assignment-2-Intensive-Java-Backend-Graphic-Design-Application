package com.furthurprogramming.assignment2.model;

import com.furthurprogramming.assignment2.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    public static boolean isAccountExists(String username, String password)
    {
        boolean result = false;

        try {
            ResultSet rs = DBUtil.executeQuery("SELECT * FROM accounts WHERE username='%s' AND password='%s'".formatted(username, password));
            if (rs != null && rs.next())
                result = true;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public static Account searchAccount(String username)
    {
        Account result = null;

        try {

            try (ResultSet rs = DBUtil.executeQuery("SELECT * FROM accounts WHERE username='%s'".formatted(username))) {
                if (rs != null && rs.next())
                    result = new Account(username, rs.getString("password"));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // true if account created successfully
    // Never call this except from UserDAO
    public static boolean createAccount(String username, String password)
    {
        return DBUtil.update("INSERT INTO accounts(username,password) VALUES('%s','%s')".formatted(username, password));
    }
}
