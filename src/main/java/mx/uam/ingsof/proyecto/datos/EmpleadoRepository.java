package mx.uam.ingsof.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;

/**
 * Esta clase controla el repositorio de los proveedores
 * 
 * @author MiguelGuzman
 *
 */

public interface EmpleadoRepository extends CrudRepository<Empleado, Long>{
	
	public Empleado findByIdEmpleado(long idEmpleado);
	
}
