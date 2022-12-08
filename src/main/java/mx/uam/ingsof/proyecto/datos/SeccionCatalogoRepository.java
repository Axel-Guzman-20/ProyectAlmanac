package mx.uam.ingsof.proyecto.datos;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.SeccionCatalogo;


/**
 * Repositorio para SeccionCatalogo
 * 
 * @author Abigail Morales Mariscal
 *
 */
public interface SeccionCatalogoRepository extends CrudRepository<SeccionCatalogo, Long> {
	/**
	 * Encuentra una sección a partir de un nombre
	 * Repositorio para SeccionCatalogo
	 * @param idSeccionCatalogo, seccion 
	 * @return SeccionCatalogo, List <SeccionCatalogo> 
	 */
	public SeccionCatalogo findByNombre(String nombre);
	
	public SeccionCatalogo findByProductos(Producto producto); 

	public List <SeccionCatalogo> findAll();
	
	
}
