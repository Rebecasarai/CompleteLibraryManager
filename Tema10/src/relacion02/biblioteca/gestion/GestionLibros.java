package relacion02.biblioteca.gestion;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class GestionLibros extends Gestion {
	private JTextField txtISBN;
	private JTextField txtSignatura;
	private JTextField txtTitulo;
	private JTextField txtAutor;
	private JTextField txtMateria;
	private JTextField txtEditorial;
	protected String tabla="libro";
	

	/**
	 * Create the frame.
	 */
	public GestionLibros() {
		super();
		initCompenents();

	}

	public GestionLibros(Connection conn,JFrame ventanaPadre){
		super(conn,ventanaPadre);
		initCompenents();
	}

	private void initCompenents(){
		setTitle("Gesti�n de Libros");
		sql="Select isbn, signatura,titulo,autor, materia,editorial from libro order by titulo";

		campos.setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(10, 22, 86, 14);
		campos.add(lblIsbn);
		
		txtISBN = new JTextField();
		txtISBN.setEditable(false);
		txtISBN.setBounds(10, 36, 107, 20);
		campos.add(txtISBN);
		txtISBN.setColumns(10);
		
		JLabel lblSignatura = new JLabel("Signatura");
		lblSignatura.setBounds(150, 22, 86, 14);
		campos.add(lblSignatura);
		
		txtSignatura = new JTextField();
		txtSignatura.setEditable(false);
		txtSignatura.setBounds(150, 36, 107, 20);
		campos.add(txtSignatura);
		txtSignatura.setColumns(10);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(10, 67, 86, 14);
		campos.add(lblTtulo);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(150, 67, 86, 14);
		campos.add(lblAutor);
		
		JLabel lblMateria = new JLabel("Materia");
		lblMateria.setBounds(10, 115, 86, 14);
		campos.add(lblMateria);
		
		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setBounds(150, 115, 86, 14);
		campos.add(lblEditorial);
		
		txtTitulo = new JTextField();
		txtTitulo.setEditable(false);
		txtTitulo.setBounds(10, 84, 107, 20);
		campos.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtAutor = new JTextField();
		txtAutor.setEditable(false);
		txtAutor.setBounds(150, 84, 107, 20);
		campos.add(txtAutor);
		txtAutor.setColumns(10);
		
		txtMateria = new JTextField();
		txtMateria.setEditable(false);
		txtMateria.setBounds(10, 134, 107, 20);
		campos.add(txtMateria);
		txtMateria.setColumns(10);
		
		txtEditorial = new JTextField();
		txtEditorial.setEditable(false);
		txtEditorial.setBounds(150, 134, 107, 20);
		campos.add(txtEditorial);
		txtEditorial.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GestionLibros.class.getResource("/relacion02/biblioteca/imagenes/notebook.png")));
		lblNewLabel.setBounds(118, 6, 35, 50);
		campos.add(lblNewLabel);


	}
	
	@Override
	protected void mostrarDatos() throws SQLException {
		txtISBN.setText(""+rset.getLong("isbn"));
		txtSignatura.setText(rset.getString("signatura"));
		txtTitulo.setText(rset.getString("titulo"));
		txtAutor.setText(rset.getString("Autor"));
		txtMateria.setText(rset.getString("Materia"));
		txtEditorial.setText(rset.getString("Editorial"));
		controlarBotonesNavegacion();
		txtNav.setText(rset.getRow()+"/"+totalregistros);
		
		super.mostrarDatos();
	}
	
	@Override
	protected void activarCampos(boolean activo) {
		//txtISBN.setEditable(activo);
		txtSignatura.setEditable(activo);
		txtTitulo.setEditable(activo);
		txtAutor.setEditable(activo);
		txtMateria.setEditable(activo);
		txtEditorial.setEditable(activo);
		super.activarCampos(activo);
	}
	@Override
	protected void activarPK(boolean activo) {
		txtISBN.setEditable(activo);
		super.activarPK(activo);
	}
	
	@Override
	protected void modificarRegistro() throws SQLException {
		rset.updateString("signatura", txtSignatura.getText());
		rset.updateString("titulo", txtTitulo.getText());
		rset.updateString("autor", txtAutor.getText());
		rset.updateString("materia", txtMateria.getText());
		rset.updateString("editorial", txtEditorial.getText());
		super.modificarRegistro();
	}
	
	@Override
	protected void limpiarCampos() {
		txtISBN.setText("");
		txtSignatura.setText("");
		txtTitulo.setText("");
		txtAutor.setText("");
		txtMateria.setText("");
		txtEditorial.setText("");
		super.limpiarCampos();
	}
	
	@Override
	protected void insertarRegistro() throws NumberFormatException, SQLException {
		rset.updateLong("isbn", Long.parseLong(txtISBN.getText()));
		modificarRegistro();
		super.insertarRegistro();
	}
	
	@Override
	protected void totalRegistros() {
		String sql2="Select count(*) from LIBRO";

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
