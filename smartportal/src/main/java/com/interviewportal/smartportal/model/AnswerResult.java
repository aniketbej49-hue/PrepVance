package com.interviewportal.smartportal.model;

public class AnswerResult {

    private boolean correct;
    private String correctOption;
    private String explanation;


    public AnswerResult() {
    }


    public AnswerResult(
            boolean correct,
            String correctOption,
            String explanation) {

        this.correct = correct;
        this.correctOption = correctOption;
        this.explanation = explanation;
    }


    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }


    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }


    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
