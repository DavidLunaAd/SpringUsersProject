// Call the dataTables jQuery plugin
$(document).ready(function() {
	
	cargarUsuarios();
	
  $('#usuarios').DataTable();
});


async function cargarUsuarios(){
	
  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers:{
		 'Accept': 'application/json',
	      'Content-Type': 'application/json',
	      'Authorization': localStorage.token
	}
  });
  const usuarios = await request.json();


let listadoHtml = '';
	for (let usuario of usuarios){
		
		let btnEliminar = '<a href="#" onClick="eliminarUsuario('+ usuario.id +')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
		let telefono = usuario.telefono == null ? ' - ' : usuario.telefono;
		
		  let usuarioHTML = '<tr><td>' + usuario.nombre + ' ' + usuario.apellido 
		  +'</td><td>'+ usuario.email +'</td><td>'+ telefono+'</td><td>'+ usuario.id 
		  + '</td><td>'+ btnEliminar +'</td></tr>';
		  
		  listadoHtml += usuarioHTML;
	}
  console.log(usuarios);
 
  document.querySelector('#usuarios tbody').outerHTML = listadoHtml;	
}


function getHeaders(){
	return{
		 'Accept': 'application/json',
	      'Content-Type': 'application/json',
	      'Authorization': localStorage.token
	}
}


async function eliminarUsuario(id) {
	if(!confirm('¿Desea eliminar ususario?')){
		return;
	}
	
	const request = await fetch('api/usuario/' + id, {
    method: 'DELETE',
    headers: {
		 'Accept': 'application/json',
	      'Content-Type': 'application/json',
	      'Authorization': localStorage.token
	      }
  });
  
	location.reload();
}
