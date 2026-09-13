package com.interviewportal.smartportal.Admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interviewportal.smartportal.Admin.model.AdminTopic;
import com.interviewportal.smartportal.Admin.service.AdminTopicService;

@RestController
@RequestMapping("/admin_topic")
public class AdminTopicController {

    @Autowired
    private AdminTopicService adminTopicService;
    
    @PostMapping("/add_topic")
    public String addTopic(@RequestBody AdminTopic adminTopic) {

        boolean isAdded =
                adminTopicService.addTopic(adminTopic);

        if(isAdded) {

            return "Topic Added Successfully";

        } else {

            return "Failed To Add Topic";

        }
    }

    @GetMapping("/get_topic")
public List<AdminTopic> getAllTopics() {

        return adminTopicService.getAllTopics();

    }


@DeleteMapping("/delete_topic/{id}")
public String deleteTopic(
        @PathVariable int id) {

    boolean isDeleted =
            adminTopicService.deleteTopic(id);

    if(isDeleted) {

        return "Topic Deleted Successfully";

    } else {

        return "Failed To Delete Topic";

    }
}
@GetMapping("/search_topic")
public List<AdminTopic> searchTopics(
        @RequestParam String keyword) {

    return adminTopicService.searchTopics(keyword);

}

}
