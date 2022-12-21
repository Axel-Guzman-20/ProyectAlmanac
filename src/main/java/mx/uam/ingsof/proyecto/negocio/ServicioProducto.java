package mx.uam.ingsof.proyecto.negocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ingsof.proyecto.negocio.ServicioProducto;
import mx.uam.ingsof.proyecto.datos.ProductoRepository;
import mx.uam.ingsof.proyecto.datos.SeccionCatalogoRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.SeccionCatalogo;

/**
 * Servicio relacionado con los productos
 * 
 * @author abigailmorales 
 * @author eduardoCastro
 */

@Slf4j
@Service
public class ServicioProducto {
	
	@Autowired 
	private ProductoRepository productoRepository; 
	
	@Autowired
	private SeccionCatalogoRepository seccionCatalogoRepository; 
	
	
	
	/**
	 * 
	 * Permite agregar un producto a la seccion catalogo que se desea 
	 * Nota: un mismo producto no puede registrar dos veces (Regla del Negocio 02)
	 * 
	 * @param nombre
	 * @param marca
	 * @param descripcion
	 * @param precio
	 * @param descuento
	 * @param existencia
	 * @param seccion
	 * @return true si el producto se agregó correctamente, false si ya estaba en la SeccionCatalogo
	 * @throws IllegalArgumentException si el producto ya existe 
	 * @throws IllegalArgumentException si no se encontro la seccion del catalogo a la que se quiere agregar el producto
	 */
	
	public boolean agregaProducto(String nombre, String marca, String descripcion, String precio, String descuento,
			String existencia, String seccion) {
		
		
		//Se verifica que no se sobrepase el maximo de productos 
		if (seccionCatalogoRepository.findByNombre(seccion).getProductos().size()<2) {
			
			// Regla de negocio: No se permite agregar dos productos con el mismo nombre
			Producto producto = productoRepository.findByNombre(nombre);
			
			if (producto != null) {
				throw new IllegalArgumentException("El producto ya existe");
			}

			SeccionCatalogo seccionCatalogo = seccionCatalogoRepository.findByNombre(seccion);

			if (seccionCatalogo == null) {
				throw new IllegalArgumentException("No se encontró la sección del catalogo");
			}

			log.info("Agregando producto con nombre: " + nombre + ", marca:" + marca + ", descripcion:" + descripcion
					+ ", precio:" + precio + ", descuento:" + descuento + ", existencia:" + existencia + ", seccion:"
					+ seccion);

			producto = new Producto();

			producto.setNombre(nombre);
			producto.setMarca(marca);
			producto.setDescripcion(descripcion);
			producto.setPrecio(Double.parseDouble(precio));
			producto.setDescuento(Double.parseDouble(descuento));
			producto.setExistencia(Integer.parseInt(existencia));

			productoRepository.save(producto);
			seccionCatalogo.addProducto(producto);

			seccionCatalogoRepository.save(seccionCatalogo);
			

			return true;
		} else {
			return false;
		}

	}
	
	
	/**
	 * 
	 * Recupera un listado de todas los productos disponibles en la base de datos 
	 * 
	 * @param 
	 * @return Una lista con todos los productos disponibles en la base de datos 
	 */
	
	public List <Producto> consultarProductosDisponibles() {
		
		List <Producto> productos = new ArrayList<>();
		
		for(Producto producto:productoRepository.findAll()) {
			productos.add(producto);
		}
				
		return productos;
	}
	
	/**
	 * 
	 * Recupera un producto de acuerdo a su nombre 
	 * 
	 * @param nombre
	 * @return un objeto de tipo Producto si este se encontro en la base de datos 
	 * @throws IllegalArgumentException si el producto no existe
	 */
	
	public Producto obtenerProducto(String nombre) {
		
		Producto producto = productoRepository.findByNombre(nombre);
		
		if (producto == null) {
			
			throw new IllegalArgumentException("El producto no existe");
		}else
			return producto; 
	}
	
	/**
	 * 
	 * Permite obtener una seccion del catalogo de acuerdo a un producto de este 
	 * 
	 * @param producto
	 * @return un objeto de tipo SeccionCatalogo si este se encontro en la base de datos 
	 * @throws IllegalArgumentException si el producto no existe en la seccion
	 */
	
	public SeccionCatalogo obtenerSeccionCatalogoDelProducto(Producto producto) {
		SeccionCatalogo seccionCatalogo=seccionCatalogoRepository.findByProductos(producto); 
		if (seccionCatalogo == null) {
			throw new IllegalArgumentException("El producto no existe en esta seccion");
		}else
			return seccionCatalogo;
	}
	
