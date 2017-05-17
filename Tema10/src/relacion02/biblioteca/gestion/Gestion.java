package relacion02.biblioteca.gestion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class Gestion extends JFrame implements WindowListener,ActionListener{

	private JPanel contentPane;
	protected JTextField txtNav;
	protected JPanel campos;
	protected JButton btnAnterior,btnPrimero,btnSiguiente,btnUltimo;
	protected JButton btnNuevo,btnModificar,btnEliminar;
	protected JFrame ventanaPadre;
	protected Connection conn;
	protected Statement stmt;
	protected ResultSet rset;
	protected String sql;
	protected int totalregistros;

	/**
	 * Create the frame.
	 */
	public Gestion() {
		initComponents();
	}
	
	public Gestion(Connection conn,JFrame ventaPadre) {
		this.ventanaPadre=ventaPadre;
		this.conn=conn;
		initComponents();
	}
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 303, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		campos = new JPanel();
		campos.setBorder(new LineBorder(new Color(51, 153, 153), 3));
		campos.setBounds(6, 6, 287, 176);
		contentPane.add(campos);
		
		btnPrimero = new JButton("<<");
		btnPrimero.setBounds(10, 212, 49, 23);
		contentPane.add(btnPrimero);
		btnPrimero.addActionListener(this);
		
		btnAnterior = new JButton("<");
		btnAnterior.setBounds(69, 212, 49, 23);
		contentPane.add(btnAnterior);
		btnAnterior.addActionListener(this);
		
		btnSiguiente = new JButton(">");
		btnSiguiente.setBounds(178, 212, 49, 23);
		contentPane.add(btnSiguiente);
		btnSiguiente.addActionListener(this);
		
		btnUltimo = new JButton(">>");
		btnUltimo.setBounds(237, 212, 49, 23);
		contentPane.add(btnUltimo);
		btnUltimo.addActionListener(this);
		
		txtNav = new JTextField();
		txtNav.setEditable(false);
		txtNav.setBounds(124, 213, 49, 20);
		contentPane.add(txtNav);
		txtNav.setColumns(10);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(10, 273, 89, 23);
		contentPane.add(btnNuevo);
		btnNuevo.addActionListener(this);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(102, 273, 89, 23);
		contentPane.add(btnModificar);
		btnModificar.addActionListener(this);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(197, 273, 89, 23);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener(this);
		
		setLocationRelativeTo(null);
		
		setVisible(true);
		addWindowListener(this);

	}
	
	protected void cargarDatos(){
		try {
			stmt=this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rset=stmt.executeQuery(sql);
			totalRegistros();
			rset.next();
			mostrarDatos();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	

	protected void mostrarDatos() throws SQLException {
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ventanaPadre.setEnabled(true);
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		ventanaPadre.setEnabled(false);
		cargarDatos();
		if(totalregistros<=0){
			JOptionPane.showMessageDialog(null, "No hay datos en la base de datos de este tipo de articulo");
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnPrimero)){
			try{
				rset.first();
				mostrarDatos();
			}catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			return;
		}
		if(e.getSource().equals(btnAnterior)){
			try{
				rset.previous();
				mostrarDatos();
			}catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			return;
		}
		if(e.getSource().equals(btnSiguiente)){
			try{
				rset.next();
				mostrarDatos();
			}catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			return;
		}
		if(e.getSource().equals(btnUltimo)){
			try{
				rset.last();
				mostrarDatos();
			}catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			return;
		}
		
		if(e.getSource().equals(btnEliminar)){
			try{
				rset.deleteRow();
				rset.refreshRow();
				mostrarDatos();
			}catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			return;
		}
		if(e.getSource().equals(btnModificar)){
			try{
				if(btnModificar.getText().equals("Modificar")){
					activarCampos(true);
					btnModificar.setText("Guardar");
				}else{
					activarCampos(false);
					btnModificar.setText("Modificar");
					modificarRegistro();
					rset.updateRow();
				}
			}catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			return;
		}
		if(e.getSource().equals(btnNuevo)){
			try{
				if(btnNuevo.getText().equals("Nuevo")){
					activarCampos(true);
					limpiarCampos();
					activarPK(true);
					btnNuevo.setText("Guardar");
				}else{
					activarCampos(false);
					activarPK(false);
					btnNuevo.setText("Nuevo");
					rset.moveToInsertRow();
					insertarRegistro();
					rset.insertRow();
					cargarDatos();
				}
			}catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			return;
		}
	}
	
	protected void insertarRegistro() throws SQLException{
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}

	protected void activarCampos(boolean activo){
		
	}
	
	protected void activarPK(boolean activo){
		
	}
	
	protected void limpiarCampos(){
		
	}
	
	protected void modificarRegistro() throws SQLException{
		
	}
	
	protected void controlarBotonesNavegacion() throws SQLException{
		btnPrimero.setEnabled(true);
		btnAnterior.setEnabled(true);
		btnUltimo.setEnabled(true);
		btnSiguiente.setEnabled(true);
		if(rset.isFirst()){
			btnPrimero.setEnabled(false);
			btnAnterior.setEnabled(false);
		}
		if(rset.isLast()){
			btnUltimo.setEnabled(false);
			btnSiguiente.setEnabled(false);
		}
	}
	
	protected void totalRegistros(){
		
	}
}
