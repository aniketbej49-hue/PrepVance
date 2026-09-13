package com.interviewportal.smartportal.Admin.model;

public class AdminTopic {

    private int id;

    private String topicName;




    public AdminTopic() {

    }


    public AdminTopic(String topicName) {

        this.topicName = topicName;

    

    }


    public int getId() {

        return id;

    }


    public void setId(int id) {

        this.id = id;

    }


    public String getTopicName() {

        return topicName;

    }


    public void setTopicName(String topicName) {

        this.topicName = topicName;

    }

    
}
