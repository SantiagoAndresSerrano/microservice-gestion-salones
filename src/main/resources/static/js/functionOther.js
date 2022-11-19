const urlParams = new URLSearchParams(window.location.search);
var actividad = urlParams.get('idActividad');
var fechaInicio = urlParams.get('fechaInicio');
var fechaFin = urlParams.get('fechaFin');

entrada();

async function entrada(){
    if(actividad == null || fechaInicio == "" || fechaFin == "" || fechaInicio == null || fechaFin == null){
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
        msg.innerHTML += `<option value="${i+1}" onclick="filtrarSalon(${i+1})">${bloques[i]}</option>`;
    }
    filtrarSalon(1);
}

async function filtrarSalon(id){
    const request = await fetch(`/prestamo/salones/${id}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const salones = await request.json();
    msg = document.querySelector("#cmbSalon");
    msg.innerHTML = '';

    for (let i=0; i < salones.length ; ++i){
        msg.innerHTML += `<option value="${i+1}">${salones[i]}</option>`;
    }
    if(salones.length == 0){
        msg.disabled = true;
    } else {
        msg.disabled = false;
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

async function continuarPrestamo(){

    //Algoritmo validacion i guess otra vez

    const request = await fetch(`actividad/${actividad}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const act = await request.json();

    let data = {};

    data.actividad = act;
    data.fecha_inicio = fechaInicio;
    data.fecha_fin = fechaFin;
    data.observacion = document.getElementById('txtObservacion').value;
    data.id_persona = 1;
    data.id_salon = 3;

    const request2 = await fetch('prestamo', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    window.location.href = 'vistaPrestamos.html';
}