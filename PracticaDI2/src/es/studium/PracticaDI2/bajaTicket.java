package es.studium.PracticaDI2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class bajaTicket extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public bajaTicket() {
		setTitle("Baja Ticket");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(224, 82, 142, 20);
		contentPane.add(spinner);
		
		JLabel lblNewLabel = new JLabel("Seleccione el tícket:");
		lblNewLabel.setBounds(50, 82, 164, 19);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Baja");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
	             Frame art=new Tickets();
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
	             Frame art=new Tickets();
	             art.setVisible(true);
			}
			
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1_1.setBounds(238, 176, 100, 44);
		contentPane.add(btnNewButton_1_1);
	}
}
