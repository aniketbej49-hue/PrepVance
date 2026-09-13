console.log("ADMIN QUESTION JS LOADED");




const adminEmail =
    localStorage.getItem("adminEmail");


if (!adminEmail) {

    window.location.href =
        "admin-login.html";

}




const adminName =
    document.getElementById("adminName");


if (adminName && adminEmail) {

    adminName.innerText =
        adminEmail;

}



const adminLogoutBtn =
    document.getElementById(
        "adminLogoutBtn"
    );


if (adminLogoutBtn) {

    adminLogoutBtn.addEventListener(
        "click",
        function () {

            localStorage.removeItem(
                "adminEmail"
            );

            window.location.href =
                "admin-login.html";

        }
    );

}



const questionForm =
    document.getElementById(
        "questionForm"
    );


const topicId =
    document.getElementById(
        "topicId"
    );


const question =
    document.getElementById(
        "question"
    );


const optionA =
    document.getElementById(
        "optionA"
    );


const optionB =
    document.getElementById(
        "optionB"
    );


const optionC =
    document.getElementById(
        "optionC"
    );


const optionD =
    document.getElementById(
        "optionD"
    );


const correctOption =
    document.getElementById(
        "correctOption"
    );


const explanation =
    document.getElementById(
        "explanation"
    );


const questionMessage =
    document.getElementById(
        "questionMessage"
    );


const submitQuestionBtn =
    document.getElementById(
        "submitQuestionBtn"
    );


const cancelEditBtn =
    document.getElementById(
        "cancelEditBtn"
    );


const searchQuestion =
    document.getElementById(
        "searchQuestion"
    );


const searchQuestionBtn =
    document.getElementById(
        "searchQuestionBtn"
    );


const questionList =
    document.getElementById(
        "questionList"
    );




let editQuestionId = null;



function loadTopics() {

    fetch(
        "http://localhost:8080/admin_topic/get_topic"
    )

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Failed to load topics"
            );

        }

        return response.json();

    })

    .then(topics => {

        topicId.innerHTML = `

            <option value="">
                Select Topic
            </option>

        `;


        topics.forEach(topic => {

            const option =
                document.createElement(
                    "option"
                );


            option.value =
                topic.id;


            option.textContent =
                topic.topicName;


            topicId.appendChild(
                option
            );

        });

    })

    .catch(error => {

        console.error(
            "Load Topics Error:",
            error
        );

    });

}



function loadQuestions() {

    fetch(
        "http://localhost:8080/admin_question/get_questions"
    )

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Failed to load questions"
            );

        }

        return response.json();

    })

    .then(data => {

        displayQuestions(data);

    })

    .catch(error => {

        console.error(
            "Load Questions Error:",
            error
        );

    });

}



function displayQuestions(
    questions
) {

    questionList.innerHTML = "";


    if (questions.length === 0) {

        questionList.innerHTML = `

            <div class="no-data">

                No questions found.

            </div>

        `;

        return;

    }


    questions.forEach(
        questionData => {

            const item =
                document.createElement(
                    "div"
                );


            item.className =
                "question-item";


            item.innerHTML = `

                <div class="question-top">

                    <div>

                        <div class="question-id">

                            Question #${questionData.id}

                        </div>

                        <div class="question-text">

                            ${questionData.question}

                        </div>

                    </div>

                    <span class="topic-badge">

                        Topic ID:
                        ${questionData.topicId}

                    </span>

                </div>


                <div class="question-options">

                    <div class="
                        question-option
                        ${questionData.correctOption === "A"
                            ? "correct-option"
                            : ""}
                    ">

                        A. ${questionData.optionA}

                    </div>


                    <div class="
                        question-option
                        ${questionData.correctOption === "B"
                            ? "correct-option"
                            : ""}
                    ">

                        B. ${questionData.optionB}

                    </div>


                    <div class="
                        question-option
                        ${questionData.correctOption === "C"
                            ? "correct-option"
                            : ""}
                    ">

                        C. ${questionData.optionC}

                    </div>


                    <div class="
                        question-option
                        ${questionData.correctOption === "D"
                            ? "correct-option"
                            : ""}
                    ">

                        D. ${questionData.optionD}

                    </div>

                </div>


                <div class="explanation">

                    <strong>
                        Explanation
                    </strong>

                    ${questionData.explanation}

                </div>


                <div class="question-actions">

                    <button
                        class="edit-btn"
                        onclick="editQuestion(
                            ${questionData.id}
                        )">

                        Edit

                    </button>


                    <button
                        class="delete-btn"
                        onclick="deleteQuestion(
                            ${questionData.id}
                        )">

                        Delete

                    </button>

                </div>

            `;


            questionList.appendChild(
                item
            );

        }
    );

}