	/**
	 * 
	 * Permite modificar un producto existente en la base de datos
	 * 
	 * @param nombreProductoAModificar
	 * @param nombre
	 * @param marca
	 * @param descripcion
	 * @param precio
	 * @param descuento
	 * @param existencia
	 * @return producto si se modifico correctamente el producto
	 * @throws IllegalArgumentException si el producto no existe 
	 */
	
	public Producto modificaProducto(String nombreProductoAModificar, String nombre, String marca, String descripcion, String precio, String descuento,String existencia) {

			
			Producto producto = productoRepository.findByNombre(nombreProductoAModificar);

			if (producto == null) {
				throw new IllegalArgumentException("El producto no existe");
			}

			log.info("Modificando el producto con nombre: " + nombre + ", marca:" + marca + ", descripcion:" + descripcion
					+ ", precio:" + precio + ", descuento:" + descuento + ", existencia:" + existencia);
			
			if(!nombre.equals("")) {
				producto.setNombre(nombre);
			}
			if(!marca.equals("")) {
				producto.setMarca(marca);
			}
			if(!descripcion.equals("")) {
				producto.setDescripcion(descripcion);
			}
			if(!precio.equals("")) {
				producto.setPrecio(Double.parseDouble(precio));
			}
			if(!descuento.equals("")) {
				producto.setDescuento(Double.parseDouble(descuento));
			}
			if(!existencia.equals("")) {
				producto.setExistencia(Integer.parseInt(existencia));
			}
			
			producto=productoRepository.save(producto);
			
			return producto;

	}
	/**
	 * 
	 * Permite agregar recuperar la lista de usuarios
	 * 
	 * 
	 * @return lista de productos (o una lista vacia)
	 */
	
	public List<Producto> recuperaListaProductos(){
		
		List <Producto> listaProductos = new ArrayList <>();
		
		 for(Producto producto: productoRepository.findAll()) {
			 listaProductos.add(producto);
		 }
		 
		return listaProductos;
	}
	
	/**
	 * 
	 * Permite buscar productos de la seccion catalogo que se desea, permitiendo 
	 * utilizar el filtro que se desee para la busqueda 
	 * 
	 * @param seccion
	 * @param idProducto
	 * @param nombre
	 * @param marca
	 * @param precioMaximo
	 * @param precioMinimo
	 * @return List<Producto>, es decir una lista con los productos encontrados en la busqueda
	 *         o una lista vacia en caso de no encontrar productos en la busqueda
	 */
	
	public List<Producto> buscarProducto(String seccion,String idProducto, String nombre, String marca,String precioMaximo, String precioMinimo) {
		
		if ((idProducto==null) || (nombre==null) || (marca==null) || (precioMaximo==null) || (precioMinimo==null)) {
			throw new NullPointerException(null);
		}
		
		if(idProducto.equals("") && nombre.equals("") && marca.equals("") && precioMaximo.equals("") && precioMinimo.equals("") )
		{
			List <Producto> listaProductos = new ArrayList <>();
			List <Producto> listaProductos2 = seccionCatalogoRepository.findByNombre(seccion).getProductos();
			
			 for(Producto producto:listaProductos2 ) {
				 listaProductos.add(producto);
			 }
			 
			 return listaProductos;
		}else {
			
			List <Producto> listaProductos = new ArrayList <>();

			 for(Producto producto: seccionCatalogoRepository.findByNombre(seccion).getProductos()) {
				 
				 listaProductos.add(producto);
			 }	 
			 
			 if(!precioMaximo.equals("") || !precioMinimo.equals("")) {	
				 
				try {
					 listaProductos=validaRangoPrecio(precioMaximo, precioMinimo, listaProductos); 	 
				} catch (Exception ex) {
					throw new IllegalArgumentException("Por el momento no contamos con productos con las caracteristicas proporcionadas por el usuario.");
				}
			 }
			 
			 if(!idProducto.equals("")) {
				 listaProductos=validaID(idProducto, listaProductos); 
			 }
			 
			 if(!nombre.equals("")) {
				 listaProductos=validaNombre(nombre, listaProductos); 
			 }
			 
			 if(!marca.equals("")) {
				 listaProductos=validaMarca(marca, listaProductos); 
			 }
			 
				 
			 return listaProductos; 
			
		}
		 
	}
	
