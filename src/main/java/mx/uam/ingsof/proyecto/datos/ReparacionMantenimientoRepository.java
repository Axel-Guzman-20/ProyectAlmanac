package mx.uam.ingsof.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ingsof.proyecto.negocio.modelo.ReparacionMantenimiento;

/**
 * Repositorio para ReparacionMantenimiento
 * 
 * @author Abigail Morales Mariscal
 *
 */

public interface ReparacionMantenimientoRepository extends CrudRepository<ReparacionMantenimiento, Long> {
	
	public ReparacionMantenimiento findByNombreEquipo(String nombre); 
}
