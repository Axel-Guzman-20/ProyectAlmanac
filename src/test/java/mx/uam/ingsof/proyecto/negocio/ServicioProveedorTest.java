package mx.uam.ingsof.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ingsof.proyecto.datos.ProveedorRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Proveedor;

/**
 * Implementacion de las pruebas unitarias del ServicioProveedor
 * 
 * @author MiguelGuzman
 *
 */


@ExtendWith(MockitoExtension.class)
class ServicioProveedorTest {
	
	@Mock
	private ProveedorRepository proveedorRepository;
	
	@InjectMocks
	private ServicioProveedor servicioProveedor;
	
	private Proveedor proveedor;
	
	//private static int maxProveedor = 100;
	//private static int digitosMaxTelefono = 10;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		proveedor = new Proveedor();
		
		proveedor.setFechaRegistro("21/11/2022");
		proveedor.setNombreProveedor("ASUS S.A. México");
		proveedor.setNombreContacto("Benito Juarez");
		proveedor.setCargoContacto("Analista de ventas");
		proveedor.setDireccionProveedor("Calle 10, Col, Roma, Alcaldía Guerrero, CP 01474, Monterrey, Nuevo León");
		proveedor.setCorreoElectronicoContacto("benito.juarez@asus.mx");
		proveedor.setTelefonoContacto("5525852585");
		proveedor.setSobreLaEmpresa("Empresa dedicada a la venta de articulos tecnológicos como celulares, componentes de computadora y de telecomunicación");
		
	}
	
	@AfterEach
	void tearDown() throws Exception {
		// Este método se ejecuta después de la ejecución
		// de cada método de prueba, es útil para
		// dejar todo como estaba antes de la prueba
	}
	
	
	
	@Test
	void testProveedoresRegistrados(){
		
		
		//Prueba 1. el metodos proveedoresregistrados funciona correctamente si el numero de proveedores es menor a 100
		servicioProveedor.registrarProveedor("22/11/2022", "ASUS", "nacho", "jefe", "CDMX", "orreo@mail", "5520201234", "Esta es mi empresa");
		
		boolean resultado=servicioProveedor.proveedoresRegistrados();
		assertTrue(resultado); 
		//Prueba 2 el metodo proveedoresregistrados funciona correctamente si el numero de proveedores registrados es mayor a 100
		when(proveedorRepository.count()).thenReturn((long) 101); 
		resultado=servicioProveedor.proveedoresRegistrados();
		assertEquals(false, resultado); 
		
	}
	
	
	
	@Test
	void testVerificarCorreoE(){
		
		// Prueba 1: corroborar que regresa un false si no hay un correo registrado con el que se intenta registrar
		
		when(proveedorRepository.findByCorreoElectronicoContacto("benit.juarezo@asus.mx")).thenReturn(null); 

		boolean resultado2 =  servicioProveedor.verificarCorreoE("benit.juarezo@asus.mx");
		
		assertEquals(false, resultado2); 
		
		//prueba2
		when(proveedorRepository.findByCorreoElectronicoContacto("benit.juarezo@asus.mx")).thenReturn(proveedor); 
		resultado2 =  servicioProveedor.verificarCorreoE("benit.juarezo@asus.mx");
		assertEquals(true,resultado2);

	

	}
	
	
	@Test
	void testcorreoValido(){
		
		//prueba 1 verifica si la sintaxis del correo este correcto
		boolean resultado5= servicioProveedor.correoValido("holaxd@gmail.com");
		assertEquals(true,resultado5);
		
		//prueba 2 verifica si la sintaxis del correo es incorrecto
		resultado5= servicioProveedor.correoValido("holaxd@gmail");
		assertEquals(false,resultado5);
		
	}
	
	
	@Test
	void testVerificarTelefono(){
		//prueba1  revisa si la cantidad de numeros del telefono sean 10.
		
		boolean resultado3= servicioProveedor.verificarTelefono("1234567890");
		
		assertEquals(true,resultado3);
		
		//prueba2 al enviar el telefono sea menor a 10 digitos mande error
		
		resultado3= servicioProveedor.verificarTelefono("123456789");
		
		assertEquals(false,resultado3);
		
		//prueba 3 detecta si en los digitos encuentra un digito que no es numero
		resultado3= servicioProveedor.verificarTelefono("123456789a");
		
		assertEquals(false,resultado3);
			
	}
	@Test
	void registrarProveedor() {
		//prueba 1 registra a un proveedor
		boolean resultado4 = servicioProveedor.registrarProveedor("22/11/2022", "ASUS", "nacho", "jefe", "CDMX", "orreo@mail", "5520201234", "Esta es mi empresa"); 
		
		assertEquals(true, resultado4); 
		
		
		
	}
	

}
