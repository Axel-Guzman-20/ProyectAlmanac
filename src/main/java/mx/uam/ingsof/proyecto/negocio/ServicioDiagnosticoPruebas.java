package mx.uam.ingsof.proyecto.negocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ingsof.proyecto.datos.DiagnosticoPruebasRepository;
import mx.uam.ingsof.proyecto.datos.ReparacionMantenimientoRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.DiagnosticoPruebas;
import mx.uam.ingsof.proyecto.negocio.modelo.Pruebas;
import mx.uam.ingsof.proyecto.negocio.modelo.ReparacionMantenimiento;

/**
 * Servicio relacionado con las reparaciones y/o mantenimientos de los equipos
 * 
 * @author abigailmorales
 */
@Slf4j
@Service
public class ServicioDiagnosticoPruebas {
	
	@Autowired
	private ReparacionMantenimientoRepository reparacionMantenimientoRepository;
	
	@Autowired
	private DiagnosticoPruebasRepository diagnosticoPruebasRepository; 
	
	public boolean realizarPruebasReparacion(String nombreEmpleado, String categoria, String nombreEquipo, List<String> listaPruebas, String observaciones) {
		
		if (validarCampos(nombreEmpleado, categoria, nombreEquipo, listaPruebas, observaciones)) {
			
			if(validaDiagnosticoSinPruebas(nombreEquipo)) {
				
				DiagnosticoPruebas pruebaReparacion = diagnosticoPruebasRepository.findByNombreEquipo(nombreEquipo) ;
				
				if (pruebaReparacion != null) {
					throw new IllegalArgumentException("El diagnostico de este equipo ya existe");
				}
				
				ReparacionMantenimiento reparacionMantenimiento = reparacionMantenimientoRepository.findByNombreEquipo(nombreEquipo);

				if (reparacionMantenimiento == null) {
					throw new IllegalArgumentException("No se encontró la categoria del diagnostico");
				}
				
				pruebaReparacion = new DiagnosticoPruebas(); 
				
				pruebaReparacion.setFecha(obtenerFechaActual());
				pruebaReparacion.setNombreEmpleado(nombreEmpleado);
				pruebaReparacion.setNombreEquipo(nombreEquipo);
				pruebaReparacion.setObservacionesAdicionales(observaciones);
				
				diagnosticoPruebasRepository.save(pruebaReparacion); 
				
				Pruebas prueba; 
				
				List <Pruebas> pruebas = new ArrayList<>(); 
				
				for(String p: listaPruebas) {
					
					prueba = new Pruebas();
					prueba.setNombre(p);
					
					pruebas.add(prueba); 	
				}
				
				if(pruebaReparacion.addPruebas(pruebas)) {
					
					if(reparacionMantenimiento.addPruebas(pruebaReparacion)) {
						reparacionMantenimientoRepository.save(reparacionMantenimiento); 
					} else {
						throw new IllegalArgumentException(
								"Error: El diagnostico de prueba no se pudo agregar exitosamente con su diagnostico de " + categoria+" del equipo " +nombreEquipo);
					}
				}
				
				
			} else {
				return false;
			}
			
		} else {
			throw new IllegalArgumentException("Error: Los campos no pueden ser nulos");
		}
		
		
		return true; 
	}
	
	/**
	 * 
	 * Permite validar que los campos del diagnostico no sean nulos 
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
	 * @return true si los campos del diagnostico no son nulos
	 * @return false si los campos del diagnostico son nulos 
	 */
	public boolean validarCampos(String nombreEmpleado, String categoria, String nombreEquipo, List<String> listaPruebas,
			String observaciones) {

		if ((nombreEmpleado == null) || (categoria == null) || (nombreEquipo == null) || (listaPruebas == null)
				|| (observaciones == null)) {
			return false;
		} else {
			if ((nombreEmpleado.equals("")) || (categoria.equals("")) || (nombreEquipo.equals(""))
					|| (listaPruebas.size() == 0)) {

				return false;
			} else {
				return true;
			}
		}

	}
	
	public boolean validaDiagnosticoSinPruebas(String nombreEquipo) {
		
		if (reparacionMantenimientoRepository.findByNombreEquipo(nombreEquipo).getDiagnosticoPruebas().size() == 0)
			return true;
		else
			return false;
		
	}
	
	/**
	 * 
	 * Permite recuperar la fecha actual del dispositivo
	 * 
	 * @return fecha
	 */
	public String obtenerFechaActual() {

		String fecha;

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		fecha = dateFormat.format(new Date());

		return fecha;
	}
	
}
