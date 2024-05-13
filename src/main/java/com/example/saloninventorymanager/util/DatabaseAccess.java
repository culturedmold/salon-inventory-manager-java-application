package com.example.saloninventorymanager.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseAccess {
    private static final String jdbcURL = DBConstants.getJdbcURL();
    private static final String driver = DBConstants.getDriver();
    private static final String userName = DBConstants.getUserName();
    private static final String password = DBConstants.getPassword();
    public static Connection connection = null;

    public static void openConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection Successful");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Close DB connection.");
        } catch (Exception e) { // handle race conditions
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }



}
