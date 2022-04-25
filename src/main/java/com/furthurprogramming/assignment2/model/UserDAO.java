package com.furthurprogramming.assignment2.model;

import com.furthurprogramming.assignment2.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static User searchUser(String username)
    {
        User result = null;

        try {
            try (ResultSet rs = DBUtil.executeQuery("SELECT * FROM users WHERE username='%s'".formatted(username))) {
                if (rs != null && rs.next())
                    result = new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("profile_picture"), username);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean createUser(String username, String password, String firstName, String lastName, String profilePicture)
    {
        if (!AccountDAO.createAccount(username, password)) {
            return false;
        }

        if (!DBUtil.update("INSERT INTO users(firstname,lastname,profile_picture,username) VALUES('%s','%s','%s','%s')"
                .formatted(firstName, lastName, profilePicture, username)))
            return false;

        return true;
    }
}
