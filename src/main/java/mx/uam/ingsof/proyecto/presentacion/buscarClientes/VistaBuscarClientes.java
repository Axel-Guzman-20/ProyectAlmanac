package mx.uam.ingsof.proyecto.presentacion.buscarClientes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ingsof.proyecto.negocio.modelo.Cliente;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;


@Component
public class VistaBuscarClientes extends JFrame implements Serializable {

	/**
	 * Indica la version de interfaz de usuario de la HU-07
	 */
	private static final long serialVersionUID = 1L;
	private ControlBuscarClientes controlBuscarClientes;
	private JPanel contentPane;
	private JTextField textoIdCliente;
	private JTextField textoNombreCliente;
	private JTable table;
	private DefaultTableModel tableModel;
	private int i = 0;
	private int j = 0;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public VistaBuscarClientes() {
		Font tipoLetra = new Font("Tahoma", Font.BOLD, 14);
		Font tipoLetra1 = new Font("Tahoma", Font.BOLD, 12);
		setTitle("Buscar Clientes");
		setBounds(100, 100, 782, 428);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(89, 126, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 746, 367);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel buscarCliente = new JLabel("Búsqueda de Clientes");
		buscarCliente.setFont(new Font("Tahoma", Font.BOLD, 22));
		buscarCliente.setBounds(255, 11, 242, 33);
		panel.add(buscarCliente);
		
		String date;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		date = dateFormat.format(new Date());
		JLabel fecha = new JLabel(date);
		fecha.setFont(tipoLetra);
		fecha.setBounds(485, 55, 89, 14);
		panel.add(fecha);

		JLabel aviso = new JLabel("*Llena los campos  acorde a la búsqueda*");
		aviso.setForeground(new Color(255, 0, 0));
		aviso.setFont(tipoLetra1);
		aviso.setBounds(160, 71, 257, 14);
		panel.add(aviso);

		JLabel idCliente = new JLabel("Id Cliente");
		idCliente.setFont(tipoLetra);
		idCliente.setBounds(170, 97, 78, 17);
		panel.add(idCliente);

		textoIdCliente = new JTextField();
		textoIdCliente.setBounds(247, 97, 105, 20);
		panel.add(textoIdCliente);
		textoIdCliente.setColumns(10);

		JLabel nombre = new JLabel("Nombre Cliente");
		nombre.setFont(tipoLetra);
		nombre.setBounds(373, 96, 114, 17);
		panel.add(nombre);

		textoNombreCliente = new JTextField();
		textoNombreCliente.setColumns(10);
		textoNombreCliente.setBounds(485, 95, 105, 20);
		panel.add(textoNombreCliente);

		JLabel lblNewLabel = new JLabel(
				"Datos Generales del Cliente--------------------------------------------------------------------------------------\r\n");
		lblNewLabel.setFont(tipoLetra);
		lblNewLabel.setBounds(32, 152, 704, 23);
		panel.add(lblNewLabel);

		JButton buscar = new JButton("Buscar");
		buscar.setFont(tipoLetra1);
		buscar.setBackground(new Color(0, 158, 15));
		buscar.setBounds(257, 128, 89, 23);
		panel.add(buscar);

		JButton limpiar = new JButton("Limpiar");
		limpiar.setBackground(new Color(89, 126, 170));
		limpiar.setFont(tipoLetra1);
		limpiar.setBounds(383, 128, 89, 23);
		panel.add(limpiar);

		JButton regresar = new JButton("Regresar");
		regresar.setBackground(new Color(0, 158, 15));
		regresar.setFont(tipoLetra1);
		regresar.setBounds(562, 337, 89, 23);
		panel.add(regresar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBounds(32, 189, 704, 137);
		panel.add(scrollPane);
		int k;
		int o;
		Object[] colName = { "Id Cliente", "Nombre", "Correo", "Dirección", "Telefono" };
		Object[][] datos = new Object[10][5];
		for (k = 0; k < 10; k++) {
			for (o = 0; o <5; o++) {
				datos[k][o] = null;
			}
		}

		tableModel = new DefaultTableModel(datos, colName);
		table = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			@Override
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Long.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				default:
					return String.class;
				}

			}
		};
		table.setBackground(SystemColor.inactiveCaption);
		table.setFont(tipoLetra);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		/***********************************
		 * 
		 * 
		 * EVENTOS DE LOS BOTONES Buscar, Limpiar, Regresar
		 * 
		 ***********************************/
		buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limpiaTabla();
				if (e.getSource() == buscar && (textoIdCliente.getText().equals("") && textoNombreCliente.getText().equals("")))
					controlBuscarClientes.buscaAll();
				if (!textoIdCliente.getText().equals("") && textoNombreCliente.getText().equals(""))
					controlBuscarClientes.buscaById(textoIdCliente.getText());
				if (textoIdCliente.getText().equals("") && !textoNombreCliente.getText().equals(""))
					controlBuscarClientes.buscaByName(textoNombreCliente.getText());
				if (!textoIdCliente.getText().equals("") && !textoNombreCliente.getText().equals(""))
					controlBuscarClientes.buscaById(textoIdCliente.getText(), textoNombreCliente.getText());
			}
		});
		limpiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textoIdCliente.setText("");
				textoNombreCliente.setText("");
				limpiaTabla();
			}
		});

		regresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textoIdCliente.setText("");
				textoNombreCliente.setText("");
				limpiaTabla();
				controlBuscarClientes.termina();

			}
		});
		
	}

	public void muestra(ControlBuscarClientes control) {
		this.controlBuscarClientes = control;
		setVisible(true);
	}

	public void muestra(Cliente cliente) {
		table.setValueAt(cliente.getIdCliente(), i, j);
		j++;
		table.setValueAt(cliente.getNombreCompleto(), i, j);
		j++;
		table.setValueAt(cliente.getCorreoelectronico(), i, j);
		j++;
		table.setValueAt(cliente.getDireccion(), i, j);
		j++;
		table.setValueAt(cliente.getTelefono(), i, j);
		j++;
		i++;
		j=0;
	}

	public void muestra(List<Cliente> clientes) {

		for (Cliente cliente : clientes) {
			while (j < 5) {
				table.setValueAt(cliente.getIdCliente(), i, j);
				j++;
				table.setValueAt(cliente.getNombreCompleto(), i, j);
				j++;
				table.setValueAt(cliente.getCorreoelectronico(), i, j);
				j++;
				table.setValueAt(cliente.getDireccion(), i, j);
				j++;
				table.setValueAt(cliente.getTelefono(), i, j);
				j++;
			}
			i++;
			j = 0;
		}

	}

	public void muestraDialogoConMensaje(String mensaje) {

		UIManager.put("OptionPane.background", new Color(184, 199, 218));
		UIManager.put("Panel.background", new Color(184, 199, 218));
		UIManager.put("Button.background", new Color(255, 255, 255));
		UIManager.put("Button.foreground", new Color(89, 126, 170));
		UIManager.put("Button.font", new Font("Tahoma", Font.BOLD, 13));

		JLabel etiqueta = new JLabel(mensaje, SwingConstants.CENTER);
		etiqueta.setFont(new Font("Tahoma", Font.BOLD, 15));
		etiqueta.setForeground(new Color(255, 255, 255));

		JOptionPane.showMessageDialog(this, etiqueta, "AVISO", JOptionPane.INFORMATION_MESSAGE);

	}
	
	public void limpiaTabla() {
		int row = table.getRowCount();
		int col = table.getColumnCount();
		int m = 0;
		int n = 0;
		while (m < row) {
			while (n < col) {
				table.setValueAt("", m, n);
				n++;
			}
			n = 0;
			m++;
		}
		i=0;
		j=0;
	}

}
