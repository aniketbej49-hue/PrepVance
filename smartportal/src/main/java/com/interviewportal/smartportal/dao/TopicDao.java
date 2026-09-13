package com.interviewportal.smartportal.dao;

import com.interviewportal.smartportal.database.DBConnection;
import com.interviewportal.smartportal.model.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class TopicDao {


    public List<Topic> getAllTopics() {

    List<Topic> topicList = new ArrayList<>();

    try {

        Connection connection =
                DBConnection.getConnection();

        String query =
                "SELECT * FROM topics";

        PreparedStatement preparedStatement =
                connection.prepareStatement(query);

        ResultSet resultSet =
                preparedStatement.executeQuery();

        while(resultSet.next()) {

            Topic topic = new Topic();

            topic.setId(
                    resultSet.getInt("id")
            );

            topic.setTopicName(
                    resultSet.getString("topic_name")
            );


         topicList.add(topic);
         
        }

    } catch (Exception e) {

        System.out.println(e.getMessage());

    }

    return topicList;
}

public List<Topic> searchTopics(String keyword) {

    List<Topic> topicList = new ArrayList<>();

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

            Topic topic = new Topic();

            topic.setId(
                    resultSet.getInt("id")
            );

            topic.setTopicName(
                    resultSet.getString("topic_name")
            );

            topicList.add(topic);
        }

    } catch (Exception e) {

        System.out.println(e.getMessage());

    }

    return topicList;
}
}
