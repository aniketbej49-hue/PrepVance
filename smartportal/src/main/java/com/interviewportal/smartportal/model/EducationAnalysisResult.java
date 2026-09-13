package com.interviewportal.smartportal.model;

import java.util.List;

public class EducationAnalysisResult {

    private List<EducationEntry> entries;

    public EducationAnalysisResult() {
    }

    public List<EducationEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<EducationEntry> entries) {
        this.entries = entries;
    }
}