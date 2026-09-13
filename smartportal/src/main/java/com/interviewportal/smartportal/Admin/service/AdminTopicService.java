package com.interviewportal.smartportal.Admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interviewportal.smartportal.Admin.dao.AdminTopicDao;
import com.interviewportal.smartportal.Admin.model.AdminTopic;

@Service
public class AdminTopicService {

   
@Autowired
private AdminTopicDao adminTopicDao;
    public boolean addTopic(AdminTopic adminTopic) { 

        return adminTopicDao.addTopic(adminTopic);

    }

    public List<AdminTopic> getAllTopics() {

        return adminTopicDao.getAllTopics();

    }


    
public boolean deleteTopic(int id) {     

    return adminTopicDao.deleteTopic(id);

}

public List<AdminTopic> searchTopics(String admintopic_keyword) { 

    return adminTopicDao.searchTopics(admintopic_keyword);

}
    
}
