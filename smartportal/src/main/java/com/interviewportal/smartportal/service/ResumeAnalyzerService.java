package com.interviewportal.smartportal.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.interviewportal.smartportal.model.ResumeAnalysisResult;

import com.fasterxml.jackson.databind.ObjectMapper;



import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeAnalyzerService {

    private final Client geminiClient;
    private final ObjectMapper objectMapper;

    public ResumeAnalyzerService(@Value("${gemini.api.key}") String apiKey) {

        this.geminiClient = Client.builder()
                .apiKey(apiKey)
                .build();

        this.objectMapper = new ObjectMapper();
    }

  

    public String extractText(MultipartFile file) {

        try {

            byte[] fileBytes = file.getBytes();

            PDDocument document = Loader.loadPDF(fileBytes);

            PDFTextStripper stripper = new PDFTextStripper();

            String text = stripper.getText(document);

            document.close();

            return text;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }




public ResumeAnalysisResult analyzeResume(String text) {

    String prompt = buildResumeAnalysisPrompt(text);

    int maxAttempts = 3;

    for (int attempt = 1; attempt <= maxAttempts; attempt++) {

        try {

           
            GenerateContentResponse response =
                    geminiClient.models.generateContent(
                            "gemini-3.8-flash",
                            prompt,
                            null
                    );

            
            String jsonResponse = response.text();

            
            ResumeAnalysisResult result =
                    objectMapper.readValue(
                            jsonResponse,
                            ResumeAnalysisResult.class
                    );

            return result;

        } catch (Exception e) {

            String errorMessage = e.getMessage();

            System.out.println(
                    "Gemini Error - Attempt "
                    + attempt + " of " + maxAttempts
            );

            System.out.println(errorMessage);


           

            boolean isTemporaryError =
                    errorMessage != null &&
                    (
                        errorMessage.contains("503") ||
                        errorMessage.contains("UNAVAILABLE") ||
                        errorMessage.contains("high demand")
                    );


           

            if (isTemporaryError && attempt < maxAttempts) {

                try {

                    long waitTime = attempt * 2000L;

                    System.out.println(
                            "Gemini temporarily unavailable."
                    );

                    System.out.println(
                            "Retrying after "
                            + waitTime
                            + " milliseconds..."
                    );

                    Thread.sleep(waitTime);

                } catch (InterruptedException interruptedException) {

                    Thread.currentThread().interrupt();

                    throw new RuntimeException(
                            "Resume analysis was interrupted."
                    );
                }

            } else {

                e.printStackTrace();

                throw new RuntimeException(
                        "Resume analysis failed. "
                        + "Please try again later."
                );
            }
        }
    }

    throw new RuntimeException(
            "Resume analysis failed."
    );
}
    

    private String buildResumeAnalysisPrompt(String resumeText) {

        return """
                You are an intelligent resume analysis system.

                Analyze the following resume carefully.

                The candidate may be:

                - A fresher
                - An experienced professional
                - A career switcher
                - From engineering
                - From commerce
                - From arts
                - From pharmacy
                - From management
                - From any other academic or professional background

                Do NOT assume that the candidate is an engineering fresher.

                Do NOT use fixed requirements.

                Understand the resume based on the actual information present.

                Extract information dynamically.

                IMPORTANT RULES:

                1. Never invent information.
                2. If information is not present, use an empty string, empty list,
                   or appropriate zero value.
                3. Do not assume a degree.
                4. Do not assume a specific career path.
                5. Do not assume the candidate needs programming skills.
                6. Evaluate the resume according to the candidate's actual profile.
                7. For experienced candidates, analyze work experience properly.
                8. For freshers, analyze projects, education, internships,
                   certifications and skills appropriately.
                9. Projects are not mandatory for experienced candidates.
                10. Education requirements must depend on the actual candidate
                    and target role.
                11. Identify skills from the resume rather than using a fixed list.
                12. Identify technologies, tools and domain-specific skills
                    dynamically.
                13. Identify measurable achievements where available.
                14. Do not create fake achievements.
                15. Do not create fake companies, dates, degrees or skills.

                ATS ANALYSIS:

                Give an ATS score from 0 to 100.

                Evaluate the resume based on:

                - relevance and clarity
                - keyword usage
                - skills relevance
                - experience relevance
                - education relevance
                - section quality
                - formatting friendliness for ATS
                - readability

                The ATS score should be appropriate for the actual resume,
                not based on a fixed fresher formula.

                FEEDBACK:

                Identify:

                - strengths
                - weaknesses
                - practical recommendations
                - ATS improvements

                The recommendations must be based on the actual resume.

                Do not recommend technologies merely because they are popular.

                =========================================================
                REQUIRED JSON FORMAT
                =========================================================

                Return ONLY valid JSON.

                Do not include:

                - Markdown
                - ```json
                - explanations outside JSON
                - comments

                JSON structure:

                {
                  "candidateProfile": {
                    "experienceLevel": "",
                    "targetRole": "",
                    "domain": "",
                    "professionalSummary": ""
                  },

                  "skills": {
                    "technicalSkills": [],
                    "softSkills": [],
                    "domainSkills": [],
                    "toolsAndTechnologies": [],
                    "recommendations": []
                  },

                  "experience": {
                    "totalYears": 0,
                    "entries": [
                      {
                        "jobTitle": "",
                        "company": "",
                        "location": "",
                        "startDate": "",
                        "endDate": "",
                        "employmentType": "",
                        "responsibilities": [],
                        "achievements": []
                      }
                    ]
                  },

                  "education": {
                    "entries": [
                      {
                        "degree": "",
                        "fieldOfStudy": "",
                        "institution": "",
                        "location": "",
                        "startDate": "",
                        "endDate": "",
                        "grade": ""
                      }
                    ]
                  },

                  "projects": {
                    "entries": [
                      {
                        "title": "",
                        "description": "",
                        "technologies": [],
                        "startDate": "",
                        "endDate": "",
                        "role": "",
                        "achievements": []
                      }
                    ]
                  },

                  "certifications": [],

                  "atsScore": {
                    "totalScore": 0,
                    "keywordMatchScore": 0,
                    "skillsRelevanceScore": 0,
                    "experienceRelevanceScore": 0,
                    "educationRelevanceScore": 0,
                    "sectionQualityScore": 0,
                    "formattingScore": 0,
                    "readabilityScore": 0
                  },

                  "feedback": {
                    "strengths": [],
                    "weaknesses": [],
                    "recommendations": [],
                    "atsImprovements": []
                  },

                  "jobMatch": {
                    "matchScore": 0,
                    "matchedSkills": [],
                    "missingSkills": [],
                    "matchedKeywords": [],
                    "missingKeywords": [],
                    "recommendations": []
                  }
                }

                =========================================================
                RESUME
                =========================================================

                """ + resumeText;
    }
}