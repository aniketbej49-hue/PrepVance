const userId =
    localStorage.getItem("userId");

const userEmail =
    localStorage.getItem("userEmail");

if (!userId) {

    window.location.href =
        "login.html";

}

document.getElementById("userEmail")
    .textContent = userEmail || "";

document.getElementById("logoutBtn")
    .addEventListener("click", function () {

        localStorage.removeItem("userId");

        localStorage.removeItem("userEmail");

        window.location.href =
            "login.html";

    });

const notesContainer =
    document.getElementById(
        "notesContainer"
    );

const searchNotes =
    document.getElementById(
        "searchNotes"
    );

const noteCount =
    document.getElementById(
        "noteCount"
    );

let allNotes = [];

function loadNotes() {

    fetch(
        "http://localhost:8080/notes/all"
    )

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Failed to load notes"
            );

        }

        return response.json();

    })

    .then(notes => {

        allNotes = notes;

        displayNotes(notes);

    })

    .catch(error => {

        console.error(
            "Error loading notes:",
            error
        );

        notesContainer.innerHTML = `

            <div class="empty-box">

                <div class="empty-icon">
                    ⚠️
                </div>

                <h3>
                    Unable to load notes
                </h3>

                <p>
                    Please try again later.
                </p>

            </div>

        `;

    });

}

function displayNotes(notes) {

    notesContainer.innerHTML = "";

    noteCount.textContent =
        notes.length;

    if (notes.length === 0) {

        notesContainer.innerHTML = `

            <div class="empty-box">

                <div class="empty-icon">
                    📚
                </div>

                <h3>
                    No notes found
                </h3>

                <p>
                    Study material will appear here
                    when available.
                </p>

            </div>

        `;

        return;

    }

    notes.forEach(note => {

        const noteCard =
            document.createElement(
                "div"
            );

        noteCard.className =
            "note-card";

        noteCard.innerHTML = `

            <div class="note-top">

                <div class="pdf-icon">
                    PDF
                </div>

                <div class="note-label">
                    STUDY MATERIAL
                </div>

            </div>

            <div class="note-title">

                ${note.title}

            </div>

            <div class="note-file">

                ${note.fileName}

            </div>

            <div class="note-divider"></div>

            <button
                class="read-btn"
                onclick="readNote('${note.filePath}')">

                Read PDF →

            </button>

        `;

        notesContainer.appendChild(
            noteCard
        );

    });

}

function readNote(filePath) {

    const pdfUrl =
        `http://localhost:8080${filePath}`;

    window.open(
        pdfUrl,
        "_blank"
    );

}

searchNotes.addEventListener(
    "input",
    function () {

        const keyword =
            searchNotes.value
                .trim()
                .toLowerCase();

        if (!keyword) {

            displayNotes(allNotes);

            return;

        }

        const filteredNotes =
            allNotes.filter(note => {

                return note.title
                    .toLowerCase()
                    .includes(keyword);

            });

        displayNotes(
            filteredNotes
        );

    }
);

loadNotes();