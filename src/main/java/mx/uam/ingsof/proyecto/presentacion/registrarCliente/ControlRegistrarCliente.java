package mx.uam.ingsof.proyecto.presentacion.registrarCliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.Data;
import mx.uam.ingsof.proyecto.negocio.ServicioCliente;


@Component
@Data
public class ControlRegistrarCliente {

	@Autowired
	private VistaRegistrarCliente ventanaRegistrarCliente;
	
	@Autowired
	private ServicioCliente servicioCliente;
	
	private String fecha;
	private String nombreCompleto;
	private String genero;
	private String direccion;
	private String telefono;
	private String correoelectronico;
	
	
	/**
	 * 
	 * Permite dar inicio al módulo ControlRegistrarCliente y a sus respectivos métodos de dicho módulo   
	 * 
	 */
	
	public void inicia() {
		
			String fecha;
			
			// Le dice a servicios que obtenga la fecha actual del sistema, la devuelve y lo coloca en la caja de textos fecha de la VistaRegistrarCliente
			fecha = servicioCliente.obtenerFechaActual();
			
			ventanaRegistrarCliente.muestra(this, fecha);
			
	}
	
	
	/**
	 * 
	 * Permite registrar a un cliente   
	 * 
	 */
	public void recolectaDatos() {
		
		// Recupera la info de las cajas de texto de la vista
		fecha = ventanaRegistrarCliente.fechaText.getText();
		nombreCompleto = ventanaRegistrarCliente.nombreCompletoText.getText();
		genero = ventanaRegistrarCliente.generoText.getText();
		direccion = ventanaRegistrarCliente.direccionText.getText();
		telefono = ventanaRegistrarCliente.telefonoText.getText();
		correoelectronico = ventanaRegistrarCliente.correoelectronicoText.getText();
				
		boolean correo;
		
		// Le dice a servicios, que valide que sí sea un correo válido
				correo = servicioCliente.correoValido(correoelectronico);
		
		// Si es true, significa que el correo cumple con la sintaxis
		if(correo == true) {
			
		
		// Le dice a servicios, que busque en repository, si el correo existe
		correo = servicioCliente.verificarCorreoElectronico(correoelectronico);
		
		// Si es false, significa que el correo NO ha sido registrado y continua con el registro (criterio de aceptacion)
		if(correo == false) {
			boolean telefonos;
			
			// Le dice a servicios, que verifique si es un numero telefonico
			telefonos = servicioCliente.verificarTelefono(telefono);
			
			// Si es true, significa que es numero telefonico valido y continua con el registro
			if(telefonos == true) {
				boolean concluido;
				
				concluido = servicioCliente.registrarCliente(fecha, nombreCompleto, genero, direccion, telefono, correoelectronico);
			 
				if(concluido == true) {
					ventanaRegistrarCliente.muestraDialogoConMensaje("¡Cliente registrado con éxito!");
					cierraVentana();
				}else
					ventanaRegistrarCliente.muestraDialogoConMensaje("No se ha podido registrar al cliente.");
				
			}else
				ventanaRegistrarCliente.muestraDialogoConMensaje("Ingrese un número de 10 digítos y no use separadores ni otro simbolo que no sea númerico.");
		}else 
			ventanaRegistrarCliente.muestraDialogoConMensaje("El correo ingresado ya está registrado.");
		}else
			ventanaRegistrarCliente.muestraDialogoConMensaje("Ingresa un correo válido.");
		
	}
	
	
	/**
	 * 
	 * Permite terminar la historia de usuario   
	 * 
	 */
	
	public void cierraVentana() {
	
		ventanaRegistrarCliente.termina();
	
	}

	
}






