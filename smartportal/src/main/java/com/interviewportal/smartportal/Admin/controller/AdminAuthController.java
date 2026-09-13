package com.interviewportal.smartportal.Admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interviewportal.smartportal.Admin.model.Admin;
import com.interviewportal.smartportal.Admin.service.AdminAuthService;
import com.interviewportal.smartportal.service.AuthService;

@RestController
@RequestMapping("/authentication")
public class AdminAuthController {

    @Autowired
    private AdminAuthService adminAuthService;

    @PostMapping("/register_admin")
    public String registerUser(@RequestBody Admin admin) {

        boolean isRegistered =
                adminAuthService.registerUser(admin);

        if(isRegistered) {

            return "Admin Registered Successfully";

        } else {

            return "Registration Failed";

        }
    }

    @PostMapping("/login_admin")
  public String loginUser(@RequestBody Admin admin) {

    boolean isValidUser =
            adminAuthService.loginUser(admin);

    if(isValidUser) {

        return "Login Successful";

    } else {

        return "Invalid Email or Password";

    }
}

    
}
