(function() {

    const [login, register] =
    document.querySelectorAll("#login,#register");

    const perfil = document.querySelector("#main-menu #perfil");

    if (sessionStorage.getItem("userInSession")) {
        perfil.style.display = "block";
        login.style.display = "none";
        register.style.display = "none";
    }

})();