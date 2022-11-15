addBloque();

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