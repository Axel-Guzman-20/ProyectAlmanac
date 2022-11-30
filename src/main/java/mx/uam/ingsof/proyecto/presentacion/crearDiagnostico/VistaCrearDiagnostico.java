package mx.uam.ingsof.proyecto.presentacion.crearDiagnostico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class VistaCrearDiagnostico extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textFieldNombre;
	private JTextField textFieldMarca;
	private JTextField textFieldDescripcionDelEquipo;
	private JTextField textFieldReparacionesMantenimientosARealizarReparacionesMantenimientosARealizar;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaCrearDiagnostico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 877, 1120);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(89, 126, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(40, 42, 779, 680);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDiagnostico = new JLabel("Diagnostico de reparación/mantenimiento de equipos");
		lblDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblDiagnostico.setBounds(90, 11, 604, 36);
		panel.add(lblDiagnostico);
		
		JLabel lblCamposObligatorios = new JLabel("*Campos Obligatorios");
		lblCamposObligatorios.setForeground(new Color(204, 0, 0));
		lblCamposObligatorios.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCamposObligatorios.setBounds(120, 74, 134, 15);
		panel.add(lblCamposObligatorios);
		
		JLabel fechaLabel = new JLabel("Fecha:");
		fechaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fechaLabel.setBounds(506, 68, 60, 25);
		panel.add(fechaLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setBorder(null);
		textField.setBackground(Color.WHITE);
		textField.setBounds(560, 68, 100, 25);
		panel.add(textField);
		
		JLabel lblNombreDelEmpleado = new JLabel("Nombre del empleado*");
		lblNombreDelEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreDelEmpleado.setBounds(120, 109, 149, 15);
		panel.add(lblNombreDelEmpleado);
		
		JComboBox comboBoxNombreDelEmpleado = new JComboBox();
		comboBoxNombreDelEmpleado.setBounds(279, 104, 381, 22);
		panel.add(comboBoxNombreDelEmpleado);
		
		JLabel lblDatosGenerales = new JLabel("Datos Generales del Equipo ------------------------------------");
		lblDatosGenerales.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDatosGenerales.setBounds(120, 144, 540, 25);
		panel.add(lblDatosGenerales);
		
		JLabel lblNombre = new JLabel("Nombre *");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(120, 180, 71, 15);
		panel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(194, 180, 200, 20);
		panel.add(textFieldNombre);
		
		JLabel lblCategoria = new JLabel("Categoria *");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoria.setBounds(415, 178, 76, 19);
		panel.add(lblCategoria);
		
		JComboBox comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxCategoria.setBounds(506, 180, 154, 22);
		panel.add(comboBoxCategoria);
		
		JLabel lblMarca = new JLabel("Marca *");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMarca.setBounds(120, 219, 60, 14);
		panel.add(lblMarca);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldMarca.setColumns(10);
		textFieldMarca.setBounds(194, 211, 200, 20);
		panel.add(textFieldMarca);
		
		JLabel lblDescripcionDelEquipo = new JLabel("Descripcion del Equipo *");
		lblDescripcionDelEquipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcionDelEquipo.setBounds(120, 254, 161, 25);
		panel.add(lblDescripcionDelEquipo);
		
		textFieldDescripcionDelEquipo = new JTextField();
		textFieldDescripcionDelEquipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldDescripcionDelEquipo.setBounds(143, 285, 496, 101);
		panel.add(textFieldDescripcionDelEquipo);
		textFieldDescripcionDelEquipo.setColumns(10);
		
		JLabel lblReparacionesMantenimientosARealizar = new JLabel("Reparaciones/Mantenimientos a realizar *");
		lblReparacionesMantenimientosARealizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReparacionesMantenimientosARealizar.setBounds(120, 397, 258, 25);
		panel.add(lblReparacionesMantenimientosARealizar);
		
		textFieldReparacionesMantenimientosARealizarReparacionesMantenimientosARealizar = new JTextField();
		textFieldReparacionesMantenimientosARealizarReparacionesMantenimientosARealizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldReparacionesMantenimientosARealizarReparacionesMantenimientosARealizar.setColumns(10);
		textFieldReparacionesMantenimientosARealizarReparacionesMantenimientosARealizar.setBounds(143, 433, 496, 101);
		panel.add(textFieldReparacionesMantenimientosARealizarReparacionesMantenimientosARealizar);
	}
}
