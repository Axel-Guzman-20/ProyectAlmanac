package mx.uam.ingsof.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
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

import mx.uam.ingsof.proyecto.datos.ProductoRepository;
import mx.uam.ingsof.proyecto.datos.SeccionCatalogoRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.SeccionCatalogo;

/**
 * Implementacion de las pruebas unitarias del ServicioProducto
 * 
 * @author Abigail Morales Mariscal
 * @author Eduardo Castro Ramon 
 *
 */

@ExtendWith(MockitoExtension.class)
class ServicioProductoTest {
	
	
	
	@Mock
	private ProductoRepository productoRepository;
	
	@Mock
	private SeccionCatalogoRepository seccionCatalogoRepository; 
	
	@InjectMocks
	private ServicioProducto servicioProducto;
	
	private Producto productoPrueba;
	private Producto productoPrueba2;
	
	private SeccionCatalogo seccionCatalogoPrueba;
	
	@BeforeEach
	void setUp() throws Exception {
		

		seccionCatalogoPrueba = new SeccionCatalogo(); 
		seccionCatalogoPrueba.setNombre("Procesador");
		
		productoPrueba = new Producto(); 
		productoPrueba.setIdProducto(1);
		productoPrueba.setNombre("Core i3");
		productoPrueba.setMarca("Intel");
		productoPrueba.setDescripcion("10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T");
		productoPrueba.setPrecio(1499.90);
		productoPrueba.setDescuento(15);
		productoPrueba.setExistencia(7);
		productoPrueba.setTotalComprasProducto(0);
		
		productoPrueba2 = new Producto();
		productoPrueba2.setIdProducto(2);
		productoPrueba2.setNombre("Core i1");
		productoPrueba2.setMarca("Intel");
		productoPrueba2.setDescripcion("10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T");
		productoPrueba2.setPrecio(2699.99);
		productoPrueba2.setDescuento(0);
		productoPrueba2.setExistencia(12);
		productoPrueba2.setTotalComprasProducto(0);
		
		
	}
	
	@AfterEach
	void tearDown() throws Exception {
		// Este método se ejecuta después de la ejecución
		// de cada método de prueba, es útil para
		// dejar todo como estaba antes de la prueba
	}
	
