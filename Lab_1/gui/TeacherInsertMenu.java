package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sql_operation.Insert;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.BoxLayout;

public class TeacherInsertMenu extends JFrame{
	private static final long serialVersionUID = 1158897003425269987L;
	private JPanel contentPane;
	public TeacherInsertMenu()
	{
		//页面大小调整
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//提示文字
		JLabel lblid = new JLabel("输入要插入的教师信息");
		lblid.setFont(new Font("宋体", Font.PLAIN, 14));
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBounds(37, 39, 150, 23);
		contentPane.add(lblid, BorderLayout.NORTH);
		
		JLabel lblid_1 = new JLabel("工 号*");
		lblid_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_1.setBounds(90, 75, 50, 23);
		contentPane.add(lblid_1, BorderLayout.NORTH);
		
		JLabel lblid_2 = new JLabel("姓 名*");
		lblid_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_2.setBounds(90, 100, 50, 23);
		contentPane.add(lblid_2, BorderLayout.NORTH);
		
		JLabel lblid_3 = new JLabel("性 别*");
		lblid_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_3.setBounds(90, 125, 50, 23);
		contentPane.add(lblid_3, BorderLayout.NORTH);
		
		JLabel lblid_4 = new JLabel("级 别");
		lblid_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_4.setBounds(90, 150, 50, 23);
		contentPane.add(lblid_4, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("增加");
		btnNewButton.setBounds(185, 195, 72, 23);
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setSize(72, 23);
		textField.setLocation(150, 75);
		contentPane.add(textField, BorderLayout.CENTER);
		
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setSize(72, 23);
		textField_1.setLocation(150, 100);
		contentPane.add(textField_1, BorderLayout.CENTER);
		
//		JTextField textField_2 = new JTextField();
//		textField_2.setColumns(10);
//		textField_2.setSize(72, 23);
//		textField_2.setLocation(150, 125);
//		contentPane.add(textField_2, BorderLayout.CENTER);
		
		JTextField textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setSize(72, 23);
		textField_3.setLocation(150, 150);
		contentPane.add(textField_3, BorderLayout.CENTER);
		
		JRadioButton man =  new JRadioButton("M", true);
		JRadioButton woman =  new JRadioButton("W", false);
		ButtonGroup anniuzu = new ButtonGroup();         //按钮组
		anniuzu.add(man);
		anniuzu.add(woman);
		JPanel panel = new JPanel();
		panel.setSize(72, 23);
		panel.setLocation(150, 125);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(man);
		panel.add(woman);
		contentPane.add(panel);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tid = textField.getText();
				String tname = textField_1.getText();
				String tsex = "";
				if(man.isSelected()) {
					tsex = "M";
				}
				else if(woman.isSelected()) {
					tsex = "W";
				}
				String level = textField_3.getText();
				String[] items = {tid, tname, tsex, level};
				Insert insert = new Insert();
				insert.insertTeacher(items);
			}
		});
		
		
	}
}