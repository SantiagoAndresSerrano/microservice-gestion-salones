addActivity();

async function addActivity(){
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

function registrarPrestamo(){


    //Algoritmo validacion i guess



    localStorage.id_actividad = document.getElementById('cmbActividad').value;
    localStorage.fecha_inicio = document.getElementById('horaInicio').value;
    localStorage.fecha_fin = document.getElementById('horaFin').value;
    window.location.href = 'other.html';
}