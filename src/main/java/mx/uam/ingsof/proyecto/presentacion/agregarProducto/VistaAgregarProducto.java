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
		
		
		SpringLayout slcontentPane = new SpringLayout();
		contentPane.setLayout(slcontentPane);
		
		JLabel lblNewLabel = new JLabel("Seccion del producto *");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 103, SpringLayout.NORTH, contentPane);
		contentPane.add(lblNewLabel);
		
		comboBoxSeccionCatalogo = new JComboBox<>();
		slcontentPane.putConstraint(SpringLayout.WEST, comboBoxSeccionCatalogo, 6, SpringLayout.EAST, lblNewLabel);
		slcontentPane.putConstraint(SpringLayout.EAST, comboBoxSeccionCatalogo, -231, SpringLayout.EAST, contentPane);
		contentPane.add(comboBoxSeccionCatalogo);
		
		JLabel lblNewLabel1 = new JLabel("Agregar Producto");
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel1, 10, SpringLayout.NORTH, contentPane);
		slcontentPane.putConstraint(SpringLayout.EAST, lblNewLabel1, -170, SpringLayout.EAST, contentPane);
		lblNewLabel1.setForeground(new Color(255, 255, 255));
		lblNewLabel1.setFont(new Font("Script MT Bold", Font.PLAIN, 28));
		contentPane.add(lblNewLabel1);
		
		JLabel lblNewLabel2 = new JLabel("Nombre del producto *");
		lblNewLabel2.setForeground(new Color(255, 255, 255));
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel2, 142, SpringLayout.NORTH, contentPane);
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel2, 32, SpringLayout.WEST, contentPane);
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, lblNewLabel2);
		contentPane.add(lblNewLabel2);
		
		JLabel lblNewLabel3 = new JLabel("Rellena los siguientes campos.");
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel3, 69, SpringLayout.NORTH, contentPane);
		slcontentPane.putConstraint(SpringLayout.NORTH, comboBoxSeccionCatalogo, 16, SpringLayout.SOUTH, lblNewLabel3);
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel3, 0, SpringLayout.WEST, lblNewLabel);
		lblNewLabel3.setForeground(new Color(255, 255, 255));
		lblNewLabel3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNewLabel3);
		
		JLabel lblNewLabel4 = new JLabel("*Campos obligatorios");
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel4, 0, SpringLayout.NORTH, lblNewLabel3);
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel4, 6, SpringLayout.EAST, lblNewLabel3);
		lblNewLabel4.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel4);
		
		textFieldNombreProducto = new JTextField();
		slcontentPane.putConstraint(SpringLayout.SOUTH, comboBoxSeccionCatalogo, -18, SpringLayout.NORTH, textFieldNombreProducto);
		slcontentPane.putConstraint(SpringLayout.NORTH, textFieldNombreProducto, -3, SpringLayout.NORTH, lblNewLabel2);
		slcontentPane.putConstraint(SpringLayout.WEST, textFieldNombreProducto, 0, SpringLayout.WEST, comboBoxSeccionCatalogo);
		slcontentPane.putConstraint(SpringLayout.EAST, textFieldNombreProducto, -116, SpringLayout.EAST, contentPane);
		contentPane.add(textFieldNombreProducto);
		textFieldNombreProducto.setColumns(10);
		
		JLabel lblNewLabel5 = new JLabel("Marca del producto *");
		lblNewLabel5.setForeground(new Color(255, 255, 255));
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel5, 20, SpringLayout.SOUTH, lblNewLabel2);
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel5, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel5);
		
		textFieldMarcaProducto = new JTextField();
		slcontentPane.putConstraint(SpringLayout.NORTH, textFieldMarcaProducto, -3, SpringLayout.NORTH, lblNewLabel5);
		slcontentPane.putConstraint(SpringLayout.WEST, textFieldMarcaProducto, 16, SpringLayout.EAST, lblNewLabel5);
		slcontentPane.putConstraint(SpringLayout.EAST, textFieldMarcaProducto, 0, SpringLayout.EAST, textFieldNombreProducto);
		contentPane.add(textFieldMarcaProducto);
		textFieldMarcaProducto.setColumns(10);
		
		JLabel lblNewLabel6 = new JLabel("Descripción *");
		lblNewLabel6.setForeground(new Color(255, 255, 255));
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel6, 22, SpringLayout.SOUTH, lblNewLabel5);
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel6, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel6);
		
		textAreaDescripcionProducto = new JTextArea();
		slcontentPane.putConstraint(SpringLayout.NORTH, textAreaDescripcionProducto, 0, SpringLayout.NORTH, lblNewLabel6);
		slcontentPane.putConstraint(SpringLayout.WEST, textAreaDescripcionProducto, 0, SpringLayout.WEST, comboBoxSeccionCatalogo);
		slcontentPane.putConstraint(SpringLayout.SOUTH, textAreaDescripcionProducto, 85, SpringLayout.NORTH, lblNewLabel6);
		slcontentPane.putConstraint(SpringLayout.EAST, textAreaDescripcionProducto, 0, SpringLayout.EAST, textFieldNombreProducto);
		contentPane.add(textAreaDescripcionProducto);
		
		JLabel lblNewLabel51 = new JLabel("Precio del producto *");
		lblNewLabel51.setForeground(new Color(255, 255, 255));
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel51, 96, SpringLayout.SOUTH, lblNewLabel6);
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel51, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel51);
		
		textFieldPecioProducto = new JTextField();
		slcontentPane.putConstraint(SpringLayout.NORTH, textFieldPecioProducto, -3, SpringLayout.NORTH, lblNewLabel51);
		slcontentPane.putConstraint(SpringLayout.WEST, textFieldPecioProducto, 0, SpringLayout.WEST, comboBoxSeccionCatalogo);
		textFieldPecioProducto.setColumns(10);
		contentPane.add(textFieldPecioProducto);
		
		JLabel lblNewLabel511 = new JLabel("Descuento del producto ");
		lblNewLabel511.setForeground(new Color(255, 255, 255));
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel511, 0, SpringLayout.NORTH, lblNewLabel51);
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel511, 31, SpringLayout.EAST, textFieldPecioProducto);
		contentPane.add(lblNewLabel511);
		
		textFieldDescuentoProducto = new JTextField();
		slcontentPane.putConstraint(SpringLayout.NORTH, textFieldDescuentoProducto, -3, SpringLayout.NORTH, lblNewLabel51);
		slcontentPane.putConstraint(SpringLayout.WEST, textFieldDescuentoProducto, 5, SpringLayout.EAST, lblNewLabel511);
		textFieldDescuentoProducto.setColumns(10);
		contentPane.add(textFieldDescuentoProducto);
		
		JLabel lblNewLabel7 = new JLabel("Número en existencia *");
		lblNewLabel7.setForeground(new Color(255, 255, 255));
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel7, 22, SpringLayout.SOUTH, lblNewLabel51);
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel7, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel7);
		
		textFieldNumeroEnExistenciaProducto = new JTextField();
		slcontentPane.putConstraint(SpringLayout.NORTH, textFieldNumeroEnExistenciaProducto, -3, SpringLayout.NORTH, lblNewLabel7);
		slcontentPane.putConstraint(SpringLayout.EAST, textFieldNumeroEnExistenciaProducto, 0, SpringLayout.EAST, textFieldPecioProducto);
		textFieldNumeroEnExistenciaProducto.setColumns(10);
		contentPane.add(textFieldNumeroEnExistenciaProducto);
		
		JButton btnAgregar = new JButton("Agregar");
		slcontentPane.putConstraint(SpringLayout.SOUTH, btnAgregar, -26, SpringLayout.SOUTH, contentPane);
		slcontentPane.putConstraint(SpringLayout.EAST, btnAgregar, -289, SpringLayout.EAST, contentPane);
		btnAgregar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnAgregar);
		
		JButton btnCancelar = new JButton("Cancelar");
		slcontentPane.putConstraint(SpringLayout.WEST, btnCancelar, 26, SpringLayout.EAST, btnAgregar);
		slcontentPane.putConstraint(SpringLayout.SOUTH, btnCancelar, 0, SpringLayout.SOUTH, btnAgregar);
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
