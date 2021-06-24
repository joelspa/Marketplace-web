(function() {
    if (!sessionStorage.getItem("userInSession")) {
        document.location.href = "login.html";
        return;
    }
    document.querySelector("body").style = "display:block";
    const urlParams = new URLSearchParams(window.location.search);
    const anuncioId = urlParams.get('id');



    if (anuncioId && !isNaN(anuncioId)) {
        document.querySelector("#page-title").innerHTML = "Editar anuncio"

        fetch(`api/anuncio/${anuncioId}`)
            .then(response => {
                return response.json();
            })
            .then(data => {

                const titulo = document.querySelector("#titulo");
                const categoria = document.querySelector("#categoria");
                const descripcion = document.querySelector("#descripcion");
                const telefono = document.querySelector("#telefono");
                const precio = document.querySelector("#precio");
                const estado = document.querySelector("#status");
                const anuncioIdControl = document.querySelector("#anuncioId");
                const imageFileIdControl = document.querySelector("#imageFileId");
                const imageElement = document.querySelector("#image");

                titulo.value = data.titulo;
                categoria.value = data.categoria;
                descripcion.value = data.descripcion;
                precio.value = data.precio;
                estado.value = data.estado;
                telefono.value = data.telefono;
                anuncioIdControl.value = data.anuncioId;
                imageFileIdControl.value = data.imagenFileId;

                const imageSrc = data.imagenFileId == 0 ? "img/User-icon.png" : "api/anuncio/image/" + data.imagenFileId;
                imageElement.src = imageSrc;

            })

    } else {
        document.querySelector("#page-title").innerHTML = "Nuevo anuncio"
    }



    document.querySelector("#btn-save").addEventListener('click', () => {

        const anuncioId = document.querySelector("#anuncioId").value
        const titulo = document.querySelector("#titulo").value.trim();
        const categoria = document.querySelector("#categoria").value.trim();
        const descripcion = document.querySelector("#descripcion").value.trim();
        const telefono = document.querySelector("#telefono").value;
        const precio = document.querySelector("#precio").value;
        const estado = document.querySelector("#status").value;
        const imagenFileId = document.querySelector("#imageFileId").value;

        const validacionTitulo = document.querySelector("#validation-titulo");
        const validacioncategoria = document.querySelector("#validation-categoria");
        const validaciondescripcion = document.querySelector("#validation-desc");
        const validaciontelefono = document.querySelector("#validation-telefono");
        const validacionprecio = document.querySelector("#validation-precio");

        validacionTitulo.style.display = "none"
        validacioncategoria.style.display = "none"
        validaciondescripcion.style.display = "none"

        let hasError = false;

        if (titulo == "") {
            hasError = true;
            validacionTitulo.style.display = "block";
        }

        if (categoria == "") {
            hasError = true;
            validacioncategoria.innerHTML = "La categoría es requerido"
            validacioncategoria.style.display = "block";
        }

        if (descripcion == "") {
            hasError = true;
            validaciondescripcion.innerHTML = "La descripción es requerido"
            validaciondescripcion.style.display = "block";
        }

        if (telefono == "") {
            hasError = true;
            validaciontelefono.innerHTML = "El telefono es requerido"
            validaciontelefono.style.display = "block";
        } else if (!istelefonoValid(telefono)) {
            hasError = true;
            validaciontelefono.innerHTML = "El telefono ingresado no es válido"
            validaciontelefono.style.display = "block";
        }

        if (precio == "") {
            hasError = true;
            validacionprecio.innerHTML = "El precio es requerido"
            validacionprecio.style.display = "block";
        } else if (!isprecioValid(precio)) {
            hasError = true;
            validacionprecio.innerHTML = "El precio ingresado no es válido"
            validacionprecio.style.display = "block";
        }


        if (hasError)
            return;

        const usuario = JSON.parse(sessionStorage.getItem("userInSession"));
        const anuncio = {
            anuncioId: anuncioId,
            usuarioId: usuario.usuarioId,
            titulo: titulo,
            categoria: categoria,
            descripcion: descripcion,
            telefono: telefono,
            precio: precio,
            estado: estado,
            imagenFileId: imagenFileId
        }
        const method = anuncioId == "0" ? "POST" : "PUT"; //POST = insert y PUT = UPDATE
        fetch('api/anuncio', {
            method: method,
            headers: {
                'Accept': 'application/json', //MimeType
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(anuncio)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            debugger;
            if (!data) {
                msgError.innerHTML = data.message;
                msgError.style.display = "block"
                return;
            }
            document.location.href = "perfil.html?msg=ok_contact_saved";

        });
    })

    document.querySelector("#image-file").addEventListener("change", uploadFile);

})();

function uploadFile() {
    let photo = document.getElementById("image-file").files[0];
    let formData = new FormData();

    formData.append("image", photo);
    fetch('api/anuncio/upload', {
        method: "POST",
        body: formData
    }).then((response) => {
        return response.json();
    }).then((data) => {

        if (!data.isOK) {
            alert("No se pudo subir el archivo");
            return;
        }

        //data.message contiene el FileId que se subió
        const fileId = data.message;
        document.querySelector("#imageFileId").value = fileId;
        const image = document.querySelector("#image");
        image.src = "api/anuncio/image/" + fileId;

    });
}

function istelefonoValid(telefono) {
    const re = /^[0-9]+$/;
    return re.test(telefono);
}

function isprecioValid(precio) {
    const re = /^[0-9]+$/;
    return re.test(precio);
}