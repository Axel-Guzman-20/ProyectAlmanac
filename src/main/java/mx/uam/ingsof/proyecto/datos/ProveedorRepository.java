package mx.uam.ingsof.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Proveedor;

/**
 * Esta clase controla el repositorio de los proveedores
 * 
 * @author MiguelGuzman
 *
 */

public interface ProveedorRepository extends CrudRepository<Proveedor, Long>{
	
	// Cuenta cuantos registros existen
	public long count();
	
	// Método de CRUD para encontrar un correo ingresado
	public Proveedor findByCorreoElectronicoContacto(String correo);
		
}
