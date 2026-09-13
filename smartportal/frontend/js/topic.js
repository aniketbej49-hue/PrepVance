console.log("PrepVance TOPICS JS LOADED");

let allTopics = [];

let userTopicStatuses = [];

function getUserId() {

    const userId = localStorage.getItem("userId");

    if (!userId) {

        console.error("User ID not found.");

        return null;
    }

    return parseInt(userId);
}

function showMessage(message, type) {

    const messageElement =
        document.getElementById("topicMessage");

    if (!messageElement) {
        return;
    }

    messageElement.innerText = message;

    messageElement.className =
        "topic-message " + type;
}

async function loadTopics() {

    const userId = getUserId();

    if (!userId) {

        alert("Please login again.");

        window.location.href = "login.html";

        return;
    }

    showMessage(
        "Loading topics...",
        "message-loading"
    );

    try {

        const topicsResponse =
            await fetch(
                "http://localhost:8080/topics/all"
            );

        if (!topicsResponse.ok) {

            throw new Error(
                "Failed to load topics."
            );
        }

        allTopics =
            await topicsResponse.json();

        const statusResponse =
            await fetch(
                `http://localhost:8080/user_topics/${userId}`
            );

        if (!statusResponse.ok) {

            throw new Error(
                "Failed to load user topic status."
            );
        }

        userTopicStatuses =
            await statusResponse.json();

        displayTopics(allTopics);

        showMessage("", "");

    } catch (error) {

        console.error(
            "Topic loading error:",
            error
        );

        showMessage(
            "Unable to load topics. Please make sure the backend is running.",
            "message-error"
        );
    }
}

function getTopicStatus(topicId) {

    const statusRecord =
        userTopicStatuses.find(
            item => item.topicId === topicId
        );

    if (!statusRecord) {

        return "pending";
    }

    return statusRecord.status;
}

function displayTopics(topics) {

    const container =
        document.getElementById(
            "topicsContainer"
        );

    const topicCount =
        document.getElementById(
            "topicCount"
        );

    container.innerHTML = "";

    topicCount.innerText =
        topics.length +
        (topics.length === 1
            ? " Topic"
            : " Topics");

    if (topics.length === 0) {

        container.innerHTML = `

            <div class="empty-state">

                <h3>
                    No Topics Found
                </h3>

                <p>
                    Try searching with a different keyword.
                </p>

            </div>

        `;

        return;
    }

    topics.forEach(topic => {

        const status =
            getTopicStatus(topic.id);

        const card =
            document.createElement("div");

        card.className =
            "topic-card";

        if (status === "completed") {

            card.classList.add("completed");

            card.innerHTML = `

                <div class="topic-card-header">

                    <h3 class="topic-name">
                        ${escapeHTML(topic.topicName)}
                    </h3>

                    <span class="status-badge status-completed">
                        ✓ Completed
                    </span>

                </div>

                <div class="topic-actions">

                    <button
                        class="topic-action-btn pending-btn"
                        onclick="updateTopicStatus(
                            ${topic.id},
                            'pending'
                        )">

                        ↩ Mark as Pending

                    </button>

                    <button
                        class="topic-action-btn questions-btn"
                        onclick="viewQuestions(
                            ${topic.id}
                        )">

                        📖 View Questions

                    </button>

                </div>

            `;
        }

        else {

            card.innerHTML = `

                <div class="topic-card-header">

                    <h3 class="topic-name">
                        ${escapeHTML(topic.topicName)}
                    </h3>

                    <span class="status-badge status-pending">
                        ! Pending
                    </span>

                </div>

                <div class="topic-actions">

                    <button
                        class="topic-action-btn complete-btn"
                        onclick="updateTopicStatus(
                            ${topic.id},
                            'completed'
                        )">

                        ✓ Mark as Completed

                    </button>

                    <button
                        class="topic-action-btn questions-btn"
                        onclick="viewQuestions(
                            ${topic.id}
                        )">

                        📖 View Questions

                    </button>

                </div>

            `;
        }

        container.appendChild(card);

    });
}

async function updateTopicStatus(
    topicId,
    status
) {

    const userId = getUserId();

    if (!userId) {

        alert("Please login again.");

        window.location.href = "login.html";

        return;
    }

    const topicStatusData = {

        userId: userId,

        topicId: topicId,

        status: status

    };

    try {

        showMessage(
            "Updating topic status...",
            "message-loading"
        );

        const response =
            await fetch(
                "http://localhost:8080/user_topics/update_status",
                {
                    method: "PUT",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(
                        topicStatusData
                    )
                }
            );

        const result =
            await response.text();

        if (!response.ok) {

            throw new Error(result);
        }

        showMessage(
            "Topic status updated successfully.",
            "message-success"
        );

        await loadTopics();

        setTimeout(() => {

            showMessage("", "");

        }, 2000);

    } catch (error) {

        console.error(
            "Status update error:",
            error
        );

        showMessage(
            "Failed to update topic status.",
            "message-error"
        );
    }
}

function viewQuestions(topicId) {

    console.log(
        "View questions for topic:",
        topicId
    );

    window.location.href =
        `questions.html?topicId=${topicId}`;
}

async function searchTopics() {

    const searchInput =
        document.getElementById(
            "searchInput"
        );

    const keyword =
        searchInput.value.trim();

    if (keyword === "") {

        displayTopics(allTopics);

        return;
    }

    try {

        showMessage(
            "Searching...",
            "message-loading"
        );

        const response =
            await fetch(
                `http://localhost:8080/topics/search?keyword=${encodeURIComponent(keyword)}`
            );

        if (!response.ok) {

            throw new Error(
                "Search failed."
            );
        }

        const topics =
            await response.json();

        displayTopics(topics);

        showMessage("", "");

    } catch (error) {

        console.error(
            "Search error:",
            error
        );

        showMessage(
            "Unable to search topics.",
            "message-error"
        );
    }
}

function clearSearch() {

    const searchInput =
        document.getElementById(
            "searchInput"
        );

    searchInput.value = "";

    displayTopics(allTopics);

    showMessage("", "");
}

function escapeHTML(value) {

    const div =
        document.createElement("div");

    div.textContent = value;

    return div.innerHTML;
}

function logoutUser() {

    localStorage.removeItem("userId");

    localStorage.removeItem("userEmail");

    window.location.href =
        "login.html";
}

document.addEventListener(
    "DOMContentLoaded",
    function () {

        console.log(
            "Topics page loaded."
        );

        const userId =
            localStorage.getItem("userId");

        if (!userId) {

            alert(
                "Please login to access topics."
            );

            window.location.href =
                "login.html";

            return;
        }

        loadTopics();

        document
            .getElementById("searchBtn")
            .addEventListener(
                "click",
                searchTopics
            );

        document
            .getElementById("clearSearchBtn")
            .addEventListener(
                "click",
                clearSearch
            );

        document
            .getElementById("searchInput")
            .addEventListener(
                "keypress",
                function (event) {

                    if (event.key === "Enter") {

                        searchTopics();
                    }

                }
            );

        document
            .getElementById("logoutBtn")
            .addEventListener(
                "click",
                logoutUser
            );

    }
);