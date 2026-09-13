package com.interviewportal.smartportal.Admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interviewportal.smartportal.Admin.model.AdminQuestion;
import com.interviewportal.smartportal.Admin.service.AdminQuestionService;


@RestController
@RequestMapping("/admin_question")
public class AdminQuestionController {


    @Autowired
    private AdminQuestionService adminQuestionService;




    @PostMapping("/add_question")
    public String addQuestion(
            @RequestBody AdminQuestion question) {


        boolean isAdded =
                adminQuestionService.addQuestion(
                        question
                );


        if (isAdded) {

            return "Question Added Successfully";

        }


        return "Failed To Add Question";
    }




    @GetMapping("/get_questions")
    public List<AdminQuestion> getAllQuestions() {

        return adminQuestionService
                .getAllQuestions();

    }




    @GetMapping("/search_questions")
    public List<AdminQuestion> searchQuestions(
            @RequestParam String keyword) {


        return adminQuestionService
                .searchQuestions(keyword);

    }




    @PutMapping("/update_question")
    public String updateQuestion(
            @RequestBody AdminQuestion question) {


        boolean isUpdated =
                adminQuestionService.updateQuestion(
                        question
                );


        if (isUpdated) {

            return "Question Updated Successfully";

        }


        return "Failed To Update Question";
    }




    @DeleteMapping("/delete_question/{id}")
    public String deleteQuestion(
            @PathVariable int id) {


        boolean isDeleted =
                adminQuestionService.deleteQuestion(
                        id
                );


        if (isDeleted) {

            return "Question Deleted Successfully";

        }


        return "Failed To Delete Question";
    }

}