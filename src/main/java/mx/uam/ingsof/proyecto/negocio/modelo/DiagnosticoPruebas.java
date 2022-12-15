package mx.uam.ingsof.proyecto.negocio.modelo;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
public class DiagnosticoPruebas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDiagnosticoPruebas;
	
	private String fecha;
	
	private String nombreEmpleado;
	
	private String nombreEquipo;
	
	private ArrayList <String> listaPruebas = new ArrayList <>(); 
	
	private String observacionesAdicionales;
	
//	@OneToOne(targetEntity = DiagnosticoPruebas.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name= "idReparacionMantenimiento")
//	private final DiagnosticoPruebas diagnosticoPruebas;
}
