package mx.uam.ingsof.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ingsof.proyecto.datos.CategoriaDiagnosticoRepository;
import mx.uam.ingsof.proyecto.datos.ReparacionMantenimientoRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.CategoriaDiagnostico;
import mx.uam.ingsof.proyecto.negocio.modelo.ReparacionMantenimiento;

/**
 * Implementacion de las pruebas unitarias del ServicioReparacionMantenimiento
 * 
 * @author Abigail Morales Mariscal
 *
 */

@ExtendWith(MockitoExtension.class)
class ServicioReparacionMantenimientoTest {
	
	@Mock
	private ReparacionMantenimientoRepository reparacionMantenimientoRepository;
	
	@Mock
	private CategoriaDiagnosticoRepository categoriaDiagnosticoRepository; 
	
	@InjectMocks
	private ServicioReparacionMantenimiento servicioReparacionMantenimiento;
	
	private ReparacionMantenimiento reparacionMantenimientoPrueba; 
	private CategoriaDiagnostico categoriaDiagnosticoPrueba;
	
	@BeforeEach
	void setUp() throws Exception {
		

		categoriaDiagnosticoPrueba = new CategoriaDiagnostico(); 
		categoriaDiagnosticoPrueba.setNombre("Mantenimiento");
		
		reparacionMantenimientoPrueba = new ReparacionMantenimiento(); 
		reparacionMantenimientoPrueba.setIdReparacionMantenimiento(1);
		reparacionMantenimientoPrueba.setNombreEmpleado("Abigail Morales");
		reparacionMantenimientoPrueba.setNombreEquipo("Laptop 22B03LA");
		reparacionMantenimientoPrueba.setMarca("HP");
		reparacionMantenimientoPrueba.setDescripcionEquipo("Procesador AMD AthlonSilver 3050U, pantalla de 14 pulgadas HD");
		reparacionMantenimientoPrueba.setDescripcionReparacionMantenimiento("Actualizacion de software y limpieza digital");
		reparacionMantenimientoPrueba.setTipoReparacionMantenimiento("Preventivo");
		reparacionMantenimientoPrueba.setPiezasRequeridas("");
		reparacionMantenimientoPrueba.setObservacionesAdicionales("Articulo fragil");
		
		
	}
	
	@AfterEach
	void tearDown() throws Exception {
		// Este método se ejecuta después de la ejecución
		// de cada método de prueba, es útil para
		// dejar todo como estaba antes de la prueba
	}

	@Test
	void testCrearDiagnostico() {
		
		// Prueba 1: Corroborar que CrearDiagnostico funciona correctamente si aun no se ha agregado el diagnostico en la categoria 
		
		when(categoriaDiagnosticoRepository.findByNombre("Mantenimiento")).thenReturn(categoriaDiagnosticoPrueba);
		
		boolean resultado = servicioReparacionMantenimiento.crearDiagnostico("Abigail Morales", "Laptop 22B03LA","Mantenimiento","HP", "Procesador AMD AthlonSilver 3050U, pantalla de 14 pulgadas HD",
				"Actualizacion de software y limpieza digital", "Preventivo", "", "Articulo fragil");  
		
		assertEquals(true, resultado); 
		
		// Prueba 2: Corroborar que no es posible crear un mismo diagnostico dos veces en la categoria selecccionada 
		
		when(reparacionMantenimientoRepository.findByNombreEquipo("Laptop 22B03LA")).thenReturn(reparacionMantenimientoPrueba);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioReparacionMantenimiento.crearDiagnostico("Abigail Morales", "Laptop 22B03LA","Mantenimiento","HP", "Procesador AMD AthlonSilver 3050U, pantalla de 14 pulgadas HD",
					"Actualizacion de software y limpieza digital", "Preventivo", "", "Articulo fragil");  
			
		});	
		
		// Prueba 3: Corroborar que no es posible crear un diagnostico si uno de los campos es nulo
	
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioReparacionMantenimiento.crearDiagnostico("Abigail Morales", null,"Mantenimiento","HP", "Procesador AMD AthlonSilver 3050U, pantalla de 14 pulgadas HD",
					"Actualizacion de software y limpieza digital", null, "", "Articulo fragil");  
			
		});	
	
	}

	@Test
	void testValidarCampos() {
		
		// Prueba 1: Ninguno de los campos pasados es nulo
		
		boolean resultado = servicioReparacionMantenimiento.validarCampos("Abigail Morales", "Laptop 22B03LA","Mantenimiento","HP", "Procesador AMD AthlonSilver 3050U, pantalla de 14 pulgadas HD",
				"Actualizacion de software y limpieza digital", "Preventivo", "", "Articulo fragil");  
		
		assertEquals(true, resultado); 
		
		// Prueba 2: Minimo alguno de los campos pasados es nulo
		
		resultado = servicioReparacionMantenimiento.validarCampos(null, "Laptop 22B03LA","Mantenimiento","HP", "Procesador AMD AthlonSilver 3050U, pantalla de 14 pulgadas HD",
					null, "Preventivo", "", null);  
				
		assertEquals(false, resultado); 
	}

	@Test
	void testValidarMaximoDiagnosticos() {
		
		//Prueba 1: Verificar que el metodo validarMaximoDiagnosticos() funcione correctamente si aun no se ha llegado al limite maximo de diagnosticos de equipos en la categoria seleccionada 
		
		when(categoriaDiagnosticoRepository.findByNombre("Mantenimiento")).thenReturn(categoriaDiagnosticoPrueba);
		
		boolean resultado = servicioReparacionMantenimiento.validarMaximoDiagnosticos("Mantenimiento");  
		
		assertEquals(true, resultado); 
		
		// Prueba 2: Verificar que el metodo validarMaximoDiagnosticos() funcione correctamente si ya se ha llegado al limite maximo de diagnosticos de equipos en la categoria seleccionada 
		
		for(int i=1; i<2002; i++) {
			servicioReparacionMantenimiento.crearDiagnostico("Abigail Morales", "Laptop 22B03LA"+i,"Mantenimiento","HP", "Procesador AMD AthlonSilver 3050U, pantalla de 14 pulgadas HD",
					"Actualizacion de software y limpieza digital", "Preventivo", "", "Articulo fragil");  
		}
		
		resultado = servicioReparacionMantenimiento.validarMaximoDiagnosticos("Mantenimiento");  
		
		assertEquals(false, resultado); 
	}

}
