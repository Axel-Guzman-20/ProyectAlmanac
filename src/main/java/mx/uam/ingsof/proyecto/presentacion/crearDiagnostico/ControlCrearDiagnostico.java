package mx.uam.ingsof.proyecto.presentacion.crearDiagnostico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	/**
	 * 
	 * Permite dar inicio al módulo ControlCrearDiagnostico y a sus respectivos métodos de dicho módulo  
	 * 
	 */
	
	public void inicia() {
		
		String fecha; 
		
		List <CategoriaDiagnostico> categorias = servicioCategoriaDiagnostico.consultarCategoriasDisponibles(); 
		
		List<Empleado> empleados = servicioEmpleado.recuperaEmpleados(); 
		
		fecha = servicioReparacionMantenimiento.obtenerFechaActual();
		
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
	
	@RequestMapping 
	public void crearDiagnostico(String nombreEmpleado,String nombre,String categoria, String marca, String descripcionEquipo,String reparacionMantenimiento, String tipo, String piezas, String observaciones) {
		
		
		try {

			if (servicioReparacionMantenimiento.crearDiagnostico(nombreEmpleado,nombre,categoria,marca,descripcionEquipo,reparacionMantenimiento,tipo,piezas,observaciones)) {

				ventana.muestraDialogoConMensaje("El registro del diagnostico del equipo '" +nombre+"' a sido agregado exitosamente.");
				termina();
			} else {

				ventana.muestraDialogoConMensaje("Se ha llegado al limite maximo de registro de diagnosticos para la categoria " + categoria);
				termina();
			}


		} catch (Exception ex) {
			ventana.muestraDialogoConMensaje("Error al registrar el diagnostico: " + ex.getMessage());
		}

		termina();
		
	}
	
	/**
	 * Termina la historia de usuario
	 * 
	 */
	public void termina() {
		ventana.setVisible(false);		
	}
}
