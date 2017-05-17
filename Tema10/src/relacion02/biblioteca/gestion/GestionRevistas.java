package relacion02.biblioteca.gestion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class GestionRevistas extends Gestion {
	private JTextField txtcod;
	private JTextField txtSignatura;
	private JTextField txtNombre;
	private JTextField txtMateria;
	protected String tabla="libro";
	

	/**
	 * Create the frame.
	 */
	public GestionRevistas() {
		super();
		initCompenents();

	}

	public GestionRevistas(Connection conn,JFrame ventanaPadre){
		super(conn,ventanaPadre);
		initCompenents();
	}

	private void initCompenents(){
		setTitle("Gesti�n de Revistas");
		sql="Select codrevista, signatura, nombre, materia from REVISTA order by codrevista";

		campos.setLayout(null);
		
		JLabel lblIsbn = new JLabel("Codigo");
		lblIsbn.setBounds(10, 22, 86, 14);
		campos.add(lblIsbn);
		
		txtcod = new JTextField();
		txtcod.setEditable(false);
		txtcod.setBounds(10, 36, 107, 20);
		campos.add(txtcod);
		txtcod.setColumns(10);
		
		JLabel lblSignatura = new JLabel("Signatura");
		lblSignatura.setBounds(150, 22, 86, 14);
		campos.add(lblSignatura);
		
		txtSignatura = new JTextField();
		txtSignatura.setEditable(false);
		txtSignatura.setBounds(150, 36, 107, 20);
		campos.add(txtSignatura);
		txtSignatura.setColumns(10);
		
		JLabel lblTtulo = new JLabel("Nombre");
		lblTtulo.setBounds(10, 67, 86, 14);
		campos.add(lblTtulo);
		
		JLabel lblMateria = new JLabel("Materia");
		lblMateria.setBounds(150, 68, 86, 14);
		campos.add(lblMateria);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(10, 84, 107, 20);
		campos.add(txtNombre);
		txtNombre.setColumns(10);
		
		
		
		txtMateria = new JTextField();
		txtMateria.setEditable(false);
		txtMateria.setBounds(150, 84, 107, 20);
		campos.add(txtMateria);
		txtMateria.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GestionRevistas.class.getResource("/relacion02/biblioteca/imagenes/magazine.png")));
		lblNewLabel.setBounds(120, 116, 34, 39);
		campos.add(lblNewLabel);
		


	}
	
	@Override
	protected void mostrarDatos() throws SQLException {
		txtcod.setText(""+rset.getLong("codrevista"));
		txtSignatura.setText(rset.getString("signatura"));
		txtNombre.setText(rset.getString("nombre"));
		txtMateria.setText(rset.getString("materia"));
		controlarBotonesNavegacion();
		txtNav.setText(rset.getRow()+"/"+totalregistros);
		
		super.mostrarDatos();
	}
	
	@Override
	protected void activarCampos(boolean activo) {
		//txtISBN.setEditable(activo);
		txtSignatura.setEditable(activo);
		txtNombre.setEditable(activo);
		txtMateria.setEditable(activo);
		super.activarCampos(activo);
	}
	@Override
	protected void activarPK(boolean activo) {
		txtcod.setEditable(activo);
		super.activarPK(activo);
	}
	
	@Override
	protected void modificarRegistro() throws SQLException {
		rset.updateString("signatura", txtSignatura.getText());
		rset.updateString("titulo", txtNombre.getText());
		rset.updateString("materia", txtMateria.getText());
		super.modificarRegistro();
	}
	
	@Override
	protected void limpiarCampos() {
		txtcod.setText("");
		txtSignatura.setText("");
		txtNombre.setText("");
		txtMateria.setText("");
		super.limpiarCampos();
	}
	
	@Override
	protected void insertarRegistro() throws NumberFormatException, SQLException {
		rset.updateLong("codrevista", Long.parseLong(txtcod.getText()));
		JOptionPane.showMessageDialog(null, sql);
		modificarRegistro();
		super.insertarRegistro();
	}
	
	@Override
	protected void totalRegistros() {
		String sql2="Select count(*) from revista";

		try {
			Statement stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery(sql2);
			rset.next();
			this.totalregistros=rset.getInt(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		super.totalRegistros();
		
		super.totalRegistros();
		
	}
	

}
