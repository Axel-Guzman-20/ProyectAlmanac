package mx.uam.ingsof.proyecto.presentacion.crearGarantia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ingsof.proyecto.negocio.ServicioGarantia;
import mx.uam.ingsof.proyecto.negocio.ServicioVenta;
import mx.uam.ingsof.proyecto.negocio.modelo.Venta;



/**
 * 
 * Módulo de control para la historia de usuario CrearGarantia
 * 
 * @author Eduardo Castro
 *
 */
@Component
public class ControlCrearGarantia {
	
	@Autowired
	private VistaCrearGrantia ventanaCrearGarantia;
	
	@Autowired
	private ServicioVenta servicioVenta;
	
	@Autowired
	private ServicioGarantia servicioGarantia;

	Venta venta;
	
	/**
	 * Inicia la historia de usuario
	 * 
	 */
	public void inicia() {
		
		ventanaCrearGarantia.muestra(this);
	}
	
	public boolean validaCompra(long idCompra) {
		
	   venta = servicioVenta.obtenCompra(idCompra);
		if(venta == null)
			return false;
		else
			return true;
	}
	
	public void creaGarantia(long idCompra,String nombreCompleto,String fecha, String calle, String numExt, String numInt, String descipEquipo) {
		
		try {
			 servicioGarantia.creaGarantia(venta, nombreCompleto, fecha, calle, numExt, numInt, descipEquipo);
			 ventanaCrearGarantia.muestraDialogoConMensaje("Garantia creada correctamente");
		} catch (Exception e) {
			// TODO: handle exception
			ventanaCrearGarantia.muestraDialogoConMensaje("La garantia no pudo ser creada "+ e.getMessage());
		}
		termina();
	}
	
	/**
	 * Termina la historia de usuario
	 * 
	 */
	public void termina() {
		ventanaCrearGarantia.setVisible(false);
		
	}

}
