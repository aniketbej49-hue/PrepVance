package com.interviewportal.smartportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interviewportal.smartportal.dao.TopicStatusDao;

@Service 
public class DashboardService {

    @Autowired 
    TopicStatusDao topicDao;

    
    public int getTotalTopics() {

        return topicDao.getTotalTopics();
    }


    public int getCompletedTopics(
            int userId) {

        return topicDao.getCompletedTopics(
                userId
        );
    }


    public int getPendingTopics(
            int userId) {

        return topicDao.getPendingTopics(
                userId
        );
    }


    public double getProgressPercentage(
            int userId) {

        return topicDao.getProgressPercentage(
                userId
        );
    }

}