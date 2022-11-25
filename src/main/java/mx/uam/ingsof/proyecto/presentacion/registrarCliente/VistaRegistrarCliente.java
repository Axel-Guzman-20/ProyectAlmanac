package mx.uam.ingsof.proyecto.presentacion.registrarCliente;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.springframework.stereotype.Component;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


@SuppressWarnings("serial")
@Component

/**
 * Ventana que mira el usuario para registrar un nuevo cliente
 * 
 * @author AxelGuzman
 * 
*/

public class VistaRegistrarCliente  extends JFrame {
	
	private ButtonGroup bg = new ButtonGroup();

	private ControlRegistrarCliente controlRegistrarCliente;
	
	private Font fuente;
	private JPanel panelPrincipal;
	private Color colorFondo;
		
	public JTextField fechaText;
	public JTextField nombreCompletoText;
	public JTextField generoText;
	public JTextField direccionText;
	public JTextField telefonoText;
	public JTextField correoelectronicoText;
	
	private JButton agregarButton;
	private JButton limpiarButton;
	private JButton cancelarButton;
	
	
	
	public VistaRegistrarCliente() {
	
		setTitle("Registrar nuevo cliente");
		
		getContentPane().setLayout(null);
	
		// Dimensiones del JFrame, no se pone valor x, y por funcion 
		// setLocationRelativeTos
		// (xPosicion,yPosicion,largoVentana, alturaVentana)
		setBounds(0, 0, 650, 560);
			
		// Hacemos que la ventana se localice en el centro de la pantalla
		setLocationRelativeTo(null);
	
		// Para el titulo
		fuente = new Font("Tahoma", Font.BOLD, 22);
		
		// Color para el fondo azul
		colorFondo = new Color(89, 126, 170);
			
		// Panel simular el fondo y se le da diseño y configuracion
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(colorFondo);
		panelPrincipal.setBounds(0, 0, 650, 529);
		panelPrincipal.setLayout(null);
		panelPrincipal.setVisible(true);
		
		
		// Color para el fondo blanco
		colorFondo = new Color(255, 255, 255);
		JPanel panelBlanco = new JPanel();
		panelBlanco.setBounds(50, 25, 545, 470);
		panelBlanco.setBackground(colorFondo);
		panelBlanco.setLayout(null);
		panelBlanco.setVisible(true);
		panelBlanco.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		
		// Agregamos el panel blanco al panel principal
		panelPrincipal.add(panelBlanco);
		
		//Creamos el titulo y lo agregamos al panel blanco
		JLabel tituloLabel = new JLabel("Agregar Cliente");
		tituloLabel.setFont(fuente);
		tituloLabel.setBounds(155, 5, 250, 30);
		panelBlanco.add(tituloLabel);
		
		
		// Para las label
		fuente = new Font("Tahoma", 0, 12);
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel camposObligatoriosLabel = new JLabel("* Campos obligatorios");
		camposObligatoriosLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		camposObligatoriosLabel.setBounds(30, 53, 150, 25);
		camposObligatoriosLabel.setForeground(Color.RED);
		panelBlanco.add(camposObligatoriosLabel);
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel fechaLabel = new JLabel("Fecha:");
		fechaLabel.setFont(fuente);
		fechaLabel.setBounds(370, 50, 60, 25);
		panelBlanco.add(fechaLabel);
		
		//Creamos la caja de texto sin poder editar
		fechaText = new JTextField();
		fechaText.setEditable(false);
		fechaText.setBackground(Color.WHITE);
		fechaText.setBorder(null);
		fechaText.setFont(fuente);
		fechaText.setBounds(420, 50, 100, 25);
		panelBlanco.add(fechaText);
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel nombreCompletoLabel = new JLabel("Nombre Completo: *");
		nombreCompletoLabel.setFont(fuente);
		nombreCompletoLabel.setBounds(30, 90, 200, 30);
		panelBlanco.add(nombreCompletoLabel);		
		
		// Le damos su caja de texto
		nombreCompletoText = new JTextField();
		nombreCompletoText.setFont(fuente);
		nombreCompletoText.setBounds(240, 90, 250, 30);
		panelBlanco.add(nombreCompletoText);
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel generoLabel = new JLabel("Genero: *");
		generoLabel.setFont(fuente);
		generoLabel.setBounds(30, 150, 200, 30);
		panelBlanco.add(generoLabel);		
					
		//caja de texto para el genero (no sera visible)
		generoText = new JTextField();
	
		
		//generamos los radiobutton para los generos
		JRadioButton Masculino = new JRadioButton("Masculino");
		JRadioButton Femenino = new JRadioButton("Femenino");
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel direccionLabel = new JLabel("Direccion: *");
		direccionLabel.setFont(fuente);
		direccionLabel.setBounds(30, 200, 200, 30);
		panelBlanco.add(direccionLabel);		
				
		// Le damos su caja de texto
		direccionText = new JTextField();
		direccionText.setFont(fuente);
		direccionText.setBounds(240, 200, 250, 30);
		panelBlanco.add(direccionText);
		

		//Creamos los label y lo agregamos al panel blanco
		JLabel telefonoLabel = new JLabel("Telefono: *");
		telefonoLabel.setFont(fuente);
		telefonoLabel.setBounds(30, 251, 200, 30);
		panelBlanco.add(telefonoLabel);		
				
		// Le damos su caja de texto
		telefonoText = new JTextField();
		telefonoText.setFont(fuente);
		telefonoText.setBounds(240, 251, 250, 30);
		panelBlanco.add(telefonoText);		
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel correoElectronicoLabel = new JLabel("Correo electronico: *");
		correoElectronicoLabel.setFont(fuente);
		correoElectronicoLabel.setBounds(30, 306, 200, 30);
		panelBlanco.add(correoElectronicoLabel);		
					
		// Le damos su caja de texto
		correoelectronicoText = new JTextField();
		correoelectronicoText.setFont(fuente);
		correoelectronicoText.setBounds(240, 306, 250, 30);
		panelBlanco.add(correoelectronicoText);
		
		
		// Fuente para los botones
		fuente = new Font("Tahoma", Font.BOLD, 11);
		
		agregarButton = new JButton("Agregar");
		agregarButton.setFont(fuente);
		agregarButton.setForeground(Color.WHITE);
		agregarButton.setBackground(new Color(0, 158, 15));
		agregarButton.setBounds(70, 377, 90, 30);
		agregarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelBlanco.add(agregarButton);
		
		
		limpiarButton = new JButton("Limpiar");
		limpiarButton.setFont(fuente);
		limpiarButton.setForeground(Color.WHITE);
		limpiarButton.setBackground(new Color(89, 126, 170));
		limpiarButton.setBounds(240, 377, 90, 30);
		limpiarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelBlanco.add(limpiarButton);
		
		
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setFont(fuente);
		cancelarButton.setForeground(Color.WHITE);
		cancelarButton.setBackground(new Color(204, 0, 0));
		cancelarButton.setBounds(390, 377, 90, 30);
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
		Masculino.setBounds(247, 150, 109, 23);
		panelBlanco.add(Masculino);
				
		Femenino.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				generoText.setText("Femenino");
				
			}
		});
		Femenino.setBackground(new Color(255, 255, 255));
		Femenino.setBounds(390, 150, 109, 23);
		panelBlanco.add(Femenino);
		
		bg.add(Masculino);
		bg.add(Femenino);
		
		
		
		//Acciones de los botones
		agregarButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// En caso que algun campo no tenga informacion
				if( nombreCompletoText.getText().equals("") || generoText.getText().equals("") 	|| direccionText.getText().equals("") 
						|| telefonoText.getText().equals("") || correoelectronicoText.getText().equals("") 
						
				)
					muestraDialogoConMensaje("Campos vacíos, rellena la información marcada con *");
				else
					controlRegistrarCliente.recolectaDatos();
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
				controlRegistrarCliente.cierraVentana();
			}
		});
		
		
		
		// Agregamos el panel principal al JFrame que creamos al incio de la aplicación
		getContentPane().add(panelPrincipal);
		
	}
	
	public void muestra(ControlRegistrarCliente control, String fecha) {
		
		controlRegistrarCliente = control;
		
		// Coloca la fecha automaticamente
		fechaText.setText(fecha);
		
		//Esto limpia las cajas de texto cuando se vuelve a abrir
		limpiarCajas();
		
		setVisible(true);
	
	}
	
	
	public void termina() {
		
		setVisible(false);
	
	}
	
	
	public void muestraDialogoConMensaje(String mensaje ) {
		
		JOptionPane.showMessageDialog(this , mensaje, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	
	
	
	public void limpiarCajas() {
		
		// Limpia todas las cajas de texto
		
		nombreCompletoText.setText("");
		generoText.setText("");
		direccionText.setText("");
		telefonoText.setText("");
		correoelectronicoText.setText("");
	
	}
}
