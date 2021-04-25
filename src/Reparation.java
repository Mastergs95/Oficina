import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Choice;
import java.awt.Font;
import javax.swing.JEditorPane;
import com.toedter.calendar.JDayChooser;

import net.proteanit.sql.DbUtils;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Reparation extends JFrame {
	String id;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reparation frame = new Reparation();
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
	public Reparation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Data");
		lblNewLabel.setBounds(101, 102, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kms");
		lblNewLabel_1.setBounds(101, 163, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_2.setBounds(101, 240, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Valor");
		lblNewLabel_3.setBounds(101, 316, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(196, 160, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(196, 237, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(199, 96, 83, 20);
		contentPane.add(dateChooser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(368, 75, 487, 300);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = table.getSelectedRow();
				TableModel model = table.getModel();				
				id = model.getValueAt(index,0).toString();
				String Data = model.getValueAt(index,1).toString();
				
				try {
				Date dt=new SimpleDateFormat("dd/MM/yyyy").parse(Data);
					dateChooser.setDate(dt);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				String kms = model.getValueAt(index,2).toString();
				textField_1.setText(kms);
				String Descrição = model.getValueAt(index,3).toString();
				textField_2.setText(Descrição);
				String Valor = model.getValueAt(index,4).toString();
				textField.setText(Valor);	
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Ver detalhes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/oficina","root","");
					Statement stmt=con.createStatement();
					String sql="Select * from reparação";
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
		btnNewButton.setBounds(567, 420, 93, 32);
		contentPane.add(btnNewButton);
		
	
		
		JButton btnNewButton_1 = new JButton("Registar");
		btnNewButton_1.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String sd = sdf.format(dateChooser.getDate());
				
				
				try {						
					String sql="Insert into reparação (Data, kms, Descrição, Valor ) Values ('"+sd+"', '"+textField_1.getText()+"', '"+textField_2.getText()+"','"+textField.getText()+"')";
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
		btnNewButton_1.setBounds(58, 390, 89, 32);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Apagar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Pretende apagar registro?", "Atenção", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					try {
						String sql="delete from reparação where id_reparacao='"+id+"'";
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
		btnNewButton_2.setBounds(196, 390, 86, 32);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Editar");
		btnNewButton_3.setBounds(121, 444, 86, 32);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_4 = new JLabel("Repara\u00E7\u00E3o");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(46, 26, 101, 20);
		contentPane.add(lblNewLabel_4);
		
	
		
		textField = new JTextField();
		textField.setBounds(196, 313, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
