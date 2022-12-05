package mx.uam.ingsof.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

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
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;

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
	
	@InjectMocks
	private ServicioCliente servicioCliente;
	
	private Cliente cliente;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		cliente = new Cliente();
		
		cliente.setFechaRegistro("22/11/2022");
		cliente.setIdCliente(1);
		cliente.setNombreCompleto("Yael Ortega");
		cliente.setDireccion("Ciudad de México");
		cliente.setTelefono("5520204585");
		cliente.setCorreoelectronico("YaelG0@gmail.com");
		
	}
	
	@AfterEach
	void tearDown() throws Exception {
		// Este método se ejecuta después de la ejecución
		// de cada método de prueba, es útil para
		// dejar todo como estaba antes de la prueba
	}
	
		
	@Test
	void testVerificarCorreoElectronico(){
		
		// Prueba 1: corroborar que regresa un false si no hay un correo registrado con el que se intenta registrar
		
		when(clienteRepository.findBycorreoelectronico("YaelG0@hotmail.com")).thenReturn(null); 

		boolean resultado2 =  servicioCliente.verificarCorreoElectronico("YaelG0@hotmail.com");
		
		assertEquals(false, resultado2); 
		
		//prueba2 corroborar que regresa un true si hay un correo registrado con el que se intenta registrar
		when(clienteRepository.findBycorreoelectronico("YaelG0@hotmail.com")).thenReturn(cliente); 
		resultado2 =  servicioCliente.verificarCorreoElectronico("YaelG0@hotmail.com");
		assertEquals(true,resultado2);

	}
	
	
	@Test
	void testcorreoValido(){
		
		//prueba 1 verifica si la sintaxis del correo este correcto
		
		boolean resultado5= servicioCliente.correoValido("holaxd@gmail.com");
		assertEquals(true,resultado5);
		
		//prueba 2 verifica si la sintaxis del correo es incorrecto
		resultado5= servicioCliente.correoValido("holaxd@gmail");
		assertEquals(false,resultado5);
	}
		
	
	
	@Test
	void testVerificarTelefono(){
		//prueba1  revisa si la cantidad de numeros del telefono sean 10.
		
		boolean resultado3= servicioCliente.verificarTelefono("1234567890");
		
		assertEquals(true,resultado3);
		
		//prueba2 al enviar el telefono sea menor a 10 digitos mande error
		
		resultado3= servicioCliente.verificarTelefono("123456789");
		
		assertEquals(false,resultado3);
		
		//prueba 3 detecta si en los digitos encuentra un digito que no es numero
		resultado3= servicioCliente.verificarTelefono("123456789a");
		
		assertEquals(false,resultado3);
			
	}
	
	
	@Test
	void registrarClienter() {
		//prueba 1 registra a un cliente
		boolean resultado4 = servicioCliente.registrarCliente("22/11/2022", "Yael Ortega", "Masculino", "Picacho Ajusco",  "5520201234", "YaelO@gmail.com"); 
		
		assertEquals(true, resultado4); 
		
		
	}
	
	@Test
	void modificarCliente() {
		// Prueba 1: Corroborar que el metodo modificarCliente funciona correctamente si el cliente no existe en la BD
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioCliente.modificarCliente(1,"Axel", "Masculino", "nose", "1234567890", "hola@gma.com");

		});
		
		// Prueba 2: Corroborar que el metodo modificarCliente funciona correctamente si el cliente existe en la BD

				long id = 1;
				
				when(clienteRepository.findByIdCliente(id)).thenReturn(cliente);
				when(clienteRepository.save(cliente)).thenReturn(cliente);
				
				Cliente cliente = servicioCliente.modificarCliente(id,"Yael","Masculino","nosexd","1234567898","YaelG0@gmail.com");
			
				assertNotEquals(null, cliente);		
		
	}
	
	@Test
	void obtenerCliente() {
		// Prueba 1: Corroborar que el metodo obtenerCliente se comporta adecuadamente cuando el cliente no existe en la BD
		
				when(clienteRepository.findByIdCliente(2)).thenReturn(null);
				
				Assertions.assertThrows(IllegalArgumentException.class, () -> {

					servicioCliente.obtenerCliente(2);

				});	
				
				// Prueba 2: Corroborar que el metodo obtenerCliente se comporta adecuadamente cuando el cliente si existe en la BD

				when(clienteRepository.findByIdCliente(1)).thenReturn(cliente);
				
				Cliente cliente = servicioCliente.obtenerCliente(1);
				
				assertNotEquals(null, cliente); 
			
			}
	
	@Test
	void consultarClientesDisponibles() {
		// Prueba 1: corroborar que regresa una lista vacía si no hay usuarios en la BD
		
				List <Cliente> clientes =  servicioCliente.consultarClientesDisponibles();
				assertTrue(clientes.isEmpty());
				
				// Prueba 2: corroborar que regresa una lista con los clientes
				
				LinkedList <Cliente> lista = new LinkedList <> ();
				
				lista.add(cliente); 
				
				when(clienteRepository.findAll()).thenReturn(lista);
				
				clientes = servicioCliente.consultarClientesDisponibles(); 
				assertEquals(1, clientes.size());
	}
		
	
	
	
	
	
	@Test
	void comparacorreos(){
		
		boolean resultado7; 
		
		
		//prueba1 corroborar que regresa un true si hay un correo registrado con el mismo que se intenta registrar
	
		resultado7 =  servicioCliente.comparacorreos("YaelG0@hotmail.com","YaelG0@hotmail.com");
		assertEquals(true,resultado7);
		
		//prueba2 corroborar que regresa un false si hay un correo registrado con el que se intenta registrar
		
			resultado7 =  servicioCliente.comparacorreos("YaelG0@hotmail.com","Yael@hotmail.com");
			assertEquals(false,resultado7);

	}
	
	@Test
	void testrecuperaClientes() {
		
		//Caso 1: No hay clientess registrados
		List <Cliente> clientes = servicioCliente.recuperaClientes();
		assertEquals(0, clientes.size());
		
		//Caso 2: Hay clientes registrados en la base de datos 
		
		ArrayList <Cliente> lista = new ArrayList <>();
		
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

}