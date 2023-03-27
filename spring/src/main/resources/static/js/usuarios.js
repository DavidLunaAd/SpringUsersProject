// Call the dataTables jQuery plugin
$(document).ready(function() {
	
	cargarUsuarios();
	
  $('#usuarios').DataTable();
  
  
  	function actualizarHora() {
						      var elementoReloj = document.getElementById('reloj');
						      var fecha = new Date();
						      var dia = fecha.getDate();
  							  var mes = fecha.getMonth() + 1; // Los meses comienzan en 0, así que sumamos 1
  							  var anio = fecha.getFullYear();
						      var hora = fecha.getHours();
						      var minutos = fecha.getMinutes();
						        var fechaCompleta = dia + '-' + mes + '-' + anio;
						      var horaCompleta =  fechaCompleta +" - " + hora + ':' + minutos;
						      elementoReloj.innerHTML = horaCompleta;
						    }
						
						    setInterval(actualizarHora, 2000); // Llama a la func
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
			
			
			let btnEliminar = '<a href="#" onClick="eliminarUsuario('+ usuario.id +')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'+ '  ';
			
			let btndetalle = '<a href="detalle.html?id='+usuario.id+'" class="btn btn-info btn-circle btn-sm"><i class="fas fa-info-circle"></i></a>';
                                   
			let telefono = usuario.telefono == null ? ' - ' : usuario.telefono;
			
			  let usuarioHTML = '<tr><td>' + usuario.nombre + ' ' + usuario.apellido 
			  +'</td><td>'+ usuario.email +'</td><td>'+ telefono+'</td><td>'+ usuario.id 
			  + '</td><td>'+ btnEliminar + btndetalle +'</td></tr>';
			  
			  listadoHtml += usuarioHTML;
		}
	  console.log("Lista de usuarios " + usuarios);
	 
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
	
	if(!confirm('¿Desea eliminar usuario '+ id +' ?')){
		return;
	}
	
	const request = await fetch('eliminar/' + id, {
    method: 'DELETE',
    headers: {
		 'Accept': 'application/json',
	      'Content-Type': 'application/json',
	      'Authorization': localStorage.token
	      }
  });
  
	location.reload();
}

async function detalleUsuario(id) {
	
	const request = await fetch('usuario/' + id, {
    method: 'GET',
    headers: {
		 'Accept': 'application/json',
	      'Content-Type': 'application/json',
	      'Authorization': localStorage.token
	      }
  });
  
  const usuario = await request.json();
  let ciudad = usuario.ciudad == null ? ' - ' : usuario.ciudad;
  window.location.href = 'detalle.html'
  alert("Datos usuario " + usuario.nombre + " Apellido: " + usuario.apellido + " Ciudad: " + ciudad)
  console.log("Detalles de "+ usuario);
}
