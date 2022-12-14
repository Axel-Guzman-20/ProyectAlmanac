package mx.uam.ingsof.proyecto.presentacion.consultarcompras;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.Serializable;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.springframework.stereotype.Component;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;


@Component
public class VistaConsultarCompras extends JFrame implements ActionListener, Serializable {
	
	
	/**
	 * Me indica la versión de la interfaz de usuario de la HU-10
	 */
	private static final long serialVersionUID = 1L;
	
	private int anchoVentana = 940;
	private int altoVentana = 740;
	
	private Font fuente;
	private String tipoLetra = "Tahoma";
	
	private Color colorAzul = new Color(89, 126, 170);
	private Color colorBlanco = Color.WHITE;
	private Color colorNegro = Color.BLACK;
	private Color colorVerde = new Color(0, 158, 15);
	private Color colorRojo = new Color(204, 0, 0);
	
	private JPanel panelPrincipal;
	private JPanel panelBlanco;
	
	private JDateChooser fechaDesdeDC;
	private JTextFieldDateEditor textoFechaDesdeDC;
	private JDateChooser fechaHastaDC;
	private JTextFieldDateEditor textoFechaHastaDC;
	
	private JComboBox <String> comboBoxEmpleados;
	
	private JTextField nombreProveedorTextField;
	private JTextField nombreProductoTextField;
	private JTextField totalGeneralComprasText;

	private JButton buscarButton;
	private JButton limpiarButton;
	private JButton cancelarButton;
	
	private JTable tablaDatos;
	private DefaultTableModel dtm;
	private JScrollPane panelTablaScroll;
	
	private String[] titulosEncabezado = {"idCompra", "Fecha", "Empleado", "Marca", "Producto", "Cantidad", "Monto"};
	
