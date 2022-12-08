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
import javax.persistence.OneToOne;
import lombok.Data;



/**
 * Entidad de negocio Compra
 * 
 * @author abigailmorales
 * @author erikamaya
 * @author eduardocastro
 *
 */

@Entity
@Data
public class Venta {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVenta;
	
	private long idCliente;
	
	private long idEmpleado;
	
	private String fechaVenta;

	@OneToMany(targetEntity = VentaProducto.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)  
	@JoinColumn(name= "idVenta")
	private  List<VentaProducto> listaProducto = new ArrayList<>(); 
	
	@OneToOne
	private Garantia garantia;
	
    public VentaProducto agregaProducto(Producto producto,int cantidad) {
		
		if(producto == null)
			throw new IllegalArgumentException("El producto no puede ser null");
		if(cantidad <= 0 )
			throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
		VentaProducto ventaProducto = new VentaProducto();
		ventaProducto.setProducto(producto);
		ventaProducto.setCantidad(cantidad);
		return ventaProducto;
	}

}
