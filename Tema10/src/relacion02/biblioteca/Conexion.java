package relacion02.biblioteca;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.net.ns.AcceptPacket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Conexion extends JFrame implements ActionListener {

	private JPanel contentPane;
	public JPasswordField passw;
	private JTextField user;
	private JButton btn_aceptar;
	private JButton btnCancelar;
	//private Connection conn;
	private int intentos;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conexion frame = new Conexion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Conexion() {
		setTitle("Iniciar conexión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 229);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		passw = new JPasswordField();
		passw.setText("java");
		passw.setBounds(139, 91, 152, 26);
		contentPane.add(passw);
		passw.setColumns(10);
		
		JLabel lbluser = new JLabel("Usuario: ");
		lbluser.setFont(new Font("PT Serif", Font.BOLD, 15));
		lbluser.setBounds(24, 44, 76, 16);
		contentPane.add(lbluser);
		
		user = new JTextField();
		user.setText("java");
		user.setColumns(10);
		user.setBounds(139, 40, 152, 26);
		contentPane.add(user);
		
		JLabel lblpass = new JLabel("Contraseña:");
		lblpass.setFont(new Font("PT Serif", Font.BOLD, 15));
		lblpass.setBounds(25, 95, 102, 16);
		contentPane.add(lblpass);
		
		btn_aceptar = new JButton("Aceptar");
		btn_aceptar.setForeground(new Color(0, 102, 102));
		btn_aceptar.setBounds(39, 152, 117, 29);
		contentPane.add(btn_aceptar);
		btn_aceptar.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(174, 152, 117, 29);
		contentPane.add(btnCancelar);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Conexion.class.getResource("/relacion02/biblioteca/imagenes/post-sample-image.jpg")));
		lblNewLabel.setBounds(0, 0, 347, 207);
		contentPane.add(lblNewLabel);
		btnCancelar.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btn_aceptar)){
			Connection conn=conectar();
			if(conn!=null){
				new Principal(conn, this);
			}
			
		}
		if(e.getSource().equals(btnCancelar)){
			System.exit(0);
		}
		
	}
	
	private Connection conectar(){
		Connection conn;
		intentos++;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", user.getText(), new String(passw.getPassword()));
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@//127.0.0.1:1521/orcl", user.getText(), new String(passw.getPassword()));
			return conn;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se encuentra el driver de Base de Datos");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"Te quedan "+(3-intentos+" intentos"));
		}
		return null;
		
	}
}
