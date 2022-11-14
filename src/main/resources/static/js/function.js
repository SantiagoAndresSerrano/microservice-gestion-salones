// Call the dataTables jQuery plugin
add();
//$select.addEventListener("change", opcionCambiada);

async function add(){
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

async function registrarPrestamo(){
    let data = {};

    data.id_actividad = document.getElementById('cmbActividad').value;
    data.fecha_inicio = document.getElementById('horaInicio').value;
    data.fecha_fin = document.getElementById('horaFin').value;

    //Algoritmo validacion i guess
    const request = await fetch('prestamo', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    window.location.href = 'other.html';
}