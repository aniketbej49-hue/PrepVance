package com.interviewportal.smartportal.model;

public class Notes {

    private int id;

    private int topicId;

    private String title;

    private String fileName;

    private String filePath;


    

    public Notes() {

    }


   

    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }


    

    public int getTopicId() {

        return topicId;

    }

    public void setTopicId(int topicId) {

        this.topicId = topicId;

    }


   

    public String getTitle() {

        return title;

    }

    public void setTitle(String title) {

        this.title = title;

    }


   

    public String getFileName() {

        return fileName;

    }

    public void setFileName(String fileName) {

        this.fileName = fileName;

    }


    
    public String getFilePath() {

        return filePath;

    }

    public void setFilePath(String filePath) {

        this.filePath = filePath;

    }

}