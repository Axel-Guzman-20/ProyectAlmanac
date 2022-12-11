package mx.uam.ingsof.proyecto.presentacion.consultarVentas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.springframework.stereotype.Component;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;

@SuppressWarnings("serial")
@Component

/**
 * Ventana que mira el usuario para consultar las ventas registradas
 * 
 * @author MiguelGuzman
 * 
*/


public class VistaConsultarVentas extends JFrame{
	
	private ControlConsultarVentas controlConsultarVentas;
	
	private Font fuente;
	
	private JPanel panelPrincipal;
	private JPanel panelBlanco;
	private JPanel tablaPanel;
	
	private Color colorFondo;	

	private JDateChooser fechaDesdeDC;
	private JTextFieldDateEditor textoFechaDesdeDC;
	private JDateChooser fechaHastaDC;
	private JTextFieldDateEditor textoFechaHastaDC;
	
	private JTextField totalVentasText;
	
	private JComboBox <String> comboBoxEmpleados;
	private JComboBox <String> comboBoxClientes; 
	private JTextField textMontoVenta; 
	private JTextField[][] tablaDatos;
	private JButton buscarButton;
	private JButton limpiarButton;
	private JButton cancelarButton;
	
	private int anchoCasillas[];
	private int anchoVentana = 940;
	private int altoVentana = 740;
	private int columnasTablas = 6;
	
