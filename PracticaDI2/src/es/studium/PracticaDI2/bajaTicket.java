package es.studium.PracticaDI2;
/**
 * Pantalla baja Ticket
 * 
 * @author migue
 * @since 01/01/2021
 * @version 1.0
 */
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

public class bajaTicket extends JFrame {

	private JPanel contentPane;
	private Statement statement;
	private ResultSet rs;
	private Connection connection;
	/**
	 * Se inicia el main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bajaTicket frame = new bajaTicket();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Se crea la pantalla de baja Ticket
	 */
	public bajaTicket() {
		setTitle("Baja Ticket");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		connection = null;
		statement = null;
		
		rs = null;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Tiendecita?useSSL=false";
		String login = "root";
		String password = "Studium2019;";
		JLabel lblNewLabel = new JLabel("Seleccione el tícket:");
		lblNewLabel.setBounds(50, 82, 164, 19);
		contentPane.add(lblNewLabel);
		Choice choice = new Choice();
		choice.setBounds(220, 83, 190, 18);
		contentPane.add(choice);
		Dialog d = new Dialog(this, "Operación Inserción", true);
		Label e1 = new Label ("Operación realizada correctamente!");
		d.setLayout(new FlowLayout());
		d.add(e1);
		d.setSize(250,150);
		d.setLocationRelativeTo(null);
		JButton ok = new JButton("Ok");
		d.add(ok);
		/**
		 * Funcionalidad del botón ok
		 */
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Frame art=new bajaTicket();
				art.setVisible(true);

			}
		});
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

			rs=statement.executeQuery("SELECT* FROM tickets");

			while(rs.next())
			{

				choice.add(rs.getInt("idTicket")+" con fecha "+rs.getString("fechaTicket")+"\n");

			}


		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
		}
		/**
		 * Funcionalidad del botón 1
		 */
		JButton btnNewButton_1 = new JButton("Baja");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				String nombre = new String(choice.getSelectedItem());

				int posicion = nombre.indexOf(" ");
				String id = nombre.substring(0, posicion);
				try
				{
					statement.executeUpdate("DELETE FROM tickets WHERE idTicket="+id);
					

				}
				catch(SQLException se)
				{
					System.out.println("No se puede dar de baja si pertenece a un ticket.");
				}

				d.setVisible(true);
				


			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(86, 176, 100, 44);
		contentPane.add(btnNewButton_1);
		/**
		 * Funcionalidad del botón 1_1
		 */
		JButton btnNewButton_1_1 = new JButton("Atrás");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
	             Frame art=new Tickets();
	             art.setVisible(true);
			}
			
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1_1.setBounds(238, 176, 100, 44);
		contentPane.add(btnNewButton_1_1);
		
		
	}
}
