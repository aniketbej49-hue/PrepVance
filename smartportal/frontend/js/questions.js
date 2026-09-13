const userId =
    localStorage.getItem("userId");

const userEmail =
    localStorage.getItem("userEmail");

if (!userId) {

    window.location.href =
        "login.html";

}

document.getElementById("userEmail").textContent =
    userEmail || "";

document.getElementById("logoutBtn")
    .addEventListener("click", function () {

        localStorage.removeItem("userId");
        localStorage.removeItem("userEmail");

        window.location.href = "login.html";

    });

const urlParams =
    new URLSearchParams(window.location.search);

const topicId =
    urlParams.get("topicId");

const questionContainer =
    document.getElementById("questionContainer");

if (!topicId) {

    questionContainer.innerHTML = `

        <div class="loading-box">

            <p>
                No topic selected.
            </p>

        </div>

    `;

} else {

    loadQuestions();

}

function loadQuestions() {

    fetch(
        `http://localhost:8080/questions/topic/${topicId}`
    )

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Failed to load questions"
            );

        }

        return response.json();

    })

    .then(questions => {

        if (questions.length === 0) {

            questionContainer.innerHTML = `

                <div class="loading-box">

                    <p>
                        No questions available for this topic.
                    </p>

                </div>

            `;

            return;
        }

        displayQuestions(questions);

    })

    .catch(error => {

        console.error(
            "Error loading questions:",
            error
        );

        questionContainer.innerHTML = `

            <div class="loading-box">

                <p>
                    Failed to load questions.
                </p>

            </div>

        `;

    });

}

function displayQuestions(questions) {

    questionContainer.innerHTML = "";

    questions.forEach((questionData, index) => {

        const questionCard =
            document.createElement("div");

        questionCard.className =
            "question-card";

        questionCard.innerHTML = `

            <div class="question-header">

                <div class="question-number">
                    QUESTION ${index + 1} OF ${questions.length}
                </div>

                <div class="question-tag">
                    Practice
                </div>

            </div>

            <div class="question-text">
                ${questionData.question}
            </div>

            <div class="options">

                <label class="option">

                    <input
                        type="radio"
                        name="question-${questionData.id}"
                        value="A">

                    <span>
                        <strong>A.</strong>
                        ${questionData.optionA}
                    </span>

                </label>

                <label class="option">

                    <input
                        type="radio"
                        name="question-${questionData.id}"
                        value="B">

                    <span>
                        <strong>B.</strong>
                        ${questionData.optionB}
                    </span>

                </label>

                <label class="option">

                    <input
                        type="radio"
                        name="question-${questionData.id}"
                        value="C">

                    <span>
                        <strong>C.</strong>
                        ${questionData.optionC}
                    </span>

                </label>

                <label class="option">

                    <input
                        type="radio"
                        name="question-${questionData.id}"
                        value="D">

                    <span>
                        <strong>D.</strong>
                        ${questionData.optionD}
                    </span>

                </label>

            </div>

            <button
                class="submit-btn"
                onclick="submitAnswer(${questionData.id})">

                Submit Answer →

            </button>

            <div
                id="result-${questionData.id}"
                class="answer-result"
                style="display: none;">

            </div>

        `;

        questionContainer.appendChild(
            questionCard
        );

    });

}

function submitAnswer(questionId) {

    const selectedOption =
        document.querySelector(
            `input[name="question-${questionId}"]:checked`
        );

    if (!selectedOption) {

        alert(
            "Please select an answer before submitting."
        );

        return;

    }

    const answerData = {

        questionId: questionId,

        selectedOption:
            selectedOption.value

    };

    fetch(
        "http://localhost:8080/questions/check-answer",
        {

            method: "POST",

            headers: {

                "Content-Type":
                    "application/json"

            },

            body:
                JSON.stringify(answerData)

        }

    )

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Failed to check answer"
            );

        }

        return response.json();

    })

    .then(result => {

        displayAnswerResult(
            questionId,
            result
        );

    })

    .catch(error => {

        console.error(
            "Error checking answer:",
            error
        );

        alert(
            "Failed to check answer."
        );

    });

}

function displayAnswerResult(
    questionId,
    result
) {

    const resultDiv =
        document.getElementById(
            `result-${questionId}`
        );

    resultDiv.style.display =
        "block";

    if (result.correct) {

        resultDiv.className =
            "answer-result result-correct";

        resultDiv.innerHTML = `

            <div class="result-title correct">

                ✓ Correct Answer

            </div>

            <div class="explanation">

                ${result.explanation}

            </div>

        `;

    }

    else {

        resultDiv.className =
            "answer-result result-wrong";

        resultDiv.innerHTML = `

            <div class="result-title wrong">

                ✕ Wrong Answer

            </div>

            <div class="correct-answer">

                Correct Answer:
                <strong>
                    ${result.correctOption}
                </strong>

            </div>

            <div class="explanation">

                ${result.explanation}

            </div>

        `;

    }

}