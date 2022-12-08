package mx.uam.ingsof.proyecto.datos;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;

/**
 * Repositorio para Producto
 * @author Abigail Morales
 * @author Eduardo Castro
 *
 */

public interface ProductoRepository extends CrudRepository<Producto, Long> {
	/**
	 * Encuentra al producto a partir de un id de producto
	 * Repositorio para Producto
	 * @param idProducto, idSeccionCatalogo, producto
	 * @return Producto, List <Producto>
	 */
	
	public Producto findByNombre(String nombre);
	
	public List <Producto>	findAll();
	
}
