package sql_operation;

import java.sql.SQLException;

import com.jdbc.DatabaseConnection;

public class Update {

	static String sql = null;  
    static DatabaseConnection db1 = null;  
    static int ret;  
    
    public void updateDepartment(String[] items) {

    	boolean isFirst = true;
    	String sql = "update department set ";
    	
    	if(!items[0].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "did = '" + items[0] + "'";
    	}
    	if(!items[1].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "dname = '" + items[1] + "'";
    	}
    	if(!items[2].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "tid = '" + items[2] + "'";
    	}
    	if(!items[3].equals("")) {
    		sql = sql + " where did = '" + items[3] + "'";
    	}
    	System.out.println(sql);
    	
//		Scanner in = new Scanner(System.in);
//		String sql = null;
//    	System.out.println("输入要修改的系的编号：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "update department set where did = '" + inputs[0] + "'";
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("修改成功！");
            }
            else {
            	System.out.println("修改失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
    public void updateTeacher(String[] items) {

    	boolean isFirst = true;
    	String sql = "update teacher set ";
    	
    	if(!items[0].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "tid = '" + items[0] + "'";
    	}
    	if(!items[1].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "tname = '" + items[1] + "'";
    	}
    	if(!items[2].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "tsex = '" + items[2] + "'";
    	}
    	if(!items[3].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "level = '" + items[3] + "'";
    	}
    	if(!items[4].equals("")) {
    		sql = sql + " where tid = '" + items[4] + "'";
    	}
    	System.out.println(sql);
//		Scanner in = new Scanner(System.in);
//		String sql = null;
//    	System.out.println("输入要修改的老师的工号：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "update teacher set where tid = '" + inputs[0] + "'";
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("修改成功！");
            }
            else {
            	System.out.println("修改失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
    public void updateStudent(String[] items) {

    	boolean isFirst = true;
    	String sql = "update student set ";
    	
    	if(!items[0].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "sid = '" + items[0] + "'";
    	}
    	if(!items[1].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "sname = '" + items[1] + "'";
    	}
    	if(!items[2].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "ssex = '" + items[2] + "'";
    	}
    	if(!items[3].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "class = '" + items[3] + "'";
    	}
    	if(!items[4].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "grade = " + items[4];
    	}
    	if(!items[5].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "birth_year = " + items[5];
    	}
    	if(!items[6].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "tid = '" + items[6] + "'";
    	}
    	if(!items[7].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "did = '" + items[7] + "'";
    	}
    	if(!items[8].equals("")) {
    		sql = sql + " where sid = '" + items[8] + "'";
    	}
    	System.out.println(sql);
//		Scanner in = new Scanner(System.in);
//		String sql = null;
//    	System.out.println("输入要修改的学生的学号：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "update student set where sid = '" + inputs[0] + "'";
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("修改成功！");
            }
            else {
            	System.out.println("修改失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
    public void updateStudy(String[] items) {

    	boolean isFirst = true;
    	String sql = "update student set score = ";
    	
    	if(!items[0].equals("")) {
    		
    		sql = sql + items[0];
    	}
    	if(!items[1].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    			sql = sql + " where ";
    		}
    		else {
    			sql = sql + " and ";
    		}
    		sql = sql + "sid = '" + items[1] + "'";
    	}
    	
    	if(!items[2].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    			sql = sql + " where ";
    		}
    		else {
    			sql = sql + " and ";
    		}
    		sql = sql + "cid = '" + items[2] + "'";
    	}
    	System.out.println(sql);
    	
//		Scanner in = new Scanner(System.in);
//		String sql = null;
//    	System.out.println("输入要修改的学生选课信息的学号和课号(空格隔开)：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "update study set where sid = '" + inputs[0] + "' and cid = '" + inputs[1] + "'";
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("修改成功！");
            }
            else {
            	System.out.println("修改失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
    public void updateCourse(String[] items) {

    	boolean isFirst = true;
    	String sql = "update course set ";
    	
    	if(!items[0].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "cid = '" + items[0] + "'";
    	}
    	if(!items[1].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "cname = '" + items[1] + "'";
    	}
    	if(!items[2].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "book = '" + items[2] + "'";
    	}
    	if(!items[3].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "lbulid = '" + items[3] + "'";
    	}
    	if(!items[4].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "lroom = '" + items[4] + "'";
    	}
    	if(!items[5].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "tid = '" + items[5] + "'";
    	}
    	if(!items[6].equals("")) {
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    			sql = sql + ", ";
    		}
    		sql = sql + "did = '" + items[6] + "'";
    	}
    	if(!items[7].equals("")) {
    		sql = sql + " where cid = '" + items[7] + "'";
    	}
    	System.out.println(sql);
//		Scanner in = new Scanner(System.in);
//		String sql = null;
//    	System.out.println("输入要修改的课程的课号：");
//		String input = in.nextLine();
//    	String[] inputs = input.split(" ");
//    	sql = "update course set where cid = '" + inputs[0] + "'";
//    	System.out.println(sql);
    	db1 = new DatabaseConnection(sql);
    	try {  
            ret = db1.pst.executeUpdate();
            if(ret > 0) {
            	System.out.println("修改成功！");
            }
            else {
            	System.out.println("修改失败！");
            }
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
//    	in.close();
    }
    
}
