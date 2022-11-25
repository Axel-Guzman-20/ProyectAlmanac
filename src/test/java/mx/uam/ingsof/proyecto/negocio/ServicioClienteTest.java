package mx.uam.ingsof.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ingsof.proyecto.datos.ClienteRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;

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

}