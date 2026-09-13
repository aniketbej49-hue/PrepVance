package com.interviewportal.smartportal.service;

import com.interviewportal.smartportal.dao.ResumeDao;
import com.interviewportal.smartportal.model.Resume;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {

    private final ResumeDao resumeDao;


    
    public ResumeService(ResumeDao resumeDao) {

        this.resumeDao = resumeDao;
    }


    

    public boolean saveResume(Resume resume) {

        return resumeDao.saveResume(resume);
    }


    

    public Resume getLatestResume(String email) {

        return resumeDao.getLatestResume(email);
    }
}