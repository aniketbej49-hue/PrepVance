package com.interviewportal.smartportal.model;

public class SectionAnalysisResult {

    private boolean profile;
    private boolean skills;
    private boolean education;
    private boolean projects;
    private boolean experience;
    private boolean certifications;


    public SectionAnalysisResult() {
    }


    public boolean isProfile() {
        return profile;
    }

    public void setProfile(boolean profile) {
        this.profile = profile;
    }


    public boolean isSkills() {
        return skills;
    }

    public void setSkills(boolean skills) {
        this.skills = skills;
    }


    public boolean isEducation() {
        return education;
    }

    public void setEducation(boolean education) {
        this.education = education;
    }


    public boolean isProjects() {
        return projects;
    }

    public void setProjects(boolean projects) {
        this.projects = projects;
    }


    public boolean isExperience() {
        return experience;
    }

    public void setExperience(boolean experience) {
        this.experience = experience;
    }


    public boolean isCertifications() {
        return certifications;
    }

    public void setCertifications(boolean certifications) {
        this.certifications = certifications;
    }
}