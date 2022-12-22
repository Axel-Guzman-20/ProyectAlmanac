package mx.uam.ingsof.proyecto.negocio;

import java.text.DateFormat;
import java.text.ParseException;
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
import mx.uam.ingsof.proyecto.datos.VentaProductoRepository;
import mx.uam.ingsof.proyecto.datos.VentaRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Venta;
import mx.uam.ingsof.proyecto.negocio.modelo.VentaProducto;

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

	@Autowired
	VentaProductoRepository ventaProductoRepository;

	@Autowired
	private VentaRepository ventaRepository;

	private static int digitosMaxTelefono = 10;
	String date = "dd/MM/yyyy";

	public String obtenerFechaActual() {

		String fecha;
		
		DateFormat dateFormat = new SimpleDateFormat(date);

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
		char []tel;

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
	 * 
	 * @param nombreCompleto
	 * @param genero
	 * @param direccion
	 * @param telefono
	 * @param correoelectronico
	 * @return true si el cliente se modifico correctamente, false si hay un error
	 * @throws IllegalArgumentException si existe un error
	 */

	public Cliente modificarCliente(long id, String nombreCliente, String genero, String direccion, String telefono,
			String correoElectronico) {

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
	 * Compara el correo electronico registrado con el nuevo correo electronico
	 * retorna un false si el correo nuevo es igual a un correo de diferentecliente
	 * true si el nuevo correo es el mismo correo al cliente a modificar
	 */
	public boolean comparaCorreos(String correo1, String correo2) {

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

	//
	// HU-08
	//

	// Recupera todos las compras de los clientes segun flitros
	// @param nombreCompleto
	// @param genero
	// @param direccion
	
	public String[][] buscarHistorial(int idCliente, String fechaInicio, String fechaFinal) throws ParseException {

		List<Venta> ventas = ventaRepository.findByIdCliente(idCliente);

		// Para mostrar todas las compras del cliente
		if (fechaInicio.equals("") && fechaFinal.equals(""))
			return convertirListaString(ventas);

		// Si no son vacias algunas de las fechas, aplica el criterio
		if (!fechaInicio.equals("") || !fechaFinal.equals(""))
			ventas = criterioFechas(fechaInicio, fechaFinal, ventas);

		if (!ventas.isEmpty())
			return convertirListaString(ventas);

		else
			return null;

	}

	public List<Venta> criterioFechas(String fechaDesde, String fechaHasta, List<Venta> ventas) throws ParseException {

		Date fechaInicio;
		Date fechaFinal;
		Date fechaVenta;
		SimpleDateFormat fechaFormato = new SimpleDateFormat(date);
		List<Venta> nuevaVenta = new ArrayList<>();
		int i;

		// Para mostrar de acuerdo con las fechas
		if (!fechaDesde.equals("") && !fechaHasta.equals("")) {
			fechaInicio = fechaFormato.parse(fechaDesde);
			fechaFinal = fechaFormato.parse(fechaHasta);

			for (i = 0; i < ventas.size(); i++) {

				fechaVenta = fechaFormato.parse(ventas.get(i).getFechaVenta());

				if (fechaVenta.compareTo(fechaInicio) >= 0 && fechaVenta.compareTo(fechaFinal) <= 0)
					nuevaVenta.add(ventas.get(i));

			}

			return nuevaVenta;
		}

		// Solo tiene fecha de inicio
		if (!fechaDesde.equals("") && fechaHasta.equals("")) {
			fechaInicio = fechaFormato.parse(fechaDesde);

			for (i = 0; i < ventas.size(); i++) {

				fechaVenta = fechaFormato.parse(ventas.get(i).getFechaVenta());

				if (fechaVenta.compareTo(fechaInicio) >= 0)
					nuevaVenta.add(ventas.get(i));
			}

			return nuevaVenta;
		}

		// Solo tiene fecha final
		if (fechaDesde.equals("") && !fechaHasta.equals("")) {
			fechaFinal = fechaFormato.parse(fechaHasta);

			for (i = 0; i < ventas.size(); i++) {
				fechaVenta = fechaFormato.parse(ventas.get(i).getFechaVenta());

				if (fechaVenta.compareTo(fechaFinal) <= 0)
					nuevaVenta.add(ventas.get(i));
			}

			return nuevaVenta;
		}

		return ventas;
	}

	public String[][] convertirListaString(List<Venta> ventas) {

		int registrosVentas;
		int columnasTabla = 5;

		double precioTotal;
		int cantidad;
		double precio;

		List<VentaProducto> ventasProducto;
		String[][] datos;

		int i;
		int k = 0;

		registrosVentas = cuentaProductosPorVenta(ventas);

		datos = new String[registrosVentas][columnasTabla];

	

		for (i = 0; i < ventas.size(); i++) {

			ventasProducto = ventaProductoRepository.findByIdVenta(ventas.get(i).getIdVenta());

			for (int j = 0; j < ventasProducto.size(); j++) {

				// Estos datos ya vienen en la venta
				datos[k][0] = String.valueOf(ventas.get(i).getFechaVenta());

				datos[k][1] = String.valueOf(ventasProducto.get(j).getProducto().getNombre());

				cantidad = ventasProducto.get(j).getCantidad();
				datos[k][2] = String.valueOf(cantidad);

				precio = ventasProducto.get(j).getProducto().getPrecio();
				datos[k][3] = String.valueOf(precio);

				precioTotal = cantidad * precio;
				datos[k][4] = String.valueOf(precioTotal);
				k++;
			}

		}

		return datos;
	}

	public int cuentaProductosPorVenta(List<Venta> venta) {

		int i;
		int cantidadProductosVendidos = 0;
		List<VentaProducto> ventasProducto;

		for (i = 0; i < venta.size(); i++) {
			ventasProducto = ventaProductoRepository.findByIdVenta(venta.get(i).getIdVenta());

			cantidadProductosVendidos = cantidadProductosVendidos + ventasProducto.size();

		}

		return cantidadProductosVendidos;
	}

	public boolean comparaFechas(String fechaDesde, String fechaHasta) {

		if (!fechaDesde.equals("") && !fechaHasta.equals("")) {

			SimpleDateFormat fechaFormato = new SimpleDateFormat(date);

			Date fechaInicio;
			Date fechaFinal;

			try {
				fechaInicio = fechaFormato.parse(fechaDesde);
				fechaFinal = fechaFormato.parse(fechaHasta);
				// Significa que la fecha de inicio es mayor a la final
				if (fechaInicio.compareTo(fechaFinal) > 0)
					return false;

				return true;
			} catch (ParseException e) {
				return false;
			}
		}
		return true;
	}

}