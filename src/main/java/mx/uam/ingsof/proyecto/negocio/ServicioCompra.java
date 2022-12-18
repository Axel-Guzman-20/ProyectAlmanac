package mx.uam.ingsof.proyecto.negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uam.ingsof.proyecto.datos.CompraRepository;
import mx.uam.ingsof.proyecto.datos.EmpleadoRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Compra;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;
import mx.uam.ingsof.proyecto.negocio.modelo.Venta;

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
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	private List <Compra> listaCompras = new ArrayList<>();
	
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
	
	
	
	/***********************************
	 * 
	 * 
	 * MÉTODOS NECESARIOS PARA LA 
	 * HU-10 ConsultarCompras
	 *
	 * 
	 * 
	 * 
	 ***********************************/
	
	public List<Compra> recuperaCompras (){
		
		return compraRepository.findAll();
		
	}
	
	public String[][] consultarCompras(String fechaDesde, String fechaHasta, int indexEmpleado, String nombreProveedor, String nombreProducto) {
		
		List<Compra> compras = compraRepository.findAll();
		
		// Para mostrar todas las ventas
		if(fechaDesde.equals("") && fechaHasta.equals("") && indexEmpleado == 0 && nombreProveedor.equals("") && nombreProducto.equals("")) {
			return comprasToDatos(compras);
		
		}else
			return null;
		
	}
	
	// Este metodo sirve para pasar los datos de las compras a un String[][] para la tabla
	public String[][] comprasToDatos(List<Compra> compras){
		
		String[][] datos = new String [compras.size()][7];
		
		int cantidad;
		double precio;
		double totalCompra;
		String nombreEmpleado;
		long idEmpleado;
		Empleado empleado;
		
		for (int i = 0; i < datos.length; i++) {
			
			cantidad = compras.get(i).getCantidad();
			precio = compras.get(i).getPrecio();
			totalCompra = precio * cantidad;
			
			idEmpleado = compras.get(i).getIdEmpleado();
			empleado = empleadoRepository.findByIdEmpleado(idEmpleado);
			nombreEmpleado = empleado.getNombreCompleto();
			
			
			datos[i][0] = String.valueOf(compras.get(i).getIdCompra());
			datos[i][1] = compras.get(i).getFecha();
			datos[i][2] = nombreEmpleado;
			datos[i][3] = compras.get(i).getMarca();
			datos[i][4] = compras.get(i).getNombre();
			datos[i][5] = String.valueOf(cantidad);
			datos[i][6] = String.valueOf(totalCompra);
			
		}
		
		return datos;
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
	
	
	/**
	 * Metodo que valida que las fechas de busqueda cuando se ingresa 
	 * Desde y hasta, sea congruente.
	 * 
	 * @return true cuando fechaFinal es mayor, false cuando es menor
	 * @throws ParseException 
	 */
	public boolean comparaFechas(String fechaDesde, String fechaHasta){
		
		SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
			
		Date fechaInicio;
		Date fechaFinal;
			
		try {
			fechaInicio = fechaFormato.parse(fechaDesde);
			fechaFinal = fechaFormato.parse(fechaHasta);
			
			// Significa que la fecha de inicio es mayor a la final
			if(fechaInicio.compareTo(fechaFinal) > 0) { 
				return false;
			}
			else{
				return true;	
			}
		} catch (ParseException e) {
			return false;
		} 	
	}
	
}
