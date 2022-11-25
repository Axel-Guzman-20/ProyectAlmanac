package mx.uam.ingsof.proyecto.presentacion.agregarProducto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.modelo.SeccionCatalogo;


@SuppressWarnings("serial")
@Component
public class VistaAgregarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombreProducto;
	private JTextField textFieldMarcaProducto;
	private JTextField textFieldPecioProducto;
	private JTextField textFieldDescuentoProducto;
	private JTextField textFieldNumeroEnExistenciaProducto;
	private JComboBox <String> comboBoxSeccionCatalogo; 
	private JTextArea textAreaDescripcionProducto;
	private ControlAgregarProducto control;
	

	/**
	 * Create the frame.
	 */
	public VistaAgregarProducto() {
		setBackground(new Color(204, 204, 153));
		setTitle("Agregar Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Seccion del producto *");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 103, SpringLayout.NORTH, contentPane);
		contentPane.add(lblNewLabel);
		
		comboBoxSeccionCatalogo = new JComboBox<>();
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBoxSeccionCatalogo, 6, SpringLayout.EAST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboBoxSeccionCatalogo, -231, SpringLayout.EAST, contentPane);
		contentPane.add(comboBoxSeccionCatalogo);
		
		JLabel lblNewLabel_1 = new JLabel("Agregar Producto");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_1, -170, SpringLayout.EAST, contentPane);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Script MT Bold", Font.PLAIN, 28));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre del producto *");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 142, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 32, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, lblNewLabel_2);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Rellena los siguientes campos.");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 69, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBoxSeccionCatalogo, 16, SpringLayout.SOUTH, lblNewLabel_3);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("*Campos obligatorios");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 0, SpringLayout.NORTH, lblNewLabel_3);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_4, 6, SpringLayout.EAST, lblNewLabel_3);
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel_4);
		
		textFieldNombreProducto = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, comboBoxSeccionCatalogo, -18, SpringLayout.NORTH, textFieldNombreProducto);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldNombreProducto, -3, SpringLayout.NORTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldNombreProducto, 0, SpringLayout.WEST, comboBoxSeccionCatalogo);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldNombreProducto, -116, SpringLayout.EAST, contentPane);
		contentPane.add(textFieldNombreProducto);
		textFieldNombreProducto.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Marca del producto *");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 20, SpringLayout.SOUTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_5, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_5);
		
		textFieldMarcaProducto = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldMarcaProducto, -3, SpringLayout.NORTH, lblNewLabel_5);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldMarcaProducto, 16, SpringLayout.EAST, lblNewLabel_5);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldMarcaProducto, 0, SpringLayout.EAST, textFieldNombreProducto);
		contentPane.add(textFieldMarcaProducto);
		textFieldMarcaProducto.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Descripción *");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_6, 22, SpringLayout.SOUTH, lblNewLabel_5);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_6, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_6);
		
		textAreaDescripcionProducto = new JTextArea();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textAreaDescripcionProducto, 0, SpringLayout.NORTH, lblNewLabel_6);
		sl_contentPane.putConstraint(SpringLayout.WEST, textAreaDescripcionProducto, 0, SpringLayout.WEST, comboBoxSeccionCatalogo);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textAreaDescripcionProducto, 85, SpringLayout.NORTH, lblNewLabel_6);
		sl_contentPane.putConstraint(SpringLayout.EAST, textAreaDescripcionProducto, 0, SpringLayout.EAST, textFieldNombreProducto);
		contentPane.add(textAreaDescripcionProducto);
		
		JLabel lblNewLabel_5_1 = new JLabel("Precio del producto *");
		lblNewLabel_5_1.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_5_1, 96, SpringLayout.SOUTH, lblNewLabel_6);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_5_1, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_5_1);
		
		textFieldPecioProducto = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldPecioProducto, -3, SpringLayout.NORTH, lblNewLabel_5_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldPecioProducto, 0, SpringLayout.WEST, comboBoxSeccionCatalogo);
		textFieldPecioProducto.setColumns(10);
		contentPane.add(textFieldPecioProducto);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Descuento del producto ");
		lblNewLabel_5_1_1.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_5_1_1, 0, SpringLayout.NORTH, lblNewLabel_5_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_5_1_1, 31, SpringLayout.EAST, textFieldPecioProducto);
		contentPane.add(lblNewLabel_5_1_1);
		
		textFieldDescuentoProducto = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldDescuentoProducto, -3, SpringLayout.NORTH, lblNewLabel_5_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldDescuentoProducto, 5, SpringLayout.EAST, lblNewLabel_5_1_1);
		textFieldDescuentoProducto.setColumns(10);
		contentPane.add(textFieldDescuentoProducto);
		
		JLabel lblNewLabel_7 = new JLabel("Número en existencia *");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_7, 22, SpringLayout.SOUTH, lblNewLabel_5_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_7, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_7);
		
		textFieldNumeroEnExistenciaProducto = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldNumeroEnExistenciaProducto, -3, SpringLayout.NORTH, lblNewLabel_7);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldNumeroEnExistenciaProducto, 0, SpringLayout.EAST, textFieldPecioProducto);
		textFieldNumeroEnExistenciaProducto.setColumns(10);
		contentPane.add(textFieldNumeroEnExistenciaProducto);
		
		JButton btnAgregar = new JButton("Agregar");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAgregar, -26, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnAgregar, -289, SpringLayout.EAST, contentPane);
		btnAgregar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnAgregar);
		
		JButton btnCancelar = new JButton("Cancelar");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCancelar, 26, SpringLayout.EAST, btnAgregar);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnCancelar, 0, SpringLayout.SOUTH, btnAgregar);
		contentPane.add(btnCancelar);
		
		
		//Listeners
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldNombreProducto.getText().equals("") || textFieldMarcaProducto.getText().equals("") || textAreaDescripcionProducto.getText().equals("") ||
						textFieldPecioProducto.getText().equals("") || textFieldNumeroEnExistenciaProducto.getText().equals("")) {
					muestraDialogoConMensaje("El nombre, la marca, la descripcion, el precio y el numero en existencia del producto no deben de estar vacios");
				} else {
					
					if(textFieldDescuentoProducto.getText().equals("")) {
						control.agregaProducto(textFieldNombreProducto.getText(), textFieldMarcaProducto.getText(), textAreaDescripcionProducto.getText(), textFieldPecioProducto.getText(),
								"0",textFieldNumeroEnExistenciaProducto.getText(), (String) comboBoxSeccionCatalogo.getSelectedItem());
					}else {
						control.agregaProducto(textFieldNombreProducto.getText(), textFieldMarcaProducto.getText(), textAreaDescripcionProducto.getText(), textFieldPecioProducto.getText(),
								textFieldDescuentoProducto.getText(),textFieldNumeroEnExistenciaProducto.getText(), (String) comboBoxSeccionCatalogo.getSelectedItem());
					}
				}
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.termina();
			}
		});
		
	}
	
	public void muestra(ControlAgregarProducto control, List <SeccionCatalogo> secciones) {
		
		this.control = control;
		
		textFieldNombreProducto.setText("");
		textFieldMarcaProducto.setText("");
		textAreaDescripcionProducto.setText("");
		textFieldPecioProducto.setText("");
		textFieldDescuentoProducto.setText("");
		textFieldNumeroEnExistenciaProducto.setText("");

		DefaultComboBoxModel <String> comboBoxModel = new DefaultComboBoxModel <>();
		
		for(SeccionCatalogo seccion:secciones) {
			comboBoxModel.addElement(seccion.getNombre());
		}
		
		comboBoxSeccionCatalogo.setModel(comboBoxModel);
		
		setVisible(true);
		
	}
	
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}

}
