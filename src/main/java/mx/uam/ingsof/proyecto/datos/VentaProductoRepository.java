package mx.uam.ingsof.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ingsof.proyecto.negocio.modelo.VentaProducto;


public interface VentaProductoRepository extends CrudRepository<VentaProducto, Long> {

}
