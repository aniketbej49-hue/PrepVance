package com.interviewportal.smartportal.model;

import java.util.List;

public class ProjectAnalysisResult {

    private List<ProjectEntry> entries;

    public ProjectAnalysisResult() {
    }

    public List<ProjectEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ProjectEntry> entries) {
        this.entries = entries;
    }
}