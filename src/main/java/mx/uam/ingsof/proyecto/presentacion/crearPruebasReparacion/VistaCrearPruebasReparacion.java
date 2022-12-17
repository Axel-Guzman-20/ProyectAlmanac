package mx.uam.ingsof.proyecto.presentacion.crearPruebasReparacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.modelo.CategoriaDiagnostico;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;
import mx.uam.ingsof.proyecto.negocio.modelo.ReparacionMantenimiento;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
@Component
public class VistaCrearPruebasReparacion extends JFrame {

	private JPanel contentPane;
	
	JScrollPane scrollVentana; 
	private JTextField textFieldFecha;
	private JTextField textFieldOtrasPruebas;
	private JTextField textFieldObservacionesAdicionales;
	private JComboBox <String> comboBoxNombreDelEmpleado; 
	private JComboBox <String> comboBoxCategoria; 
	private JComboBox<String> comboBoxNombreEquipo;
	private JCheckBox chckbxPrueba1; 
	private JCheckBox chckbxPrueba2;
	private JCheckBox chckbxPrueba3;
	private JCheckBox chckbxPrueba4;
	private JCheckBox chckbxPrueba5;
	private JCheckBox chckbxOtras;
	private List <String> pruebas;
	private DefaultComboBoxModel <String> comboBoxModelEmpleados; 
	private DefaultComboBoxModel <String> comboBoxModelCategorias; 
	private DefaultComboBoxModel<String> comboBoxModelNombreEquipo;
	private ButtonGroup bg = new ButtonGroup();
	private ControlCrearPruebasReparacion control;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaCrearPruebasReparacion() {
		
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
		panel.setPreferredSize(new Dimension(0, 850));
		
		JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(41, 43, 780, 451);
		contentPane.add(scroll);
		
		JLabel lblPruebas = new JLabel("Pruebas de reparación/mantenimiento de equipos");
		lblPruebas.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblPruebas.setBounds(88, 27, 604, 36);
		panel.add(lblPruebas);
		
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
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1, 1);
		panel.add(desktopPane);
		
		JLabel lblNombreEquipo = new JLabel("Nombre del Equipo *");
		lblNombreEquipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreEquipo.setBounds(372, 242, 134, 15);
		panel.add(lblNombreEquipo);
		
		JLabel lblCategoria = new JLabel("Categoria *");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoria.setBounds(120, 240, 76, 19);
		panel.add(lblCategoria);
		
		comboBoxCategoria = new JComboBox<>();
		comboBoxCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxCategoria.setBounds(200, 237, 154, 22);
		panel.add(comboBoxCategoria);
		
		JLabel lblPruebasEquipo = new JLabel("Pruebas del Equipo Realizadas  --------------------------------");
		lblPruebasEquipo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPruebasEquipo.setBounds(120, 284, 540, 25);
		panel.add(lblPruebasEquipo);
		
		textFieldOtrasPruebas = new JTextField();
		textFieldOtrasPruebas.setEditable(false);
		textFieldOtrasPruebas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldOtrasPruebas.setColumns(10);
		textFieldOtrasPruebas.setBounds(164, 530, 496, 101);
		panel.add(textFieldOtrasPruebas);
		
		JLabel lblObservacionesAdicionales = new JLabel("Observaciones Adicionales");
		lblObservacionesAdicionales.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblObservacionesAdicionales.setBounds(143, 653, 161, 25);
		panel.add(lblObservacionesAdicionales);
		
		textFieldObservacionesAdicionales = new JTextField();
		textFieldObservacionesAdicionales.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldObservacionesAdicionales.setColumns(10);
		textFieldObservacionesAdicionales.setBounds(164, 689, 496, 101);
		panel.add(textFieldObservacionesAdicionales);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setBackground(new Color(0, 158, 15));
		btnCrear.setBounds(345, 806, 90, 23);
		panel.add(btnCrear);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setBackground(new Color(89, 126, 170));
		btnLimpiar.setBounds(470, 806, 90, 23);
		panel.add(btnLimpiar);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setForeground(Color.WHITE);
		btnRegresar.setBackground(new Color(204, 0, 0));
		btnRegresar.setBounds(220, 806, 90, 23);
		panel.add(btnRegresar);
		
		comboBoxNombreEquipo = new JComboBox<String>();
		comboBoxNombreEquipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxNombreEquipo.setBounds(506, 239, 154, 22);
		panel.add(comboBoxNombreEquipo);
		
		chckbxPrueba1 = new JCheckBox("Correcto funcionamiento de tarjetas logicas, poder y alto voltaje.");
		chckbxPrueba1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxPrueba1.setBackground(Color.WHITE);
		chckbxPrueba1.setBounds(143, 326, 517, 23);
		panel.add(chckbxPrueba1);
		
