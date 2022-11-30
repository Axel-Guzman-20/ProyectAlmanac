package mx.uam.ingsof.proyecto.presentacion.crearDiagnostico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlCrearDiagnostico {
	
	@Autowired 
	private VentanaCrearDiagnostico ventana; 
	
	public void inicia() {
		
		String fecha;
		
		//List <SeccionCatalogo> secciones= servicioSeccionCatalogo.consultarSeccionesDisponibles(); 
		
		// Le dice a servicios que obtenga la fecha actual del sistema, la devuelve y lo coloca en la caja de textos fecha de la VistaBuscarProducto
		//fecha = servicioProducto.obtenerFechaActual();
		
		ventana.muestra(this);
	}
}
