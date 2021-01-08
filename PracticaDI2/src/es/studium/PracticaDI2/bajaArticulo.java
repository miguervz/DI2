package es.studium.PracticaDI2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.Dialog;

public class bajaArticulo extends JFrame {

	private JPanel contentPane;
	private Statement statement;
	private ResultSet rs;
	private Connection connection;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bajaArticulo frame = new bajaArticulo();
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
	public bajaArticulo() {
		setTitle("Baja Artículos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Tiendecita?useSSL=false";
		String login = "root";
		String password = "Studium2019;";
		Dialog d = new Dialog(this, "Operación Inserción", true);
		Label e1 = new Label ("Operación realizada correctamente!");
		
		d.setLayout(new FlowLayout());
		d.add(e1);
		d.setSize(250,150);
		d.setLocationRelativeTo(null);
		Dialog d1 = new Dialog(this, "Operación Inserción", true);
		Label e2 = new Label ("No se puede dar de baja si pertenece a un ticket.");
		
		d1.setLayout(new FlowLayout());
		d1.add(e2);
		d1.setSize(300,150);
		d1.setLocationRelativeTo(null);
		JButton ok = new JButton("Ok");
		JButton ok2 = new JButton("Ok");

		d.add(ok);
		d1.add(ok2);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);

			}
		});
		ok2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d1.setVisible(false);

			}
		});
		JLabel lblNewLabel = new JLabel("Seleccione el artículo:");
		lblNewLabel.setBounds(52, 82, 188, 19);
		contentPane.add(lblNewLabel);
		Choice choice = new Choice();
		choice.setBounds(247, 82, 162, 18);
		contentPane.add(choice);

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

				choice.add(rs.getInt("idArticulo")+" "+rs.getString("nombreArticulo")+"\n");

			}


		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
		}


		JButton btnNewButton_1 = new JButton("Baja");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombre = new String(choice.getSelectedItem());

				int posicion = nombre.indexOf(" ");
				String id = nombre.substring(0, posicion);
				try
				{
					statement.executeUpdate("DELETE FROM articulos WHERE idArticulo="+id);
					d.setVisible(true);

				}
				catch(SQLException se)
				{
					d1.setVisible(true);
				}

				dispose();
				Frame art=new bajaArticulo();
				art.setVisible(true);
				


			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(86, 176, 100, 44);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Atrás");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Frame art=new Articulos();
				art.setVisible(true);
			}

		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1_1.setBounds(238, 176, 100, 44);
		contentPane.add(btnNewButton_1_1);
		
		

	}
}
