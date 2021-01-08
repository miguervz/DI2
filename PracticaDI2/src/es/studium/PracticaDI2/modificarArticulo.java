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

public class modificarArticulo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	 private Statement statement;
	 private ResultSet rs;
	 private Connection connection;
	 private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modificarArticulo frame = new modificarArticulo();
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
	public modificarArticulo() {
		setResizable(false);
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Tiendecita?useSSL=false";
		String login = "root";
		String password = "Studium2019;";
		setTitle("Modificar Artículos");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		Choice choice = new Choice();
		choice.setBounds(282, 32, 160, 18);
		contentPane.add(choice);
		JLabel lblNewLabel = new JLabel("Seleccione el artículo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(66, 31, 133, 19);
		contentPane.add(lblNewLabel);
		Dialog d = new Dialog(this, "Operación Inserción", true);
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
		textField_3 = new JTextField();
		textField_3.setBounds(291, 64, 119, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		textField = new JTextField();
		textField.setBounds(230, 87, 180, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(365, 110, 45, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(365, 133, 45, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
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

		try
		{
		statement =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

		rs=statement.executeQuery("SELECT* FROM articulos");
		if(rs.next())
		{
			
			textField.setText(rs.getString("descripcionArticulo"));
			textField_1.setText(rs.getString("precioArticulo"));
			textField_2.setText(rs.getString("numeroStock"));
			textField_3.setText(rs.getString("nombreArticulo"));
		


				
		}}
		catch(SQLException e)
		{
		System.out.println("Error en la sentencia SQL");
		}
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				String nombreItem = new String(choice.getSelectedItem());


				try
				{
					int posicion = nombreItem.indexOf(" ");
					
					String id = nombreItem.substring(0, posicion);
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

					 rs = statement.executeQuery("SELECT* FROM articulos WHERE idArticulo="+id);
					if(rs.next())
					{

						textField.setText(rs.getString("descripcionArticulo"));
						textField_1.setText(rs.getString("precioArticulo"));
						textField_2.setText(rs.getString("numeroStock"));
						textField_3.setText(rs.getString("nombreArticulo"));





					}}
				catch(SQLException e1)
				{
					System.out.println("Error en la sentencia SQL");
				}
			}
		});
		
		JButton btnNewButton_1_1 = new JButton("Atrás");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
	             Frame art=new Articulos();
	             art.setVisible(true);
			}
			
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1_1.setBounds(258, 173, 100, 44);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(66, 90, 133, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(66, 113, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Número de artículos en stock:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setBounds(66, 136, 154, 13);
		contentPane.add(lblNewLabel_3);
		
		
		
		JButton btnNewButton_1_1_1 = new JButton("Modificar");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.setVisible(true);
				String nombre1 = new String(choice.getSelectedItem());

				int posicion = nombre1.indexOf(" ");
				String id = nombre1.substring(0, posicion);
				try
				{
				statement =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

				statement.executeUpdate("UPDATE articulos SET nombreArticulo='"+textField_3.getText()+"',descripcionArticulo='"+textField.getText()+"',numeroStock='"+textField_2.getText()+"',precioArticulo='"+textField_1.getText()+"' where idArticulo="+id);
				if(rs.next())
				{
					
					textField.setText(rs.getString("descripcionArticulo"));
					textField_1.setText(rs.getString("precioArticulo"));
					textField_2.setText(rs.getString("numeroStock"));
					textField_3.setText(rs.getString("nombreArticulo"));


						
				}}
				catch(SQLException e1)
				{
				System.out.println("Error en la sentencia SQL");
				}
				dispose();
	             Frame art=new modificarArticulo();
	             art.setVisible(true);
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1_1_1.setBounds(78, 173, 100, 44);
		contentPane.add(btnNewButton_1_1_1);
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1_1.setBounds(66, 67, 133, 13);
		contentPane.add(lblNewLabel_1_1);
		
		
	}
}
