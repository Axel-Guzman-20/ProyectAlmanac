package mx.uam.ingsof.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ingsof.proyecto.datos.EmpleadoRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;

@ExtendWith(MockitoExtension.class)
class ServicioEmpleadoTest {

	@Mock
	private EmpleadoRepository empleadoRepository;
	@InjectMocks
	private ServicioEmpleado servicioEmpleado;
	@Test
	void test() {
		//Caso 1: No hay empleados registrados
		List <Empleado> empleados = servicioEmpleado.recuperaEmpleados();
		assertEquals(0, empleados.size());
						
		//Caso 2: Hay empleados registrados en la base de datos 
						
		ArrayList <Empleado> lista = new ArrayList <>();
		
		Empleado empleado = new Empleado();
		empleado.setIdEmpleado(1);
		empleado.setNombreCompleto("Eduardo Castro");
		empleado.setGenero("Masculino");
		empleado.setFechaIngreso("23/11/2022");
		empleado.setDireccionCompleta("Calle creacion mz 45");
		empleado.setCorreoElectronico("castro2000@hotmail,com");
		
		Empleado empleado1 = new Empleado();
		empleado1.setIdEmpleado(1);
		empleado1.setNombreCompleto("Eduardo Castro");
		empleado1.setGenero("Masculino");
		empleado1.setFechaIngreso("23/11/2022");
		empleado1.setDireccionCompleta("Calle creacion mz 45");
		empleado1.setCorreoElectronico("castro2000@hotmail,com");
		lista.add(empleado);
		lista.add(empleado1);
						
		List<Empleado> listaIterable = lista;
						
						
		when(empleadoRepository.findAll()).thenReturn(listaIterable);
		empleados = servicioEmpleado.recuperaEmpleados();
		assertEquals(2, lista.size());
	}

}
