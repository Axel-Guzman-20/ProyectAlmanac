package mx.uam.ingsof.proyecto.presentacion.modificarCliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.ServicioCliente;

import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;

@Component
public class ControlModificarCliente {

	@Autowired
	private ServicioCliente servicioCliente;

	@Autowired
	private VistaModificarCliente ventana;

	/**
	 * 
	 * Permite dar inicio al módulo ControlModificarCliente y a sus respectivos
	 * métodos de dicho módulo
	 * 
	 */
	public void inicia() {
		String fecha;
		fecha = servicioCliente.obtenerFechaActual();

		List<Cliente> clientes = servicioCliente.consultarClientesDisponibles();
		ventana.muestra(fecha, this, clientes);

	}

	/**
	 * 
	 * Manda a llamar al método obtenerCliente() del módulo ServicioCliente
	 * pasandole los mismos parámetros recibidos
	 * 
	 * @param cliente

	 * @return Dialogo con mensaje
	 */
	

	
	
	public void modificarCliente(String ClienteSeleccionado, String nombreCompleto, String genero, String direccion,
			String telefono, String correoelectronico,  String correoelectronico2) {
		
		boolean correo;
		boolean correo2;
		// Le dice a servicios, que valide que sí sea un correo válido
		correo = servicioCliente.correoValido(correoelectronico);

		// Si es true, significa que el correo cumple con la sintaxis
		if(correo == true) {
			
			// Le dice a servicios, que busque en repository, si el correo existe
			correo2 = servicioCliente.verificarCorreoElectronico(correoelectronico);
			
			
			// Si es false, significa que el correo NO ha sido registrado y continua con el registro (criterio de aceptacion)
			if(correo2 == false) {
				boolean telefonos;
				
				// Le dice a servicios, que verifique si es un numero telefonico
				telefonos = servicioCliente.verificarTelefono(telefono);
				
				// Si es true, significa que es numero telefonico valido y continua con el registro
				if(telefonos == true) {
					
					try {
			
						servicioCliente.modificarCliente(ClienteSeleccionado, nombreCompleto, genero, direccion, telefono,
								correoelectronico);

						ventana.muestraDialogoConMensaje("El Cliente " + nombreCompleto + " ha sido modificado exitosamente");
						cierraVentana();

					} catch (Exception ex) {
						ventana.muestraDialogoConMensaje("Error al modificar los datos del cliente: " + ex.getMessage());
						}
		
				
				}else 					
					ventana.muestraDialogoConMensaje("Ingrese un número de 10 digítos y no use separadores ni otro simbolo que no sea númerico.");
			
				}else {
					correo2 = servicioCliente.comparacorreos(correoelectronico,correoelectronico2);
					if(correo2 == true)
					{
						boolean telefonos;
						
						// Le dice a servicios, que verifique si es un numero telefonico
						telefonos = servicioCliente.verificarTelefono(telefono);
						
						// Si es true, significa que es numero telefonico valido y continua con el registro
						if(telefonos == true) {
							
						
						try {
						
							servicioCliente.modificarCliente(ClienteSeleccionado, nombreCompleto, genero, direccion, telefono,
								correoelectronico);

							ventana.muestraDialogoConMensaje("El Cliente " + nombreCompleto + " ha sido modificado exitosamente");
							cierraVentana();

						} catch (Exception ex) {
							ventana.muestraDialogoConMensaje("Error al modificar los datos del cliente: " + ex.getMessage());
						}
						}
						else
							ventana.muestraDialogoConMensaje("Ingrese un número de 10 digítos y no use separadores ni otro simbolo que no sea númerico.");					
					}
					else
					ventana.muestraDialogoConMensaje("El correo ingresado ya está registrado.");
			}
				
		}else
			ventana.muestraDialogoConMensaje("Ingresa un correo válido.");
		
	}


	/**
	 * 
	 * Manda a llamar al método obtenerCliente() del módulo ServicioProducto
	 * pasandole los mismos parámetros recibidos
	 * 
	 * @param nombre
	 * @return cliente
	 */

	public Cliente obtenerCliente(String nombre) {
		Cliente cliente = servicioCliente.obtenerCliente(nombre);
		return cliente;
	}

	/**
	 * 
	 * Permite terminar la historia de usuario
	 * 
	 */

	public void cierraVentana() {

		ventana.termina();

	}

}
