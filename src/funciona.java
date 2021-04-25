import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class funciona extends JFrame {
	String id;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					funciona frame = new funciona();
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
	public funciona() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(100, 82, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Morada");
		lblNewLabel_1.setBounds(100, 135, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NSS");
		lblNewLabel_2.setBounds(100, 238, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("NIB");
		lblNewLabel_3.setBounds(100, 284, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(184, 79, 112, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(184, 132, 112, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(184, 235, 112, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(184, 281, 112, 20);
		contentPane.add(textField_3);
		
		JLabel lblNewLabel_4 = new JLabel("Funcion\u00E1rio");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(21, 11, 112, 33);
		contentPane.add(lblNewLabel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 79, 482, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				id = model.getValueAt(index,0).toString();
				String Nome = model.getValueAt(index,1).toString();
				textField.setText(Nome);
				String Morada = model.getValueAt(index,2).toString();
				textField_1.setText(Morada);
				String Telefone = model.getValueAt(index,3).toString();
				textField_4.setText(Telefone);
				String NSS = model.getValueAt(index,4).toString();
				textField_2.setText(NSS);	
				String NIB = model.getValueAt(index,5).toString();
				textField_3.setText(NIB);	
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Registar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql="Insert into funcionário (Nome, Morada,Telefone, NSS, NIB) Values ('"+textField.getText()+"', '"+textField_1.getText()+"', '"+textField_4.getText()+"','"+textField_2.getText()+"','"+textField_3.getText()+"')";
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
		btnNewButton.setBounds(74, 342, 89, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Editar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Pretende editar registro?", "Atenção", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					try {
						String sql="Update funcionário set Nome='"+textField.getText()+"', Morada= '"+textField_1.getText()+"', Telefone='"+textField_4.getText()+"',NSS='"+textField_3.getText()+"',NIB='"+textField_3.getText()+"  where NºFuncionário='"+id+"'";
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
		btnNewButton_1.setBounds(187, 342, 89, 33);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Apagar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Pretende apagar registro?", "Atenção", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					try {
						String sql="delete from funcionário where NºFuncionário='"+id+"'";
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
		btnNewButton_2.setBounds(127, 405, 89, 33);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Ver detalhes");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina","root","");
					Statement stmt=con.createStatement();
					String sql="Select * from funcionário";
					ResultSet rs=stmt.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					System.out.println("Carregar dados para a tabela");
					con.close();
				}catch(Exception ee) {
					System.out.println(ee);
				}
			}
		});
		btnNewButton_3.setBounds(515, 369, 119, 33);
		contentPane.add(btnNewButton_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(184, 183, 112, 20);
		contentPane.add(textField_4);
		
		JLabel lblNewLabel_2_1 = new JLabel("Telefone");
		lblNewLabel_2_1.setBounds(100, 186, 46, 14);
		contentPane.add(lblNewLabel_2_1);
	}
}
