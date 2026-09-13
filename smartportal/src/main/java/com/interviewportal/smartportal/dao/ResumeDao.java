package com.interviewportal.smartportal.dao;

import com.interviewportal.smartportal.database.DBConnection;
import com.interviewportal.smartportal.model.Resume;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

@Repository
public class ResumeDao {


    

    public boolean saveResume(Resume resume) {

        String sql = """
                INSERT INTO Resume_Analyzer
                (email, file_name, ats_score)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, resume.getEmail());

            statement.setString(2, resume.getFileName());

            statement.setDouble(3, resume.getAtsScore());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }


   

    public Resume getLatestResume(String email) {

        String sql = """
                SELECT id, email, file_name, ats_score, created_at
                FROM Resume_Analyzer
                WHERE email = ?
                ORDER BY id DESC
                LIMIT 1
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Resume resume = new Resume();

                resume.setId(
                        resultSet.getInt("id")
                );

                resume.setEmail(
                        resultSet.getString("email")
                );

                resume.setFileName(
                        resultSet.getString("file_name")
                );

                resume.setAtsScore(
                        resultSet.getDouble("ats_score")
                );

                resume.setCreatedAt(
                        resultSet.getObject(
                                "created_at",
                                LocalDateTime.class
                        )
                );

                return resume;
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }
}