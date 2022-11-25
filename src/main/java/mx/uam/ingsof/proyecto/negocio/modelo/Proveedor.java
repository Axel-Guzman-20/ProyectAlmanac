package mx.uam.ingsof.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author MiguelGuzman
 * @author AbigailMorales
 * @author AxelGuzman
 * @author EduardoCastro
 *
 */

@Entity
@Data
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProveedor;
	
	private String fechaRegistro;
	
	private String nombreProveedor;
	
	private String nombreContacto;
	
	private String cargoContacto;
	
	private String direccionProveedor;
	
	private String correoElectronicoContacto;
	
	private String telefonoContacto;
	
	private String sobreLaEmpresa;	

}
