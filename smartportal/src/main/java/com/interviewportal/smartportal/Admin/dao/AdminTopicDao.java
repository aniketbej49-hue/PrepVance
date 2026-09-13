package com.interviewportal.smartportal.Admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.interviewportal.smartportal.database.DBConnection;
import com.interviewportal.smartportal.Admin.model.AdminTopic;
import java.sql.*;

@Repository
public class AdminTopicDao {
    
    public boolean addTopic(AdminTopic adminTopic) {

        boolean isAdded = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO topics(topic_name) VALUES (?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(
                    1,
                    adminTopic.getTopicName()
            );

            int rowsAffected =
                    preparedStatement.executeUpdate();

            if(rowsAffected > 0) {

                isAdded = true;

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        return isAdded;
    }

   

public List<AdminTopic> getAllTopics() {

    List<AdminTopic> topicList =
            new ArrayList<>();

    try {

        Connection connection =
                DBConnection.getConnection();

        String query =
                "SELECT id, topic_name FROM topics";

        PreparedStatement preparedStatement =
                connection.prepareStatement(query);

        ResultSet resultSet =
                preparedStatement.executeQuery();


        while (resultSet.next()) {

            AdminTopic adminTopic =
                    new AdminTopic();

            adminTopic.setId(
                    resultSet.getInt("id")
            );

            adminTopic.setTopicName(
                    resultSet.getString("topic_name")
            );

            topicList.add(adminTopic);
        }

    } catch (Exception e) {

        System.out.println(
                "Get Topics Error: "
                + e.getMessage()
        );
    }

    return topicList;
}
    public boolean deleteTopic(int id) {

    boolean isDeleted = false;

    try {

        Connection connection =
                DBConnection.getConnection();

        String query =
                "DELETE FROM topics WHERE id=?";

        PreparedStatement preparedStatement =
                connection.prepareStatement(query);

        preparedStatement.setInt(1, id);

        int rowsAffected =
                preparedStatement.executeUpdate();

        if(rowsAffected > 0) {

            isDeleted = true;

        }

    } catch (Exception e) {

        System.out.println(e.getMessage());

    }

    return isDeleted;
}

public List<AdminTopic> searchTopics(String keyword) {

    List<AdminTopic> topicList = new ArrayList<>();

    try {

        Connection connection =
                DBConnection.getConnection();

        String query =
                "SELECT * FROM topics WHERE topic_name LIKE ?";

        PreparedStatement preparedStatement =
                connection.prepareStatement(query);

        preparedStatement.setString(
                1,
                "%" + keyword + "%"
        );

        ResultSet resultSet =
                preparedStatement.executeQuery();

        while(resultSet.next()) {

            AdminTopic adminTopic = new AdminTopic();

            adminTopic.setId(
                    resultSet.getInt("id")
            );

            adminTopic.setTopicName(
                    resultSet.getString("topic_name")
            );

           

            topicList.add(adminTopic);
        }

    } catch (Exception e) {

        System.out.println(e.getMessage());

    }

    return topicList;
}
}
