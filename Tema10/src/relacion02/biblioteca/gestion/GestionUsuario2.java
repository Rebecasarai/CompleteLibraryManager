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

public class GestionUsuario2 extends Gestion {

	private JTextField txtCod;
	private JTextField txtNombre;
	private JTextField txtApe1;
	private JTextField txtApe2;
	protected String tabla="usuario";
	

	/**
	 * Create the frame.
	 */
	public GestionUsuario2() {
		super();
		initCompenents();

	}

	public GestionUsuario2(Connection conn,JFrame ventanaPadre){
		super(conn,ventanaPadre);
		initCompenents();
	}

	private void initCompenents(){
		setTitle("Gesti�n de Usuario");
		sql="Select codusuario, nombre,apellido1,apellido2 from usuario order by nombre";

		campos.setLayout(null);
		
		JLabel lblIsbn = new JLabel("Codigo");
		lblIsbn.setBounds(19, 44, 86, 14);
		campos.add(lblIsbn);
		
		txtCod = new JTextField();
		txtCod.setEditable(false);
		txtCod.setBounds(19, 58, 107, 20);
		campos.add(txtCod);
		txtCod.setColumns(10);
		
		JLabel lblSignatura = new JLabel("Nombre");
		lblSignatura.setBounds(159, 44, 86, 14);
		campos.add(lblSignatura);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(159, 58, 107, 20);
		campos.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTtulo = new JLabel("Primer Apellido");
		lblTtulo.setBounds(19, 89, 86, 14);
		campos.add(lblTtulo);
		
		JLabel lblAutor = new JLabel("Segundo apellido");
		lblAutor.setBounds(159, 89, 86, 14);
		campos.add(lblAutor);
		
		txtApe1 = new JTextField();
		txtApe1.setEditable(false);
		txtApe1.setBounds(19, 106, 107, 20);
		campos.add(txtApe1);
		txtApe1.setColumns(10);
		
		txtApe2 = new JTextField();
		txtApe2.setEditable(false);
		txtApe2.setBounds(159, 106, 107, 20);
		campos.add(txtApe2);
		txtApe2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GestionUsuario2.class.getResource("/relacion02/biblioteca/imagenes/users.png")));
		lblNewLabel.setBounds(111, 6, 27, 35);
		campos.add(lblNewLabel);
		


	}
	

	
	@Override
	protected void mostrarDatos() throws SQLException {
		txtCod.setText(""+rset.getLong("CODUSUARIO"));
		txtNombre.setText(rset.getString("NOMBRE"));
		txtApe1.setText(rset.getString("APELLIDO1"));
		txtApe2.setText(rset.getString("APELLIDO2"));
		controlarBotonesNavegacion();
		txtNav.setText(rset.getRow()+"/"+totalregistros);
		
		super.mostrarDatos();
	}
	
	@Override
	protected void activarCampos(boolean activo) {
		//txtCod.setEditable(activo);
		txtNombre.setEditable(activo);
		txtApe1.setEditable(activo);
		txtApe2.setEditable(activo);
		super.activarCampos(activo);
	}
	@Override
	protected void activarPK(boolean activo) {
		txtCod.setEditable(activo);
		super.activarPK(activo);
	}
	
	@Override
	protected void modificarRegistro() throws SQLException {
		rset.updateString("NOMBRE", txtNombre.getText());
		rset.updateString("APELLIDO1", txtApe1.getText());
		rset.updateString("APELLIDO2", txtApe2.getText());
		super.modificarRegistro();
	}
	
	@Override
	protected void limpiarCampos() {
		txtCod.setText("");
		txtNombre.setText("");
		txtApe1.setText("");
		txtApe2.setText("");
		super.limpiarCampos();
	}
	
	@Override
	protected void insertarRegistro() throws NumberFormatException, SQLException {
		rset.updateLong("CODUSUARIO", Long.parseLong(txtCod.getText()));
		modificarRegistro();
		super.insertarRegistro();
	}
	
	@Override
	protected void totalRegistros() {
		String sql2="Select count(*) from usuario";

		try {
			Statement stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery(sql2);
			rset.next();
			this.totalregistros=rset.getInt(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		super.totalRegistros();
		
	}
	

}

