package mx.uam.ingsof.proyecto.presentacion.consultarVentas;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ingsof.proyecto.negocio.ServicioCliente;
import mx.uam.ingsof.proyecto.negocio.ServicioEmpleado;
import mx.uam.ingsof.proyecto.negocio.ServicioVenta;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;

/**
 * Control relacionado con las consultas de Ventas
 * 
 * @author MiguelGuzman
 *
 */

@Component
public class ControlConsultarVentas {
	
	@Autowired
	private VistaConsultarVentas vistaConsultarVentas;
	
	@Autowired
	private ServicioCliente servicioCliente;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ServicioVenta servicioVenta;
	
	public void inicia() {
		
		List <Empleado> empleado = servicioEmpleado.recuperaEmpleados();
		
		List <Cliente> cliente = servicioCliente.recuperaClientes();
				
		if(empleado.size() != 0) {
			if(cliente.size() != 0) {
				if(servicioVenta.sizeVentas() != 0) {
					vistaConsultarVentas.muestra(this, empleado, cliente);
				}else
					vistaConsultarVentas.muestraDialogoConMensaje("No hay ventas registradas, registra una venta para utilizar esta función.");
			}else
				vistaConsultarVentas.muestraDialogoConMensaje("No hay clientes registrados, por favor registra un cliente para consultar esta ventana.");
		}else 
			vistaConsultarVentas.muestraDialogoConMensaje("No hay empleados registrados, por favor registra un empleado para consultar esta ventana.");	
	}
	
	
	
	public String[][] consultarVentas(String fechaDesde, String fechaHasta, String itemEmpleadoId, String itemClienteId, String montoVenta) throws ParseException {
		
		/*
		// Valida que haya registros
		if(servicioVenta.sizeVentas() == 0) {
			vistaConsultarVentas.muestraDialogoConMensaje("No hay ventas registradas");
			return null;
		}
		*/
		
		// Valida que la fecha de inicio no sea mayor a la final
		if(servicioVenta.comparaFechas(fechaDesde, fechaHasta) == false) {
			vistaConsultarVentas.muestraDialogoConMensaje("La fechaInicio es mayor a la FechaFinal");
			return null;
		}
		
		// Valida que el monto sea una cifra correcta
		if(servicioVenta.validarMonto(montoVenta) == false) {
			vistaConsultarVentas.muestraDialogoConMensaje("El monto de la venta es incorrecto");
			return null;
		}
		
		String datos[][] = servicioVenta.consultarVentas(fechaDesde, fechaHasta, itemEmpleadoId, itemClienteId, montoVenta);
		
		if(datos != null)
			return datos;
		else
			vistaConsultarVentas.muestraDialogoConMensaje("No hay registro de ventas con la información proporcionada");
			return null;
	}
	
	
	/**
	 * 
	 * Permite terminar la historia de usuario   
	 * 
	 */
	public void cierraVentana() {
	
		vistaConsultarVentas.termina();
	
	}
	
}
