package com.interviewportal.smartportal.Admin.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.interviewportal.smartportal.Admin.model.AdminNotes;
import com.interviewportal.smartportal.Admin.service.AdminNotesService;

@RestController
@RequestMapping("/admin_notes")
public class AdminNotesController {

    @Autowired
    private AdminNotesService adminNotesService;


    

    @PostMapping("/add_notes")
public String addNote(

        @RequestParam int topicId,

        @RequestParam String title,

        @RequestParam MultipartFile file) {

    try {

        // Check file
        if (file.isEmpty()) {

            return "Please select a PDF file";

        }


        // Check PDF
        if (!"application/pdf".equals(file.getContentType())) {

            return "Only PDF files are allowed";

        }


        

        Path uploadDirectory =
                Paths.get("uploads", "notes")
                .toAbsolutePath()
                .normalize();


        Files.createDirectories(uploadDirectory);


    

        String uniqueFileName =
                UUID.randomUUID().toString()
                + ".pdf";


    

        Path destination =
                uploadDirectory.resolve(uniqueFileName);


    

        file.transferTo(destination.toFile());


    

        String filePath =
                "/uploads/notes/" + uniqueFileName;


    

        AdminNotes adminNotes =
                new AdminNotes();

        adminNotes.setTopicId(topicId);

        adminNotes.setTitle(title);

        adminNotes.setFileName(
                file.getOriginalFilename()
        );

        adminNotes.setFilePath(filePath);


    

        boolean isAdded =
                adminNotesService.addNote(adminNotes);


        if (isAdded) {

            return "Note Added Successfully";

        } else {

            
            Files.deleteIfExists(destination);

            return "Failed To Add Note";

        }


    } catch (Exception e) {

        e.printStackTrace();

        return "Failed To Upload PDF";

    }
}




    @GetMapping("/get_notes")
    public List<AdminNotes> getAllNotes() {

        return adminNotesService.getAllNotes();

    }



    @PutMapping("/update_notes")
    public String updateNote(

            @RequestParam int id,

            @RequestParam int topicId,

            @RequestParam String title) {


        AdminNotes adminNotes =
                new AdminNotes();


        adminNotes.setId(id);

        adminNotes.setTopicId(topicId);

        adminNotes.setTitle(title);


        boolean isUpdated =
                adminNotesService.updateNote(adminNotes);


        if (isUpdated) {

            return "Note Updated Successfully";

        } else {

            return "Failed To Update Note";

        }
    }


    

    @DeleteMapping("/delete_notes/{id}")
    public String deleteNote(

            @PathVariable int id) {


        boolean isDeleted =
                adminNotesService.deleteNote(id);


        if (isDeleted) {

            return "Note Deleted Successfully";

        } else {

            return "Failed To Delete Note";

        }
    }

}