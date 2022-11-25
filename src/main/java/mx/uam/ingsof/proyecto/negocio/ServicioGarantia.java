package mx.uam.ingsof.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ingsof.proyecto.datos.CompraRepository;
import mx.uam.ingsof.proyecto.datos.GarantiaRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Compra;
import mx.uam.ingsof.proyecto.negocio.modelo.Garantia;


/**
 * 
 * Servicio relacionado con las garantias
 * 
 * @author eduardoCastro
 */


@Service
public class ServicioGarantia {
	
	@Autowired
	private GarantiaRepository garantiaRepository;
	@Autowired
	private CompraRepository compraRepository;

	/**
	 * 
	 * Permite crear una garantia a partir de los parmetros 
	 * 
	 * 
	 * @return lista de productos (o una lista vacia)
	 */
	public Garantia creaGarantia(Compra compra,String nombreCompleto,String fecha, String calle, String numExt, String numInt, String descipEquipo){
			
		if(compra == null)
			throw new IllegalArgumentException("Compra no debe ser null");
		if(nombreCompleto == null)
			throw new IllegalArgumentException("Nombre no debe ser null");
		if(fecha == null)
			throw new IllegalArgumentException("fecha no debe ser null");
		if(calle == null)
			throw new IllegalArgumentException("Calle no debe ser null");
		if(numExt == null)
			throw new IllegalArgumentException("Num. Ext. no debe ser null");
		if(numInt == null)
			throw new IllegalArgumentException("Num. Int. no debe ser null");
		if(descipEquipo == null)
			throw new IllegalArgumentException("Descripcion de equipo no debe ser null");
		Garantia garantia = new Garantia();
		garantia.setCompra(compra);
		garantia.setNombreCompleto(nombreCompleto);
		garantia.setFecha(fecha);
		garantia.setCalle(calle);
		garantia.setNumExt(numExt);
		garantia.setNumInt(numInt);
		garantia.setDescripcionEquipo(descipEquipo);
		garantia = garantiaRepository.save(garantia);
		compra.setGarantia(garantia);
		compraRepository.save(compra);
		return garantia;
		

		
	}
}
