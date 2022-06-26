let baseUrl = "http://localhost:8080";

async function login() {
    console.log("login button pressed")

    let email = document.getElementById('email').value;
    let password = document.getElementById('pass').value;

    let user = {
        email: email,
        pass: password
    }

    console.log(user);

    let userJson = JSON.stringify(user);

    let res = await fetch(
        `${baseUrl}/login`,
        {
            method: 'POST',
            header: { 'Content-Type': 'application/json' },
            body: userJson
        }
    );
    let resJson = await res.json()
        .then((resp) => {
            console.log(resp);
            sessionStorage.setItem("user", JSON.stringify(resp));
            window.location.assign("homePage.html");
        })
        .catch((error) => {
            console.log(error);
        });
}

function checkSession() {
    console.log(JSON.parse(sessionStorage.getItem("user")));
}