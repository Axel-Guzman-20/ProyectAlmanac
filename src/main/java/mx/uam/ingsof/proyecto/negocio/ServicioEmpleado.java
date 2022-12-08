package mx.uam.ingsof.proyecto.negocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ingsof.proyecto.datos.EmpleadoRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;

/**
 * Esta clase controla el Servicio de Empleado
 * 
 * @author MiguelGuzman
 *
 */

@Slf4j
@Service
public class ServicioEmpleado {
	
	
	@Autowired
	EmpleadoRepository empleadoRepository;
	
	
	
	public String obtenerFechaActual() {
		
		String fecha;
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 
        fecha = dateFormat.format(new Date());
		
		return fecha;
	}
	
	
	
	public boolean registrarEmpleado(String fecha, String nombreCompleto, String genero, String correoElectronico, String telefono, String direccionCompleta) {
		try {
			// Muestra en las lineas de la ejecución del spring
			log.info("Registrando empleado con fecha de registro: " + fecha + ", nombre empleado: " + nombreCompleto + " , genero: " + genero+ 
					", correo electronico del empleado: " + correoElectronico + ", telefono del empleado: " + telefono + 
					", direccion del empleado: " + direccionCompleta);
			
			// Crea una instancia tipo empleado
			Empleado empleado = new Empleado();
			
			empleado.setFechaIngreso(fecha);
			empleado.setNombreCompleto(nombreCompleto);
			empleado.setGenero(genero);
			empleado.setCorreoElectronico(correoElectronico);
			empleado.setTelefono(telefono);
			empleado.setDireccionCompleta(direccionCompleta);
						
			// Se registra en la BD
			empleadoRepository.save(empleado);
			
			return true;
			
		}catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * Recupera todos los empleados existentes 
	 * Contrato:
	 * @return Una lista con todos los empleados existentes
	 *         Una lista vacía 
	 */	
	public List<Empleado> recuperaEmpleados(){
		
		List<Empleado> listaEmpleados = new ArrayList <>();
		
		for(Empleado empleado:empleadoRepository.findAll()) {
			listaEmpleados.add(empleado);
		}
		return listaEmpleados;
	}


}
