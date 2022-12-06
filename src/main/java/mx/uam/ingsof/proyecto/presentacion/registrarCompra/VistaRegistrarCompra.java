package mx.uam.ingsof.proyecto.presentacion.registrarCompra;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

@SuppressWarnings("serial")
@Component
public class VistaRegistrarCompra extends JFrame {

	private ControlRegistrarCompra controlRegistrarCompra;

	private JPanel contentPane;
	private JTextField textNombreProducto;
	private JTextField textCantidad;
	private JTextField textMarca;
	private JTextField textPrecio;
	private JScrollPane scrollPane;
	private JTable tabla;
	private JComboBox<String> comboBoxEmpleado;

	private List<Empleado> listaEmpleados;
	private Empleado empleado;
	private String nomProducto;
	private String marca;
	private String cantidad;
	private String precio;
	private String fecha;

	private int i = 0, j = 0;

	/**
	 * Launch the application. /** Create the frame.
	 */
	public VistaRegistrarCompra() {
		fecha = fechaActual();
		setTitle("Registrar Compra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 491);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(89, 126, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(20, 11, 632, 430);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel labelTitulo = new JLabel("Registrar Compra");
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		labelTitulo.setBounds(205, 11, 208, 34);
		panel.add(labelTitulo);

		JLabel labelCamObli = new JLabel("*Campos obligatorios*");
		labelCamObli.setForeground(new Color(204, 0, 0));
		labelCamObli.setBackground(new Color(204, 0, 0));
		labelCamObli.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelCamObli.setBounds(25, 50, 141, 14);
		panel.add(labelCamObli);

		JLabel labelIdEmpleado = new JLabel("Id Empleado *");
		labelIdEmpleado.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelIdEmpleado.setBounds(25, 75, 106, 17);
		panel.add(labelIdEmpleado);

		JLabel lblNombreProducto = new JLabel("Nombre Producto*");
		lblNombreProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreProducto.setBounds(25, 103, 141, 17);
		panel.add(lblNombreProducto);

		JLabel lblCantidad = new JLabel("Cantidad *");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCantidad.setBounds(25, 131, 141, 17);
		panel.add(lblCantidad);

		JLabel lblMarca = new JLabel("Marca *");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMarca.setBounds(335, 103, 67, 17);
		panel.add(lblMarca);

		JLabel lblPrecio = new JLabel("Precio *");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecio.setBounds(334, 131, 67, 17);
		panel.add(lblPrecio);

		comboBoxEmpleado = new JComboBox<>();
		comboBoxEmpleado.setBounds(176, 74, 147, 22);
		panel.add(comboBoxEmpleado);

		textNombreProducto = new JTextField();
		textNombreProducto.setBounds(176, 103, 148, 20);
		panel.add(textNombreProducto);
		textNombreProducto.setColumns(10);

		textCantidad = new JTextField();
		textCantidad.setText("");
		textCantidad.setBounds(176, 131, 148, 20);
		panel.add(textCantidad);
		textCantidad.setColumns(10);

		textMarca = new JTextField();
		textMarca.setBounds(406, 103, 147, 20);
		panel.add(textMarca);
		textMarca.setColumns(10);

		textPrecio = new JTextField();
		textPrecio.setBounds(406, 131, 147, 20);
		panel.add(textPrecio);
		textPrecio.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha *");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(395, 56, 67, 17);
		panel.add(lblFecha);

		JLabel lblFecha_1 = new JLabel(fecha);
		lblFecha_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha_1.setBounds(472, 56, 95, 17);
		panel.add(lblFecha_1);

		JButton btnAgregarLista = new JButton("Agregar a lista");
		btnAgregarLista.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAgregarLista.setBackground(new Color(0, 158, 15));
		btnAgregarLista.setBounds(259, 162, 132, 23);

		btnAgregarLista.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAgregarLista) {

					int indexEmpleado = comboBoxEmpleado.getSelectedIndex();
					int cantidadProductos = 0;
					double precioProducto = 0.0;
					empleado = listaEmpleados.get(indexEmpleado);
					nomProducto = textNombreProducto.getText();
					marca = textMarca.getText();
					cantidad = textCantidad.getText();
					precio = textPrecio.getText();

					if (esEntero(cantidad)) {
						cantidadProductos = Integer.parseInt(cantidad);
					}
					if (esReal(precio)) {
						precioProducto = Double.parseDouble(precio);
					}

					if (nomProducto.equals("") || marca.equals("") || cantidad.equals("") || precio.equals("") || (!esEntero(cantidad)) || (!esReal(precio))) {
						muestraDialogoConMensaje("¡Debes ingresar todos los datos deacuerdo a su tipo!");
					} else {
						if (empleado == null || cantidadProductos <= 0 || precioProducto <= 0) {
							muestraDialogoConMensaje("Elige un ID de empleado");
						} else {
							comboBoxEmpleado.setEnabled(false);
							if (controlRegistrarCompra.crearCompra(empleado.getIdEmpleado(), nomProducto, marca,
									cantidadProductos, precioProducto, fecha)) {
								while (j < 5) {
									tabla.setValueAt(nomProducto, i, j);
									j++;
									tabla.setValueAt(marca, i, j);
									j++;
									tabla.setValueAt(cantidadProductos, i, j);
									j++;
									tabla.setValueAt(precioProducto, i, j);
									j++;
									tabla.setValueAt(precioProducto * cantidadProductos, i, j);
									j++;

								}
								j = 0;
								i++;
								textNombreProducto.setText("");
								textMarca.setText("");
								textCantidad.setText("");
								textPrecio.setText("");
							} else {
								muestraDialogoConMensaje("La compra no pudo ser creada");
							}
						}
					}

				}

			}
		});
		panel.add(btnAgregarLista);

		JButton btnAgregarCompra = new JButton("Agregar compra");
		btnAgregarCompra.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAgregarCompra.setBackground(new Color(0, 158, 15));
		btnAgregarCompra.setBounds(180, 399, 132, 23);
		btnAgregarCompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == btnAgregarCompra) {
					comboBoxEmpleado.setEnabled(true);
					int m = 0, n = 0;
					while (m < i) {
						while (n < 5) {
							tabla.setValueAt(null, m, n);
							n++;
							tabla.setValueAt(null, m, n);
							n++;
							tabla.setValueAt(null, m, n);
							n++;
							tabla.setValueAt(null, m, n);
							n++;
							tabla.setValueAt(null, m, n);
							n++;

						}
						n = 0;
						m++;
					}
					i = 0;
					if(controlRegistrarCompra.guardaCompras()) {
						muestraDialogoConMensaje("Venta creada con exito!");
						setVisible(false);
					}
					else {
						muestraDialogoConMensaje("La venta no pudo ser creada");
						setVisible(false);
					}
					
				}

			}
		});
		panel.add(btnAgregarCompra);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelar.setBackground(new Color(204, 0, 0));
		btnCancelar.setBounds(322, 399, 121, 23);
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource() == btnCancelar) {
					comboBoxEmpleado.setEnabled(true);
					int m = 0, n = 0;
					while (m < i) {
						while (n < 5) {
							tabla.setValueAt(null, m, n);
							n++;
							tabla.setValueAt(null, m, n);
							n++;
							tabla.setValueAt(null, m, n);
							n++;
							tabla.setValueAt(null, m, n);
							n++;
							tabla.setValueAt(null, m, n);
							n++;

						}
						n = 0;
						m++;
					}
					i = 0;
					if(controlRegistrarCompra.limpiaLista())
						muestraDialogoConMensaje("¡Compra cancelada exitosamente!");
					else
						muestraDialogoConMensaje("¡Evento desafortunado!, la venta no pudo ser creada");
					setVisible(false);
				}
				
			}
		});
		panel.add(btnCancelar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 196, 612, 190);
		panel.add(scrollPane);
		int k,o;
		Object[] colName = { "Nombre producto", "Marca", "Cantidad", "Precio", "Precio Total" };
		Object[][] datos = new Object[5][10];
		for (k = 0; k < 5; k++) {//renglones
			for (o = 0; o < 5; o++) {//columnas
				datos[k][o] = null;
			}
		}
	
		DefaultTableModel model = new DefaultTableModel(datos, colName);
		tabla = new JTable(model) {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				case 3:
					return Double.class;
				case 4:
					return Double.class;
				default:
					return String.class;
				}

			}
		};

		tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
		tabla.setBounds(30, 170, 465, 207);
		tabla.setEnabled(false);
		scrollPane.setVisible(true);
		scrollPane.setViewportView(tabla);

	}

	public void muestra(ControlRegistrarCompra control, List<Empleado> listaEmpleados) {
		this.controlRegistrarCompra = control;
		this.listaEmpleados = listaEmpleados;
		DefaultComboBoxModel<String> comboBoxModeloEmpleado = new DefaultComboBoxModel<>();
		for (Empleado empleado : listaEmpleados) {
			comboBoxModeloEmpleado.addElement(Long.toString(empleado.getIdEmpleado()));
		}
		comboBoxEmpleado.setModel(comboBoxModeloEmpleado);
		setVisible(true);
	}

	public void muestraDialogoConMensaje(String mensaje) {
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

	public String fechaActual() {
		Date facha = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("d/MM/YYYY");
		return formatoFecha.format(facha);
	}

	public boolean esEntero(String s) {
		if (s == null || s.equals("")) {
			return false;
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	public boolean esReal(String cad) {
		try {
			Double.parseDouble(cad);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
