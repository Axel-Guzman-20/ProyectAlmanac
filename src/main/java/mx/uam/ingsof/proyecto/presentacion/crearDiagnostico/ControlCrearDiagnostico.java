package mx.uam.ingsof.proyecto.presentacion.crearDiagnostico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.ServicioCategoriaDiagnostico;
import mx.uam.ingsof.proyecto.negocio.ServicioEmpleado;
import mx.uam.ingsof.proyecto.negocio.ServicioReparacionMantenimiento;
import mx.uam.ingsof.proyecto.negocio.modelo.CategoriaDiagnostico;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;

@Component
public class ControlCrearDiagnostico {
	
	@Autowired 
	private VentanaCrearDiagnostico ventana; 
	
	@Autowired 
	private ServicioCategoriaDiagnostico servicioCategoriaDiagnostico; 
	
	@Autowired 
	private ServicioEmpleado servicioEmpleado; 
	
	@Autowired 
	private ServicioReparacionMantenimiento servicioReparacionMantenimiento; 
	
	private String fecha;
	
	/**
	 * 
	 * Permite dar inicio al módulo ControlCrearDiagnostico y a sus respectivos métodos de dicho módulo  
	 * 
	 */
	
	public void inicia() {
		
		
		List <CategoriaDiagnostico> categorias = servicioCategoriaDiagnostico.consultarCategoriasDisponibles(); 
		
		List<Empleado> empleados = servicioEmpleado.recuperaEmpleados(); 
		
		fecha = servicioReparacionMantenimiento.obtenerFechaActual();
		
		ventana.muestra(this,categorias, empleados,fecha);
	}
	
	/**
	 * Termina la historia de usuario
	 * 
	 */
	public void termina() {
		ventana.setVisible(false);		
	}
}
