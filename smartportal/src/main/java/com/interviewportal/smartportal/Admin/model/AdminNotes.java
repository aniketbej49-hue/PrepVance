package com.interviewportal.smartportal.Admin.model;

public class AdminNotes {

    private int id;

    private int topicId;

    private String title;

    private String fileName;

    private String filePath;


    
    public AdminNotes() {

    }


    public AdminNotes(
            int topicId,
            String title,
            String fileName,
            String filePath) {

        this.topicId = topicId;
        this.title = title;
        this.fileName = fileName;
        this.filePath = filePath;

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