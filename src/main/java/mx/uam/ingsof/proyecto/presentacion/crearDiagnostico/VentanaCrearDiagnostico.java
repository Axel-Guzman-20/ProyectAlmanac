package mx.uam.ingsof.proyecto.presentacion.crearDiagnostico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
@Component
public class VentanaCrearDiagnostico extends JFrame {

	private JPanel contentPane;
	
	JScrollPane scrollVentana; 
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentanaCrearDiagnostico() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 877, 578);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(89, 126, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
//		panel.setBounds(41, 43, 779, 451);
		panel.setPreferredSize(new Dimension(0,1500));
//		contentPane.add(panel);
		
		JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(41, 43, 779, 451);
		contentPane.add(scroll);
		
		JLabel lblDiagnostico = new JLabel("Diagnostico de reparación/mantenimiento de equipos");
		lblDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblDiagnostico.setBounds(88, 27, 604, 36);
		panel.add(lblDiagnostico);
		
		JLabel lblCamposObligatorios = new JLabel("*Campos Obligatorios");
		lblCamposObligatorios.setForeground(new Color(204, 0, 0));
		lblCamposObligatorios.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCamposObligatorios.setBounds(120, 95, 134, 15);
		panel.add(lblCamposObligatorios);
		
		JLabel fechaLabel = new JLabel("Fecha:");
		fechaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fechaLabel.setBounds(505, 74, 60, 25);
		panel.add(fechaLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setBorder(null);
		textField.setBackground(Color.WHITE);
		textField.setBounds(560, 74, 100, 25);
		panel.add(textField);
		
		JLabel lblNombreDelEmpleado = new JLabel("Nombre del empleado*");
		lblNombreDelEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreDelEmpleado.setBounds(120, 130, 149, 15);
		panel.add(lblNombreDelEmpleado);
		
		JComboBox comboBoxNombreDelEmpleado = new JComboBox();
		comboBoxNombreDelEmpleado.setBounds(279, 128, 381, 22);
		panel.add(comboBoxNombreDelEmpleado);
		
		JLabel lblDatosGenerales = new JLabel("Datos Generales del Equipo ------------------------------------");
		lblDatosGenerales.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDatosGenerales.setBounds(120, 172, 540, 25);
		panel.add(lblDatosGenerales);
		
		JLabel lblNombre = new JLabel("Nombre *");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(120, 213, 71, 15);
		panel.add(lblNombre);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(194, 239, 200, 20);
		panel.add(textField_1);
		
		JLabel lblCategoria = new JLabel("Categoria *");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoria.setBounds(419, 211, 76, 19);
		panel.add(lblCategoria);
		
		JComboBox comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxCategoria.setBounds(505, 210, 154, 22);
		panel.add(comboBoxCategoria);
		
		JLabel lblMarca = new JLabel("Marca *");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMarca.setBounds(120, 239, 60, 14);
		panel.add(lblMarca);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(194, 211, 200, 20);
		panel.add(textField_2);
		
		JLabel lblDescripcionDelEquipo = new JLabel("Descripcion del Equipo *");
		lblDescripcionDelEquipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcionDelEquipo.setBounds(120, 280, 161, 25);
		panel.add(lblDescripcionDelEquipo);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(143, 310, 496, 101);
		panel.add(textField_3);
		
		JLabel lblReparacionesMantenimientosARealizar = new JLabel("Reparaciones/Mantenimientos a realizar *");
		lblReparacionesMantenimientosARealizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReparacionesMantenimientosARealizar.setBounds(120, 421, 258, 25);
		panel.add(lblReparacionesMantenimientosARealizar);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(143, 450, 496, 101);
		panel.add(textField_4);
		
		
	}
	
	public void muestra(ControlCrearDiagnostico control) {
		
		setVisible(true);
		
	}
}
