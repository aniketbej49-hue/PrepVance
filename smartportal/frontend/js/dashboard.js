console.log("PrepVance DASHBOARD JS LOADED");

const userId = localStorage.getItem("userId");

async function loadDashboard() {

    if (!userId) {
        console.log("User ID not found");
        return;
    }

    try {

        const totalResponse =
            await fetch("http://localhost:8080/dashboard/total-topics");

        const totalTopics =
            await totalResponse.json();

        document.getElementById("totalTopics").innerText =
            totalTopics;

        const completedResponse =
            await fetch(
                `http://localhost:8080/dashboard/completed-topics/${userId}`
            );

        const completedTopics =
            await completedResponse.json();

        document.getElementById("completedTopics").innerText =
            completedTopics;

        const pendingResponse =
            await fetch(
                `http://localhost:8080/dashboard/pending-topics/${userId}`
            );

        const pendingTopics =
            await pendingResponse.json();

        document.getElementById("pendingTopics").innerText =
            pendingTopics;

        const progressResponse =
            await fetch(
                `http://localhost:8080/dashboard/progress/${userId}`
            );

        const progress =
            await progressResponse.json();

        document.getElementById("progressPercentage").innerText =
            progress + "%";

        document.getElementById("progressText").innerText =
            progress + "%";

        document.getElementById("progressBar").style.width =
            progress + "%";

    } catch (error) {

        console.error(
            "Dashboard loading error:",
            error
        );

    }
}

const logoutBtn =
    document.getElementById("logoutBtn");

if (logoutBtn) {

    logoutBtn.addEventListener("click", function () {

        localStorage.removeItem("userId");
        localStorage.removeItem("userEmail");

    });

}

document.addEventListener("DOMContentLoaded", function () {

    const openAnalyzerBtn =
        document.getElementById("openAnalyzerBtn");

    const closeAnalyzerBtn =
        document.getElementById("closeAnalyzerBtn");

    const resumeAnalyzer =
        document.getElementById("resumeAnalyzer");

    const resumeFile =
        document.getElementById("resumeFile");

    const analyzeBtn =
        document.getElementById("analyzeBtn");

    const analysisResult =
        document.getElementById("analysisResult");

    if (openAnalyzerBtn) {

        openAnalyzerBtn.addEventListener(
            "click",
            function () {

                resumeAnalyzer.classList.add("active");

            }
        );

    }

    if (closeAnalyzerBtn) {

        closeAnalyzerBtn.addEventListener(
            "click",
            function () {

                resumeAnalyzer.classList.remove("active");

            }
        );

    }

    if (analyzeBtn) {

        analyzeBtn.addEventListener(
            "click",
            async function () {

                if (!resumeFile.files.length) {

                    alert("Please choose a resume PDF.");

                    return;

                }

                const file =
                    resumeFile.files[0];

                if (
                    file.type !== "application/pdf" &&
                    !file.name.toLowerCase().endsWith(".pdf")
                ) {

                    alert("Please upload a PDF file.");

                    return;

                }

                const userEmail =
                    localStorage.getItem("userEmail");

                if (!userEmail) {

                    alert("User email not found. Please login again.");

                    return;

                }

                const formData =
                    new FormData();

                formData.append(
                    "file",
                    file
                );

                formData.append(
                    "email",
                    userEmail
                );

                analyzeBtn.disabled = true;

                analyzeBtn.innerText =
                    "Analyzing Resume...";

                try {

                    const response =
                        await fetch(
                            "http://localhost:8080/resume/upload",
                            {
                                method: "POST",
                                body: formData
                            }
                        );

                    if (!response.ok) {

                        throw new Error(
                            "Resume analysis failed. Server returned " +
                            response.status
                        );

                    }

                    const data =
                        await response.json();

                    console.log(
                        "Resume Analysis Result:",
                        data
                    );

                    displayResumeAnalysis(data);

                } catch (error) {

                    console.error(
                        "Resume Analyzer Error:",
                        error
                    );

                    alert(
                        "Something went wrong while analyzing the resume."
                    );

                } finally {

                    analyzeBtn.disabled = false;

                    analyzeBtn.innerText =
                        "Analyze Resume";

                }

            }
        );

    }

});

