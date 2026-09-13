package com.interviewportal.smartportal.Admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.interviewportal.smartportal.Admin.model.AdminNotes;
import com.interviewportal.smartportal.database.DBConnection;

@Repository
public class AdminNotesDao {




    public boolean addNote(AdminNotes adminNotes) {

        boolean isAdded = false;

        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "INSERT INTO notes(topic_id, title, file_name, file_path) " +
                    "VALUES (?, ?, ?, ?)";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            preparedStatement.setInt(
                    1,
                    adminNotes.getTopicId()
            );


            preparedStatement.setString(
                    2,
                    adminNotes.getTitle()
            );


            preparedStatement.setString(
                    3,
                    adminNotes.getFileName()
            );


            preparedStatement.setString(
                    4,
                    adminNotes.getFilePath()
            );


            int rowsAffected =
                    preparedStatement.executeUpdate();


            if (rowsAffected > 0) {

                isAdded = true;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return isAdded;
    }


    public List<AdminNotes> getAllNotes() {

        List<AdminNotes> notes =
                new ArrayList<>();


        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "SELECT id, topic_id, title, file_name, file_path " +
                    "FROM notes";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            ResultSet resultSet =
                    preparedStatement.executeQuery();


            while (resultSet.next()) {

                AdminNotes adminNotes =
                        new AdminNotes();


                adminNotes.setId(
                        resultSet.getInt("id")
                );


                adminNotes.setTopicId(
                        resultSet.getInt("topic_id")
                );


                adminNotes.setTitle(
                        resultSet.getString("title")
                );


                adminNotes.setFileName(
                        resultSet.getString("file_name")
                );


                adminNotes.setFilePath(
                        resultSet.getString("file_path")
                );


                notes.add(adminNotes);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }


        return notes;
    }




    public boolean updateNote(AdminNotes adminNotes) {

        boolean isUpdated = false;

        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "UPDATE notes SET topic_id=?, title=? " +
                    "WHERE id=?";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            preparedStatement.setInt(
                    1,
                    adminNotes.getTopicId()
            );


            preparedStatement.setString(
                    2,
                    adminNotes.getTitle()
            );


            preparedStatement.setInt(
                    3,
                    adminNotes.getId()
            );


            int rowsAffected =
                    preparedStatement.executeUpdate();


            if (rowsAffected > 0) {

                isUpdated = true;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }


        return isUpdated;
    }


    public boolean deleteNote(int id) {

        boolean isDeleted = false;

        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "DELETE FROM notes WHERE id=?";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            preparedStatement.setInt(1, id);


            int rowsAffected =
                    preparedStatement.executeUpdate();


            if (rowsAffected > 0) {

                isDeleted = true;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }


        return isDeleted;
    }

}