	/**
	 * 
	 * Permite validar que productos cumplen la condicion del rango de precio dada 
	 * por el usuario 
	 * 
	 * @param precioMaximo
	 * @param precioMinimo
	 * @param lista de productos 
	 * @return List<Producto>, es decir una lista con los productos encontrados en el rango de precio dado
	 *         o una lista vacia en caso de no encontrar productos en el rango de precio dado
	 */
	public List<Producto> validaRangoPrecio(String precioMaximo, String precioMinimo, List<Producto> listaProductos){
		
		if ((precioMaximo==null) || (precioMinimo==null)|| (listaProductos==null)) {
			throw new NullPointerException(null);
		}
		
		List <Producto> nuevaListaProductos = new ArrayList <>();
		double precioMax;
		double precioMin;
		
		if(!precioMaximo.equals("") && !precioMinimo.equals("")) {
			
			precioMax = Double.parseDouble(precioMaximo);
			precioMin = Double.parseDouble(precioMinimo);
			
			for(Producto producto: listaProductos) {
				
				if(producto.getPrecio()<=precioMax && producto.getPrecio()>=precioMin) {
					nuevaListaProductos.add(producto); 
				}

			}
			
			return nuevaListaProductos; 
		}else {
			
			if(!precioMaximo.equals("")) {
				
				precioMax = Double.parseDouble(precioMaximo);
				for(Producto producto: listaProductos) {
					
					if(producto.getPrecio()<=precioMax) {
						nuevaListaProductos.add(producto); 
					}

				}
				
				return nuevaListaProductos; 
				
			}else {

				precioMin = Double.parseDouble(precioMinimo);
				for(Producto producto: listaProductos) {
					
					if(producto.getPrecio()>=precioMin) {
						nuevaListaProductos.add(producto); 
					}

				}
				
				return nuevaListaProductos; 
			}
		}
	}
	
	/**
	 * 
	 * Permite validar que productos cumplen la condicion del ID dado por el usuario 
	 * 
	 * @param id del producto
	 * @param lista de productos 
	 * @return List<Producto>, es decir una lista con los productos encontrados con el nombre dado
	 *         o una lista vacia en caso de no encontrar productos en el nombre dado
	 */
	public List<Producto> validaID(String idProducto, List<Producto> listaProductos){
		
		if ((idProducto == null) || ( listaProductos == null)) {
			throw new NullPointerException(null);
		}
		
		List <Producto> nuevaListaProductos = new ArrayList <>();
		String idProduc; 
		for(Producto producto: listaProductos) {
			idProduc=""+producto.getIdProducto(); 
			if(idProduc.compareTo(idProducto)==0) {
				nuevaListaProductos.add(producto); 
			}

		}
		
		return nuevaListaProductos; 
	}
	
	/**
	 * 
	 * Permite validar que productos cumplen la condicion del nombre dado por el usuario 
	 * 
	 * @param nombre del producto
	 * @param lista de productos 
	 * @return List<Producto>, es decir una lista con los productos encontrados con el nombre dado
	 *         o una lista vacia en caso de no encontrar productos en el nombre dado
	 */
	public List<Producto> validaNombre(String nombre, List<Producto> listaProductos){
		
		if ((nombre == null)|| (listaProductos == null)) {
			throw new NullPointerException("No se permiten parametros nulos");
		}
		
		List <Producto> nuevaListaProductos = new ArrayList <>();
		 
		for(Producto producto: listaProductos) {
			 
			if(producto.getNombre().compareTo(nombre)==0) {
				nuevaListaProductos.add(producto); 
			}

		}
		
		return nuevaListaProductos; 
	}
	
	/**
	 * 
	 * Permite validar que productos cumplen la condicion de la marca dada por el usuario 
	 * 
	 * @param marca del producto
	 * @param lista de productos 
	 * @return List<Producto>, es decir una lista con los productos encontrados con la marca dada
	 *         o una lista vacia en caso de no encontrar productos en la marca dada
	 */
	public List<Producto> validaMarca(String marca, List<Producto> listaProductos){
		
		if ((marca == null)|| (listaProductos == null)) {
			throw new NullPointerException("No se permiten parametros nulos");
		}
		
		List <Producto> nuevaListaProductos = new ArrayList <>();
		 
		for(Producto producto: listaProductos) {
			 
			if(producto.getMarca().compareTo(marca)==0) {
				nuevaListaProductos.add(producto); 
			}

		}
		
		return nuevaListaProductos; 
	}
	
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
