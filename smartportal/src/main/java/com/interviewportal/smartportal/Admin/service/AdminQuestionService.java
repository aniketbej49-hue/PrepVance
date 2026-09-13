package com.interviewportal.smartportal.Admin.service;

import com.interviewportal.smartportal.Admin.dao.AdminQuestionDao;
import com.interviewportal.smartportal.Admin.model.AdminQuestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminQuestionService {

      @Autowired
    private AdminQuestionDao adminQuestionDao;
            


    public boolean addQuestion(
            AdminQuestion question) {

        return adminQuestionDao.addQuestion(
                question
        );
    }


    

    public List<AdminQuestion> getAllQuestions() {

        return adminQuestionDao.getAllQuestions();

    }


    

    public List<AdminQuestion> searchQuestions(
            String keyword) {

        return adminQuestionDao.searchQuestions(
                keyword
        );

    }


    
    public boolean updateQuestion(
            AdminQuestion question) {

        return adminQuestionDao.updateQuestion(
                question
        );

    }


   

    public boolean deleteQuestion(int id) {

        return adminQuestionDao.deleteQuestion(
                id
        );

    }
}