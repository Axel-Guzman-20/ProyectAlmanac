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
public class DiagnosticoPruebas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDiagnosticoPruebas;
	
	private String fecha;
	
	private String nombreEmpleado;
	
	private String nombreEquipo; 
	
	private String observacionesAdicionales;
	
	@OneToMany(targetEntity = Pruebas.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name= "idDiagnosticoPruebas")
	private final List <Pruebas> listaPruebas = new ArrayList <> ();
	
	public boolean addPruebas(List<Pruebas> pruebas) {
		
		if(pruebas == null || pruebas.size()==0) {
			throw new IllegalArgumentException("La lista de pruebas no puede ser null y/o vacia");
		}
		
		for(Pruebas prueba: pruebas) {
			listaPruebas.add(prueba); 
		}
		
		if(listaPruebas.size() == pruebas.size()) {
			return true; 
		}else {
			return false; 
		}
	}
	
}