function displayResumeAnalysis(data) {

    const analysisResult =
        document.getElementById("analysisResult");

    analysisResult.style.display = "block";

    if (data.atsScore) {

        setText(
            "atsScore",
            `${data.atsScore.totalScore} / 100`
        );

        setText(
            "keywordMatchScore",
            data.atsScore.keywordMatchScore
        );

        setText(
            "skillsRelevanceScore",
            data.atsScore.skillsRelevanceScore
        );

        setText(
            "experienceRelevanceScore",
            data.atsScore.experienceRelevanceScore
        );

        setText(
            "educationRelevanceScore",
            data.atsScore.educationRelevanceScore
        );

        setText(
            "sectionQualityScore",
            data.atsScore.sectionQualityScore
        );

        setText(
            "formattingScore",
            data.atsScore.formattingScore
        );

        setText(
            "readabilityScore",
            data.atsScore.readabilityScore
        );

    }

    if (data.candidateProfile) {

        setText(
            "experienceLevel",
            data.candidateProfile.experienceLevel
        );

        setText(
            "targetRole",
            data.candidateProfile.targetRole
        );

        setText(
            "candidateDomain",
            data.candidateProfile.domain
        );

        setText(
            "professionalSummary",
            data.candidateProfile.professionalSummary
        );

    }

    if (data.skills) {

        fillList(
            "technicalSkillsList",
            data.skills.technicalSkills
        );

        fillList(
            "softSkillsList",
            data.skills.softSkills
        );

        fillList(
            "domainSkillsList",
            data.skills.domainSkills
        );

        fillList(
            "toolsList",
            data.skills.toolsAndTechnologies
        );

    }

    if (data.experience) {

        displayExperience(
            data.experience.entries
        );

    }

    if (data.education) {

        displayEducation(
            data.education.entries
        );

    }

    if (data.projects) {

        displayProjects(
            data.projects.entries
        );

    }

    fillList(
        "certificationsList",
        data.certifications
    );

    if (data.feedback) {

        fillList(
            "strengthsList",
            data.feedback.strengths
        );

        fillList(
            "weaknessesList",
            data.feedback.weaknesses
        );

        fillList(
            "recommendationsList",
            data.feedback.recommendations
        );

        fillList(
            "atsImprovementsList",
            data.feedback.atsImprovements
        );

    }

    if (data.jobMatch) {

        setText(
            "jobMatchScore",
            data.jobMatch.matchScore + " / 100"
        );

        fillList(
            "matchedSkillsList",
            data.jobMatch.matchedSkills
        );

        fillList(
            "missingSkillsList",
            data.jobMatch.missingSkills
        );

        fillList(
            "matchedKeywordsList",
            data.jobMatch.matchedKeywords
        );

        fillList(
            "missingKeywordsList",
            data.jobMatch.missingKeywords
        );

        fillList(
            "jobMatchRecommendationsList",
            data.jobMatch.recommendations
        );

    }

}

function setText(elementId, value) {

    const element =
        document.getElementById(elementId);

    if (!element) {
        return;
    }

    if (
        value === null ||
        value === undefined ||
        value === ""
    ) {

        element.textContent = "--";

    } else {

        element.textContent = value;

    }

}

function fillList(elementId, items) {

    const list =
        document.getElementById(elementId);

    if (!list) {
        return;
    }

    list.innerHTML = "";

    if (
        !items ||
        !Array.isArray(items) ||
        items.length === 0
    ) {

        const li =
            document.createElement("li");

        li.textContent =
            "No information found.";

        list.appendChild(li);

        return;

    }

    items.forEach(function (item) {

        const li =
            document.createElement("li");

        li.textContent = item;

        list.appendChild(li);

    });

}

