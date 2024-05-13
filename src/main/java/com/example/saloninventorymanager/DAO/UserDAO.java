package com.example.saloninventorymanager.DAO;

import com.example.saloninventorymanager.domain_objects.User;
import com.example.saloninventorymanager.util.DatabaseAccess;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private static final String findUsersStatement = "SELECT * FROM users";
    private static final String findByUsernameAndPasswordStatement = "SELECT * FROM users WHERE username = ? AND password = ?";
    private static final String createUserStatement = "INSERT INTO users (first_name, last_name, username, password) VALUES (?, ?, ?, ?)";
    private static final String updateUserByIdStatement = "";
    private static final String deleteUserByIdStatement = "";

    public static Boolean findUserByUsernameAndPassword(String username, byte[] password) {
        try {
            PreparedStatement ps = DatabaseAccess.getConnection().prepareStatement(findByUsernameAndPasswordStatement);
            ps.setString(1, username);
            ps.setBytes(2, password);

            ResultSet result = ps.executeQuery();

            System.out.println(result);

            if (result.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Boolean createUser(String firstName, String lastName, String username, byte[] password) {

        try {
            PreparedStatement ps = DatabaseAccess.getConnection().prepareStatement(createUserStatement);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, username);
            ps.setBytes(4, password);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Boolean updateUserById(Integer userId) {

        return false;
    }

    public static ArrayList<User> findUsers() {
        ArrayList<User> usersList = new ArrayList<>();
        try (PreparedStatement ps = DatabaseAccess.getConnection().prepareStatement(findUsersStatement)) {
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);

            while (rs != null && rs.next()) {
                Blob passwordBlob = rs.getBlob("password");
                int blobLength = (int)passwordBlob.length();
                byte[] blobBytes = passwordBlob.getBytes(1, blobLength);
                passwordBlob.free();

                User user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("first_name"), rs.getString("last_name"), blobBytes);
                System.out.println(user);
                usersList.add(user);
                rs.next();
            }
            System.out.println(usersList);
            return usersList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }
}
