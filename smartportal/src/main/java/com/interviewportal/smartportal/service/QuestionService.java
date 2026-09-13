package com.interviewportal.smartportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interviewportal.smartportal.dao.QuestionDao;
import com.interviewportal.smartportal.model.AnswerResult;
import com.interviewportal.smartportal.model.Question;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;


    public List<Question> getQuestionsByTopic(int topicId) {

        return questionDao.getQuestionsByTopic(topicId);
    }

    public AnswerResult checkAnswer(
        int questionId,
        String selectedOption) {

    return questionDao.checkAnswer(
            questionId,
            selectedOption
    );
}
}