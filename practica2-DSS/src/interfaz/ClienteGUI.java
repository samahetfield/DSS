package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import modelo.Usuario;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTable;

public class ClienteGUI implements ActionListener{

	private JFrame frame;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textEmail;
	private JTable tableUsers;
	JPanel panel;
	
	final String urlString = "http://localhost:8080/practica2-DSS/ListaCorreosServlet";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteGUI window = new ClienteGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClienteGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(734, 471);
		frame.getContentPane().setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(20, 9, 152, 21);
		frame.getContentPane().add(toolBar);
		
		JButton btnAadirUsuario = new JButton("A\u00F1adir usuario");
		btnAadirUsuario.setActionCommand("SHOW_ADD_USER");
		btnAadirUsuario.addActionListener(this);
		toolBar.add(btnAadirUsuario);
		
		panel = new JPanel();
		panel.setBounds(430, 43, 278, 334);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(20, 50, 117, 14);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(20, 75, 248, 29);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(20, 117, 117, 14);
		panel.add(lblApellidos);
		
		textApellidos = new JTextField();
		textApellidos.setBounds(20, 142, 248, 29);
		panel.add(textApellidos);
		textApellidos.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(20, 190, 117, 14);
		panel.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(20, 215, 248, 29);
		panel.add(textEmail);
		textEmail.setColumns(10);
		
		JButton btnAadir = new JButton("A\u00F1adir");
		btnAadir.setActionCommand("ADD_USER");
		btnAadir.addActionListener((ActionListener) this);
		btnAadir.setBounds(99, 266, 89, 23);
		panel.add(btnAadir);
				
		tableUsers = new JTable(new DefaultTableModel(new Object[]{"Nombre", "Apellidos", "Email"}, 0));
		tableUsers.setEnabled(false);
		tableUsers.setBounds(20, 43, 600, 300);
		
		frame.getContentPane().add(tableUsers);
		
		JPopupMenu pop = new JPopupMenu();
		JMenuItem item1 = new JMenuItem("Eliminar usuario");
		
		pop.add(item1);
		
		tableUsers.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if(SwingUtilities.isRightMouseButton(me)) {
					pop.show(tableUsers, me.getX(), me.getY());
					int row = tableUsers.rowAtPoint(me.getPoint());
					tableUsers.setRowSelectionInterval(row, row);
					String email = (String) tableUsers.getValueAt(row, 2);
					item1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Map<String, String> parametros = new HashMap<String, String>();
							parametros.put("action", "eliminarUsuario");
							parametros.put("email", email);
							
							try {
								ObjectInputStream respuesta = new ObjectInputStream(realizarPeticionPost(urlString, parametros));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}		
							updateUserPanel();

						}
						
					});
					
				}
			}
		});
		
		updateUserPanel();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("SHOW_ADD_USER")) {
			panel.setVisible(true);
			tableUsers.setBounds(20, 43, 400, 334);
		}
		else if(e.getActionCommand().equals("ADD_USER")){
			String nombre = textNombre.getText();
			String apellidos = textApellidos.getText();
			String email = textEmail.getText();
			
			Map<String, String> parametros = new HashMap<String, String>();
			parametros.put("action", "aniadirUsuario");
			parametros.put("nombre", nombre);
			parametros.put("apellidos", apellidos);
			parametros.put("email", email);
			
			try {
				ObjectInputStream respuesta = new ObjectInputStream(realizarPeticionPost(urlString, parametros));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
			panel.setVisible(false);
			tableUsers.setBounds(20, 43, 600, 300);
			updateUserPanel();
		}
	}

	@SuppressWarnings("deprecation")
	private InputStream realizarPeticionPost(String urlString2, Map<String, String> parametros) {
		String cadenaParametros = "";
		boolean primerPar = true;
		for (Map.Entry<String, String> entry : parametros.entrySet()) {
			if (!primerPar) {
				cadenaParametros += "&";
			} else {
				primerPar = false;
			}
		    String parDeParametro = String.format("%s=%s", 
					URLEncoder.encode(entry.getKey()), 
					URLEncoder.encode(entry.getValue()));
		    cadenaParametros += parDeParametro;
		}
		try {
			URL url = new URL(urlString2);
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			conexion.setUseCaches(false);
			conexion.setRequestMethod("POST");
			conexion.setDoOutput(true);
			OutputStream output = conexion.getOutputStream();
			output.write(cadenaParametros.getBytes());
			output.flush();
			output.close();
			
			InputStream entrada = conexion.getInputStream();
			
			
			return entrada;
		} catch (MalformedURLException | ProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	private void updateUserPanel() {
		
		List<Usuario> mostrarUser = mostrarUsuarios();
		String texto = "";
		DefaultTableModel model = (DefaultTableModel) tableUsers.getModel();
		
		int rowCount = model.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
		    model.removeRow(i);
		}
		
		if(!mostrarUser.isEmpty()) {
			texto = "";
			for(Usuario u: mostrarUser) {
				model.addRow(new Object[]{u.getName(), u.getApellidos(), u.getEmail()});
			}
		}
	}
	
	private List<Usuario> mostrarUsuarios(){
		try {
			Map<String,String> parametros = new HashMap<String, String>();
			parametros.put("action", "listarUsuario");
			ObjectInputStream respuesta = new ObjectInputStream(realizarPeticionPost(urlString, parametros));
			try {
				List<Usuario> listaUsuarios = (List<Usuario>) respuesta.readObject();	
				
				return listaUsuarios;
			}catch(Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
