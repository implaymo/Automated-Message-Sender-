package com.sendit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final static String dbUrl = System.getenv("url");
    private final static String dbDriver = System.getenv("driver");
    private final static String dbUsername = System.getenv("username");
    private final static String dbPassword = System.getenv("password");

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            System.out.println("Connected with database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}