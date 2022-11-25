package mx.uam.ingsof.proyecto.bean;

import lombok.Data;

@Data
public class BeanVenta {
	
		
		private long idProducto;
		private String nombreProducto;
		private int cantidad;
		private double precio;
		private double precioTotal;
			
	
}
