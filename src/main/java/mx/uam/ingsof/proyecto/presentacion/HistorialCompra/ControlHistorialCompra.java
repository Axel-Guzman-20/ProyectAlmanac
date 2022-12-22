package mx.uam.ingsof.proyecto.presentacion.HistorialCompra;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ingsof.proyecto.negocio.ServicioCliente;
import mx.uam.ingsof.proyecto.negocio.ServicioVenta;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Venta;

/**
 * Control relacionado con las consultas de Ventas
 * 
 * @author AxelGuzman
 *
 */

@Component
public class ControlHistorialCompra {

	@Autowired
	private VistaHistorialCompra vistaHistorialCompra;

	@Autowired
	private ServicioCliente servicioCliente;

	@Autowired
	private ServicioVenta servicioVenta;

	public void inicia() {

		List<Cliente> cliente = servicioCliente.recuperaClientes();

		if (!cliente.isEmpty()) {
			vistaHistorialCompra.muestra(this, cliente);

		} else
			vistaHistorialCompra.muestraDialogoConMensaje(
					"No hay clientes registrados, por favor registra un cliente para consultar esta ventana.");
	}

	/**
	 * 
	 * Permite terminar la historia de usuario
	 * 
	 */
	public void cierraVentana() {

		vistaHistorialCompra.termina();

	}

	public void buscarHistorial(int idcliente, String fechaInicio, String fechaFinal) throws ParseException {

		// Valida que la fecha de inicio no sea mayor a la final
		if (!servicioVenta.comparaFechas(fechaInicio, fechaFinal)) {
			vistaHistorialCompra.muestraDialogoConMensaje("La fechaInicio es mayor a la FechaFinal");

		} else {

			List<Venta> ventas = servicioVenta.recuperaPorIdCliente(idcliente);

			if (!ventas.isEmpty()) {

				String[][] datos = servicioCliente.buscarHistorial(idcliente, fechaInicio, fechaFinal);

				if (datos != null)
					vistaHistorialCompra.mostrarHistorial(datos);

				else {
					vistaHistorialCompra.muestraDialogoConMensaje("No hay registro de ventas con la información proporcionada");
					vistaHistorialCompra.limpiaTabla();
				}

			} else
				vistaHistorialCompra.muestraDialogoConMensaje("El cliente no tiene compras registradas");

		}

	}

}
