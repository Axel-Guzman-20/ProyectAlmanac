package mx.uam.ingsof.proyecto.presentacion.modificarProducto;

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

import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.SeccionCatalogo;

import javax.swing.JRadioButton;

@SuppressWarnings("serial")
@Component
public class VentanaModificarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombreProducto;
	private JTextField textFieldMarcaProducto;
	private JTextField textFieldPecioProducto;
	private JTextField textFieldDescuentoProducto;
	private JTextField textFieldNumeroEnExistenciaProducto;
	private JComboBox <String> comboBoxProductos; 
	private JTextArea textAreaDescripcionProducto;
	private JRadioButton rdbtnVerInformacion; 
	
	private String seccionDelProducto=""; 
	private String productoSeleccionado=""; 
	
	private ControlModificarProducto control;
	private JTextField textFieldSeccionCatalogo;



	/**
	 * Create the frame.
	 */
	public VentanaModificarProducto() {
		setBackground(new Color(204, 204, 153));
		setTitle("Modificar Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Selecciona el producto que deseas modificar");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 69, SpringLayout.NORTH, contentPane);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel);
		
		comboBoxProductos = new JComboBox<>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBoxProductos, -4, SpringLayout.NORTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBoxProductos, 20, SpringLayout.EAST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboBoxProductos, -225, SpringLayout.EAST, contentPane);
		contentPane.add(comboBoxProductos);
		
		JLabel lblNewLabel_1 = new JLabel("Modificar Producto");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, comboBoxProductos);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Script MT Bold", Font.PLAIN, 28));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre del producto *");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 142, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 32, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Rellena los siguientes campos que deseas modificar del producto seleccionado");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 21, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNewLabel_3);
		
		textFieldNombreProducto = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldNombreProducto, -3, SpringLayout.NORTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldNombreProducto, 5, SpringLayout.EAST, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldNombreProducto, -87, SpringLayout.EAST, contentPane);
		contentPane.add(textFieldNombreProducto);
		textFieldNombreProducto.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Marca del producto *");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_5, 32, SpringLayout.WEST, contentPane);
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 20, SpringLayout.SOUTH, lblNewLabel_2);
		contentPane.add(lblNewLabel_5);
		
		textFieldMarcaProducto = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldMarcaProducto, -3, SpringLayout.NORTH, lblNewLabel_5);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldMarcaProducto, 13, SpringLayout.EAST, lblNewLabel_5);
		contentPane.add(textFieldMarcaProducto);
		textFieldMarcaProducto.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Descripción *");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_6, 32, SpringLayout.WEST, contentPane);
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_6, 22, SpringLayout.SOUTH, lblNewLabel_5);
		contentPane.add(lblNewLabel_6);
		
		textAreaDescripcionProducto = new JTextArea();
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldMarcaProducto, 0, SpringLayout.EAST, textAreaDescripcionProducto);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textAreaDescripcionProducto, 0, SpringLayout.NORTH, lblNewLabel_6);
		sl_contentPane.putConstraint(SpringLayout.WEST, textAreaDescripcionProducto, 51, SpringLayout.EAST, lblNewLabel_6);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textAreaDescripcionProducto, 85, SpringLayout.NORTH, lblNewLabel_6);
		sl_contentPane.putConstraint(SpringLayout.EAST, textAreaDescripcionProducto, -87, SpringLayout.EAST, contentPane);
		contentPane.add(textAreaDescripcionProducto);
		
		JLabel lblNewLabel_5_1 = new JLabel("Precio del producto *");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_5_1, 32, SpringLayout.WEST, contentPane);
		lblNewLabel_5_1.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_5_1, 96, SpringLayout.SOUTH, lblNewLabel_6);
		contentPane.add(lblNewLabel_5_1);
		
		textFieldPecioProducto = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldPecioProducto, -3, SpringLayout.NORTH, lblNewLabel_5_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldPecioProducto, 13, SpringLayout.EAST, lblNewLabel_5_1);
		textFieldPecioProducto.setColumns(10);
		contentPane.add(textFieldPecioProducto);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Descuento del producto ");
		lblNewLabel_5_1_1.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_5_1_1, 0, SpringLayout.NORTH, lblNewLabel_5_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_5_1_1, 31, SpringLayout.EAST, textFieldPecioProducto);
		contentPane.add(lblNewLabel_5_1_1);
		
		textFieldDescuentoProducto = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldDescuentoProducto, 5, SpringLayout.EAST, lblNewLabel_5_1_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldDescuentoProducto, -87, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldDescuentoProducto, -3, SpringLayout.NORTH, lblNewLabel_5_1);
		textFieldDescuentoProducto.setColumns(10);
		contentPane.add(textFieldDescuentoProducto);
		
		JLabel lblNewLabel_7 = new JLabel("Número en existencia *");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_7, 32, SpringLayout.WEST, contentPane);
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_7, 22, SpringLayout.SOUTH, lblNewLabel_5_1);
		contentPane.add(lblNewLabel_7);
		
		textFieldNumeroEnExistenciaProducto = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldNumeroEnExistenciaProducto, -3, SpringLayout.NORTH, lblNewLabel_7);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldNumeroEnExistenciaProducto, 0, SpringLayout.EAST, textFieldPecioProducto);
		textFieldNumeroEnExistenciaProducto.setColumns(10);
		contentPane.add(textFieldNumeroEnExistenciaProducto);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnModificar);
		
		JButton btnCancelar = new JButton("Cancelar");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCancelar, 370, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnCancelar, -26, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnModificar, 0, SpringLayout.NORTH, btnCancelar);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnModificar, -21, SpringLayout.WEST, btnCancelar);
		btnCancelar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnCancelar);
		
		JLabel lblSeccinDelProducto = new JLabel("Sección del producto");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSeccinDelProducto, 0, SpringLayout.NORTH, lblNewLabel_7);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSeccinDelProducto, 31, SpringLayout.EAST, textFieldNumeroEnExistenciaProducto);
		lblSeccinDelProducto.setForeground(Color.WHITE);
		contentPane.add(lblSeccinDelProducto);
		
		rdbtnVerInformacion = new JRadioButton("Ver información");
		rdbtnVerInformacion.setBackground(new Color(153, 204, 153));
		sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnVerInformacion, -4, SpringLayout.NORTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, rdbtnVerInformacion, 0, SpringLayout.EAST, textFieldNombreProducto);
		contentPane.add(rdbtnVerInformacion);
		
		textFieldSeccionCatalogo = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldSeccionCatalogo, -3, SpringLayout.NORTH, lblNewLabel_7);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldSeccionCatalogo, 0, SpringLayout.WEST, textFieldDescuentoProducto);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldSeccionCatalogo, 0, SpringLayout.EAST, textFieldNombreProducto);
		textFieldSeccionCatalogo.setColumns(10);
		contentPane.add(textFieldSeccionCatalogo);
		
		rdbtnVerInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(rdbtnVerInformacion.isSelected()) {
					muestraInformacionProducto();
				}else {
					
					textFieldNombreProducto.setEditable(true);
					textFieldMarcaProducto.setEditable(true);
					textAreaDescripcionProducto.setEditable(true);
					textFieldPecioProducto.setEditable(true);
					textFieldDescuentoProducto.setEditable(true);
					textFieldNumeroEnExistenciaProducto.setEditable(true);
					textFieldSeccionCatalogo.setEditable(false);
					comboBoxProductos.setEnabled(true); 
					
					textFieldNombreProducto.setText("");
					textFieldMarcaProducto.setText("");
					textAreaDescripcionProducto.setText("");
					textFieldPecioProducto.setText("");
					textFieldDescuentoProducto.setText("");
					textFieldNumeroEnExistenciaProducto.setText("");
					textFieldSeccionCatalogo.setText("");
					
				}
			}
		});
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				control.modificaProducto(productoSeleccionado, textFieldNombreProducto.getText(), textFieldMarcaProducto.getText(), textAreaDescripcionProducto.getText(), textFieldPecioProducto.getText(),
						textFieldDescuentoProducto.getText(),textFieldNumeroEnExistenciaProducto.getText());
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.termina();
			}
		});
		
	}
	
	public void muestra(ControlModificarProducto control, List <Producto> productos) {
		
		this.control = control;
		
		textFieldNombreProducto.setText("");
		textFieldMarcaProducto.setText("");
		textAreaDescripcionProducto.setText("");
		textFieldPecioProducto.setText("");
		textFieldDescuentoProducto.setText("");
		textFieldNumeroEnExistenciaProducto.setText("");
		textFieldSeccionCatalogo.setText("");
		
		DefaultComboBoxModel <String> comboBoxModel = new DefaultComboBoxModel <>();
		
		for(Producto producto:productos) {
			comboBoxModel.addElement(producto.getNombre());
		}
		
		comboBoxProductos.setModel(comboBoxModel);
		
		setVisible(true);
		
	}
	
	public void muestraInformacionProducto() {
		
		productoSeleccionado = (String) comboBoxProductos.getSelectedItem();
		
		Producto producto=control.obtenerProducto(productoSeleccionado); 
		SeccionCatalogo seccion=control.obtenerSeccionCatalogoDelProducto(producto);
		seccionDelProducto=seccion.getNombre();
		
		if(producto!=null) {
			textFieldNombreProducto.setText(producto.getNombre());
			textFieldMarcaProducto.setText(producto.getMarca());
			textAreaDescripcionProducto.setText(producto.getDescripcion());
			textFieldPecioProducto.setText(""+producto.getPrecio());
			textFieldDescuentoProducto.setText(""+producto.getDescuento());
			textFieldNumeroEnExistenciaProducto.setText(""+producto.getExistencia());
			textFieldSeccionCatalogo.setText(""+seccion.getNombre());
		}else {
			textFieldNombreProducto.setText("");
			textFieldMarcaProducto.setText("");
			textAreaDescripcionProducto.setText("");
			textFieldPecioProducto.setText("");
			textFieldDescuentoProducto.setText("");
			textFieldNumeroEnExistenciaProducto.setText("");
			textFieldSeccionCatalogo.setText("");
		}
		
		textFieldNombreProducto.setEditable(false);
		textFieldMarcaProducto.setEditable(false);
		textAreaDescripcionProducto.setEditable(false);
		textFieldPecioProducto.setEditable(false);
		textFieldDescuentoProducto.setEditable(false);
		textFieldNumeroEnExistenciaProducto.setEditable(false);
		textFieldSeccionCatalogo.setEditable(false);
		comboBoxProductos.setEnabled(false);
		
		
	}
	
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
}
