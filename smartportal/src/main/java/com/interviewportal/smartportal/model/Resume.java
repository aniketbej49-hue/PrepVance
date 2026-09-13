package com.interviewportal.smartportal.model;

import java.time.LocalDateTime;

public class Resume {

    private int id;
    private String email;
    private String fileName;
    private double atsScore;
    private LocalDateTime createdAt;


    
    public Resume() {
    }


    
    public Resume(String email, String fileName) {

        this.email = email;
        this.fileName = fileName;
    }


    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public double getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(double atsScore) {
        this.atsScore = atsScore;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}