(function() {

    document.querySelector("#btn-signup").addEventListener('click', (e) => {
        e.preventDefault();


        const Name = document.querySelector("#inputName").value
        const User = document.querySelector("#inputUser").value
        const Mail = document.querySelector("#inputMail").value
        const PasswordS = document.querySelector("#inputPasswordTwo").value
        const Password = document.querySelector("#inputPassword").value

        const vnombre = document.querySelector("#validation-name");
        const vuser = document.querySelector("#validation-username");
        const vmail = document.querySelector("#validation-mail");
        const vpass = document.querySelector("#validation-password");
        const vpassS = document.querySelector("#validation-password-two");

        const msgError = document.querySelector("#error-signup")

        vnombre.style.display = "none";
        vuser.style.display = "none";
        vmail.style.display = "none";
        vpass.style.display = "none";
        vpassS.style.display = "none";

        let hasError = false;

        if (Name == "") {
            hasError = true;
            vnombre.style.display = "block"
        }

        if (User == "") {
            hasError = true;
            vuser.style.display = "block"
        }

        if (Mail == "") {
            hasError = true;
            vmail.style.display = "block"
        } else if (!isEmailValid(Mail)) {
            hasError = true;
            vmail.innerHTML = "Ingrese una dirección de E-mail válida"
            vmail.style.display = "block"
        }


        if (Password == "") {
            hasError = true;
            vpass.style.display = "block"
        } else if (!isPasswordValid(Password)) {
            hasError = true;
            vpass.innerHTML = "La contraseña debe tener mínimo ocho caracteres, al menos una letra, un número y un carácter especial"
            vpass.style.display = "block"
        }

        if (PasswordS == "") {
            hasError = true;
            vpassS.style.display = "block"
        }
        if (Password != PasswordS) {
            hasError = true;
            vpass.innerHTML = "Las contraseñas no coinciden"
            vpassS.innerHTML = "Las contraseñas no coinciden"
            vpass.style.display = "block"
            vpassS.style.display = "block"
        }

        if (hasError)
            return;

        const usuario = {
            nombreCompleto: Name,
            username: User,
            email: Mail,
            password: Password
        }
        debugger;
        fetch('api/usuario/', {
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

            document.location.href = "index.html";

        });
    });
})();

function isEmailValid(email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function isPasswordValid(pass) {
    const re = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

    return re.test(pass);
}