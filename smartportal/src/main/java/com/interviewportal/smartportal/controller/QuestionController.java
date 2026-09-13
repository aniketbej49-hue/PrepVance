package com.interviewportal.smartportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interviewportal.smartportal.model.AnswerRequest;
import com.interviewportal.smartportal.model.AnswerResult;
import com.interviewportal.smartportal.model.Question;
import com.interviewportal.smartportal.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/topic/{topicId}")
    public List<Question> getQuestionsByTopic(
            @PathVariable int topicId) {

        return questionService.getQuestionsByTopic(topicId);
    }

    @PostMapping("/check-answer")
public AnswerResult checkAnswer(
        @RequestBody AnswerRequest answerRequest) {

    return questionService.checkAnswer(
            answerRequest.getQuestionId(),
            answerRequest.getSelectedOption()
    );
}
}