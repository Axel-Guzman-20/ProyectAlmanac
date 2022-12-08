package mx.uam.ingsof.proyecto.negocio;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.util.ArrayList;
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
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.Venta;
import mx.uam.ingsof.proyecto.negocio.modelo.VentaProducto;
import mx.uam.ingsof.proyecto.presentacion.empleado.ControladorEmpleado;

@ExtendWith(MockitoExtension.class)

	class ServicioVentaTest {

	@Mock
	VentaRepository ventaRepository;
	
	@Mock
	ClienteRepository clienteRepository;
	
	@Mock
	EmpleadoRepository empleadoRepository;
	
	@Mock
	VentaProductoRepository ventaProductoRepository;
	
	@Mock
	CompraRepository compraRepository;
	
	@Mock
	SeccionCatalogoRepository seccionCatalogoRepository;
	
	@InjectMocks
	ServicioVenta servicioVenta;
	
	@InjectMocks
	ControladorEmpleado controladorEmpleado;
	
	@InjectMocks
	ServicioCliente servicioCliente;
	
	
	private Producto producto;
	private Cliente cliente1, cliente2;
	private Empleado empleado, empleado2;
	private Venta venta1;
	private VentaProducto ventaProducto1;
	private ArrayList<VentaProducto> listaProductos;
	
	@BeforeEach
	void setUp() throws Exception {
		

		producto = new Producto();
		cliente1 = new Cliente();
		cliente2 = new Cliente();
		empleado = new Empleado();
		empleado2 = new Empleado();
		venta1 = new Venta();
		
		cliente1.setIdCliente(1);
		cliente1.setNombreCompleto("Cliente 1");
		cliente1.setTelefono("5535857414");
		cliente1.setGenero("Masculino");
		cliente1.setFechaRegistro("03/12/2022");
		cliente1.setDireccion("Calle 1");
		cliente1.setCorreoelectronico("c1@com");
		
		
		cliente2.setIdCliente(2);
		cliente2.setNombreCompleto("Cliente 2");
		cliente2.setTelefono("5535857414");
		cliente2.setGenero("Masculino");
		cliente2.setFechaRegistro("03/12/2022");
		cliente2.setDireccion("Calle 1");
		cliente2.setCorreoelectronico("c2@com");
		
		
		empleado.setIdEmpleado(1);
		empleado.setNombreCompleto("Empleado 1");
		empleado.setTelefono("5535857496");
		empleado.setGenero("Masculino");
		empleado.setFechaIngreso("02/12/2022");
		empleado.setDireccionCompleta("Calle 2");
		empleado.setCorreoElectronico("e1@com");
		
		empleado2.setIdEmpleado(2);
		empleado2.setNombreCompleto("Empleado 2");
		empleado2.setTelefono("5535857496");
		empleado2.setGenero("Masculino");
		empleado2.setFechaIngreso("02/12/2022");
		empleado2.setDireccionCompleta("Calle 2");
		empleado2.setCorreoElectronico("e2@com");
		
		
		producto.setNombre("Core i3");
		producto.setMarca("Intel");
		producto.setDescripcion("10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T");
		producto.setPrecio(1499.90);
		producto.setDescuento(15);
		producto.setExistencia(7);
		producto.setTotalComprasProducto(0);
		
		
		listaProductos = new ArrayList<VentaProducto>();
						
		ventaProducto1 = new VentaProducto();
					
		ventaProducto1.setIdVentaProducto(1);
		ventaProducto1.setCantidad(3);
		ventaProducto1.setProducto(producto);
						
		listaProductos.add(ventaProducto1);
						
		venta1 = new Venta();
						
		venta1.setFechaVenta("20/11/2022");
		venta1.setGarantia(null);
		venta1.setIdCliente(1);
		venta1.setIdEmpleado(1);
		venta1.setIdVenta(1);
		venta1.setListaProducto(listaProductos);
		
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
	
	
	
	
	
	/*************************************
	 * 
	 * 
	 * PRUEBAS DE USUARIO DE LA HU-09
	 * CONSULTAR VENTAS
	 * MIGUEL GUZMAN
	 * 
	 * 
	 * ********************************
	 */
	
	
	@Test
	void comparaFechas(){
		
		String fechaDesde; 
		String fechaHasta; 
		boolean fechaValida;
		
		// Prueba 1: Si las dos fecha son vacías, devuelve true porque no se utilizan las fechas
		fechaDesde = ""; 
		fechaHasta = "";
		fechaValida = servicioVenta.comparaFechas(fechaDesde, fechaHasta);
		assertTrue(fechaValida);
		
		// Prueba 2: Si fechaDesde es mayor a fechaHasta, devuelve falso
		fechaDesde = "05/12/2022"; 
		fechaHasta = "04/12/2022";
		fechaValida = servicioVenta.comparaFechas(fechaDesde, fechaHasta);
		assertFalse(fechaValida);
		
		// Prueba 3: Si fechaDesde es menor a fechaHasta, devuelve true
		fechaDesde = "05/12/2022"; 
		fechaHasta = "06/12/2022";
		fechaValida = servicioVenta.comparaFechas(fechaDesde, fechaHasta);
		assertTrue(fechaValida);	
		
		// Prueba 3: Si fechaDesde es igual a fechaHasta, devuelve true
		fechaDesde = "05/12/2022"; 
		fechaHasta = "05/12/2022";
		fechaValida = servicioVenta.comparaFechas(fechaDesde, fechaHasta);
		assertTrue(fechaValida);
		
	}
	
	
	@Test
	void validarMonto() {
		
		String montoVenta;
		boolean montoValido;
		
		// Prueba 1: Si el monto es vacío, devuelve true porque no se utiliza el monto
		montoVenta = ""; 
		montoValido = servicioVenta.validarMonto(montoVenta);
		assertTrue(montoValido);
				
		// Prueba 2: Si el monto tiene un entero, devuelve true porque es un número valido
		montoVenta = "100"; 
		montoValido = servicioVenta.validarMonto(montoVenta);
		assertTrue(montoValido);
		
		// Prueba 3: Si el monto tiene un entero y decimales, devuelve true porque es un número valido
		montoVenta = "100.105"; 
		montoValido = servicioVenta.validarMonto(montoVenta);
		assertTrue(montoValido);
		
		// Prueba 4: Si el monto tiene un entero y decimales y cualquier otro caracter que no sea númerico, devuelve false porque es valido
		montoVenta = "100.105a"; 
		montoValido = servicioVenta.validarMonto(montoVenta);
		assertFalse(montoValido);
						
	}
	
	
	@Test
	void sizeVentas() {
		
		List<Venta> ventas = new ArrayList<Venta>();
		int noVentas;
		
		// Prueba 1: Si la lista es vacía, indica que no hay ventas y regresa un 0
		when(ventaRepository.findAll()).thenReturn(ventas);
		noVentas = servicioVenta.sizeVentas();
		assertEquals(0, noVentas);
		
		// Prueba 2: Corroborar que regresa un numero entero, que es la cantidad de registros, en este caso suponemos que solo hay 1 registro
		ventas.add(venta1);
		when(ventaRepository.findAll()).thenReturn(ventas);
		noVentas = servicioVenta.sizeVentas();
		assertNotEquals(0, noVentas);
		
	}
	
	@Test
	void criterioFechas() throws ParseException{
		String fechaDesde; 
		String fechaHasta; 
		
		List<Venta> ventas = new ArrayList<Venta>();
		ventas.add(venta1);
		int registrosEncontrados;
				
		//Prueba 1: Corroborar que regresa un número entero (son todos los registros) cuando no se usa fechaDesde y fechaHasta
		fechaDesde = ""; 
		fechaHasta = "";
		ventas = servicioVenta.criterioFechas(fechaDesde, fechaHasta, ventas);
		registrosEncontrados = ventas.size();
		assertNotEquals(0, registrosEncontrados);
		
		//Prueba 2: Corroborar que regresa un número entero (son los registros encontrados) cuando se usa fechaDesde y fechaHasta
		fechaDesde = "20/11/2022"; 
		fechaHasta = "21/11/2022";
		ventas = servicioVenta.criterioFechas(fechaDesde, fechaHasta, ventas);
		registrosEncontrados = ventas.size();
		assertNotEquals(0, registrosEncontrados);
		
		//Prueba 3: Corroborar que regresa un número entero (son los registros encontrados) cuando se usa fechaDesde
		fechaDesde = "20/11/2022"; 
		fechaHasta = "";
		ventas = servicioVenta.criterioFechas(fechaDesde, fechaHasta, ventas);
		registrosEncontrados = ventas.size();
		assertNotEquals(0, registrosEncontrados);	
		
		//Prueba 4: Corroborar que regresa un número entero (son los registros encontrados) cuando se usa fechaHasta
		fechaDesde = ""; 
		fechaHasta = "20/11/2022";
		ventas = servicioVenta.criterioFechas(fechaDesde, fechaHasta, ventas);
		registrosEncontrados = ventas.size();
		assertNotEquals(0, registrosEncontrados);
		
		//Prueba 5: Corroborar que regresa una lista sin registros (no se encontraron registros) cuando se usa fechaDesde y fechaHasta
		fechaDesde = "21/11/2022"; 
		fechaHasta = "21/11/2022";
		ventas = servicioVenta.criterioFechas(fechaDesde, fechaHasta, ventas);
		registrosEncontrados = ventas.size();
		assertEquals(0, registrosEncontrados);
		
		// Se vuelve a agregar porque los anteriores modificador a venta, lo quitaron
		ventas.add(venta1);
		//Prueba 6: Corroborar que regresa una lista sin registros (no se encontraron registros) cuando se usa fechaDesde
		fechaDesde = "21/11/2022"; 
		fechaHasta = "";
		ventas = servicioVenta.criterioFechas(fechaDesde, fechaHasta, ventas);
		registrosEncontrados = ventas.size();
		assertEquals(0, registrosEncontrados);	
		
		// Se vuelve a agregar porque los anteriores modificador a venta, lo quitaron
		ventas.add(venta1);
		//Prueba 7: Corroborar que regresa una lista sin registros (no se encontraron registros) cuando se usa  fechaHasta
		fechaDesde = ""; 
		fechaHasta = "19/11/2022";
		ventas = servicioVenta.criterioFechas(fechaDesde, fechaHasta, ventas);
		registrosEncontrados = ventas.size();
		assertEquals(0, registrosEncontrados);		
		
		// Se vuelve a agregar porque los anteriores modificador a venta, lo quitaron
		ventas.add(venta1);
		//Prueba 8: Corroborar que regresa un número entero (son los registros encontrados) cuando se usa fechaDesde y fechaHasta y tienen la misma fecha
		fechaDesde = "20/11/2022"; 
		fechaHasta = "20/11/2022";
		ventas = servicioVenta.criterioFechas(fechaDesde, fechaHasta, ventas);
		registrosEncontrados = ventas.size();
		assertNotEquals(0, registrosEncontrados);
	
	}
	
	
	@Test
	void criterioEmpleado() throws ParseException{
		
		List<Venta> ventas = new ArrayList<Venta>();
		String [][] datos;
		
		ventas.add(venta1);
		
		when(ventaRepository.findAll()).thenReturn(ventas);
		when(clienteRepository.findByIdCliente(1)).thenReturn(cliente2);
		when(empleadoRepository.findByIdEmpleado(1)).thenReturn(empleado);
		
		//Prueba 1: Corroborar que regresa un null cuando no hay ventas con el nombre del empleado seleccionado
		datos = servicioVenta.consultarVentas("", "", "2", "0", "");
		assertEquals(null, datos);
				
		// Prueba 2: Corroborar que regresa un lista cuando hay ventas con el nombre del empleado seleccionado
		datos = servicioVenta.consultarVentas("", "", "1", "0", "");
		assertNotEquals(null, datos);
		
	}
	
	@Test
	void criterioCliente() throws ParseException{
		
		List<Venta> ventas = new ArrayList<Venta>();
		String [][] datos;
		
		ventas.add(venta1);
		
		when(ventaRepository.findAll()).thenReturn(ventas);
		when(clienteRepository.findByIdCliente(1)).thenReturn(cliente1);
		when(empleadoRepository.findByIdEmpleado(1)).thenReturn(empleado);
		
		//Prueba 1: Corroborar que regresa un null cuando no hay ventas con el nombre del cliente seleccionado
		datos = servicioVenta.consultarVentas("", "", "0", "2", "");
		assertEquals(null, datos);
				
		// Prueba 2: Corroborar que regresa un lista cuando hay ventas con el nombre del cliente seleccionado
		datos = servicioVenta.consultarVentas("", "", "0", "1", "");
		assertNotEquals(null, datos);
		
	}
	
	
	@Test
	void consultarVentas() throws ParseException {
		
		List<Venta> ventas = new ArrayList<Venta>();
		String [][] datos;
		
		ventas.add(venta1);
		
		when(ventaRepository.findAll()).thenReturn(ventas);
		when(clienteRepository.findByIdCliente(1)).thenReturn(cliente1);
		when(empleadoRepository.findByIdEmpleado(1)).thenReturn(empleado);
		
		// Prueba 1: corroborar que regresa una lista si hay ventas
		datos = servicioVenta.consultarVentas("", "", "0", "0", "");
		assertNotEquals(null, datos);
		
		// Prueba 2: Corroborar que regresa un null cuando hay ventas de acuerdo con los criterios de busqueda establecido
		datos = servicioVenta.consultarVentas("", "", "1", "1", "100");
		assertEquals(null, datos);
		
	}
		
}
