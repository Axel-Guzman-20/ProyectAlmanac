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
public class Pruebas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPrueba;

	private String nombre;
}
