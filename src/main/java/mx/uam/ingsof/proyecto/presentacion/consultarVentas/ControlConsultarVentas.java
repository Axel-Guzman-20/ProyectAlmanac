package mx.uam.ingsof.proyecto.presentacion.consultarVentas;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ingsof.proyecto.negocio.ServicioCliente;
import mx.uam.ingsof.proyecto.negocio.ServicioEmpleado;
import mx.uam.ingsof.proyecto.negocio.ServicioVenta;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;

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
				
					vistaConsultarVentas.muestra(this, empleado, cliente);
	
			}else
				vistaConsultarVentas.muestraDialogoConMensaje("No hay clientes registrados, por favor registra un cliente para consultar esta ventana.");
		}else 
			vistaConsultarVentas.muestraDialogoConMensaje("No hay empleados registrados, por favor registra un empleado para consultar esta ventana.");	
	}
	
	
	
	public String[][] consultarVentas(String fechaDesde, String fechaHasta, String nombreEmpleado, String nombreCliente, String montoVenta) {
			
		if(servicioVenta.sizeVentas() == 0) {
			vistaConsultarVentas.muestraDialogoConMensaje("No hay ventas registradas, registra una venta para usar está función");
			return null;
		}
		
		return servicioVenta.consultarVentas(fechaDesde, fechaHasta, nombreEmpleado, nombreCliente, montoVenta);
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
