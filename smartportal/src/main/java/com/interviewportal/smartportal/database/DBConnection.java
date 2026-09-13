package com.interviewportal.smartportal.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/interview_portal";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "SQ12@Ani";

    public static Connection getConnection() {

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );

            System.out.println("Database Connected Successfully");

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        return connection;
    }
}