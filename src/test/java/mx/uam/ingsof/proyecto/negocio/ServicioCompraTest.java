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
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Compra;
/**
 * Implementacion de las pruebas unitarias del ServicioCompra
 * 
 * @author Erika Maya Perez
 * 
 *
 */
@ExtendWith(MockitoExtension.class)
class ServicioCompraTest {
	
	private Cliente clientePrueba;
	private Compra compraPrueba;
	@BeforeEach
	void setUp() throws Exception {
		/* Este método se ejecuta antes de la ejecución
		 * de cada método de prueba, es útil para
		   establecer las precondiciones */
		/*
	//Creamos un cliente prueba 
		clientePrueba= new Cliente();
		clientePrueba.setIdCliente(123);
		clientePrueba.setNombreCompleto("Juan Pérez");
		clientePrueba.setTelefono("5561330399");
		clientePrueba.setCorreo("juan123@gmail.com");
		clientePrueba.setDireccionEntrega("Calle 33 #113 Col. Santa Cruz Meyehualco");
		*/
    //Creamos una compraPrueba
		compraPrueba= new Compra();
		compraPrueba.setIdCompra(1111);
		compraPrueba.setFecha("12/03/2022");
		compraPrueba.setCantidad(2);
	}
	@Mock //genera un sustituto
	private CompraRepository compraRepository;
	@InjectMocks
	private ServicioCompra servicioCompra;
	
    @Test
	void testRecuperaTodasCompras() {
    	
    	//Caso de prueba No.1: Regresa una lista de compras cuando se le pasa un clientePrueba
    	
    	List <Compra> compras = servicioCompra.recuperaTodasCompras(clientePrueba);
    	ArrayList <Compra> listaCompras = new ArrayList <>();
    	
    	listaCompras.add(compraPrueba);
    	when(compraRepository.findAll()).thenReturn(listaCompras);
		compraPrueba = (Compra) servicioCompra.recuperaTodasCompras(clientePrueba);
		assertEquals(1,compras.size());
    	
    	
		//Caso de prueba No.2: Regresa un IllegalArgumentException cuando se le pasa un null como parametro
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			servicioCompra.recuperaTodasCompras(null);
		});
	}

}
