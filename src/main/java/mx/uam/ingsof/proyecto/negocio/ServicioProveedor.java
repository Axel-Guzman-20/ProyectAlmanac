package mx.uam.ingsof.proyecto.negocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ingsof.proyecto.datos.ProveedorRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Proveedor;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Esta clase controla el Servicio de los Proveedores
 * 
 * @author MiguelGuzman
 *
 */

@Slf4j
@Service
public class ServicioProveedor {
	
	@Autowired
	ProveedorRepository proveedorRepository;
	
	private static int maxProveedor = 100;
	private static int digitosMaxTelefono = 10;
	
	
	public boolean proveedoresRegistrados() {
		
		long registrados = proveedorRepository.count();
		
		int size = (int) registrados;
		
		// Si la cantidad de registros de proveedores, es menor a 100, significa que aún pueden ser registrados algunos proveedores (Criterio de aceptacion)
		if(size < maxProveedor)
			return true;
		else
			return false;
		
	}
	
	
	public String obtenerFechaActual() {
		
		String fecha;
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 
        fecha = dateFormat.format(new Date());
		
		return fecha;
	}
	
	
	
	public boolean verificarCorreoE(String correo) {
			
		Proveedor proveedor = proveedorRepository.findByCorreoElectronicoContacto(correo);
		
		// Si es igual a null, significa que el correo no ha sido registrado (Criterio de aceptacion)
		if(proveedor == null) 
			return false;
		else 
			return true;
				
	}
	
	
	public boolean correoValido(String email) {
		
	    // Patron para validar el email
	    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	    Matcher mather = pattern.matcher(email);
	    
	    return mather.find();
	}
	
	
	public boolean verificarTelefono(String telefono) {
		
		int i;
		char tel[];
		
		tel = telefono.toCharArray();
		
		// Cumple la longitud de un numero telefonico
		if(tel.length == digitosMaxTelefono) {
			
			// Recorre cada digito, si uno no es un digito, se sale del for
			for (i = 0; i < tel.length; i++) {
				if(!Character.isDigit(tel[i])) 
					break;
			}
			
			// Si llega a 10, significa que todos son números
			if(i==10)
				return true;
			else
				return false;
			
		}else
			return false;
	}
	
	
	
	/**
	 * 
	 * Permite registrar un proveedor
	 * 
	 * @param fecha
	 * @param nombreProveedor
	 * @param nombreContacto
	 * @param cargoContacto
	 * @param direccionProveedor
	 * @param correoEleContacto
	 * @param telefonoContacto
	 * @param sobreLaEmpresa
	 * @return true si el proveedor se agregó correctamente, false si hay un error
	 * @throws IllegalArgumentException si existe un error
	 */
	
	public boolean registrarProveedor(String fecha, String nombreProveedor, String nombreContacto, String cargoContacto,
			String direccionProveedor, String correoEleContacto, String telefonoContacto,  String sobreLaEmpresa) {
		try {
			// Muestra en las lineas de la ejecución del spring
			log.info("Registrando proveedor con fecha de registro: " + fecha + ", marca o empresa: " + nombreProveedor + " , nombre del Contacto: " + nombreContacto+ 
					", cargo del contacto: " + cargoContacto + ", direccion de la empresa: " + direccionProveedor + ", correo electrónico del contacto: " + correoEleContacto + 
					", teléfono del contacto: " + telefonoContacto + ", sobre la empresa:" + sobreLaEmpresa);
			
			// Crea una instancia tipo proveedor
			Proveedor proveedor = new Proveedor();
			
			proveedor.setFechaRegistro(fecha);
			proveedor.setNombreProveedor(nombreProveedor);
			proveedor.setNombreContacto(nombreContacto);
			proveedor.setCargoContacto(cargoContacto);
			proveedor.setDireccionProveedor(direccionProveedor);
			proveedor.setCorreoElectronicoContacto(correoEleContacto);
			proveedor.setTelefonoContacto(telefonoContacto);
			proveedor.setSobreLaEmpresa(sobreLaEmpresa);
						
			// Se registra en la BD
			proveedorRepository.save(proveedor);
			
			return true;
			
		}catch (Exception e) {
			return false;
		}
		
	}
	
}
