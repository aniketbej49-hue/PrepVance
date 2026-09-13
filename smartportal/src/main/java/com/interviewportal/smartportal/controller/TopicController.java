package com.interviewportal.smartportal.controller;

import com.interviewportal.smartportal.model.Topic;
import com.interviewportal.smartportal.service.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    TopicService topicService;


    

    @GetMapping("/all")
 public List<Topic> getAllTopics() {

    return topicService.getAllTopics();

}




@GetMapping("/search")
public List<Topic> searchTopics(
        @RequestParam String keyword) {

    return topicService.searchTopics(keyword);

}

}