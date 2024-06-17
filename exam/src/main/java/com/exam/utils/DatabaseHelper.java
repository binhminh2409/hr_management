package com.exam.utils;

import com.exam.config.DatasourceProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static DatasourceProperties datasourceProperties = new DatasourceProperties();

    public static Connection connection;

    private DatabaseHelper() {
    }

    public static synchronized Connection getConnection() {
        if (connection == null) {
            init();
        }
        return connection;
    }

    public static void init() {
        try {
            Class.forName(datasourceProperties.getDriverClassName());
            connection = DriverManager.getConnection(datasourceProperties.getUrl(), datasourceProperties.getUsername(), datasourceProperties.getPassword());
            if (connection != null) {
                System.out.println("Connection Successful");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
