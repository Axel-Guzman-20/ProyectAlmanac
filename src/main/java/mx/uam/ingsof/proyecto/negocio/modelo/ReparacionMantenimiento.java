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
public class ReparacionMantenimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idReparacionMantenimiento;
	
	private String fecha;
	
	private String nombreEmpleado;
	
	private String nombreEquipo; 
	
	private String marca; 
	
	private String descripcionEquipo; 
	
	private String descripcionReparacionMantenimiento;
	
	private String tipoReparacionMantenimiento;
	
	private String piezasRequeridas; 
	
	private String observacionesAdicionales; 
}
