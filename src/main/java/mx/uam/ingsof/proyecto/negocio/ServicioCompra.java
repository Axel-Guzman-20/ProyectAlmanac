package mx.uam.ingsof.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ingsof.proyecto.datos.ClienteRepository;
import mx.uam.ingsof.proyecto.datos.CompraRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Compra;
/**
 * Servicio relacionado con las compras
 * 
 * @author AxelGuzman
 * @author eduardoCastro
 */
@Service
public class ServicioCompra {
	
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	private List <Compra> listaCompras = new ArrayList<Compra>();
	
	/**
	 * Recupera todas las compras de un cliente.
	 * @param IdCliente 
	 * @return Compras de un cliente
	 */
	
	public List <Compra> recuperaTodasCompras(Cliente idCliente){
		
	    Cliente cliente =  clienteRepository.findByIdCliente(idCliente.getIdCliente());
		
		if( cliente == null) {
			throw new IllegalArgumentException("El cliente no existe "); //Validamos la existencia del cliente que se paso 
		}
		
		List<Compra> compras = clienteRepository.findBycompras(cliente);
		
		if(compras == null) {
			throw new IllegalArgumentException("No se encontraron compras");
		}		
		return compras;
	}
	/**
	 * Retorna una compra si no existe null
	 * @param IdCliente 
	 * @return Compra de un cliente/null
	 */
	
	
	public  boolean creaGrantia(long idEmpleado, String nombreProducto, String marcaProducto, int cantidadProdcuto,double precioProducto,String fecha) {
		if(idEmpleado == 0)
			throw new IllegalArgumentException("El id no es valido");
		if(nombreProducto == null)
			throw new IllegalArgumentException("El nombre es null, ingresa un nombre valido");
		if(marcaProducto ==  null)
			throw new IllegalArgumentException("El nombre es null, ingresa un nombre valido");
		if(cantidadProdcuto == 0)
			throw new IllegalArgumentException("No es valido el numero de productos");
		if(precioProducto == 0)
			throw new IllegalArgumentException("No es valido el precio de los prodctos");
		if(fecha ==  null)
			throw new IllegalArgumentException("La fecha no es valida");

		Compra compra = new Compra();
		compra.setNombre(nombreProducto);
		compra.setMarca(marcaProducto);
		compra.setFecha(fecha);
		compra.setPrecio(precioProducto);
		compra.setIdEmpleado(idEmpleado);
		compra.setCantidad(cantidadProdcuto);
		listaCompras.add(compra);
		return true;
	}
	public boolean guardaCompras() {
		if(listaCompras.size() == 0)
			return false;
		else {
			compraRepository.saveAll(listaCompras);
			return true;
		}
			
	}
	public boolean limpiaCompras() {
		if(listaCompras.size() == 0) {
			return true;
		}else {
			if(listaCompras.size() != 0) {
				listaCompras.clear();
				return true;
			}else {
				return false;
			}
		}
	}
	
}