if (questionForm) {

    questionForm.addEventListener(
        "submit",
        function(event) {

            event.preventDefault();


            const questionData = {

                topicId:
                    parseInt(
                        topicId.value
                    ),

                question:
                    question.value.trim(),

                optionA:
                    optionA.value.trim(),

                optionB:
                    optionB.value.trim(),

                optionC:
                    optionC.value.trim(),

                optionD:
                    optionD.value.trim(),

                correctOption:
                    correctOption.value,

                explanation:
                    explanation.value.trim()

            };


        

            if (!topicId.value) {

                questionMessage.innerText =
                    "Please select a topic.";

                return;

            }


            if (
                !questionData.question ||
                !questionData.optionA ||
                !questionData.optionB ||
                !questionData.optionC ||
                !questionData.optionD ||
                !questionData.correctOption ||
                !questionData.explanation
            ) {

                questionMessage.innerText =
                    "Please fill all fields.";

                return;

            }




            if (editQuestionId === null) {

                fetch(
                    "http://localhost:8080/admin_question/add_question",
                    {

                        method: "POST",

                        headers: {

                            "Content-Type":
                                "application/json"

                        },

                        body:
                            JSON.stringify(
                                questionData
                            )

                    }
                )

                .then(response =>
                    response.text()
                )

                .then(data => {

                    questionMessage.innerText =
                        data;


                    questionForm.reset();


                    loadQuestions();

                })

                .catch(error => {

                    console.error(
                        "Add Question Error:",
                        error
                    );


                    questionMessage.innerText =
                        "Something went wrong.";

                });

            }



            else {

                questionData.id =
                    editQuestionId;


                fetch(
                    "http://localhost:8080/admin_question/update_question",
                    {

                        method: "PUT",

                        headers: {

                            "Content-Type":
                                "application/json"

                        },

                        body:
                            JSON.stringify(
                                questionData
                            )

                    }
                )

                .then(response =>
                    response.text()
                )

                .then(data => {

                    questionMessage.innerText =
                        data;


                    resetForm();


                    loadQuestions();

                })

                .catch(error => {

                    console.error(
                        "Update Question Error:",
                        error
                    );


                    questionMessage.innerText =
                        "Something went wrong.";

                });

            }

        }
    );

}


function editQuestion(id) {

    fetch(
        "http://localhost:8080/admin_question/get_questions"
    )

    .then(response =>
        response.json()
    )

    .then(questions => {

        const selectedQuestion =
            questions.find(
                item =>
                    item.id === id
            );


        if (!selectedQuestion) {

            return;

        }


        editQuestionId =
            selectedQuestion.id;


        topicId.value =
            selectedQuestion.topicId;


        question.value =
            selectedQuestion.question;


        optionA.value =
            selectedQuestion.optionA;


        optionB.value =
            selectedQuestion.optionB;


        optionC.value =
            selectedQuestion.optionC;


        optionD.value =
            selectedQuestion.optionD;


        correctOption.value =
            selectedQuestion.correctOption;


        explanation.value =
            selectedQuestion.explanation;


        submitQuestionBtn.innerText =
            "Update Question";


        cancelEditBtn.style.display =
            "inline-block";


        window.scrollTo({

            top: 0,

            behavior: "smooth"

        });

    })

    .catch(error => {

        console.error(
            "Edit Question Error:",
            error
        );

    });

}


if (cancelEditBtn) {

    cancelEditBtn.addEventListener(
        "click",
        function () {

            resetForm();

        }
    );

}


function resetForm() {

    questionForm.reset();


    editQuestionId =
        null;


    submitQuestionBtn.innerText =
        "Add Question";


    cancelEditBtn.style.display =
        "none";


    questionMessage.innerText =
        "";

}


function deleteQuestion(id) {

    const confirmation =
        confirm(
            "Are you sure you want to delete this question?"
        );


    if (!confirmation) {

        return;

    }


    fetch(
        `http://localhost:8080/admin_question/delete_question/${id}`,
        {

            method: "DELETE"

        }
    )

    .then(response =>
        response.text()
    )

    .then(data => {

        console.log(
            "Delete Question Response:",
            data
        );


        loadQuestions();

    })

    .catch(error => {

        console.error(
            "Delete Question Error:",
            error
        );

    });

}

if (searchQuestionBtn) {

    searchQuestionBtn.addEventListener(
        "click",
        function () {

            const keyword =
                searchQuestion.value.trim();


            if (keyword === "") {

                loadQuestions();

                return;

            }


            fetch(
                `http://localhost:8080/admin_question/search_questions?keyword=${encodeURIComponent(keyword)}`
            )

            .then(response => {

                if (!response.ok) {

                    throw new Error(
                        "Failed to search questions"
                    );

                }

                return response.json();

            })

            .then(data => {

                displayQuestions(data);

            })

            .catch(error => {

                console.error(
                    "Search Questions Error:",
                    error
                );

            });

        }
    );

}



loadTopics();

loadQuestions();