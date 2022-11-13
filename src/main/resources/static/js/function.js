// Call the dataTables jQuery plugin
$(document).ready(function() {
    chargeInventory();
    $('#inventory').DataTable();
});

async function chargeInventory(){

    const request = await fetch('api/inventory', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const inventory = await request.json();

    let listHTML = '';
    for (let data of inventory){
        //<td><a href="#" onclick="" class="btn btn-primary"></a></td>
        let inventoryHTML = '<tr><td>'+data.id_inventario+'</td><td>'+data.nombre+'</td></tr>';
        listHTML += inventoryHTML;
    }

    document.querySelector("#inventory tbody").outerHTML = listHTML;
}