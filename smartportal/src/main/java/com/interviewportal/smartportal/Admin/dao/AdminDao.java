package com.interviewportal.smartportal.Admin.dao;

import com.interviewportal.smartportal.Admin.model.Admin;
import com.interviewportal.smartportal.Admin.database.AdminDBConnection;
import java.sql.*;

import org.springframework.stereotype.Repository;
@Repository
public class AdminDao {

    public boolean registerUser(Admin admin) {

        boolean isRegistered = false;

        try {

            Connection connection =
                    AdminDBConnection.getConnection();

            String query =
                    "INSERT INTO Admin(name, email, password) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(1, admin.getName());

            preparedStatement.setString(2, admin.getEmail());

            preparedStatement.setString(3, admin.getPassword());

            int rowsAffected =
                    preparedStatement.executeUpdate();

            if(rowsAffected > 0) {

                isRegistered = true;

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        return isRegistered;
    }


    public boolean loginUser(Admin admin) {

    boolean isValidUser = false;

    try {

        Connection connection =
                AdminDBConnection.getConnection();

        String query =
                "SELECT * FROM admin WHERE email=? AND password=?";

        PreparedStatement preparedStatement =
                connection.prepareStatement(query);

        preparedStatement.setString(1, admin.getEmail());

        preparedStatement.setString(2, admin.getPassword());

        ResultSet resultSet =
                preparedStatement.executeQuery();

        if(resultSet.next()) {

            isValidUser = true;

        }

    } catch (Exception e) {

        System.out.println(e.getMessage());

    }

    return isValidUser;
}

    
}
