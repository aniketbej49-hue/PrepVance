package com.interviewportal.smartportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interviewportal.smartportal.dao.TopicStatusDao;
import com.interviewportal.smartportal.model.TopicStatus;


@Service
public class TopicStatusService {


    @Autowired
    private TopicStatusDao userTopicDao;


    public List<TopicStatus> getUserTopicStatuses(
        int userId) {

    return userTopicDao.getUserTopicStatuses(
            userId
    );
}
    
    // UPDATE TOPIC STATUS
    

    public boolean updateTopicStatus(
            TopicStatus userTopic) {


        boolean exists =
                userTopicDao.userTopicExists(
                        userTopic.getUserId(),
                        userTopic.getTopicId()
                );


        
        if(!exists) {

            boolean created =
                    userTopicDao.createUserTopic(
                            userTopic.getUserId(),
                            userTopic.getTopicId()
                    );


            if(!created) {

                return false;

            }
        }


      
        return userTopicDao.updateTopicStatus(
                userTopic
        );
    }


    
}