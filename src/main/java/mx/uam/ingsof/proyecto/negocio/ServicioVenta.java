package mx.uam.ingsof.proyecto.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ingsof.proyecto.datos.VentaRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.Venta;
import mx.uam.ingsof.proyecto.negocio.modelo.VentaProducto;

/**
 * Servicio relacionado con las compras
 * 
 * @author erikamaya 
 * @author eduardoCastro
 */
@Service
public class ServicioVenta {
	
	@Autowired
	private VentaRepository ventaRepository;
	
	
	
	/**
	 * Retorna una compra si no existe null
	 * @param IdCliente 
	 * @return Compra de un cliente/null
	 */
	public Venta obtenCompra(long idCompra) {
		Venta venta = new Venta();
		venta =  ventaRepository.findById(idCompra); 
		return venta;
	}
	/**
	 * Retorna  una VentaProducto despues de crearla
	 * @param Producto, cantidad
	 * @return VentaPriducto/null
	 */
	public VentaProducto agregaProducto(Producto producto,int cantidad) {
		Venta venta = new Venta();
		VentaProducto ventaProducto = new VentaProducto();
		ventaProducto = venta.agregaProducto(producto, cantidad);
		return ventaProducto;
	}
	/**
	 * Retorna Retorna una venta despues de crearla
	 * @param IdCliente, idEmpleado, fecha,lista de productos
	 * @return venta de un cliente/null
	 */
	public Venta creaVenta(Long idCliente,Long idEmpleado, String fecha,List<VentaProducto> productos) {
		
		if(idCliente == null)
			throw new IllegalArgumentException("Id Cliente  no debe ser null");
		if(idEmpleado == null)
			throw new IllegalArgumentException("Id Empleado no debe ser null");
		if(fecha == null)
			throw new IllegalArgumentException("La fecha no debe ser null");
		if(productos.size() == 0)
			throw new IllegalArgumentException("La lista esta vacia");
		Venta venta = new Venta();
		venta.setIdCliente(idCliente);
		venta.setIdEmpleado(idEmpleado);
		venta.setFechaVenta(fecha);
		venta.setListaProducto(productos);
		venta = ventaRepository.save(venta);
		
		return venta;
	}
	
}
