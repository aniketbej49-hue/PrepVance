
const adminEmail = localStorage.getItem("adminEmail");

if (!adminEmail) {

    window.location.href = "admin-login.html";

}

document.getElementById("adminEmail").innerText = adminEmail;




const noteForm =
    document.getElementById("noteForm");

const topicId =
    document.getElementById("topicId");

const title =
    document.getElementById("title");

const pdfFile =
    document.getElementById("pdfFile");

const submitBtn =
    document.getElementById("submitBtn");

const cancelBtn =
    document.getElementById("cancelBtn");

const formTitle =
    document.getElementById("formTitle");

const tableBody =
    document.getElementById("notesTableBody");




let editNoteId = null;




function loadTopics() {

    fetch("http://localhost:8080/admin_topic/get_topic")

        .then(response => {

            if (!response.ok) {

                throw new Error("Failed to load topics");

            }

            return response.json();

        })

        .then(topics => {

            topics.forEach(topic => {

                const option =
                    document.createElement("option");


                option.value = topic.id;


                option.innerText =
                    topic.topicName;


                topicId.appendChild(option);

            });

        })

        .catch(error => {

            console.error(error);

            alert("Failed to load topics");

        });

}



function loadNotes() {

    fetch("http://localhost:8080/admin_notes/get_notes")

        .then(response => {

            if (!response.ok) {

                throw new Error("Failed to load notes");

            }

            return response.json();

        })

        .then(notes => {

            displayNotes(notes);

        })

        .catch(error => {

            console.error(error);

            alert("Failed to load notes");

        });

}




function displayNotes(notes) {

    tableBody.innerHTML = "";


    if (notes.length === 0) {

        tableBody.innerHTML = `

            <tr>

                <td colspan="5">

                    No notes found

                </td>

            </tr>

        `;

        return;

    }


    notes.forEach(note => {

        const row =
            document.createElement("tr");


        row.innerHTML = `

            <td>
                ${note.id}
            </td>


            <td>
                ${note.topicId}
            </td>


            <td>
                ${note.title}
            </td>


            <td>

                <button
                    class="view-btn"
                    onclick="viewPDF('${note.filePath}')">

                    View PDF

                </button>

            </td>


            <td>

                <button
                    class="edit-btn"
                    onclick="editNote(${note.id})">

                    Edit

                </button>


                <button
                    class="delete-btn"
                    onclick="deleteNote(${note.id})">

                    Delete

                </button>

            </td>

        `;


        tableBody.appendChild(row);

    });

}




noteForm.addEventListener(
    "submit",
    function(event) {

        event.preventDefault();


        // Check topic

        if (topicId.value === "") {

            alert("Please select a topic");

            return;

        }


        // Check PDF

        if (pdfFile.files.length === 0) {

            alert("Please select a PDF file");

            return;

        }


        const file =
            pdfFile.files[0];


        // Check PDF type

        if (file.type !== "application/pdf") {

            alert("Only PDF files are allowed");

            return;

        }

        const formData =
            new FormData();


        formData.append(
            "topicId",
            topicId.value
        );


        formData.append(
            "title",
            title.value.trim()
        );


        formData.append(
            "file",
            file
        );




        fetch(
            "http://localhost:8080/admin_notes/add_notes",
            {

                method: "POST",

                body: formData

            }
        )

        .then(response => response.text())

        .then(message => {

            alert(message);

            resetForm();

            loadNotes();

        })

        .catch(error => {

            console.error(error);

            alert("Failed to upload PDF");

        });

    }
);


function viewPDF(filePath) {

    const pdfURL =
        "http://localhost:8080" + filePath;

    window.open(pdfURL, "_blank");

}

function editNote(id) {

    fetch(
        "http://localhost:8080/admin_notes/get_notes"
    )

    .then(response => response.json())

    .then(notes => {

        const note =
            notes.find(
                item => item.id === id
            );


        if (!note) {

            alert("Note not found");

            return;

        }


        // Put old values into form

        topicId.value =
            note.topicId;


        title.value =
            note.title;


        // Store ID

        editNoteId = id;


        // Change form

        formTitle.innerText =
            "Update PDF Note";


        submitBtn.innerText =
            "Update Note";


        cancelBtn.style.display =
            "inline-block";


        // File is NOT selected during edit

        pdfFile.required = false;


        window.scrollTo({

            top: 0,

            behavior: "smooth"

        });

    })

    .catch(error => {

        console.error(error);

        alert("Failed to load note");

    });

}


function updateNote() {

    const formData =
        new FormData();


    formData.append(
        "id",
        editNoteId
    );


    formData.append(
        "topicId",
        topicId.value
    );


    formData.append(
        "title",
        title.value.trim()
    );


    fetch(
        "http://localhost:8080/admin_notes/update_notes",
        {

            method: "PUT",

            body: formData

        }
    )

    .then(response => response.text())

    .then(message => {

        alert(message);

        resetForm();

        loadNotes();

    })

    .catch(error => {

        console.error(error);

        alert("Failed to update note");

    });

}



function deleteNote(id) {

    const confirmDelete =
        confirm(
            "Are you sure you want to delete this PDF note?"
        );


    if (!confirmDelete) {

        return;

    }


    fetch(
        `http://localhost:8080/admin_notes/delete_notes/${id}`,
        {

            method: "DELETE"

        }
    )

    .then(response => response.text())

    .then(message => {

        alert(message);

        loadNotes();

    })

    .catch(error => {

        console.error(error);

        alert("Failed to delete note");

    });

}




function searchNotes() {

    const keyword =
        document.getElementById("searchInput")
        .value
        .trim()
        .toLowerCase();


    if (keyword === "") {

        loadNotes();

        return;

    }


    fetch(
        "http://localhost:8080/admin_notes/get_notes"
    )

    .then(response => response.json())

    .then(notes => {

        const filteredNotes =
            notes.filter(note =>

                note.title
                    .toLowerCase()
                    .includes(keyword)

            );


        displayNotes(filteredNotes);

    })

    .catch(error => {

        console.error(error);

        alert("Failed to search notes");

    });

}



function resetForm() {

    noteForm.reset();


    editNoteId = null;


    formTitle.innerText =
        "Add PDF Note";


    submitBtn.innerText =
        "Upload PDF";


    cancelBtn.style.display =
        "none";


    pdfFile.required = true;

}



function logout() {

    localStorage.removeItem("adminEmail");

    window.location.href =
        "admin-login.html";

}

loadTopics();

loadNotes();