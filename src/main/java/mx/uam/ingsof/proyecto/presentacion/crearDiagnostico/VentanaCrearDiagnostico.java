package mx.uam.ingsof.proyecto.presentacion.crearDiagnostico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.modelo.CategoriaDiagnostico;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

@SuppressWarnings("serial")
@Component
public class VentanaCrearDiagnostico extends JFrame {

	private JPanel contentPane;
	
	JScrollPane scrollVentana; 
	private JTextField textFieldFecha;
	private JTextField textFieldNombre;
	private JTextField textFieldMarca;
	private JTextField textFieldDescripcionDelEquipo;
	private JTextField textFieldReparacionesMantenimientosARealizar;
	private JTextField textFieldPiezasRequeridas;
	private JTextField textFieldObservacionesAdicionales;
	private JComboBox <String> comboBoxNombreDelEmpleado; 
	private JComboBox <String> comboBoxCategoria; 
	private JRadioButton rdbtnPreventivo; 
	private JRadioButton rdbtnCorrectivo; 
	private ButtonGroup bg = new ButtonGroup();
	private ControlCrearDiagnostico control;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentanaCrearDiagnostico() {
		
		setTitle("Crear Diagnostico");
		setBounds(100, 100, 877, 578);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(89, 126, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(0,1000));
		
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
		
		textFieldFecha = new JTextField();
		textFieldFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldFecha.setEditable(false);
		textFieldFecha.setBorder(null);
		textFieldFecha.setBackground(Color.WHITE);
		textFieldFecha.setBounds(560, 80, 100, 25);
		panel.add(textFieldFecha);
		
		JLabel lblNombreDelEmpleado = new JLabel("Nombre del empleado*");
		lblNombreDelEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreDelEmpleado.setBounds(120, 158, 149, 15);
		panel.add(lblNombreDelEmpleado);
		
		comboBoxNombreDelEmpleado = new JComboBox<>();
		comboBoxNombreDelEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
		
		comboBoxCategoria = new JComboBox<>();
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
		
		rdbtnPreventivo = new JRadioButton("Preventivo",true);
		rdbtnPreventivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnPreventivo.setBackground(Color.WHITE);
		rdbtnPreventivo.setBounds(370, 615, 91, 23);
		panel.add(rdbtnPreventivo);
		
		rdbtnCorrectivo = new JRadioButton("Correctivo");
		rdbtnCorrectivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnCorrectivo.setBackground(Color.WHITE);
		rdbtnCorrectivo.setBounds(470, 615, 91, 23);
		panel.add(rdbtnCorrectivo);
		
		bg.add(rdbtnPreventivo); 
		bg.add(rdbtnCorrectivo); 
		
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
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setBackground(new Color(0, 158, 15));
		btnCrear.setBounds(345, 953, 90, 23);
		panel.add(btnCrear);
		
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
		
		/***********************************
		 * 
		 * 
		 * EVENTOS DE LOS RADIOBUTTONS
		 * Preventivo, Correctivo
		 * 
		 ***********************************/
		
		rdbtnPreventivo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldPiezasRequeridas.setText("");
				textFieldPiezasRequeridas.setEditable(false);
			}
		});
		
		rdbtnCorrectivo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldPiezasRequeridas.setText("");
				textFieldPiezasRequeridas.setEditable(true);
			}
		});
		
		/***********************************
		 * 
		 * 
		 * EVENTOS DE LOS BOTONES
		 * Buscar, Limpiar, Regresar
		 * 
		 ***********************************/
		
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validaCampos()) {

					if (rdbtnCorrectivo.isSelected()) {
						control.crearDiagnostico((String) comboBoxNombreDelEmpleado.getSelectedItem(),
								textFieldNombre.getText(), (String) comboBoxCategoria.getSelectedItem(),
								textFieldMarca.getText(), textFieldDescripcionDelEquipo.getText(),
								textFieldReparacionesMantenimientosARealizar.getText(), "Correctivo",
								textFieldPiezasRequeridas.getText(), textFieldObservacionesAdicionales.getText());
					} else {
						control.crearDiagnostico((String) comboBoxNombreDelEmpleado.getSelectedItem(),
								textFieldNombre.getText(), (String) comboBoxCategoria.getSelectedItem(),
								textFieldMarca.getText(), textFieldDescripcionDelEquipo.getText(),
								textFieldReparacionesMantenimientosARealizar.getText(), "Preventivo",
								textFieldPiezasRequeridas.getText(), textFieldObservacionesAdicionales.getText());
					}
				}
			}
		});
		
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpiaCampos(); 
			}
		});
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.termina();
			}
		});
		
		
	}
	
	/***********************************
	 * 
	 * Limpieza de campos
	 * 
	 ***********************************/
	public void limpiaCampos() {
		
		textFieldNombre.setText("");
		textFieldMarca.setText("");
		textFieldDescripcionDelEquipo.setText("");
		textFieldReparacionesMantenimientosARealizar.setText("");
		textFieldPiezasRequeridas.setText("");
		textFieldObservacionesAdicionales.setText("");
		
		comboBoxNombreDelEmpleado.setSelectedItem("      ----------------------- Seleccione una opción -----------------------");
		comboBoxCategoria.setSelectedItem(" Seleccione una opción");
		
		rdbtnPreventivo.setSelected(true);
		textFieldPiezasRequeridas.setEditable(false);
	}

	/***********************************
	 * 
	 * Validación de campos
	 * 
	 ***********************************/
	public boolean validaCampos() {

		if ((comboBoxNombreDelEmpleado.getSelectedItem().equals(null))
				|| (comboBoxCategoria.getSelectedItem().equals(null)) || textFieldNombre.getText().equals(null)
				|| textFieldMarca.getText().equals(null) || textFieldDescripcionDelEquipo.getText().equals(null)
				|| textFieldReparacionesMantenimientosARealizar.getText().equals(null)) {
			muestraDialogoConMensaje("Los campos no pueden ser nulos");
			return false;
		} else {
			if (rdbtnCorrectivo.isSelected() && textFieldPiezasRequeridas.getText().equals("")) {
				muestraDialogoConMensaje("Ingresar las piezas requeridas.");
				return false;

			} else {
				if ((comboBoxNombreDelEmpleado.getSelectedItem()
						.equals("      ----------------------- Seleccione una opción -----------------------"))
						|| (comboBoxCategoria.getSelectedItem().equals(" Seleccione una opción"))
						|| textFieldNombre.getText().equals("") || textFieldMarca.getText().equals("")
						|| textFieldDescripcionDelEquipo.getText().equals("")
						|| textFieldReparacionesMantenimientosARealizar.getText().equals("")) {
					muestraDialogoConMensaje("Campos vacíos, rellena la información marcada con *");
					return false;
				} else {
					return true;
				}
			}
		}
	}
	
	public void muestra(ControlCrearDiagnostico control, List <CategoriaDiagnostico> categorias, List<Empleado> empleados, String fecha) {
		
		this.control = control; 
		
		textFieldNombre.setText("");
		textFieldMarca.setText("");
		textFieldDescripcionDelEquipo.setText("");
		textFieldReparacionesMantenimientosARealizar.setText("");
		textFieldPiezasRequeridas.setText("");
		textFieldObservacionesAdicionales.setText("");
		textFieldFecha.setText(fecha);
		
		
		DefaultComboBoxModel <String> comboBoxModelEmpleados = new DefaultComboBoxModel <>();
		
		comboBoxModelEmpleados.addElement("      ----------------------- Seleccione una opción -----------------------");
		for(Empleado empleado:empleados) {
			comboBoxModelEmpleados.addElement(empleado.getNombreCompleto());
		}
		
		comboBoxNombreDelEmpleado.setModel(comboBoxModelEmpleados);
		
		DefaultComboBoxModel <String> comboBoxModelCategorias = new DefaultComboBoxModel <>();
		
		comboBoxModelCategorias.addElement(" Seleccione una opción");
		for(CategoriaDiagnostico categoria:categorias) {
			comboBoxModelCategorias.addElement(categoria.getNombre());
		}
		
		comboBoxCategoria.setModel(comboBoxModelCategorias);
		
		rdbtnPreventivo.setSelected(true);
		textFieldPiezasRequeridas.setEditable(false);
		
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
