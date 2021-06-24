(function() {


    if (sessionStorage.getItem("userInSession")) {
        document.location.href = "index.html";
        return;
    }

    document.querySelector("#btn-login").addEventListener('click', (e) => {
        e.preventDefault();



        const [validationUsername, validationPassword, msgError] =
        document.querySelectorAll("#validation-username, #validation-password, #msg-error-login");

        validationUsername.style.display = "none";
        validationPassword.style.display = "none";
        msgError.style.display = "none";

        const inputUsername = document.querySelector("#InputUsername");
        const inputPassword = document.querySelector("#InputPassword");
        let hasError = false;
        if (inputUsername.value == "") {
            hasError = true;
            validationUsername.style.display = "block";
        }
        if (inputPassword.value == "") {
            hasError = true;
            validationPassword.style.display = "block";
        }

        if (hasError) {
            return;
        }

        const usuario = {
            "username": inputUsername.value,
            "password": inputPassword.value
        }

        fetch('api/usuario/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usuario)
        }).then((response) => {
            return response.json();
        }).then((data) => {

            if (!data.isOK) {
                msgError.innerHTML = data.message;
                msgError.style.display = "block"
                return;
            }
            sessionStorage.setItem("userInSession", data.message);

            document.location.href = "index.html";

        });

    })



})();