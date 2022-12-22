package mx.uam.ingsof.proyecto.datos;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Compra;
import mx.uam.ingsof.proyecto.negocio.modelo.Venta;


/**
 * Repositorio para Compra
 * 
 * @author Eduardo Castro
 * 
 */
public interface CompraRepository extends CrudRepository<Compra, Long> {

	/**
	 * Encuentra una compra a partir de un IdCompra  
	 * Repositorio para Compra
	 * @param idCompra 
	 * @return
	 */
	
	public Compra findById(long idCompra);
	
	public List <Compra> findAll();

	public void save(Venta venta);
	
	

}
