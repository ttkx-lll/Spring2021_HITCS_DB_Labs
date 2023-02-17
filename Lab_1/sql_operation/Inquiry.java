package sql_operation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jdbc.DatabaseConnection;

public class Inquiry {
	
	static String sql = null;  
    static DatabaseConnection db1 = null;  
    static ResultSet ret = null;  
    
    public List<Vector<String>> inquiryDepartment(String item) {

    	// 查找该学院的全部学生信息
    	String sql = "select sid, sname, ssex, class, grade, birth_year, tname,"
    			+ " dname from student join teacher using(tid) join department"
    			+ " using(did) where did = '" + item + "'";    	
    	System.out.println(sql);

    	List<Vector<String>> result = new ArrayList<Vector<String>>();
    	
    	db1 = new DatabaseConnection(sql);
        try {  
            ret = db1.pst.executeQuery();
            try {
    			while(ret.next()) {
    				Vector<String> row = new Vector<String>();
    				row.add(ret.getString(1));
    				row.add(ret.getString(2));
    				row.add(ret.getString(3));
    				row.add(ret.getString(4));
    				row.add(ret.getString(5));
    				row.add(ret.getString(6));
    				row.add(ret.getString(7));
    				row.add(ret.getString(8));
    				result.add(row);
    			}
    		} catch (SQLException e1) {
    			e1.printStackTrace();
    		}
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return result;
    }
    
    public List<Vector<String>> inquiryTeacher(String item) {

    	// 查找老师所带的全部学生信息
    	String sql = "select sid, sname, ssex, class, grade, birth_year, dname "
    			+ "from student join department using(did) where student.tid = '" + item + "'";    	
    	System.out.println(sql);
    	
    	List<Vector<String>> result = new ArrayList<Vector<String>>();
    	
    	db1 = new DatabaseConnection(sql);
        try {  
            ret = db1.pst.executeQuery();
            try {
    			while(ret.next()) {
    				Vector<String> row = new Vector<String>();
    				row.add(ret.getString(1));
    				row.add(ret.getString(2));
    				row.add(ret.getString(3));
    				row.add(ret.getString(4));
    				row.add(ret.getString(5));
    				row.add(ret.getString(6));
    				row.add(ret.getString(7));
    				result.add(row);
    			}
    		} catch (SQLException e1) {
    			e1.printStackTrace();
    		}
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return result;
    }
    
    public List<Vector<String>> inquiryStudent(String item) {

    	// 查找该学生所上的全部课程及成绩
    	String sql = "select cid, cname, score from course join study using (cid) where sid = '" + item + "'";
    	System.out.println(sql);

    	List<Vector<String>> result = new ArrayList<Vector<String>>();
    	
    	db1 = new DatabaseConnection(sql);
        try {  
            ret = db1.pst.executeQuery();
            try {
    			while(ret.next()) {
    				Vector<String> row = new Vector<String>();
    				row.add(ret.getString(1));
    				row.add(ret.getString(2));
    				row.add(ret.getString(3));
    				result.add(row);
    			}
    		} catch (SQLException e1) {
    			e1.printStackTrace();
    		}
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return result;
    }
    
    public List<Vector<String>> inquiryCourse(String item) {

    	// 查找选课的全部学生
    	String sql = "select sid, sname, class, grade, dname, score from study join"
    			+ " student using (sid) join course using (cid) join department on"
    			+ " (department.did = student.did) where cid = '" + item + "'";
    	
    	System.out.println(sql);

    	List<Vector<String>> result = new ArrayList<Vector<String>>();
    	
    	db1 = new DatabaseConnection(sql);
        try {  
            ret = db1.pst.executeQuery();
            try {
    			while(ret.next()) {
    				Vector<String> row = new Vector<String>();
    				row.add(ret.getString(1));
    				row.add(ret.getString(2));
    				row.add(ret.getString(3));
    				row.add(ret.getString(4));
    				row.add(ret.getString(5));
    				row.add(ret.getString(6));
    				result.add(row);
    			}
    		} catch (SQLException e1) {
    			e1.printStackTrace();
    		}
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return result;
    }
    
}
