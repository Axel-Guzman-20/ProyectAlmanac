package mx.uam.ingsof.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uam.ingsof.proyecto.datos.ClienteRepository;
import mx.uam.ingsof.proyecto.datos.CompraRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Compra;

/**
 * Servicio relacionado con las compras
 * 
 * @author AxelGuzman
 * @author EduardoCastro
 */

@Service
public class ServicioCompra {
	
	@Autowired
	private CompraRepository compraRepository;	
	private List <Compra> listaCompras = new ArrayList<Compra>();
	
	/**
	 * Retorna true si se pudo agregar, false en caso contrario
	 * @param  idEmpleado, nombreProducto, marcaProducto, cantidadProdcuto,precioProducto,fecha 
	 * @return true/false
	 */
	public  boolean agregaProducto(long idEmpleado, String nombreProducto, String marcaProducto, int cantidadProdcuto,double precioProducto,String fecha) {
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
		
		if(listaCompras.size()==0)
			return false;
		else
			return true;
	}
	/**
	 * Retorna true si se pudo guardar, false en caso contrario
	 * @param  sin parametros
	 * @return true/false
	 */
	public boolean guardaCompras() {
		if(listaCompras.size() == 0)
			return false;
		else {
			compraRepository.saveAll(listaCompras);
			return true;
		}
			
	}
	/**
	 * Retorna true si se pudo limpiar la lista, false en caso contrario
	 * @param  sin parametros
	 * @return true/false
	 */
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
