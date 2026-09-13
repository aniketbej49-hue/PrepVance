package com.interviewportal.smartportal.model;

import java.util.List;

public class ExperienceAnalysisResult {

    private double totalYears;
    private List<ExperienceEntry> entries;

    public ExperienceAnalysisResult() {
    }

    public double getTotalYears() {
        return totalYears;
    }

    public void setTotalYears(double totalYears) {
        this.totalYears = totalYears;
    }

    public List<ExperienceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ExperienceEntry> entries) {
        this.entries = entries;
    }
}