function displayExperience(entries) {

    const container =
        document.getElementById("experienceList");

    if (!container) {
        return;
    }

    container.innerHTML = "";

    if (
        !entries ||
        !Array.isArray(entries) ||
        entries.length === 0
    ) {

        const p =
            document.createElement("p");

        p.textContent =
            "No professional experience found.";

        container.appendChild(p);

        return;

    }

    entries.forEach(function (experience) {

        const div =
            document.createElement("div");

        div.className =
            "experience-entry";

        const title =
            document.createElement("h4");

        title.textContent =
            experience.jobTitle || "Job Title";

        const company =
            document.createElement("p");

        company.textContent =
            experience.company || "Company";

        const dates =
            document.createElement("p");

        dates.textContent =
            `${experience.startDate || "--"} - ${experience.endDate || "--"}`;

        div.appendChild(title);

        div.appendChild(company);

        div.appendChild(dates);

        if (
            experience.responsibilities &&
            experience.responsibilities.length > 0
        ) {

            const heading =
                document.createElement("strong");

            heading.textContent =
                "Responsibilities:";

            div.appendChild(heading);

            const list =
                document.createElement("ul");

            experience.responsibilities.forEach(
                function (responsibility) {

                    const li =
                        document.createElement("li");

                    li.textContent =
                        responsibility;

                    list.appendChild(li);

                }
            );

            div.appendChild(list);

        }

        if (
            experience.achievements &&
            experience.achievements.length > 0
        ) {

            const heading =
                document.createElement("strong");

            heading.textContent =
                "Achievements:";

            div.appendChild(heading);

            const list =
                document.createElement("ul");

            experience.achievements.forEach(
                function (achievement) {

                    const li =
                        document.createElement("li");

                    li.textContent =
                        achievement;

                    list.appendChild(li);

                }
            );

            div.appendChild(list);

        }

        container.appendChild(div);

    });

}

function displayEducation(entries) {

    const container =
        document.getElementById("educationList");

    if (!container) {
        return;
    }

    container.innerHTML = "";

    if (
        !entries ||
        !Array.isArray(entries) ||
        entries.length === 0
    ) {

        const p =
            document.createElement("p");

        p.textContent =
            "No education information found.";

        container.appendChild(p);

        return;

    }

    entries.forEach(function (education) {

        const div =
            document.createElement("div");

        div.className =
            "education-entry";

        const degree =
            document.createElement("h4");

        degree.textContent =
            education.degree || "Degree";

        const field =
            document.createElement("p");

        field.textContent =
            education.fieldOfStudy || "";

        const institution =
            document.createElement("p");

        institution.textContent =
            education.institution || "Institution";

        const dates =
            document.createElement("p");

        dates.textContent =
            `${education.startDate || "--"} - ${education.endDate || "--"}`;

        div.appendChild(degree);

        if (education.fieldOfStudy) {
            div.appendChild(field);
        }

        div.appendChild(institution);

        div.appendChild(dates);

        if (education.grade) {

            const grade =
                document.createElement("p");

            grade.textContent =
                "Grade: " + education.grade;

            div.appendChild(grade);

        }

        container.appendChild(div);

    });

}

function displayProjects(entries) {

    const container =
        document.getElementById("projectsList");

    if (!container) {
        return;
    }

    container.innerHTML = "";

    if (
        !entries ||
        !Array.isArray(entries) ||
        entries.length === 0
    ) {

        const p =
            document.createElement("p");

        p.textContent =
            "No projects found.";

        container.appendChild(p);

        return;

    }

    entries.forEach(function (project) {

        const div =
            document.createElement("div");

        div.className =
            "project-entry";

        const title =
            document.createElement("h4");

        title.textContent =
            project.title || "Project";

        const description =
            document.createElement("p");

        description.textContent =
            project.description || "";

        div.appendChild(title);

        div.appendChild(description);

        if (
            project.technologies &&
            project.technologies.length > 0
        ) {

            const tech =
                document.createElement("p");

            tech.textContent =
                "Technologies: " +
                project.technologies.join(", ");

            div.appendChild(tech);

        }

        if (project.role) {

            const role =
                document.createElement("p");

            role.textContent =
                "Role: " + project.role;

            div.appendChild(role);

        }

        if (
            project.startDate ||
            project.endDate
        ) {

            const dates =
                document.createElement("p");

            dates.textContent =
                `${project.startDate || "--"} - ${project.endDate || "--"}`;

            div.appendChild(dates);

        }

        if (
            project.achievements &&
            project.achievements.length > 0
        ) {

            const heading =
                document.createElement("strong");

            heading.textContent =
                "Achievements:";

            div.appendChild(heading);

            const list =
                document.createElement("ul");

            project.achievements.forEach(
                function (achievement) {

                    const li =
                        document.createElement("li");

                    li.textContent =
                        achievement;

                    list.appendChild(li);

                }
            );

            div.appendChild(list);

        }

        container.appendChild(div);

    });

}

loadDashboard();