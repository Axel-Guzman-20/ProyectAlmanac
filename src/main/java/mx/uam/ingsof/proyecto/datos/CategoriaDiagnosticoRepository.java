package mx.uam.ingsof.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ingsof.proyecto.negocio.modelo.CategoriaDiagnostico;

/**
 * Repositorio para CategoriaDiagnostico
 * 
 * @author Abigail Morales Mariscal
 *
 */

public interface CategoriaDiagnosticoRepository extends CrudRepository<CategoriaDiagnostico, Long> {

}
