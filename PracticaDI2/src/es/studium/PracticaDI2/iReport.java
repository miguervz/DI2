package es.studium.PracticaDI2;
/**
 * Pantalla IReport
 * 
 * @author migue
 * @since 01/01/2021
 * @version 1.0
 */
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.File;
import java.util.HashMap;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class iReport extends JFrame {


	private JPanel contentPane;
	private Statement statement;
	private ResultSet rs;
private String fechaInicio;
private String fechaFinal;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Se inicia el main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					iReport frame = new iReport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Se crea la pantalla para generear el iReport
	 */
	public iReport() {

		setTitle("iReport");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 265, 176);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);





        JDialog d = new JDialog(this, "Error",true);
        JLabel lblD = new JLabel("Introduce los campos correctamente");
        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			d.setVisible(false);
			}});

        d.setLayout(new FlowLayout());
		d.add(lblD);
		d.setSize(320,150);
		d.add(ok);
		d.setLocationRelativeTo(null);
        
		JLabel lblNewLabel = new JLabel("Desde:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(46, 31, 62, 19);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Aceptar");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				/**
				 * Se crea el método para general el ireport
				 */
				try
				{
					fechaInicio= convertirFecha(textField.getText());
				fechaFinal= convertirFecha(textField_1.getText());
					// Compilar el informe generando fichero jasper
					JasperCompileManager.compileReportToFile("Tickets.jrxml");
					System.out.println("Fichero Tickets.jasper generado CORRECTAMENTE!");
					// Objeto para guardar parámetros necesarios para el informe
					HashMap<String,Object> parametros = new HashMap<String,Object>();
					parametros.put("fechaInicio", fechaInicio);
					parametros.put("fechaFinal", fechaFinal);
					// Cargar el informe compilado
					JasperReport report = (JasperReport)
							JRLoader.loadObjectFromFile("informeTiendecita.jasper");

					// Conectar a la base de datos para sacar la información
					Class.forName("com.mysql.jdbc.Driver");
					String servidor = "jdbc:mysql://localhost:3306/tiendecita?useSSL=false";
					String usuarioDB = "root";
					String passwordDB = "Studium2019;";
					Connection conexion = DriverManager.getConnection(servidor, usuarioDB,passwordDB);
					// Completar el informe con los datos de la base de datos
					JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);

					// Mostrar el informe en JasperViewer
					JasperViewer.viewReport(print, false);
					// Para exportarlo a pdf
					
				}
				
				catch (StringIndexOutOfBoundsException e1)
				{
					d.setVisible(true);	}
			
			catch (Exception e1)
			{
d.setVisible(true);
			}}


		});
		btnNewButton.setBounds(79, 96, 85, 21);
		contentPane.add(btnNewButton);

		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblHasta.setBounds(46, 66, 154, 19);
		contentPane.add(lblHasta);

		textField = new JTextField();
		textField.setBounds(112, 31, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(112, 66, 96, 19);
		contentPane.add(textField_1);

		JButton btnNewButton_1_1 = new JButton("Atrás");

		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Frame art=new Articulos();
				art.setVisible(true);
			}

		});

	}

	 /**

     * Método que devuelve la fecha en formato europeo

     * @return fecha

     */
	public static String convertirFecha(String fecha) {

		int posicion = fecha.indexOf("-");
		String dia = fecha.substring(0,posicion);
		String subcadena=fecha.substring(posicion+1);
		int posicion2 = subcadena.indexOf("-");
		String mes = subcadena.substring(0,posicion2);
		String año=subcadena.substring(posicion2+1);
		String fechaValida=(año+"-"+mes+"-"+dia);
		return fechaValida;
	}
	 //Cierre del método
	}
