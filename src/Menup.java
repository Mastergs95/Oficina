import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menup extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menup frame = new Menup();
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
	public Menup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 767, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Repara\u00E7\u00E3o");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				
					try {
						Reparation frame = new Reparation();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
			
			
			}
			});
	
		btnNewButton.setBounds(66, 142, 114, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Automov\u00E9l");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				try {
					Autocar frame = new Autocar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(383, 142, 103, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Funcion\u00E1rio");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				try {
					funciona frame = new funciona();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(215, 142, 114, 39);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cliente");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				try {
					oficina frame = new oficina();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(529, 142, 103, 39);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Menu Principal");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(308, 45, 114, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_3_1 = new JButton("Donos Automov\u00E9is");
		btnNewButton_3_1.setBounds(290, 272, 168, 39);
		contentPane.add(btnNewButton_3_1);
	}
}


