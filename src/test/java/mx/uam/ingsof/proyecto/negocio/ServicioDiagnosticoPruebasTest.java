package mx.uam.ingsof.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ingsof.proyecto.datos.CategoriaDiagnosticoRepository;
import mx.uam.ingsof.proyecto.datos.DiagnosticoPruebasRepository;
import mx.uam.ingsof.proyecto.datos.EmpleadoRepository;
import mx.uam.ingsof.proyecto.datos.ReparacionMantenimientoRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.CategoriaDiagnostico;
import mx.uam.ingsof.proyecto.negocio.modelo.DiagnosticoPruebas;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;
import mx.uam.ingsof.proyecto.negocio.modelo.Pruebas;
import mx.uam.ingsof.proyecto.negocio.modelo.ReparacionMantenimiento;

/**
 * Implementacion de las pruebas unitarias del ServicioDiagnosticoPruebas
 * 
 * @author AbigailMorales
 *
 */


@ExtendWith(MockitoExtension.class)
class ServicioDiagnosticoPruebasTest{
	
	@Mock
	private ReparacionMantenimientoRepository reparacionMantenimientoRepository;
	
	@Mock
	private CategoriaDiagnosticoRepository categoriaDiagnosticoRepository; 
	
	@Mock
	private EmpleadoRepository empleadoRepository;
	
	@Mock
	private DiagnosticoPruebasRepository diagnosticoPruebasRepository;
	
	@InjectMocks
	private ServicioDiagnosticoPruebas servicioDiagnosticoPruebas;
	
	private DiagnosticoPruebas diagnosticoPruebas; 
	private ReparacionMantenimiento reparacionMantenimientoPrueba; 
	private CategoriaDiagnostico categoriaDiagnosticoPrueba;
	private Pruebas prueba1;
	private Pruebas prueba2;
	private Empleado empleadoPrueba; 
	
	@BeforeEach
	void setUp() throws Exception {
		
		reparacionMantenimientoPrueba = new ReparacionMantenimiento(); 
		reparacionMantenimientoPrueba.setIdReparacionMantenimiento(1);
		reparacionMantenimientoPrueba.setNombreEmpleado("Ricardo");
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
	void testRealizarPruebasReparacion() {
		
		// Prueba 1: Corroborar que RealizarPruebasReparacion funciona correctamente si el diagnostico de 
		//           Reparacion/Mantenimiento todavia no tenga su diagnostico de pruebas
		
		when(reparacionMantenimientoRepository.findByNombreEquipo("Laptop 22B03LA")).thenReturn(reparacionMantenimientoPrueba);
		when(diagnosticoPruebasRepository.findByNombreEquipo("Laptop 22B03LA")).thenReturn(null);
		
		List<String> pruebas = new ArrayList<>();
		pruebas.add("Prueba 1"); 
		pruebas.add("Prueba 2"); 
		
		boolean resultado = servicioDiagnosticoPruebas.realizarPruebasReparacion("Ricardo", "Mantenimiento", "Laptop 22B03LA", pruebas, "---"); 
	
		assertEquals(true, resultado);
		
		// Prueba 2: Corroborar que RealizarPruebasReparacion funciona correctamente si el diagnostico de 
		//           Reparacion/Mantenimiento todavia ya tiene su diagnostico de pruebas
		
		resultado = servicioDiagnosticoPruebas.realizarPruebasReparacion("Ricardo", "Mantenimiento", "Laptop 22B03LA", pruebas, "---"); 
		
		assertEquals(false, resultado);
		
		// Prueba 3: Corroborar que no es posible crear un diagnostico de pruebas si uno de los campos es nulo
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioDiagnosticoPruebas.realizarPruebasReparacion("Ricardo", null, "Laptop 22B03LA", null, "---");   
			
		});	
		
		// Prueba 4: Corroborar que no es posible crear un diagnostico de pruebas si alguno de los campos obligatorios estan vacios 
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioDiagnosticoPruebas.realizarPruebasReparacion("", "Mantenimiento", "Laptop 22B03LA", pruebas, "---");
		});
		
		// Prueba 5: Corroborar que no es posible crear un diagnostico de pruebas si la lista de pruebas esta vacia
		
		pruebas.remove(0); 
		pruebas.remove(0);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioDiagnosticoPruebas.realizarPruebasReparacion("Ricardo", "Mantenimiento", "Laptop 22B03LA", pruebas, "---");
		});
	}
	
	@Test
	void testValidarCampos() {
		
		// Prueba 1: Ninguno de los campos pasados es nulo o vacio
		
		List<String> pruebas = new ArrayList<>();
		pruebas.add("Prueba 1"); 
		pruebas.add("Prueba 2"); 
		
		boolean resultado = servicioDiagnosticoPruebas.validarCampos("Ricardo", "Mantenimiento", "Laptop 22B03LA", pruebas, "---"); 
		
		assertEquals(true, resultado); 
		
		// Prueba 2: El campo de las observaciones si puede ser vacio
		
		resultado = servicioDiagnosticoPruebas.validarCampos("Ricardo", "Mantenimiento", "Laptop 22B03LA", pruebas, ""); 
		
		assertEquals(true, resultado); 
		
		// Prueba 3: Alguno de los campos es vacio
		
		resultado = servicioDiagnosticoPruebas.validarCampos("Ricardo", null, "Laptop 22B03LA", pruebas, ""); 
				
		assertEquals(false, resultado);
		
		// Prueba 3: Alguno de los campos obligatorios es vacio
		
		resultado = servicioDiagnosticoPruebas.validarCampos("",  "Mantenimiento", "Laptop 22B03LA", pruebas, ""); 
						
		assertEquals(false, resultado);
		
		// Prueba 3: La lista de pruebas es vacia
		
		pruebas.remove(0); 
		pruebas.remove(0);
		
		resultado = servicioDiagnosticoPruebas.validarCampos("Ricardo",  "Mantenimiento", "Laptop 22B03LA", pruebas, "---"); 
		
		assertEquals(false, resultado);
		
	}

}
