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
	
	public boolean crearDiagnostico(String nombreEmpleado,String nombre,String categoria, String marca, String descripcionEquipo,String reparacionMantenimiento, String tipo, String piezas, String observaciones) {
		
		
		// Regla de negocio: No se permite agregar dos productos con el mismo nombre

		if (validarMaximoDiagnosticos(categoria)) {

			ReparacionMantenimiento diagnostico = reparacionMantenimientoRepository.findByNombreEquipo(nombre);

			if (diagnostico != null) {
				throw new IllegalArgumentException("El diagnostico de este equipo ya existe");
			}

			CategoriaDiagnostico categoriaDiagnostico = categoriaDiagnosticoRepository.findByNombre(categoria);

			if (categoriaDiagnostico == null) {
				throw new IllegalArgumentException("No se encontró la categoria del diagnostico");
			}

			log.info("Agregando nuevo diagnostico por: " + nombreEmpleado + ", con nombre:" + nombre + ", categoria:"
					+ categoria + ", marca:" + marca + ", descripcion del equipo:" + descripcionEquipo
					+ ", Reparaciones/Mantenimientos a realizar:" + reparacionMantenimiento + ", tipo:" + tipo
					+ ", piezas requeridas:" + piezas + ", observaciones adicionales:" + observaciones);

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
			categoriaDiagnostico.addDiagnostico(diagnostico);
			categoriaDiagnosticoRepository.save(categoriaDiagnostico);
			
			return true;
			
		} else {
			return false;
		}
		 
	}
	
	boolean validarMaximoDiagnosticos(String categoria) {
		 if(categoriaDiagnosticoRepository.findByNombre(categoria).getDiagnosticos().size()<=2000)
			 return true;
		 else 
			 return true; 
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
