async function listarLibros() {
    let response = await fetch("http://localhost:8080/api/libros",{
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    });

    let json = await response.json()

    let html = ""

    json.forEach(libro => {
        html +=  `<tr>
        <td>${libro.titulo}</td>
        <td>${libro.categoria}</td>
        <td>${libro.autor.nombre}</td>
        <td>${libro.disponible ? 'si' : 'no'}</td>
        <td>
            <a href="#" onclick="editLibro(${libro.id})" class="btn btn-primary btn-icon-split">
                <span class="text">Editar</span>
            </a>
            <a href="#" onclick="deleteLibro(${libro.id})" class="btn btn-danger btn-icon-split">
                <span class="text">Eliminar</span>
            </a>
        </td>
    </tr>`
    });

    document.getElementById("tablaLibros").outerHTML = html;
}

async function deleteLibro(id) {
    let confirmaEliminar = confirm("Desea eliminar el libro?")
    if (confirmaEliminar) {
        await fetch ("http://localhost:8080/api/libros/" + id, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        });
        listarLibros();
    }
}

async function editLibro(id) {
    window.location.href = "gestionar-libro.html?" + id
}

async function listarCategorias() {
    let categoria = await getCategorias();
    let container = document.getElementById("categoria-container");
    container.innerHTML = "";

    categoria.forEach(categoria => {
        let buttonHtml = `<div class="text-right" style="display: inline-block;">
            <a href="tables.html?categoria=${categoria}" 
            class="btn btn-primary btn-icon-split d-flex btn-margin">
                <span class="text">${categoria}</span>
            </a>
        </div>
        `;
        container.innerHTML += buttonHtml 
    })
}

document.addEventListener("DOMContentLoaded", listarCategorias);

listarLibros()