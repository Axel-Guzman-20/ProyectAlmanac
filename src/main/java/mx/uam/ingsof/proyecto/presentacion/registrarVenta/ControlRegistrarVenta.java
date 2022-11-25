package mx.uam.ingsof.proyecto.presentacion.registrarVenta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.ServicioCliente;
import mx.uam.ingsof.proyecto.negocio.ServicioEmpleado;
import mx.uam.ingsof.proyecto.negocio.ServicioProducto;
import mx.uam.ingsof.proyecto.negocio.ServicioVenta;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.VentaProducto;


@Component
public class ControlRegistrarVenta {
	
	@Autowired
	private VentanaRegistrarVenta ventanaRegistrarVenta;
	
	@Autowired
	private ServicioCliente servicioCliente;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ServicioVenta servicioVenta;
	/**
	 * Inicia la historia de usuario
	 * 
	 */
	public void inicia(){
		List <Cliente> listaClinetes = servicioCliente.recuperaClientes();
		List <Empleado> listaEmpleados = servicioEmpleado.recuperaEmpleados();
		List <Producto> listaProducto = servicioProducto.consultarProductosDisponibles();
		ventanaRegistrarVenta.muestra(this, listaClinetes, listaEmpleados,listaProducto);
	}
	public VentaProducto agregarProducto(Producto producto,int cantidad) {
		
		return  servicioVenta.agregaProducto(producto, cantidad);
		
	}
	public void CrearVenta(Long idCliente,Long idEmpleado, String fecha,List<VentaProducto> productos) {
		
		 try {
			 servicioVenta.creaVenta(idCliente, idEmpleado, fecha, productos);
			 ventanaRegistrarVenta.muestraDialogoConMensaje("Venta agregada correctamente");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("La venta no pudo ser creada "+ e.getMessage());
			ventanaRegistrarVenta.muestraDialogoConMensaje("La venta no pudo ser creada "+ e.getMessage());
		}
		termina();
	
	}
	/**
	 * Termina la historia de usuario
	 * 
	 */
	public void termina() {
		ventanaRegistrarVenta.setVisible(false);
	}
	

}
