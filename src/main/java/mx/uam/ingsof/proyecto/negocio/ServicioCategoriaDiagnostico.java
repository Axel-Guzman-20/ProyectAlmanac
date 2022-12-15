package mx.uam.ingsof.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ingsof.proyecto.datos.CategoriaDiagnosticoRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.CategoriaDiagnostico;
import mx.uam.ingsof.proyecto.negocio.modelo.ReparacionMantenimiento;

/**
 * Servicio relacionado con las categorias para los Diagnosticos
 * 
 * @author abigailmorales 
 *
 */
@Service
public class ServicioCategoriaDiagnostico {
	
	@Autowired
	CategoriaDiagnosticoRepository categoriaDiagnosticoRepository; 
	
	/**
	 * 
	 * Recupera todas las categorias para los Diagnosticos disponibles 
	 *  
	 * @param 
	 * @return una lista con las categorias para los Diagnosticos (o una lista vacia)
	 */
	public List <CategoriaDiagnostico> consultarCategoriasDisponibles(){
		
		List <CategoriaDiagnostico> categorias = new ArrayList<>();
		
		for(CategoriaDiagnostico categoria:categoriaDiagnosticoRepository.findAll()) {
			categorias.add(categoria);
		}
				
		return categorias;
	}
	
	public List<ReparacionMantenimiento> buscarDiagnosticos(String categoria){
		
		//CategoriaDiagnostico categoria = categoriaDiagnosticoRepository.findByNombre(nombreCategoria);
		
//		List <ReparacionMantenimiento> diagnosticos  = new ArrayList<>();
//		
//		for(ReparacionMantenimiento diagnostico:categoriaDiagnosticoRepository.findByNombre(categoria).getDiagnosticos()) {
//			diagnosticos.add(diagnostico);
//		}
		//System.out.println(categoria.getDiagnosticos().toString());
		
		return categoriaDiagnosticoRepository.findByNombre(categoria).getDiagnosticos(); 
	}
}
