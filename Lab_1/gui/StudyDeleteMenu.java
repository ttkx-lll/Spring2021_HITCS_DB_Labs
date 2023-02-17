package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sql_operation.Delete;

import java.awt.BorderLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class StudyDeleteMenu extends JFrame{
	private static final long serialVersionUID = 1158897003425269987L;
	private JPanel contentPane;
	public StudyDeleteMenu()
	{
		//页面大小调整
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//提示文字
		JLabel lblid = new JLabel("输入要删除的学生选课信息");
		lblid.setFont(new Font("宋体", Font.PLAIN, 14));
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBounds(37, 39, 200, 23);
		contentPane.add(lblid, BorderLayout.NORTH);
		
		JLabel lblid_1 = new JLabel("学生学号");
		lblid_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_1.setBounds(90, 75, 60, 23);
		contentPane.add(lblid_1, BorderLayout.NORTH);
		
		JLabel lblid_2 = new JLabel("课程编号");
		lblid_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_2.setBounds(90, 100, 60, 23);
		contentPane.add(lblid_2, BorderLayout.NORTH);
		
		JLabel lblid_3 = new JLabel("考试成绩");
		lblid_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_3.setBounds(90, 125, 60, 23);
		contentPane.add(lblid_3, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("删除");
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
				String sid = textField.getText();
				String cid = textField_1.getText();
				String score = textField_2.getText();
				String[] items = {sid, cid, score};
				Delete delete = new Delete();
				delete.deleteStudy(items);
			}
		});
		
		
	}
}