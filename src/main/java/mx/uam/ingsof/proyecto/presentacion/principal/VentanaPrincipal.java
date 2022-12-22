package mx.uam.ingsof.proyecto.presentacion.principal;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.springframework.stereotype.Component;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;



@SuppressWarnings("serial")
@Component
public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	private ControlPrincipal controlPrincipal;
	
	private Font fuente;

	
	/**
	 * Inicia el frame.
	*/
	
	public VentanaPrincipal() {
		
		setTitle("Menú principal de ServiceTec");

		// Dimensiones del JFrame, no se pone valor x, y por funcion
		// setLocationRelativeTos
		// (xPosicion,yPosicion,largoVentana, alturaVentana)
		setBounds(0, 0, 900, 535);

		// Hacemos que la ventana se localice en el centro de la pantalla
		setLocationRelativeTo(null);

		// fuente para los menús
		fuente = new Font("Sitka Subheading", Font.BOLD, 16);

		
	
		
		/***********************************
		 * 
		 * 
		 * MENU BAR (PRINCIPAL)
		 * Catálogo, Clientes, Ventas Compras, Reparaciones, Proveedores
		 * 
		 ***********************************/
		

		// Objeto para crear opciones de menu
		JMenuBar menuBar = new JMenuBar();
		
		// Tamaño del menuBar
		menuBar.setBounds(0, 0, 900, 10);
		
		// Damos color al menuBar en general"
		menuBar.setBackground(new Color(244, 244, 244));
		
		// Se asigna un FlowLAyout a menuBar para que esten centrados los titulos y se recorran automaticamente y ajusten, el 90 es pix de separacion de ancho y 0 de alto
		menuBar.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));

		
		
		// Se crea menu Catalogo con tipo de letra y diseño y se agrega a menu bar
		JMenu menuCatalogo = new JMenu("Catálogo");
		menuCatalogo.setFont(fuente);
		menuBar.add(menuCatalogo);
		
		
		// Se crea menu Clientes con tipo de letra y diseño y se agrega a menu bar
		JMenu menuClientes = new JMenu("Clientes");
		menuClientes.setFont(fuente);
		menuBar.add(menuClientes);
		
		
		// Se crea menu Ventas con tipo de letra y diseño y se agrega a menu bar
		JMenu menuVentas = new JMenu("Ventas");
		menuVentas.setFont(fuente);
		menuBar.add(menuVentas);
		
		
		// Se crea menu Compras con tipo de letra y diseño y se agrega a menu bar
		JMenu menuCompras = new JMenu("Compras");
		menuCompras.setFont(fuente);
		menuBar.add(menuCompras);
		
		
		// Se crea menu Reparaciones con tipo de letra y diseño y se agrega a menu bar
		JMenu menuReparacion = new JMenu("Reparación");
		menuReparacion.setFont(fuente);
		menuBar.add(menuReparacion);
		
		
		// Se crea menu menuProveedores con tipo de letra y diseño y se agrega a menu bar
		JMenu menuProveedores = new JMenu("Proveedores");
		menuProveedores.setFont(fuente);
		menuBar.add(menuProveedores);
		
		
		// fuente para los submenus, se vuelve a declarar
		fuente = new Font("Sitka Subheading", Font.PLAIN, 15);
		
		
				
				
		/***********************************
		 * 
		 * 
		 * MENU CATALAGO CON SUS SUBMENUS
		 * Agregar nuevo, Modificar producto, Eliminar, Buscador
		 * 
		 ***********************************/
		
		
		// Se crea menuItemAgregar para Catalogo y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemAgregar = new JMenuItem("Agregar Nuevo");
		menuItemAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPrincipal.agregarProducto();
			}
		});
		// Se le pone a menuItemAgregar tipo de letra y diseño y se agrega a menuCatalogo
		menuItemAgregar.setFont(fuente);
		menuCatalogo.add(menuItemAgregar);

		
		// Se crea menuItemModificar para Catalogo y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemModificar = new JMenuItem("Modificar Producto");
		menuItemModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPrincipal.modificarProducto();
			}
		});
		// Se le pone a menuItemModificar tipo de letra y diseño y se agrega a menuCatalogo
		menuItemModificar.setFont(fuente);
		menuCatalogo.add(menuItemModificar);
		
		// Se crea menuItemBuscador para Catalogo y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemBuscador = new JMenuItem("Buscador");
		menuItemBuscador.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			     controlPrincipal.buscarProducto();
			}
		});
		// Se le pone a menuItemBuscador tipo de letra y diseño y se agrega a menuCatalogo
		menuItemBuscador.setFont(fuente);
		menuCatalogo.add(menuItemBuscador);
		
		
		
		
		/***********************************
		 * 
		 * 
		 * MENU CLIENTES CON SUS SUBMENUS
		 * Nuevo cliente, Buscar Cliente, Modificar Cliente, Historial
		 * 
		 ***********************************/

		
		// Se crea menuItemNuevoCliente para Clientes y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemNuevoCliente = new JMenuItem("Nuevo Cliente");
		menuItemNuevoCliente.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controlPrincipal.nuevoCliente();					
			}
		});
		// Se le pone a menuItemNuevoCliente tipo de letra y diseño y se agrega a menuCatalogo
		menuItemNuevoCliente.setFont(fuente);
		menuClientes.add(menuItemNuevoCliente);
		
		
		// Se crea menuItemBuscarCliente para Clientes y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemBuscarCliente = new JMenuItem("Buscar Cliente");
		menuItemBuscarCliente.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
						
			}
		});
		// Se le pone a menuItemBuscarCliente tipo de letra y diseño y se agrega a menuCatalogo
		menuItemBuscarCliente.setFont(fuente);
		menuClientes.add(menuItemBuscarCliente);
		
		
		// Se crea menuItemModificarCliente para Clientes y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemModificarCliente = new JMenuItem("Modificar Cliente");
		menuItemModificarCliente.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				controlPrincipal.modificarCliente();
			}
		});
		
		
		// Se le pone a menuItemModificarCliente tipo de letra y diseño y se agrega a menuCatalogo
		menuItemModificarCliente.setFont(fuente);
		menuClientes.add(menuItemModificarCliente);
		
		
		// Se crea menuItemHistorial para Clientes y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemHistorial = new JMenuItem("Historial");
		menuItemHistorial.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
						
			}
		});
		// Se le pone a menuItemHistorial tipo de letra y diseño y se agrega a menuCatalogo
		menuItemHistorial.setFont(fuente);
		menuClientes.add(menuItemHistorial);
		
		
		
		
		/***********************************
		 * 
		 * 
		 * MENU VENTAS CON SUS SUBMENUS
		 * Nueva venta, Garantías, Más vendidos, Mostrar ventas
		 * 
		 ***********************************/
		
		
		// Se crea menuItemNuevaVenta para Ventas y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemNuevaVenta = new JMenuItem("Nueva Venta");
		menuItemNuevaVenta.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controlPrincipal.nuevaVenta();
				
			}
		});
		// Se le pone a menuItemNuevaVenta tipo de letra y diseño y se agrega a menuVentas
		menuItemNuevaVenta.setFont(fuente);
		menuVentas.add(menuItemNuevaVenta);
		
		
		// Se crea menuItemGarantias para Ventas y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemGarantias = new JMenuItem("Garantía");
		menuItemGarantias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPrincipal.creaGarantia();
			}
		});
		// Se le pone a menuItemGarantias tipo de letra y diseño y se agrega a menuVentas
		menuItemGarantias.setFont(fuente);
		menuVentas.add(menuItemGarantias);
		
		
		/*ESTÁ PENDIENTE SABER SI SE QUITA O NO
		// Se crea menuItemMasVendidos para Ventas y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemMasVendidos = new JMenuItem("Más Vendidos");
		menuItemMasVendidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPrincipal.listaProductosMasComprados();
			}
		});
		// Se le pone a menuItemMasVendidos tipo de letra y diseño y se agrega a menuVentas
		menuItemMasVendidos.setFont(new Font("Sitka Subheading", Font.PLAIN, 15));
		menuVentas.add(menuItemMasVendidos);
		*/
		
		
		// Se crea menuItemMostrarVentas para Ventas y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemMostrarVentas = new JMenuItem("Mostrar Ventas");
		menuItemMostrarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPrincipal.mostrarVentas();
			}
		});
		// Se le pone a menuItemMostrarVentas tipo de letra y diseño y se agrega a menuVentas
		menuItemMostrarVentas.setFont(fuente);
		menuVentas.add(menuItemMostrarVentas);
		
		
		
		
		/***********************************
		 * 
		 * 
		 * MENU COMPRAS CON SUS SUBMENUS
		 * Nueva compra, Mostrar inversiones
		 * 
		 ***********************************/
		
		
		// Se crea menuItemNuevaCompra para Compras y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemNuevaCompra = new JMenuItem("Nueva Compra");
		menuItemNuevaCompra.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				controlPrincipal.nuevaCompra();
			}
		});
		// Se le pone a menuItemNuevaCompra tipo de letra y diseño y se agrega a menuVentas
		menuItemNuevaCompra.setFont(fuente);
		menuCompras.add(menuItemNuevaCompra);
		
		
		// Se crea menuItemMostrarInversiones para Compras y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemMostrarInversiones = new JMenuItem("Mostrar Inversiones");
		menuItemMostrarInversiones.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
			}
		});
		// Se le pone a menuItemNuevaCompra tipo de letra y diseño y se agrega a menuVentas
		menuItemMostrarInversiones.setFont(fuente);
		menuCompras.add(menuItemMostrarInversiones);
		
		
		
		
		/***********************************
		 * 
		 * 
		 * MENU REPARACIÓN CON SUS SUBMENUS
		 * Nueva reparación, Pruebas de reparación
		 * 
		 ***********************************/
		
		
		// Se crea menuItemNuevaReparacion para Reparacion y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemNuevaReparacion = new JMenuItem("Nueva Reparación");
		menuItemNuevaReparacion.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			controlPrincipal.crearDiagnostico();
				
			}
		});
		// Se le pone a menuItemNuevaReparacion tipo de letra y diseño y se agrega a menuVentas
		menuItemNuevaReparacion.setFont(fuente);
		menuReparacion.add(menuItemNuevaReparacion);
		
		
		// Se crea menuItemPruebasReparacion para Reparacion y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemPruebasReparacion = new JMenuItem("Pruebas de Reparación");
		menuItemPruebasReparacion.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			controlPrincipal.realizarPruebasReparacion(); 
				
			}
		});
		// Se le pone a menuItemPruebasReparacion tipo de letra y diseño y se agrega a menuVentas
		menuItemPruebasReparacion.setFont(fuente);
		menuReparacion.add(menuItemPruebasReparacion);
		
		
		
		
		/***********************************
		 * 
		 * 
		 * MENU PROVEEDORES CON SUS SUBMENUS
		 * Nueva proveedor
		 * 
		 ***********************************/
		
		
		// Se crea menuItemNuevoProveedor para Proveedor y su boton de acciones (menuItem = Submenu)
		JMenuItem menuItemNuevoProveedor = new JMenuItem("Nuevo Proveedor");
		menuItemNuevoProveedor.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				controlPrincipal.nuevoProveedor();
			}
		});
		// Se le pone a menuItemNuevaVenta tipo de letra y diseño y se agrega a menuVentas
		menuItemNuevoProveedor.setFont(fuente);
		menuProveedores.add(menuItemNuevoProveedor);
		
		
		
		
		
		
		// Se agrega a la Ventana el menuBar
		setJMenuBar(menuBar);

		
		// Se crea panel para poner una etiqueta como texto principal e imagenes para adornar,
		// se da su posición y tamaño y un background
		// Se pone Layout libre para jugar con las posiciones y se agrega a la Ventana
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, 600, 450);
		contentPane.setBackground(new Color(89, 126, 170));
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
		
		// Se agrega un label a la Ventana, con fuente modificada, (tipo, personalizaciones, tamaño, color) y se da su posicion y tamaño
		// y se Agrega al panel
		JLabel lblTitulo = new JLabel("ServiceTEC");
		lblTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 80));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(245, 72, 400, 210);
		contentPane.add(lblTitulo);
		
		
		// Se agrega una imagen a la ventana principal, se da su posición y tamaño, con la ruta de la imagen y se agrega al panel
		JLabel lblIcono = new JLabel(new ImageIcon(VentanaPrincipal.class.getResource("/mx/uam/ingsof/imagen/logoComputadora.png")));
		lblIcono.setBounds(157, 30, 565, 408);
		contentPane.add(lblIcono);
		
	
		// Termina la ejecucion del programa
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	
	public void muestra(ControlPrincipal control) {
		
		controlPrincipal = control;
		setVisible(true);
	
	}
	
}