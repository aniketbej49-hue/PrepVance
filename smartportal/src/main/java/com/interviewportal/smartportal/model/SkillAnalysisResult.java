package com.interviewportal.smartportal.model;

import java.util.List;

public class SkillAnalysisResult {

    private List<String> technicalSkills;
    private List<String> softSkills;
    private List<String> domainSkills;
    private List<String> toolsAndTechnologies;
    private List<String> recommendations;

    public SkillAnalysisResult() {
    }

    public List<String> getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(List<String> technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    public List<String> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(List<String> softSkills) {
        this.softSkills = softSkills;
    }

    public List<String> getDomainSkills() {
        return domainSkills;
    }

    public void setDomainSkills(List<String> domainSkills) {
        this.domainSkills = domainSkills;
    }

    public List<String> getToolsAndTechnologies() {
        return toolsAndTechnologies;
    }

    public void setToolsAndTechnologies(List<String> toolsAndTechnologies) {
        this.toolsAndTechnologies = toolsAndTechnologies;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }
}