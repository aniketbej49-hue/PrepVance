package com.interviewportal.smartportal.Admin.service;

import com.interviewportal.smartportal.Admin.model.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interviewportal.smartportal.Admin.dao.AdminDao;

@Service
public class AdminAuthService {

   @Autowired
   private AdminDao adminDAO;
   
    public boolean registerUser(Admin admin) {

        return adminDAO.registerUser(admin);
    }


    public boolean loginUser(Admin admin) {

        return adminDAO.loginUser(admin);
    }

    
}
