package mx.uam.ingsof.proyecto.presentacion.registrarVenta;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.bean.BeanVenta;
import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;
import mx.uam.ingsof.proyecto.negocio.modelo.Empleado;
import mx.uam.ingsof.proyecto.negocio.modelo.Producto;
import mx.uam.ingsof.proyecto.negocio.modelo.VentaProducto;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

@SuppressWarnings("serial")
@Component
public class VentanaRegistrarVenta extends JFrame {

	private ControlRegistrarVenta control;

	private JPanel contentPane;
	private JLabel fecha;
	private JComboBox<String> comboBoxCliente;
	private JComboBox<String> comboBoxEmpleado;
	private JComboBox<String> comboBoxProducto;
	private JTextField cuadroFecha;
	private JTextField textoCantidad;
	private JScrollPane scrollPane;
	private JTable tabla;

	private List<Cliente> listaClientes;
	private List<Empleado> listaEmpleados;
	private List<Producto> listaProdutos;
	private List<VentaProducto> ventaProducto;

	private Cliente cliente;
	private Empleado empleado;
	private Producto productoSeleccionado;
	private int cantidadProductos;
	private String fechaActual;
	private int i, j;
	private VentaProducto prod;
	private BeanVenta beanVenta;

	public VentanaRegistrarVenta() {
		setTitle("Registro Venta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 791, 465);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(89, 126, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 755, 404);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("* Campos obligatorios *");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setForeground(new Color(204, 0, 0));
		lblNewLabel_1.setBackground(new Color(204, 0, 0));
		lblNewLabel_1.setBounds(140, 47, 149, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ID Cliente *");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(138, 72, 90, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("ID Empleado *");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(138, 99, 114, 22);
		panel.add(lblNewLabel_3);

		comboBoxCliente = new JComboBox<>();// ComboBox para cliente
		comboBoxCliente.setBounds(243, 72, 109, 22);
		panel.add(comboBoxCliente);

		comboBoxEmpleado = new JComboBox<>();// ComboBox para el empleado
		comboBoxEmpleado.setBounds(243, 98, 109, 22);
		panel.add(comboBoxEmpleado);

		JLabel lblNewLabel_4 = new JLabel("Nombre producto *");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(357, 72, 138, 20);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Cantidad *");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(359, 100, 122, 14);
		panel.add(lblNewLabel_5);

		comboBoxProducto = new JComboBox<>();// ComboBox para producto
		comboBoxProducto.setBounds(501, 70, 122, 22);
		panel.add(comboBoxProducto);

		textoCantidad = new JTextField();
		textoCantidad.setBounds(501, 98, 122, 20);
		panel.add(textoCantidad);
		textoCantidad.setColumns(10);

		JButton botonAgregarLista = new JButton("Agregar a lista");
		botonAgregarLista.setBackground(new Color(0, 158, 15));
		botonAgregarLista.setFont(new Font("Tahoma", Font.BOLD, 14));
		botonAgregarLista.setBounds(297, 131, 134, 23);
		i = 0;
		j = 0;
		prod = new VentaProducto();
		ventaProducto = new ArrayList<VentaProducto>();
		botonAgregarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabla.setBounds(300, 1700, 4650, 2070);
				tabla.setBounds(30, 170, 465, 207);
				if (e.getSource() == botonAgregarLista) {
					String cantidad = textoCantidad.getText();
					int indiceCliente = comboBoxCliente.getSelectedIndex();
					cliente = listaClientes.get(indiceCliente);
					
					int indiceEmpleado = comboBoxEmpleado.getSelectedIndex();
					empleado = listaEmpleados.get(indiceEmpleado);
					
					int indiceProducto = comboBoxProducto.getSelectedIndex();
					productoSeleccionado = listaProdutos.get(indiceProducto);
					
					if (esNumero(cantidad)) {
						cantidadProductos = Integer.parseInt(cantidad);
					}
					if (!esNumero(cantidad)) {
						muestraDialogoConMensaje("Por favor ingresa un numero valido");
					} else {
						if (cliente == null || empleado == null || productoSeleccionado == null || cantidadProductos <= 0) {
							muestraDialogoConMensaje(
									"Debes seleccionar los ID de cliente, empleado o producto, la cantidad debe ser mayor a cero");
						} else {
							comboBoxCliente.setEnabled(false);
							comboBoxEmpleado.setEnabled(false);

							prod = control.agregarProducto(productoSeleccionado, cantidadProductos);
							ventaProducto.add(prod);

							beanVenta = new BeanVenta();
							beanVenta.setIdProducto(productoSeleccionado.getIdProducto());
							beanVenta.setNombreProducto(productoSeleccionado.getNombre());
							beanVenta.setCantidad(cantidadProductos);
							beanVenta.setPrecio(productoSeleccionado.getPrecio());
							beanVenta.setPrecioTotal(productoSeleccionado.getPrecio() * cantidadProductos);

							while (j < 5) {
								tabla.setValueAt(beanVenta.getIdProducto(), i, j);
								j++;
								tabla.setValueAt(beanVenta.getNombreProducto(), i, j);
								j++;
								tabla.setValueAt(beanVenta.getCantidad(), i, j);
								j++;
								tabla.setValueAt(beanVenta.getPrecio(), i, j);
								j++;
								tabla.setValueAt(beanVenta.getPrecioTotal(), i, j);
								j++;

							}
							i++;
							j = 0;
							textoCantidad.setText("");
							cantidadProductos = 0;
							beanVenta = null;
						}
					}
				}
			}
		});
		panel.add(botonAgregarLista);

