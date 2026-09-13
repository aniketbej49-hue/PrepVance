package com.interviewportal.smartportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.interviewportal.smartportal.database.DBConnection;
import com.interviewportal.smartportal.model.TopicStatus;

@Repository
public class TopicStatusDao {


    public List<TopicStatus> getUserTopicStatuses(int userId) {

    List<TopicStatus> topicStatuses =
            new ArrayList<>();


    try {

        Connection connection =
                DBConnection.getConnection();


        String query =
                "SELECT * FROM user_topics " +
                "WHERE user_id=?";


        PreparedStatement preparedStatement =
                connection.prepareStatement(query);


        preparedStatement.setInt(
                1,
                userId
        );


        ResultSet resultSet =
                preparedStatement.executeQuery();


        while (resultSet.next()) {

            TopicStatus topicStatus =
                    new TopicStatus();


            topicStatus.setId(
                    resultSet.getInt("id")
            );


            topicStatus.setUserId(
                    resultSet.getInt("user_id")
            );


            topicStatus.setTopicId(
                    resultSet.getInt("topic_id")
            );


            topicStatus.setStatus(
                    resultSet.getString("status")
            );


            topicStatuses.add(
                    topicStatus
            );
        }


    } catch (Exception e) {

        System.out.println(
                e.getMessage()
        );

    }


    return topicStatuses;
}
    
    // UPDATE TOPIC STATUS
    

    public boolean updateTopicStatus(
            TopicStatus userTopic) {

        boolean isUpdated = false;

        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "UPDATE user_topics " +
                    "SET status=? " +
                    "WHERE user_id=? " +
                    "AND topic_id=?";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            preparedStatement.setString(
                    1,
                    userTopic.getStatus()
            );


            preparedStatement.setInt(
                    2,
                    userTopic.getUserId()
            );


            preparedStatement.setInt(
                    3,
                    userTopic.getTopicId()
            );


            int rowsAffected =
                    preparedStatement.executeUpdate();


            if(rowsAffected > 0) {

                isUpdated = true;

            }

        } catch(Exception e) {

            System.out.println(e.getMessage());

        }


        return isUpdated;
    }


    
    public boolean userTopicExists(
            int userId,
            int topicId) {

        boolean exists = false;

        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "SELECT id FROM user_topics " +
                    "WHERE user_id=? " +
                    "AND topic_id=?";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            preparedStatement.setInt(
                    1,
                    userId
            );


            preparedStatement.setInt(
                    2,
                    topicId
            );


            ResultSet resultSet =
                    preparedStatement.executeQuery();


            if(resultSet.next()) {

                exists = true;

            }

        } catch(Exception e) {

            System.out.println(e.getMessage());

        }


        return exists;
    }


    

    public boolean createUserTopic(
            int userId,
            int topicId) {

        boolean isCreated = false;

        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "INSERT INTO user_topics " +
                    "(user_id, topic_id, status) " +
                    "VALUES (?, ?, ?)";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            preparedStatement.setInt(
                    1,
                    userId
            );


            preparedStatement.setInt(
                    2,
                    topicId
            );


            preparedStatement.setString(
                    3,
                    "pending"
            );


            int rowsAffected =
                    preparedStatement.executeUpdate();


            if(rowsAffected > 0) {

                isCreated = true;

            }

        } catch(Exception e) {

            System.out.println(e.getMessage());

        }


        return isCreated;
    }


    

    public int getTotalTopics() {

        int totalTopics = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "SELECT COUNT(*) FROM topics";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            ResultSet resultSet =
                    preparedStatement.executeQuery();


            if(resultSet.next()) {

                totalTopics =
                        resultSet.getInt(1);

            }

        } catch(Exception e) {

            System.out.println(e.getMessage());

        }


        return totalTopics;
    }




    public int getCompletedTopics(
            int userId) {

        int completedTopics = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "SELECT COUNT(*) " +
                    "FROM user_topics " +
                    "WHERE user_id=? " +
                    "AND status=?";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            preparedStatement.setInt(
                    1,
                    userId
            );


            preparedStatement.setString(
                    2,
                    "completed"
            );


            ResultSet resultSet =
                    preparedStatement.executeQuery();


            if(resultSet.next()) {

                completedTopics =
                        resultSet.getInt(1);

            }

        } catch(Exception e) {

            System.out.println(e.getMessage());

        }


        return completedTopics;
    }


    
    public int getPendingTopics(
            int userId) {

        int pendingTopics = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "SELECT COUNT(*) " +
                    "FROM topics t " +
                    "LEFT JOIN user_topics ut " +
                    "ON t.id = ut.topic_id " +
                    "AND ut.user_id=? " +
                    "WHERE ut.status IS NULL " +
                    "OR ut.status=?";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            preparedStatement.setInt(
                    1,
                    userId
            );


            preparedStatement.setString(
                    2,
                    "pending"
            );


            ResultSet resultSet =
                    preparedStatement.executeQuery();


            if(resultSet.next()) {

                pendingTopics =
                        resultSet.getInt(1);

            }

        } catch(Exception e) {

            System.out.println(e.getMessage());

        }


        return pendingTopics;
    }


    public double getProgressPercentage(
            int userId) {

        int totalTopics =
                getTotalTopics();


        int completedTopics =
                getCompletedTopics(userId);


        if(totalTopics == 0) {

            return 0;

        }


        return
            ((double) completedTopics
            / totalTopics) * 100;
    }

}