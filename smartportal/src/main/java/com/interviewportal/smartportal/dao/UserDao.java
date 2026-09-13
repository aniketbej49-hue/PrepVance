package com.interviewportal.smartportal.dao;
import com.interviewportal.smartportal.database.DBConnection;
import com.interviewportal.smartportal.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;


@Repository
public class UserDao {

    public boolean registerUser(User user) {

        boolean isRegistered = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(1, user.getName());

            preparedStatement.setString(2, user.getEmail());

            preparedStatement.setString(3, user.getPassword());

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


    public int loginUser(User user) {

    int userId = -1;

    try {

        Connection connection =
                DBConnection.getConnection();

        String query =
                "SELECT id FROM users WHERE email=? AND password=?";

        PreparedStatement preparedStatement =
                connection.prepareStatement(query);

        preparedStatement.setString(
                1,
                user.getEmail()
        );

        preparedStatement.setString(
                2,
                user.getPassword()
        );

        ResultSet resultSet =
                preparedStatement.executeQuery();

        if(resultSet.next()) {

            userId =
                    resultSet.getInt("id");

        }

    } catch (Exception e) {

        System.out.println(e.getMessage());

    }

    return userId;
}

public boolean emailExists(String email) {

    String sql = "SELECT COUNT(*) FROM users WHERE email = ?";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, email);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {

            return resultSet.getInt(1) > 0;
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return false;
}

public boolean resetPassword(String email, String newPassword) {

    String sql = "UPDATE users SET password = ? WHERE email = ?";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, newPassword);
        statement.setString(2, email);

        int rows = statement.executeUpdate();

        return rows > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
}