		chckbxPrueba2 = new JCheckBox("Correcto funcionamiento de resistencias.");
		chckbxPrueba2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxPrueba2.setBackground(Color.WHITE);
		chckbxPrueba2.setBounds(143, 360, 517, 23);
		panel.add(chckbxPrueba2);
		
		chckbxPrueba3 = new JCheckBox("Correcto funcionamiento del elemento termino.");
		chckbxPrueba3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxPrueba3.setBackground(Color.WHITE);
		chckbxPrueba3.setBounds(143, 394, 517, 23);
		panel.add(chckbxPrueba3);
		
		chckbxPrueba4 = new JCheckBox("Correcto funcionamiento de gomas de arrastre.");
		chckbxPrueba4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxPrueba4.setBackground(Color.WHITE);
		chckbxPrueba4.setBounds(143, 428, 517, 23);
		panel.add(chckbxPrueba4);
		
		chckbxPrueba5 = new JCheckBox("Correcto funcionamiento de sensores.");
		chckbxPrueba5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxPrueba5.setBackground(Color.WHITE);
		chckbxPrueba5.setBounds(143, 462, 517, 23);
		panel.add(chckbxPrueba5);
		
		chckbxOtras = new JCheckBox("Otras:");
		chckbxOtras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxOtras.setBackground(Color.WHITE);
		chckbxOtras.setBounds(143, 496, 97, 23);
		panel.add(chckbxOtras);
		
		/***********************************
		 * 
		 * 
		 * EVENTOS DE LOS BOTONES
		 * Buscar, Limpiar, Regresar, checkBox, comboBox
		 * 
		 ***********************************/
		
		chckbxOtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxOtras.isSelected()) {
					textFieldOtrasPruebas.setText("");
					textFieldOtrasPruebas.setEditable(true);
				}else {

					textFieldOtrasPruebas.setText("");
					textFieldOtrasPruebas.setEditable(false);
				}
			}
		}); 
		
		comboBoxCategoria.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!comboBoxCategoria.getSelectedItem().equals(" Seleccione una opción")) {
					List<ReparacionMantenimiento> diagnosticos = control
							.buscarDiagnosticos((String) comboBoxCategoria.getSelectedItem());

					if (diagnosticos.size() != 0) {
						comboBoxModelNombreEquipo = new DefaultComboBoxModel<>();

						comboBoxModelNombreEquipo.addElement(" Seleccione una opción");
						for (ReparacionMantenimiento diagnostico : diagnosticos) {
							comboBoxModelNombreEquipo.addElement(diagnostico.getNombreEquipo());
						}

						comboBoxNombreEquipo.setModel(comboBoxModelNombreEquipo);

						comboBoxNombreEquipo.setEnabled(true);
					} else {
						
						comboBoxModelNombreEquipo = new DefaultComboBoxModel<>();
						comboBoxModelNombreEquipo.addElement("");
						comboBoxNombreEquipo.setModel(comboBoxModelNombreEquipo);
						muestraDialogoConMensaje("No hay diagnosticos en la seccion " + (String) comboBoxCategoria.getSelectedItem());
						comboBoxNombreEquipo.setEnabled(false);
					}
				} else {
					
					chckbxPrueba1.setSelected(false); 
					chckbxPrueba2.setSelected(false); 
					chckbxPrueba3.setSelected(false); 
					chckbxPrueba4.setSelected(false); 
					chckbxPrueba5.setSelected(false); 
					chckbxOtras.setSelected(false); 
					
					chckbxPrueba1.setEnabled(false); 
					chckbxPrueba2.setEnabled(false); 
					chckbxPrueba3.setEnabled(false); 
					chckbxPrueba4.setEnabled(false); 
					chckbxPrueba5.setEnabled(false); 
					chckbxOtras.setEnabled(false);
					
					textFieldOtrasPruebas.setEditable(false);
					
					comboBoxModelNombreEquipo = new DefaultComboBoxModel<>();
					comboBoxModelNombreEquipo.addElement("");
					comboBoxNombreEquipo.setModel(comboBoxModelNombreEquipo);
					comboBoxNombreEquipo.setEnabled(false);
				}
			}
		}); 
		
		comboBoxNombreEquipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!comboBoxCategoria.getSelectedItem().equals(" Seleccione una opción") &&
					!comboBoxNombreDelEmpleado.getSelectedItem().equals("      ----------------------- Seleccione una opción -----------------------") &&
					!comboBoxNombreEquipo.getSelectedItem().equals(" Seleccione una opción")) {
					
					chckbxPrueba1.setEnabled(true); 
					chckbxPrueba2.setEnabled(true); 
					chckbxPrueba3.setEnabled(true); 
					chckbxPrueba4.setEnabled(true); 
					chckbxPrueba5.setEnabled(true); 
					chckbxOtras.setEnabled(true);
				}else {
					
					chckbxPrueba1.setSelected(false); 
					chckbxPrueba2.setSelected(false); 
					chckbxPrueba3.setSelected(false); 
					chckbxPrueba4.setSelected(false); 
					chckbxPrueba5.setSelected(false); 
					chckbxOtras.setSelected(false); 
					
					chckbxPrueba1.setEnabled(false); 
					chckbxPrueba2.setEnabled(false); 
					chckbxPrueba3.setEnabled(false); 
					chckbxPrueba4.setEnabled(false); 
					chckbxPrueba5.setEnabled(false); 
					chckbxOtras.setEnabled(false);
					
					textFieldOtrasPruebas.setEditable(false);
				}
			}
		}); 
		
		comboBoxNombreDelEmpleado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!comboBoxCategoria.getSelectedItem().equals(" Seleccione una opción") &&
					!comboBoxNombreDelEmpleado.getSelectedItem().equals("      ----------------------- Seleccione una opción -----------------------") &&
					!comboBoxNombreEquipo.getSelectedItem().equals(" Seleccione una opción")) {
					
					chckbxPrueba1.setEnabled(true); 
					chckbxPrueba2.setEnabled(true); 
					chckbxPrueba3.setEnabled(true); 
					chckbxPrueba4.setEnabled(true); 
					chckbxPrueba5.setEnabled(true); 
					chckbxOtras.setEnabled(true);
				}else {
					
					chckbxPrueba1.setSelected(false); 
					chckbxPrueba2.setSelected(false); 
					chckbxPrueba3.setSelected(false); 
					chckbxPrueba4.setSelected(false); 
					chckbxPrueba5.setSelected(false); 
					chckbxOtras.setSelected(false); 
					
					chckbxPrueba1.setEnabled(false); 
					chckbxPrueba2.setEnabled(false); 
					chckbxPrueba3.setEnabled(false); 
					chckbxPrueba4.setEnabled(false); 
					chckbxPrueba5.setEnabled(false); 
					chckbxOtras.setEnabled(false);
					
					textFieldOtrasPruebas.setEditable(false);
				}
			}
		});
		
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				listaDePruebas();

				if (validaCampos() && pruebas.size()>0) {

					control.realizarPruebasReparacion((String) comboBoxNombreDelEmpleado.getSelectedItem(),
							(String) comboBoxCategoria.getSelectedItem(),
							(String) comboBoxNombreEquipo.getSelectedItem(), pruebas,
							textFieldObservacionesAdicionales.getText());

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
		
		textFieldObservacionesAdicionales.setText("");
		textFieldOtrasPruebas.setText("");
		
		comboBoxNombreDelEmpleado.setSelectedItem("      ----------------------- Seleccione una opción -----------------------");
		comboBoxCategoria.setSelectedItem(" Seleccione una opción");
		
		comboBoxModelNombreEquipo = new DefaultComboBoxModel<>();
		comboBoxModelNombreEquipo.addElement("");
		comboBoxNombreEquipo.setModel(comboBoxModelNombreEquipo);
		
		chckbxPrueba1.setSelected(false); 
		chckbxPrueba2.setSelected(false); 
		chckbxPrueba3.setSelected(false); 
		chckbxPrueba4.setSelected(false); 
		chckbxPrueba5.setSelected(false); 
		chckbxOtras.setSelected(false); 
		
		chckbxPrueba1.setEnabled(false); 
		chckbxPrueba2.setEnabled(false); 
		chckbxPrueba3.setEnabled(false); 
		chckbxPrueba4.setEnabled(false); 
		chckbxPrueba5.setEnabled(false); 
		chckbxOtras.setEnabled(false); 
		
		comboBoxNombreEquipo.setEnabled(false);
		textFieldOtrasPruebas.setEditable(false);
	}

	/***********************************
	 * 
	 * Validación de campos
	 * 
	 ***********************************/
	public boolean validaCampos() {

		if ((comboBoxNombreDelEmpleado.getSelectedItem().equals(null))
				|| (comboBoxCategoria.getSelectedItem().equals(null))
				|| comboBoxNombreEquipo.getSelectedItem().equals(null) || chckbxPrueba1.getText().equals(null)
				|| chckbxPrueba2.getText().equals(null) || chckbxPrueba3.getText().equals(null)
				|| chckbxPrueba4.getText().equals(null) || chckbxPrueba5.getText().equals(null)
				|| chckbxOtras.getText().equals(null) || textFieldOtrasPruebas.getText().equals(null)
				|| textFieldObservacionesAdicionales.getText().equals(null)) {
			muestraDialogoConMensaje("Los campos no pueden ser nulos");
			return false;
		} else {

			if ((comboBoxNombreDelEmpleado.getSelectedItem()
					.equals("      ----------------------- Seleccione una opción -----------------------"))
					|| (comboBoxCategoria.getSelectedItem().equals(" Seleccione una opción"))
					|| (comboBoxNombreEquipo.getSelectedItem().equals(" Seleccione una opción")) 
					|| (comboBoxNombreEquipo.getSelectedItem().equals(""))) {
				muestraDialogoConMensaje("Campos vacíos, rellena la información marcada con *");
				return false;
			} else {

				if (!chckbxPrueba1.isSelected() && !chckbxPrueba2.isSelected() && !chckbxPrueba3.isSelected()
						&& !chckbxPrueba4.isSelected() && !chckbxPrueba5.isSelected() && !chckbxOtras.isSelected()) {
					muestraDialogoConMensaje(
							"Selecciona alguna de las pruebas o ingresa alguna prueba de tu preferencia");
					return false;
				} else {
					if (chckbxOtras.isSelected() && textFieldOtrasPruebas.getText().equals("")) {
						muestraDialogoConMensaje("Ingresa las otras pruebas realizadas a "
								+ (String) comboBoxNombreEquipo.getSelectedItem());
						return false;

					} else {
						return true;
					}

				}
			}
		}
	}
	
	public void listaDePruebas() {
		
		if(chckbxPrueba1.isSelected()) {
			pruebas.add(chckbxPrueba1.getText()); 
		}
		if(chckbxPrueba2.isSelected()) {
			pruebas.add(chckbxPrueba2.getText()); 
		}
		if(chckbxPrueba3.isSelected()) {
			pruebas.add(chckbxPrueba3.getText()); 
		}
		if(chckbxPrueba4.isSelected()) {
			pruebas.add(chckbxPrueba4.getText()); 
		}
		if(chckbxPrueba5.isSelected()) {
			pruebas.add(chckbxPrueba5.getText()); 
		}
		if(chckbxOtras.isSelected()) {
			pruebas.add(textFieldOtrasPruebas.getText()); 
		}
	}
	
	public void muestra(ControlCrearPruebasReparacion control, List <CategoriaDiagnostico> categorias, List<Empleado> empleados, String fecha) {

		
		this.control = control; 
		
		textFieldObservacionesAdicionales.setText("");
		textFieldOtrasPruebas.setText("");
		textFieldFecha.setText(fecha);
		
		comboBoxModelEmpleados = new DefaultComboBoxModel <>();
		
		comboBoxModelEmpleados.addElement("      ----------------------- Seleccione una opción -----------------------");
		for(Empleado empleado:empleados) {
			comboBoxModelEmpleados.addElement(empleado.getNombreCompleto());
		}
		
		comboBoxNombreDelEmpleado.setModel(comboBoxModelEmpleados);
		
		comboBoxModelCategorias = new DefaultComboBoxModel <>();
		
		comboBoxModelCategorias.addElement(" Seleccione una opción");
		for(CategoriaDiagnostico categoria:categorias) {
			comboBoxModelCategorias.addElement(categoria.getNombre());
		}
		
		comboBoxCategoria.setModel(comboBoxModelCategorias);
		
		comboBoxModelNombreEquipo = new DefaultComboBoxModel<>();
		comboBoxModelNombreEquipo.addElement("");
		comboBoxNombreEquipo.setModel(comboBoxModelNombreEquipo);
		
		chckbxPrueba1.setEnabled(false); 
		chckbxPrueba2.setEnabled(false); 
		chckbxPrueba3.setEnabled(false); 
		chckbxPrueba4.setEnabled(false); 
		chckbxPrueba5.setEnabled(false); 
		chckbxOtras.setEnabled(false);
		
		chckbxPrueba1.setSelected(false); 
		chckbxPrueba2.setSelected(false); 
		chckbxPrueba3.setSelected(false); 
		chckbxPrueba4.setSelected(false); 
		chckbxPrueba5.setSelected(false); 
		chckbxOtras.setSelected(false); 
		
		comboBoxNombreEquipo.setEnabled(false);
		textFieldOtrasPruebas.setEditable(false);		
		pruebas = new ArrayList<>();
		
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
