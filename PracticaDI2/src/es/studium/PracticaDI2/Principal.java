package es.studium.PracticaDI2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;

public class Principal extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setTitle("Tiendecita");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JButton bArticulos = new JButton("ARTICULOS");
		bArticulos.setFont(new Font("Tahoma", Font.BOLD, 20));
		bArticulos.addActionListener(new ActionListener()
				{
			
			public void actionPerformed(ActionEvent e) {
			 dispose();
             Frame art=new Articulos();
             art.setVisible(true);
            
			}
				});
		bArticulos.setBounds(10, 72, 192, 104);
		contentPane.add(bArticulos);
		
		JButton bTickets = new JButton("TICKETS");
		bTickets.setFont(new Font("Tahoma", Font.BOLD, 20));
		bTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
	             Frame art=new Tickets();
	             art.setVisible(true);
			}
		});
		bTickets.setBounds(234, 72, 192, 104);
		contentPane.add(bTickets);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
