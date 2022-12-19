package mx.uam.ingsof.proyecto.presentacion.HistorialCompra;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
@Component

/**
 * Ventana que mira el usuario para consultar las ventas registradas
 * 
 * @author AxelGuzman
 * 
 */

public class VistaHistorialCompra extends JFrame {

	private ControlHistorialCompra controlHistorialCompra;

	private Font fuente;

	private JPanel panelPrincipal;
	private JPanel panelBlanco;
	private JPanel tablaPanel;

	private Color colorFondo;

	private JDateChooser fechaDesdeDC;
	private JTextFieldDateEditor textoFechaDesdeDC;
	private JDateChooser fechaHastaDC;
	private JTextFieldDateEditor textoFechaHastaDC;

	private JComboBox<String> comboBoxClientes;
	private JTable table;
	private JButton buscarButton;
	private JButton limpiarButton;
	private JButton cancelarButton;

	private int anchoVentana = 940;
	private int altoVentana = 740;

	private DefaultTableModel tableModel;

	public VistaHistorialCompra() {

		setTitle("Consultar Historial de Compras de Clientes");

		getContentPane().setLayout(null);

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

		// Creamos el titulo y lo agregamos al panel blanco
		JLabel tituloLabel = new JLabel("Historial de Compras");
		tituloLabel.setFont(fuente);
		tituloLabel.setBounds(320, 15, 450, 30);
		panelBlanco.add(tituloLabel);

		// Para las label y las cajas de texto
		fuente = new Font("Tahoma", 0, 14);

		// Creamos los label y lo agregamos al panel blanco

		JLabel fechaInicioLabel = new JLabel("Fecha desde:");
		fechaInicioLabel.setFont(fuente);
		fechaInicioLabel.setBounds(50, 160, 90, 25);
		panelBlanco.add(fechaInicioLabel);

		// Se crea la caja y el icono para escoger la fecha
		fechaDesdeDC = new JDateChooser();
		fechaDesdeDC.setBounds(140, 160, 140, 25);
		fechaDesdeDC.setDateFormatString("dd/MM/yyyy");
		// Lo que hace es recuperar la TextFieldDate del JCalendar
		textoFechaDesdeDC = (JTextFieldDateEditor) fechaDesdeDC.getDateEditor();
		textoFechaDesdeDC.setEditable(false);
		textoFechaDesdeDC.setBackground(Color.WHITE);
		textoFechaDesdeDC.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textoFechaDesdeDC.setForeground(Color.BLACK);

		panelBlanco.add(fechaDesdeDC);

		// Creamos los label y lo agregamos al panel blanco
		JLabel fechaFinalLabel = new JLabel("Hasta:");
		fechaFinalLabel.setFont(fuente);
		fechaFinalLabel.setBounds(350, 160, 90, 25);
		panelBlanco.add(fechaFinalLabel);

		// Se crea la caja y el icono para escoger la fecha
		fechaHastaDC = new JDateChooser();
		fechaHastaDC.setBounds(410, 160, 140, 25);
		fechaHastaDC.setDateFormatString("dd/MM/yyyy");
		// Lo que hace es recuperar la TextFieldDate del JCalendar
		textoFechaHastaDC = (JTextFieldDateEditor) fechaHastaDC.getDateEditor();
		textoFechaHastaDC.setEditable(false);
		textoFechaHastaDC.setBackground(Color.WHITE);
		textoFechaHastaDC.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textoFechaHastaDC.setForeground(Color.BLACK);

		panelBlanco.add(fechaHastaDC);

		// creacion de la tabla
		table = new JTable();
		table.setBackground(SystemColor.inactiveCaption);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		tableModel = new DefaultTableModel();

		tableModel.addColumn("FECHA");
		tableModel.addColumn("NOMBRE PRODUCTO");
		tableModel.addColumn("CANTIDAD");
		tableModel.addColumn("PRECIO");
		tableModel.addColumn("PRECIO TOTAL");

		table.setModel(tableModel);

		// creamos un scrollpane y le añadimos la tabla creada
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(41, 250, 750, 301);
		panelBlanco.add(scroll);

		// Creamos los label y lo agregamos al panel blanco
		JLabel nombreClienteLabel = new JLabel("Nombre del Cliente:");
		nombreClienteLabel.setFont(fuente);
		nombreClienteLabel.setBounds(50, 70, 150, 30);
		panelBlanco.add(nombreClienteLabel);

		// Le damos su caja de texto
		comboBoxClientes = new JComboBox<String>();
		comboBoxClientes.setBounds(220, 70, 250, 30);
		panelBlanco.add(comboBoxClientes);

		int xBotones = 210, espacioEntreBotones = 160;
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

		// Acciones de los botones

		// boton buscar

		buscarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (comboBoxClientes.getSelectedIndex() != 0) {

					int idcliente = comboBoxClientes.getSelectedIndex();
					String fechaInicio = textoFechaDesdeDC.getText();
					String fechaFinal = textoFechaHastaDC.getText();
					try {
						controlHistorialCompra.buscarHistorial(idcliente, fechaInicio, fechaFinal);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else
					muestraDialogoConMensaje("Seleccione a un cliente");

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

				for (int i = 0; i < table.getRowCount(); i++) {
					tableModel.removeRow(i);
					i -= 1;
				}
				controlHistorialCompra.cierraVentana();
			}
		});

