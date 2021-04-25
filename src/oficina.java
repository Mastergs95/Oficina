import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import net.proteanit.sql.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class oficina extends JFrame {

	String id;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTable table_1;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					oficina frame = new oficina();
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
	public oficina() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registar Cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(52, 44, 179, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(52, 102, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NIF");
		lblNewLabel_2.setBounds(52, 186, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Telefone");
		lblNewLabel_3.setBounds(52, 143, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(115, 99, 145, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(115, 140, 145, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(115, 183, 145, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Registar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql="Insert into Cliente (Nome, Telefone,NIF, Morada) Values ('"+textField.getText()+"', '"+textField_1.getText()+"', '"+textField_2.getText()+"','"+textField_3.getText()+"')";
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina","root","");
					Statement stmt=con.createStatement();
					int ok=stmt.executeUpdate(sql);
						System.out.println("inseridas " + ok + " linha(s) na BD");
					}catch (Exception ex1) {
						System.out.println(ex1);
				}
			}
		});
		btnNewButton.setBounds(37, 320, 80, 37);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			
			
		});
		scrollPane.setBounds(293, 44, 388, 282);
		contentPane.add(scrollPane);
		
		
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table_1.getSelectedRow();
				TableModel model = table_1.getModel();
				id = model.getValueAt(index,0).toString();
				String nome = model.getValueAt(index,1).toString();
				textField.setText(nome);
				String telefone = model.getValueAt(index,2).toString();
				textField_1.setText(telefone);
				String NIF = model.getValueAt(index,3).toString();
				textField_2.setText(NIF);
				String Morada = model.getValueAt(index,4).toString();
				textField_3.setText(Morada);				
			}
		});
		scrollPane.setViewportView(table_1);
		
		
		JButton btnNewButton_1 = new JButton("Ver Detalhes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina","root","");
					Statement stmt=con.createStatement();
					String sql="Select * from cliente";
					ResultSet rs=stmt.executeQuery(sql);
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					System.out.println("Carregar dados para a tabela");
					con.close();
				}catch(Exception ee) {
					System.out.println(ee);
				}
			}
		});
		btnNewButton_1.setBounds(293, 342, 118, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Apagar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Pretende apagar registro?", "Atenção", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					try {
						String sql="delete from Cliente where id='"+id+"'";
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina","root","");
						Statement stmt=con.createStatement();
						int ok=stmt.executeUpdate(sql);
							System.out.println("Foi apagada " + ok + " linha na BD");
						}catch (Exception ex1) {
							System.out.println(ex1);
					}
					}
				}
			
	
		});
		btnNewButton_2.setBounds(142, 320, 89, 37);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Editar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Pretende apagar registro?", "Atenção", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					try {
						String sql="Update Cliente set Nome='"+textField.getText()+"', Telefone= '"+textField_1.getText()+"', NIF='"+textField_2.getText()+"', Morada='"+textField_3.getText()+"' where id='"+id+"'";
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina","root","");
						Statement stmt=con.createStatement();
						int ok=stmt.executeUpdate(sql);
							System.out.println("Foi editada " + ok + " linha na BD");
						}catch (Exception ex1) {
							System.out.println(ex1);
					}
					}
				}
				
			
		});
		btnNewButton_3.setBounds(92, 368, 89, 33);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("Morada");
		lblNewLabel_2_1.setBounds(52, 224, 46, 14);
		contentPane.add(lblNewLabel_2_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(115, 221, 145, 20);
		contentPane.add(textField_3);
	}
}
