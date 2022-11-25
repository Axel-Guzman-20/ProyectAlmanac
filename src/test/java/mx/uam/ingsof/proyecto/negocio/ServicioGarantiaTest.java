package mx.uam.ingsof.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ingsof.proyecto.datos.GarantiaRepository;
import mx.uam.ingsof.proyecto.negocio.ServicioGarantia;
import mx.uam.ingsof.proyecto.negocio.modelo.Compra;
import mx.uam.ingsof.proyecto.negocio.modelo.Garantia;


/**
 * Implementacion de las pruebas unitarias del ServicioGarantia
 * 
 * @author Eduardo Castro
 *
 */
@ExtendWith(MockitoExtension.class)
class ServicioGarantiaTest extends ServicioGarantia {

	
	@Mock
	private GarantiaRepository garantiaRepository;
	@InjectMocks
	private ServicioGarantia servicioGarantia;
	
	
	@Test
	void test() {
		
		//Casos: Pasamos argumentos invalidos 
		Compra compra  = new Compra();
		String nombre = "Eduardo";
		String facha = "26/09/2022";
		String calle = "Creacion";
		String numExt = "16";
		String numInt = "15";
		String descripcionEquipo = "Impresora Hp Lj P1102w";
				
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioGarantia.creaGarantia(null, nombre, facha, calle, numExt, numInt, descripcionEquipo);
		});

		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioGarantia.creaGarantia(compra, null, facha, calle, numExt, numInt, descripcionEquipo);
		});
		
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioGarantia.creaGarantia(compra, nombre, null, calle, numExt, numInt, descripcionEquipo);
		});
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioGarantia.creaGarantia(compra, nombre, facha, null, numExt, numInt, descripcionEquipo);
		});
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioGarantia.creaGarantia(compra, nombre, facha, calle, null, numInt, descripcionEquipo);
		});
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioGarantia.creaGarantia(compra, nombre, facha, calle, numExt, null, descripcionEquipo);
		});
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioGarantia.creaGarantia(compra, nombre, facha, calle, numExt, numInt, null);
		});
		//Caso; Pasamos los arguementos validos y guardamos la garantia
		Garantia garantia = new Garantia();
		
		garantia.setCompra(compra);
		garantia.setNombreCompleto(nombre);
		garantia.setFecha(facha);
		garantia.setCalle(calle);
		garantia.setNumExt(numExt);
		garantia.setNumInt(numInt);
		garantia.setDescripcionEquipo(descripcionEquipo);
		
		when(garantiaRepository.save(garantia)).thenReturn(garantia);
		garantia = servicioGarantia.creaGarantia(compra, nombre, facha, calle, numExt, numInt, descripcionEquipo);
		assertEquals(garantia, garantia);
		

	}

}
