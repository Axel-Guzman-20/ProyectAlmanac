package mx.uam.ingsof.proyecto.presentacion.agregarProducto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ingsof.proyecto.negocio.ServicioProducto;
import mx.uam.ingsof.proyecto.negocio.ServicioSeccionCatalogo;
import mx.uam.ingsof.proyecto.negocio.modelo.SeccionCatalogo;

/**
 * 
 * Módulo de control para la historia de usuario AgregarProducto
 * 
 * @author abigailmorales
 *
 */
@Component
public class ControlAgregarProducto {
	
	@Autowired 
	private ServicioSeccionCatalogo servicioSeccionCatalogo; 
	
	@Autowired 
	private ServicioProducto servicioProducto; 
	
	@Autowired 
	private VistaAgregarProducto ventana; 
	
	
	/**
	 * 
	 * Permite dar inicio al módulo ControlAgregarProducto y a sus respectivos métodos de dicho módulo  
	 * 
	 */
	
	public void inicia() {
		
		List <SeccionCatalogo> secciones= servicioSeccionCatalogo.consultarSeccionesDisponibles(); 
		ventana.muestra(this, secciones);
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
	public void agregaProducto(String nombre, String marca, String descripcion,String precio, String descuento, String existencia, String seccion) {
		
		
		try {

			if (servicioProducto.agregaProducto(nombre, marca, descripcion, precio, descuento, existencia,
					seccion) != false) {

				ventana.muestraDialogoConMensaje("Producto agregado exitosamente");
				termina();
			} else {

				ventana.muestraDialogoConMensaje("Se ha llegado al limite maximo para registrar productos en la seccion " + seccion);
				termina();
			}


		} catch (Exception ex) {
			ventana.muestraDialogoConMensaje("Error al agregar producto: " + ex.getMessage());
		}

		termina();
		
	}
	

	
	/**
	 * Termina la historia de usuario
	 * 
	 */
	public void termina() {
		ventana.setVisible(false);		
	}
}
