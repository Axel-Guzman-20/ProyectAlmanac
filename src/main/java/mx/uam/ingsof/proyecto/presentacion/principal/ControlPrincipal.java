package mx.uam.ingsof.proyecto.presentacion.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ingsof.proyecto.presentacion.agregarProducto.ControlAgregarProducto;
import mx.uam.ingsof.proyecto.presentacion.buscarClientes.ControlBuscarClientes;
import mx.uam.ingsof.proyecto.presentacion.buscarProducto.ControlBuscarProducto;
import mx.uam.ingsof.proyecto.presentacion.consultarVentas.ControlConsultarVentas;
import mx.uam.ingsof.proyecto.presentacion.crearDiagnostico.ControlCrearDiagnostico;
import mx.uam.ingsof.proyecto.presentacion.crearGarantia.ControlCrearGarantia;
import mx.uam.ingsof.proyecto.presentacion.modificarCliente.ControlModificarCliente;
import mx.uam.ingsof.proyecto.presentacion.modificarProducto.ControlModificarProducto;
import mx.uam.ingsof.proyecto.presentacion.registrarProveedor.ControlRegistrarProveedor;
import mx.uam.ingsof.proyecto.presentacion.registrarVenta.ControlRegistrarVenta;
import mx.uam.ingsof.proyecto.presentacion.registrarCliente.ControlRegistrarCliente;
import mx.uam.ingsof.proyecto.presentacion.registrarCompra.ControlRegistrarCompra;

@Component
public class ControlPrincipal{
	
	@Autowired
	private ControlAgregarProducto controlAgregarProducto; 
	
	@Autowired
	private ControlModificarProducto controlModificarProducto; 
	
	@Autowired
	private VentanaPrincipal ventana;
	
	@Autowired
	private ControlCrearGarantia controlCrearGarantia;
	
	@Autowired
	private ControlRegistrarProveedor controlRegistrarProveedor;
	
	@Autowired
	private ControlRegistrarCliente controlRegistrarCliente;
	
	@Autowired
	private ControlModificarCliente controlModificarCliente;
	
	@Autowired
	private ControlBuscarProducto controlBuscarProducto;
	
	@Autowired
	private ControlRegistrarVenta controlRegistrarVenta;
	
	@Autowired
	private ControlConsultarVentas controlConsultarVentas;
	
	@Autowired
	private ControlCrearDiagnostico controlCrearDiagnostico;
	
	@Autowired
	private ControlRegistrarCompra controlRegistrarCompra;
	
	@Autowired
	private ControlBuscarClientes controlBuscarClientes;
	
	
	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia() {
		ventana.muestra(this);
	}
	
	
	/**
	 * Método que arranca la historia de usuario "agregar productos al catalogo"
	 * 
	 */
	public void agregarProducto() {
		controlAgregarProducto.inicia();
	}
	
	
	/**
	 * Método que arranca la historia de usuario "modificar productos del catalogo"
	 * 
	 */
	public void modificarProducto() {
		controlModificarProducto.inicia();
	}

	
	
	/**
	 * Método que arranca la historia de usuario "Crea garantía"
	 * 
	 */
	public void creaGarantia() {
		controlCrearGarantia.inicia();
	}
	
	/**
	 * HISTORIAS DE USUARIO DEL PRIMER SPRINT
	 * 
	 */
	
	/**
	 * Método que arranca la historia de usuario "Nuevo proveedor"
	 * 
	 */
	public void nuevoProveedor() {
		controlRegistrarProveedor.inicia();
	}
	
	/**
	 * Método que arranca la historia de usuario "Nuevo cliente"
	 * 
	 */
	public void nuevoCliente() {
		controlRegistrarCliente.inicia();
	}
	
	
	/**
	 * Método que arranca la historia de usuario "Modificar Cliente"
	 * 
	 */
	public void modificarCliente() {
		
		controlModificarCliente.inicia();
		
	}
	
	/**
	 * Método que arranca la historia de usuario "Buscar Productos en Catalogo"
	 * 
	 */
	public void buscarProducto() {
		
		controlBuscarProducto.inicia();
		
	}
	
	/**
	 * Método que arranca la historia de usuario "Registra Ventas"
	 * 
	 */
	public void nuevaVenta() {
		
		controlRegistrarVenta.inicia();
		
	}
	/**
	 * Método que arranca la historia de usuario "Registra Compras"
	 * 
	 */
	public void nuevaCompra() {
		
		controlRegistrarCompra.inicia();
		
	}
	
	/**
	 * Método que arranca la historia de usuario "Crear Diagnostico"
	 * 
	 */
	public void crearDiagnostico() {
		
		controlCrearDiagnostico.inicia(); 
	}
	
	/**
	 * Método que arranca la historia de usuario "Consultar Ventas"
	 * 
	 */
	public void mostrarVentas() {
		
		controlConsultarVentas.inicia();
		
	}
	/**
	 * Método que arranca la historia de usuario "Buscar Clientes"
	 * 
	 */
	public void buscarClientes() {
		
		controlBuscarClientes.inicia();
		
	}

}