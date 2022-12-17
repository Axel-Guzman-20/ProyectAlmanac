package mx.uam.ingsof.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ingsof.proyecto.negocio.modelo.DiagnosticoPruebas;


public interface DiagnosticoPruebasRepository extends CrudRepository<DiagnosticoPruebas, Long> {
	
	public DiagnosticoPruebas findByNombreEquipo(String nombre);
}
