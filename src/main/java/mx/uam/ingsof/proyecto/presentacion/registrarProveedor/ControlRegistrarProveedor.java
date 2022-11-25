package mx.uam.ingsof.proyecto.presentacion.registrarProveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.Data;
import mx.uam.ingsof.proyecto.negocio.ServicioProveedor;


@Component
@Data
public class ControlRegistrarProveedor {

	@Autowired
	private VistaRegistrarProveedor ventanaRegistrarProveedor;
	
	@Autowired
	private ServicioProveedor servicioProveedor;
	
	private String fecha;
	private String nombreEmpresa;
	private String nombreContacto;
	private String cargoContacto;
	private String direccionEmpresa;
	private String correoEleContacto;
	private String telefonoContacto;
	private String sobreLaEmpresa;
	
	
	/**
	 * 
	 * Permite dar inicio al módulo ControlRegistrarProveedor y a sus respectivos métodos de dicho módulo   
	 * 
	 */
	
	public void inicia() {
		
		boolean proveedoresRegistrados;
		
		// Le dice a servicios que busque en la BD la cantidad de proveedores registrados
		proveedoresRegistrados = servicioProveedor.proveedoresRegistrados();
		
		// El metodo de proveedoresRegistrados devuelve true si es menor a 100 (criterio de aceptacion)
		if(proveedoresRegistrados == true) {
			String fecha;
			
			// Le dice a servicios que obtenga la fecha actual del sistema, la devuelve y lo coloca en la caja de textos fecha de la VistaRegistrarProveedor
			fecha = servicioProveedor.obtenerFechaActual();
			
			ventanaRegistrarProveedor.muestra(this, fecha);
			
		}else
			ventanaRegistrarProveedor.muestraDialogoConMensaje("Ya se alcanzó el máximo registro de proveedores (100).");
	}
	
	
	/**
	 * 
	 * Permite registrar a un proveedor   
	 * 
	 */
	public void recolectaDatos() {
		
		// Recupera la info de las cajas de texto de la vista
		fecha = ventanaRegistrarProveedor.fechaText.getText();
		nombreEmpresa = ventanaRegistrarProveedor.nombreEmpresaText.getText();
		nombreContacto = ventanaRegistrarProveedor.nombreContactoText.getText();
		cargoContacto = ventanaRegistrarProveedor.cargoContactoText.getText();
		direccionEmpresa = ventanaRegistrarProveedor.direccionEmpresaText.getText();
		correoEleContacto = ventanaRegistrarProveedor.correoEleContactoText.getText();
		telefonoContacto = ventanaRegistrarProveedor.telefonoContactoText.getText();
		sobreLaEmpresa = ventanaRegistrarProveedor.sobreLaEmpresaText.getText();
				
		boolean correo;
		
		// Le dice a servicios, que valide que sí sea un correo válido
		correo = servicioProveedor.correoValido(correoEleContacto);
		
		// Si es true, significa que el correo cumple con la sintaxis
		if(correo == true) {
			
			// Verifica que el correo no exista en la base de datos
			correo = servicioProveedor.verificarCorreoE(correoEleContacto);
			
			
			//Si es false, significa que no existe en la BD
			if(correo == false) {
				
				boolean telefono;
				
				// Le dice a servicios, que verifique si es un numero telefonico
				telefono = servicioProveedor.verificarTelefono(telefonoContacto);
				
				// Si es true, significa que es numero telefonico valido y continua con el registro
				if(telefono == true) {
					boolean concluido;
					
					concluido = servicioProveedor.registrarProveedor(fecha, nombreEmpresa, nombreContacto, cargoContacto, direccionEmpresa, correoEleContacto, telefonoContacto, sobreLaEmpresa);
				
					if(concluido == true) {
						ventanaRegistrarProveedor.muestraDialogoConMensaje("El proveedor ha sido registrado correctamente.");
						cierraVentana();
					}
					
					
					
					else
						ventanaRegistrarProveedor.muestraDialogoConMensaje("No se ha podido registrar al proveedor.");
					
				}else
					ventanaRegistrarProveedor.muestraDialogoConMensaje("Ingrese un número de 10 digítos y no use separadores ni otro simbolo que no sea númerico.");
			}else
				ventanaRegistrarProveedor.muestraDialogoConMensaje("El correo ya está registrado.");
		}else 
			ventanaRegistrarProveedor.muestraDialogoConMensaje("Ingresa un correo válido.");
		
	}
	
	
	
	/**
	 * 
	 * Permite terminar la historia de usuario   
	 * 
	 */
	
	public void cierraVentana() {
	
		ventanaRegistrarProveedor.termina();
	
	}

	
}
