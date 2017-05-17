package relacion02.biblioteca;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class Ventana extends JFrame implements WindowListener, ActionListener {

	protected JPanel contentPane;
	protected JFrame ventanaPadre;
	protected Connection conn;
	protected JTextField textField_5;
	protected ResultSet rset;
	protected Statement stmt;
	protected JButton button, button_1,button_2,button_3,btnInsertar,btnBorrar, button_6;

	

	/**
	 * Create the frame.
	 */
	public Ventana() {
		InitComponent();

	}
	public Ventana(JFrame padre, Connection conn){
		this.ventanaPadre=padre;
		this.conn=conn;
		InitComponent();
		this.ventanaPadre.setEnabled(false);
		try {
			stmt= conn.createStatement();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
		
		
	}
	
	public void InitComponent(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setBounds(62, 6, 309, 60);
		contentPane.add(lblImagen);
		
		JButton button = new JButton("<<");
		button.setBounds(62, 300, 49, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton(">>");
		button_1.setBounds(324, 300, 49, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton(">");
		button_2.setBounds(262, 300, 49, 23);
		contentPane.add(button_2);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(174, 301, 84, 20);
		contentPane.add(textField_5);
		
		button_3 = new JButton("<");
		button_3.setBounds(121, 300, 49, 23);
		contentPane.add(button_3);
		
		btnInsertar = new JButton("Insertar ");
		btnInsertar.setBounds(62, 357, 123, 25);
		contentPane.add(btnInsertar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(245, 357, 123, 25);
		contentPane.add(btnBorrar);
		
		button_6 = new JButton("Limpiar Campos");
		button_6.setEnabled(false);
		button_6.setBounds(152, 393, 132, 25);
		contentPane.add(button_6);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panel.setBounds(26, 25, 379, 210);
		contentPane.add(panel);
		addWindowListener(this);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		ventanaPadre.setEnabled(true);
		this.dispose();
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}
}
