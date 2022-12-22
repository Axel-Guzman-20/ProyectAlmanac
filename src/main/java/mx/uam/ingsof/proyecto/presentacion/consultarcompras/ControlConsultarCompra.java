package mx.uam.ingsof.proyecto.presentacion.consultarcompras;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.ServicioCompra;
import mx.uam.ingsof.proyecto.negocio.ServicioEmpleado;
import mx.uam.ingsof.proyecto.negocio.modelo.Compra;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;

@Component
public class ControlConsultarCompra implements Serializable{

	/**
	 * Nos indica la versión del controlador de la HU 10
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private VistaConsultarCompras vistaConsultarCompras;
	
	@Autowired
	private ServicioCompra servicioCompra;
	
	public void inicia() {
		
		List <Empleado> empleado = servicioEmpleado.recuperaEmpleados();
		List <Compra> compra = servicioCompra.recuperaCompras();
	
		if(!empleado.isEmpty()) {
				if(!compra.isEmpty()) {
					
					vistaConsultarCompras.muestra(this, empleado);
				
				}else
					vistaConsultarCompras.muestraDialogoConMensaje("No hay compras registradas, por favor registra una compra para consultar esta ventana.");
		}else 
			vistaConsultarCompras.muestraDialogoConMensaje("No hay empleados registrados, por favor registra un empleado para consultar esta ventana.");	
		
	}

	
	public void consultarCompras(String fechaDesde, String fechaHasta, int indexEmpleado, String nombreProveedor, String nombreProducto) throws ParseException {
		
		boolean fechasCongruentes = true;
		String[][] datos;
		
		
		// En caso de que este fechaInicio y fechaHasta, se comparan en que fechaFinal sea mayor a la de inicio
		if(!fechaDesde.isEmpty() && !fechaHasta.isEmpty()) {
			fechasCongruentes = servicioCompra.comparaFechas(fechaDesde, fechaHasta);
		}
		
		if(fechasCongruentes) {
			datos = servicioCompra.consultarCompras(fechaDesde, fechaHasta, indexEmpleado, nombreProveedor, nombreProducto);
			vistaConsultarCompras.mostrarCompras(datos);
		}else
			vistaConsultarCompras.muestraDialogoConMensaje("La fechaInicio es mayor a la FechaFinal.");
		
		
	}
	
}
