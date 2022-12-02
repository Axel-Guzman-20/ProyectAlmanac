package mx.uam.ingsof.proyecto.negocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio relacionado con las reparaciones y/o mantenimientos de los equipos
 * 
 * @author abigailmorales 
 */

@Slf4j
@Service
public class ServicioReparacionMantenimiento {
	
	/**
	 * 
	 * Permite recuperar la fecha actual del dispositivo
	 * 
	 * @return fecha 
	 */
	public String obtenerFechaActual() {
		
		String fecha;
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 
        fecha = dateFormat.format(new Date());
		
		return fecha;
	}
}
