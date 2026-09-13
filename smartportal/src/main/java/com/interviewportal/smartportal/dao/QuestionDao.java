package com.interviewportal.smartportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.interviewportal.smartportal.database.DBConnection;
import com.interviewportal.smartportal.model.AnswerResult;
import com.interviewportal.smartportal.model.Question;

@Repository
public class QuestionDao {

    public List<Question> getQuestionsByTopic(int topicId) {

        List<Question> questionList = new ArrayList<>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT id, topic_id, question, " +
                    "option_a, option_b, option_c, option_d " +
                    "FROM questions " +
                    "WHERE topic_id=?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, topicId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while (resultSet.next()) {

                Question question =
                        new Question();

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

                questionList.add(question);
            }

        } catch (Exception e) {

            System.out.println(
                    "Get Questions By Topic Error: "
                    + e.getMessage()
            );
        }

        return questionList;
    }

    public AnswerResult checkAnswer(
        int questionId,
        String selectedOption) {

    AnswerResult result = null;

    try {

        Connection connection =
                DBConnection.getConnection();

        String query =
                "SELECT correct_option, explanation " +
                "FROM questions " +
                "WHERE id=?";

        PreparedStatement preparedStatement =
                connection.prepareStatement(query);

        preparedStatement.setInt(1, questionId);

        ResultSet resultSet =
                preparedStatement.executeQuery();


        if (resultSet.next()) {

            String correctOption =
                    resultSet.getString("correct_option");

            String explanation =
                    resultSet.getString("explanation");


            boolean isCorrect =
                    correctOption.equalsIgnoreCase(
                            selectedOption
                    );


            result = new AnswerResult(
                    isCorrect,
                    correctOption,
                    explanation
            );
        }

    } catch (Exception e) {

        System.out.println(
                "Check Answer Error: "
                + e.getMessage()
        );
    }

    return result;
}
}