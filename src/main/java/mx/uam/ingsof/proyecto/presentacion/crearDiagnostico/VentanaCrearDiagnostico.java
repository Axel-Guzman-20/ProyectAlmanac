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
import javax.swing.JRadioButton;
import javax.swing.JButton;

@SuppressWarnings("serial")
@Component
public class VentanaCrearDiagnostico extends JFrame {

	private JPanel contentPane;
	
	JScrollPane scrollVentana; 
	private JTextField textField;
	private JTextField textFieldNombre;
	private JTextField textFieldMarca;
	private JTextField textFieldDescripcionDelEquipo;
	private JTextField textFieldReparacionesMantenimientosARealizar;
	private JTextField textFieldPiezasRequeridas;
	private JTextField textFieldObservacionesAdicionales;

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
		panel.setPreferredSize(new Dimension(0,1000));
//		contentPane.add(panel);
		
		JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(41, 43, 780, 451);
		contentPane.add(scroll);
		
		JLabel lblDiagnostico = new JLabel("Diagnostico de reparación/mantenimiento de equipos");
		lblDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblDiagnostico.setBounds(88, 27, 604, 36);
		panel.add(lblDiagnostico);
		
		JLabel lblCamposObligatorios = new JLabel("*Campos Obligatorios");
		lblCamposObligatorios.setForeground(new Color(204, 0, 0));
		lblCamposObligatorios.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCamposObligatorios.setBounds(120, 114, 134, 15);
		panel.add(lblCamposObligatorios);
		
		JLabel fechaLabel = new JLabel("Fecha:");
		fechaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fechaLabel.setBounds(506, 80, 60, 25);
		panel.add(fechaLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setBorder(null);
		textField.setBackground(Color.WHITE);
		textField.setBounds(560, 80, 100, 25);
		panel.add(textField);
		
		JLabel lblNombreDelEmpleado = new JLabel("Nombre del empleado*");
		lblNombreDelEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreDelEmpleado.setBounds(120, 158, 149, 15);
		panel.add(lblNombreDelEmpleado);
		
		JComboBox comboBoxNombreDelEmpleado = new JComboBox();
		comboBoxNombreDelEmpleado.setBounds(279, 151, 381, 22);
		panel.add(comboBoxNombreDelEmpleado);
		
		JLabel lblDatosGenerales = new JLabel("Datos Generales del Equipo ------------------------------------");
		lblDatosGenerales.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDatosGenerales.setBounds(120, 196, 540, 25);
		panel.add(lblDatosGenerales);
		
		JLabel lblNombre = new JLabel("Nombre *");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(120, 240, 71, 15);
		panel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(194, 238, 200, 20);
		panel.add(textFieldNombre);
		
		JLabel lblCategoria = new JLabel("Categoria *");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoria.setBounds(419, 240, 76, 19);
		panel.add(lblCategoria);
		
		JComboBox comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxCategoria.setBounds(506, 237, 154, 22);
		panel.add(comboBoxCategoria);
		
		JLabel lblMarca = new JLabel("Marca *");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMarca.setBounds(120, 274, 60, 14);
		panel.add(lblMarca);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldMarca.setColumns(10);
		textFieldMarca.setBounds(194, 272, 200, 20);
		panel.add(textFieldMarca);
		
		JLabel lblDescripcionDelEquipo = new JLabel("Descripcion del Equipo *");
		lblDescripcionDelEquipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcionDelEquipo.setBounds(120, 307, 161, 25);
		panel.add(lblDescripcionDelEquipo);
		
		textFieldDescripcionDelEquipo = new JTextField();
		textFieldDescripcionDelEquipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldDescripcionDelEquipo.setColumns(10);
		textFieldDescripcionDelEquipo.setBounds(143, 343, 496, 101);
		panel.add(textFieldDescripcionDelEquipo);
		
		JLabel lblReparacionesMantenimientosARealizar = new JLabel("Reparaciones/Mantenimientos a realizar *");
		lblReparacionesMantenimientosARealizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReparacionesMantenimientosARealizar.setBounds(120, 460, 258, 25);
		panel.add(lblReparacionesMantenimientosARealizar);
		
		textFieldReparacionesMantenimientosARealizar = new JTextField();
		textFieldReparacionesMantenimientosARealizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldReparacionesMantenimientosARealizar.setColumns(10);
		textFieldReparacionesMantenimientosARealizar.setBounds(143, 496, 496, 101);
		panel.add(textFieldReparacionesMantenimientosARealizar);
		
		JLabel lblTipoDeReparacionMantenimiento = new JLabel("Tipo de Reparación/Mantenimiento *");
		lblTipoDeReparacionMantenimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoDeReparacionMantenimiento.setBounds(120, 613, 238, 25);
		panel.add(lblTipoDeReparacionMantenimiento);
		
		JRadioButton rdbtnPreventivo = new JRadioButton("Preventivo");
		rdbtnPreventivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnPreventivo.setSelected(true);
		rdbtnPreventivo.setBackground(Color.WHITE);
		rdbtnPreventivo.setBounds(370, 615, 91, 23);
		panel.add(rdbtnPreventivo);
		
		JRadioButton rdbtnCorrectivo = new JRadioButton("Correctivo");
		rdbtnCorrectivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnCorrectivo.setBackground(Color.WHITE);
		rdbtnCorrectivo.setBounds(470, 615, 91, 23);
		panel.add(rdbtnCorrectivo);
		
		JLabel lblPiezasRequeridas = new JLabel("Piezas Requeridas ");
		lblPiezasRequeridas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPiezasRequeridas.setBounds(120, 647, 116, 25);
		panel.add(lblPiezasRequeridas);
		
		textFieldPiezasRequeridas = new JTextField();
		textFieldPiezasRequeridas.setEditable(false);
		textFieldPiezasRequeridas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldPiezasRequeridas.setColumns(10);
		textFieldPiezasRequeridas.setBounds(143, 683, 496, 101);
		panel.add(textFieldPiezasRequeridas);
		
		JLabel lblObservacionesAdicionales = new JLabel("Observaciones Adicionales");
		lblObservacionesAdicionales.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblObservacionesAdicionales.setBounds(120, 800, 161, 25);
		panel.add(lblObservacionesAdicionales);
		
		textFieldObservacionesAdicionales = new JTextField();
		textFieldObservacionesAdicionales.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldObservacionesAdicionales.setColumns(10);
		textFieldObservacionesAdicionales.setBounds(143, 836, 496, 101);
		panel.add(textFieldObservacionesAdicionales);
		
		JButton btnBuscar = new JButton("Crear");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setBackground(new Color(0, 158, 15));
		btnBuscar.setBounds(345, 953, 90, 23);
		panel.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setBackground(new Color(89, 126, 170));
		btnLimpiar.setBounds(470, 953, 90, 23);
		panel.add(btnLimpiar);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setForeground(Color.WHITE);
		btnRegresar.setBackground(new Color(204, 0, 0));
		btnRegresar.setBounds(220, 953, 90, 23);
		panel.add(btnRegresar);
		
		
	}
	
	public void muestra(ControlCrearDiagnostico control) {
		
		setVisible(true);
		
	}
}
