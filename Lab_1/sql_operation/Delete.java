package sql_operation;

import java.sql.SQLException;

import com.jdbc.DatabaseConnection;

public class Delete {

	static String sql = null;  
    static DatabaseConnection db1 = null;  
    static int ret;  
    
    public void deleteDepartment(String[] items) {

    	boolean isFirst = true;
    	String sql = "delete from department where ";
    	if(!items[0].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "did = " + "'" + items[0] + "' ";
    	}
    	if(!items[1].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "dname = " + "'" + items[1] + "' ";
    	}
    	if(!items[2].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "tid = " + "'" + items[2] + "' ";
    	}
    	System.out.println(sql);
//		Scanner in = new Scanner(System.in);
//		String sql = null;
//    	System.out.println("输入要删除的系的编号：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "delete from department where did = '" + inputs[0] + "'";
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("删除成功！");
            }
            else {
            	System.out.println("删除失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
    public void deleteTeacher(String[] items) {

    	boolean isFirst = true;
    	String sql = "delete from teacher where ";
    	if(!items[0].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "tid = " + "'" + items[0] + "' ";
    	}
    	if(!items[1].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "tname = " + "'" + items[1] + "' ";
    	}
    	if(!items[2].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "tsex = " + "'" + items[2] + "' ";
    	}
    	if(!items[3].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "level = " + "'" + items[3] + "' ";
    	}
    	System.out.println(sql);
//		Scanner in = new Scanner(System.in);
//		String sql = null;
//    	System.out.println("输入要删除的老师的工号：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "delete from teacher where tid = '" + inputs[0] + "'";
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("删除成功！");
            }
            else {
            	System.out.println("删除失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
    public void deleteStudent(String[] items) {

    	boolean isFirst = true;
    	String sql = "delete from student where ";
    	if(!items[0].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "sid = " + "'" + items[0] + "' ";    	}
    	if(!items[1].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "sname = " + "'" + items[1] + "' ";
    	}
    	if(!items[2].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "ssex = " + "'" + items[2] + "' ";
    	}
    	if(!items[3].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "class = " + "'" + items[3] + "' ";
    	}
    	if(!items[4].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "grade = " + items[4] + " ";
    	}
    	if(!items[5].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "birth_year = " + items[5] + " ";
    	}
    	if(!items[6].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "tid = " + "'" + items[6] + "' ";
    	}
    	if(!items[7].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "did = " + "'" + items[7] + "' ";
    	}
    	System.out.println(sql);
//		Scanner in = new Scanner(System.in);
//		String sql = null;
//    	System.out.println("输入要删除的学生的学号：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "delete from student where sid = '" + inputs[0] + "'";
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("删除成功！");
            }
            else {
            	System.out.println("删除失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
    public void deleteStudy(String[] items) {

    	boolean isFirst = true;
    	String sql = "delete from study where ";
    	if(!items[0].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "sid = " + "'" + items[0] + "' ";
    	}
    	if(!items[1].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "cid = " + "'" + items[1] + "' ";
    	}
    	if(!items[2].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "score = "+ items[2] + " ";
    	}
    	System.out.println(sql);
//		Scanner in = new Scanner(System.in);
//		String sql = null;
//    	System.out.println("输入要删除的学生选课信息的学号和课号(空格隔开)：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "delete from study where sid = '" + inputs[0] + "' and cid = '" + inputs[1] + "'";
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("删除成功！");
            }
            else {
            	System.out.println("删除失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
    public void deleteCourse(String[] items) {

    	boolean isFirst = true;
    	String sql = "delete from course where ";
    	if(!items[0].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "cid = " + "'" + items[0] + "' ";
    	}
    	if(!items[1].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "cname = " + "'" + items[1] + "' ";
    	}
    	if(!items[2].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "book = " + "'" + items[2] + "' ";
    	}
    	if(!items[3].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "lbuild = " + "'" + items[3] + "' ";
    	}
    	if(!items[4].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "lroom = " + "'" + items[4] + "' ";
    	}
    	if(!items[5].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "tid = " + "'" + items[5] + "' ";
    	}
    	if(!items[6].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + "and ";
    		}
    		sql = sql + "did = " + "'" + items[6] + "' ";
    	}
    	System.out.println(sql);
//		Scanner in = new Scanner(System.in);
//		String sql = null;
//    	System.out.println("输入要删除的课程的课号：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "delete from course where cid = '" + inputs[0] + "'";
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("删除成功！");
            }
            else {
            	System.out.println("删除失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
    
}
