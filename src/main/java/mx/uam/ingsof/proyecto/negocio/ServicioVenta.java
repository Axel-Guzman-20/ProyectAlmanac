package mx.uam.ingsof.proyecto.negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	
	
	
	
	
	
	
	/***********************************
	 * 
	 * 
	 * MÉTODOS NECESARIOS PARA LA 
	 * HU-09 ConsultarVentas
	 *
	 * 
	 * 
	 * 
	 ***********************************/
	
	/**
	 * Retorna una matriz de Strings que contiene la informacion que se pidió buscar
	 * @param fechaDesde, fechaHasta, nombreEmpleado, nombreCliente, nombreProducto
	 * @return matriz de String con los datos
	 * @throws ParseException 
	 */
	public String[][] consultarVentas(String fechaDesde, String fechaHasta, String itemEmpleadoId, String itemClienteId, String montoVentaIngresada) throws ParseException {
				
		List<Venta> ventas;
		ventas = ventaRepository.findAll();
		
		// Para mostrar todas las ventas
		if(fechaDesde.equals("") && fechaHasta.equals("") && itemEmpleadoId.equals("0") && itemClienteId.equals("0") && montoVentaIngresada.equals(""))
			return llenarDatosString(ventas);	
		
		// Si no son vacias algunas de las fechas, aplica el criterio
		if( !fechaDesde.equals("") || !fechaHasta.equals("") )
			ventas = criterioFechas(fechaDesde, fechaHasta, ventas);
		
		// Si no se selecciono algun empleado, no entra al método ya que no requiere un críterio de empleado
		if(!itemEmpleadoId.equals("0") && ventas.size() != 0)
			ventas = criterioEmpleado(itemEmpleadoId, ventas);
			
		// Si no se selecciono algun cliente, no entra al método ya que no requiere un críterio de cliente
		if(!itemClienteId.equals("0") && ventas.size() != 0)
			ventas = criterioCliente(itemClienteId, ventas);
		
		// Si no se ingresó un monto, no entra al método ya que no requiere un críterio
		if(!montoVentaIngresada.equals("") && ventas.size() != 0)
			ventas = criterioMonto(montoVentaIngresada, ventas);
		
		if(ventas.size() != 0)
			return llenarDatosString(ventas);
		else
			return null;
		
	}
	
	
		
	public List<Venta> criterioFechas(String fechaDesde, String fechaHasta, List<Venta> ventas) throws ParseException{
		
		Date fechaInicio;
		Date fechaFinal;
		Date fechaVenta;
		SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
		List<Venta> nuevaVenta = new ArrayList<>();
		int i;
		
		
		// Para mostrar de acuerdo con las fechas
		if(!fechaDesde.equals("") && !fechaHasta.equals("")) {		
			fechaInicio = fechaFormato.parse(fechaDesde);
			fechaFinal = fechaFormato.parse(fechaHasta);
						
			for (i = 0; i < ventas.size(); i++) {
				
				fechaVenta = fechaFormato.parse(ventas.get(i).getFechaVenta());
				
				if(fechaVenta.compareTo(fechaInicio) >= 0 && fechaVenta.compareTo(fechaFinal) <= 0)
					nuevaVenta.add(ventas.get(i));
				
			}
		
			return nuevaVenta;	
		}
		
		
		//Solo tiene fecha de inicio
		if(!fechaDesde.equals("") && fechaHasta.equals("")) {
			fechaInicio = fechaFormato.parse(fechaDesde);
						
			for (i = 0; i < ventas.size(); i++) {
					
				fechaVenta = fechaFormato.parse(ventas.get(i).getFechaVenta());
				
				if(fechaVenta.compareTo(fechaInicio) >= 0)
					nuevaVenta.add(ventas.get(i));
			}
				
			return nuevaVenta;						
		}
		
		
		// Solo tiene fecha final
		if(fechaDesde.equals("") && !fechaHasta.equals("")) {
			fechaFinal = fechaFormato.parse(fechaHasta);
			
			for (i = 0; i < ventas.size(); i++) {
				fechaVenta = fechaFormato.parse(ventas.get(i).getFechaVenta());
					
				if(fechaVenta.compareTo(fechaFinal) <= 0)
					nuevaVenta.add(ventas.get(i));
			}
			
			return nuevaVenta;
		}
		
		return ventas;
	}
	
	
	public List<Venta> criterioEmpleado(String itemEmpleadoId, List<Venta> ventas){
		
		Long idEmpleado;
		List<Venta> nuevaVenta = new ArrayList<>();
		int i;
		
		idEmpleado = Long.parseLong(itemEmpleadoId);
						
		for (i = 0; i < ventas.size(); i++) {
			
			if(idEmpleado == ventas.get(i).getIdEmpleado())
				nuevaVenta.add(ventas.get(i));
		
		}
						
		return nuevaVenta;
	}
	
	
	public List<Venta> criterioCliente(String itemClienteId, List<Venta> ventas){
		
		Long idCliente;
		List<Venta> nuevaVenta = new ArrayList<>();
		int i;
						
		idCliente = Long.parseLong(itemClienteId);
						
		for (i = 0; i < ventas.size(); i++) {
				
			if(idCliente == ventas.get(i).getIdCliente())
				nuevaVenta.add(ventas.get(i));
			
		}
						
		return nuevaVenta;

	}
	
	
	public List<Venta> criterioMonto(String montoVentaIngresada, List<Venta> ventas){
		
		double monto;
		List<Venta> nuevaVenta = new ArrayList<>();
		int i;
		
		// Para mostrar de acuerdo con el monto
		if(!montoVentaIngresada.equals("")) {
						
			monto = Double.parseDouble(montoVentaIngresada);
			double total;		
			
			for (i = 0; i < ventas.size(); i++) {
				
				List<VentaProducto> ventaProducto = ventaProductoRepository.findByIdVenta(ventas.get(i).getIdVenta());
				total = montoTotalxVenta(ventaProducto);
				
				if(monto == total)
					nuevaVenta.add(ventas.get(i));
			}
									
			return nuevaVenta;
		}
		
		return ventas;
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
	
	
	public String[][] llenarDatosString(List<Venta> ventas) {
		
		int registrosVentas;
		int columnasTabla = 6;
		
		String[][] datos;
		Cliente cliente;
		Empleado empleado;
		int cantidadProductosVendidos;
		double montoVenta;
		
		int i;
		
		registrosVentas = ventas.size();
		datos = new String[registrosVentas][columnasTabla];
		
		List<VentaProducto> ventaProducto;
		
		for (i = 0; i < datos.length; i++) {			
			// Estos datos ya vienen en la venta
			datos[i][0] = String.valueOf(ventas.get(i).getIdVenta());
			datos[i][1] = String.valueOf(ventas.get(i).getFechaVenta());
			
			// Buscamos en los repositorios correspondiente el nombre de los empleados
			cliente = clienteRepository.findByIdCliente(ventas.get(i).getIdCliente());
			empleado = empleadoRepository.findByIdEmpleado(ventas.get(i).getIdEmpleado());
			datos[i][2] = String.valueOf(cliente.getNombreCompleto());
			datos[i][3] = String.valueOf(empleado.getNombreCompleto());
			
			// Buscamos en el repositorio de la venta producto, el idVenta
			ventaProducto = ventaProductoRepository.findByIdVenta(ventas.get(i).getIdVenta());
			cantidadProductosVendidos = cuentaProductosPorVenta(ventaProducto);
			montoVenta = montoTotalxVenta(ventaProducto);
			datos[i][4] = String.valueOf(cantidadProductosVendidos);
			datos[i][5] = String.format("%.2f", montoVenta);
		}
		
		return datos;
	}
	
	
	
	/**
	 * Retorna true cuando las fechas inicio y final son validas
	 * @return true cuando final es mayor, false cuando es menor
	 * @throws ParseException 
	 */
	public boolean comparaFechas(String fechaDesde, String fechaHasta){
		
		if(!fechaDesde.equals("") && !fechaHasta.equals("")) {
			
			SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
			
			Date fechaInicio;
			Date fechaFinal;
			
			try {
				fechaInicio = fechaFormato.parse(fechaDesde);
				fechaFinal = fechaFormato.parse(fechaHasta);
				// Significa que la fecha de inicio es mayor a la final
				if(fechaInicio.compareTo(fechaFinal) > 0) 
					return false;
				
				return true;			
			} catch (ParseException e) {
				return false;
			} 	
		}
		return true;
	}
	
	
	public boolean validarMonto(String montoVenta) {
		if(!montoVenta.equals("")) {
			try
			 {
			   Double.parseDouble(montoVenta);
			   return true;
			 }
			 catch(NumberFormatException nfe)
			 {
			   return false;
			 }	
		}
		return true;
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
