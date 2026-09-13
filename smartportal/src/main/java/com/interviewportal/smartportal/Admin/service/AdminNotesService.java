package com.interviewportal.smartportal.Admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interviewportal.smartportal.Admin.dao.AdminNotesDao;
import com.interviewportal.smartportal.Admin.model.AdminNotes;

@Service
public class AdminNotesService {

    @Autowired
    private AdminNotesDao adminNotesDao;


    

    public boolean addNote(AdminNotes adminNotes) {

        return adminNotesDao.addNote(adminNotes);

    }


    

    public List<AdminNotes> getAllNotes() {

        return adminNotesDao.getAllNotes();

    }


   

    public boolean updateNote(AdminNotes adminNotes) {

        return adminNotesDao.updateNote(adminNotes);

    }


    

    public boolean deleteNote(int id) {

        return adminNotesDao.deleteNote(id);

    }

}