package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DatabaseConnection {
	//连接信息
	
	//连接类型：mysql
	public static final String name = "com.mysql.jdbc.Driver";  
	//mysql的账号
	public static final  String userName = "root"; 
	//mysql设置的密码
	public static final  String password = "123456";
	//数据库连接信息
	/*
	 * 第一个是java连接数据库协议，
	 * 中间的是要连接的ip地址和端口号，
	 * localhost是本地ip，
	 * 后面的是你要连接的数据库的名字
	 */
	public static final  String url = "jdbc:mysql://localhost:3306/teaching?useSSL=false";
	public Connection connection = null;
	public PreparedStatement pst = null;
	
	/*
	 * 构造器
	 */
	public DatabaseConnection(String sql) {
		
		try {  
            Class.forName(name);//指定连接类型  
            connection = DriverManager.getConnection(url,userName,password);//获取连接  
            pst = connection.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	/*
	 * 连接关闭
	 */
	public void close() {  
        try {  
            this.connection.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }
}
