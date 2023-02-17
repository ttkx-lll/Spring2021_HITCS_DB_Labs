package sql_operation;

import java.sql.SQLException;
import com.jdbc.DatabaseConnection;

public class Insert {
	static String sql = null;  
    static DatabaseConnection db1 = null;  
    static int ret;  
    
    public void insertDepartment(String[] items) {

    	String sql = "insert into department (";
    	String item = ") values (";
    	if(!items[0].equals("")) {
    		sql = sql + "did";
    		item = item + "'" + items[0] + "'";
    	}
    	if(!items[1].equals("")) {
    		sql = sql + ", dname";
    		item = item + ", " + "'" + items[1] + "'";
    	}
    	if(!items[2].equals("")) {
    		sql = sql + ", tid";
    		item = item + ", " + "'" + items[2] + "'";
    	}
    	sql = sql + item + ")";
    	System.out.println(sql);
//		Scanner in = new Scanner(System.in);
//    	System.out.println("输入要增加的系的编号、名称、系主任的工号(空格隔开)：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "insert into department values ('" 
//    			+ inputs[0] + "', '" + inputs[1] + "', '" + inputs[2] + "')" ;
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("插入成功！");
            }
            else {
            	System.out.println("插入失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
    public void insertTeacher(String[] items) {

    	String sql = "insert into teacher (";
    	String item = ") values (";
    	if(!items[0].equals("")) {
    		sql = sql + "tid";
    		item = item + "'" + items[0] + "'";
    	}
    	if(!items[1].equals("")) {
    		sql = sql + ", tname";
    		item = item + ", " + "'" + items[1] + "'";
    	}
    	if(!items[2].equals("")) {
    		sql = sql + ", tsex";
    		item = item + ", " + "'" + items[2] + "'";
    	}
    	if(!items[3].equals("")) {
    		sql = sql + ", level";
    		item = item + ", " + "'" + items[3] + "'";
    	}
    	sql = sql + item + ")";
    	System.out.println(sql);
//		Scanner in = new Scanner(System.in);
//    	System.out.println("输入要增加的老师的工号、名字、性别以及级别(空格隔开)：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "insert into teacher values ('" 
//    			+ inputs[0] + "', '" + inputs[1] + "', '" + inputs[2] + "', '" + inputs[3] + "')" ;
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("插入成功！");
            }
            else {
            	System.out.println("插入失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
    public void insertStudent(String[] items) {

    	String sql = "insert into student (";
    	String item = ") values (";
    	if(!items[0].equals("")) {
    		sql = sql + "sid";
    		item = item + "'" + items[0] + "'";
    	}
    	if(!items[1].equals("")) {
    		sql = sql + ", sname";
    		item = item + ", " + "'" + items[1] + "'";
    	}
    	if(!items[2].equals("")) {
    		sql = sql + ", ssex";
    		item = item + ", " + "'" + items[2] + "'";
    	}
    	if(!items[3].equals("")) {
    		sql = sql + ", class";
    		item = item + ", " + "'" + items[3] + "'";
    	}
    	if(!items[4].equals("")) {
    		sql = sql + ", grade";
    		item = item + ", " + items[4];
    	}
    	if(!items[5].equals("")) {
    		sql = sql + ", birth_year";
    		item = item + ", " + items[5];
    	}
    	if(!items[6].equals("")) {
    		sql = sql + ", tid";
    		item = item + ", " + "'" + items[6] + "'";
    	}
    	if(!items[7].equals("")) {
    		sql = sql + ", did";
    		item = item + ", " + "'" + items[7] + "'";
    	}
    	sql = sql + item + ")";
    	System.out.println(sql);
//		Scanner in = new Scanner(System.in);
//		String sql = null;
//    	System.out.println("输入要增加的学生的学号、名字、性别、班号、入学年份、出生年份、导师工号、以及所在系的编号(空格隔开)：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "insert into student values ('" 
//    			+ inputs[0] + "', '" + inputs[1] + "', '" + inputs[2] + "', '" + inputs[3] + "', " + inputs[4] + ", " + inputs[5] + ", '" + inputs[6] + "', '" + inputs[7] + "')" ;
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("插入成功！");
            }
            else {
            	System.out.println("插入失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
    public void insertStudy(String[] items) {

    	String sql = "insert into study (";
    	String item = ") values (";
    	if(!items[0].equals("")) {
    		sql = sql + "sid";
    		item = item + "'" + items[0] + "'";
    	}
    	if(!items[1].equals("")) {
    		sql = sql + ", cid";
    		item = item + ", " + "'" + items[1] + "'";
    	}
    	if(!items[2].equals("")) {
    		sql = sql + ", score";
    		item = item + ", " + items[2];
    	}
    	sql = sql + item + ")";
    	System.out.println(sql);
//		Scanner in = new Scanner(System.in);
//		String sql = null;
//    	System.out.println("输入要增加的学生选课信息的学号、课号以及成绩(空格隔开)：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "insert into study values ('" 
//    			+ inputs[0] + "', '" + inputs[1] + "', " + inputs[3] + ")" ;
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("插入成功！");
            }
            else {
            	System.out.println("插入失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
    public void insertCourse(String[] items) {

    	String sql = "insert into course (";
    	String item = ") values (";
    	if(!items[0].equals("")) {
    		sql = sql + "cid";
    		item = item + "'" + items[0] + "'";
    	}
    	if(!items[1].equals("")) {
    		sql = sql + ", cname";
    		item = item + ", " + "'" + items[1] + "'";
    	}
    	if(!items[2].equals("")) {
    		sql = sql + ", book";
    		item = item + ", " + "'" + items[2] + "'";
    	}
    	if(!items[3].equals("")) {
    		sql = sql + ", lbuild";
    		item = item + ", " + "'" + items[3] + "'";
    	}
    	if(!items[4].equals("")) {
    		sql = sql + ", lroom";
    		item = item + ", " + "'" + items[4] + "'";
    	}
    	if(!items[5].equals("")) {
    		sql = sql + ", tid";
    		item = item + ", " + "'" + items[5] + "'";
    	}
    	if(!items[6].equals("")) {
    		sql = sql + ", did";
    		item = item + ", " + "'" + items[6] + "'";
    	}
    	sql = sql + item + ")";
    	System.out.println(sql);
//		Scanner in = new Scanner(System.in);
//		String sql = null;
//    	System.out.println("输入要增加的课程的课号、课名、教材名称、上课地点的楼名、上课地点的房间号、授课教师以及开设学院(空格隔开)：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "insert into course values ('" 
//    			+ inputs[0] + "', '" + inputs[1] + "', '" + inputs[2] + "', '" + inputs[3] + "', '" + inputs[4] + "', '" + inputs[5] + "', '" + inputs[6] + "')" ;
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("插入成功！");
            }
            else {
            	System.out.println("插入失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
//    public static void main(String[] args) {  
//        Insert in = new Insert();
//        in.insertDepartment();
//        
//    }
}
