package mx.uam.ingsof.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uam.ingsof.proyecto.datos.SeccionCatalogoRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.SeccionCatalogo;

/**
 * Servicio relacionado con las secciones del catalogo
 * 
 * @author abigailmorales 
 *
 */

@Service
public class ServicioSeccionCatalogo {
	
	
	@Autowired 
	SeccionCatalogoRepository seccionCatalogoRepository;
	
	/**
	 * 
	 * Recupera todas las secciones del catalogo disponibles 
	 *  
	 * @param 
	 * @return una lista con las secciones del catalogo (o una lista vacia)
	 */
	public List <SeccionCatalogo> consultarSeccionesDisponibles() {
		
		List <SeccionCatalogo> secciones = new ArrayList<>();
		
		for(SeccionCatalogo seccion:seccionCatalogoRepository.findAll()) {
			secciones.add(seccion);
		}
				
		return secciones;
	}

}
