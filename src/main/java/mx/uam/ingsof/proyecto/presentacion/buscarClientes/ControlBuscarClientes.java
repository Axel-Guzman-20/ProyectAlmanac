package mx.uam.ingsof.proyecto.presentacion.buscarClientes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.ServicioCliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
/**
 * 
 * Módulo de control para la historia de usuario BuscarCliente
 * 
 * @author EduardoCastro
 *
 */
@Component
public class ControlBuscarClientes implements Serializable {
	/**
	 * Indica la version del Controlador de la HU-07
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private VistaBuscarClientes vistaBuscarClientes;
	
	@Autowired
	private ServicioCliente servicioCliente;
	/**
	 * 
	 * Permite dar inicio al módulo ControlBuscarCliente y a sus respectivos métodos de dicho módulo  
	 * 
	 */
	public void inicia() {
		
		vistaBuscarClientes.muestra(this);
	}
	/**
	 * 
	 * Manda a llamar al método buscaClienteById() del módulo {@link ServicioCliente} pasandole los mismos parámetros recibidos 
	 * 
	 * @param idCliente
	 * 
	 */
	public void buscaById(String idCliente) {
		Cliente cliente = null;
		try {
			cliente = servicioCliente.buscaClienteById(idCliente);
			if(cliente != null)
				vistaBuscarClientes.muestra(cliente);
			else
				vistaBuscarClientes.muestraDialogoConMensaje("El cliente no se encontro, ¡No se encuentra registrado en el sistema!");
		} catch (Exception e) {e.printStackTrace();}
	}
	/**
	 * 
	 * Manda a llamar al método buscaByName() del módulo {@link ServicioCliente} pasandole los mismos parámetros recibidos 
	 * 
	 * @param nombre
	 * 
	 */
	public void buscaByName(String nombre) {
		List<Cliente> listaClientes = new ArrayList<>();
		try {
			listaClientes = servicioCliente.buscaClientebyName(nombre);
			if(!listaClientes.isEmpty())
				vistaBuscarClientes.muestra(listaClientes);
			else
				vistaBuscarClientes.muestraDialogoConMensaje("El nombre ingresado "+nombre+" no fue encontrado. Te recomendamos buscar por Id");
		} catch (Exception e) {e.printStackTrace();}
	}
	/**
	 * 
	 * Manda a llamar al método buscaAll() del módulo {@link ServicioCliente}  
	 * 
	 * 
	 * 
	 */
	public void buscaAll() {
		List<Cliente> listaClientes = new ArrayList<>();
		try {
			listaClientes = servicioCliente.consultarClientesDisponibles();
			if(!listaClientes.isEmpty())
				vistaBuscarClientes.muestra(listaClientes);
			else
				vistaBuscarClientes.muestraDialogoConMensaje("No hay clientes registrados");
		} catch (Exception e) { e.printStackTrace();}
	}
	/**
	 * 
	 * Manda a llamar al método buscaById() del módulo {@link ServicioCliente} pasandole los mismos parámetros recibidos 
	 * 
	 * @param idCliente
	 * @param nombre
	 */
	public void buscaById(String idCliente,String nombre) {
		Cliente cliente = null;
		try {
			cliente = servicioCliente.buscaClienteById(idCliente);
			if(cliente != null && cliente.getNombreCompleto().equals(nombre))
				vistaBuscarClientes.muestra(cliente);
			else {
				vistaBuscarClientes.muestraDialogoConMensaje("¡Debes ingresar el nombre completo e ingresar un ID existente!");
			}
				
		} catch (Exception e) {e.printStackTrace();}
	}
	/**
	 * Permite dar fin a la historia de usuario
	 * 
	 * @param 
	 * 
	 */
	public void termina() {
		vistaBuscarClientes.setVisible(false);
	}
}
