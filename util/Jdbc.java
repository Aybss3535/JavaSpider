package com.tencent.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
	private static final String URL="jdbc:mysql://127.0.0.1:3306/cartoon?serverTimezone=UTC&useSSL=false";
	private static final String USER="root";
	private static final String PASSWORD="1234";
	public static Connection conn;
	static{
		try {
			//1.加载驱动程序
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2.获得数据库连接
			 conn= DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		return conn;
	}

}
