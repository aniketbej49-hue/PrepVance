package com.interviewportal.smartportal.Admin.dao;

import com.interviewportal.smartportal.database.DBConnection;
import com.interviewportal.smartportal.Admin.model.AdminQuestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class AdminQuestionDao {


    

    public boolean addQuestion(
            AdminQuestion question) {

        boolean isAdded = false;

        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "INSERT INTO questions " +
                    "(topic_id, question, option_a, option_b, " +
                    "option_c, option_d, correct_option, explanation) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            preparedStatement.setInt(
                    1,
                    question.getTopicId()
            );

            preparedStatement.setString(
                    2,
                    question.getQuestion()
            );

            preparedStatement.setString(
                    3,
                    question.getOptionA()
            );

            preparedStatement.setString(
                    4,
                    question.getOptionB()
            );

            preparedStatement.setString(
                    5,
                    question.getOptionC()
            );

            preparedStatement.setString(
                    6,
                    question.getOptionD()
            );

            preparedStatement.setString(
                    7,
                    question.getCorrectOption()
            );

            preparedStatement.setString(
                    8,
                    question.getExplanation()
            );


            int rowsAffected =
                    preparedStatement.executeUpdate();


            if (rowsAffected > 0) {

                isAdded = true;

            }

        } catch (Exception e) {

            System.out.println(
                    "Add Question Error: "
                    + e.getMessage()
            );

        }

        return isAdded;
    }




    public List<AdminQuestion> getAllQuestions() {

        List<AdminQuestion> questionList =
                new ArrayList<>();


        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "SELECT * FROM questions";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            ResultSet resultSet =
                    preparedStatement.executeQuery();


            while (resultSet.next()) {

                AdminQuestion question =
                        new AdminQuestion();


                question.setId(
                        resultSet.getInt("id")
                );


                question.setTopicId(
                        resultSet.getInt("topic_id")
                );


                question.setQuestion(
                        resultSet.getString("question")
                );


                question.setOptionA(
                        resultSet.getString("option_a")
                );


                question.setOptionB(
                        resultSet.getString("option_b")
                );


                question.setOptionC(
                        resultSet.getString("option_c")
                );


                question.setOptionD(
                        resultSet.getString("option_d")
                );


                question.setCorrectOption(
                        resultSet.getString("correct_option")
                );


                question.setExplanation(
                        resultSet.getString("explanation")
                );


                questionList.add(question);

            }

        } catch (Exception e) {

            System.out.println(
                    "Get Questions Error: "
                    + e.getMessage()
            );

        }

        return questionList;
    }



    public List<AdminQuestion> searchQuestions(
            String keyword) {

        List<AdminQuestion> questionList =
                new ArrayList<>();


        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "SELECT * FROM questions " +
                    "WHERE question LIKE ?";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            preparedStatement.setString(
                    1,
                    "%" + keyword + "%"
            );


            ResultSet resultSet =
                    preparedStatement.executeQuery();


            while (resultSet.next()) {

                AdminQuestion question =
                        new AdminQuestion();


                question.setId(
                        resultSet.getInt("id")
                );


                question.setTopicId(
                        resultSet.getInt("topic_id")
                );


                question.setQuestion(
                        resultSet.getString("question")
                );


                question.setOptionA(
                        resultSet.getString("option_a")
                );


                question.setOptionB(
                        resultSet.getString("option_b")
                );


                question.setOptionC(
                        resultSet.getString("option_c")
                );


                question.setOptionD(
                        resultSet.getString("option_d")
                );


                question.setCorrectOption(
                        resultSet.getString("correct_option")
                );


                question.setExplanation(
                        resultSet.getString("explanation")
                );


                questionList.add(question);

            }

        } catch (Exception e) {

            System.out.println(
                    "Search Questions Error: "
                    + e.getMessage()
            );

        }

        return questionList;
    }


    public boolean updateQuestion(
            AdminQuestion question) {

        boolean isUpdated = false;


        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "UPDATE questions SET " +
                    "topic_id=?, " +
                    "question=?, " +
                    "option_a=?, " +
                    "option_b=?, " +
                    "option_c=?, " +
                    "option_d=?, " +
                    "correct_option=?, " +
                    "explanation=? " +
                    "WHERE id=?";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            preparedStatement.setInt(
                    1,
                    question.getTopicId()
            );

            preparedStatement.setString(
                    2,
                    question.getQuestion()
            );

            preparedStatement.setString(
                    3,
                    question.getOptionA()
            );

            preparedStatement.setString(
                    4,
                    question.getOptionB()
            );

            preparedStatement.setString(
                    5,
                    question.getOptionC()
            );

            preparedStatement.setString(
                    6,
                    question.getOptionD()
            );

            preparedStatement.setString(
                    7,
                    question.getCorrectOption()
            );

            preparedStatement.setString(
                    8,
                    question.getExplanation()
            );

            preparedStatement.setInt(
                    9,
                    question.getId()
            );


            int rowsAffected =
                    preparedStatement.executeUpdate();


            if (rowsAffected > 0) {

                isUpdated = true;

            }

        } catch (Exception e) {

            System.out.println(
                    "Update Question Error: "
                    + e.getMessage()
            );

        }

        return isUpdated;
    }




    public boolean deleteQuestion(int id) {

        boolean isDeleted = false;


        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "DELETE FROM questions WHERE id=?";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            preparedStatement.setInt(
                    1,
                    id
            );


            int rowsAffected =
                    preparedStatement.executeUpdate();


            if (rowsAffected > 0) {

                isDeleted = true;

            }

        } catch (Exception e) {

            System.out.println(
                    "Delete Question Error: "
                    + e.getMessage()
            );

        }

        return isDeleted;
    }
}