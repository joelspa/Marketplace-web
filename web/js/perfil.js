(function() {

    document.body.style.display = "block"

    document.querySelector("#salir").addEventListener('click', () => {
        sessionStorage.removeItem("userInSession");
        document.location.href = "index.html";
    })
})();

function cargarItems() {
    let userInSession = JSON.parse(sessionStorage.getItem("userInSession"))
    fetch("api/anuncio/usuario/" + userInSession.usuarioId)
        .then((response) => {
            return response.json();
        })
        .then(function(data) {
            console.log("imprimiendo anuncios")
            console.log(data)
            mostrarItems(data);
        })
        .catch((error) => {
            console.log(error);
            alert("Ocurrion un error al obtener los anuncios");
        });


}

function mostrarItems(contactos) {
    const contactosHTML = document.querySelector("#anuncios");
    contactosHTML.innerHTML = "";

    if (contactos.length == 0) {
        contactosHTML.innerHTML = '<div class="producto-content">No tiene productos registrados. Presione el boton "Nuevo producto" para registrar uno nuevo.</div>'
        return;
    }


    let html = ""
    for (const i in contactos) {
        const obj = contactos[i];

        let contactoHTML = getContactoInHTML(obj);

        html += contactoHTML;
    }
    contactosHTML.innerHTML = html;
}

function getContactoInHTML(obj) {
    const image = obj.imagenFileId == 0 ? "images/producto_default.png" : "api/anuncio/image/" + obj.imagenFileId;
    return `<div class="item">
                <div class="card-item">
                    <img src="${ image }" alt="...">
                        <h2>${ obj.titulo }</h2>
                        <div class="desc"><strong>Descripción :</strong><br>${ obj.descripcion}</div>
                        <div class="cont"><strong>Contacto :</strong> ${ obj.telefono }</div>
                        <div class="precio"><strong>Precio :</strong> ${ obj.precio } Bs</div>
                        <div class="opciones">
                        <a href="anuncio-frm.html?id=${ obj.anuncioId }" class="edit">Editar</a>
                        <button type="button" class="eliminar" onclick="eliminarItems(${ obj.anuncioId })">Eliminar</button>
                        </div>
                        </div>
            </div>`
}


function eliminarItems(anuncioId) {
    if (!confirm("¿Esta seguro que desea eliminar el contacto seleccionado?")) {
        return;
    }

    console.log("Eliminando contacto " + anuncioId)

    fetch('api/anuncio/' + anuncioId, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then((response) => {
        return response.json();
    }).then((data) => {
        debugger;
        if (!data.isOK) {
            alert(data.message);
            return;
        }
        cargarItems();

    });
}

function editarItems(evt, anuncioId) {
    evt.preventDefault();
    debugger;
}