package com.example.saloninventorymanager.model;

import com.example.saloninventorymanager.DAO.UserDAO;
import com.example.saloninventorymanager.domain_objects.User;
import com.example.saloninventorymanager.util.AlertResponse;
import com.example.saloninventorymanager.util.PasswordHasher;

import java.util.ArrayList;
import java.util.HashMap;

public class UsersModel {
    private final HashMap<String, User> userMap = new HashMap<>();

    public UsersModel() {
        setUsersMap();
    }

    public void setUsersMap() {
        ArrayList<User> usersList = UserDAO.findUsers();
        System.out.println(usersList.toString());

        for (User u : usersList) {
            userMap.put(u.getUsername(), u);
        }
    }

    public AlertResponse createUser(String firstName, String lastName, String username, String password, String confirmPassword) {
        AlertResponse result = null;

        if (userMap.containsKey(username)) {
            result = new AlertResponse("error", "Invalid Username", "This username is already in use.");
            return result;
        } else if (!validatePassword(password, confirmPassword)) {
            result = new AlertResponse("error", "Invalid Password", "This password does not meet minimum length requirements (8 characters) or password confirmation field does not match.");
            return result;
        } else {
            byte[] hashedPassword = PasswordHasher.hashPassword(password);

            Boolean createUser = UserDAO.createUser(firstName, lastName, username, hashedPassword);

            if (createUser) {
                result = new AlertResponse("success", "Success!", "User account was successfully created.");
            }
        }
        return result;
    }

    public Boolean validatePassword(String password, String confirmPassword) {
        if (password.length() < 8) { // check that password meets minimum length
            return false;
        }

        return password.equals(confirmPassword);
    }

    public Boolean updatePassword(String p, String confirmPassword, String username) {
        if (!validatePassword(p, confirmPassword)) { // check that password meets minimum length
            return false;
        }

        User user = userMap.get(username);

        try {
            byte[] hashedPassword = PasswordHasher.hashPassword(p);
            user.setPassword(hashedPassword);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Boolean login(String username, String password) {
        byte[] hashedPassword = PasswordHasher.hashPassword(password);

        return UserDAO.findUserByUsernameAndPassword(username, hashedPassword);
    }

}
