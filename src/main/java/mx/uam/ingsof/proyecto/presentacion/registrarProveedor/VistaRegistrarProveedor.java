package mx.uam.ingsof.proyecto.presentacion.registrarProveedor;

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
import javax.swing.UIManager;

import org.springframework.stereotype.Component;


@SuppressWarnings("serial")
@Component

/**
 * Ventana que mira el usuario para registrar un nuevo proveedor
 * 
 * @author MiguelGuzman
 * 
*/

public class VistaRegistrarProveedor extends JFrame {

	private ControlRegistrarProveedor controlRegistrarProveedor;
	
	private Font fuente;
	private JPanel panelPrincipal;
	private Color colorFondo;
		
	public JTextField fechaText;
	public JTextField nombreEmpresaText;
	public JTextField nombreContactoText;
	public JTextField cargoContactoText;
	public JTextField direccionEmpresaText;
	public JTextField correoEleContactoText;
	public JTextField telefonoContactoText;
	public JTextField sobreLaEmpresaText;
	
	private JButton agregarButton;
	private JButton limpiarButton;
	private JButton cancelarButton;
	
	
	public VistaRegistrarProveedor() {
	
		setTitle("Registrar nuevo proveedor");
		
		setLayout(null);
	
		// Dimensiones del JFrame, no se pone valor x, y por funcion
		// setLocationRelativeTos
		// (xPosicion,yPosicion,largoVentana, alturaVentana)
		setBounds(0, 0, 650, 650);
			
		// Hacemos que la ventana se localice en el centro de la pantalla
		setLocationRelativeTo(null);
	
		// Para el titulo
		fuente = new Font("Tahoma", Font.BOLD, 22);
		
		// Color para el fondo azul
		colorFondo = new Color(89, 126, 170);
			
		// Panel simular el fondo y se le da diseño y configuracion
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(colorFondo);
		panelPrincipal.setBounds(0, 0, 650, 650);
		panelPrincipal.setLayout(null);
		panelPrincipal.setVisible(true);
		
		
		// Color para el fondo blanco
		colorFondo = new Color(255, 255, 255);
		JPanel panelBlanco = new JPanel();
		panelBlanco.setBounds(50, 25, 545, 555);
		panelBlanco.setBackground(colorFondo);
		panelBlanco.setLayout(null);
		panelBlanco.setVisible(true);
		panelBlanco.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		
		// Agregamos el panel blanco al panel principal
		panelPrincipal.add(panelBlanco);
		
		//Creamos el titulo y lo agregamos al panel blanco
		JLabel tituloLabel = new JLabel("Registrar Proveedor");
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
		JLabel nombreEmpresaLabel = new JLabel("Nombre de la empresa o marca: *");
		nombreEmpresaLabel.setFont(fuente);
		nombreEmpresaLabel.setBounds(30, 90, 200, 30);
		panelBlanco.add(nombreEmpresaLabel);		
		
		// Le damos su caja de texto
		nombreEmpresaText = new JTextField();
		nombreEmpresaText.setFont(fuente);
		nombreEmpresaText.setBounds(240, 90, 250, 30);
		panelBlanco.add(nombreEmpresaText);
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel nombreContactoLabel = new JLabel("Nombre del contacto: *");
		nombreContactoLabel.setFont(fuente);
		nombreContactoLabel.setBounds(30, 130, 200, 30);
		panelBlanco.add(nombreContactoLabel);		
				
		// Le damos su caja de texto
		nombreContactoText = new JTextField();
		nombreContactoText.setFont(fuente);
		nombreContactoText.setBounds(240, 130, 250, 30);
		panelBlanco.add(nombreContactoText);
		

		//Creamos los label y lo agregamos al panel blanco
		JLabel cargoContactoLabel = new JLabel("Cargo del contacto: *");
		cargoContactoLabel.setFont(fuente);
		cargoContactoLabel.setBounds(30, 170, 200, 30);
		panelBlanco.add(cargoContactoLabel);		
				
		// Le damos su caja de texto
		cargoContactoText = new JTextField();
		cargoContactoText.setFont(fuente);
		cargoContactoText.setBounds(240, 170, 250, 30);
		panelBlanco.add(cargoContactoText);		
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel direccionEmpresaLabel = new JLabel("Dirección de la empresa o marca: *");
		direccionEmpresaLabel.setFont(fuente);
		direccionEmpresaLabel.setBounds(30, 210, 200, 30);
		panelBlanco.add(direccionEmpresaLabel);		
					
		// Le damos su caja de texto
		direccionEmpresaText = new JTextField();
		direccionEmpresaText.setFont(fuente);
		direccionEmpresaText.setBounds(240, 210, 250, 30);
		panelBlanco.add(direccionEmpresaText);
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel correoEleContactoLabel = new JLabel("Correo electrónico del contacto: *");
		correoEleContactoLabel.setFont(fuente);
		correoEleContactoLabel.setBounds(30, 250, 200, 30);
		panelBlanco.add(correoEleContactoLabel);		
							
		// Le damos su caja de texto
		correoEleContactoText = new JTextField();
		correoEleContactoText.setFont(fuente);
		correoEleContactoText.setBounds(240, 250, 250, 30);
		panelBlanco.add(correoEleContactoText);
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel telefonoContactoLabel = new JLabel("Teléfono del contacto: *");
		telefonoContactoLabel.setFont(fuente);
		telefonoContactoLabel.setBounds(30, 290, 200, 30);
		panelBlanco.add(telefonoContactoLabel);		
									
		// Le damos su caja de texto
		telefonoContactoText = new JTextField();
		telefonoContactoText.setFont(fuente);
		telefonoContactoText.setBounds(240, 290, 250, 30);
		panelBlanco.add(telefonoContactoText);
		
		
		//Creamos los label y lo agregamos al panel blanco
		JLabel sobreLaEmpresaLabel = new JLabel("Sobre la empresa:");
		sobreLaEmpresaLabel.setFont(fuente);
		sobreLaEmpresaLabel.setBounds(30, 330, 200, 30);
		panelBlanco.add(sobreLaEmpresaLabel);		
											
		// Le damos su caja de texto
		sobreLaEmpresaText = new JTextField();
		sobreLaEmpresaText.setFont(fuente);
		sobreLaEmpresaText.setBounds(240, 330, 250, 110);
		panelBlanco.add(sobreLaEmpresaText);		
		
		
		// Fuente para los botones
		fuente = new Font("Tahoma", Font.BOLD, 11);
		
		agregarButton = new JButton("Agregar");
		agregarButton.setFont(fuente);
		agregarButton.setForeground(Color.WHITE);
		agregarButton.setBackground(new Color(0, 158, 15));
		agregarButton.setBounds(70, 480, 90, 30);
		agregarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelBlanco.add(agregarButton);
		
		
		limpiarButton = new JButton("Limpiar");
		limpiarButton.setFont(fuente);
		limpiarButton.setForeground(Color.WHITE);
		limpiarButton.setBackground(new Color(89, 126, 170));
		limpiarButton.setBounds(230, 480, 90, 30);
		limpiarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelBlanco.add(limpiarButton);
		
		
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setFont(fuente);
		cancelarButton.setForeground(Color.WHITE);
		cancelarButton.setBackground(new Color(204, 0, 0));
		cancelarButton.setBounds(390, 480, 90, 30);
		cancelarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelBlanco.add(cancelarButton);
		
		
		
		
		//Acciones de los botones
		agregarButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// En caso que algun campo no tenga informacion
				if( nombreEmpresaText.getText().equals("") || nombreContactoText.getText().equals("") 
						|| cargoContactoText.getText().equals("") || direccionEmpresaText.getText().equals("") 
						|| correoEleContactoText.getText().equals("") || telefonoContactoText.getText().equals("")
				)
					muestraDialogoConMensaje("Campos vacíos, rellena la información marcada con *");
				else
					controlRegistrarProveedor.recolectaDatos();
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
				controlRegistrarProveedor.cierraVentana();
			}
		});
		
		
		
		// Agregamos el panel principal al JFrame que creamos al incio de la aplicación
		getContentPane().add(panelPrincipal);
		
	}
	
	public void muestra(ControlRegistrarProveedor control, String fecha) {
		
		controlRegistrarProveedor = control;
		
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
	
	
	public void limpiarCajas() {
		
		// Limpia todas las cajas de texto
		nombreEmpresaText.setText("");
		nombreContactoText.setText("");
		cargoContactoText.setText("");
		direccionEmpresaText.setText("");
		correoEleContactoText.setText("");
		telefonoContactoText.setText("");
		sobreLaEmpresaText.setText("");
	
	}
	
	
}
