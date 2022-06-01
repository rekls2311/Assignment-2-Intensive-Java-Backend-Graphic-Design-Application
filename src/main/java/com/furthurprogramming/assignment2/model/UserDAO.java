package com.furthurprogramming.assignment2.model;

import com.furthurprogramming.assignment2.util.DBUtil;
import com.furthurprogramming.assignment2.util.ImageUtil;
import javafx.scene.image.Image;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
    public static User searchUser(String username)
    {
        User result = null;

        try {
            try (ResultSet rs = DBUtil.executeQuery("SELECT * FROM users WHERE username='%s'".formatted(username))) {
                if (rs != null && rs.next())
                {
                    var fisrtName = rs.getString("firstname");
                    var lastName = rs.getString("lastname");
                    byte[] imageBytes = rs.getBytes("profile_picture");
                    Image profileImage = ImageUtil.ByteArrayToImage(imageBytes);

                    result = new User(fisrtName, lastName, profileImage, username);
                }
            }
        } catch(SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean createUser(String username, String password, String firstName, String lastName, String profilePicturePath)
    {
        if (profilePicturePath == null)
        {
            return createUser(username, password, firstName, lastName);
        }

        if (!AccountDAO.createAccount(username, password)) {
            return false;
        }

        try {
            String sql = "INSERT INTO users(firstname,lastname,username, profile_picture) VALUES(?,?,?,?)";
            try (PreparedStatement statement = DBUtil.getConnection().prepareStatement(sql)) {

                // SQL starts from 1
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, username);
                statement.setBytes(4, ImageUtil.ImageToByteArray(profilePicturePath));
                statement.executeUpdate();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public static boolean createUser(String username, String password, String firstName, String lastName)
    {
        if (AccountDAO.searchAccount(username) == null){
            if (!AccountDAO.createAccount(username, password)) {
                return false;
            }
        }

        return DBUtil.update("INSERT INTO users(firstname,lastname,username) VALUES('%s','%s','%s')"
                .formatted(firstName, lastName, username));
    }

    public static boolean deleteAllUsers(){
        return DBUtil.update("DELETE FROM users");
    }
}
