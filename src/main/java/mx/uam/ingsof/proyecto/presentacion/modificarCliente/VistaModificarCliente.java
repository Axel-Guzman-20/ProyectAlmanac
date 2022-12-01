package mx.uam.ingsof.proyecto.presentacion.modificarCliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.springframework.stereotype.Component;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@SuppressWarnings("serial")
@Component

/**
 * Ventana que mira el usuario para registrar un nuevo cliente
 * 
 * @author AxelGuzman
 * 
 */

public class VistaModificarCliente extends JFrame {

	private ButtonGroup bg = new ButtonGroup();
	private ButtonGroup bg2 = new ButtonGroup();

	private ControlModificarCliente control;

	private Font fuente;
	private JPanel panelPrincipal;
	private Color colorFondo;

	public JTextField fechaText;
	public JTextField nombreCompletoText;
	public JTextField generoText;
	public JTextField direccionText;
	public JTextField telefonoText;
	public JTextField correoelectronicoText;
	public JTextField correoelectronicoText2;
	private String clienteSeleccionado = "";
	
	
	private JComboBox<String> comboBoxClientes;
	private JButton modificarButton;
	private JButton limpiarButton;
	private JButton cancelarButton;

	public VistaModificarCliente() {

		setTitle("Modificar cliente");

		getContentPane().setLayout(null);

		// Dimensiones del JFrame, no se pone valor x, y por funcion
		// setLocationRelativeTos
		// (xPosicion,yPosicion,largoVentana, alturaVentana)
		setBounds(0, 0, 661, 621);

		// Hacemos que la ventana se localice en el centro de la pantalla
		setLocationRelativeTo(null);

		// Para el titulo
		fuente = new Font("Tahoma", Font.BOLD, 22);

		// Color para el fondo azul
		colorFondo = new Color(89, 126, 170);

		// Panel simular el fondo y se le da diseño y configuracion
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(colorFondo);
		panelPrincipal.setBounds(0, 0, 650, 586);
		panelPrincipal.setLayout(null);
		panelPrincipal.setVisible(true);

		// Color para el fondo blanco
		colorFondo = new Color(255, 255, 255);
		JPanel panelBlanco = new JPanel();
		panelBlanco.setBounds(23, 25, 606, 537);
		panelBlanco.setBackground(colorFondo);
		panelBlanco.setLayout(null);
		panelBlanco.setVisible(true);
		panelBlanco.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

		// Agregamos el panel blanco al panel principal
		panelPrincipal.add(panelBlanco);

		// Creamos el titulo y lo agregamos al panel blanco
		JLabel tituloLabel = new JLabel("Modificar Cliente");
		tituloLabel.setFont(fuente);
		tituloLabel.setBounds(199, 11, 250, 30);
		panelBlanco.add(tituloLabel);

		// combobox
		JLabel combobox = new JLabel("Selecciona al cliente que deseas modificar");
		combobox.setFont(new Font("Tahoma", Font.BOLD, 10));
		combobox.setBounds(30, 111, 215, 25);
		panelBlanco.add(combobox);

		comboBoxClientes = new JComboBox<>();
		comboBoxClientes.setToolTipText("");
		comboBoxClientes.setBounds(261, 111, 150, 25);
		panelBlanco.add(comboBoxClientes);

		// Para las label
		fuente = new Font("Tahoma", 0, 12);

		// Creamos los label y lo agregamos al panel blanco
		JLabel camposObligatoriosLabel = new JLabel("* Campos obligatorios");
		camposObligatoriosLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		camposObligatoriosLabel.setBounds(30, 75, 150, 25);
		camposObligatoriosLabel.setForeground(Color.RED);
		panelBlanco.add(camposObligatoriosLabel);

		// Creamos los label y lo agregamos al panel blanco
		JLabel fechaLabel = new JLabel("Fecha:");
		fechaLabel.setFont(fuente);
		fechaLabel.setBounds(436, 50, 60, 25);
		panelBlanco.add(fechaLabel);

		// Creamos la caja de texto sin poder editar
		fechaText = new JTextField();
		fechaText.setEditable(false);
		fechaText.setBackground(Color.WHITE);
		fechaText.setBorder(null);
		fechaText.setFont(fuente);
		fechaText.setBounds(485, 50, 100, 25);
		panelBlanco.add(fechaText);

		// Creamos los label y lo agregamos al panel blanco
		JLabel nombreCompletoLabel = new JLabel("Nombre Completo: *");
		nombreCompletoLabel.setFont(fuente);
		nombreCompletoLabel.setBounds(30, 178, 200, 30);
		panelBlanco.add(nombreCompletoLabel);

		// Le damos su caja de texto
		nombreCompletoText = new JTextField();
		nombreCompletoText.setFont(fuente);
		nombreCompletoText.setBounds(240, 179, 250, 30);
		panelBlanco.add(nombreCompletoText);

		// Creamos los label y lo agregamos al panel blanco
		JLabel generoLabel = new JLabel("Genero: *");
		generoLabel.setFont(fuente);
		generoLabel.setBounds(30, 241, 100, 30);
		panelBlanco.add(generoLabel);

		// caja de texto para el genero ( sera visible)
		generoText = new JTextField();
		generoText.setFont(fuente);
		generoText.setBounds(240, 242, 150, 30);
		panelBlanco.add(generoText);

		// generamos los radiobutton para los generos

		JRadioButton Masculino = new JRadioButton("Masculino");
		JRadioButton Femenino = new JRadioButton("Femenino");

		JRadioButton VerInformacion = new JRadioButton("Ver información");
		VerInformacion.setBackground(new Color(255, 255, 255));
		VerInformacion.setBounds(455, 112, 109, 23);
		panelBlanco.add(VerInformacion);

		// Creamos los label y lo agregamos al panel blanco
		JLabel direccionLabel = new JLabel("Direccion: *");
		direccionLabel.setFont(fuente);
		direccionLabel.setBounds(30, 318, 200, 30);
		panelBlanco.add(direccionLabel);

		// Le damos su caja de texto
		direccionText = new JTextField();
		direccionText.setFont(fuente);
		direccionText.setBounds(240, 319, 250, 30);
		panelBlanco.add(direccionText);

		// Creamos los label y lo agregamos al panel blanco
		JLabel telefonoLabel = new JLabel("Telefono: *");
		telefonoLabel.setFont(fuente);
		telefonoLabel.setBounds(30, 359, 200, 30);
		panelBlanco.add(telefonoLabel);

		// Le damos su caja de texto
		telefonoText = new JTextField();
		telefonoText.setFont(fuente);
		telefonoText.setBounds(240, 360, 250, 30);
		panelBlanco.add(telefonoText);

		// Creamos los label y lo agregamos al panel blanco
		JLabel correoElectronicoLabel = new JLabel("Correo electronico: *");
		correoElectronicoLabel.setFont(fuente);
		correoElectronicoLabel.setBounds(30, 400, 200, 30);
		panelBlanco.add(correoElectronicoLabel);

		// Le damos su caja de texto
		correoelectronicoText = new JTextField();
		correoelectronicoText.setFont(fuente);
		correoelectronicoText.setBounds(240, 401, 250, 30);
		panelBlanco.add(correoelectronicoText);
		
		correoelectronicoText2 = new JTextField();
		

		// Fuente para los botones
		fuente = new Font("Tahoma", Font.BOLD, 11);

		modificarButton = new JButton("Modificar");
		modificarButton.setFont(fuente);
		modificarButton.setForeground(Color.WHITE);
		modificarButton.setBackground(new Color(0, 158, 15));
		modificarButton.setBounds(70, 475, 90, 30);
		modificarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelBlanco.add(modificarButton);

		limpiarButton = new JButton("Limpiar");
		limpiarButton.setFont(fuente);
		limpiarButton.setForeground(Color.WHITE);
		limpiarButton.setBackground(new Color(89, 126, 170));
		limpiarButton.setBounds(240, 475, 90, 30);
		limpiarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelBlanco.add(limpiarButton);

		cancelarButton = new JButton("Cancelar");
		cancelarButton.setFont(fuente);
		cancelarButton.setForeground(Color.WHITE);
		cancelarButton.setBackground(new Color(204, 0, 0));
		cancelarButton.setBounds(388, 475, 90, 30);
		cancelarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelBlanco.add(cancelarButton);

		// Accion de los radioButton
		Masculino.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				generoText.setText("Masculino");
			}
		});
		Masculino.setBackground(new Color(255, 255, 255));
		Masculino.setBounds(420, 242, 109, 23);
		panelBlanco.add(Masculino);

		Femenino.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				generoText.setText("Femenino");

			}
		});
		Femenino.setBackground(new Color(255, 255, 255));
		Femenino.setBounds(420, 268, 109, 23);
		panelBlanco.add(Femenino);

		bg.add(Masculino);
		bg.add(Femenino);
		bg2.add(VerInformacion);

		VerInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (VerInformacion.isSelected() && (String) comboBoxClientes.getSelectedItem() != "Seleccione Cliente") {

					muestraInformacionCliente();
					nombreCompletoText.setEditable(true);
					generoText.setEditable(false);
					direccionText.setEditable(true);
					telefonoText.setEditable(true);
					correoelectronicoText.setEditable(true);
					comboBoxClientes.setEnabled(false);
					
				} else {
					muestraDialogoConMensaje("No se ha seleccionado un cliente ");
					nombreCompletoText.setEditable(false);
					generoText.setEditable(false);
					direccionText.setEditable(false);
					telefonoText.setEditable(false);
					correoelectronicoText.setEditable(false);
					
					bg2.clearSelection();
					nombreCompletoText.setText("");
					generoText.setText("");
					direccionText.setText("");
					telefonoText.setText("");
					correoelectronicoText.setText("");

				}
			}
		});

		// Acciones de los botones
		modificarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (nombreCompletoText.getText().equals("") || generoText.getText().equals("")
						|| direccionText.getText().equals("") || telefonoText.getText().equals("")
						|| correoelectronicoText.getText().equals("") || correoelectronicoText2.getText().equals(""))
					muestraDialogoConMensaje("Campos vacíos, rellena la información marcada con *");
				else
					control.modificarCliente(clienteSeleccionado, nombreCompletoText.getText(), generoText.getText(),
							direccionText.getText(), telefonoText.getText(), correoelectronicoText.getText(),correoelectronicoText2.getText());

			}
		});

		limpiarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limpiarCajas();
			}
		});

		cancelarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				control.cierraVentana();
			}
		});

		// Agregamos el panel principal al JFrame que creamos al incio de la aplicación
		getContentPane().add(panelPrincipal);

	}

	public void muestra(String fecha, ControlModificarCliente control, List<Cliente> clientes) {

		this.control = control;
		// Coloca la fecha automaticamente
		fechaText.setText(fecha);
		nombreCompletoText.setText("");
		generoText.setText("");
		direccionText.setText("");
		telefonoText.setText("");
		correoelectronicoText.setText("");

		nombreCompletoText.setEditable(false);
		generoText.setEditable(false);
		direccionText.setEditable(false);
		telefonoText.setEditable(false);
		correoelectronicoText.setEditable(false);
		
	//	new DefaultComboBoxModel(new String[] {"select"})
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		comboBoxModel.addElement("Seleccione Cliente");
		for (Cliente cliente : clientes) {
			comboBoxModel.addElement(cliente.getNombreCompleto());
		}
		
		comboBoxClientes.setModel(comboBoxModel);

		// Esto limpia las cajas de texto cuando se vuelve a abrir
		limpiarCajas();

		setVisible(true);

	}

	public void muestraInformacionCliente() {
		clienteSeleccionado = (String) comboBoxClientes.getSelectedItem();

		Cliente cliente = control.obtenerCliente(clienteSeleccionado);
		
	
		if (cliente != null) {
			nombreCompletoText.setText(cliente.getNombreCompleto());
			generoText.setText(cliente.getGenero());
			direccionText.setText(cliente.getDireccion());
			telefonoText.setText(cliente.getTelefono());
			correoelectronicoText.setText(cliente.getCorreoelectronico());
			correoelectronicoText2.setText(cliente.getCorreoelectronico());
			
		} else {
			muestraDialogoConMensaje("no hay clientes seleccionados *");
			nombreCompletoText.setText("");
			generoText.setText("");
			direccionText.setText("");
			telefonoText.setText("");
			correoelectronicoText.setText("");

			nombreCompletoText.setEditable(false);
			generoText.setEditable(false);
			direccionText.setEditable(false);
			telefonoText.setEditable(false);
			correoelectronicoText.setEditable(false);
			
			comboBoxClientes.setEnabled(true);
			
		}

	}

	public void termina() {

		setVisible(false);

	}

	public void muestraDialogoConMensaje(String mensaje) {

		JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.INFORMATION_MESSAGE);

	}

	public void limpiarCajas() {

		// Limpia todas las cajas de texto

		nombreCompletoText.setText("");
		generoText.setText("");
		direccionText.setText("");
		telefonoText.setText("");
		correoelectronicoText.setText("");
		correoelectronicoText2.setText("");
		bg.clearSelection();
		bg2.clearSelection();
		nombreCompletoText.setEditable(false);
		generoText.setEditable(false);
		direccionText.setEditable(false);
		telefonoText.setEditable(false);
		correoelectronicoText.setEditable(false);
		
		comboBoxClientes.setEnabled(true);
		comboBoxClientes.setSelectedItem("Seleccione Cliente");

	}
}
