package mx.uam.ingsof.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ingsof.proyecto.datos.SeccionCatalogoRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.SeccionCatalogo;

/**
 * Implementacion de las pruebas unitarias del ServicioSeccionCatalogo
 * 
 * @author Abigail Morales Mariscal 
 *
 */
@ExtendWith(MockitoExtension.class)
class ServicioSeccionCatalogoTest {
	

	
	// Al usar la anotación @Mock, el framework Mockito crea un sustituto
	// de la clase que regresa valores por default
	@Mock
	private SeccionCatalogoRepository seccionCatalogoRepository;
	
	
	// Esta anotación hace que se inyecten todos los Mocks al módulo que quiero
	// probar para que no haya nullPointerException por que las dependencias
	// no están satisfechas en tiempo de pruebas
	@InjectMocks
	private ServicioSeccionCatalogo servicioSeccionCatalogo; 
	
	@Test
	void testConsultarSeccionesDisponibles() {
		
		// Prueba 1: corroborar que regresa una lista vacía si no hay seccciones de catalogo en la BD
		
		// en este momento, la invocación a usuarioRepository.findAll() regresa una lista vacía
		
		List <SeccionCatalogo> secciones = servicioSeccionCatalogo.consultarSeccionesDisponibles();
		
		assertEquals(0,secciones.size());
		
		// Prueba 2: corroborar que regresa una lista con secciones de usuario
		
		LinkedList <SeccionCatalogo> lista = new LinkedList <> ();
		
		SeccionCatalogo seccion1 = new SeccionCatalogo();
		seccion1.setNombre("Procesadores");
		
		SeccionCatalogo seccion2 = new SeccionCatalogo();
		seccion2.setNombre("Monitores");
		
		lista.add(seccion1);
		lista.add(seccion2); 
		
		/* Al usar when, lo que hacemos es que definimos un comportamiento
		   para la invoación del método.
		   A partir de este punto, la invocación a usuarioRepository.findAll() ya
		   no me regresa una lista vacía, si no que me regresa una listaLigada
		   vista como Iterable que tiene dos elementos*/
		
		when(seccionCatalogoRepository.findAll()).thenReturn(lista);
		
		secciones = servicioSeccionCatalogo.consultarSeccionesDisponibles(); 
		assertEquals(2, secciones.size());
	}

}
