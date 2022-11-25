package mx.uam.ingsof.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class VentaProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVentaProducto;
	private int cantidad;
	@OneToOne
	private Producto producto;
	
}
