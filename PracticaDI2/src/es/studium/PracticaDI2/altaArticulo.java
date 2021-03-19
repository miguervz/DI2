package es.studium.PracticaDI2;
/**
 * Pantalla alta artículo
 * 
 * @author migue
 * @since 01/01/2021
 * @version 1.0
 */
import java.awt.BorderLayout;
import java.awt.Button;
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

public class altaArticulo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Tiendecita?useSSL=false";
	String login = "root";
	String password = "Studium2019;";
	Connection connection = null;
	Statement statement = null;


	private JTextField textField_3;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					altaArticulo frame = new altaArticulo();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creación de la frame altaArticulo
	 */
	public altaArticulo() {
		setResizable(false);
		setTitle("Alta Artículo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		Dialog d = new Dialog(this, "Operación Inserción", true);
		Dialog c = new Dialog(this, "Operación Inserción", true);
		Label e2 = new Label ("Introduzca algún dato!");
		Label e1 = new Label ("Operación realizada correctamente!");
		d.setLayout(new FlowLayout());
		d.add(e1);
		d.setSize(250,150);
		d.setLocationRelativeTo(null);
		JButton ok = new JButton("Ok");
		JButton ok2 = new JButton("Ok");
		c.setLayout(new FlowLayout());
		c.add(e2);
		c.setSize(250,150);
		c.setLocationRelativeTo(null);
		c.add(ok);
		d.add(ok2);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setVisible(false);
	            
			}
		});
		/**
		 * Funcionalidad del botón ok2
		 */
		ok2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	             d.setVisible(false);
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
		//Establecer la conexión con la base de datos
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
		statement =
		connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		}
		catch(SQLException e)
		{
		System.out.println("Error en la sentencia SQL");
		}
		
		JLabel lblNewLabel = new JLabel("Introducir descripción:");
		lblNewLabel.setBounds(66, 66, 158, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Introducir precio:");
		lblNewLabel_1.setBounds(66, 120, 158, 13);
		contentPane.add(lblNewLabel_1);
		
	
		
		textField = new JTextField();
		textField.setBounds(234, 66, 155, 44);
		contentPane.add(textField);
		textField.setColumns(10);
		
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
	             Frame art=new Articulos();
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

				try {
					statement.executeUpdate("INSERT INTO articulos VALUES (null,'"+textField_2.getText()+"','"+textField.getText()+"','"+textField_1.getText()+"','"+textField_3.getText()+"')");   
					d.setVisible(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					c.setVisible(true);
				}
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");

				textField.setText("");
				
				 
	             
	             
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(82, 178, 100, 44);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Introduce el nombre:");
		lblNewLabel_2.setBounds(66, 34, 145, 13);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(234, 31, 155, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(344, 146, 45, 19);
		contentPane.add(textField_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("Introducir número en stock:");
		lblNewLabel_1_1.setBounds(66, 155, 158, 13);
		contentPane.add(lblNewLabel_1_1);
	}
}