		JButton botonAgregarVenta = new JButton("Agregar venta");
		botonAgregarVenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == botonAgregarVenta) {
					comboBoxCliente.setEnabled(true);
					comboBoxEmpleado.setEnabled(true);
					
					int n = 0, m = 0;
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
					beanVenta = null;
					control.CrearVenta(cliente.getIdCliente(), empleado.getIdEmpleado(), fechaActual, ventaProducto);
					cliente = null;
					empleado = null;
					productoSeleccionado = null;
					cantidadProductos = 0;
					ventaProducto.clear();
				}
			}
		});
		botonAgregarVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		botonAgregarVenta.setBackground(new Color(0, 158, 15));
		botonAgregarVenta.setBounds(255, 369, 138, 23);
		panel.add(botonAgregarVenta);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == botonCancelar) {
					muestraDialogoConMensaje("Compra cancelada");
					comboBoxCliente.setEnabled(true);
					comboBoxEmpleado.setEnabled(true);
					int n = 0, m = 0;
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
					beanVenta = null;
					if(cliente != null)
						cliente = null;
					if(empleado != null)
						empleado = null;
					if(productoSeleccionado != null)
						productoSeleccionado = null;
					cantidadProductos = 0;
					if(ventaProducto.size() > 0)
						ventaProducto.clear();
					setVisible(false);
				}
			}
		});
		botonCancelar.setBackground(new Color(204, 0, 0));
		botonCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		botonCancelar.setBounds(399, 368, 96, 23);
		panel.add(botonCancelar);

		fecha = new JLabel("Fecha");
		fecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		fecha.setBounds(449, 48, 46, 14);
		panel.add(fecha);

		fechaActual = fechaActual();
		cuadroFecha = new JTextField();
		cuadroFecha.setText(fechaActual);
		cuadroFecha.setEditable(false);
		cuadroFecha.setBounds(501, 45, 86, 20);

		panel.add(cuadroFecha);
		cuadroFecha.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Registro de ventas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(259, 11, 226, 32);
		panel.add(lblNewLabel);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 170, 717, 190);
		Object[] colName = { "Id Producto", "Nombre producto", "Cantidad", "Precio", "Precio Total" };
		Object[][] datos = new Object[5][10];
		for (int k = 0; k < 5; k++) {
			for (int o = 0; o < 10; o++) {
				datos[i][j] = null;
			}
		}
		DefaultTableModel model = new DefaultTableModel(datos, colName);

		tabla = new JTable(model) {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings( { "unchecked", "rawtypes" } )
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Long.class;
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
		contentPane.add(scrollPane);
	}

	public void muestra(ControlRegistrarVenta controlRegistrarVenta, List<Cliente> clientes, List<Empleado> empleados,
			List<Producto> listaProductos) {
		this.control = controlRegistrarVenta;
		this.listaClientes = clientes;
		this.listaEmpleados = empleados;
		this.listaProdutos = listaProductos;
		DefaultComboBoxModel<String> comboBoxModeloCliente = new DefaultComboBoxModel<>();
		for (Cliente cliente : clientes) {
			comboBoxModeloCliente.addElement(Long.toString(cliente.getIdCliente()));
		}
		DefaultComboBoxModel<String> comboBoxModeloEmpleado = new DefaultComboBoxModel<>();
		for (Empleado empleado : empleados) {
			comboBoxModeloEmpleado.addElement(Long.toString(empleado.getIdEmpleado()));
		}
		DefaultComboBoxModel<String> comboBoxModeloProductos = new DefaultComboBoxModel<>();
		for (Producto producto : listaProductos) {
			comboBoxModeloProductos.addElement(producto.getNombre());
		}

		comboBoxCliente.setModel(comboBoxModeloCliente);
		comboBoxEmpleado.setModel(comboBoxModeloEmpleado);
		comboBoxProducto.setModel(comboBoxModeloProductos);
		setVisible(true);
	}

	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	public String fechaActual() {
		Date facha = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
		return formatoFecha.format(facha);
	}

	public boolean esNumero(String s) {
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
}
