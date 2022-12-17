package mx.uam.ingsof.proyecto.negocio.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	@OneToMany(targetEntity = DiagnosticoPruebas.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name= "idReparacionMantenimiento")
	private final List <DiagnosticoPruebas> diagnosticoPruebas = new ArrayList <> ();
	
	/**
	 * 
	 * Permite agregar un diagnostico a una categoria disponible
	 * Nota: un mismo diagnostico no se puede registrar dos veces (Regla del Negocio )
	 * 
	 * @param diagnostico, el diagnostico que desea agregar a la categoria
	 * @return true si el diagnostico se agregó correctamente, false si ya estaba en la CategoriaDiagnostico
	 * @throws IllegalArgumentException si el diagnostico es nulo
	 */
	public boolean addPruebas(DiagnosticoPruebas pruebas) {
		
		if(pruebas == null) {
			throw new IllegalArgumentException("El diagnostico no puede ser null");
		}
		
		if(diagnosticoPruebas.contains(pruebas)) {
			// Checo si el prueba está en la categoria por que no se puede agregar una prueba dos veces
			return false;
		}
		
		return diagnosticoPruebas.add(pruebas); 
	}
}
