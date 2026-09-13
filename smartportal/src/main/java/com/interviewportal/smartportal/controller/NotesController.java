package com.interviewportal.smartportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interviewportal.smartportal.model.Notes;
import com.interviewportal.smartportal.service.NotesService;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;



    @GetMapping("/all")
    public List<Notes> getAllNotes() {

        return notesService.getAllNotes();

    }

}