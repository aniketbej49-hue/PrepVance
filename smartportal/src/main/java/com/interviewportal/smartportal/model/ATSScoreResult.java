package com.interviewportal.smartportal.model;

public class ATSScoreResult {

    private int totalScore;
    private int keywordMatchScore;
    private int skillsRelevanceScore;
    private int experienceRelevanceScore;
    private int educationRelevanceScore;
    private int sectionQualityScore;
    private int formattingScore;
    private int readabilityScore;

    public ATSScoreResult() {
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getKeywordMatchScore() {
        return keywordMatchScore;
    }

    public void setKeywordMatchScore(int keywordMatchScore) {
        this.keywordMatchScore = keywordMatchScore;
    }

    public int getSkillsRelevanceScore() {
        return skillsRelevanceScore;
    }

    public void setSkillsRelevanceScore(int skillsRelevanceScore) {
        this.skillsRelevanceScore = skillsRelevanceScore;
    }

    public int getExperienceRelevanceScore() {
        return experienceRelevanceScore;
    }

    public void setExperienceRelevanceScore(int experienceRelevanceScore) {
        this.experienceRelevanceScore = experienceRelevanceScore;
    }

    public int getEducationRelevanceScore() {
        return educationRelevanceScore;
    }

    public void setEducationRelevanceScore(int educationRelevanceScore) {
        this.educationRelevanceScore = educationRelevanceScore;
    }

    public int getSectionQualityScore() {
        return sectionQualityScore;
    }

    public void setSectionQualityScore(int sectionQualityScore) {
        this.sectionQualityScore = sectionQualityScore;
    }

    public int getFormattingScore() {
        return formattingScore;
    }

    public void setFormattingScore(int formattingScore) {
        this.formattingScore = formattingScore;
    }

    public int getReadabilityScore() {
        return readabilityScore;
    }

    public void setReadabilityScore(int readabilityScore) {
        this.readabilityScore = readabilityScore;
    }
}