	@Test
	void testAgregaProducto() {
		
		// Prueba 1: Corroborar que AgregaProducto funciona correctamente si aun no se ha agregado el producto en la seccion 
		
		when(seccionCatalogoRepository.findByNombre("Procesador")).thenReturn(seccionCatalogoPrueba);
		
		boolean resultado = servicioProducto.agregaProducto("Core i2", "Intel", "10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
				 "1499.90", "15", "7", "Procesador"); 
		
		assertEquals(true, resultado); 
		
		// Prueba 2: Corroborar que no es posible agregar un mismo producto dos veces en la seccion del catálogo
		
		when(productoRepository.findByNombre("Core i3")).thenReturn(productoPrueba);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioProducto.agregaProducto("Core i3", "Intel", "10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
					 "1499.90", "15", "7", "Procesador");

		});		
	
	}

	@Test
	void testConsultarProductosDisponibles() {
		
		// Prueba 1: corroborar que regresa una lista vacía si no hay usuarios en la BD
		
		List <Producto> productos =  servicioProducto.consultarProductosDisponibles();
		assertTrue(productos.isEmpty());
		
		// Prueba 2: corroborar que regresa una lista con productos
		
		LinkedList <Producto> lista = new LinkedList <> ();
		
		lista.add(productoPrueba); 
		lista.add(productoPrueba);
		
		when(productoRepository.findAll()).thenReturn(lista);
		
		productos = servicioProducto.consultarProductosDisponibles(); 
		assertEquals(2, productos.size());
	}

	@Test
	void testObtenerProducto() {
		
		// Prueba 1: Corroborar que el metodo obtenerProducto se comporta adecuadamente cuando el producto no existe en la BD
		
		when(productoRepository.findByNombre("Core i3")).thenReturn(null);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioProducto.obtenerProducto("Core i3");

		});	
		
		// Prueba 2: Corroborar que el metodo obtenerProducto se comporta adecuadamente cuando el producto si existe en la BD

		when(productoRepository.findByNombre("Core i3")).thenReturn(productoPrueba);
		
		Producto producto = servicioProducto.obtenerProducto("Core i3");
		
		assertNotEquals(null, producto); 
	
	}

	@Test
	void testObtenerSeccionCatalogoDelProducto() {
		
		// Prueba 1: Corroborar que el metodo obtenerSeccionCatalogoDelProducto se comporta adecuadamente cuando el producto no existe en la BD
		
		when(seccionCatalogoRepository.findByProductos(productoPrueba)).thenReturn(null);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioProducto.obtenerSeccionCatalogoDelProducto(productoPrueba);

		});	
		
		// Prueba 2: Corroborar que el metodo obtenerProducto se comporta adecuadamente cuando el producto si existe en la BD

		when(seccionCatalogoRepository.findByProductos(productoPrueba)).thenReturn(seccionCatalogoPrueba);
		
		SeccionCatalogo seccion = servicioProducto.obtenerSeccionCatalogoDelProducto(productoPrueba);
		
		assertNotEquals(null, seccion); 
	}

	@Test
	void testModificaProducto() {
		
		// Prueba 1: Corroborar que el metodo modificaProducto funciona correctamente si el producto no existe en la BD
		
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioProducto.modificaProducto("Core i3","Core i2", "Intel", "10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
					 "1499.90", "15", "7");

		});
		
		// Prueba 2: Corroborar que el metodo modificaProducto funciona correctamente si el producto existe en la BD

		String nombre = "Core i3";
		
		when(productoRepository.findByNombre(nombre)).thenReturn(productoPrueba);
		when(productoRepository.save(productoPrueba)).thenReturn(productoPrueba);
		
		Producto producto = servicioProducto.modificaProducto(nombre,"Core i5", "", "","3499.90", "", "13");
	
		assertNotEquals(null, producto);
		
	} 
	@Test
	void testRecuperaListaProductos() {
		
		//Caso 1: No hay productos registrados
		List <Producto> productos = servicioProducto.recuperaListaProductos();
		assertEquals(0, productos.size());
		
		//Caso 2: Hay productos registrados en la base de datos 
		
		ArrayList <Producto> lista = new ArrayList <>();
		
		Producto producto = new Producto();
		producto.setIdProducto(1);
		producto.setNombre("Impresora Hp Lj P1102");
		producto.setTotalComprasProducto(4);
		producto.setDescuento(0.002);
		producto.setMarca("HP");
		producto.setExistencia(3);
		producto.setPrecio(1200.00);
		producto.setDescripcion("Impresora simplex");
		
		Producto producto1 = new Producto();
		producto.setIdProducto(2);
		producto.setNombre("Impresora Hp Lj P1003");
		producto.setTotalComprasProducto(4);
		producto.setDescuento(0.2);
		producto.setMarca("HP");
		producto.setExistencia(3);
		producto.setPrecio(1000.00);
		producto.setDescripcion("Impresora simplex");
		
		lista.add(producto);
		lista.add(producto1);
		
		List<Producto> listaIterable = lista;
		
		
		when(productoRepository.findAll()).thenReturn(listaIterable);
		productos = servicioProducto.recuperaListaProductos();
		assertEquals(2, lista.size());
		
		
	}
	@Test
	void testBuscarProducto() {
		
		// Prueba 1: corroborar que regresa una lista vacía si no hay productos en la BD
		
		when(seccionCatalogoRepository.findByNombre("Procesador")).thenReturn(seccionCatalogoPrueba);
		
		List <Producto> productos =  servicioProducto.buscarProducto("Procesador", "", "", "", "4500", "1100"); 
		assertTrue(productos.isEmpty());
		
		// Prueba 2: corroborar que regresa una lista con productos de acuerdo al filtro de busqueda dado por el usuario
		
		
		servicioProducto.agregaProducto("Core i3", "Intel", "10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
				 "1499.90", "15", "7", "Procesador"); 
		servicioProducto.agregaProducto("Core i5", "Intel", "10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
				 "1499.90", "15", "7", "Procesador");
		servicioProducto.agregaProducto("Core i7", "Intel", "10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
				 "1499.90", "15", "7", "Procesador");
		
		productos =  servicioProducto.buscarProducto("Procesador", "", "", "", "4500", "1100");
		
		assertNotEquals(0, productos.size());
		
		// Prueba 3: corroborar que regresa una lista vacia en el caso de que haya productos en la BD
		//           pero ninguno de esos productos concuerda con el filtro de busqueda dado por el usuario
		
		productos =  servicioProducto.buscarProducto("Procesador", "", "Core i9", "", "", "");
		
		assertEquals(0, productos.size());
		
		//Prueba 4: Corroborar que no es posible pasar parametros nulos por parte del usuario
		
		Assertions.assertThrows(NullPointerException.class, () -> {

			servicioProducto.buscarProducto(null, null, null, "", "4500", "1100");

		});
		
	}
	
	@Test
	void testvalidaRangoPrecio() {
		
		//Prueba 1: Corroborar que no es posible pasar parametros nulos 
		
		Assertions.assertThrows(NullPointerException.class, () -> {

			servicioProducto.validaRangoPrecio(null, "5678", null); 

		});
		
		// Prueba 2: corroborar que regresa una lista con productos de acuerdo al filtro de busqueda dado por el usuario

		when(seccionCatalogoRepository.findByNombre("Procesador")).thenReturn(seccionCatalogoPrueba);
		
		servicioProducto.agregaProducto("Core i3", "Intel", "10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
				 "1499.90", "15", "7", "Procesador"); 
		servicioProducto.agregaProducto("Core i1", "Intel", "10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
				 "1499.90", "15", "7", "Procesador");
		

		List <Producto> lista = new ArrayList <>();
		lista.add(productoPrueba);
		lista.add(productoPrueba2);
		
		List <Producto> productos =  servicioProducto.validaRangoPrecio("4500", "1100",lista); 
		assertTrue(!productos.isEmpty());
		
		// Prueba 3: corroborar que regresa una lista vacia en el caso de que haya productos en la BD
		//           pero ninguno de esos productos concuerda con el filtro de busqueda dado por el usuario
		
		productos =  servicioProducto.validaRangoPrecio("1200", "",lista); 
		assertTrue(productos.isEmpty());
		
		
	}
	
	@Test
	void testvalidaID() {

		// Prueba 1: Corroborar que no es posible pasar parametros nulos

		Assertions.assertThrows(NullPointerException.class, () -> {

			servicioProducto.validaID(null, null);

		});

		// Prueba 2: corroborar que regresa una lista con productos de acuerdo al filtro
		// de busqueda dado por el usuario

		when(seccionCatalogoRepository.findByNombre("Procesador")).thenReturn(seccionCatalogoPrueba);

		servicioProducto.agregaProducto("Core i3", "Intel",
				"10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
				"1499.90", "15", "7", "Procesador");
		servicioProducto.agregaProducto("Core i1", "Intel",
				"10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
				"1499.90", "15", "7", "Procesador");

		List<Producto> lista = new ArrayList<>();
		lista.add(productoPrueba);
		lista.add(productoPrueba2);

		List<Producto> productos = servicioProducto.validaID("1", lista);
		assertTrue(!productos.isEmpty());

		// Prueba 3: corroborar que regresa una lista vacia en el caso de que haya productos en la BD
		// pero ninguno de esos productos concuerda con el filtro de busqueda dado por el usuario

		productos = servicioProducto.validaID("5", lista);
		assertTrue(productos.isEmpty());

	}
	
	@Test
	void testvalidaNombre() {
		
		// Prueba 1: Corroborar que no es posible pasar parametros nulos

		Assertions.assertThrows(NullPointerException.class, () -> {

			servicioProducto.validaNombre("Core i2", null);

		});

		// Prueba 2: corroborar que regresa una lista con productos de acuerdo al filtro
		// de busqueda dado por el usuario

		when(seccionCatalogoRepository.findByNombre("Procesador")).thenReturn(seccionCatalogoPrueba);

		servicioProducto.agregaProducto("Core i3", "Intel",
				"10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
				"1499.90", "15", "7", "Procesador");
		servicioProducto.agregaProducto("Core i1", "Intel",
				"10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
				"1499.90", "15", "7", "Procesador");

		List<Producto> lista = new ArrayList<>();
		lista.add(productoPrueba);
		lista.add(productoPrueba2);

		List<Producto> productos = servicioProducto.validaNombre("Core i1", lista);
		assertTrue(!productos.isEmpty());

		// Prueba 3: corroborar que regresa una lista vacia en el caso de que haya productos en la BD
		// pero ninguno de esos productos concuerda con el filtro de busqueda dado por el usuario

		productos = servicioProducto.validaNombre("Core i9", lista);
		assertTrue(productos.isEmpty());
	}
	
	@Test
	void testvalidaMarca() {
		
		// Prueba 1: Corroborar que no es posible pasar parametros nulos
		
		List<Producto> lista = new ArrayList<>();
		
		Assertions.assertThrows(NullPointerException.class, () -> {

			servicioProducto.validaMarca(null, lista);

		});

		// Prueba 2: corroborar que regresa una lista con productos de acuerdo al filtro
		// de busqueda dado por el usuario

		when(seccionCatalogoRepository.findByNombre("Procesador")).thenReturn(seccionCatalogoPrueba);

		servicioProducto.agregaProducto("Core i3", "Intel",
				"10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
				"1499.90", "15", "7", "Procesador");
		servicioProducto.agregaProducto("Core i1", "Intel",
				"10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T",
				"1499.90", "15", "7", "Procesador");

		
		lista.add(productoPrueba);
		lista.add(productoPrueba2);

		List<Producto> productos = servicioProducto.validaMarca("Intel", lista);
		assertTrue(!productos.isEmpty());

		// Prueba 3: corroborar que regresa una lista vacia en el caso de que haya productos en la BD
		// pero ninguno de esos productos concuerda con el filtro de busqueda dado por el usuario

		productos = servicioProducto.validaMarca("AMD", lista);
		assertTrue(productos.isEmpty());
	}

}
