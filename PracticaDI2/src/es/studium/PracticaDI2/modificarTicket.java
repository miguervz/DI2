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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.Dialog;

public class modificarTicket extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	 private Statement statement;
	 private ResultSet rs;
	 private Connection connection;
	 private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modificarTicket frame = new modificarTicket();
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
	public modificarTicket() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Tiendecita?useSSL=false";
		String login = "root";
		String password = "Studium2019;";
		setTitle("Modificar Ticket");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setLocationRelativeTo(null);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Choice choice = new Choice();
		choice.setBounds(346, 32, 72, 18);
		contentPane.add(choice);
		
		textField_1 = new JTextField();
		textField_1.setBounds(332, 96, 72, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField = new JTextField();
		textField.setBounds(308, 67, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		textField_2 = new JTextField();
		textField_2.setBounds(359, 125, 45, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		Dialog d = new Dialog(this, "Operación Modificación", true);
		Label e1 = new Label ("Operación realizada correctamente!");
		d.setLayout(new FlowLayout());
		d.add(e1);
		d.setSize(250,150);
		d.setLocationRelativeTo(null);
		JButton ok = new JButton("Ok");
		d.add(ok);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
	             
			}
		});
		Dialog d1 = new Dialog(this, "Operación Modificación", true);
		Label e2 = new Label ("Introduzca un id de un artículo existente!");
		d1.setLayout(new FlowLayout());
		d1.add(e2);
		d1.setSize(250,150);
		d1.setLocationRelativeTo(null);
		JButton ok2 = new JButton("Ok");
		d1.add(ok2);
		ok2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d1.setVisible(false);
	             
			}
		});
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

			rs=statement.executeQuery("SELECT* FROM tickets");

			while(rs.next())
			{

				choice.add(rs.getInt("idTicket")+"\n");
				
			}


		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL");
		}

		try
		{
		statement =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

		rs=statement.executeQuery("SELECT* FROM tickets");
		if(rs.next())
		{
			
			textField_1.setText(rs.getString("fechaTicket"));
			textField_2.setText(rs.getString("precioTicket"));
			textField.setText(rs.getString("idArticulo"));
		


				
		}}
		catch(SQLException e)
		{
		System.out.println("Error en la sentencia SQL");
		}
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				String id = new String(choice.getSelectedItem());


				try
				{
					
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

					 rs = statement.executeQuery("SELECT* FROM tickets WHERE idTicket="+id);
					if(rs.next())
					{

						textField_1.setText(rs.getString("fechaTicket"));
						textField_2.setText(rs.getString("precioTicket"));
						textField.setText(rs.getString("idArticulo"));




					}}
				catch(SQLException e1)
				{
					System.out.println("Error en la sentencia SQL");
				}
			}
		});
		JLabel lblNewLabel = new JLabel("Seleccione el ticket:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(66, 31, 133, 19);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1_1 = new JButton("Atrás");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
	             Frame art=new Tickets();
	             art.setVisible(true);
			}
			
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1_1.setBounds(258, 173, 100, 44);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha de compra:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(66, 99, 143, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Total ticket:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setBounds(66, 133, 154, 13);
		contentPane.add(lblNewLabel_3);
		
	
		
		JButton btnNewButton_1_1_1 = new JButton("Modificar");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = new String(choice.getSelectedItem());

				try
				{
				statement =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

				statement.executeUpdate("UPDATE tickets SET fechaTicket='"+textField_1.getText()+"',idArticulo='"+textField.getText()+"',precioTicket='"+textField_2.getText()+"' where idTicket="+id);
				d.setVisible(true);
}
				catch(SQLException e1)
				{
				d1.setVisible(true);
				}

				dispose();
	             Frame art=new modificarTicket();
	             art.setVisible(true);
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1_1_1.setBounds(78, 173, 100, 44);
		contentPane.add(btnNewButton_1_1_1);
		
	
		
		JLabel lblNewLabel_2_1 = new JLabel("Id del art\u00EDculo:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_1.setBounds(66, 76, 143, 13);
		contentPane.add(lblNewLabel_2_1);
		
	
	}
}
