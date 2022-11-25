package mx.uam.ingsof.proyecto.negocio.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.SeccionCatalogo;

class SeccionCatalogoTest {
	
	/**
	 * Implementacion de las pruebas unitarias de la SeccionCatalogo
	 * 
	 * @author Abigail Morales Mariscal 
	 *
	 */
	
	private Producto productoPrueba; 
	
	@BeforeEach
	void setUp() throws Exception {
		// Este método se ejecuta antes de la ejecución
		// de cada metodo de prueba, es útil para
		// establecer las precondiciones
		
		productoPrueba = new Producto(); 
		productoPrueba.setNombre("Core i7");
		productoPrueba.setMarca("Intel");
		productoPrueba.setDescripcion("5.00 GHz, 12va Generacion");
		productoPrueba.setPrecio(8499.99);
		productoPrueba.setDescuento(0);
		productoPrueba.setExistencia(10);
		productoPrueba.setTotalComprasProducto(0);
		
	}

	@Test
	void testAddProducto() {
		
		SeccionCatalogo seccionCatalogoPrueba = new SeccionCatalogo();
		seccionCatalogoPrueba.setNombre("Procesadores");
		
		//Caso de prueba 1: Se agrega un producto exitosamente a la SeccionCatalogo
		
		boolean resultado = seccionCatalogoPrueba.addProducto(productoPrueba); 
		
		assertEquals(true,resultado,"Debio regresar true");
		
		//Caso de prueba 2: Se trata de agregar un producto que ya existe en la SeccionCatalogo
		
		resultado = seccionCatalogoPrueba.addProducto(productoPrueba);
		assertEquals(false,resultado,"Debio regresar false");
		
		//Caso de prueba 3: Se pasa un null como parametro
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			seccionCatalogoPrueba.addProducto(null); 
		});
		
	}

}
