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
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Autocar extends JFrame {
	String matricula;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Autocar frame = new Autocar();
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
	public Autocar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 993, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Matricula");
		lblNewLabel.setBounds(103, 113, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Marca");
		lblNewLabel_1.setBounds(103, 164, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Modelo");
		lblNewLabel_2.setBounds(103, 216, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(188, 110, 119, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(188, 161, 119, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(188, 213, 119, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(367, 98, 561, 250);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				matricula= model.getValueAt(index,0).toString();
				String Matricula = model.getValueAt(index,0).toString();
				textField.setText(Matricula);
				String Marca = model.getValueAt(index,1).toString();
				textField_1.setText(Marca);
				String Modelo = model.getValueAt(index,2).toString();
				textField_2.setText(Modelo);
				String Ano = model.getValueAt(index,3).toString();
				textField_3.setText(Ano);	
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Registar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql="Insert into automovél (Matricula, Marca, Modelo, Ano) Values ('"+textField.getText()+"', '"+textField_1.getText()+"', '"+textField_2.getText()+"','"+textField_3.getText()+"')";
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
		btnNewButton.setBounds(53, 368, 96, 44);
		contentPane.add(btnNewButton);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Pretende apagar registro?", "Atenção", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					try {
						String sql="delete from automovél where Matricula='"+matricula+"'";
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
		btnApagar.setBounds(185, 368, 103, 44);
		contentPane.add(btnApagar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Pretende editar registro?", "Atenção", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					try {
						String sql="Update automovél set Matricula='"+textField.getText()+"', Marca= '"+textField_1.getText()+"', Modelo='"+textField_2.getText()+"', Ano='"+textField_3.getText()+"' where matricula='"+matricula+"'";
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
		btnEditar.setBounds(124, 432, 96, 44);
		contentPane.add(btnEditar);
		
		JButton btnVerDetalhes = new JButton("Ver detalhes");
		btnVerDetalhes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina","root","");
					Statement stmt=con.createStatement();
					String sql="Select * from automovél";
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
		btnVerDetalhes.setBounds(610, 389, 89, 23);
		contentPane.add(btnVerDetalhes);
		
		JLabel lblNewLabel_3 = new JLabel("Automov\u00E9l");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(53, 33, 145, 23);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("Ano");
		lblNewLabel_2_1.setBounds(103, 266, 46, 14);
		contentPane.add(lblNewLabel_2_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(188, 263, 119, 20);
		contentPane.add(textField_3);
	}
}
