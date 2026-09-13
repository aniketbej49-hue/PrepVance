package com.interviewportal.smartportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.interviewportal.smartportal.database.DBConnection;
import com.interviewportal.smartportal.model.Notes;

@Repository
public class NotesDao {


    

    public List<Notes> getAllNotes() {

        List<Notes> notesList =
                new ArrayList<>();


        try {

            Connection connection =
                    DBConnection.getConnection();


            String query =
                    "SELECT id, topic_id, title, " +
                    "file_name, file_path " +
                    "FROM notes";


            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);


            ResultSet resultSet =
                    preparedStatement.executeQuery();


            while (resultSet.next()) {

                Notes notes =
                        new Notes();


                notes.setId(
                        resultSet.getInt("id")
                );


                notes.setTopicId(
                        resultSet.getInt("topic_id")
                );


                notes.setTitle(
                        resultSet.getString("title")
                );


                notes.setFileName(
                        resultSet.getString("file_name")
                );


                notes.setFilePath(
                        resultSet.getString("file_path")
                );


                notesList.add(notes);

            }

        } catch (Exception e) {

            System.out.println(
                    "Get All Notes Error: "
                    + e.getMessage()
            );

        }


        return notesList;

    }
}