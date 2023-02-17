package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sql_operation.Update;

import java.awt.BorderLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DepartmentUpdateMenu extends JFrame{
	private static final long serialVersionUID = 1158897003425269987L;
	private JPanel contentPane;
	public DepartmentUpdateMenu()
	{
		//页面大小调整
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//提示文字
		JLabel lblid = new JLabel("输入要修改的学院编号");
		lblid.setFont(new Font("宋体", Font.PLAIN, 14));
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBounds(60, 20, 150, 23);
		contentPane.add(lblid, BorderLayout.NORTH);
		
		JLabel lblid_5 = new JLabel("填入要修改的信息：");
		lblid_5.setFont(new Font("宋体", Font.PLAIN, 14));
		lblid_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_5.setBounds(37, 50, 250, 23);
		contentPane.add(lblid_5, BorderLayout.NORTH);
		
		JTextField textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setSize(72, 23);
		textField_4.setLocation(220, 20);
		contentPane.add(textField_4, BorderLayout.CENTER);
		
		JLabel lblid_1 = new JLabel("学院编号");
		lblid_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_1.setBounds(90, 75, 60, 23);
		contentPane.add(lblid_1, BorderLayout.NORTH);
		
		JLabel lblid_2 = new JLabel("学院名称");
		lblid_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_2.setBounds(90, 100, 60, 23);
		contentPane.add(lblid_2, BorderLayout.NORTH);
		
		JLabel lblid_3 = new JLabel("院长工号");
		lblid_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_3.setBounds(90, 125, 60, 23);
		contentPane.add(lblid_3, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("修改");
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
		
		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setSize(72, 23);
		textField_2.setLocation(150, 125);
		contentPane.add(textField_2, BorderLayout.CENTER);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String did_ori = textField_4.getText();
				String did = textField.getText();
				String dname = textField_1.getText();
				String tid = textField_2.getText();
				String[] items = {did, dname, tid, did_ori};
				Update update = new Update();
				update.updateDepartment(items);
			}
		});
		
		
	}
}