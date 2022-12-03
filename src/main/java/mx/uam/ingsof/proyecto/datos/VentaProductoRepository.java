package mx.uam.ingsof.proyecto.datos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.VentaProducto;

/**
 * Esta clase controla el repositorio de los proveedores
 * 
 * @author Eduardo Castro
 * @author MiguelGuzman
 *
 */
public interface VentaProductoRepository extends CrudRepository<VentaProducto, Long> {

	public List<VentaProducto> findByIdVentaProducto(long idVenta);
	
	@Query(value = "select * from Venta_Producto WHERE Id_venta = ?", nativeQuery = true)
	List<VentaProducto> findByIdVenta(long id_Venta);
	
}
