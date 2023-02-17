package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sql_operation.Inquiry;

import java.awt.BorderLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DepartmentInquiryMenu extends JFrame{
	private static final long serialVersionUID = 1158897003425269987L;
	private JPanel contentPane;
	public DepartmentInquiryMenu()
	{
		//页面大小调整
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//提示文字
		JLabel lblid = new JLabel("查找该学院的全部学生");
		lblid.setFont(new Font("宋体", Font.PLAIN, 20));
		lblid.setBounds(37, 39, 300, 40);
		contentPane.add(lblid, BorderLayout.NORTH);
		
		JLabel lblid_1 = new JLabel("输入要查找的学院编号");
		lblid_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblid_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_1.setBounds(60, 90, 150, 23);
		contentPane.add(lblid_1, BorderLayout.NORTH);
		
		
		JButton btnNewButton = new JButton("查找");
		btnNewButton.setBounds(185, 195, 72, 23);
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setSize(72, 23);
		textField.setLocation(210, 90);
		contentPane.add(textField, BorderLayout.SOUTH);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = textField.getText();
				Inquiry inquiry = new Inquiry();
				List<Vector<String>> result = inquiry.inquiryDepartment(item);
				DepartmentTable table = new DepartmentTable(result);
				table.setVisible(true);
			}
		});
		
		
	}
}