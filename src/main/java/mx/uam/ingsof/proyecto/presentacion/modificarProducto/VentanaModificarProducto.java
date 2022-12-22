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
		SpringLayout slcontentPane = new SpringLayout();
		contentPane.setLayout(slcontentPane);
		
		JLabel lblNewLabel = new JLabel("Selecciona el producto que deseas modificar");
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 69, SpringLayout.NORTH, contentPane);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNewLabel);
		
		comboBoxProductos = new JComboBox<>();
		slcontentPane.putConstraint(SpringLayout.NORTH, comboBoxProductos, -4, SpringLayout.NORTH, lblNewLabel);
		slcontentPane.putConstraint(SpringLayout.WEST, comboBoxProductos, 20, SpringLayout.EAST, lblNewLabel);
		slcontentPane.putConstraint(SpringLayout.EAST, comboBoxProductos, -225, SpringLayout.EAST, contentPane);
		contentPane.add(comboBoxProductos);
		
		JLabel lblNewLabel1 = new JLabel("Modificar Producto");
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel1, 10, SpringLayout.NORTH, contentPane);
		slcontentPane.putConstraint(SpringLayout.EAST, lblNewLabel1, 0, SpringLayout.EAST, comboBoxProductos);
		lblNewLabel1.setForeground(new Color(255, 255, 255));
		lblNewLabel1.setFont(new Font("Script MT Bold", Font.PLAIN, 28));
		contentPane.add(lblNewLabel1);
		
		JLabel lblNewLabel2 = new JLabel("Nombre del producto *");
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, lblNewLabel2);
		lblNewLabel2.setForeground(new Color(255, 255, 255));
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel2, 142, SpringLayout.NORTH, contentPane);
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel2, 32, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel2);
		
		JLabel lblNewLabel3 = new JLabel("Rellena los siguientes campos que deseas modificar del producto seleccionado");
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel3, 21, SpringLayout.SOUTH, lblNewLabel);
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel3, 0, SpringLayout.WEST, lblNewLabel);
		lblNewLabel3.setForeground(new Color(255, 255, 255));
		lblNewLabel3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNewLabel3);
		
		textFieldNombreProducto = new JTextField();
		slcontentPane.putConstraint(SpringLayout.NORTH, textFieldNombreProducto, -3, SpringLayout.NORTH, lblNewLabel2);
		slcontentPane.putConstraint(SpringLayout.WEST, textFieldNombreProducto, 5, SpringLayout.EAST, lblNewLabel2);
		slcontentPane.putConstraint(SpringLayout.EAST, textFieldNombreProducto, -87, SpringLayout.EAST, contentPane);
		contentPane.add(textFieldNombreProducto);
		textFieldNombreProducto.setColumns(10);
		
		JLabel lblNewLabel5 = new JLabel("Marca del producto *");
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel5, 32, SpringLayout.WEST, contentPane);
		lblNewLabel5.setForeground(new Color(255, 255, 255));
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel5, 20, SpringLayout.SOUTH, lblNewLabel2);
		contentPane.add(lblNewLabel5);
		
		textFieldMarcaProducto = new JTextField();
		slcontentPane.putConstraint(SpringLayout.NORTH, textFieldMarcaProducto, -3, SpringLayout.NORTH, lblNewLabel5);
		slcontentPane.putConstraint(SpringLayout.WEST, textFieldMarcaProducto, 13, SpringLayout.EAST, lblNewLabel5);
		contentPane.add(textFieldMarcaProducto);
		textFieldMarcaProducto.setColumns(10);
		
		JLabel lblNewLabel6 = new JLabel("Descripción *");
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel6, 32, SpringLayout.WEST, contentPane);
		lblNewLabel6.setForeground(new Color(255, 255, 255));
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel6, 22, SpringLayout.SOUTH, lblNewLabel5);
		contentPane.add(lblNewLabel6);
		
		textAreaDescripcionProducto = new JTextArea();
		slcontentPane.putConstraint(SpringLayout.EAST, textFieldMarcaProducto, 0, SpringLayout.EAST, textAreaDescripcionProducto);
		slcontentPane.putConstraint(SpringLayout.NORTH, textAreaDescripcionProducto, 0, SpringLayout.NORTH, lblNewLabel6);
		slcontentPane.putConstraint(SpringLayout.WEST, textAreaDescripcionProducto, 51, SpringLayout.EAST, lblNewLabel6);
		slcontentPane.putConstraint(SpringLayout.SOUTH, textAreaDescripcionProducto, 85, SpringLayout.NORTH, lblNewLabel6);
		slcontentPane.putConstraint(SpringLayout.EAST, textAreaDescripcionProducto, -87, SpringLayout.EAST, contentPane);
		contentPane.add(textAreaDescripcionProducto);
		
		JLabel lblNewLabel51 = new JLabel("Precio del producto *");
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel51, 32, SpringLayout.WEST, contentPane);
		lblNewLabel51.setForeground(new Color(255, 255, 255));
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel51, 96, SpringLayout.SOUTH, lblNewLabel6);
		contentPane.add(lblNewLabel51);
		
		textFieldPecioProducto = new JTextField();
		slcontentPane.putConstraint(SpringLayout.NORTH, textFieldPecioProducto, -3, SpringLayout.NORTH, lblNewLabel51);
		slcontentPane.putConstraint(SpringLayout.WEST, textFieldPecioProducto, 13, SpringLayout.EAST, lblNewLabel51);
		textFieldPecioProducto.setColumns(10);
		contentPane.add(textFieldPecioProducto);
		
		JLabel lblNewLabel511 = new JLabel("Descuento del producto ");
		lblNewLabel511.setForeground(new Color(255, 255, 255));
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel511, 0, SpringLayout.NORTH, lblNewLabel51);
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel511, 31, SpringLayout.EAST, textFieldPecioProducto);
		contentPane.add(lblNewLabel511);
		
		textFieldDescuentoProducto = new JTextField();
		slcontentPane.putConstraint(SpringLayout.WEST, textFieldDescuentoProducto, 5, SpringLayout.EAST, lblNewLabel511);
		slcontentPane.putConstraint(SpringLayout.EAST, textFieldDescuentoProducto, -87, SpringLayout.EAST, contentPane);
		slcontentPane.putConstraint(SpringLayout.NORTH, textFieldDescuentoProducto, -3, SpringLayout.NORTH, lblNewLabel51);
		textFieldDescuentoProducto.setColumns(10);
		contentPane.add(textFieldDescuentoProducto);
		
		JLabel lblNewLabel7 = new JLabel("Número en existencia *");
		slcontentPane.putConstraint(SpringLayout.WEST, lblNewLabel7, 32, SpringLayout.WEST, contentPane);
		lblNewLabel7.setForeground(new Color(255, 255, 255));
		slcontentPane.putConstraint(SpringLayout.NORTH, lblNewLabel7, 22, SpringLayout.SOUTH, lblNewLabel51);
		contentPane.add(lblNewLabel7);
		
		textFieldNumeroEnExistenciaProducto = new JTextField();
		slcontentPane.putConstraint(SpringLayout.NORTH, textFieldNumeroEnExistenciaProducto, -3, SpringLayout.NORTH, lblNewLabel7);
		slcontentPane.putConstraint(SpringLayout.EAST, textFieldNumeroEnExistenciaProducto, 0, SpringLayout.EAST, textFieldPecioProducto);
		textFieldNumeroEnExistenciaProducto.setColumns(10);
		contentPane.add(textFieldNumeroEnExistenciaProducto);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnModificar);
		
		JButton btnCancelar = new JButton("Cancelar");
		slcontentPane.putConstraint(SpringLayout.WEST, btnCancelar, 370, SpringLayout.WEST, contentPane);
		slcontentPane.putConstraint(SpringLayout.SOUTH, btnCancelar, -26, SpringLayout.SOUTH, contentPane);
		slcontentPane.putConstraint(SpringLayout.NORTH, btnModificar, 0, SpringLayout.NORTH, btnCancelar);
		slcontentPane.putConstraint(SpringLayout.EAST, btnModificar, -21, SpringLayout.WEST, btnCancelar);
		btnCancelar.setBackground(new Color(255, 255, 255));
		contentPane.add(btnCancelar);
		
		JLabel lblSeccinDelProducto = new JLabel("Sección del producto");
		slcontentPane.putConstraint(SpringLayout.NORTH, lblSeccinDelProducto, 0, SpringLayout.NORTH, lblNewLabel7);
		slcontentPane.putConstraint(SpringLayout.WEST, lblSeccinDelProducto, 31, SpringLayout.EAST, textFieldNumeroEnExistenciaProducto);
		lblSeccinDelProducto.setForeground(Color.WHITE);
		contentPane.add(lblSeccinDelProducto);
		
		rdbtnVerInformacion = new JRadioButton("Ver información");
		rdbtnVerInformacion.setBackground(new Color(153, 204, 153));
		slcontentPane.putConstraint(SpringLayout.NORTH, rdbtnVerInformacion, -4, SpringLayout.NORTH, lblNewLabel);
		slcontentPane.putConstraint(SpringLayout.EAST, rdbtnVerInformacion, 0, SpringLayout.EAST, textFieldNombreProducto);
		contentPane.add(rdbtnVerInformacion);
		
		textFieldSeccionCatalogo = new JTextField();
		slcontentPane.putConstraint(SpringLayout.NORTH, textFieldSeccionCatalogo, -3, SpringLayout.NORTH, lblNewLabel7);
		slcontentPane.putConstraint(SpringLayout.WEST, textFieldSeccionCatalogo, 0, SpringLayout.WEST, textFieldDescuentoProducto);
		slcontentPane.putConstraint(SpringLayout.EAST, textFieldSeccionCatalogo, 0, SpringLayout.EAST, textFieldNombreProducto);
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
		seccion.getNombre();
		
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
