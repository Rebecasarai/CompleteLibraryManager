package relacion02.biblioteca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Acerca extends JFrame implements WindowListener, ActionListener{

	private JPanel contentPane;
	private JFrame ventanaPadre;


	/**
	 * Create the frame.
	 */
	public Acerca(JFrame ventanaPadre) {
		setTitle("Acerca de");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.ventanaPadre = ventanaPadre;
		setBounds(100, 100, 612, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel imagen = new JLabel("");
		imagen.setVerticalAlignment(SwingConstants.BOTTOM);
		imagen.setHorizontalAlignment(SwingConstants.CENTER);
		imagen.setIcon(new ImageIcon(Acerca.class.getResource("/relacion02/biblioteca/imagenes/rebeca.png")));
		imagen.setBounds(6, 69, 261, 329);
		imagen.setBorder(new LineBorder(new Color(51, 153, 153), 3));
		contentPane.add(imagen);
		
		JLabel lblNewLabel = new JLabel("Acerca de la App");
		lblNewLabel.setFont(new Font("Damascus", Font.BOLD, 17));
		lblNewLabel.setBounds(227, 20, 165, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblCreadoPorRebeca = new JLabel("Creado por: Rebeca González ");
		lblCreadoPorRebeca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCreadoPorRebeca.setBounds(301, 98, 261, 16);
		contentPane.add(lblCreadoPorRebeca);
		
		JLabel lblConTutoraDe = new JLabel("Con tutoría de: Antonio Velasquez");
		lblConTutoraDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConTutoraDe.setBounds(301, 138, 261, 16);
		contentPane.add(lblConTutoraDe);
		
		JLabel lblInstituto = new JLabel("Instituto:");
		lblInstituto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInstituto.setBounds(301, 189, 249, 16);
		contentPane.add(lblInstituto);
		
		JLabel lblIesPoligonoSur = new JLabel("I.E.S. Poligono Sur");
		lblIesPoligonoSur.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIesPoligonoSur.setBounds(301, 202, 249, 37);
		contentPane.add(lblIesPoligonoSur);
		
		JLabel lblNewLabel_1 = new JLabel("¡Sigueme en redes sociales!");
		lblNewLabel_1.setBounds(311, 340, 218, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblTwitter = new JLabel("Twitter: @Codelovr");
		lblTwitter.setBounds(332, 392, 218, 16);
		contentPane.add(lblTwitter);
		
		JLabel lblGithub = new JLabel("Github: /Rebecasarai");
		lblGithub.setBounds(332, 431, 218, 16);
		contentPane.add(lblGithub);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		ventanaPadre.enabled(false);
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ventanaPadre.enabled(true);
		
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
