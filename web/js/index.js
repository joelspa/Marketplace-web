function cargarItems() {
    fetch("api/anuncio/todos")
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
            alert("Ocurrio un error al obtener los anuncios");
        });
}



function mostrarItems(anuncios) {
    const anunciosHTML = document.querySelector("#anuncios");
    anunciosHTML.innerHTML = "";

    if (anuncios.length == 0) {
        anunciosHTML.innerHTML = '<div class="producto-content">No tiene productos registrados. Presione el boton "Nuevo producto" para registrar uno nuevo.</div>'
        return;
    }


    let html = ""
    for (const i in anuncios) {
        const obj = anuncios[i];

        let itemsHTML = getItemsInHTML(obj);

        html += itemsHTML;
    }
    anunciosHTML.innerHTML = html;
}


function getItemsInHTML(obj) {
    const image = obj.imagenFileId == 0 ? "images/producto_default.png" : "api/anuncio/image/" + obj.imagenFileId;
    return `<div class="item">
                <a href="#popup">
                    <div class="card-item">
                        <img src="${ image }" alt="...">
                        <h2>${ obj.titulo }</h2>
                        <div class="desc"><strong>Descripción : </strong><br>${ obj.descripcion}</div>
                        <div class="cont"><strong>Contacto : </strong>${ obj.telefono }</div>
                        <div class="precio"><strong>Precio : ${ obj.precio } Bs</strong> </div>
                    </div>
                </a>
            </div>
    `
}