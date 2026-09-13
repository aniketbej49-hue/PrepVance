package com.interviewportal.smartportal.model;

import java.util.List;

public class FeedbackResult {

    private List<String> strengths;
    private List<String> weaknesses;
    private List<String> recommendations;
    private List<String> atsImprovements;

    public FeedbackResult() {
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }

    public List<String> getAtsImprovements() {
        return atsImprovements;
    }

    public void setAtsImprovements(List<String> atsImprovements) {
        this.atsImprovements = atsImprovements;
    }
}