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

public class CourseUpdateMenu extends JFrame{
	private static final long serialVersionUID = 1158897003425269987L;
	private JPanel contentPane;
	public CourseUpdateMenu()
	{
		//页面大小调整
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//提示文字
		JLabel lblid = new JLabel("输入要修改的课程编号");
		lblid.setFont(new Font("宋体", Font.PLAIN, 14));
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBounds(37, 20, 150, 23);
		contentPane.add(lblid, BorderLayout.NORTH);
		
		JLabel lblid_8 = new JLabel("填入要修改的信息：");
		lblid_8.setFont(new Font("宋体", Font.PLAIN, 14));
		lblid_8.setBounds(37, 50, 250, 23);
		contentPane.add(lblid_8, BorderLayout.NORTH);
		
		JTextField textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setSize(72, 23);
		textField_7.setLocation(190, 20);
		contentPane.add(textField_7, BorderLayout.CENTER);
		
		JLabel lblid_1 = new JLabel("课程编号");
		lblid_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_1.setBounds(55, 75, 70, 23);
		contentPane.add(lblid_1, BorderLayout.NORTH);
		
		JLabel lblid_2 = new JLabel("课程名称");
		lblid_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_2.setBounds(55, 100, 70, 23);
		contentPane.add(lblid_2, BorderLayout.NORTH);
		
		JLabel lblid_3 = new JLabel("课程教材");
		lblid_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_3.setBounds(55, 125, 70, 23);
		contentPane.add(lblid_3, BorderLayout.NORTH);
		
		JLabel lblid_4 = new JLabel("所在教学楼");
		lblid_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_4.setBounds(55, 150, 70, 23);
		contentPane.add(lblid_4, BorderLayout.NORTH);
		
		JLabel lblid_5 = new JLabel("所在房间号");
		lblid_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_5.setBounds(250, 75, 70, 23);
		contentPane.add(lblid_5, BorderLayout.CENTER);
		
		JLabel lblid_6 = new JLabel("教师工号");
		lblid_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_6.setBounds(250, 100, 70, 23);
		contentPane.add(lblid_6, BorderLayout.NORTH);
		
		JLabel lblid_7 = new JLabel("学院编号");
		lblid_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblid_7.setBounds(250, 125, 70, 23);
		contentPane.add(lblid_7, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.setBounds(185, 195, 72, 23);
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setSize(72, 23);
		textField.setLocation(130, 75);
		contentPane.add(textField, BorderLayout.CENTER);
		
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setSize(72, 23);
		textField_1.setLocation(130, 100);
		contentPane.add(textField_1, BorderLayout.CENTER);
		
		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setSize(72, 23);
		textField_2.setLocation(130, 125);
		contentPane.add(textField_2, BorderLayout.CENTER);
		
		JTextField textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setSize(72, 23);
		textField_3.setLocation(130, 150);
		contentPane.add(textField_3, BorderLayout.CENTER);
		
		JTextField textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setSize(72, 23);
		textField_4.setLocation(330, 75);
		contentPane.add(textField_4, BorderLayout.CENTER);
		
		JTextField textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setSize(72, 23);
		textField_5.setLocation(330, 100);
		contentPane.add(textField_5, BorderLayout.CENTER);
		
		JTextField textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setSize(72, 23);
		textField_6.setLocation(330, 125);
		contentPane.add(textField_6, BorderLayout.CENTER);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cid_ori = textField_7.getText();
				String cid = textField.getText();
				String cname = textField_1.getText();
				String book = textField_2.getText();
				String lbuild = textField_3.getText();
				String lroom = textField_4.getText();
				String tid = textField_5.getText();
				String did = textField_6.getText();
				String[] items = {cid, cname, book, lbuild, lroom, tid, did, cid_ori};
				Update update = new Update();
				update.updateCourse(items);
			}
		});
		
		
	}
}