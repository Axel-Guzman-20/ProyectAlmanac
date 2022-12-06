package mx.uam.ingsof.proyecto.presentacion.buscarProducto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.ServicioProducto;
import mx.uam.ingsof.proyecto.negocio.ServicioSeccionCatalogo;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.SeccionCatalogo;

/**
 * 
 * Módulo de control para la historia de usuario AgregarProducto
 * 
 * @author abigailmorales
 *
 */
@Component
public class ControlBuscarProducto {
	
	@Autowired 
	private ServicioSeccionCatalogo servicioSeccionCatalogo; 
	
	@Autowired 
	private ServicioProducto servicioProducto;
	
	@Autowired 
	private VentanaBuscarProducto ventana;
	
	private String fecha;
	
	/**
	 * 
	 * Permite dar inicio al módulo ControlBuscarProducto y a sus respectivos métodos de dicho módulo  
	 * 
	 */
	
	public void inicia() {
		
		List <SeccionCatalogo> secciones= servicioSeccionCatalogo.consultarSeccionesDisponibles(); 
		
		// Le dice a servicios que obtenga la fecha actual del sistema, la devuelve y lo coloca en la caja de textos fecha de la VistaBuscarProducto
		fecha = servicioProducto.obtenerFechaActual();
		
		ventana.muestra(this, secciones,fecha);
	}
	
	
	/**
	 * 
	 * Manda a llamar al método agregarProducto() del módulo ServicioProducto pasandole los mismos parámetros recibidos 
	 * 
	 * @param nombre
	 * @param marca
	 * @param descripcion
	 * @param precio
	 * @param descuento
	 * @param existencia
	 * @param seccion
	 * @return Dialogo con mensaje 
	 */
	public void buscarProducto(String seccion,String idProducto, String nombre, String marca,String precioMaximo, String precioMinimo) {
		
		
		try {

			List <Producto> productos =servicioProducto.buscarProducto(seccion,idProducto, nombre, marca, precioMaximo, precioMinimo); 
			
			if(productos.size()>0)
				ventana.muestraProductosObtenidos(this, productos);
			else
				ventana.muestraDialogoConMensaje("Por el momento no contamos con productos con las caracteristicas proporcionadas por el usuario.");


		} catch (Exception ex) {
			ventana.muestraDialogoConMensaje("Error al buscar producto: " + ex.getMessage());
		}
		
	}
	

	
	/**
	 * Termina la historia de usuario
	 * 
	 */
	public void termina() {
		ventana.setVisible(false);		
	}

}
