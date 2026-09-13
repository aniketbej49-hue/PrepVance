package com.interviewportal.smartportal.controller;

import com.interviewportal.smartportal.model.ATSScoreResult;
import com.interviewportal.smartportal.model.EducationAnalysisResult;
import com.interviewportal.smartportal.model.Resume;
import com.interviewportal.smartportal.model.SkillAnalysisResult;
import com.interviewportal.smartportal.service.ResumeAnalyzerService;
import com.interviewportal.smartportal.service.ResumeService;
import com.interviewportal.smartportal.model.ProjectAnalysisResult;
import com.interviewportal.smartportal.model.ResumeAnalysisResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    private final ResumeService resumeService;
    private final ResumeAnalyzerService resumeAnalyzerService;


    public ResumeController(ResumeService resumeService, ResumeAnalyzerService resumeAnalyzerService) {
    this.resumeAnalyzerService = resumeAnalyzerService;
    this.resumeService = resumeService;
}
    

    @PostMapping("/save")
    public String saveResume(@RequestBody Resume resume) {

        boolean saved = resumeService.saveResume(resume);

        if (saved) {
            return "Resume saved successfully";
        }

        return "Failed to save resume";
    }


    

    @GetMapping("/latest")
    public Resume getLatestResume(
            @RequestParam String email) {

        return resumeService.getLatestResume(email);
    }


@PostMapping("/upload")
public ResumeAnalysisResult uploadResume(
        @RequestParam String email,
        @RequestParam("file") MultipartFile file) {


   

    System.out.println(
            "Email: " + email
    );


    System.out.println(
            "File name: " +
            file.getOriginalFilename()
    );


    

    String text =
            resumeAnalyzerService.extractText(file);


    System.out.println(
            "========== RESUME TEXT =========="
    );


    System.out.println(
            text
    );


    System.out.println(
            "================================="
    );


    

    ResumeAnalysisResult result =
            resumeAnalyzerService.analyzeResume(text);


    
    return result;
}
}