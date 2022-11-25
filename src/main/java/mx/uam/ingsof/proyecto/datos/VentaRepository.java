package mx.uam.ingsof.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ingsof.proyecto.negocio.modelo.Venta;

/**
 * Repositorio para venta
 * 
 * @author Eduardo Castro
 *
 */
public interface VentaRepository extends CrudRepository<Venta, Long> {

	/**
	 * Encuentra una compra a partir de un IdCompra  
	 * Repositorio para Compra
	 * @param idCompra 
	 * @return
	 */
	
	public Venta findById(long idCompra);

	public List <Venta> findAll();
	

}