		// Agregamos el panel principal al JFrame que creamos al incio de la aplicación
		getContentPane().add(panelPrincipal);

	}

	public void muestra(ControlHistorialCompra control, List<Cliente> clientes) {

		controlHistorialCompra = control;

		llenaDatosComboBox(clientes);

		limpiaTodo();

		setVisible(true);
	}

	public void termina() {

		setVisible(false);

	}

	public void muestraDialogoConMensaje(String mensaje) {

		UIManager.put("OptionPane.background", new Color(184, 199, 218));
		UIManager.put("Panel.background", new Color(184, 199, 218));
		UIManager.put("Button.background", new Color(255, 255, 255));
		UIManager.put("Button.foreground", new Color(89, 126, 170));
		UIManager.put("Button.font", new Font("Tahoma", Font.BOLD, 13));

		JLabel etiqueta = new JLabel(mensaje, JLabel.CENTER);
		etiqueta.setFont(new Font("Tahoma", Font.BOLD, 15));
		etiqueta.setForeground(new Color(255, 255, 255));

		JOptionPane.showMessageDialog(this, etiqueta, "AVISO", JOptionPane.INFORMATION_MESSAGE);

	}

	public void llenaDatosComboBox(List<Cliente> clientes) {

		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		comboBoxModel.addElement("--Seleccione una opción");

		comboBoxModel = new DefaultComboBoxModel<>();
		comboBoxModel.addElement("--Seleccione una opción");
		for (Cliente cliente : clientes)
			comboBoxModel.addElement(Long.toString(cliente.getIdCliente()) + "  " + cliente.getNombreCompleto());
		comboBoxClientes.setModel(comboBoxModel);

	}

	public void limpiaTodo() {

		textoFechaDesdeDC.setText("");
		textoFechaHastaDC.setText("");
		comboBoxClientes.setSelectedIndex(0);
		for (int i = 0; i < table.getRowCount(); i++) {
			tableModel.removeRow(i);
			i -= 1;
		}

	}

	public void limpiaTabla() {

		for (int i = 0; i < table.getRowCount(); i++) {
			tableModel.removeRow(i);
			i -= 1;
		}
	}

	public void mostrarHistorial(String[][] datos) {

		// Se vuelve a crear si pulsa Buscar más de 1 vez, de lo contrario, duplica la
		// misma compra
		tableModel = new DefaultTableModel();
		tableModel.addColumn("FECHA");
		tableModel.addColumn("NOMBRE PRODUCTO");
		tableModel.addColumn("CANTIDAD");
		tableModel.addColumn("PRECIO");
		tableModel.addColumn("PRECIO TOTAL");

		table.setModel(tableModel);
		for (int i = 0; i < datos.length; i++) {
			tableModel.addRow(datos[i]);
			for (int j = 0; j < 5; j++) {

				// System.out.println("\t "+datos[i][j]);

				// System.out.print("");

			}

		}

	}
}
