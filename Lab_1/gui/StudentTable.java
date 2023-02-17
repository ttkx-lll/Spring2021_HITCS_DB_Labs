package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;

import java.util.List;
import java.util.Vector;
import java.awt.Font;
import javax.swing.JTable;

public class StudentTable extends JFrame{
	private static final long serialVersionUID = 1158897003425269987L;
	private JPanel contentPane;
	private JTable table;
	public StudentTable(List<Vector<String>> result)
	{
		
		//页面大小调整
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblid = new JLabel("学生选课列表");
		lblid.setFont(new Font("宋体", Font.PLAIN, 16));
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBounds(130, 10, 150, 23);
		contentPane.add(lblid, BorderLayout.NORTH);
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"课程编号", "课程名称", "成绩"
				}
			);
		table = new JTable(model);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, tcr);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 35, 420, 225);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		for(Vector<String> row : result) {
			model.addRow(row);
		}
	}
	
}