	public VistaConsultarVentas() {
		
		setTitle("Consultar ventas registradas");
		
		setLayout(null);
	
		// Dimensiones del JFrame, no se pone valor x, y por funcion
		// setLocationRelativeTos
		// (xPosicion,yPosicion,largoVentana, alturaVentana)
		setBounds(0, 0, anchoVentana, altoVentana);
			
		// Hacemos que la ventana se localice en el centro de la pantalla
		setLocationRelativeTo(null);
	
		// Para el titulo
		fuente = new Font("Tahoma", Font.BOLD, 22);
		
		// Color para el fondo azul
		colorFondo = new Color(89, 126, 170);
			
		// Panel simular el fondo y se le da diseño y configuracion
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(colorFondo);
		panelPrincipal.setBounds(0, 0, anchoVentana, altoVentana);
		panelPrincipal.setLayout(null);
		panelPrincipal.setVisible(true);
		
		
		// Para el panel blanco (donde dontiene la informacion)
		panelBlanco = new JPanel();
		panelBlanco.setBounds(40, 25, 840, 650);
		panelBlanco.setBackground(Color.WHITE);
		panelBlanco.setLayout(null);
		panelBlanco.setVisible(true);
		panelBlanco.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		
		// Agregamos el panel blanco al panel principal
		panelPrincipal.add(panelBlanco);
		
		//Creamos el titulo y lo agregamos al panel blanco
		JLabel tituloLabel = new JLabel("Ventas registradas");
		tituloLabel.setFont(fuente);
		tituloLabel.setBounds(320, 15, 450, 30);
		panelBlanco.add(tituloLabel);
		
		
		
		// Para las label y las cajas de texto
		fuente = new Font("Tahoma", 0, 14);
		
		
		//Creamos los label y lo agregamos al panel blanco
		
		JLabel fechaInicioLabel = new JLabel("Fecha desde:");
		fechaInicioLabel.setFont(fuente);
		fechaInicioLabel.setBounds(50, 70, 90, 25);
		panelBlanco.add(fechaInicioLabel);
				
		// Se crea la caja y el icono para escoger la fecha
		fechaDesdeDC = new JDateChooser();
		fechaDesdeDC.setBounds(140, 70, 140, 25);
		fechaDesdeDC.setDateFormatString("dd/MM/yyyy");
		// Lo que hace es recuperar la TextFieldDate del JCalendar
		textoFechaDesdeDC = (JTextFieldDateEditor) fechaDesdeDC.getDateEditor();
		textoFechaDesdeDC.setEditable(false);
		textoFechaDesdeDC.setBackground(Color.WHITE);
		textoFechaDesdeDC.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textoFechaDesdeDC.setForeground(Color.BLACK);
		
		panelBlanco.add(fechaDesdeDC);
			
		

		//Creamos los label y lo agregamos al panel blanco
		JLabel fechaFinalLabel = new JLabel("Hasta:");
		fechaFinalLabel.setFont(fuente);
		fechaFinalLabel.setBounds(350, 70, 90, 25);
		panelBlanco.add(fechaFinalLabel);
		
		// Se crea la caja y el icono para escoger la fecha
		fechaHastaDC = new JDateChooser();
		fechaHastaDC.setBounds(410, 70, 140, 25);
		fechaHastaDC.setDateFormatString("dd/MM/yyyy");	
		// Lo que hace es recuperar la TextFieldDate del JCalendar
		textoFechaHastaDC = (JTextFieldDateEditor) fechaHastaDC.getDateEditor();
		textoFechaHastaDC.setEditable(false);
		textoFechaHastaDC.setBackground(Color.WHITE);
		textoFechaHastaDC.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textoFechaHastaDC.setForeground(Color.BLACK);
				
		panelBlanco.add(fechaHastaDC);
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel nombreEmpleadoLabel = new JLabel("Nombre del Empleado:");
		nombreEmpleadoLabel.setFont(fuente);
		nombreEmpleadoLabel.setBounds(50, 110, 150, 30);
		panelBlanco.add(nombreEmpleadoLabel);			
		
		// Lo dejamos como caja de texto
		comboBoxEmpleados = new JComboBox<String>();
		comboBoxEmpleados.setBounds(220, 110, 250, 30);
		panelBlanco.add(comboBoxEmpleados);
		
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel nombreClienteLabel = new JLabel("Nombre del Cliente:");
		nombreClienteLabel.setFont(fuente);
		nombreClienteLabel.setBounds(50, 160, 150, 30);
		panelBlanco.add(nombreClienteLabel);			
						
		// Le damos su caja de texto
		comboBoxClientes = new JComboBox<String>();
		comboBoxClientes.setBounds(220, 160, 250, 30);
		panelBlanco.add(comboBoxClientes);
		
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel nombreProductoLabel = new JLabel("Monto de la Venta:");
		nombreProductoLabel.setFont(fuente);
		nombreProductoLabel.setBounds(50, 210, 150, 30);
		panelBlanco.add(nombreProductoLabel);			
								
		// Le damos su caja de texto
		textMontoVenta = new JTextField();
		textMontoVenta.setBounds(220, 210, 250, 30);
		panelBlanco.add(textMontoVenta);
				
		// Color de las casillas de los encabezados de las tablas
		colorFondo = new Color(205, 205, 205);
		
		
		fuente = new Font("Tahoma", Font.BOLD, 12);
		
		JTextField idVentaText = new JTextField();
		idVentaText.setBackground(colorFondo);
		idVentaText.setText("idVenta");
		idVentaText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		idVentaText.setBounds(50, 260, 60, 25);
		idVentaText.setFont(fuente);
		idVentaText.setForeground(Color.BLACK);
		idVentaText.setEditable(false);
		idVentaText.setHorizontalAlignment(SwingConstants.CENTER);
		panelBlanco.add(idVentaText);
				
		JTextField fechaTablaText = new JTextField();
		fechaTablaText.setBackground(colorFondo);
		fechaTablaText.setText("Fecha");
		fechaTablaText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		fechaTablaText.setBounds(109, 260, 80, 25);
		fechaTablaText.setFont(fuente);
		fechaTablaText.setForeground(Color.BLACK);
		fechaTablaText.setEditable(false);
		fechaTablaText.setHorizontalAlignment(SwingConstants.CENTER);
		panelBlanco.add(fechaTablaText);
		
		JTextField clienteTablaText = new JTextField();
		clienteTablaText.setBackground(colorFondo);
		clienteTablaText.setText("Cliente");
		clienteTablaText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		clienteTablaText.setBounds(188, 260, 190, 25);
		clienteTablaText.setFont(fuente);
		clienteTablaText.setForeground(Color.BLACK);
		clienteTablaText.setEditable(false);
		clienteTablaText.setHorizontalAlignment(SwingConstants.CENTER);
		panelBlanco.add(clienteTablaText);
		
		JTextField productoTablaText = new JTextField();
		productoTablaText.setBackground(colorFondo);
		productoTablaText.setText("Empleado");
		productoTablaText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		productoTablaText.setBounds(377, 260, 190, 25);
		productoTablaText.setFont(fuente);
		productoTablaText.setForeground(Color.BLACK);
		productoTablaText.setEditable(false);
		productoTablaText.setHorizontalAlignment(SwingConstants.CENTER);
		panelBlanco.add(productoTablaText);
		
		JTextField cantidadProductosVendidos = new JTextField();
		cantidadProductosVendidos.setBackground(colorFondo);
		cantidadProductosVendidos.setText("Cantidad Productos");
		cantidadProductosVendidos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		cantidadProductosVendidos.setBounds(566, 260, 130, 25);
		cantidadProductosVendidos.setFont(fuente);
		cantidadProductosVendidos.setForeground(Color.BLACK);
		cantidadProductosVendidos.setEditable(false);
		cantidadProductosVendidos.setHorizontalAlignment(SwingConstants.CENTER);
		panelBlanco.add(cantidadProductosVendidos);
		

		JTextField totalTablaText = new JTextField();
		totalTablaText.setBackground(colorFondo);
		totalTablaText.setText("Monto Venta");
		totalTablaText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		totalTablaText.setBounds(695, 260, 100, 25);
		totalTablaText.setForeground(Color.BLACK);
		totalTablaText.setFont(fuente);
		totalTablaText.setEditable(false);
		totalTablaText.setHorizontalAlignment(SwingConstants.CENTER);
		panelBlanco.add(totalTablaText);
		
		// Panel para la mostrar los datos de la tabla
		tablaPanel = new JPanel();
		tablaPanel.setLayout(null);
		tablaPanel.setBounds(50, 284, 745, 241);
		tablaPanel.setBackground(Color.WHITE);
		
		panelBlanco.add(tablaPanel);
		
		// Con la información anterior (idVenta, Fecha, etc), pude saber la anchura que le tocara a cada casilla, primero lo hice manual
		anchoCasillas = new int[columnasTablas];
		anchoCasillas[0] = 60;
		anchoCasillas[1] = 80;
		anchoCasillas[2] = 190;
		anchoCasillas[3] = 190;
		anchoCasillas[4] = 130;
		anchoCasillas[5] = 100;
		
		
		// Para las label
		fuente = new Font("Tahoma", Font.BOLD, 16);
						
		//Creamos los label y lo agregamos al panel blanco
		JLabel totalVentasLabel = new JLabel("Total de Ventas Registradas:");
		totalVentasLabel.setFont(fuente);
		totalVentasLabel.setBounds(400, 540, 250, 30);
		panelBlanco.add(totalVentasLabel);
						
		//Creamos la caja de texto sin poder editar
		totalVentasText = new JTextField();
		totalVentasText.setFont(fuente);
		totalVentasText.setEditable(false);
		totalVentasText.setBackground(Color.WHITE);
		totalVentasText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		totalVentasText.setBounds(650, 540, 140, 33);
		panelBlanco.add(totalVentasText);
		
		// Fuente para los botones
		fuente = new Font("Tahoma", Font.BOLD, 11);
		
		int xBotones = 210;
		int espacioEntreBotones = 160;
		int yBotones = 600;
		int anchoBotones = 90;
		int altoBotones = 30;
		
		buscarButton = new JButton("Buscar");
		buscarButton.setFont(fuente);
		buscarButton.setForeground(Color.WHITE);
		buscarButton.setBackground(new Color(0, 158, 15));
		buscarButton.setBounds(xBotones, yBotones, anchoBotones, altoBotones);
		buscarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelBlanco.add(buscarButton);
		
		xBotones = xBotones + espacioEntreBotones;
		
		limpiarButton = new JButton("Limpiar");
		limpiarButton.setFont(fuente);
		limpiarButton.setForeground(Color.WHITE);
		limpiarButton.setBackground(new Color(89, 126, 170));
		limpiarButton.setBounds(xBotones, yBotones, anchoBotones, altoBotones);
		limpiarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelBlanco.add(limpiarButton);
		
		xBotones = xBotones + espacioEntreBotones;
		
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setFont(fuente);
		cancelarButton.setForeground(Color.WHITE);
		cancelarButton.setBackground(new Color(204, 0, 0));
		cancelarButton.setBounds(xBotones, yBotones, anchoBotones, altoBotones);
		cancelarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelBlanco.add(cancelarButton);

		
		
		//Acciones de los botones
		buscarButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					recolectaDatosConsultaVenta();
				} catch (ParseException e1) {
				}
			}
		});		
				
				
		limpiarButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limpiaTodo();
			}
		});
				
				
		cancelarButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controlConsultarVentas.cierraVentana();
			}
		});
			
		// Agregamos el panel principal al JFrame que creamos al incio de la aplicación
		getContentPane().add(panelPrincipal);

	}
	
	

	
	public void muestra(ControlConsultarVentas control, List <Empleado> empleados, List <Cliente> clientes) {
			
		controlConsultarVentas = control;
		
		llenaDatosComboBox(empleados, clientes);
		
		limpiaTodo();
		
		setVisible(true);
	}
	
	
	// Todas las casillas son de la misma altura	
	private final int altoCasilla = 25;
	private final Color colorAzulClarito = new Color(184, 199, 218);
	
	public void mostrarPanelTablaDatos(String [][]datos) {
		
		// Panel para la mostrar los datos de la tabla
		// Se vuelve a crear el panel
		tablaPanel = new JPanel();
		tablaPanel.setLayout(null);
		tablaPanel.setBounds(50, 284, 745, 241);
		tablaPanel.setBackground(Color.WHITE);
		panelBlanco.add(tablaPanel);
		
		
		tablaDatos = new JTextField[datos.length][columnasTablas];		
		
		// Esta información va variando depende la casilla
		int xCasilla = 0;
		int yCasilla = 0;
			
		// Se da diseño y se crea la instancia
		for (int i = 0; i < tablaDatos.length; i++) {
			
			if(i%2==0)
				colorFondo = colorAzulClarito;
			else
				colorFondo = Color.WHITE;
			
			for (int j = 0; j < 6; j++) {
				tablaDatos[i][j] = new JTextField();
				tablaDatos[i][j].setBackground(colorFondo);
				tablaDatos[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				tablaDatos[i][j].setBounds(xCasilla, yCasilla, anchoCasillas[j], altoCasilla);
				tablaDatos[i][j].setForeground(Color.BLACK);
				tablaDatos[i][j].setEditable(false);
				// Se evita que la columna Cliente y Empleado se alinean al centro, esos iran por default a la izquierda
				if(j!=2 && j!=3)
					tablaDatos[i][j].setHorizontalAlignment(SwingConstants.CENTER);
			
				tablaDatos[i][j].setText(datos[i][j]);
				
				tablaPanel.add(tablaDatos[i][j]);
				xCasilla = (xCasilla + anchoCasillas[j]) - 1;
				
			}
			xCasilla = 0;
			yCasilla = yCasilla + (altoCasilla - 1);
		}
		
		// Aqui empieza para poner la cifra de monto y venta total en formato de pesos con 2 decimales
		
		Locale formatoLocal = new Locale ("en", "UK");
		
        NumberFormat formatoDecimal = NumberFormat.getInstance (formatoLocal);
        
        String montoConFormatoDec;
        double montoCaja;
		double totalVentas = 0;
		
		for (int i = 0; i < datos.length; i++) {
			montoCaja = Double.parseDouble(tablaDatos[i][5].getText());
			totalVentas = totalVentas + montoCaja;
			montoConFormatoDec =  formatoDecimal.format(montoCaja);
			tablaDatos[i][5].setText("$ " + montoConFormatoDec);
		}
		        
		montoConFormatoDec= formatoDecimal.format(totalVentas);
        
        totalVentasText.setText("$ " + montoConFormatoDec);
		
		// Como ya se llenó la info, se muestra el panel de la tabla
		tablaPanel.setVisible(true);
	}
	
	
	
	public void termina() {
		
		setVisible(false);
	
	}
	
	
	
	public void muestraDialogoConMensaje(String mensaje ) {
		
		UIManager.put("OptionPane.background", new Color(184,199,218));
		UIManager.put("Panel.background", new Color(184,199,218));
		UIManager.put("Button.background", new Color(255,255,255));
		UIManager.put("Button.foreground", new Color(89, 126, 170));
		UIManager.put("Button.font", new Font("Tahoma", Font.BOLD, 13));
		
		JLabel etiqueta = new JLabel(mensaje, JLabel.CENTER); 
		etiqueta.setFont(new Font("Tahoma", Font.BOLD, 15)); 
		etiqueta.setForeground(new Color(255,255,255)); 
		
		JOptionPane.showMessageDialog(this ,etiqueta, "AVISO", JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	
	public void llenaDatosComboBox(List <Empleado> empleados, List <Cliente> clientes) {
		
		DefaultComboBoxModel <String> comboBoxModel = new DefaultComboBoxModel <>();
		comboBoxModel.addElement("--Seleccione una opción");
		for(Empleado empleado: empleados)
			comboBoxModel.addElement(empleado.getNombreCompleto());
		comboBoxEmpleados.setModel(comboBoxModel);
		
		
		comboBoxModel = new DefaultComboBoxModel <>();
		comboBoxModel.addElement("--Seleccione una opción");
		for(Cliente cliente: clientes)
			comboBoxModel.addElement(cliente.getNombreCompleto());
		comboBoxClientes.setModel(comboBoxModel);
		
	}
	
	
	public void recolectaDatosConsultaVenta() throws ParseException {
		
		String fechaDesde = textoFechaDesdeDC.getText();
		String fechaHasta = textoFechaHastaDC.getText();
		String montoVenta = textMontoVenta.getText();
		String[][] datos;
		
		// En caso que haya echo una busqueda, y le vuelva a dar sin que haya limpiado
		tablaPanel.setVisible(false);
		totalVentasText.setText("");
		
		
		// Recupera el texto del item del comboBox Selecionado
		String itemEmpleadoId = String.valueOf(comboBoxEmpleados.getSelectedIndex());
		String itemClienteId = String.valueOf(comboBoxClientes.getSelectedIndex());
		
		datos = controlConsultarVentas.consultarVentas(fechaDesde, fechaHasta, itemEmpleadoId, itemClienteId, montoVenta);
		 
		if(datos != null) 
		  mostrarPanelTablaDatos(datos);
		
	}
	
	
	
	public void limpiaTodo() {
		
		textoFechaDesdeDC.setText("");
		textoFechaHastaDC.setText("");
		comboBoxEmpleados.setSelectedIndex(0);
		comboBoxClientes.setSelectedIndex(0);
		textMontoVenta.setText("");
		totalVentasText.setText("");	
		tablaPanel.setVisible(false);
		
	}
	
	
}
