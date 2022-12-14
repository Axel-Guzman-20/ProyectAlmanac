package mx.uam.ingsof.proyecto;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import mx.uam.ingsof.proyecto.datos.CategoriaDiagnosticoRepository;
import mx.uam.ingsof.proyecto.datos.ClienteRepository;
import mx.uam.ingsof.proyecto.datos.CompraRepository;
import mx.uam.ingsof.proyecto.datos.EmpleadoRepository;
import mx.uam.ingsof.proyecto.datos.ProductoRepository;
import mx.uam.ingsof.proyecto.datos.SeccionCatalogoRepository;
import mx.uam.ingsof.proyecto.datos.VentaRepository;
import mx.uam.ingsof.proyecto.negocio.ServicioCliente;
import mx.uam.ingsof.proyecto.negocio.ServicioCompra;
import mx.uam.ingsof.proyecto.negocio.ServicioEmpleado;
import mx.uam.ingsof.proyecto.negocio.ServicioVenta;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;
import mx.uam.ingsof.proyecto.negocio.modelo.CategoriaDiagnostico;
import mx.uam.ingsof.proyecto.negocio.*;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.SeccionCatalogo;
import mx.uam.ingsof.proyecto.presentacion.empleado.ControladorEmpleado;
import mx.uam.ingsof.proyecto.presentacion.principal.ControlPrincipal;
import mx.uam.ingsof.proyecto.presentacion.registrarCompra.ControlRegistrarCompra;



/**
 * 
 * Clase principal que arranca la aplicación 
 * construida usando el principio de 
 * inversión de control
 * 
 * 
 * @author Humberto Cervantes (c) 9 Dic 2021
 *
 *
 */

@SpringBootApplication
public class ProyectoApplication {
	
	@Autowired
	ControlPrincipal controlPrincipal;
	
	@Autowired
	SeccionCatalogoRepository seccionCatalogoRepository;
	
	@Autowired
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	CompraRepository compraRepository;
	
	@Autowired
	CategoriaDiagnosticoRepository categoriaDiagnosticoRepository; 
	
	@Autowired
	ServicioEmpleado servicioEmpleado;
	
	@Autowired
	ServicioCliente servicioCliente;
	
	@Autowired
	ServicioVenta servicioVenta;
	
	@Autowired
	VentaRepository ventaRepository;

	@Autowired
	ServicioReparacionMantenimiento servicioReparacionMantenimiento; 


	/**
	 * 
	 * Método principal
	 *
	 * @params args argumentos de la línea de comando
	 * 
	 */
	public static void main(String[] args) {
		
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ProyectoApplication.class);

		builder.headless(false);

