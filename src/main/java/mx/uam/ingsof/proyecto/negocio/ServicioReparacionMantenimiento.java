package mx.uam.ingsof.proyecto.negocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ingsof.proyecto.datos.CategoriaDiagnosticoRepository;
import mx.uam.ingsof.proyecto.datos.ReparacionMantenimientoRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.CategoriaDiagnostico;
import mx.uam.ingsof.proyecto.negocio.modelo.ReparacionMantenimiento;

/**
 * Servicio relacionado con las reparaciones y/o mantenimientos de los equipos
 * 
 * @author abigailmorales
 */

@Slf4j
@Service
public class ServicioReparacionMantenimiento {

	@Autowired
	private ReparacionMantenimientoRepository reparacionMantenimientoRepository;

	@Autowired
	private CategoriaDiagnosticoRepository categoriaDiagnosticoRepository;
	
	/**
	 * 
	 * Permite crear un diagnostico en la categoria deseada 
	 * Nota: un mismo diagnostico no puede registrarse dos veces (Regla del Negocio)
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
	 * @return true si el diagnostico se agregó correctamente
	 * @return false si ya se excedio el maximo de diagnosticos en la categoria seleccionada 
	 * @throws IllegalArgumentException si los campos del diagnostico son nulos
	 * @throws IllegalArgumentException si el diagnostico ya existe 
	 * @throws IllegalArgumentException si no se encontro la categoria a la que se quiere agregar el diagnostico
	 * @throws IllegalArgumentException si el diagnostico no se pudo agregar exitosamente a la categoria
	 */
	
	public boolean crearDiagnostico(String nombreEmpleado, String nombre, String categoria, String marca,
			String descripcionEquipo, String reparacionMantenimiento, String tipo, String piezas,
			String observaciones) {

		if (validarCampos(nombreEmpleado, nombre, categoria, marca, descripcionEquipo, reparacionMantenimiento, tipo,
				piezas, observaciones)) {
			if (validarMaximoDiagnosticos(categoria)) {

				// Regla de negocio: No se permite agregar dos diagnosticos con el mismo nombre

				ReparacionMantenimiento diagnostico = reparacionMantenimientoRepository.findByNombreEquipo(nombre);

				if (diagnostico != null) {
					throw new IllegalArgumentException("El diagnostico de este equipo ya existe");
				}

				CategoriaDiagnostico categoriaDiagnostico = categoriaDiagnosticoRepository.findByNombre(categoria);

				if (categoriaDiagnostico == null) {
					throw new IllegalArgumentException("No se encontró la categoria del diagnostico");
				}

				diagnostico = new ReparacionMantenimiento();

				diagnostico.setFecha(obtenerFechaActual());
				diagnostico.setNombreEmpleado(nombreEmpleado);
				diagnostico.setNombreEquipo(nombre);
				diagnostico.setMarca(marca);
				diagnostico.setDescripcionEquipo(descripcionEquipo);
				diagnostico.setDescripcionReparacionMantenimiento(reparacionMantenimiento);
				diagnostico.setTipoReparacionMantenimiento(tipo);
				diagnostico.setPiezasRequeridas(piezas);
				diagnostico.setObservacionesAdicionales(observaciones);

				reparacionMantenimientoRepository.save(diagnostico);
				if (categoriaDiagnostico.addDiagnostico(diagnostico)) {
					categoriaDiagnosticoRepository.save(categoriaDiagnostico);
				} else {
					throw new IllegalArgumentException(
							"Error: El diagnostico no se pudo agregar exitosamente a la categoria " + categoria);
				}

				return true;

			} else {
				return false;
			}
		} else {
			throw new IllegalArgumentException("Error: Los campos no pueden ser nulos");
		}
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
	boolean validarCampos(String nombreEmpleado, String nombre, String categoria, String marca,
			String descripcionEquipo, String reparacionMantenimiento, String tipo, String piezas,
			String observaciones) {

		if ((nombreEmpleado == null) || (nombre == null) || (categoria == null) || (marca == null)
				|| (descripcionEquipo == null) || (reparacionMantenimiento == null) || (tipo == null)
				|| (piezas == null) || (observaciones == null))
			return false;
		else
			return true;
	}
	
	/**
	 * 
	 * Valida que no se sobrepase el valor máximo asignado para crear diagnosticos en la categoria correspondiente 
	 * 
	 * @param categoria
	 * @return true si aun no execede el valor maximo asignado de diagnosticos en la categoria correspondiente 
	 * @return false si ya se execede el valor maximo asignado de diagnosticos en la categoria correspondiente
	 */
	boolean validarMaximoDiagnosticos(String categoria) {
		if (categoriaDiagnosticoRepository.findByNombre(categoria).getDiagnosticos().size() <= 2000)
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
