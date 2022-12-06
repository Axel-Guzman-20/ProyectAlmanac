package mx.uam.ingsof.proyecto.presentacion.registrarCompra;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.ServicioCompra;
import mx.uam.ingsof.proyecto.negocio.ServicioEmpleado;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;
@Component
public class ControlRegistrarCompra {
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	@Autowired
	private VistaRegistrarCompra vistaRegistrarVenta;
	@Autowired
	private ServicioCompra servicioCompra;
	public void inicia() {
		List <Empleado> listaEmpleado = servicioEmpleado.recuperaEmpleados();
		vistaRegistrarVenta.muestra(this, listaEmpleado);
		
	}
	public boolean crearCompra(long idEmpleado, String nombreProducto, String marcaProducto, int cantidadProdcuto,double precioProducto,String fecha){
		if(servicioCompra.creaGrantia(idEmpleado, nombreProducto, marcaProducto, cantidadProdcuto, precioProducto, fecha)) {
			return true;
		}else {
			return false;
		}
		
	}
	public boolean guardaCompras() {
		if(servicioCompra.guardaCompras()) {
			return true;
			
		}
		else
			return false;
	}
	public boolean limpiaLista() {
		if(servicioCompra.limpiaCompras())
			return true;
		else
			return false;
	}
}
//Eduardo castro estuvo aqui