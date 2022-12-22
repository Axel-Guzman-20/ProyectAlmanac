package mx.uam.ingsof.proyecto.presentacion.buscarProducto;

import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.springframework.stereotype.Component;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.SeccionCatalogo;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
@Component
public class VentanaBuscarProducto extends JFrame {

	private ControlBuscarProducto control;
	
	private JPanel contentPane;
	private JComboBox <String> comboBoxSeccionCatalogo; 
	private JTextField fechaText;
	private JTextField textFieldIdProducto;
	private JTextField textFieldPrecioMaximo;
	private JTextField textFieldMarca;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecioMinimo;
	private JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentanaBuscarProducto() {
		
		setTitle("Buscador de productos");
		setBounds(100, 100, 858, 646);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(89, 126, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane); 
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(38, 44, 765, 525);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscador de Productos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(255, 28, 254, 36);
		panel.add(lblNewLabel);
		
		JLabel lblCamposObligatorios = new JLabel("*Campos Obligatorios");
		lblCamposObligatorios.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCamposObligatorios.setForeground(new Color(204, 0, 0));
		lblCamposObligatorios.setBounds(120, 93, 134, 15);
		panel.add(lblCamposObligatorios);
		
		//Creamos los label y lo agregamos al panel blanco
				JLabel fechaLabel = new JLabel("Fecha:");
				fechaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				fechaLabel.setBounds(430, 89, 60, 25);
				panel.add(fechaLabel);
				
				//Creamos la caja de texto sin poder editar
				fechaText = new JTextField();
				fechaText.setFont(new Font("Tahoma", Font.PLAIN, 14));
				fechaText.setEditable(false);
				fechaText.setBackground(Color.WHITE);
				fechaText.setBorder(null);
				fechaText.setBounds(492, 89, 100, 25);
				panel.add(fechaText);
		
		JLabel lblSeccion = new JLabel("Sección *");
		lblSeccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeccion.setBounds(120, 144, 65, 15);
		panel.add(lblSeccion);
		
		comboBoxSeccionCatalogo = new JComboBox<String>();
		comboBoxSeccionCatalogo.setBounds(206, 142, 168, 22);
		panel.add(comboBoxSeccionCatalogo);
		
		JLabel lblIdProducto = new JLabel("Id Producto");
		lblIdProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdProducto.setBounds(120, 185, 78, 15);
		panel.add(lblIdProducto);
		
		textFieldIdProducto = new JTextField();
		textFieldIdProducto.setBounds(206, 184, 168, 20);
		panel.add(textFieldIdProducto);
		textFieldIdProducto.setColumns(10);
		
		JLabel lblPrecioMaximo = new JLabel("Precio Máximo");
		lblPrecioMaximo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecioMaximo.setBounds(120, 229, 95, 15);
		panel.add(lblPrecioMaximo);
		
		textFieldPrecioMaximo = new JTextField();
		textFieldPrecioMaximo.setColumns(10);
		textFieldPrecioMaximo.setBounds(225, 228, 149, 20);
		panel.add(textFieldPrecioMaximo);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMarca.setBounds(431, 146, 41, 15);
		panel.add(lblMarca);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setColumns(10);
		textFieldMarca.setBounds(492, 143, 168, 20);
		panel.add(textFieldMarca);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(431, 187, 54, 15);
		panel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(492, 184, 168, 20);
		panel.add(textFieldNombre);
		
		JLabel lblPrecioMinimo = new JLabel("Precio Mínimo");
		lblPrecioMinimo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecioMinimo.setBounds(431, 231, 89, 15);
		panel.add(lblPrecioMinimo);
		
		textFieldPrecioMinimo = new JTextField();
		textFieldPrecioMinimo.setColumns(10);
		textFieldPrecioMinimo.setBounds(526, 228, 134, 20);
		panel.add(textFieldPrecioMinimo);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(0, 158, 15));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBounds(265, 279, 89, 23);
		panel.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setBackground(new Color(89, 126, 170));
		btnLimpiar.setBounds(396, 279, 89, 23);
		panel.add(btnLimpiar);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setForeground(Color.WHITE);
		btnRegresar.setBackground(new Color(204, 0, 0));
		btnRegresar.setBounds(338, 491, 89, 23);
		panel.add(btnRegresar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBorder(BorderFactory.createEmptyBorder()); 
		scrollPane.setBounds(82, 336, 628, 112);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(SystemColor.inactiveCaption);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		/***********************************
		 * 
		 * 
		 * EVENTOS DE LOS BOTONES
		 * Buscar, Limpiar, Regresar
		 * 
		 ***********************************/
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				control.buscarProducto((String) comboBoxSeccionCatalogo.getSelectedItem(),textFieldIdProducto.getText(), textFieldNombre.getText(),
						textFieldMarca.getText(), textFieldPrecioMaximo.getText(),textFieldPrecioMinimo.getText());

			}
		});
		
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldIdProducto.setText("");
				textFieldPrecioMaximo.setText("");
				textFieldMarca.setText("");
				textFieldNombre.setText("");
				textFieldPrecioMinimo.setText("");
			}
		});
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.termina();
			}
		});
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void muestra(ControlBuscarProducto control, List <SeccionCatalogo> secciones, String fecha) {
		
		this.control = control;
		
		textFieldIdProducto.setText("");
		textFieldPrecioMaximo.setText("");
		textFieldMarca.setText("");
		textFieldNombre.setText("");
		textFieldPrecioMinimo.setText("");
		fechaText.setText(fecha);

		DefaultComboBoxModel <String> comboBoxModel = new DefaultComboBoxModel <>();
		
		for(SeccionCatalogo seccion:secciones) {
			comboBoxModel.addElement(seccion.getNombre());
		}
		
		comboBoxSeccionCatalogo.setModel(comboBoxModel);
		
		setVisible(true);
		
	}
	
	public void muestraProductosObtenidos( List <Producto> productos) {
		
		DefaultTableModel tableModel = new DefaultTableModel(); 
		tableModel.addColumn("ID PRODUCTO");
		tableModel.addColumn("PRODUCTO");
		tableModel.addColumn("SECCIÓN");
		tableModel.addColumn("MARCA");
		tableModel.addColumn("PRECIO");
		tableModel.addColumn("DESCUENTO");
		tableModel.addColumn("EXISTENCIA");
		
		for(Producto producto:productos) {
			
			String[] p = new String [7]; 
			
			p[0] = ""+ producto.getIdProducto(); 
			p[1] = ""+ producto.getNombre();
			p[2] = ""+ comboBoxSeccionCatalogo.getSelectedItem(); 
			p[3] = ""+ producto.getMarca(); 
			p[4] = ""+ producto.getPrecio(); 
			p[5] = ""+ producto.getDescuento(); 
			p[6] = ""+ producto.getExistencia(); 
			
			tableModel.addRow(p);
		}
		
		table.setModel(tableModel);
		setVisible(true);
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
}
