console.log("ADMIN AUTH JS LOADED");


const adminRegisterForm =
    document.getElementById("adminRegisterForm");


if (adminRegisterForm) {

    adminRegisterForm.addEventListener(
        "submit",
        function(event) {

            event.preventDefault();


            const name =
                document.getElementById(
                    "adminName"
                ).value;

            const email =
                document.getElementById(
                    "adminEmail"
                ).value;

            const password =
                document.getElementById(
                    "adminPassword"
                ).value;

            const confirmPassword =
                document.getElementById(
                    "adminConfirmPassword"
                ).value;

            const message =
                document.getElementById(
                    "adminRegisterMessage"
                );


            if (password !== confirmPassword) {

                message.innerText =
                    "Passwords do not match.";

                return;
            }


            fetch(
                "http://localhost:8080/authentication/register_admin",
                {

                    method: "POST",

                    headers: {

                        "Content-Type":
                            "application/json"

                    },

                    body: JSON.stringify({

                        name: name,
                        email: email,
                        password: password

                    })

                }
            )


            .then(response =>
                response.text()
            )


            .then(data => {

                message.innerText = data;


                if (
                    data ===
                    "Admin Registered Successfully"
                ) {

                    setTimeout(
                        function() {

                            window.location.href =
                                "admin-login.html";

                        },
                        1500
                    );

                }

            })


            .catch(error => {

                console.error(
                    "Admin registration error:",
                    error
                );

                message.innerText =
                    "Something went wrong. Please try again.";

            });

        }
    );

}



const adminLoginForm =
    document.getElementById("adminLoginForm");


if (adminLoginForm) {

    adminLoginForm.addEventListener(
        "submit",
        function(event) {

            event.preventDefault();


            const email =
                document.getElementById(
                    "adminLoginEmail"
                ).value;

            const password =
                document.getElementById(
                    "adminLoginPassword"
                ).value;

            const message =
                document.getElementById(
                    "adminLoginMessage"
                );


            fetch(
                "http://localhost:8080/authentication/login_admin",
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

                message.innerText = data;


                if (
                    data ===
                    "Login Successful"
                ) {


                    localStorage.setItem(
                        "adminEmail",
                        email
                    );


                    window.location.href =
                        "admin-dashboard.html";

                }

            })


            .catch(error => {

                console.error(
                    "Admin login error:",
                    error
                );

                message.innerText =
                    "Something went wrong. Please try again.";

            });

        }
    );

}