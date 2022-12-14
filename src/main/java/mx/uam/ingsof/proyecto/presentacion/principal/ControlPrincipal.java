package mx.uam.ingsof.proyecto.presentacion.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.presentacion.HistorialCompra.ControlHistorialCompra;
import mx.uam.ingsof.proyecto.presentacion.agregarProducto.ControlAgregarProducto;
import mx.uam.ingsof.proyecto.presentacion.buscarClientes.ControlBuscarClientes;
import mx.uam.ingsof.proyecto.presentacion.buscarProducto.ControlBuscarProducto;
import mx.uam.ingsof.proyecto.presentacion.consultarVentas.ControlConsultarVentas;
import mx.uam.ingsof.proyecto.presentacion.consultarcompras.ControlConsultarCompra;
import mx.uam.ingsof.proyecto.presentacion.crearDiagnostico.ControlCrearDiagnostico;
import mx.uam.ingsof.proyecto.presentacion.crearGarantia.ControlCrearGarantia;
import mx.uam.ingsof.proyecto.presentacion.crearPruebasReparacion.ControlCrearPruebasReparacion;
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
	private ControlHistorialCompra controlHistorialCompra;
		
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

	private ControlConsultarCompra controlConsultarCompras;
	
	@Autowired
	private ControlBuscarClientes controlBuscarClientes;
	
	@Autowired
	private ControlCrearPruebasReparacion controlCrearPruebasReparacion; 
	
	
	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia() {
		ventana.muestra(this);
	}
	
	
	/**
	 * M??todo que arranca la historia de usuario "agregar productos al catalogo"
	 * 
	 */
	public void agregarProducto() {
		controlAgregarProducto.inicia();
	}
	
	
	/**
	 * M??todo que arranca la historia de usuario "modificar productos del catalogo"
	 * 
	 */
	public void modificarProducto() {
		controlModificarProducto.inicia();
	}

	
	
	/**
	 * M??todo que arranca la historia de usuario "Crea garant??a"
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
	 * M??todo que arranca la historia de usuario "Nuevo proveedor"
	 * 
	 */
	public void nuevoProveedor() {
		controlRegistrarProveedor.inicia();
	}
	
	/**
	 * M??todo que arranca la historia de usuario "Nuevo cliente"
	 * 
	 */
	public void nuevoCliente() {
		controlRegistrarCliente.inicia();
	}
	
	
	/**
	 * M??todo que arranca la historia de usuario "Modificar Cliente"
	 * 
	 */
	public void modificarCliente() {
		
		controlModificarCliente.inicia();
		
	}
	
	/**
	 * M??todo que arranca la historia de usuario "Historial Compra Cliente"
	 * 
	 */
	public void HistorialCompra() {
		controlHistorialCompra.inicia();
	
	}
	
	/**
	 * M??todo que arranca la historia de usuario "Buscar Productos en Catalogo"
	 * 
	 */
	public void buscarProducto() {
		
		controlBuscarProducto.inicia();
		
	}
	
	/**
	 * M??todo que arranca la historia de usuario "Registra Ventas"
	 * 
	 */
	public void nuevaVenta() {
		
		controlRegistrarVenta.inicia();
		
	}
	/**
	 * M??todo que arranca la historia de usuario "Registra Compras"
	 * 
	 */
	public void nuevaCompra() {
		
		controlRegistrarCompra.inicia();
		
	}
	
	/**
	 * M??todo que arranca la historia de usuario "Crear Diagnostico"
	 * 
	 */
	public void crearDiagnostico() {
		
		controlCrearDiagnostico.inicia(); 
	}
	
	/**
	 * M??todo que arranca la historia de usuario "Consultar Ventas"
	 * 
	 */
	public void mostrarVentas() {
		
		controlConsultarVentas.inicia();
		
	}
	
	/**
	 * M??todo que arranca la historia de usuario "Consultar Compras"
	 * 
	 */
	public void mostrarCompras() {
		
		controlConsultarCompras.inicia();
		
	}

	/**
	 * M??todo que arranca la historia de usuario "Buscar Clientes"
	 * 
	 */
	public void buscarClientes() {
		
		controlBuscarClientes.inicia();

	}
	
	/**
	 * M??todo que arranca la historia de usuario "CrearPruebasReparacion"
	 * 
	 */
	public void realizarPruebasReparacion() {
		
		controlCrearPruebasReparacion.inicia();

	}

}
