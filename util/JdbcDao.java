package com.tencent.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tencent.entity.Cartoon;

public class JdbcDao {
	public static void addChapter(String tableName,String name){
		Connection conn=Jdbc.getConnection();
		String sql ="insert into "+tableName+"(name)"+
				"values(?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addCartoon(String tableName,String name,String cartoonName,String chapterNum){
		Connection conn = Jdbc.getConnection();
		String sql = "insert into "+tableName+"(id,url)"+
		"values(?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(name.substring(0,name.indexOf("."))));
			String url="https://www.hackerwe.cn/CartoonPic/"+cartoonName+"/"+chapterNum+"/"+name;
			ps.setString(2, url);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
