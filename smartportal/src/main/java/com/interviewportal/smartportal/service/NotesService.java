package com.interviewportal.smartportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interviewportal.smartportal.dao.NotesDao;
import com.interviewportal.smartportal.model.Notes;

@Service
public class NotesService {

    @Autowired
    private NotesDao notesDao;




    public List<Notes> getAllNotes() {

        return notesDao.getAllNotes();

    }

}