package mx.uam.ingsof.proyecto.presentacion.registrarCompra;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mx.uam.ingsof.proyecto.negocio.ServicioEmpleado;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;

public class ControlRegistrarCompra {
	@Autowired
	ServicioEmpleado servicioEmpleado;
	@Autowired
	VistaRegistrarCompra vistaRegistrarVenta;
	public void inicia() {
		List <Empleado> listaEmpleado = servicioEmpleado.recuperaEmpleados();
		vistaRegistrarVenta.muestra(this, listaEmpleado);
		
	}
}
