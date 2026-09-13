package com.interviewportal.smartportal.controller;

import com.interviewportal.smartportal.model.User;
import com.interviewportal.smartportal.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

      @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {

        boolean isRegistered =
                authService.registerUser(user);

        if(isRegistered) {

            return "User Registered Successfully";

        } else {

            return "Registration Failed";

        }
    }
@PostMapping("/login")
public String loginUser(@RequestBody User user) {

    int userId =
            authService.loginUser(user);

    if(userId != -1) {

        return String.valueOf(userId);

    } else {

        return "Invalid Email or Password";

    }
}
@PostMapping("/send-otp")
public String sendOtp(@RequestParam String email) {

    String otp = authService.sendOtp(email);

    return otp;
}

@PostMapping("/verify-otp")
public String verifyOtp(
        @RequestParam String email,
        @RequestParam String otp) {

    boolean verified = authService.verifyOtp(email, otp);

    if (verified) {
        return "OTP verified successfully";
    }

    return "Invalid or expired OTP";
}

@PostMapping("/reset-password")
public String resetPassword(
        @RequestParam String email,
        @RequestParam String newPassword) {

    boolean updated =
            authService.resetPassword(email, newPassword);

    if (updated) {
        return "Password reset successfully";
    }

    return "Password reset failed";
}
}