console.log("ADMIN TOPIC JS LOADED");




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

    .then(data => {

        displayTopics(data);

    })

    .catch(error => {

        console.error(
            "View Topics Error:",
            error
        );

    });

}


function displayTopics(topics) {

    const tableBody =
        document.getElementById(
            "topicTableBody"
        );


    tableBody.innerHTML = "";


    if (topics.length === 0) {

        tableBody.innerHTML = `

            <tr>

                <td colspan="3"
                    class="no-data">

                    No topics found.

                </td>

            </tr>

        `;

        return;

    }


    topics.forEach(topic => {


        const row =
            document.createElement("tr");


        row.innerHTML = `

            <td>
                ${topic.id}
            </td>

            <td class="topic-name">
                ${topic.topicName}
            </td>

                <td>
                <button
                    class="delete-btn"
                    onclick="deleteTopic(${topic.id})">

                    Delete

                </button>

            </td>

        `;


        tableBody.appendChild(row);

    });

}


const addTopicForm =
    document.getElementById(
        "addTopicForm"
    );


if (addTopicForm) {

    addTopicForm.addEventListener(
        "submit",
        function(event) {

            event.preventDefault();


            const topicName =
                document.getElementById(
                    "topicName"
                ).value.trim();



            const message =
                document.getElementById(
                    "topicMessage"
                );


            if (topicName === "") {

                message.innerText =
                    "Please enter a topic name.";

                return;

            }


            fetch(
                "http://localhost:8080/admin_topic/add_topic",
                {

                    method: "POST",

                    headers: {

                        "Content-Type":
                            "application/json"

                    },

                    body: JSON.stringify({

                        topicName:
                            topicName,

                    })

                }
            )

            .then(response =>
                response.text()
            )

            .then(data => {

                message.innerText =
                    data;


                addTopicForm.reset();


                // Reload topics

                loadTopics();

            })

            .catch(error => {

                console.error(
                    "Add Topic Error:",
                    error
                );

                message.innerText =
                    "Something went wrong.";

            });

        }
    );

}




function deleteTopic(id) {


    const confirmation =
        confirm(
            "Are you sure you want to delete this topic?"
        );


    if (!confirmation) {

        return;

    }


    fetch(
        `http://localhost:8080/admin_topic/delete_topic/${id}`,
        {

            method: "DELETE"

        }

    )

    .then(response =>
        response.text()
    )

    .then(data => {

        console.log(
            "Delete Response:",
            data
        );


        // Reload topics

        loadTopics();

    })

    .catch(error => {

        console.error(
            "Delete Topic Error:",
            error
        );

    });

}




const searchTopic =
    document.getElementById("searchTopic");

const searchTopicBtn =
    document.getElementById("searchTopicBtn");


if (searchTopicBtn) {

    searchTopicBtn.addEventListener(
        "click",
        function () {

            const keyword =
                searchTopic.value.trim();


            // If search box is empty,
            // show all topics

            if (keyword === "") {

                loadTopics();

                return;

            }


            fetch(
                `http://localhost:8080/admin_topic/search_topic?keyword=${encodeURIComponent(keyword)}`
            )

            .then(response => {

                if (!response.ok) {

                    throw new Error(
                        "Failed to search topics"
                    );

                }

                return response.json();

            })

            .then(data => {

                displayTopics(data);

            })

            .catch(error => {

                console.error(
                    "Search Topics Error:",
                    error
                );

            });

        }
    );

}


loadTopics();