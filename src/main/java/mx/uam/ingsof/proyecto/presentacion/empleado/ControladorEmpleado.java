package mx.uam.ingsof.proyecto.presentacion.empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.Data;
import mx.uam.ingsof.proyecto.negocio.ServicioEmpleado;

@Component
@Data
public class ControladorEmpleado {
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	private String fechaIngreso;
	private String nombreCompleto;
	private String genero;
	private String correoElectronico;
	private String telefono;
	private String direccionCompleta;

	
	public void registraEmpleado(String nombreCompleto, String genero, String correoElectronico, String telefono, String direccionCompleta) {

		fechaIngreso = servicioEmpleado.obtenerFechaActual();
		
		servicioEmpleado.registrarEmpleado(fechaIngreso, nombreCompleto, genero, correoElectronico, telefono, direccionCompleta);
		
	}
	
	
}
