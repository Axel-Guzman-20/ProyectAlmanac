package mx.uam.ingsof.proyecto.negocio;

import java.io.Serializable;
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

/**
 * Servicio relacionado con las compras
 * 
 * @author AxelGuzman
 * @author EduardoCastro
 * @author MiguelGuzman
 */

@Service
public class ServicioCompra implements Serializable{
	
	
	/**
	 * Nos indica la versión del servicio de la entidad Compras
	 */
	private static final long serialVersionUID = 1L;
	
	
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
	
	public String[][] consultarCompras(String fechaDesde, String fechaHasta, int indexEmpleado, String nombreMarca, String nombreProducto) throws ParseException {
		
		List<Compra> compras = compraRepository.findAll();
		
		// Para mostrar todas las ventas
		if(fechaDesde.isEmpty() && fechaHasta.isEmpty() && indexEmpleado == 0 && nombreMarca.isEmpty() && nombreProducto.isEmpty()  ) 
			return comprasToDatos(compras);
		
		
		if(!fechaDesde.isEmpty() || !fechaHasta.isEmpty()) 
			compras = criterioFechas(fechaDesde, fechaHasta, compras);
		
		// 0 == Ningun empleado (--Seleccione una opción--
		if(indexEmpleado != 0 && !compras.isEmpty()) 
			compras = criterioEmpleado(indexEmpleado, compras);
		
		if( !nombreMarca.isEmpty() && !compras.isEmpty())
			compras = criterioMarca(nombreMarca, compras);
		
		if( !nombreProducto.isEmpty() && !compras.isEmpty())
			compras = criterioProducto(nombreProducto, compras);
				
		return comprasToDatos(compras);
		
	}
	
	
	// Buscas las compras con base a los criterios de fecha
	public List<Compra> criterioFechas(String fechaDesde, String fechaHasta, List<Compra> compras) throws ParseException{
		
		Date fechaInicio;
		Date fechaFinal;
		Date fechaCompra;
		SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
		List<Compra> nuevaCompra = new ArrayList<>();
		int i;
		
		
		// Para mostrar de acuerdo con las fechas
		if( !fechaDesde.isEmpty() && !fechaHasta.isEmpty() ) {		
			fechaInicio = fechaFormato.parse(fechaDesde);
			fechaFinal = fechaFormato.parse(fechaHasta);
						
			for (i = 0; i < compras.size(); i++) {
				
				fechaCompra = fechaFormato.parse(compras.get(i).getFecha());
				
				if(fechaCompra.compareTo(fechaInicio) >= 0 && fechaCompra.compareTo(fechaFinal) <= 0)
					nuevaCompra.add(compras.get(i));
				
			}
			
			return nuevaCompra;	
		
		//Solo tiene fecha de inicio
		}else if( !fechaDesde.isEmpty() && fechaHasta.isEmpty() ) {
			fechaInicio = fechaFormato.parse(fechaDesde);
					
			for (i = 0; i < compras.size(); i++) {
					
				fechaCompra = fechaFormato.parse(compras.get(i).getFecha());
				
				if(fechaCompra.compareTo(fechaInicio) >= 0)
					nuevaCompra.add(compras.get(i));
			}
			
			return nuevaCompra;		
			
		// Solo tiene fecha final
		}else {
			
			fechaFinal = fechaFormato.parse(fechaHasta);
			
			for (i = 0; i < compras.size(); i++) {
				fechaCompra = fechaFormato.parse(compras.get(i).getFecha());
					
				if(fechaCompra.compareTo(fechaFinal) <= 0)
					nuevaCompra.add(compras.get(i));
			}
			
			return nuevaCompra;
		}
		
	}
	
	
	
	public List<Compra> criterioEmpleado(int indexEmpleado, List<Compra> compras){
		
		List<Compra> nuevaCompra = new ArrayList<>();
		int i;
						
		for (i = 0; i < compras.size(); i++) {
			
			if(indexEmpleado == compras.get(i).getIdEmpleado())
				nuevaCompra.add(compras.get(i));
		
		}
						
		return nuevaCompra;
	}
	
	public List<Compra> criterioMarca(String nombreMarca, List<Compra> compras){
		
		List<Compra> nuevaCompra = new ArrayList<>();
		int i, j;
		int longitudMarca = nombreMarca.length();
		String marca;
		String aux = "";
		
		for (i = 0; i < compras.size(); i++) {
			
			marca = compras.get(i).getMarca();
				
			if(marca.length() >= longitudMarca) {
				
				for (j = 0; j <= marca.length() - longitudMarca; j++) {
					
					aux = marca.substring(j, j + longitudMarca);
					
					if(nombreMarca.equalsIgnoreCase(aux)) 
						nuevaCompra.add(compras.get(i));
					
					aux = "";
				}
			}
			
		}
						
		return nuevaCompra;
	}
	
	
	public List<Compra> criterioProducto(String nombreProducto, List<Compra> compras){
		
		List<Compra> nuevaCompra = new ArrayList<>();
		int i;
		int j;
		int longitudProducto = nombreProducto.length();
		String producto;
		String aux = "";
		
		for (i = 0; i < compras.size(); i++) {
			
			producto = compras.get(i).getNombre();
				
			if(producto.length() >= longitudProducto) {
				
				for (j = 0; j <= producto.length() - longitudProducto; j++) {
					
					aux = producto.substring(j, j + longitudProducto);
					
					if(nombreProducto.equalsIgnoreCase(aux))
						nuevaCompra.add(compras.get(i));
					
					aux = "";
				}
			}
			
		}
						
		return nuevaCompra;
	}
	
	
	/**
	 * Metodo que combierte los datos de Compras
	 * a una matriz de string
	 * 
	 * @return String[][]
	 * @throws ParseException 
	 */
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
				
				// Con base al idEmpleado, busca el nombre de ese empleado
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
