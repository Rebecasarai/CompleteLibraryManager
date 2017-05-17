package relacion02.biblioteca;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import relacion02.biblioteca.gestion.GestionArticulo;
import relacion02.biblioteca.gestion.GestionCdrom;
import relacion02.biblioteca.gestion.GestionRevistas;
import relacion02.biblioteca.gestion.GestionUsuario2;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class Principal extends JFrame implements ActionListener, WindowListener{

	private JPanel contentPane;
	private Connection conn;
	private JFrame miVentana;
	private JMenuItem mtmUsuarios;
	private JMenuItem mntmLibros;
	private JMenuItem mntmCdroms;
	private JMenuItem mntmRevistas;
	private JMenuItem mntmArticulos;
	protected String table;
	
	
	/**
	 * Create the frame.
	 */
	public Principal() {
		initComponents();
		
	}
	
	public Principal(Connection conn, JFrame ventanaPadre){
		this.conn= conn;
		ventanaPadre.dispose();
		initComponents();
		
	}
	
	private void initComponents(){
		//setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("library.jpeg")));
		miVentana=this;
		setTitle("Fun Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu Gestion = new JMenu("Gestión");
		menuBar.add(Gestion);
		
		mtmUsuarios = new JMenuItem("Usuarios");
		mtmUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionUsuario2(conn,miVentana);
			}
		});
		Gestion.add(mtmUsuarios);
		
		mntmLibros = new JMenuItem("Libros");
		mntmLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new relacion02.biblioteca.gestion.GestionLibros(conn, miVentana);
			}
		});
		Gestion.add(mntmLibros);
		
		mntmCdroms = new JMenuItem("Cd-Roms");
		mntmCdroms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionCdrom(conn, miVentana);
			}
		});
		Gestion.add(mntmCdroms);
		
		mntmRevistas = new JMenuItem("Revistas");
		mntmRevistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionRevistas(conn, miVentana);
			}
		});
		Gestion.add(mntmRevistas);
		
		mntmArticulos = new JMenuItem("Articulos");
		mntmArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionArticulo(conn, miVentana);
			}
		});
		Gestion.add(mntmArticulos);
		
		JMenu mnPrestamos = new JMenu("Prestamos");
		menuBar.add(mnPrestamos);
		
		JMenuItem mntmRealizar = new JMenuItem("Realizar Prestamos");
		mnPrestamos.add(mntmRealizar);
		
		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mnPrestamos.add(mntmConsultar);
		
		JMenu mnListados = new JMenu("Listados");
		menuBar.add(mnListados);
		
		JMenuItem mntmListarPendientes = new JMenuItem("Listar pendientes de Devolución");
		mnListados.add(mntmListarPendientes);
		
		JMenuItem mntmListarPrestamosPor = new JMenuItem("Listar prestamos por usuarios");
		mnListados.add(mntmListarPrestamosPor);
		
		JMenuItem mntmDeUsuarios = new JMenuItem("Listado de usuarios");
		mntmDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table= "usuario";
				generarListado();
				return;
			}
		});
		mnListados.add(mntmDeUsuarios);
		
		JMenuItem mntmListadoDeUsuarios = new JMenuItem("Listado de Libros");
		mntmListadoDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table= "libro";
				generarListado();
				return;
			}
		});
		mnListados.add(mntmListadoDeUsuarios);
		
		JMenuItem mntmListadoDeLibros = new JMenuItem("Listado de CD-Roms");
		mntmListadoDeLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table= "cdrom";
				generarListado();
				return;
			}
		});
		mnListados.add(mntmListadoDeLibros);
		
		JMenuItem mntmListadoDeArticulos = new JMenuItem("Listado de Articulos");
		mntmListadoDeArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table= "articulo";
				generarListado();
				return;
			}
		});
		mnListados.add(mntmListadoDeArticulos);
		
		JMenuItem mntmListadoDeRevistas = new JMenuItem("Listado de Revistas");
		mntmListadoDeRevistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table= "revista";
				generarListado();
				return;
			}
		});
		mnListados.add(mntmListadoDeRevistas);
		
		JMenu menu = new JMenu("?");
		menuBar.add(menu);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Acerca();
			}
		});
		menu.add(mntmAcercaDe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/relacion02/biblioteca/imagenes/library.jpeg")));
		lblNewLabel.setBounds(0, 0, 1620, 1200);
		contentPane.add(lblNewLabel);
		setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(mtmUsuarios)){
			
		}
		if(e.getSource().equals(mntmLibros)){
			
		}
		if(e.getSource().equals(mntmCdroms)){
			
		}
		if(e.getSource().equals(mntmRevistas)){
			
		}
		if(e.getSource().equals(mntmArticulos)){
			
		}		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		desconectar();
		
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
	
	private void desconectar(){
		try {
			this.conn.close();
		} catch (SQLException e) {
			
		}
	}
	
	
	
	private void generarListado() {
		try {
			JFileChooser chooser = new JFileChooser(); 
			chooser.showOpenDialog(this);
			File file = chooser.getSelectedFile();
			if(file!=null){
				generarPDF(file, table);
				Desktop.getDesktop().open(file);
			}
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "No se encuentra el fichero creado");
		}
	}
	
	
	
	private void generarPDF(File file, String table){
		// Se crea el documento
		Document documento = new Document();

		// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
		FileOutputStream ficheroPdf;
		try {
			ficheroPdf = new FileOutputStream(file);
			
			// Se asocia el documento al OutputStream y se indica que el espaciado entre
			// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
			PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);

			// Se abre el documento.
			documento.open();
			//Ponemos titulo al documento
			documento.add(new Paragraph("Listado de "+table,
							FontFactory.getFont("Tahoma",   // fuente
							20,                            // tama�o
							Font.BOLDITALIC,                   // estilo
							BaseColor.BLUE)));
			
			
			//Salto de linea
			documento.add(new Paragraph(" "));
			//generamos la tabla con los datos de los empleados del departamento seleccionado
			try{
				//Creamos las filas
				Statement stmt=conn.createStatement();
				String sql="select * from "+table;
				ResultSet rset=stmt.executeQuery(sql);
				
				//Nos traemos los metadatos
				ResultSetMetaData metaDatos = rset.getMetaData();
				// Se obtiene el n�mero de columnas y se crea la tabla del pdf.
				int numeroColumnas = metaDatos.getColumnCount();
				PdfPTable tabla = new PdfPTable(numeroColumnas);
				
				//A�ado la cabecera
				for(int i=1;i<=numeroColumnas;i++){
					tabla.addCell(metaDatos.getColumnLabel(i));
				}

				//A�adimos los empleados
				// Bucle para cada resultado en la consulta
				while (rset.next())
				{
				   // Se rellena cada celda de la tabla con una de las columnas de la tabla en base de datos.
				   for (int i=1;i<=numeroColumnas;i++){
				      tabla.addCell(""+rset.getObject(i)); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
				   }
				}
				documento.add(tabla);
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			
			//Cerramos el pdf
			documento.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "La ruta introducida no es valida");
		} catch (DocumentException e) {
			JOptionPane.showMessageDialog(null, "Error al Crear el listado");
		}

	}
}
