package mx.uam.ingsof.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ingsof.proyecto.datos.ClienteRepository;
import mx.uam.ingsof.proyecto.datos.VentaProductoRepository;
import mx.uam.ingsof.proyecto.datos.VentaRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;

import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.Venta;
import mx.uam.ingsof.proyecto.negocio.modelo.VentaProducto;


/**
 * Implementacion de las pruebas unitarias del ServicioCliente
 * 
 * @author AxelGuzman
 *
 */

@ExtendWith(MockitoExtension.class)
class ServicioClienteTest {

	@Mock
	private ClienteRepository clienteRepository;


	@Mock
	private VentaRepository ventaRepository;
	
	@Mock
	private VentaProductoRepository ventaProductoRepository;
	
	@InjectMocks
	private ServicioCliente servicioCliente;
	
	@InjectMocks
	private ServicioVenta servicioVenta;
	
	private Venta venta1;

	private Cliente cliente;
	
	private ArrayList<VentaProducto> listaProductos;
	
	private Producto producto;
	
	private VentaProducto ventaProducto1;
	

	@BeforeEach
	void setUp() throws Exception {

		cliente = new Cliente();

		cliente.setFechaRegistro("22/11/2022");
		cliente.setIdCliente(1);
		cliente.setNombreCompleto("Yael Ortega");
		cliente.setDireccion("Ciudad de México");
		cliente.setTelefono("5520204585");
		cliente.setCorreoelectronico("YaelG0@gmail.com");

		producto = new Producto();
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

	@AfterEach
	void tearDown() throws Exception {
		// Este método se ejecuta después de la ejecución
		// de cada método de prueba, es útil para
		// dejar todo como estaba antes de la prueba
	}

	
	//
	//Pruebas unitarias de la HU-03
	//
		

	@Test
	void testVerificarCorreoElectronico() {

		// Prueba 1: corroborar que regresa un false si no hay un correo registrado con
		// el que se intenta registrar

		when(clienteRepository.findBycorreoelectronico("YaelG0@hotmail.com")).thenReturn(null);

		boolean resultado2 = servicioCliente.verificarCorreoElectronico("YaelG0@hotmail.com");

		assertEquals(false, resultado2);

		// prueba2 corroborar que regresa un true si hay un correo registrado con el que
		// se intenta registrar
		when(clienteRepository.findBycorreoelectronico("YaelG0@hotmail.com")).thenReturn(cliente);
		resultado2 = servicioCliente.verificarCorreoElectronico("YaelG0@hotmail.com");
		assertEquals(true, resultado2);

	}

	@Test
	void testcorreoValido() {

		// prueba 1 verifica si la sintaxis del correo este correcto

		boolean resultado5 = servicioCliente.correoValido("holaxd@gmail.com");
		assertEquals(true, resultado5);

		// prueba 2 verifica si la sintaxis del correo es incorrecto
		resultado5 = servicioCliente.correoValido("holaxd@gmail");
		assertEquals(false, resultado5);
	}

	@Test
	void testVerificarTelefono() {
		// prueba1 revisa si la cantidad de numeros del telefono sean 10.

		boolean resultado3 = servicioCliente.verificarTelefono("1234567890");

		assertEquals(true, resultado3);

		// prueba2 al enviar el telefono sea menor a 10 digitos mande error

		resultado3 = servicioCliente.verificarTelefono("123456789");

		assertEquals(false, resultado3);

		// prueba 3 detecta si en los digitos encuentra un digito que no es numero
		resultado3 = servicioCliente.verificarTelefono("123456789a");

		assertEquals(false, resultado3);

	}

	@Test
	void registrarClienter() {
		// prueba 1 registra a un cliente
		boolean resultado4 = servicioCliente.registrarCliente("22/11/2022", "Yael Ortega", "Masculino",
				"Picacho Ajusco", "5520201234", "YaelO@gmail.com");

		assertEquals(true, resultado4);

	}

	
	//
	//Pruebas unitarias de la HU-04
	//
	

	@Test
	void modificarCliente() {
		// Prueba 1: Corroborar que el metodo modificarCliente funciona correctamente si
		// el cliente no existe en la BD
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioCliente.modificarCliente(1, "Axel", "Masculino", "nose", "1234567890", "hola@gma.com");

		});

