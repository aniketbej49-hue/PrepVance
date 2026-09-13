package com.interviewportal.smartportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.interviewportal.smartportal.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;


    

    @GetMapping("/total-topics")
    public int getTotalTopics() {

        return dashboardService.getTotalTopics();

    }


    

    @GetMapping("/completed-topics/{userId}")
    public int getCompletedTopics(
            @PathVariable int userId) {

        return dashboardService.getCompletedTopics(userId);

    }


    

    @GetMapping("/pending-topics/{userId}")
    public int getPendingTopics(
            @PathVariable int userId) {

        return dashboardService.getPendingTopics(userId);

    }


    

    @GetMapping("/progress/{userId}")
    public double getProgressPercentage(
            @PathVariable int userId) {

        return dashboardService.getProgressPercentage(userId);

    }

}