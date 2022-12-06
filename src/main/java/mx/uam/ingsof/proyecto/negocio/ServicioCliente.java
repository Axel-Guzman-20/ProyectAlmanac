package mx.uam.ingsof.proyecto.negocio;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ingsof.proyecto.datos.ClienteRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;

/**
 * Esta clase controla el Servicio de los Clientes
 * 
 * @author AxelGuzman
 *
 */

@Slf4j
@Service
public class ServicioCliente {

	@Autowired
	ClienteRepository clienteRepository;

	private static int digitosMaxTelefono = 10;

	public String obtenerFechaActual() {

		String fecha;

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		fecha = dateFormat.format(new Date());

		return fecha;
	}

	public boolean verificarCorreoElectronico(String correo) {

		Cliente cliente = clienteRepository.findBycorreoelectronico(correo);
		// Si es igual a null, significa que el correo no ha sido registrado (Criterio
		// de aceptacion)
		if (cliente == null)
			return false;
		else
			return true;

	}

	public boolean correoValido(String email) {

		// Patron para validar el email
		Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);

		return mather.find(); // devuelve un true si es un correo valido
	}

	public boolean verificarTelefono(String telefono) {

		int i;
		char tel[];

		tel = telefono.toCharArray();

		// Cumple la longitud de un numero telefonico
		if (tel.length == digitosMaxTelefono) {

			// Recorre cada digito, si uno no es un digito, se sale del for
			for (i = 0; i < tel.length; i++) {
				if (!Character.isDigit(tel[i]))
					break;
			}

			// Si llega a 10, significa que todos son números
			if (i == 10)
				return true;
			else
				return false;

		} else
			return false;
	}

	/**
	 * 
	 * Permite registrar un cliente
	 * 
	 * @param fecha
	 * @param nombreCompleto
	 * @param genero
	 * @param direccion
	 * @param telefono
	 * @param correoelectronico
	 * @return true si el cliente se agregó correctamente, false si hay un error
	 * @throws IllegalArgumentException si existe un error
	 */

	public boolean registrarCliente(String fecha, String nombreCompleto, String genero, String direccion,
			String telefono, String correoelectronico) {
		try {
			// Muestra en las lineas de la ejecución del spring
			log.info("Registrando cliente con fecha de registro: " + fecha + ", Nombre Completo: " + nombreCompleto
					+ ", genero: " + genero + ", Direccion: " + direccion + ", Telefono: " + telefono
					+ ", Correo Electronico: " + correoelectronico);

			// Crea una instancia tipo cliente
			Cliente cliente = new Cliente();

			cliente.setFechaRegistro(fecha);
			cliente.setNombreCompleto(nombreCompleto);
			cliente.setGenero(genero);
			cliente.setDireccion(direccion);
			cliente.setTelefono(telefono);
			cliente.setCorreoelectronico(correoelectronico);

			// Se registra en la BD
			clienteRepository.save(cliente);

			return true;

		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 
	 * Permite modificar un cliente existente en la base de datos
	 * 

	 * @param nombreCompleto
	 * @param genero
	 * @param direccion
	 * @param telefono
	 * @param correoelectronico
	 * @return true si el cliente se modifico correctamente, false si hay un error
	 * @throws IllegalArgumentException si existe un error
	 */

	public Cliente modificarCliente( long id, String nombreCliente, String genero, String direccion,
			String telefono, String correoElectronico) {

		Cliente cliente = clienteRepository.findByIdCliente(id);

		if (cliente == null) {
			throw new IllegalArgumentException("El Cliente no existe");
		}

		log.info("Modificando al cliente con nombre: " + nombreCliente + ", genero:" + genero + ", direccion:"
				+ direccion + ", telefono:" + telefono + ", correo electronico:" + correoElectronico);

		if (!nombreCliente.equals("")) {
			cliente.setNombreCompleto(nombreCliente);
		}
		if (!genero.equals("")) {
			cliente.setGenero(genero);
		}
		if (!direccion.equals("")) {
			cliente.setDireccion(direccion);
		}
		if (!telefono.equals("")) {
			cliente.setTelefono(telefono);
		}
		if (!correoElectronico.equals("")) {
			cliente.setCorreoelectronico(correoElectronico);
		}

		cliente = clienteRepository.save(cliente);

		return cliente;

	}




	public Cliente obtenerCliente(long id) {

		Cliente cliente = clienteRepository.findByIdCliente(id);

		if (cliente == null) {
			throw new IllegalArgumentException("El cliente no existe");
		} else
			return cliente;
	}

	/**
	 * 
	 * Recupera un listado de todas los clientes disponibles en la base de datos
	 * 
	 * @param
	 * @return Una lista con todos los clientes disponibles en la base de datos
	 */

	public List<Cliente> consultarClientesDisponibles() {

		List<Cliente> clientes = new ArrayList<>();

		for (Cliente nombreCompleto : clienteRepository.findAll()) {
			clientes.add(nombreCompleto);
		}

		return clientes;
	}
	
	
	/**
	 * 
	 * Compara el correo electronico registrado con el nuevo correo electronico retorna un
	 * false si el correo nuevo es igual a un correo de diferentecliente
	 * true si el nuevo correo es el mismo correo al cliente a modificar
	 * */
	public boolean comparacorreos(String correo1, String correo2) {

		if (correo1.compareTo(correo2) == 0) {
			return true;
		} else
			return false;

	}

	/**
	 * Recupera todos los clientes existentes 
	 * 
	 * @return Una lista con todos los clientes existentes. Una lista vacía
	 */
	public List<Cliente> recuperaClientes() {

		List<Cliente> listaClientes = new ArrayList<>();

		for (Cliente cliente : clienteRepository.findAll()) {
			listaClientes.add(cliente);
		}
		return listaClientes;
	}

}