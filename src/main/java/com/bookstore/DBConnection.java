package com.bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static PropertiesDB properties = new PropertiesDB();

    public static Connection getConnection() throws SQLException {
        String url = properties.getUrl();
        String user = properties.getUser();
        String password = properties.getPassword();

        return DriverManager.getConnection(url, user, password);
    }
}
