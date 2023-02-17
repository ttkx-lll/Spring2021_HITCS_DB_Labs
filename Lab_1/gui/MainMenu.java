package gui;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame{
	private static final long serialVersionUID = 1158897003425269987L;
	private JPanel contentPane;
	public MainMenu()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//页面大小调整
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//提示文字
		JLabel lblid = new JLabel("选择要操作的表格");
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBounds(37, 39, 200, 23);
		contentPane.add(lblid);
		
		//下拉框
		JComboBox<String> comboBox = new JComboBox<String>(new String[] {
				"学院列表",
				"老师列表", 
				"学生列表", 
				"课程列表", 
				"学生选课表"});
		comboBox.setMaximumRowCount(5);
		comboBox.setBounds(76, 95, 265, 35);
		contentPane.add(comboBox, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("增加");
		btnNewButton.setBounds(24, 185, 72, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int item = comboBox.getSelectedIndex();
				switch(item) {
				case 0:
					DepartmentInsertMenu dmenu = new DepartmentInsertMenu();
					dmenu.setVisible(true);
					break;
				case 1:
					TeacherInsertMenu tmenu = new TeacherInsertMenu();
					tmenu.setVisible(true);
					break;
				case 2:
					StudentInsertMenu smenu = new StudentInsertMenu();
					smenu.setVisible(true);
					break;
				case 3:
					CourseInsertMenu cmenu = new CourseInsertMenu();
					cmenu.setVisible(true);
					break;
				case 4:
					StudyInsertMenu stmenu = new StudyInsertMenu();
					stmenu.setVisible(true);
					break;
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.setBounds(124, 185, 72, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int item = comboBox.getSelectedIndex();
				switch(item) {
				case 0:
					DepartmentDeleteMenu dmenu = new DepartmentDeleteMenu();
					dmenu.setVisible(true);
					break;
				case 1:
					TeacherDeleteMenu tmenu = new TeacherDeleteMenu();
					tmenu.setVisible(true);
					break;
				case 2:
					StudentDeleteMenu smenu = new StudentDeleteMenu();
					smenu.setVisible(true);
					break;
				case 3:
					CourseDeleteMenu cmenu = new CourseDeleteMenu();
					cmenu.setVisible(true);
					break;
				case 4:
					StudyDeleteMenu stmenu = new StudyDeleteMenu();
					stmenu.setVisible(true);
					break;
				}
			}
		});
		
		JButton btnNewButton_2 = new JButton("修改");
		btnNewButton_2.setBounds(228, 185, 72, 23);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int item = comboBox.getSelectedIndex();
				switch(item) {
				case 0:
					DepartmentUpdateMenu dmenu = new DepartmentUpdateMenu();
					dmenu.setVisible(true);
					break;
				case 1:
					TeacherUpdateMenu tmenu = new TeacherUpdateMenu();
					tmenu.setVisible(true);
					break;
				case 2:
					StudentUpdateMenu smenu = new StudentUpdateMenu();
					smenu.setVisible(true);
					break;
				case 3:
					CourseUpdateMenu cmenu = new CourseUpdateMenu();
					cmenu.setVisible(true);
					break;
				case 4:
					StudyUpdateMenu stmenu = new StudyUpdateMenu();
					stmenu.setVisible(true);
					break;
				}
			}
		});
		
		JButton btnNewButton_3 = new JButton("查询");
		btnNewButton_3.setBounds(329, 185, 72, 23);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int item = comboBox.getSelectedIndex();
				
				switch(item) {
				case 0:
					DepartmentInquiryMenu dmenu = new DepartmentInquiryMenu();
					dmenu.setVisible(true);
					break;
				case 1:
					TeacherInquiryMenu tmenu = new TeacherInquiryMenu();
					tmenu.setVisible(true);
					break;
				case 2:
					StudentInquiryMenu smenu = new StudentInquiryMenu();
					smenu.setVisible(true);
					break;
				case 3:
					CourseInquiryMenu cmenu = new CourseInquiryMenu();
					cmenu.setVisible(true);
					break;
				case 4:
					System.out.println("没有设置查询操作！");
					break;
				}
			}
		});
		
	}
}