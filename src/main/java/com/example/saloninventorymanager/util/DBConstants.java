package com.example.saloninventorymanager.util;

public abstract class DBConstants {
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String location = "//localhost/";
    private static final String dbName = "salon_inv"; // the schema name
    private static final String jdbcURL = protocol + vendorName + location + dbName + "?connectionTimeZone = UTC"; // local
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // driver reference
    private static final String userName = "salonInvUser";
    private static final String password = "salonInvUser!";

    public static String getJdbcURL() {
        return jdbcURL;
    }

    public static String getDriver() {
        return driver;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }
}
