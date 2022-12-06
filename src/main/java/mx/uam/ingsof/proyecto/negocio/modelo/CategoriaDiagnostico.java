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
public class CategoriaDiagnostico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoriaDiagnostico;

	private String nombre;
	
	@OneToMany(targetEntity = ReparacionMantenimiento.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name= "idCategoriaDiagnostico")
	private final List <ReparacionMantenimiento> diagnosticos = new ArrayList <> ();
	
	/**
	 * 
	 * Permite agregar un diagnostico a una categoria disponible
	 * Nota: un mismo diagnostico no se puede registrar dos veces (Regla del Negocio )
	 * 
	 * @param diagnostico, el diagnostico que desea agregar a la categoria
	 * @return true si el diagnostico se agregó correctamente, false si ya estaba en la CategoriaDiagnostico
	 * @throws IllegalArgumentException si el diagnostico es nulo
	 */
	public boolean addDiagnostico(ReparacionMantenimiento diagnostico) {
		
		if(diagnostico == null) {
			throw new IllegalArgumentException("El diagnostico no puede ser null");
		}
		
		if(diagnosticos.contains(diagnostico)) {
			// Checo si el diagnostico está en la categoria por que no se puede agregar un diagnostico dos veces
			return false;
		}
		
		return diagnosticos.add(diagnostico); 
	}
}
