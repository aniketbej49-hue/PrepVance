console.log("AUTH JS LOADED");


const registerForm = document.querySelector("#registerForm");

if (registerForm) {

    registerForm.addEventListener("submit", function(event) {

        event.preventDefault();

        const name = document.querySelector("#name").value;
        const email = document.querySelector("#email").value;
        const password = document.querySelector("#password").value;

        fetch("http://localhost:8080/auth/register", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                name: name,
                email: email,
                password: password
            })

        })

        .then(response => response.text())

        .then(data => {
            
            document.querySelector("#message").innerText = data;

        })

        .catch(error => {

            console.log(error);

        });

    });

}


const loginForm =
    document.querySelector("#loginForm");


if (loginForm) {

    loginForm.addEventListener(
        "submit",
        function(event) {

            event.preventDefault();


            const email =
                document.querySelector(
                    "#loginEmail"
                ).value;


            const password =
                document.querySelector(
                    "#loginPassword"
                ).value;


            fetch(
                "http://localhost:8080/auth/login",
                {

                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body: JSON.stringify({

                        email: email,

                        password: password

                    })

                }
            )

            .then(response =>
                response.text()
            )

            .then(data => {

                if (
                    data !==
                    "Invalid Email or Password"
                ) {

                    localStorage.setItem(
                        "userEmail",
                        email
                    );


                    localStorage.setItem(
                        "userId",
                        data
                    );


                    window.location.href =
                        "dashboard.html";


                } else {

                    document.querySelector(
                        "#loginMessage"
                    ).innerText = data;

                }

            })

            .catch(error => {

                console.error(
                    "Login Error:",
                    error
                );


                document.querySelector(
                    "#loginMessage"
                ).innerText =
                    "Something went wrong. Please try again.";

            });

        }
    );

}


const forgotPasswordForm =
    document.getElementById("forgotPasswordForm");

if (forgotPasswordForm) {

    forgotPasswordForm.addEventListener("submit", function(event) {

        event.preventDefault();

        const email =
            document.getElementById("forgotEmail").value;

        const message =
            document.getElementById("forgotMessage");


        fetch(
            `http://localhost:8080/auth/send-otp?email=${encodeURIComponent(email)}`,
            {
                method: "POST"
            }
        )

        .then(response => response.text())

        .then(data => {

            message.innerText = data;


            if (data === "OTP sent successfully") {

                document.getElementById("emailSection")
                    .style.display = "none";

                document.getElementById("otpSection")
                    .style.display = "block";

            }

        })

        .catch(error => {

            console.error("Error:", error);

            message.innerText =
                "Something went wrong. Please try again.";

        });

    });

}


const verifyOtpForm =
    document.getElementById("verifyOtpForm");

if (verifyOtpForm) {

    verifyOtpForm.addEventListener("submit", function(event) {

        event.preventDefault();

        const email =
            document.getElementById("forgotEmail").value;

        const otp =
            document.getElementById("otp").value;

        const message =
            document.getElementById("forgotMessage");


        fetch(
            `http://localhost:8080/auth/verify-otp?email=${encodeURIComponent(email)}&otp=${encodeURIComponent(otp)}`,
            {
                method: "POST"
            }
        )

        .then(response => response.text())

        .then(data => {

            message.innerText = data;


            if (data === "OTP verified successfully") {

                document.getElementById("otpSection")
                    .style.display = "none";

                document.getElementById("passwordSection")
                    .style.display = "block";

            }

        })

        .catch(error => {

            console.error("Error:", error);

            message.innerText =
                "Something went wrong. Please try again.";

        });

    });

}


const resetPasswordForm =
    document.getElementById("resetPasswordForm");

if (resetPasswordForm) {

    resetPasswordForm.addEventListener("submit", function(event) {

        event.preventDefault();

        const email =
            document.getElementById("forgotEmail").value;

        const newPassword =
            document.getElementById("newPassword").value;

        const confirmPassword =
            document.getElementById("confirmPassword").value;

        const message =
            document.getElementById("forgotMessage");


        if (newPassword !== confirmPassword) {

            message.innerText =
                "Passwords do not match.";

            return;
        }


        fetch(
            `http://localhost:8080/auth/reset-password?email=${encodeURIComponent(email)}&newPassword=${encodeURIComponent(newPassword)}`,
            {
                method: "POST"
            }
        )

        .then(response => response.text())

        .then(data => {

            message.innerText = data;


            if (data === "Password reset successfully") {

                setTimeout(function() {

                    window.location.href = "login.html";

                }, 1500);

            }

        })

        .catch(error => {

            console.error("Error:", error);

            message.innerText =
                "Something went wrong. Please try again.";

        });

    });

}