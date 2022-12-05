package mx.uam.ingsof.proyecto.negocio;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ingsof.proyecto.datos.ClienteRepository;
import mx.uam.ingsof.proyecto.datos.CompraRepository;
import mx.uam.ingsof.proyecto.datos.EmpleadoRepository;
import mx.uam.ingsof.proyecto.datos.SeccionCatalogoRepository;
import mx.uam.ingsof.proyecto.datos.VentaProductoRepository;
import mx.uam.ingsof.proyecto.datos.VentaRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Compra;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.SeccionCatalogo;
import mx.uam.ingsof.proyecto.negocio.modelo.Venta;
import mx.uam.ingsof.proyecto.negocio.modelo.VentaProducto;
import mx.uam.ingsof.proyecto.presentacion.empleado.ControladorEmpleado;

@ExtendWith(MockitoExtension.class)

	class ServicioVentaTest {

	@Mock
	VentaRepository ventaRepository;
	ClienteRepository clienteRepository;
	EmpleadoRepository empleadoRepository;
	@InjectMocks
	ServicioVenta servicioVenta;
	VentaProductoRepository ventaProductoRepository;
	ControladorEmpleado controladorEmpleado;
	ServicioCliente servicioCliente;
	CompraRepository compraRepository;
	SeccionCatalogoRepository seccionCatalogoRepository;
	
	private Producto producto;
	private Cliente cliente;
	private Empleado empleado;
	
	
	@BeforeEach
	void setUp() throws Exception {
		

		producto = new Producto();
		cliente = new Cliente();
		empleado = new Empleado();
		
		cliente.setIdCliente(1);
		cliente.setNombreCompleto("Abel Ramirez");
		cliente.setTelefono("5535857414");
		cliente.setGenero("Masculino");
		cliente.setFechaRegistro("03/12/2022");
		cliente.setDireccion("Calle 1");
		cliente.setCorreoelectronico("a@com");
		
		
		empleado.setIdEmpleado(1);
		empleado.setNombreCompleto("David Hernandez");
		empleado.setTelefono("5535857496");
		empleado.setGenero("Masculino");
		empleado.setFechaIngreso("02/12/2022");
		empleado.setDireccionCompleta("Calle 2");
		empleado.setCorreoElectronico("aasd1@com");
		
		
		producto.setNombre("Core i3");
		producto.setMarca("Intel");
		producto.setDescripcion("10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T");
		producto.setPrecio(1499.90);
		producto.setDescuento(15);
		producto.setExistencia(7);
		producto.setTotalComprasProducto(0);
		
		
	}
	
	@Test
	void testAgregaProducto() {
		
		//Caso 1:No se pudo agregar el producto
		
		VentaProducto ventaProducto = new VentaProducto();
		VentaProducto ventaProducto2 = new VentaProducto();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			servicioVenta.agregaProducto(null, 1);
		
		});
		
		//Caso 2: Se pudo agregar el producto
		ventaProducto.setCantidad(1);
		ventaProducto.setProducto(producto);
		
		ventaProducto2 = servicioVenta.agregaProducto(producto, 1);
		assertEquals(ventaProducto,ventaProducto2);
	}

	
	@Test
	void consultarVentas() {
		
		
		
		List<Venta> ventas;
		ventas = ventaRepository.findAll();
		
		//ArrayList<Venta> nuevaVenta = new ArrayList<Venta>();
		
		// Prueba 1: corroborar que regresa una lista vacía si no hay ventas en la BD
		ventas = ventaRepository.findAll();
		assertTrue(ventas.isEmpty());
		
		
			
	}
	
	
	@Test	
	void criterioFechas() throws ParseException {
		
		Date fechaInicio;
		Date fechaFinal;
		Date fechaVenta;
		String fechaDesde = "20/11/2022"; 
		String fechaHasta = "25/11/2022"; 
		
		List<Venta> ventas;
		ventas = ventaRepository.findAll();
		
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
		
			
						
						
			//Solo tiene fecha de inicio
			}else if(!fechaDesde.equals("") && fechaHasta.equals("")) {
				fechaInicio = fechaFormato.parse(fechaDesde);
						
				for (i = 0; i < ventas.size(); i++) {
					
					fechaVenta = fechaFormato.parse(ventas.get(i).getFechaVenta());
					
					if(fechaVenta.compareTo(fechaInicio) >= 0)
						nuevaVenta.add(ventas.get(i));
				}
				
				
						
					
			// Solo tiene fecha final
			}else if(fechaDesde.equals("") && !fechaHasta.equals("")) {
				fechaFinal = fechaFormato.parse(fechaHasta);
				
				for (i = 0; i < ventas.size(); i++) {
					fechaVenta = fechaFormato.parse(ventas.get(i).getFechaVenta());
					
					if(fechaVenta.compareTo(fechaFinal) <= 0)
						nuevaVenta.add(ventas.get(i));
				}
				
				
			}
		
		
	}
	
	@Test
	void criterioEmpleado(){
		
		Long idEmpleado;
		String itemEmpleadoId = "1";
		
		List<Venta> ventas = ventaRepository.findAll();
		
		List<Venta> nuevaVenta = new ArrayList<>();
		int i;
		
		// Para mostrar de acuerdo con los item del ComboBox Empleado
		if(!itemEmpleadoId.equals("0")) {
						
			idEmpleado = Long.parseLong(itemEmpleadoId);
						
			for (i = 0; i < ventas.size(); i++) {
				
				if(idEmpleado == ventas.get(i).getIdEmpleado())
					nuevaVenta.add(ventas.get(i));
			
			}
						
			
		}
		
		
	}
	
	
	@Test
	void criterioCliente(){
		
		Long idCliente;
		String itemClienteId = "1";
		
		List<Venta> ventas = ventaRepository.findAll();
		
		List<Venta> nuevaVenta = new ArrayList<>();
		int i;
		
		// Para mostrar de acuerdo con los item del ComboBox CLiente
		if(!itemClienteId.equals("0")) {
						
			idCliente = Long.parseLong(itemClienteId);
						
			for (i = 0; i < ventas.size(); i++) {
				
				if(idCliente == ventas.get(i).getIdCliente())
					nuevaVenta.add(ventas.get(i));
			
			}
						
			
		}
		
		
	}
	
	
	@Test
	void criterioMonto(){
		
		double monto;
		List<Venta> nuevaVenta = new ArrayList<>();
		int i;
		String montoVentaIngresada = "";
		List<Venta> ventas = ventaRepository.findAll();
		
		
		// Para mostrar de acuerdo con el monto
		if(!montoVentaIngresada.equals("")) {
						
			monto = Double.parseDouble(montoVentaIngresada);
			double total;		
			
			for (i = 0; i < ventas.size(); i++) {
				
				//List<VentaProducto> ventaProducto = ventaProductoRepository.findByIdVenta(ventas.get(i).getIdVenta());
				//total = montoTotalxVenta();
				
				//if(monto == total)
					nuevaVenta.add(ventas.get(i));
			}
									
			
		}
		
		
	}
	
	
	@Test
	void cuentaProductosPorVenta () {
		
		int i;
		int cantidadProductosVendidos = 0;
		List<VentaProducto> ventaProducto =  ventaProductoRepository.findByIdVenta(1);
		
		for (i = 0; i < ventaProducto.size(); i++) 
			cantidadProductosVendidos = cantidadProductosVendidos + ventaProducto.get(i).getCantidad();
		
		
	}
	
	
	@Test
	void montoTotalxVenta () {
		
		int i;
		double montoVenta = 0;
		List<VentaProducto> ventaProducto =  ventaProductoRepository.findByIdVenta(1);
		
						
		for (i = 0; i < ventaProducto.size(); i++)
			montoVenta = montoVenta + (ventaProducto.get(i).getCantidad() * ventaProducto.get(i).getProducto().getPrecio());
		
		
	}
	
	
	@Test
	void llenarDatosString() {
		
		int registrosVentas;
		int columnasTabla = 6;
		List<Venta> ventas = ventaRepository.findAll();
		String[][] datos;
		Cliente cliente;
		Empleado empleado;
		int cantidadProductosVendidos;
		double montoVenta;
		
		int i;
		
		registrosVentas = ventas.size();
		datos = new String[registrosVentas][columnasTabla];
		
		//List<VentaProducto> ventaProducto;
		
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
			//ventaProducto = ventaProductoRepository.findByIdVenta(ventas.get(i).getIdVenta());
			//cantidadProductosVendidos = cuentaProductosPorVenta();
			//montoVenta = montoTotalxVenta();
			//datos[i][4] = String.valueOf(cantidadProductosVendidos);
			//datos[i][5] = String.format("%.2f", montoVenta);
		}
		
		
	}
	
	
	
	@Test
	void comparaFechas(){
		
		String fechaDesde = "20/11/2022"; 
		String fechaHasta = "25/11/2022"; 
		
		if(!fechaDesde.equals("") && !fechaHasta.equals("")) {
			
			SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
			
			Date fechaInicio;
			Date fechaFinal;
			
			try {
				fechaInicio = fechaFormato.parse(fechaDesde);
				fechaFinal = fechaFormato.parse(fechaHasta);
				// Significa que la fecha de inicio es mayor a la final
				//if(fechaInicio.compareTo(fechaFinal) > 0) 
				
			} catch (ParseException e) {
			} 	
		}
		
	}
	
	
	@Test
	void validarMonto() {
		
		String montoVenta = "100"; 
		
		if(!montoVenta.equals("")) {
			try
			 {
			   Double.parseDouble(montoVenta);
			 }
			 catch(NumberFormatException nfe)
			 {
			 }	
		}
	}
	
	
	@Test
	void sizeVentas() {
		List<Venta> ventas;
		ventas = ventaRepository.findAll();
	}
	
	
	
}
