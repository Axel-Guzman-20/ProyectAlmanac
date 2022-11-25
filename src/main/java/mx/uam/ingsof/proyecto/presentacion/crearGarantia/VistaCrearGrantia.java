package mx.uam.ingsof.proyecto.presentacion.crearGarantia;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.springframework.stereotype.Component;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;


@SuppressWarnings("serial")
@Component
public class VistaCrearGrantia extends JFrame {
	
	private ControlCrearGarantia controlCrearGarantia;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	 
	public VistaCrearGrantia() {
		setTitle("Crea Garantia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 561);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 51, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Acta de garantía ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(165, 11, 228, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Realización de la garantía");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(152, 46, 241, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha de expedición");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(259, 83, 134, 28);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(404, 89, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("IDCompra");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(24, 90, 69, 20);
		contentPane.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(94, 92, 116, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Nombre del usuario");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(24, 121, 124, 20);
		contentPane.add(lblNewLabel_3_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(149, 121, 183, 20);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Direccion de usuario");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_1.setBounds(24, 152, 124, 20);
		contentPane.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Calle");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2.setBounds(24, 176, 44, 20);
		contentPane.add(lblNewLabel_3_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(58, 176, 207, 20);
		contentPane.add(textField_3);
		
		JLabel lblNewLabel_3_3 = new JLabel("Num. Exterior");
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_3.setBounds(277, 176, 89, 20);
		contentPane.add(lblNewLabel_3_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(365, 176, 44, 20);
		contentPane.add(textField_4);
		
		JLabel lblNewLabel_3_3_1 = new JLabel("Num. Interior");
		lblNewLabel_3_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_3_1.setBounds(426, 176, 89, 20);
		contentPane.add(lblNewLabel_3_3_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(514, 176, 44, 20);
		contentPane.add(textField_5);
		
		JEditorPane dtrpncomunicamosAUstedes = new JEditorPane();
		dtrpncomunicamosAUstedes.setText("Comunicamos a ustedes mediante esta Carta de Garantía, que la empresa denominada Service Tec, con número de Registro Federal de ST0947254D,y domicilio es Calle Creacion N.E 15 N.I 15 Santa Catarina, asume completa responsabilidad y obligación solidaria para cubrir cualquier reposición, del producto en caso de fallo");
		dtrpncomunicamosAUstedes.setEditable(false);
		dtrpncomunicamosAUstedes.setBounds(21, 207, 547, 69);
		contentPane.add(dtrpncomunicamosAUstedes);
		
		JLabel lblNewLabel_3_4 = new JLabel("Descripción del equipo");
		lblNewLabel_3_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_4.setBounds(24, 279, 145, 20);
		contentPane.add(lblNewLabel_3_4);
		
		textField_6 = new JTextField();
		textField_6.setBounds(22, 301, 503, 76);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(24, 404, 183, 69);
		contentPane.add(textField_7);
		
		JLabel lblNewLabel_3_4_1 = new JLabel("Firma del garantor");
		lblNewLabel_3_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_4_1.setBounds(24, 382, 145, 20);
		contentPane.add(lblNewLabel_3_4_1);
		
		JButton btnNewButton_1 = new JButton("Finalizar la garantia");
		btnNewButton_1.setBounds(340, 432, 154, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if((textField.getText().length() != 0) && (textField_1.getText().length() != 0) && (textField_2.getText().length() != 0) &&
				   (textField_3.getText().length() != 0) && (textField_4.getText().length() != 0) && (textField_5.getText().length() != 0) &&
				   (textField_6.getText().length() != 0)){
					String fecha = textField.getText();
					String idByCuadroTexto  = textField_1.getText();
					String nombreCompleto = textField_2.getText();
					String calle = textField_3.getText();
					String numExt = textField_4.getText();
					String numInt = textField_5.getText();
					String descripcionEquipo = textField_6.getText();
					
					long idCompra = Long.parseLong(idByCuadroTexto);
					boolean validaCompra = (boolean) controlCrearGarantia.validaCompra(idCompra);
					
					if(validaCompra) {
						controlCrearGarantia.creaGarantia(idCompra,nombreCompleto,fecha,calle,numExt,numInt,descripcionEquipo);
						
					}else {
						
					}
					
				}else {
				muestraDialogoConMensaje("Llene todos los campos");	
				}
			}
		});
		contentPane.add(btnNewButton_1);
	}
	
	public void muestra(ControlCrearGarantia control) {
		controlCrearGarantia = control;
		setVisible(true);
	}
	
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
	
	
}
