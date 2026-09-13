package com.interviewportal.smartportal.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interviewportal.smartportal.dao.OtpDao;
import com.interviewportal.smartportal.dao.UserDao;
import com.interviewportal.smartportal.model.User;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserDao userDAO;

    @Autowired
    private OtpDao otpDAO;

    @Autowired
    private EmailService emailService;
    private Set<String> verifiedEmails = new HashSet<>();


    public boolean registerUser(User user) {

        return userDAO.registerUser(user);
    }


    public int loginUser(User user) {

        return userDAO.loginUser(user);
    }


    public String sendOtp(String email) {

    
    boolean exists = userDAO.emailExists(email);

    System.out.println("Email entered: " + email);
    System.out.println("Email exists: " + exists);

    if (!exists) {
        return "Email not registered";
    }

   
    String otp = String.valueOf(
            (int) (Math.random() * 900000) + 100000
    );

    
    LocalDateTime expiryTime =
            LocalDateTime.now().plusMinutes(5);

    
    otpDAO.saveOtp(email, otp, expiryTime);

  
    emailService.sendOtp(email, otp);

    return "OTP sent successfully";
}

public boolean verifyOtp(String email, String otp) {

    boolean verified = otpDAO.verifyOtp(email, otp);

    if (verified) {

        verifiedEmails.add(email);

        return true;
    }

    return false;
}

public boolean resetPassword(String email, String newPassword) {

    if (!verifiedEmails.contains(email)) {

        return false;
    }

    boolean updated =
            userDAO.resetPassword(email, newPassword);

    if (updated) {

        verifiedEmails.remove(email);
    }

    return updated;
}
}