		builder.run(args);
	}

	/**
	 * Metodo que arranca la aplicacion
	 * inicializa la bd y arranca el controlador
	 * otro comentario
	 */
	@PostConstruct
	public void inicia() {
		
		inicializaBD();
		
		controlPrincipal.inicia();
	}
	
	
	/**
	 * 
	 * Inicializa la BD con datos
	 * 
	 * 
	 */
	public void inicializaBD() {
		
		// Vamos a crear las secciones del catalogo
		
		SeccionCatalogo seccionImpresora = new SeccionCatalogo();
		seccionImpresora.setNombre("Impresora");
		seccionCatalogoRepository.save(seccionImpresora);
		
		SeccionCatalogo seccionProcesador = new SeccionCatalogo();
		seccionProcesador.setNombre("Procesadores");
		
		SeccionCatalogo seccionTarjetaMadre = new SeccionCatalogo();
		seccionTarjetaMadre.setNombre("Tarjetas Madres");
		seccionCatalogoRepository.save(seccionTarjetaMadre);
		
		SeccionCatalogo seccionMonitor = new SeccionCatalogo();
		seccionMonitor.setNombre("Monitores");
		seccionCatalogoRepository.save(seccionMonitor);
		
		SeccionCatalogo seccionDiscoDuro = new SeccionCatalogo();
		seccionDiscoDuro.setNombre("Discos Duros");
		seccionCatalogoRepository.save(seccionDiscoDuro);
		
		SeccionCatalogo seccionLaptop = new SeccionCatalogo();
		seccionLaptop.setNombre("Laptops");
		seccionCatalogoRepository.save(seccionLaptop);
		
		//Vamos a crear las categorias para los diagnosticos 
		
		CategoriaDiagnostico  categoriaReparacion = new CategoriaDiagnostico(); 
		categoriaReparacion.setNombre("Reparación");
		categoriaDiagnosticoRepository.save(categoriaReparacion); 
		
		CategoriaDiagnostico  categoriaMantenimiento = new CategoriaDiagnostico(); 
		categoriaMantenimiento.setNombre("Mantenimiento");
		categoriaDiagnosticoRepository.save(categoriaMantenimiento); 
		
		
		var productoPrueba = new Producto();
		 
		productoPrueba.setNombre("Core i3");
		productoPrueba.setMarca("Intel");
		productoPrueba.setDescripcion("10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T");
		productoPrueba.setPrecio(1499.95);
		productoPrueba.setDescuento(15);
		productoPrueba.setExistencia(7);
		productoPrueba.setTotalComprasProducto(0);
		
		seccionProcesador.addProducto(productoPrueba);
		
		var productoPrueba2 = new Producto();
		
		productoPrueba2.setNombre("Core i4");
		productoPrueba2.setMarca("Intel");
		productoPrueba2.setDescripcion("10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T");
		productoPrueba2.setPrecio(5000.93);
		productoPrueba2.setDescuento(10);
		productoPrueba2.setExistencia(1);
		productoPrueba2.setTotalComprasProducto(0);
		
		seccionProcesador.addProducto(productoPrueba2);
		
		var productoPrueba3 = new Producto();
		
		productoPrueba3.setNombre("Core i1");
		productoPrueba3.setMarca("AMD");
		productoPrueba3.setDescripcion("10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T");
		productoPrueba3.setPrecio(900.94);
		productoPrueba3.setDescuento(10);
		productoPrueba3.setExistencia(1);
		productoPrueba3.setTotalComprasProducto(0);
		
		seccionProcesador.addProducto(productoPrueba3);
		
		var productoPrueba4 = new Producto();
		
		productoPrueba4.setNombre("Core i2");
		productoPrueba4.setMarca("AMD");
		productoPrueba4.setDescripcion("10100F Comet Lake Quad - Procesador de sobremesa, Core 3,6 GHz, 6 MB, LGA 1200, 65W, 4C / 8T");
		productoPrueba4.setPrecio(1000.90);
		productoPrueba4.setDescuento(10);
		productoPrueba4.setExistencia(9);
		productoPrueba4.setTotalComprasProducto(0);
		
		seccionProcesador.addProducto(productoPrueba4);
		
		seccionCatalogoRepository.save(seccionProcesador);
		
		// SE REGISTRAN EMPLEADOS EN AUTOMÁTICO
		servicioEmpleado.registrarEmpleado("10/12/2022", "Abigail Morales", "M", "abigail@correo.com", "1234567891", "Calle 1, Col, Roma, Alc Carranza, CDMX, CDMX");
		servicioEmpleado.registrarEmpleado("11/12/2022", "Miguel Guzman", "H", "miguel@correo.com", "5896479625", "Calle 2, Col, Roma, Alc Carranza, CDMX, CDMX");
		servicioEmpleado.registrarEmpleado("12/12/2022", "Axel Guzman", "H", "axel@correo.com", "2563149563", "Calle 3, Col, Roma, Alc Carranza, CDMX, CDMX");
		servicioEmpleado.registrarEmpleado("13/12/2022", "Eduardo Castro", "H", "eduardo@correo.com", "9674852893", "Calle 4, Col, Roma, Alc Carranza, CDMX, CDMX");
	
    
	}
}
