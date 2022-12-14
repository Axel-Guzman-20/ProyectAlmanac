package mx.uam.ingsof.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ingsof.proyecto.datos.CompraRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Compra;


/**
 * Implementacion de las pruebas unitarias del ServicioCompra
 * 
 * @author Eduardo Castro
 * 
 *
 */
@ExtendWith(MockitoExtension.class)
class ServicioCompraTest {
	
	@Mock //genera un sustituto
	private CompraRepository compraRepository;
	
	@InjectMocks
	private ServicioCompra servicioCompra;
	
	
	private List<Compra> listaCompras = new ArrayList<Compra>();
	private Compra compra1;
	private Compra compra2;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		compra1 = new Compra();
		compra1.setIdCompra(1);
		compra1.setFecha("20/12/2022");
		compra1.setIdEmpleado(2);
		compra1.setMarca("Asus S.A.");
		compra1.setNombre("Laptop S120");
		compra1.setCantidad(5);
		compra1.setPrecio(15699.45);
		
		compra2 = new Compra();
		compra2.setIdCompra(2);
		compra2.setFecha("20/11/2022");
		compra2.setIdEmpleado(3);
		compra2.setMarca("MSI");
		compra2.setNombre("Laptop MSI330");
		compra2.setCantidad(3);
		compra2.setPrecio(11199.95);
		
	}
	
	
    @Test
    void testAgregaProduto() {
    	//Caso 1: Se pasan argumentos invalidos 
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		servicioCompra.agregaProducto(0, "Impresora", "Hp", 1, 1200.00, "05/12/2022");
    	});
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		servicioCompra.agregaProducto(1, null, "Hp", 1, 1200.00, "05/12/2022");
    	});
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		servicioCompra.agregaProducto(1, "Impresora", null, 1, 1200.00, "05/12/2022");
    	});
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		servicioCompra.agregaProducto(1, "Impresora", "Hp", 0, 1200.00, "05/12/2022");
    	});
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		servicioCompra.agregaProducto(1, "Impresora", "Hp", 1,0, "05/12/2022");
    	});
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		servicioCompra.agregaProducto(1, "Impresora", "Hp", 1, 1200.00, null);
    	});
    	
    }
    @Test
    void testGuardaCompras() {
    	//Caso 1: La lista esta vacia 
    	Assertions.assertEquals(listaCompras.size(), 0);
    	//Caso 2: La lista no esta vacia y guardamos las compras
    	Compra compra = new Compra();
    	compra.setIdCompra(1);
    	compra.setIdEmpleado(2);
    	compra.setNombre("Impresora Multifuncional");
    	compra.setCantidad(2);
    	compra.setMarca("HP");
    	compra.setPrecio(2000.00);
    	compra.setFecha("06/12/2022");
    	Compra compra1 = new Compra();
    	compra1.setIdCompra(2);
    	compra1.setIdEmpleado(2);
    	compra1.setNombre("Impresora simplex");
    	compra1.setCantidad(2);
    	compra1.setMarca("HP");
    	compra1.setPrecio(1200.00);
    	compra1.setFecha("06/12/2022");
    	listaCompras.add(compra);
    	listaCompras.add(compra1);
    	Assertions.assertEquals(2,listaCompras.size());
    	listaCompras.clear();
    }
    
    
    @Test
    void testLimpiaCompras() {
    	//Caso 1: La lista esta vacia
    	Assertions.assertEquals(listaCompras.size(), 0);
    	//Caso 2: la lista no esta vacia
    	Compra compra = new Compra();
    	compra.setIdCompra(1);
    	compra.setIdEmpleado(2);
    	compra.setNombre("Impresora Multifuncional");
    	compra.setCantidad(2);
    	compra.setMarca("HP");
    	compra.setPrecio(2000.00);
    	compra.setFecha("06/12/2022");
    	Compra compra1 = new Compra();
    	compra1.setIdCompra(2);
    	compra1.setIdEmpleado(2);
    	compra1.setNombre("Impresora simplex");
    	compra1.setCantidad(2);
    	compra1.setMarca("HP");
    	compra1.setPrecio(1200.00);
    	compra1.setFecha("06/12/2022");
    	listaCompras.add(compra);
    	listaCompras.add(compra1);
    	listaCompras.clear();
    	Assertions.assertEquals(0,listaCompras.size());
    }
    
    
    
    
    
    
}
