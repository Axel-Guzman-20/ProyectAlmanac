package mx.uam.ingsof.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ingsof.proyecto.negocio.modelo.Compra;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;


/**
 * Repositorio para Cliente
 * 
 *
 * @author Axel Guzman
 * 
 */
public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
	// Cuenta cuantos registros existen
	public long count();
	
	// Método de CRUD para encontrar un correo ingresado
	public Cliente findBycorreoelectronico(String correo);
	
	public List <Compra> findBycompras(Cliente cliente);
	public Cliente findByIdCliente(long idCliente);
	
	public List <Cliente>findAll(); // El Repository recupera a una lista de clientes  
	
		
}
