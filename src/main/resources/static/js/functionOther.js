entrada();

async function entrada(){
    const urlParams = new URLSearchParams(window.location.search);
    var id_actividad = urlParams.get('idActividad');
    var fecha_inicio = urlParams.get('fechaInicio');
    var fecha_fin = urlParams.get('fechaFin');

    console.log("Id -> " + id_actividad + " Inicio -> " + fecha_inicio + " Fin -> " + fecha_fin);

    if(id_actividad == null || fecha_inicio == "" || fecha_fin == "" || fecha_inicio == null || fecha_fin == null){
        alert("No insertó datos >:c");
        window.location.href="index.html";
    }

    const request = await fetch('/prestamo/bloques', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });

    const bloques = await request.json();
    let msg = document.querySelector("#cmbBloque");
    for (let i=0; i < bloques.length ; ++i){
        msg.innerHTML += `<option value="${i}">${bloques[i]}</option>`;
    }
}


async function addBloque(){
    const request = await fetch('actividad', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const actividades = await request.json();
    let msg = document.querySelector("#cmbActividad");
    for (let i=0; i < actividades.length ; ++i){
        msg.innerHTML += `<option value="${actividades[i].id_actividad}">${actividades[i].nombre}</option>`;
    }
}

function continuarPrestamo(){

}

function formatDate(timestamp){
    let x=new Date(timestamp);
    let dd = x.getDate();
    let mm = x.getMonth()+1;
    let yy = x.getFullYear();
    return dd +"/" + mm+"/" + yy;
}

async function registrarPrestamo(){
    let data = {};

    data.id_actividad = document.getElementById('cmbActividad').value;
    data.fecha_inicio = document.getElementById('horaInicio').value;
    data.fecha_fin = document.getElementById('horaFin').value;

    //Algoritmo validacion i guess
    localStorage.id_actividad = document.getElementById('cmbActividad').value;
    localStorage.fecha_inicio = document.getElementById('horaInicio').value;
    localStorage.fecha_fin = document.getElementById('horaFin').value;
    window.location.href = 'other.html';
}