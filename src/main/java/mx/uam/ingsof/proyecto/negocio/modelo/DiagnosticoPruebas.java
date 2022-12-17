package mx.uam.ingsof.proyecto.negocio.modelo;

import java.util.ArrayList;
import java.util.List;

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
public class DiagnosticoPruebas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDiagnosticoPruebas;
	
	private String fecha;
	
	private String nombreEmpleado;
	
	private String nombreEquipo;
	
	private ArrayList <String> listaPruebas = new ArrayList <>(); 
	
	private String observacionesAdicionales;
	
}
