package com.interviewportal.smartportal.model;

public class TopicStatus {

    private int id;

    private int userId;

    private int topicId;

    private String status;


    public TopicStatus() {

    }


    public TopicStatus(
            int userId,
            int topicId,
            String status) {

        this.userId = userId;
        this.topicId = topicId;
        this.status = status;
    }


    public int getId() {

        return id;
    }


    public void setId(int id) {

        this.id = id;
    }


    public int getUserId() {

        return userId;
    }


    public void setUserId(int userId) {

        this.userId = userId;
    }


    public int getTopicId() {

        return topicId;
    }


    public void setTopicId(int topicId) {

        this.topicId = topicId;
    }


    public String getStatus() {

        return status;
    }


    public void setStatus(String status) {

        this.status = status;
    }
}