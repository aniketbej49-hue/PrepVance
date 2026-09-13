console.log("ADMIN DASHBOARD JS LOADED");


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