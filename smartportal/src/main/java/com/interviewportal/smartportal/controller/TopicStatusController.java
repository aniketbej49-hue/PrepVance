package com.interviewportal.smartportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.interviewportal.smartportal.model.TopicStatus;
import com.interviewportal.smartportal.service.TopicStatusService;


@RestController
@RequestMapping("/user_topics")
public class TopicStatusController {


    @Autowired
    private TopicStatusService userTopicService;


    @GetMapping("/{userId}")
public List<TopicStatus> getUserTopicStatuses(
        @PathVariable int userId) {

    return userTopicService.getUserTopicStatuses(
            userId
    );
}
   
    @PutMapping("/update_status")
    public String updateTopicStatus(
            @RequestBody TopicStatus userTopic) {


        boolean isUpdated =
                userTopicService.updateTopicStatus(
                        userTopic
                );


        if(isUpdated) {

            return "Topic Status Updated Successfully";

        } else {

            return "Failed To Update Topic Status";

        }
    }


}