	private ControlConsultarCompra controlConsultarCompras;
	 
	
	public VistaConsultarCompras() throws PrinterException {
		
		setTitle("Consultar comrpas registradas");
		
		setLayout(null);
		
		// Dimensiones del JFrame, no se pone valor x, y por funcion
		// setLocationRelativeTos
		// (xPosicion,yPosicion,largoVentana, alturaVentana)
		setBounds(0, 0, anchoVentana, altoVentana);
		
		// Hacemos que la ventana se localice en el centro de la pantalla
		setLocationRelativeTo(null);
				
		// Para el titulo
		fuente = new Font(tipoLetra, Font.BOLD, 22);
		
		// Panel simular el fondo y se le da diseño y configuracion
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(colorAzul);
		panelPrincipal.setBounds(0, 0, anchoVentana, altoVentana);
		panelPrincipal.setLayout(null);
		panelPrincipal.setVisible(true);
		
		// Para el panel blanco (donde dontiene la informacion)
		panelBlanco = new JPanel();
		panelBlanco.setBounds(40, 25, 840, 650);
		panelBlanco.setBackground(colorBlanco);
		panelBlanco.setLayout(null);
		panelBlanco.setVisible(true);
		panelBlanco.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));		

		
		// Agregamos el panel blanco al panel principal
		panelPrincipal.add(panelBlanco);
		
		
		//Creamos el titulo y lo agregamos al panel blanco
		JLabel tituloLabel = new JLabel("Compras Generadas");
		tituloLabel.setFont(fuente);
		tituloLabel.setBounds(320, 15, 450, 30);
		panelBlanco.add(tituloLabel);
		
		
		// Para las label y las cajas de texto
		fuente = new Font(tipoLetra, 0, 14);
				
				
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
		textoFechaDesdeDC.setBackground(colorBlanco);
		textoFechaDesdeDC.setBorder(BorderFactory.createLineBorder(colorNegro));
		textoFechaDesdeDC.setForeground(colorNegro);
				
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
		textoFechaHastaDC.setBackground(colorBlanco);
		textoFechaHastaDC.setBorder(BorderFactory.createLineBorder(colorNegro));
		textoFechaHastaDC.setForeground(colorNegro);
				
		panelBlanco.add(fechaHastaDC);
	
		//Creamos los label y lo agregamos al panel blanco
		JLabel nombreEmpleadoLabel = new JLabel("Nombre del Empleado:");
		nombreEmpleadoLabel.setFont(fuente);
		nombreEmpleadoLabel.setBounds(50, 110, 150, 30);
		panelBlanco.add(nombreEmpleadoLabel);			
				
		comboBoxEmpleados = new JComboBox<>();
		comboBoxEmpleados.setBounds(220, 110, 250, 30);
		panelBlanco.add(comboBoxEmpleados);
				
					
		//Creamos los label y lo agregamos al panel blanco
		JLabel nombreProveedorLabel = new JLabel("Nombre del Proveedor:");
		nombreProveedorLabel.setFont(fuente);
		nombreProveedorLabel.setBounds(50, 160, 150, 30);
		panelBlanco.add(nombreProveedorLabel);			
								
		// Le damos su caja de texto
		nombreProveedorTextField = new JTextField();
		nombreProveedorTextField.setBounds(220, 160, 250, 30);
		panelBlanco.add(nombreProveedorTextField);
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel nombreProdcutoLabel = new JLabel("Nombre del Producto:");
		nombreProdcutoLabel.setFont(fuente);
		nombreProdcutoLabel.setBounds(50, 210, 150, 30);
		panelBlanco.add(nombreProdcutoLabel);			
										
		// Le damos su caja de texto
		nombreProductoTextField = new JTextField();
		nombreProductoTextField.setBounds(220, 210, 250, 30);
		panelBlanco.add(nombreProductoTextField);
		
		// Para las label
		fuente = new Font(tipoLetra, Font.BOLD, 16);
								
		//Creamos los label y lo agregamos al panel blanco
		JLabel totalComprasLabel = new JLabel("Total de Compras Registradas:");
		totalComprasLabel.setFont(fuente);
		totalComprasLabel.setBounds(390, 540, 260, 30);
		panelBlanco.add(totalComprasLabel);
								
		//Creamos la caja de texto sin poder editar
		totalGeneralComprasText = new JTextField();
		totalGeneralComprasText.setFont(fuente);
		totalGeneralComprasText.setEditable(false);
		totalGeneralComprasText.setBackground(colorBlanco);
		totalGeneralComprasText.setBorder(BorderFactory.createLineBorder(colorNegro, 1));
		totalGeneralComprasText.setBounds(650, 540, 140, 33);
		panelBlanco.add(totalGeneralComprasText);
		
		tabla();
		
		// Fuente para los botones
		fuente = new Font(tipoLetra, Font.BOLD, 11);
				
		int xBotones = 210;
		int yBotones = 600;
		int anchoBotones = 90;
		int altoBotones = 30;
		int espacioEntreBotones = 160;
				
		buscarButton = new JButton("Buscar");
		buscarButton.setFont(fuente);
		buscarButton.setForeground(colorBlanco);
		buscarButton.setBackground(colorVerde);
		buscarButton.setBounds(xBotones, yBotones, anchoBotones, altoBotones);
		buscarButton.setBorder(BorderFactory.createLineBorder(colorNegro));
		panelBlanco.add(buscarButton);
				
		xBotones = xBotones + espacioEntreBotones;
			
		limpiarButton = new JButton("Limpiar");
		limpiarButton.setFont(fuente);
		limpiarButton.setForeground(colorBlanco);
		limpiarButton.setBackground(colorAzul);
		limpiarButton.setBounds(xBotones, yBotones, anchoBotones, altoBotones);
		limpiarButton.setBorder(BorderFactory.createLineBorder(colorNegro));
		panelBlanco.add(limpiarButton);
				
		xBotones = xBotones + espacioEntreBotones;
				
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setFont(fuente);
		cancelarButton.setForeground(colorBlanco);
		cancelarButton.setBackground(colorRojo);
		cancelarButton.setBounds(xBotones, yBotones, anchoBotones, altoBotones);
		cancelarButton.setBorder(BorderFactory.createLineBorder(colorNegro));
		panelBlanco.add(cancelarButton);
	
		buscarButton.addActionListener(this);
		limpiarButton.addActionListener(this);
		cancelarButton.addActionListener(this);
				
				
		// Agregamos el panel principal al JFrame que creamos al incio de la aplicación
		getContentPane().add(panelPrincipal);
		
	}
	
	
	public void tabla() {
		
		panelTablaScroll = new JScrollPane();
		panelTablaScroll.getViewport().setBackground(Color.WHITE);
		panelTablaScroll.setBorder(BorderFactory.createEmptyBorder()); 
		panelTablaScroll.setBounds(51, 266, 745, 251);
		
		
		tablaDatos = new JTable();
		tablaDatos.setGridColor(colorNegro);
		
		tablaDatos.setBackground(SystemColor.inactiveCaption);
		tablaDatos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelTablaScroll.setViewportView(tablaDatos);
			
		dtm = new DefaultTableModel(); 
		
		dtm.setColumnIdentifiers(titulosEncabezado);
			
		tablaDatos.setModel(dtm);
		
		detallesTabla();
		
		panelTablaScroll.setVisible(true);
		
		panelBlanco.add(panelTablaScroll);
		
	}
	
	
	public void detallesTabla() {
		
		// Me permite modificar el ancho de las columnas
		tablaDatos.getColumnModel().getColumn(0).setPreferredWidth(70);
		tablaDatos.getColumnModel().getColumn(1).setPreferredWidth(80);
		tablaDatos.getColumnModel().getColumn(2).setPreferredWidth(115);
		tablaDatos.getColumnModel().getColumn(3).setPreferredWidth(115);
		tablaDatos.getColumnModel().getColumn(4).setPreferredWidth(145);
		tablaDatos.getColumnModel().getColumn(5).setPreferredWidth(85);
		tablaDatos.getColumnModel().getColumn(6).setPreferredWidth(100);
								
		// Me permite centrar los datos
		DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
		tableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
							
		tablaDatos.getColumnModel().getColumn(0).setCellRenderer(tableCellRenderer);
		tablaDatos.getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
		tablaDatos.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		tablaDatos.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		
	}
	
	public void muestra(ControlConsultarCompra control, List <Empleado> empleados) {
		
		controlConsultarCompras = control;
		
		// Vaciamos todos los empleados en el comboBox
		DefaultComboBoxModel <String> comboBoxModel = new DefaultComboBoxModel <>();
		comboBoxModel.addElement("--Seleccione una opción--");
		for(Empleado empleado: empleados)
			comboBoxModel.addElement(empleado.getNombreCompleto());
		comboBoxEmpleados.setModel(comboBoxModel);
		
		limpiaTodo();
		
		setVisible(true);
	}
	
	
	public void muestraDialogoConMensaje(String mensaje ) {
		
		UIManager.put("OptionPane.background", new Color(184,199,218));
		UIManager.put("Panel.background", new Color(184,199,218));
		UIManager.put("Button.background", new Color(255,255,255));
		UIManager.put("Button.foreground", new Color(89, 126, 170));
		UIManager.put("Button.font", new Font(tipoLetra, Font.BOLD, 13));
		
		JLabel etiqueta = new JLabel(mensaje, SwingConstants.CENTER); 
		etiqueta.setFont(new Font(tipoLetra, Font.BOLD, 15)); 
		etiqueta.setForeground(new Color(255,255,255)); 
		
		JOptionPane.showMessageDialog(this ,etiqueta, "AVISO", JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	
	
	public void limpiaTodo() {
		
		dtm = new DefaultTableModel(); 
		
		dtm.setColumnIdentifiers(titulosEncabezado);
			
		tablaDatos.setModel(dtm);
		
		detallesTabla();
		
		textoFechaDesdeDC.setText("");
		textoFechaHastaDC.setText("");
		comboBoxEmpleados.setSelectedIndex(0);
		nombreProveedorTextField.setText("");
		nombreProductoTextField.setText("");
		totalGeneralComprasText.setText("");	
	
	}
	
	
	public void termina() {
		
		setVisible(false);
	
	}
	
	
	public void recolectaDatos() throws ParseException {
		
		String fechaDesde;
		String fechaHasta;
		int indexEmpleado;
		String nombreProveedor;
		String nombreProducto;
		
		fechaDesde = textoFechaDesdeDC.getText();
		fechaHasta = textoFechaHastaDC.getText();
		indexEmpleado = comboBoxEmpleados.getSelectedIndex();
		nombreProveedor = nombreProveedorTextField.getText();
		nombreProducto = nombreProductoTextField.getText();	
		
		controlConsultarCompras.consultarCompras(fechaDesde, fechaHasta, indexEmpleado, nombreProveedor, nombreProducto);
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == limpiarButton) {
			limpiaTodo();
		}else if(e.getSource() == cancelarButton){
			termina();
		}else if(e.getSource() == buscarButton) {
			try {
				recolectaDatos();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		
	}


	
	public void mostrarCompras(String[][] datos) {
		
		// Se vuelve a crear si pulsa Buscar más de 1 vez, de lo contrario, duplica la misma compra
		dtm = new DefaultTableModel(); 
		dtm.setColumnIdentifiers(titulosEncabezado);
		
		tablaDatos.setModel(dtm);
		
		if(datos.length == 0) {
			
			detallesTabla();
			totalGeneralComprasText.setText("");
			muestraDialogoConMensaje("No se encontró alguna compra con la información proporcionada.");
		
		}else {
		
			// Aqui empieza para poner la cifra de monto y venta total en formato de pesos con 2 decimales
			Locale formatoLocal = new Locale ("en", "UK");			
			NumberFormat formatoDecimal = NumberFormat.getInstance (formatoLocal);
			        
			double montoCaja;
			double totalCompra = 0;
			
			for (int i = 0; i < datos.length; i++) {
				// Se hace la suma del total
				montoCaja = Double.parseDouble(datos[i][6]);
				totalCompra = totalCompra + montoCaja;
				datos[i][6] = "$ " + formatoDecimal.format(montoCaja);
				
				// La información se agrega al modelo de la tabla
				dtm.addRow(datos[i]);
			}			
			
			detallesTabla();
			
			tablaDatos.setModel(dtm);
			
			totalGeneralComprasText.setText(String.valueOf("$ " + formatoDecimal.format(totalCompra)));
					
			panelTablaScroll.setVisible(true);
		}
	}
		
}
