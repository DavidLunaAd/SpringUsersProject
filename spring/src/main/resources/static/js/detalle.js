$(document).ready(function() {
	
	
	var parametros = new URLSearchParams(window.location.search);
	var id = parametros.get('id');
	detalleUsuario(id);
	
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
  //alert("Datos usuario " + usuario.nombre + " Apellido: " + usuario.apellido + " Ciudad: " + ciudad)
   document.querySelector('#nombre').outerHTML = usuario.nombre;
   document.querySelector('#nombre1 span').outerHTML = usuario.nombre;
   document.querySelector('#apellido span').outerHTML = usuario.apellido;
   document.querySelector('#email span').outerHTML = usuario.email;
   document.querySelector('#ciudad span').outerHTML = ciudad;
   document.querySelector('#telefono span').outerHTML = usuario.telefono;
  console.log("Detalles de "+ usuario);
}