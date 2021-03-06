package es.studium.PracticaDI2;
/**
 * Pantalla Consulta Articulo
 * 
 * @author migue
 * @since 01/01/2021
 * @version 1.0
 */
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Desktop;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class consultaArticulos extends JFrame {


	private JPanel contentPane;
	private Statement statement;
	private ResultSet rs;
	private Connection connection;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Tiendecita?useSSL=false";
	String login = "root";
	String password = "Studium2019;";
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					consultaArticulos frame = new consultaArticulos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Se crea la pantalla consulta Art�culos
	 */
	public consultaArticulos() {

		setTitle("Consulta Art�culos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);


		JLabel lblNewLabel_1 = new JLabel("Descripci�n:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(66, 90, 133, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(66, 113, 45, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("N�mero de art�culos en stock:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setBounds(66, 136, 154, 13);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(230, 90, 142, 13);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(230, 113, 154, 13);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_6.setBounds(230, 136, 175, 13);
		contentPane.add(lblNewLabel_6);

		Choice choice_1 = new Choice();

		String sentencia = "SELECT * FROM articulos";
		connection = null;
		statement = null;
		rs = null;

		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Se ha producido un error al cargar el Driver");


		}
		try
		{
			connection = (Connection) DriverManager.getConnection(url, login, password);
		}
		catch(SQLException e)
		{
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}
		try
		{
			statement =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			rs=statement.executeQuery("SELECT* FROM articulos");

			while(rs.next())
			{

				choice_1.add(rs.getInt("idArticulo")+" "+rs.getString("nombreArticulo")+"\n");

			}


		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
		}

		try
		{
			statement =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			rs=statement.executeQuery("SELECT* FROM articulos");
			if(rs.next())
			{

				lblNewLabel_4.setText(rs.getString("descripcionArticulo"));
				lblNewLabel_5.setText(rs.getString("precioArticulo"));
				lblNewLabel_6.setText(rs.getString("numeroStock"));




			}}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
		}
		choice_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				String nombreItem = new String(choice_1.getSelectedItem());


				try
				{
					int posicion = nombreItem.indexOf(" ");

					String id = nombreItem.substring(0, posicion);
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

					rs = statement.executeQuery("SELECT* FROM articulos WHERE idArticulo="+id);
					if(rs.next())
					{

						lblNewLabel_4.setText(rs.getString("descripcionArticulo"));
						lblNewLabel_5.setText(rs.getString("precioArticulo"));
						lblNewLabel_6.setText(rs.getString("numeroStock"));




					}}
				catch(SQLException e1)
				{
					System.out.println("Error en la sentencia SQL");
				}
			}
		});
		choice_1.setBounds(247, 32, 156, 18);
		contentPane.add(choice_1);


		JLabel lblNewLabel = new JLabel("Seleccione el art�culo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(66, 31, 154, 19);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Frame art=new Articulos();
				art.setVisible(true);
			}
		});
		btnNewButton.setBounds(166, 186, 85, 21);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("iReport");
		/**
		 * Funcionalidad del bot�n 1
		 */
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					// Compilar el informe generando fichero jasper
					JasperCompileManager.compileReportToFile("Articulos.jrxml");
					System.out.println("Fichero informeTiendecita.jasper generado CORRECTAMENTE!");
					// Objeto para guardar par�metros necesarios para el informe
					HashMap<String,Object> parametros = new HashMap<String,Object>();
					// Cargar el informe compilado
					JasperReport report = (JasperReport)
							JRLoader.loadObjectFromFile("informeTiendecita.jasper");
					// Conectar a la base de datos para sacar la informaci�n
					Class.forName("com.mysql.jdbc.Driver");
					String servidor = "jdbc:mysql://localhost:3306/tiendecita?useSSL=false";
					String usuarioDB = "root";
					String passwordDB = "Studium2019;";
					java.sql.Connection conexion = DriverManager.getConnection(servidor, usuarioDB,
							passwordDB);
					// Completar el informe con los datos de la base de datos
					JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);
					// Mostrar el informe en JasperViewer
					JasperViewer.viewReport(print, false);

				}
				catch (Exception e1)
				{
					System.out.println("Error: " + e1.toString());
				}
			}
		});
		btnNewButton_1.setBounds(276, 186, 85, 21);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Atr�s");

		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Frame art=new Articulos();
				art.setVisible(true);
			}

		});

	}
}
