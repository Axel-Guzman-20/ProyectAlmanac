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
public class SeccionCatalogo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSeccionCatalogo;

	private String nombre;
	
	@OneToMany(targetEntity = Producto.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name= "idSeccionCatalogo")
	private final List <Producto> productos = new ArrayList <> ();
	
	
	/**
	 * 
	 * Permite agregar un producto a la seccion del catalogo
	 * Nota: un mismo producto no puede registrar dos veces (Regla del Negocio 02)
	 * 
	 * @param producto el producto que deseo agregar a la seccion
	 * @return true si el producto se agregó correctamente, false si ya estaba en la SeccionCatalogo
	 * @throws IllegalArgumentException si el producto es nulo
	 */
	public boolean addProducto(Producto producto) {
		
		if(producto == null) {
			throw new IllegalArgumentException("El producto no puede ser null");
		}
		
		if(productos.contains(producto)) {
			// Checo si el producto está en la seccion del catalogo por que no se puede agregar un producto dos veces
			return false;
		}
		
		return productos.add(producto); 
	}
	
	
}
