package mx.uam.ingsof.proyecto.presentacion.crearPruebasReparacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.ServicioCategoriaDiagnostico;
import mx.uam.ingsof.proyecto.negocio.ServicioDiagnosticoPruebas;
import mx.uam.ingsof.proyecto.negocio.ServicioEmpleado;
import mx.uam.ingsof.proyecto.negocio.ServicioReparacionMantenimiento;
import mx.uam.ingsof.proyecto.negocio.modelo.CategoriaDiagnostico;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;
import mx.uam.ingsof.proyecto.negocio.modelo.ReparacionMantenimiento;

@Component
public class ControlCrearPruebasReparacion {
	
	@Autowired 
	private VistaCrearPruebasReparacion ventana; 
	
	@Autowired 
	private ServicioCategoriaDiagnostico servicioCategoriaDiagnostico; 
	
	@Autowired 
	private ServicioEmpleado servicioEmpleado; 
	
	@Autowired 
	private ServicioDiagnosticoPruebas servicioDiagnosticoReparacion;  
	
	private String fecha;
	
	/**
	 * 
	 * Permite dar inicio al módulo ControlCrearDiagnostico y a sus respectivos métodos de dicho módulo  
	 * 
	 */
	
	public void inicia() {
		
		
		List <CategoriaDiagnostico> categorias = servicioCategoriaDiagnostico.consultarCategoriasDisponibles(); 
		
		List<Empleado> empleados = servicioEmpleado.recuperaEmpleados(); 
		
		fecha = servicioDiagnosticoReparacion.obtenerFechaActual();
		
		ventana.muestra(this,categorias, empleados,fecha);
	}
	
	/**
	 * 
	 * Manda a llamar al método crearDiagnostico() del módulo ServicioReparacionMantenimiento pasandole los mismos parámetros recibidos 
	 *
	 * @param nombreEmpleado
	 * @param nombre
	 * @param categoria
	 * @param marca
	 * @param descripcionEquipo
	 * @param reparacionMantenimiento
	 * @param tipo
	 * @param piezas
	 * @param observaciones
	 * @return Dialogo con mensaje 
	 */
	public void realizarPruebasReparacion(String nombreEmpleado, String categoria, String nombreEquipo, List<String> listaPruebas, String observaciones) {
		
		try {

			if (servicioDiagnosticoReparacion.realizarPruebasReparacion(nombreEmpleado, categoria, nombreEquipo, listaPruebas, observaciones) == true) {

				ventana.muestraDialogoConMensaje("Sea ha registro exitosamente el diagnostico de pruebas del equipo " +nombreEquipo+".");
				termina();
			} else {

				ventana.muestraDialogoConMensaje("El equipo " + nombreEquipo +" ya tiene un diagnostico de pruebas registrado.");
				termina();
			}


		} catch (Exception ex) {
			ventana.muestraDialogoConMensaje("Error al registrar el diagnostico de pruebas: " + ex.getMessage());
		}

		termina();
		
	}
	
	public List<ReparacionMantenimiento> buscarDiagnosticos(String categoria) {
		
		List <ReparacionMantenimiento> diagnosticos = servicioCategoriaDiagnostico.buscarDiagnosticos(categoria); 
		
		return diagnosticos; 
		
	}
	
	/**
	 * Termina la historia de usuario
	 * 
	 */
	public void termina() {
		ventana.setVisible(false);		
	}
}
