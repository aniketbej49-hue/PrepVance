package com.interviewportal.smartportal.model;

import java.util.List;

public class ResumeAnalysisResult {

    private CandidateProfile candidateProfile;
    private SkillAnalysisResult skills;
    private ExperienceAnalysisResult experience;
    private EducationAnalysisResult education;
    private ProjectAnalysisResult projects;
    private List<String> certifications;
    private ATSScoreResult atsScore;
    private FeedbackResult feedback;
    private JobMatchResult jobMatch;

    public ResumeAnalysisResult() {
    }

    public CandidateProfile getCandidateProfile() {
        return candidateProfile;
    }

    public void setCandidateProfile(CandidateProfile candidateProfile) {
        this.candidateProfile = candidateProfile;
    }

    public SkillAnalysisResult getSkills() {
        return skills;
    }

    public void setSkills(SkillAnalysisResult skills) {
        this.skills = skills;
    }

    public ExperienceAnalysisResult getExperience() {
        return experience;
    }

    public void setExperience(ExperienceAnalysisResult experience) {
        this.experience = experience;
    }

    public EducationAnalysisResult getEducation() {
        return education;
    }

    public void setEducation(EducationAnalysisResult education) {
        this.education = education;
    }

    public ProjectAnalysisResult getProjects() {
        return projects;
    }

    public void setProjects(ProjectAnalysisResult projects) {
        this.projects = projects;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }

    public ATSScoreResult getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(ATSScoreResult atsScore) {
        this.atsScore = atsScore;
    }

    public FeedbackResult getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedbackResult feedback) {
        this.feedback = feedback;
    }

    public JobMatchResult getJobMatch() {
        return jobMatch;
    }

    public void setJobMatch(JobMatchResult jobMatch) {
        this.jobMatch = jobMatch;
    }
}