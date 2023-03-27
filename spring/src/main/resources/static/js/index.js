
$(document).ready(function() {
	
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

