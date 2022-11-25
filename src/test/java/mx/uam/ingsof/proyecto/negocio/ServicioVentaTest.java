package mx.uam.ingsof.proyecto.negocio;



import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ingsof.proyecto.datos.VentaRepository;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.VentaProducto;
@ExtendWith(MockitoExtension.class)
class ServicioVentaTest {

	@Mock
	VentaRepository ventaRepository;
	@InjectMocks
	ServicioVenta servicioVenta;
	private Producto producto;
	@BeforeEach
	void setUp() throws Exception {
		producto = new Producto();
		
		producto.setNombre("Core i3");
		producto.setMarca("Intel");
		producto.setDescripcion("10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T");
		producto.setPrecio(1499.90);
		producto.setDescuento(15);
		producto.setExistencia(7);
		producto.setTotalComprasProducto(0);
		
	}
	@Test
	void testAgregaProducto() {
		//Caso 1:No se pudo agregar el producto
		
		VentaProducto ventaProducto = new VentaProducto();
		VentaProducto ventaProducto2 = new VentaProducto();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			servicioVenta.agregaProducto(null,1);
		
		});
		//Caso 2: Se pudo agregar el producto
		ventaProducto.setCantidad(1);
		ventaProducto.setProducto(producto);
		
		ventaProducto2 = servicioVenta.agregaProducto(producto, 1);
		assertEquals(ventaProducto,ventaProducto2);
		
		
	}

}