		// Prueba 2: Corroborar que el metodo modificarCliente funciona correctamente si
		// el cliente existe en la BD

		long id = 1;

		when(clienteRepository.findByIdCliente(id)).thenReturn(cliente);
		when(clienteRepository.save(cliente)).thenReturn(cliente);

		Cliente cliente = servicioCliente.modificarCliente(id, "Yael", "Masculino", "nosexd", "1234567898",
				"YaelG0@gmail.com");

		assertNotEquals(null, cliente);

	}

	@Test
	void obtenerCliente() {
		// Prueba 1: Corroborar que el metodo obtenerCliente se comporta adecuadamente
		// cuando el cliente no existe en la BD

		when(clienteRepository.findByIdCliente(2)).thenReturn(null);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioCliente.obtenerCliente(2);

		});

		// Prueba 2: Corroborar que el metodo obtenerCliente se comporta adecuadamente
		// cuando el cliente si existe en la BD

		when(clienteRepository.findByIdCliente(1)).thenReturn(cliente);

		Cliente cliente = servicioCliente.obtenerCliente(1);

		assertNotEquals(null, cliente);

	}

	@Test
	void consultarClientesDisponibles() {
		// Prueba 1: corroborar que regresa una lista vacía si no hay usuarios en la BD

		List<Cliente> clientes = servicioCliente.consultarClientesDisponibles();
		assertTrue(clientes.isEmpty());

		// Prueba 2: corroborar que regresa una lista con los clientes

		LinkedList<Cliente> lista = new LinkedList<>();

		lista.add(cliente);

		when(clienteRepository.findAll()).thenReturn(lista);

		clientes = servicioCliente.consultarClientesDisponibles();
		assertEquals(1, clientes.size());
	}



	void comparaCorreos(){
		
		boolean resultado7; 
		
		
		//prueba1 corroborar que regresa un true si hay un correo registrado con el mismo que se intenta registrar
	
		resultado7 =  servicioCliente.comparaCorreos("YaelG0@hotmail.com","YaelG0@hotmail.com");
		assertEquals(true,resultado7);
		
		//prueba2 corroborar que regresa un false si hay un correo registrado con el que se intenta registrar
		
			resultado7 =  servicioCliente.comparaCorreos("YaelG0@hotmail.com","Yael@hotmail.com");
			assertEquals(false,resultado7);


	}

	@Test
	void testrecuperaClientes() {

		// Caso 1: No hay clientess registrados
		List<Cliente> clientes = servicioCliente.recuperaClientes();
		assertEquals(0, clientes.size());

		// Caso 2: Hay clientes registrados en la base de datos

		ArrayList<Cliente> lista = new ArrayList<>();

		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente.setNombreCompleto("Yo");
		cliente.setGenero("Masculino");
		cliente.setDireccion("CDMX");
		cliente.setTelefono("1234567890");
		cliente.setCorreoelectronico("UAMI@gmai.com");

		Cliente cliente2 = new Cliente();
		cliente2.setIdCliente(2);
		cliente2.setNombreCompleto("Yoxd");
		cliente2.setGenero("Masculino");
		cliente2.setDireccion("CDMXa");
		cliente2.setTelefono("1234567893");
		cliente2.setCorreoelectronico("UAM@gmai.com");

		lista.add(cliente);
		lista.add(cliente2);

		List<Cliente> listaIterable = lista;

		when(clienteRepository.findAll()).thenReturn(listaIterable);
		clientes = servicioCliente.recuperaClientes();
		assertEquals(2, lista.size());

	}

	@Test
	void testBuscaClienteById() {
		// Caso 1: El parametro recibido es null
		
		Assertions.assertThrows(NullPointerException.class, () -> {

			servicioCliente.buscaClienteById(null); 
			
		});	
		// Caso 2: El parametro recibido  es una cadena vacia
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioCliente.buscaClienteById(""); 
			
		});	

		String id = "1";
		// Caso 3: El cliente no se encuentra registrado en la base de dato
		
		when(clienteRepository.findByIdCliente(1)).thenReturn(null);
		
		Cliente cliente = servicioCliente.buscaClienteById(id); 
		assertEquals(null, cliente);

		// Caso 4: El cliente se encuentra en la base de datos
		when(clienteRepository.findByIdCliente(1)).thenReturn(cliente);

		Cliente cliente2 = new Cliente();
		cliente2 = servicioCliente.buscaClienteById(id);
		assertSame(cliente, cliente2);

	}
	
	
	//
	//Pruebas unitarias de la HU-08
	//
	
	@Test
	void buscarHistorial() throws ParseException {
		
		List<Venta> ventas = new ArrayList<Venta>();
		String [][] datos;
		
		ventas.add(venta1);
		long id = 1;
		
		when(ventaRepository.findByIdCliente(id)).thenReturn(ventas);
		
		// Prueba 1: corroborar que regresa una lista si hay compras
		datos = servicioCliente.buscarHistorial(1, "", "");
		assertNotEquals(null, datos);
		
		// Prueba 2: Corroborar que regresa un null cuando hay compras de acuerdo con los criterios de busqueda establecido
		datos = servicioCliente.buscarHistorial(1, "22/12/2022", "");
		assertEquals(null, datos);
		
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
	void comparaFechas(){
		
		String fechaDesde; 
		String fechaHasta; 
		boolean fechaValida;
		
		// Prueba 1: Si las dos fecha son vacías, devuelve true porque no se utilizan las fechas
		fechaDesde = ""; 
		fechaHasta = "";
		fechaValida = servicioCliente.comparaFechas(fechaDesde, fechaHasta);
		assertTrue(fechaValida);
		
		// Prueba 2: Si fechaDesde es mayor a fechaHasta, devuelve falso
		fechaDesde = "05/12/2022"; 
		fechaHasta = "04/12/2022";
		fechaValida = servicioCliente.comparaFechas(fechaDesde, fechaHasta);
		assertFalse(fechaValida);
		
		// Prueba 3: Si fechaDesde es menor a fechaHasta, devuelve true
		fechaDesde = "05/12/2022"; 
		fechaHasta = "06/12/2022";
		fechaValida = servicioCliente.comparaFechas(fechaDesde, fechaHasta);
		assertTrue(fechaValida);	
		
		// Prueba 3: Si fechaDesde es igual a fechaHasta, devuelve true
		fechaDesde = "05/12/2022"; 
		fechaHasta = "05/12/2022";
		fechaValida = servicioCliente.comparaFechas(fechaDesde, fechaHasta);
		assertTrue(fechaValida);
		
	}
  //pruebas HU-07
	@Test
	void testBuscaClienteByName() {
		// Caso 1:La lista recuperada de todos los clientes esta vacia
		List<Cliente> listaClientes = new ArrayList<>();
		List<Cliente> listaClientes2 = new ArrayList<>();
		
		when(clienteRepository.findAll()).thenReturn(listaClientes);
		
		listaClientes2 =servicioCliente.buscaClientebyName("Lalo");
		
		assertEquals(0, listaClientes2.size());
		
		// Caso 2:Si hay clientes registrados pero ninguno cumple con el parametro pasado 
		
		listaClientes.add(cliente); 
				
		when(clienteRepository.findAll()).thenReturn(listaClientes);
		
		listaClientes2 =servicioCliente.buscaClientebyName("Lalo");
				
		assertEquals(0, listaClientes2.size()); 
		
		// Caso 3:Si hay clientes registrados y alguno cumple con el parametro pasado 
		
		listaClientes.add(cliente); 
						
		when(clienteRepository.findAll()).thenReturn(listaClientes);
				
		listaClientes2 =servicioCliente.buscaClientebyName("Yael");
						
		assertNotEquals(0, listaClientes2.size());
		
	}
}
