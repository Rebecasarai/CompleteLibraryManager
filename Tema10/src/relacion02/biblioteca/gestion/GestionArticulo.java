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

public class GestionArticulo extends Gestion {
	private JTextField txtcod;
	private JTextField txtTitulo;
	private JTextField txtAutor;
	private JTextField txtNum;
	

	/**
	 * Create the frame.
	 */
	public GestionArticulo() {
		super();
		initCompenents();
	}

	public GestionArticulo(Connection conn,JFrame ventanaPadre){
		super(conn,ventanaPadre);
		initCompenents();
	}

	private void initCompenents(){
		setTitle("Gesti�n de Articulos");
		sql="Select codarticulo, titulo, autor, numpaginas from articulo order by titulo";

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
		
		txtTitulo = new JTextField();
		txtTitulo.setEditable(false);
		txtTitulo.setBounds(150, 36, 107, 20);
		campos.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblTtulo = new JLabel("Num de paginas");
		lblTtulo.setBounds(10, 67, 86, 14);
		campos.add(lblTtulo);
		
		JLabel lblMateria = new JLabel("Materia");
		lblMateria.setBounds(150, 68, 86, 14);
		campos.add(lblMateria);
		
		txtAutor = new JTextField();
		txtAutor.setEditable(false);
		txtAutor.setBounds(10, 84, 107, 20);
		campos.add(txtAutor);
		txtAutor.setColumns(10);
		
		
		
		txtNum = new JTextField();
		txtNum.setEditable(false);
		txtNum.setBounds(150, 84, 107, 20);
		campos.add(txtNum);
		
		


	}
	
	@Override
	protected void mostrarDatos() throws SQLException {
		txtcod.setText(""+rset.getLong("codarticulo"));
		txtTitulo.setText(rset.getString("titulo"));
		txtAutor.setText(rset.getString("autor"));
		txtNum.setText(rset.getString("numpaginas"));
		controlarBotonesNavegacion();
		txtNav.setText(rset.getRow()+"-"+totalregistros);
		
		super.mostrarDatos();
	}
	
	@Override
	protected void activarCampos(boolean activo) {
		//txtISBN.setEditable(activo);
		txtTitulo.setEditable(activo);
		txtAutor.setEditable(activo);
		txtNum.setEditable(activo);
		super.activarCampos(activo);
	}
	@Override
	protected void activarPK(boolean activo) {
		txtcod.setEditable(activo);
		super.activarPK(activo);
	}
	
	@Override
	protected void modificarRegistro() throws SQLException {
		rset.updateString("titulo", txtTitulo.getText());
		rset.updateString("autor", txtAutor.getText());
		rset.updateString("numpaginas", txtNum.getText());
		super.modificarRegistro();
	}
	
	@Override
	protected void limpiarCampos() {
		txtcod.setText("");
		txtTitulo.setText("");
		txtAutor.setText("");
		txtNum.setText("");
		super.limpiarCampos();
	}
	
	@Override
	protected void insertarRegistro() throws NumberFormatException, SQLException {
		rset.updateLong("codrevista", Long.parseLong(txtcod.getText()));
		modificarRegistro();
		super.insertarRegistro();
	}
	
	@Override
	protected void totalRegistros() {
		String sql2="Select count(*) from articulo";

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
