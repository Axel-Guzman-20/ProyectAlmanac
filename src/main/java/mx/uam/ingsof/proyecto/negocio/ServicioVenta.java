package mx.uam.ingsof.proyecto.negocio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uam.ingsof.proyecto.datos.ClienteRepository;
import mx.uam.ingsof.proyecto.datos.EmpleadoRepository;
import mx.uam.ingsof.proyecto.datos.VentaProductoRepository;
import mx.uam.ingsof.proyecto.datos.VentaRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.Venta;
import mx.uam.ingsof.proyecto.negocio.modelo.VentaProducto;

/**
 * Servicio relacionado con las Ventas
 * 
 * @author EduardoCastro 
 * @author MiguelGuzman
 */


@Service
public class ServicioVenta {
	
	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private VentaProductoRepository ventaProductoRepository;
	
	
	
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
	
	
	
	/**
	 * Retorna una matriz de Strings que contiene la informacion que se pidió buscar
	 * @param fechaDesde, fechaHasta, nombreEmpleado, nombreCliente, nombreProducto
	 * @return matriz de String con los datos
	 */
	public String[][] consultarVentas(String fechaDesde, String fechaHasta, String nombreEmpleado, String nombreCliente, String nombreProducto) {
		
		int registrosVentas;
		int columnasTabla = 6;
		String[][] datos;
		List<Venta> ventas;
		Cliente cliente;
		Empleado empleado;
		int cantidadProductosVendidos;
		double montoVenta;
		int i;
		
		
		if(fechaDesde.equals("") && fechaHasta.equals("") && nombreEmpleado.equals("") && nombreCliente.equals("") && nombreProducto.equals("") ) {
			
			ventas = ventaRepository.findAll();
			registrosVentas = ventas.size();
			datos = new String[registrosVentas][columnasTabla];
			List<VentaProducto> ventaProducto;
			
			for (i = 0; i < datos.length; i++) {
				
				cliente = clienteRepository.findByIdCliente(ventas.get(i).getIdCliente());
				empleado = empleadoRepository.findByIdEmpleado(ventas.get(i).getIdEmpleado());
				ventaProducto = ventaProductoRepository.findByIdVenta(ventas.get(i).getIdVenta());
				cantidadProductosVendidos = cuentaProductosPorVenta(ventaProducto);
				montoVenta = montoTotalxVenta(ventaProducto);
				
				datos[i][0] = String.valueOf(ventas.get(i).getIdVenta());
				datos[i][1] = String.valueOf(ventas.get(i).getFechaVenta());
				datos[i][2] = String.valueOf(cliente.getNombreCompleto());
				datos[i][3] = String.valueOf(empleado.getNombreCompleto());
				datos[i][4] = String.valueOf(cantidadProductosVendidos);
				datos[i][5] = String.format("%.2f", montoVenta);
			}
			
			return datos;
		}
		return null;
	}
	
	
	public int cuentaProductosPorVenta (List<VentaProducto> ventaProducto) {
		
		int i;
		int cantidadProductosVendidos = 0;
		
		for (i = 0; i < ventaProducto.size(); i++) 
			cantidadProductosVendidos = cantidadProductosVendidos + ventaProducto.get(i).getCantidad();
		
		return cantidadProductosVendidos;
	}
	
	
	public double montoTotalxVenta (List<VentaProducto> ventaProducto) {
		
		int i;
		double montoVenta = 0;
						
		for (i = 0; i < ventaProducto.size(); i++)
			montoVenta = montoVenta + (ventaProducto.get(i).getCantidad() * ventaProducto.get(i).getProducto().getPrecio());
		
		return montoVenta;
	}
	
	
	
	/**
	 * Retorna el número de registros existentes en el repositorio de Ventas
	 * @return int con la cantidad de registros
	 */
	public int sizeVentas() {
		List<Venta> ventas;
		ventas = ventaRepository.findAll();
		return ventas.size();
	}
	
}
