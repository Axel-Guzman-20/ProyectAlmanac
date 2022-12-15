package mx.uam.ingsof.proyecto.presentacion.crearPruebasReparacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.ServicioCategoriaDiagnostico;
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
//	public void crearDiagnostico(String nombreEmpleado,String nombre,String categoria, String marca, String descripcionEquipo,String reparacionMantenimiento, String tipo, String piezas, String observaciones) {
//		
//		
//		try {
//
//			if (servicioReparacionMantenimiento.crearDiagnostico(nombreEmpleado,nombre,categoria,marca,descripcionEquipo,reparacionMantenimiento,tipo,piezas,observaciones) == true) {
//
//				ventana.muestraDialogoConMensaje("El registro del diagnostico del equipo '" +nombre+"' a sido agregado exitosamente.");
//				termina();
//			} else {
//
//				ventana.muestraDialogoConMensaje("Se ha llegado al limite maximo de registro de diagnosticos para la categoria " + categoria);
//				termina();
//			}
//
//
//		} catch (Exception ex) {
//			ventana.muestraDialogoConMensaje("Error al registrar el diagnostico: " + ex.getMessage());
//		}
//
//		termina();
//		
//	}
	
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
