package es.studium.PracticaDI2;
/**
 * Pantalla alta Ticket
 * 
 * @author migue
 * @since 01/01/2021
 * @version 1.0
 */
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Choice;

public class altaTicket extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField tfFecha;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Tiendecita?useSSL=false";
	String login = "root";
	String password = "Studium2019;";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	/**
	 * Se inicia el main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					altaTicket frame = new altaTicket();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creamos el frame de alta Ticket
	 */
	public altaTicket() {
		setTitle("Alta Ticket");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		contentPane.setLayout(null);
		Choice choice = new Choice();
		choice.setBounds(264, 66, 160, 18);
		contentPane.add(choice);
		Dialog d = new Dialog(this, "Operación Inserción", true);
		Label e1 = new Label ("Operación realizada correctamente!");
		d.setLayout(new FlowLayout());
	
		d.add(e1);
		d.setSize(250,150);
		d.setLocationRelativeTo(null);
		Dialog c = new Dialog(this, "Operación Inserción", true);
		Label e2 = new Label ("Faltan datos!");
		c.setLayout(new FlowLayout());
	
		c.add(e2);
		c.setSize(250,150);
		c.setLocationRelativeTo(null);
		JButton ok = new JButton("Ok");
		d.add(ok);
		JButton ok2 = new JButton("Ok");

		c.add(ok2);
		/**
		 * Funcionalidad del botón ok
		 */
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
				 dispose();
	             Frame art=new altaTicket();
	             art.setVisible(true);
			}
		});
		/**
		 * Funcionalidad del botón ok2
		 */
		ok2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setVisible(false);
				 dispose();
	             Frame art=new altaTicket();
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
		connection = DriverManager.getConnection(url, login, password);
		}
		catch(SQLException e)
		{
		System.out.println("Se produjo un error al conectar a la Base de Datos");
		}
		//Preparar el statement
		try
		{
			statement =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			rs=statement.executeQuery("SELECT* FROM Articulos");

			while(rs.next())
			{

			choice.add(rs.getString("idArticulo")+" "+rs.getString("nombreArticulo"));
			}

		}
		catch(SQLException e)
		{
		System.out.println("Error en la sentencia SQL");
		}

		JLabel lblNewLabel = new JLabel("Artículos del ticket:");
		lblNewLabel.setBounds(66, 66, 158, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Total:");
		lblNewLabel_1.setBounds(66, 120, 158, 13);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(344, 117, 45, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		/**
		 * Funcionalidad del botón Atrás
		 */
		JButton btnNewButton = new JButton("Atrás");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
	             Frame art=new Tickets();
	             art.setVisible(true);
			}
		});
		btnNewButton.setBounds(246, 178, 100, 45);
		contentPane.add(btnNewButton);
		/**
		 * Funcionalidad del botón Alta
		 */
		JButton btnNewButton_1 = new JButton("Alta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					/**
					 * Con esto vamos a pasar de formato de DATA europeo a americano
					 */
				String articulo = new String(choice.getSelectedItem());
				int pos = articulo.indexOf(" ");
					String nEscuadron = articulo.substring(0,pos);
					String fecha = new String(tfFecha.getText());
				
				int posicion = fecha.indexOf("/");

				String dia = fecha.substring(0,posicion);

				String subcadena=fecha.substring(posicion+1);
				int posicion2 = subcadena.indexOf("/");

				String mes = subcadena.substring(0,posicion2);
					
				String año=subcadena.substring(posicion2+1);
				
				String fechaValida=(año+"/"+mes+"/"+dia);
				statement.executeUpdate("INSERT INTO tickets VALUES (null,'"+fechaValida+"','"+textField_1.getText()+"','"+nEscuadron+"')");
			

				tfFecha.setText("");
				textField_1.setText("");
				
				d.setVisible(true);
				}
				catch(SQLException se)
				{
				c.setVisible(true);
				}
				catch(StringIndexOutOfBoundsException se2)
				{
				c.setVisible(true);
				}
			

				
				
				 
	             
	             
			
				}
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(82, 178, 100, 44);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha de compra:");
		lblNewLabel_2.setBounds(66, 34, 145, 13);
		contentPane.add(lblNewLabel_2);
		
		tfFecha = new JTextField();
		tfFecha.setBounds(234, 31, 155, 19);
		contentPane.add(tfFecha);
		tfFecha.setColumns(10);
		
		
	}
}
