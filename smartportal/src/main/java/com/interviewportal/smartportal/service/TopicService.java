package com.interviewportal.smartportal.service;

import com.interviewportal.smartportal.dao.TopicDao;
import com.interviewportal.smartportal.model.Topic;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

   @Autowired
   TopicDao topicDao;

    

    public List<Topic> getAllTopics() {  
 
    return topicDao.getAllTopics();

}




public List<Topic> searchTopics(String keyword) { 

    return topicDao.searchTopics(keyword);

}
}